package com.iamkhs.fooddelivery.service;

import com.iamkhs.fooddelivery.entity.CustomerOrder;
import com.iamkhs.fooddelivery.request.OrderRequest;
import com.iamkhs.fooddelivery.dto.CustomerOrderDto;

import java.util.List;

public interface OrderService {
    List<CustomerOrder> saveOrders(List<OrderRequest> orders);
    List<CustomerOrderDto> getOrdersByRestaurant(String restaurantId);
}
