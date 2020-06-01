package com.ms.order.orderms;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class ProductInventory {	
	
	@Id
	private Integer orderId;
	
	private String jwtToken;
	public ProductInventory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductInventory(Integer orderId, String jwtToken) {
		super();
		
		this.orderId = orderId;
		this.jwtToken=jwtToken;
	}	
	
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	

}
