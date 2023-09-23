package com.ar.lighthouse.member.test;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MemberTest {
	public static void main(String[] args) {
        String answer = "{\"mId\":\"tvivarepublica\",\"lastTransactionKey\":\"2F24793351BAEAAF3A6CDF114C98243E\",\"paymentKey\":\"xMBvpmjnoD4yKeq5bgrpBEpnzLGl4Q3GX0lzW6YOQJ1w9NLR\",\"orderId\":\"d3a55dff-98c3-4f2f-a7e5-37\",\"orderName\":\"머리터짐 외 3건\",\"taxExemptionAmount\":0,\"status\":\"DONE\",\"requestedAt\":\"2023-09-21T09:44:45+09:00\",\"approvedAt\":\"2023-09-21T09:45:16+09:00\",\"useEscrow\":false,\"cultureExpense\":false,\"card\":{\"issuerCode\":\"11\",\"acquirerCode\":\"11\",\"number\":\"55704269****163*\",\"installmentPlanMonths\":0,\"isInterestFree\":false,\"interestPayer\":null,\"approveNo\":\"00000000\",\"useCardPoint\":false,\"cardType\":\"신용\",\"ownerType\":\"개인\",\"acquireStatus\":\"READY\",\"amount\":143450},\"virtualAccount\":null,\"transfer\":null,\"mobilePhone\":null,\"giftCertificate\":null,\"cashReceipt\":null,\"cashReceipts\":null,\"discount\":null,\"cancels\":null,\"secret\":\"ps_0RnYX2w532qjyma9ZQel8NeyqApQ\",\"type\":\"NORMAL\",\"easyPay\":{\"provider\":\"토스페이\",\"amount\":0,\"discountAmount\":0},\"country\":\"KR\",\"failure\":null,\"isPartialCancelable\":true,\"receipt\":{\"url\":\"https://dashboard.tosspayments.com/receipt/redirection?transactionId=tviva20230921094449SZUn9&ref=PX\"},\"checkout\":{\"url\":\"https://api.tosspayments.com/v1/payments/xMBvpmjnoD4yKeq5bgrpBEpnzLGl4Q3GX0lzW6YOQJ1w9NLR/checkout\"},\"currency\":\"KRW\",\"totalAmount\":143450,\"balanceAmount\":143450,\"suppliedAmount\":130409,\"vat\":13041,\"taxFreeAmount\":0,\"method\":\"간편결제\",\"version\":\"2022-11-16\"}";
        
        JSONParser jsonParser = new JSONParser();
        
        Object obj;
        JSONObject jsonObject = null;
     try {
        obj = jsonParser.parse(answer);
        jsonObject = (JSONObject)obj;
     } catch (ParseException e) {
        e.printStackTrace();
     }
     System.out.println((String)jsonObject.get("mId"));

	}
}
