package com.iamkhs.fooddelivery.controller;

import com.iamkhs.fooddelivery.dto.FoodMenuDto;
import com.iamkhs.fooddelivery.entity.FoodMenu;
import com.iamkhs.fooddelivery.service.FoodMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food-app/api/")
@CrossOrigin("*")
public class FoodController {

    private final FoodMenuService foodMenuService;

    @GetMapping("/foods")
    public List<FoodMenuDto> getAllFoods(){
        return this.foodMenuService.getAllFoods();
    }

    @GetMapping("/foods/restaurant/{id}")
    public List<FoodMenu> getAllFoodByRestaurantId(@PathVariable String id){
        return this.foodMenuService.getAllFoodsByRestaurantId(id);
    }
}
