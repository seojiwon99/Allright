package com.ar.lighthouse.orders.service;

import java.util.List;

import lombok.Data;
@Data
public class OrderDeliveryVO {
	List<OrderPayVO> orderPayVO;
	DeliveryVO deliveryVO;
}
