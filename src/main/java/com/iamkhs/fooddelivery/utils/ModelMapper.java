package com.iamkhs.fooddelivery.utils;

import com.iamkhs.fooddelivery.dto.FoodMenuDto;
import com.iamkhs.fooddelivery.dto.RestaurantAdminDto;
import com.iamkhs.fooddelivery.dto.RestaurantDto;
import com.iamkhs.fooddelivery.dto.UserDto;
import com.iamkhs.fooddelivery.entity.*;
import com.iamkhs.fooddelivery.request.OrderRequest;
import com.iamkhs.fooddelivery.dto.CustomerOrderDto;

import java.util.*;

public class ModelMapper {

    public static RestaurantDto restaurantToDto(Restaurant restaurant){

        // Create a RestaurantDto object for each restaurant
        return new RestaurantDto(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getCategories(),
                restaurant.getContact(),
                restaurant.getImage(),
                restaurant.getAddress(),
                restaurant.getFoodMenuList(),
                restaurantAdminToDto(restaurant.getRestaurantAdmin()));
    }

    public static UserDto userToDto(User user){
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.isEnable(),
                user.getRole());
    }

    public static RestaurantAdminDto restaurantAdminToDto(RestaurantAdmin restaurantAdmin){
        Optional<RestaurantAdmin> restaurantAdminIdOptional = Optional.ofNullable(restaurantAdmin);
        UUID restaurantAdminId = null;
        if (restaurantAdminIdOptional.isPresent()){
            restaurantAdminId = restaurantAdminIdOptional.get().getId();
        }
        return new RestaurantAdminDto(restaurantAdminId);
    }

    public static FoodMenuDto foodMenuToDto(FoodMenu foods, Restaurant restaurant) {

        RestaurantDto restaurantDto = restaurantToDto(restaurant);

        return new FoodMenuDto(foods.getId(),
                foods.getName(),
                foods.getDescription(),
                foods.getPrice(),
                foods.getImage(),
                restaurantDto);
    }

    public static List<RestaurantDto> restaurantListToDtoList(List<Restaurant> restaurantList) {
        List<RestaurantDto> restaurantDtoList = new ArrayList<>();
        for (var restaurant : restaurantList){
            // Converting to DTO
            RestaurantDto restaurantDto = restaurantToDto(restaurant);

            // Add the RestaurantDto to the list
            restaurantDtoList.add(restaurantDto);
        }
        return restaurantDtoList;
    }


    public static CustomerOrderDto customerOrderToResponse(CustomerOrder order){
        return new CustomerOrderDto(
                order.getOrderId(),
                order.getProductId(),
                order.getName(),
                order.getDescription(),
                order.getPrice(),
                order.getRestaurantId(),
                ModelMapper.userToDto(order.getUser())
        );
    }

    public static CustomerOrder orderRequestToCustomerOrder(OrderRequest order){
        CustomerOrder customerOrder = new CustomerOrder();

        customerOrder.setProductId(order.id());
        customerOrder.setName(order.name());
        customerOrder.setDescription(order.description());
        customerOrder.setRestaurantId(order.restaurantId());
        customerOrder.setPrice(order.price());

        return customerOrder;
    }
}
