package com.iamkhs.fooddelivery.request;

import com.iamkhs.fooddelivery.dto.UserDto;

public record OrderRequest (int id,
                            String name,
                            String description,
                            Double price,
                            String restaurantId,
                            UserDto userDto){
}
