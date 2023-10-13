OrdersController

package com.ar.lighthouse.orders.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ar.lighthouse.common.CodeVO;
import com.ar.lighthouse.member.service.MemberVO;
import com.ar.lighthouse.orders.service.CreditVO;
import com.ar.lighthouse.orders.service.DeliveryVO;
import com.ar.lighthouse.orders.service.OrderChkVO;
import com.ar.lighthouse.orders.service.OrderDeliveryVO;
import com.ar.lighthouse.orders.service.OrderPayVO;
import com.ar.lighthouse.orders.service.OrdersService;
import com.ar.lighthouse.orders.service.OrdersVO;
import com.ar.lighthouse.orders.service.RefundVO;
import com.ar.lighthouse.product.service.ProductService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class OrdersController {

   @Autowired
   OrdersService ordersService;
   
   @Autowired
   ProductService productService;
   

   // 장바구니에서 구매 상품 가져옴.
   @PostMapping("orders/pay")
   public String orderList(HttpServletRequest request, Model model, @RequestParam (name = "cartCode") int[] cartCode) {
      
      HttpSession session = request.getSession();
      MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
      String memberId = memberVO.getMemberId();
      
      List<CodeVO> codeList = ordersService.getCode();
      
      int[] lists = cartCode;
      List<OrdersVO> orderGet = new ArrayList<OrdersVO>();
      for(int cartNum : lists)  {
         orderGet.add((ordersService.getOrders(memberId, cartNum)));  
      }
      int productNum = 0;
      String productName = "";
      if(orderGet.size() != 1) {
      productNum = orderGet.size()-1;
         productName = orderGet.get(0).getProductName()  + " 외 " + productNum + "건";
      } else {
         productName = orderGet.get(0).getProductName();   
      }
      
      model.addAttribute("productName",productName);
      model.addAttribute("orderGet", orderGet);
      session.setAttribute("orderGet", orderGet);
      
      List<OrdersVO> couponList = ordersService.getCoupon(memberId);
      session.setAttribute("couponList", couponList);
      model.addAttribute("couponList",couponList);
      
      session.setAttribute("cartCode", cartCode);
      
      // 배송지 마스터 코드 전송
      model.addAttribute("codeList",codeList);
       
      return "page/orders/ordersPay";
   }
   
   //배송지, 배송 주문 테이블 Session 저장
   @PostMapping("orders/save")
   @ResponseBody
   public void ordersSave(HttpServletRequest req, @RequestBody OrderDeliveryVO orderDeliveryVO) { //RequestBody는 한번만 사용가능. 여러 값 받을 VO 필요
      HttpSession session = req.getSession();
      session.setAttribute("orderPayVO", orderDeliveryVO.getOrderPayVO());
      session.setAttribute("deliveryVO", orderDeliveryVO.getDeliveryVO());
   }
   //토스페이 먼츠 결제 실패
   @GetMapping("orders/fail")
   public String crditFail(Model message) {
      //메세지 담아서 보내고 메세지 있을 때 alert 보내고 데이터 있을 경우에만 띄우기
      String msg = "주문 결제 중 예상치 못한 오류로 결제 실패했습니다.";
      message.addAttribute("msg",msg);
      return "redirect:/page/cart/cartView";
   }
   
   //토스페이 먼츠 결제 승인 및 DB 저장
   @GetMapping("orders/credit") 
   public String creditTest(CreditVO creditVO, HttpSession session) throws IOException, InterruptedException {
      
   
      //주문, 주문결제 내역 페이지 데이터 저장
      List<OrdersVO> orderList = (List<OrdersVO>) session.getAttribute("orderGet");
      List<OrderPayVO> orderPayVO = (List<OrderPayVO>) session.getAttribute("orderPayVO");
      MemberVO memberVO = (MemberVO) session.getAttribute("loginMember");
      List<OrdersVO> couponList = (List<OrdersVO>) session.getAttribute("couponList");
      DeliveryVO deliveryVO = (DeliveryVO) session.getAttribute("deliveryVO");
      int[] cartCode = (int[]) session.getAttribute("cartCode");
      OrdersVO orderCoupon = new OrdersVO();
      
      //orderPayVO 쿠폰 사용 시 N으로 변경
      String memberId = memberVO.getMemberId();
      
      int couponCode = 0;
      List<Integer> optionCodeList = new ArrayList<Integer>();
      int idx = 0;
      for(OrderPayVO couponNum : orderPayVO) {
         
         couponCode = couponNum.getCouponCode();
         optionCodeList.add(couponNum.getOptionDetailCode());
         int couponUse = ordersService.editNotCoupon(memberId, couponCode);
         idx++;
      }

      //주문 데이터 저장 method (배송 등) 총 주문 결제 정보
      ordersService.addOrderPay(memberId, deliveryVO);
         
      //주문 코드 파싱
      int orderCode =   ordersService.getOrderCode(memberId);
      
      //토스결제
      tossPayment(creditVO, orderCode);
      
      List<String> dcostList = new ArrayList<String>();
      //같은 상품의 경우 배송비 빼기
      for(OrdersVO vo : orderList) {
         if(dcostList.contains(vo.getProductCode())) {
            vo.setDeliveryCost(0);
         }else {
            dcostList.add(vo.getProductCode());
         }
      } //같은 상품의 경우 배송비 빼기
      for(OrderPayVO vo : orderPayVO) {
         if(dcostList.contains(vo.getProductCode())) {
            vo.setDeliveryCost(0);
         }else {
            dcostList.add(vo.getProductCode());
         }
      }
      
   
      //쿠폰 할인 상품
       for (OrderPayVO pay : orderPayVO) {
             if(optionCodeList.contains(pay.getOptionDetailCode())) {
                orderCoupon.setCartCount(pay.getCartCount());
                orderCoupon.setOptionDetailCode(pay.getOptionDetailCode());
                orderCoupon.setOrderCode(orderCode);
                orderCoupon.setOptionCouponCheck("Y");
                orderCoupon.setCouponCode(couponCode);
                orderCoupon.setOrderPrice(pay.getProductSalePrice());
                orderCoupon.setDiscountPrice(pay.getCouponPrice());          
                orderCoupon.setPaymentPrice(pay.getProductSalePrice() - pay.getCouponPrice() + pay.getDeliveryCost());
               
               ordersService.addOrders(orderCoupon);
                } else {
                   continue;
                }
      }
       
      // 할인 받지 않은 상품
            for(OrdersVO order : orderList) {
                  if(optionCodeList.contains(order.getOptionDetailCode())) { //쿠폰 할인 받은 상품
                     continue;
                  }else {
                     order.setOrderCode(orderCode);
                     order.setOptionCouponCheck("N");
                     order.setOrderPrice(order.getCartCount() *(order.getOptionPrice() + order.getSalePrice()));
                     order.setPaymentPrice(order.getCartCount() * (order.getOptionPrice() + order.getSalePrice()) + order.getDeliveryCost());
                     ordersService.addOrders(order);   
                  }
                     
               }   
               for(int cartNum : cartCode) {
               ordersService.removeCart(memberId, cartNum); //장바구니 비우기   
            }
         return "redirect:/page/buyer/orderList";
         
      }
   

   //토스 결제 method
      public CreditVO tossPayment(CreditVO creditVO, int orderCode) throws IOException, InterruptedException {
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
         Long amount = creditVO.getAmount();
         String paymentKey = creditVO.getPaymentKey();
         String orderId = creditVO.getOrderId();
         
         
         HttpRequest request = HttpRequest.newBuilder()
               .uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
               .header("Authorization", "Basic dGVzdF9za19RYmdNR1p6b3J6ZTVXZzJwQWVsVmw1RTFlbTRkOg==")
               .header("Content-Type", "application/json")
               .method("POST", HttpRequest.BodyPublishers.ofString("{\"amount\":\""+amount+"\",\"orderId\":\""+orderId+"\",\"paymentKey\":\""+paymentKey+"\"}"))
               .build();
         HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

         String creditRs = response.body();
         JSONParser jsonParser = new JSONParser();
         Object obj;
         JSONObject jsonObject = null;

         try {
            obj = jsonParser.parse(creditRs);
            jsonObject = (JSONObject) obj;
         } catch (ParseException e) {
            e.printStackTrace();
         }
        
        
         Date creDate = null;
         try {
            creDate = simpleDateFormat.parse((String)jsonObject.get("approvedAt"));
         } catch (java.text.ParseException e) {
            e.printStackTrace();
         }
           creditVO.setPaymentKey(paymentKey);
           creditVO.setType((String)jsonObject.get("type"));
           creditVO.setOrderId(orderId);
           creditVO.setOrderName((String)jsonObject.get("orderName"));
           creditVO.setMethod((String)jsonObject.get("method"));
           creditVO.setCurrency((String)jsonObject.get("currency"));
           creditVO.setMId((String)jsonObject.get("mId"));
         creditVO.setBalanceAmount((Long)jsonObject.get("balanceAmount"));
         creditVO.setTotalAmount((Long)jsonObject.get("totalAmount"));
         creditVO.setApprovedAt(creDate);
         creditVO.setOrderCode(orderCode);
         
         ordersService.addCredit(creditVO);
         return creditVO;
      }
      
      
      //토스페이먼츠 환불  규연
      @PostMapping(value = "/orders/cancel")
      public String orderCancel(String refundList, HttpSession session) throws IOException, InterruptedException {
      ObjectMapper objectMapper = new ObjectMapper();
      OrderChkVO[] orderchkList= objectMapper.readValue(refundList, OrderChkVO[].class);
      RefundVO newRefund = new RefundVO();
       for(OrderChkVO chk : orderchkList) {
       if(chk.getCancelReason() == "") {
          chk.setCancelReason("판매자 자체 취소");
          chk.setRefundType("C");
       }
       RefundVO refund = ordersService.getRefund(chk.getOrderCode(), chk.getOrderDetailCode(), chk.getMemberId());
       
       //상품 취소 중 쿠폰 사용한 경우 쿠폰 반환
           if(refund.getCouponCode() != 0) { 
              ordersService.editRefundCoupon(chk.getMemberId(),refund.getCouponCode()); 
           }
          
       
       //페이먼츠 키 찾을 때 필요한 데이터/ 환불 신청 시 필요한 데이터 = cancelReason, cancelAmount, paymentKey 
       String paymentKey = refund.getPaymentKey(); // 페이먼츠 키 넣기.
       String cancelReason = chk.getCancelReason();
       int cancelAmount = (refund.getPaymentPrice()); //부분 취소할 금액. select 해서 삼풍에서 가져오기. select에서 가져와야함.
       
       //refund 들어갈 데이터

       HttpRequest request = HttpRequest.newBuilder()
             .uri(URI.create("https://api.tosspayments.com/v1/payments/"+paymentKey+"/cancel")) //   payments/뒤에 넣기 paymentKey
             .header("Authorization", "Basic dGVzdF9za19RYmdNR1p6b3J6ZTVXZzJwQWVsVmw1RTFlbTRkOg==")
             .header("Content-Type", "application/json") 
             .method("POST", HttpRequest.BodyPublishers.ofString("{\"cancelReason\":\""+ cancelReason +"\",\"cancelAmount\":\""+cancelAmount+"\"}")) 
             .build();
      
       HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
       
       String refundVal = response.body();
      
      int refundAbleAmount = 0;
      try {
         JsonNode rootNode = objectMapper.readTree(refundVal);
         JsonNode cancels = rootNode.get("cancels");
         if(cancels.isArray() && cancels.size() > 0) {
            
            JsonNode lastCancel = cancels.get(cancels.size() -1);
            JsonNode refundableAmountNode = lastCancel.get("refundableAmount");
               if(refundableAmountNode != null) {
                  refundAbleAmount = refundableAmountNode.asInt();
            }
         }
         
      } catch (Exception  e) {
         e.printStackTrace();
      }
         newRefund.setRefundAmount(refundAbleAmount);
         newRefund.setPaymentKey(paymentKey);
         newRefund.setRefundTypecode(chk.getRefundTypecode()); // 취소, 반품 구분 C , R
         newRefund.setRefundType(chk.getRefundType());
           
         ordersService.addRefund(newRefund);
      
         //refundBalanceAmount 값 rders테이블,credit테이블 결제액 업데이트 
         if(newRefund.getRefundTypecode().equals("C")) {
            productService.editCancelOk(newRefund.getRefundTypecode());            
         } else {
            productService.editReturnOk(newRefund.getRefundTypecode());
         }
         ordersService.editTossRefundAmount(paymentKey, refundAbleAmount);
         ordersService.editOrderRefundAmount(chk.getOrderCode(), refundAbleAmount); 
      }
         return newRefund.getRefundType().equals("C") ?  "redirect:/cancelProduct" : "redirect:/exchangeList";    
      }
    }
            
