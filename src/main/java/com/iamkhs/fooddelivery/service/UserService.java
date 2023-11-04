package com.iamkhs.fooddelivery.service;

import com.iamkhs.fooddelivery.dto.RestaurantDto;
import com.iamkhs.fooddelivery.dto.UserDto;
import com.iamkhs.fooddelivery.entity.User;

import java.util.List;

public interface UserService {
    // create
    UserDto saveUser(User user);

    // get
    UserDto getUser(String id);

    // update
    UserDto updateUser(String id, User user);

    // delete
    void deleteUser(String id);

    List<User> allUsers();

    RestaurantDto getRestaurantByAdmin(String adminId);

}
