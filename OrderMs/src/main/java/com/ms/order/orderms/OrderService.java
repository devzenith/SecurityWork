package com.ms.order.orderms;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository repo;
	
	public String placeOrder(Beer request) {
		
		String transactionId=UUID.randomUUID().toString();
		
		ProductInventory o = new ProductInventory(1,request.getOrderId());
		
		repo.save(o);
		return transactionId;
	}

}
