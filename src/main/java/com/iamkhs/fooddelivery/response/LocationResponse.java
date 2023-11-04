package com.iamkhs.fooddelivery.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class LocationResponse implements Serializable {
    private String county;
    private String road;
    private String postcode;
    private String road_type;
    private String state_district;
    private String state;
    private String village;
    private String formattedAddress;
}