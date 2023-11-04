package com.iamkhs.fooddelivery.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String streetAddress;
    private String area;
    private String city;
    private String pauroshova;
    private String district;
    private String subDistrict;
    private String postal;


    @OneToOne
    @JsonBackReference
    private Restaurant restaurant;
}
