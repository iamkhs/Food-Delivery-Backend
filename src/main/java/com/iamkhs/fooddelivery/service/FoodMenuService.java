package com.iamkhs.fooddelivery.service;

import com.iamkhs.fooddelivery.dto.FoodMenuDto;
import com.iamkhs.fooddelivery.entity.FoodMenu;

import java.util.List;

public interface FoodMenuService {
    FoodMenuDto saveFood(FoodMenu foodMenu, String restaurantId);
    FoodMenuDto findById(int id, String restaurantId);
    List<FoodMenuDto> getAllFoods();
    List<FoodMenu> getAllFoodsByRestaurantId(String id);
    FoodMenuDto updateFood(int id, FoodMenu foodMenu);
    void deleteFood(int foodId);
}
