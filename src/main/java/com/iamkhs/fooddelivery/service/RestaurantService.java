package com.iamkhs.fooddelivery.service;

import com.iamkhs.fooddelivery.dto.RestaurantDto;
import com.iamkhs.fooddelivery.response.RestaurantResponse;
import com.iamkhs.fooddelivery.entity.Restaurant;

import java.util.List;

public interface RestaurantService {

    void saveRestaurant(Restaurant restaurant);
    RestaurantDto findRestaurantById(String id);
    RestaurantResponse getAllRestaurant(int pageNumber, int pageSize);
    List<RestaurantDto> findNearRestaurant(double latitude, double longitude, double radius);
}
