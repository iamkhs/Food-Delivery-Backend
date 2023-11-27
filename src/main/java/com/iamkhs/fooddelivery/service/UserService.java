package com.iamkhs.fooddelivery.service;

import com.iamkhs.fooddelivery.dto.RestaurantDto;
import com.iamkhs.fooddelivery.dto.UserDto;
import com.iamkhs.fooddelivery.entity.User;

import java.util.List;

public interface UserService {
    // create
    User saveUser(User user);

    // get
    UserDto getUser(String id);

    // verify user
    boolean isUserVerified(String verificationCode);

    // update
    UserDto updateUser(String id, User user);

    // delete
    void deleteUser(String id);

    List<User> allUsers();

    RestaurantDto getRestaurantByAdmin(String adminId);

}
