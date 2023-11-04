package com.iamkhs.fooddelivery.service.impl;

import com.iamkhs.fooddelivery.entity.CustomerOrder;
import com.iamkhs.fooddelivery.entity.User;
import com.iamkhs.fooddelivery.exceptions.UserNotFoundException;
import com.iamkhs.fooddelivery.repository.OrderRepository;
import com.iamkhs.fooddelivery.repository.UserRepository;
import com.iamkhs.fooddelivery.request.OrderRequest;
import com.iamkhs.fooddelivery.dto.CustomerOrderDto;
import com.iamkhs.fooddelivery.service.OrderService;
import com.iamkhs.fooddelivery.utils.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public List<CustomerOrder> saveOrders(List<OrderRequest> orderList) {
        List<CustomerOrder> customerOrderList = new LinkedList<>();

        for (OrderRequest orderRequest : orderList){
            CustomerOrder customerOrder = ModelMapper.orderRequestToCustomerOrder(orderRequest);
            User user = this.userRepository.findById(orderRequest.userDto().id()).orElseThrow(() ->
                    new UserNotFoundException("User not found with this id!"));
            customerOrder.setUser(user);
            customerOrderList.add(customerOrder);
        }
        return this.orderRepository.saveAll(customerOrderList);
    }

    @Override
    public List<CustomerOrderDto> getOrdersByRestaurant(String restaurantId) {
        List<CustomerOrder> customerOrderList = this.orderRepository.findByRestaurantId(restaurantId);

        List<CustomerOrderDto> customerOrderDtoList = new LinkedList<>();
        for (CustomerOrder order : customerOrderList){
            CustomerOrderDto customerOrderDto = ModelMapper.customerOrderToResponse(order);
            customerOrderDtoList.add(customerOrderDto);
        }
        return customerOrderDtoList;
    }
}
