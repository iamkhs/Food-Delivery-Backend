package com.iamkhs.fooddelivery.repository;

import com.iamkhs.fooddelivery.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, Integer> {
    List<CustomerOrder> findByRestaurantId (String restaurantId);
}
