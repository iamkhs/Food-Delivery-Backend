package com.iamkhs.fooddelivery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    private Integer productId;
    private String name;
    private String description;
    private Double price;
    private String restaurantId;

    @ManyToOne
    private User user;
}
