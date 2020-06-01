package com.ms.order.orderms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerHome {
	
	@Autowired
	OrderService service;
	
	@PostMapping("/placeOrder")
	public String placeOrder(@RequestBody Beer request) {
		
		System.out.println("token received from upstream " + request.getOrderId());
		return service.placeOrder(request);
	}

}
