package com.ms.order.orderms;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderRepository extends JpaRepository<ProductInventory, Integer>{

	
}
