package com.iamkhs.fooddelivery.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.iamkhs.fooddelivery.annotations.NotEmptyNotBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Getter
@Setter
@Entity
@DynamicUpdate
public class FoodMenu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmptyNotBlank
    private String name;

    @NotEmptyNotBlank
    private String description;

    @NotNull
    private Double price;
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Restaurant restaurant;
}
