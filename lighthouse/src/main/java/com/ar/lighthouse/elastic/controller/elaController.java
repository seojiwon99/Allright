package com.ar.lighthouse.elastic.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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
    //String hostname = "e4d7-58-238-119-6.ngrok.io";
    int port = 9200;
    String scheme = "http";
    HttpHost host = new HttpHost(hostname, port, scheme);
    String dd = "http://ec34-58-238-119-6.ngrok.io";
    HttpHost hos = new HttpHost("e6c7-58-238-119-6.ngrok-free.app");
    //RestClientBuilder restClientBuilder = RestClient.builder(host);
    RestClientBuilder restClientBuilder = RestClient.builder(hos);
    
    RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);
	
	
	@Autowired
	MainPageService service;	
	
	//matchAll 쿼리
	public List<Map<String,Object>> HighLevelClientQuery(String indexName, int pageNum){
		
        List<Map<String,Object>> finalResult = new ArrayList<Map<String,Object>>();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(
                QueryBuilders.matchAllQuery()
                //QueryBuilders.matchQuery("name", "product")
        );
        searchSourceBuilder.from(((pageNum-1)*9));//hit 시작 지점(0~8 /9~)
        searchSourceBuilder.size(9); //hit 사이즈 (9~17)
//      searchSourceBuilder.sort(name) // Add a sort against the given field name.

        try {
            SearchRequest request = new SearchRequest(indexName); //인덱스 이름으로 검색 객체
            request.source(searchSourceBuilder); //검색 조건 소스로 searchSouceBuilder 이용
            SearchResponse response = client.search(request, RequestOptions.DEFAULT); // 응답 = RestHighLevelClient로 부터 검색
            SearchHits searchHits = response.getHits(); //searchHits 객체에 응답의 hits로 초기화
            for(SearchHit hit : searchHits){ // 반복문 돌며 hit 하나씩 꺼내서 Map으로 담기
                Map<String, Object> result = hit.getSourceAsMap();
                finalResult.add(result); // 담은 hit들은 하나의 객체이므로 다시 final에 담기
            }
            return finalResult;
            
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
 
    }
	//전체중 키워드 검색
	public List<Map<String,Object>> HighLevelClientQueryMatch(String indexName, int pageNum, String keyword){
        List<Map<String,Object>> finalResult = new ArrayList<Map<String,Object>>();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(
                QueryBuilders.matchQuery("product_name", keyword)
                //QueryBuilders.matchQuery("name", "product")
        );
        searchSourceBuilder.from(((pageNum-1)*9));
        searchSourceBuilder.size(9);
        try {
            SearchRequest request = new SearchRequest(indexName);
            request.source(searchSourceBuilder);
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            SearchHits searchHits = response.getHits();
            for(SearchHit hit : searchHits){
                Map<String, Object> result = hit.getSourceAsMap();
                finalResult.add(result);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return finalResult;
    }
	//카테고리 + 키워드 검색
	public List<Map<String,Object>> HighLevelClientQueryCtg(String indexName, int pageNum, String keyword, String ctg){
        List<Map<String,Object>> finalResult = new ArrayList<Map<String,Object>>();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(
                QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("category_code", ctg))
                .should(QueryBuilders.matchQuery("product_name", keyword))
                //QueryBuilders.matchQuery("name", "product")
        );
        searchSourceBuilder.from(((pageNum-1)*9));
        searchSourceBuilder.size(9);
        try {
            SearchRequest request = new SearchRequest(indexName);
            request.source(searchSourceBuilder);
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            SearchHits searchHits = response.getHits();
            for(SearchHit hit : searchHits){
                Map<String, Object> result = hit.getSourceAsMap();
                finalResult.add(result);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return finalResult;
    }
	
	
	
	
	public int QueryAllCount(String indexName) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(
                QueryBuilders.matchAllQuery()
                //QueryBuilders.matchQuery("name", "product")
        );
        searchSourceBuilder.size(1000);
        try {
        	SearchRequest request = new SearchRequest(indexName);
        	request.source(searchSourceBuilder);
            SearchResponse response = client.search(request,RequestOptions.DEFAULT);
            SearchHits searchHits = null;

            searchHits = response.getHits();

            //hit 갯수 가져오기
            TotalHits hitCount  = searchHits.getTotalHits();
            String hitTotal = String.valueOf(hitCount);
            int cnt =  Integer.valueOf(hitTotal.substring(0,hitTotal.indexOf(" "))); // 결과 =  "xx hits" 따라서 0~공백까지 substr 후 변환
            System.out.println(cnt);
            return cnt;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}
	public int QueryMatchCount(String indexName,String keyword) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(
                QueryBuilders.matchQuery("product_name", keyword)
                //QueryBuilders.matchQuery("name", "product")
        );
        searchSourceBuilder.size(1000);
        try {
        	SearchRequest request = new SearchRequest(indexName);
        	request.source(searchSourceBuilder);
            SearchResponse response = client.search(request,RequestOptions.DEFAULT);
            SearchHits searchHits = null;

            searchHits = response.getHits();

            //hit 갯수 가져오기
            TotalHits hitCount  = searchHits.getTotalHits();
            String hitTotal = String.valueOf(hitCount);
            int cnt =  Integer.valueOf(hitTotal.substring(0,hitTotal.indexOf(" "))); // 결과 =  "xx hits" 따라서 0~공백까지 substr 후 변환
            System.out.println(cnt);
            return cnt;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}
	
	public int QueryCtgMatchCount(String indexName,String keyword,String ctg) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(
                QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("category_code", ctg))
                .should(QueryBuilders.matchQuery("product_name", keyword))
                //QueryBuilders.matchQuery("name", "product")
        );
        searchSourceBuilder.size(1000);
        try {
        	SearchRequest request = new SearchRequest(indexName);
        	request.source(searchSourceBuilder);
            SearchResponse response = client.search(request,RequestOptions.DEFAULT);
            SearchHits searchHits = null;

            searchHits = response.getHits();

            //hit 갯수 가져오기
            TotalHits hitCount  = searchHits.getTotalHits();
            String hitTotal = String.valueOf(hitCount);
            int cnt =  Integer.valueOf(hitTotal.substring(0,hitTotal.indexOf(" "))); // 결과 =  "xx hits" 따라서 0~공백까지 substr 후 변환
            System.out.println(cnt);
            return cnt;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}
	
	
	//insert or update
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("PList")
	public String elaTestCategory(Criteria cri, Model model, String keyword, String ctg) {
		System.out.println(keyword + "  /  " + ctg);
		int totalCnt = -1;
		if((keyword == "" || keyword == null) && (ctg == "" || ctg == null)) { //키워드와 카테고리 둘다 없을 경우 matchAll
			totalCnt = QueryAllCount("ar_products");
			model.addAttribute("products", HighLevelClientQuery("ar_products", cri.getPageNum())) ;
			model.addAttribute("pageMaker",new PageDTO(cri, totalCnt));
				
		}else if((ctg == "" || ctg == null) && (keyword != null || keyword != "")) { // 카테고리는 없고 키워드만 있을 경우 match bool (카테고리 없이 검색)
			totalCnt = QueryMatchCount("ar_products", keyword);
			model.addAttribute("products", HighLevelClientQueryMatch("ar_products", cri.getPageNum(), keyword)) ;
			model.addAttribute("pageMaker",new PageDTO(cri, totalCnt));
		}else if((keyword == "" || keyword == null) && (ctg != null || ctg != "")) { // 키워드는 없고 카테고리만 있을 경우 match must (카테고리 선택)
			
		}else { //키워드와 카테고리 둘다 있을 경우 (카테고리 선택 후 검색)
			totalCnt = QueryCtgMatchCount("ar_products", keyword, ctg);
			model.addAttribute("products", HighLevelClientQueryCtg("ar_products", cri.getPageNum(), keyword, ctg)) ;
			model.addAttribute("pageMaker",new PageDTO(cri, totalCnt));
		}
		/*
		 *as 
		 * 
		 * New = Regdate가 최근순
		 * 
		 * 
		 * Best = 리뷰 수 * 별점 높은순 
		 * 
		 * 
		 * 정렬 = 가격 순 + -
		 * 
		 * 
		 */
		model.addAttribute("categories",service.getCategoryList());
		model.addAttribute("allCtg", service.getAllCategoryList());
		return "page/goods/goodsList"; 
	}
}
