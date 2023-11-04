package com.iamkhs.fooddelivery.request;

import java.io.Serializable;

public record LocationRequest(Double latitude,
                              Double longitude) implements Serializable {
}
