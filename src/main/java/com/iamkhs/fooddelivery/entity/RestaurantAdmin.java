package com.iamkhs.fooddelivery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RestaurantAdmin extends User{

    @OneToOne
    private Restaurant manageRestaurant;
}
