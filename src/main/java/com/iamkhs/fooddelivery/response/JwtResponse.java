package com.iamkhs.fooddelivery.response;

import com.iamkhs.fooddelivery.dto.UserDto;

public record JwtResponse (String token, UserDto userDto) {
}
