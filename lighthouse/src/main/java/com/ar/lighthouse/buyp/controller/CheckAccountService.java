package com.ar.lighthouse.buyp.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@Service
public class CheckAccountService {
	public HashMap<Object, Object> getAccessToken1(String bank_code, String bank_num) {
        HashMap<Object, Object> map = new HashMap<>();
		String impKey = "7445661700233425";
		String impSecret = "SSyLPkStz6a8SvOq61mmjq12cJlN2ee3VS0v794Zrua0TwoPfM1Ya1hZLKEgwqQvsmEvQzJt3qyKUyQa";
        // 토큰 요청 보낼 주소
		String strUrl = "https://api.iamport.kr/users/getToken"; 

		try {
         // url Http 연결 생성
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);// outputStreamm으로 post 데이터를 넘김

			conn.setRequestProperty("content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");

			// 파라미터 세팅
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

			JSONObject requestData = new JSONObject();
			requestData.put("imp_key", impKey);
			requestData.put("imp_secret", impSecret);

			bw.write(requestData.toString());
			bw.flush();
			bw.close();

			int resposeCode = conn.getResponseCode();

			// System.out.println("응답코드 =============" + resposeCode);
			if (resposeCode == 200) {// 성공
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}

				br.close();

				// 토큰 값 빼기
				JsonElement jsonElement = JsonParser.parseString(sb.toString());
		        String access_token = jsonElement.getAsJsonObject().getAsJsonObject("response").get("access_token").getAsString();
				// System.out.println("Access Token: " + access_token);

				String getPaymentUrl = "https://api.iamport.kr/vbanks/holder";

				String query = String.format("?bank_code=%s&bank_num=%s", URLEncoder.encode(bank_code, "UTF-8"),
						URLEncoder.encode(bank_num, "UTF-8"));
				URL bankurl = new URL(getPaymentUrl + query);
				// System.out.println(bankurl);

				HttpURLConnection getConn = (HttpURLConnection) bankurl.openConnection();
				getConn.setRequestMethod("GET");
				getConn.setRequestProperty("Content-Type", "application/json");
				getConn.setRequestProperty("Authorization", "Bearer " + access_token);

				int getResponseCode = getConn.getResponseCode();
				// System.out.println("GET 응답코드 =============" + getResponseCode);

				if (getResponseCode == 200) {
					// System.out.println("#########성공");

					BufferedReader getBr = new BufferedReader(new InputStreamReader(getConn.getInputStream()));
					StringBuilder getResponseSb = new StringBuilder();
					String getLine;
					while ((getLine = getBr.readLine()) != null) {
						getResponseSb.append(getLine).append("\n");
					}
					getBr.close();

					String getResponse = getResponseSb.toString();
					// System.out.println("GET 응답 결과: " + getResponse);

					JsonParser parser1 = new JsonParser();
					JsonObject phoneJson1 = parser1.parse(getResponse).getAsJsonObject();

					// 예금주만 값 빼기
					String bankHolderInfo = phoneJson1.getAsJsonObject("response").get("bank_holder").getAsString();
					// System.out.println("bankHolderInfo: " + bankHolderInfo);

					map.put("bankHolderInfo", bankHolderInfo);
				} else {
					map.put("error", getResponseCode);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}
	
	public List<JSONObject> bankList(){
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		String impKey = "7445661700233425";
		String impSecret = "SSyLPkStz6a8SvOq61mmjq12cJlN2ee3VS0v794Zrua0TwoPfM1Ya1hZLKEgwqQvsmEvQzJt3qyKUyQa";
        // 토큰 요청 보낼 주소
		String strUrl = "https://api.iamport.kr/users/getToken"; 
		BufferedReader in = null;
		
		List<JSONObject> list = new ArrayList<JSONObject>();
		
		try {
	         // url Http 연결 생성
				URL url = new URL(strUrl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();

				// POST 요청
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);// outputStreamm으로 post 데이터를 넘김

				conn.setRequestProperty("content-Type", "application/json");
				conn.setRequestProperty("Accept", "application/json");

				// 파라미터 세팅
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

				JSONObject requestData = new JSONObject();
				requestData.put("imp_key", impKey);
				requestData.put("imp_secret", impSecret);

				bw.write(requestData.toString());
				bw.flush();
				bw.close();

				int resposeCode = conn.getResponseCode();
				System.out.println("responseCode============================================== : "+ resposeCode);
				
				//
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}

				br.close();

				// 토큰 값 빼기
				JsonElement jsonElement = JsonParser.parseString(sb.toString());
		        String access_token = jsonElement.getAsJsonObject().getAsJsonObject("response").get("access_token").getAsString();
				// System.out.println("Access Token: " + access_token);

				String getPaymentUrl = "https://api.iamport.kr/banks";


				URL bankurl = new URL(getPaymentUrl);
				// System.out.println(bankurl);

				HttpURLConnection getConn = (HttpURLConnection) bankurl.openConnection();
				getConn.setRequestMethod("GET");
				getConn.setRequestProperty("Content-Type", "application/json");
				getConn.setRequestProperty("Authorization", "Bearer " + access_token);

				int getResponseCode = getConn.getResponseCode();
				 System.out.println("GET 응답코드 =============" + getResponseCode);
				 
				 
				 //
				// System.out.println("#########성공");

					BufferedReader getBr = new BufferedReader(new InputStreamReader(getConn.getInputStream()));
					StringBuilder getResponseSb = new StringBuilder();
					String getLine;
					while ((getLine = getBr.readLine()) != null) {
						getResponseSb.append(getLine).append("\n");
					}
					getBr.close();

					String getResponse = getResponseSb.toString();
					System.out.println(getResponse);
					// System.out.println("GET 응답 결과: " + getResponse);

					JSONParser parser1 = new JSONParser();
					JSONArray jsonArr =  new JSONArray();
					Object obj = null;
					try {
						obj = parser1.parse(getResponse);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					JSONObject jsonObject = (JSONObject)obj;
					jsonArr = (JSONArray)jsonObject.get("response");
					
					
					if (jsonArr.size() > 0){
					    for(int i=0; i<jsonArr.size(); i++){
					        JSONObject jsonObj = (JSONObject)jsonArr.get(i);
					        list.add(jsonObj);
					    }
					    // StudyingAzae, Soodal 출력
					}
	
				

			} catch (IOException e) {
				e.printStackTrace();
			}

		
		return list;
	}
	
}
