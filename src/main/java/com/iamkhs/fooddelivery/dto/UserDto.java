package com.iamkhs.fooddelivery.dto;

import com.iamkhs.fooddelivery.entity.Role;

import java.io.Serializable;
import java.util.UUID;

public record UserDto(UUID id,
                      String name,
                      String email,
                      String phone,
                      String address,
                      boolean enable,
                      Role role) implements Serializable {
}