package com.iamkhs.fooddelivery.controller;

import com.iamkhs.fooddelivery.dto.RestaurantDto;
import com.iamkhs.fooddelivery.response.RestaurantResponse;
import com.iamkhs.fooddelivery.service.RestaurantService;
import com.iamkhs.fooddelivery.request.LocationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food-app/api/")
@CrossOrigin("*")
public class HomeController {

    private final RestaurantService restaurantService;

    // Get a list of all restaurants
    @GetMapping("/restaurants")
    public RestaurantResponse getAllRestaurant(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                               @RequestParam(defaultValue = "10", required = false) int pageSize){
        System.err.println("all restaurant calling");
        return this.restaurantService.getAllRestaurant(pageNumber, pageSize);
    }

    // Get a specific restaurant by ID
    @GetMapping("/restaurants/{id}")
    public RestaurantDto restaurant(@PathVariable String id){
        // Find and return the restaurant by its ID
        return this.restaurantService.findRestaurantById(id);
    }

    // Get a list of restaurants by location
    @PostMapping("/restaurants/location")
    public List<RestaurantDto> findAllNearRestaurantByLocation(@RequestBody LocationRequest locationRequest){

        // Convert latitude and longitude to radians for calculation
        double latitude = Math.toRadians(locationRequest.latitude());
        double longitude = Math.toRadians(locationRequest.longitude());

        // Define a search radius (in radians)
        double radius = 2;

        // Return the list of RestaurantDto objects
        return this.restaurantService.findNearRestaurant(latitude, longitude, radius);
    }
}
