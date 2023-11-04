package com.iamkhs.fooddelivery.controller;

import com.iamkhs.fooddelivery.entity.CustomerOrder;
import com.iamkhs.fooddelivery.request.OrderRequest;
import com.iamkhs.fooddelivery.dto.CustomerOrderDto;
import com.iamkhs.fooddelivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food-app/api/users/admin")
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;

    @MessageMapping("/placeOrder")
    @SendTo("/topic/orders")
    public List<OrderRequest> orderRequest(@RequestBody List<OrderRequest> orders){
        List<CustomerOrder> customerOrderList = this.orderService.saveOrders(orders);
        System.err.println(customerOrderList);
        return orders;
    }

    @GetMapping("/orders/{id}")
    public List<CustomerOrderDto> getOrders(@PathVariable String id){
        return this.orderService.getOrdersByRestaurant(id);
    }

}
