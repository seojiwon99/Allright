package com.ar.lighthouse.elastic.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.MinAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ar.lighthouse.common.Criteria;
import com.ar.lighthouse.common.PageDTO;
import com.ar.lighthouse.main.service.MainPageService;





@Controller
public class elaController {
    String hostname ="localhost";
    int port = 9200;
    String scheme = "http";
    HttpHost host = new HttpHost(hostname, port, scheme);
    HttpHost hos = new HttpHost("e6c0-111-118-98-16.ngrok-free.app");
    //RestClientBuilder restClientBuilder = RestClient.builder(host);
    RestClientBuilder restClientBuilder = RestClient.builder(hos);
    
    RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);
	
	
	@Autowired
	MainPageService service;	
	//페이징 적용. =======================================================================================================
	 private void applyPaging(SearchSourceBuilder searchSourceBuilder, int pageNum) {
	        searchSourceBuilder.from(((pageNum - 1) * 9));
	        searchSourceBuilder.size(9);
	    }
	//필터 적용. ========================================================================================================
	 private void applyFilter(SearchSourceBuilder searchSourceBuilder, String order) {
		 if(order == "" || order == null) { //null exception 처리
			 return;
		 }
		 else if(order.equalsIgnoreCase("new")) { // 신상품 순 정렬
			 searchSourceBuilder.sort(SortBuilders.fieldSort("product_regdate").order(SortOrder.DESC));
		 }else if(order.equalsIgnoreCase("review")) {
			 searchSourceBuilder.sort(SortBuilders.fieldSort("review_count").order(SortOrder.DESC));
			 //DB에 넣을 때부터 아마 review Count * review Star 평균을 한 score 필드를 하나 선언해줘야 할 듯.
		 }else if(order.equalsIgnoreCase("price_desc")) {
			 searchSourceBuilder.sort(SortBuilders.fieldSort("product_cost").order(SortOrder.DESC));
		 }else if(order.equalsIgnoreCase("price_asc")) {
			 searchSourceBuilder.sort(SortBuilders.fieldSort("product_cost").order(SortOrder.ASC));
		 }
	    }
	//쿼리를 실행하는 메소드 ================================================================================================
    private List<Map<String, Object>> executeSearch(String indexName, SearchSourceBuilder searchSourceBuilder) {
        List<Map<String, Object>> finalResult = new ArrayList<>();

        try {
            SearchRequest request = new SearchRequest(indexName);
            request.source(searchSourceBuilder);

            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            SearchHits searchHits = response.getHits();

            for (SearchHit hit : searchHits) {
                Map<String, Object> result = hit.getSourceAsMap();
                finalResult.add(result);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to execute search.", e);
        }

        return finalResult;
    }

	/*
	 * 실제로 사용할 쿼리들 목록 ================================================================
	*/
    
    public List<Map<String, Object>> FinalMatchQuery(String indexName, int pageNum, String keyword, String ctg, String order, Double minPrice, Double maxPrice){
    	SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    	BoolQueryBuilder boolQuery = QueryBuilders.boolQuery(); // 실행되야할 boolQuery builder
    	
    	if(minPrice != null && maxPrice != null) {
    		minPrice = (double)minPrice;
	    	maxPrice = (double)maxPrice;
	    	RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("product_cost")
	                .gte(minPrice)  // Greater than equal
	                .lte(maxPrice); // Less than equal 
	    	boolQuery.must(rangeQuery); // 범위 추가
    	}

	    if (ctg != null && !ctg.isEmpty()) { //카테고리가 있을 경우 boolQuery에 추가 
	        boolQuery.must(QueryBuilders.matchQuery("category_code", ctg)); 
	        QueryBuilders.wildcardQuery(indexName, order);
	    }

	    if (keyword != null && !keyword.isEmpty()) { // 키워드가 있을 경우 boolQuery에 추가 
	        boolQuery.should(QueryBuilders.matchQuery("product_name", keyword));
	    }
        
        searchSourceBuilder.query(boolQuery); // 설정
    	
    	applyFilter(searchSourceBuilder, order);
        applyPaging(searchSourceBuilder, pageNum);
        
    	return executeSearch(indexName, searchSourceBuilder);
    }
    	//어디다 쓸지 모르겠음
	    public List<Map<String, Object>> filterWithBoolQuery(String indexName, List<Map<String, Object>> filters, int pageNum) {
	        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
	        for (Map<String, Object> filter : filters) {
	            boolQueryBuilder.filter(QueryBuilders.matchQuery(filter.get("field").toString(), filter.get("value")));
	        }
	
	        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
	        searchSourceBuilder.query(boolQueryBuilder);
	        applyPaging(searchSourceBuilder, pageNum);
	
	        return executeSearch(indexName, searchSourceBuilder);
	    }
    

		//카운트 하는 쿼리 (queryBuilder를 받아서 그에 맞는 TotalHits를 가져온다) -------------------------------------------이하 카운트
		public int countWithQuery(String indexName, QueryBuilder queryBuilder) {
		    try {
		        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		        searchSourceBuilder.query(queryBuilder);
		        searchSourceBuilder.size(0); // hits는 필요없고, 전체 수만 가져오면 된다.
		        SearchRequest request = new SearchRequest(indexName);
		        request.source(searchSourceBuilder);

		        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
		        TotalHits hitCount = response.getHits().getTotalHits();

		        return (int) hitCount.value;
		    } catch (IOException e) {
		        throw new RuntimeException("Failed to execute search.", e);
		    }
		}
		//들어온 카테고리, 키워드에 따라 쿼리 변동
		public int queryCount(String indexName, String keyword, String ctg, Double minPrice, Double maxPrice) {
		    BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		   

		    if (ctg != null && !ctg.isEmpty()) { //카테고리가 있을 경우
		        boolQuery.must(QueryBuilders.matchQuery("category_code", ctg));
		    }

		    if (keyword != null && !keyword.isEmpty()) { // 키워드가 있을 경우
		        boolQuery.should(QueryBuilders.matchQuery("product_name", keyword));
		    }
		    if (minPrice != null && maxPrice != null) { //가격범위가 있을 경우
		    	minPrice = (double)minPrice;
		    	maxPrice = (double)maxPrice;
		    	boolQuery.must(QueryBuilders.rangeQuery("product_cost").gte(minPrice).lte(maxPrice));
		    }

		    return countWithQuery(indexName, boolQuery);
		}
	
		//insert or update 아직 쓸 일 없음.
		public void saveOrUpdate(String indexName, JSONObject json) {
	        String result  = "";
	        try {
	            final IndexRequest request = new IndexRequest(indexName);
	            request.source(json, XContentType.JSON).id("p00001");
	            final IndexResponse response = client.index(request, RequestOptions.DEFAULT);
	            System.out.println("response : " +response.getResult().toString());
	            result = response.getId().toString();
	        } catch (IOException e) {
	            System.out.println(e.toString());
	        }
		}
	
	    //에러 발생. 계산된 가격 필터링 ? parse까지 성공,  가격 - 할인 이 음수인 경우가 있어서 오류 발생 중.
  		public List<Map<String,Object>> HighLevelClientFilterPriceQuery(String indexName, int pageNum){
  			
  	        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
  	     // Function Score Query를 사용하여 계산된 점수에 따라 정렬
  	        ScoreFunctionBuilder<?> scoreFunction = ScoreFunctionBuilders.scriptFunction(
  	                "doc['product_cost'].value + Long.parseLong(doc['sale_price.keyword'].value)"); // 실제 판매되는 가격 순으로 정렬..?

  	        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(QueryBuilders.matchAllQuery(), scoreFunction);
  	        searchSourceBuilder.query(functionScoreQueryBuilder);
  	        searchSourceBuilder.sort(SortBuilders.scoreSort().order(SortOrder.DESC));
  	        applyPaging(searchSourceBuilder, pageNum);

            return executeSearch(indexName, searchSourceBuilder);
  	 
  	    }
  		//점수별 전체 쿼리  // 아직 못했음. DB 추가필요
  		public List<Map<String, Object>> HighLevelClientFilterBestQuery(String indexName, int pageNum) {
  		    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

  		    // 스크립트를 사용하여 field1과 field2의 값을 곱한 값을 계산하고 내림차순으로 정렬
  		    Script script = new Script(ScriptType.INLINE, "painless",
  		            "doc['field1'].value * doc['field2'].value", null);

  		    searchSourceBuilder.query(QueryBuilders.matchAllQuery());
  		    searchSourceBuilder.sort(new ScriptSortBuilder(script, ScriptSortBuilder.ScriptSortType.NUMBER).order(SortOrder.DESC));
            applyPaging(searchSourceBuilder, pageNum);
            return executeSearch(indexName, searchSourceBuilder);
  		}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("PList")
	public String elaTestCategory(Criteria cri, Model model, String keyword, String ctg, String order, Double minPrice, Double maxPrice) {
		System.out.println(keyword + "  /  " + ctg);
		System.out.println("order : ====" + order);
		
		int totalCnt = -1;
		List<Map<String, Object>> products = new ArrayList<Map<String,Object>>();
		
		
		//필요한 조건들이 있다면 bool쿼리에 축적하여 쌓는방식
		totalCnt = queryCount("ar_products",keyword,ctg, minPrice, maxPrice);
		products = FinalMatchQuery("ar_products", cri.getPageNum(), keyword, ctg, order, minPrice, maxPrice);
		
//		products = HighLevelClientFilterPriceQuery("ar_products", cri.getPageNum());
		
//		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//		boolQueryBuilder.must(QueryBuilders.wildcardQuery("message", "ANG*"));
//		나중에 카테고리 선택시 Filter 추가할 예정
		
		System.out.println("전체 수 : =====" + totalCnt);
		model.addAttribute("products", products);
		model.addAttribute("pageMaker",new PageDTO(cri, totalCnt));
		model.addAttribute("categories",service.getCategoryList());
		model.addAttribute("allCtg", service.getAllCategoryList());
		return "page/goods/goodsList"; 
	}
}
