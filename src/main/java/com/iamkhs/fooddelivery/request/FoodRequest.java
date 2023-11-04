package com.iamkhs.fooddelivery.request;

import java.io.Serializable;

public record FoodRequest (
        int foodId,
        String restaurantId) implements Serializable {
}
