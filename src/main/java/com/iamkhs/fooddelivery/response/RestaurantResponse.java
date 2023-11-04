package com.iamkhs.fooddelivery.response;

import com.iamkhs.fooddelivery.dto.RestaurantDto;

import java.io.Serializable;
import java.util.List;

public record RestaurantResponse(
        List<RestaurantDto> restaurantDtoList,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean lastPage) implements Serializable {
}
