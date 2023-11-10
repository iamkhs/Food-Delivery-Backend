package com.iamkhs.fooddelivery.service.impl;

import com.iamkhs.fooddelivery.dto.RestaurantDto;
import com.iamkhs.fooddelivery.dto.UserDto;
import com.iamkhs.fooddelivery.entity.Restaurant;
import com.iamkhs.fooddelivery.entity.Role;
import com.iamkhs.fooddelivery.entity.User;
import com.iamkhs.fooddelivery.exceptions.PasswordMismatchException;
import com.iamkhs.fooddelivery.exceptions.ResourceNotFoundException;
import com.iamkhs.fooddelivery.exceptions.UserAlreadyExistsException;
import com.iamkhs.fooddelivery.exceptions.UserNotFoundException;
import com.iamkhs.fooddelivery.repository.RestaurantRepository;
import com.iamkhs.fooddelivery.repository.UserRepository;
import com.iamkhs.fooddelivery.service.UserService;
import com.iamkhs.fooddelivery.utils.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestaurantRepository restaurantRepository;

    @Override
    public UserDto saveUser(User user) {

        Optional<User> userByEmail = this.userRepository.findByEmail(user.getEmail());

        if (userByEmail.isPresent()) {
            throw new UserAlreadyExistsException("User with this "+ user.getEmail() +" email already registered!");
        }

        user.setRole(Role.NORMAL);
        user.setUserRegistrationDate(LocalDateTime.now());
        user.setVerificationCode(null);

        if (!user.getPassword().equals(user.getConfirmPassword())){
            throw new PasswordMismatchException("Password & Confirm Password Not Match!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
        return ModelMapper.userToDto(user);

    }

    @Override
    public UserDto getUser(String id) {

        if (id.contains("@") && id.endsWith(".com")){
            return getUserByEmail(id);
        }

        User user = this.userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserNotFoundException("User with this id: " + id + " not found!"));

        return ModelMapper.userToDto(user);
    }

    private UserDto getUserByEmail(String email) {
        User user = this.userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("User with this email " + email + " not found"));
        return ModelMapper.userToDto(user);
    }

    @Override
    public UserDto updateUser(String id, User user) {
        // I will implement this later
        return null;
    }

    @Override
    public void deleteUser(String id) {
        User user = this.userRepository.findById(UUID.fromString(id)).orElseThrow(() ->
                new UserNotFoundException("User with this id: " + id + " not found!"));
        this.userRepository.delete(user);
    }

    @Override
    public List<User> allUsers() {
        return this.userRepository.findAll();
    }

    // This api for admin panel
    @Override
    @Cacheable(value = "restaurantByAdmin")
    public RestaurantDto getRestaurantByAdmin(String adminId) {
        Restaurant restaurant = this.restaurantRepository.findRestaurantByRestaurantAdminId(UUID.fromString(adminId))
                .orElseThrow(()-> new ResourceNotFoundException("Restaurant Not FOUND! with this admin id: "+adminId));
        return ModelMapper.restaurantToDto(restaurant);
    }
}
