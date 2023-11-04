package com.iamkhs.fooddelivery.dto;

import com.iamkhs.fooddelivery.entity.Address;
import com.iamkhs.fooddelivery.entity.FoodMenu;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public record RestaurantDto(UUID id,
                            String name,
                            String categories,
                            String contact,
                            String image,
                            Address addresses,
                            Set<FoodMenu> foodMenuList,
                            RestaurantAdminDto restaurantAdminDto) implements Serializable {
}
