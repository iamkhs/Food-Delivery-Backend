package com.iamkhs.fooddelivery.repository;

import com.iamkhs.fooddelivery.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

    /**
     * Finds all restaurants within a specified radius of a given geographic point.
     * The point is specified by its latitude and longitude, and the radius is in kilometers.
     * It uses the Haversine formula to calculate the great-circle distance between the given point and each restaurant.
     * The Haversine formula is implemented in SQL using trigonometric functions like SIN, COS, and ACOS.
     * The result of the formula is multiplied by 6371, which is the approximate radius of Earth in kilometers.
     * The query returns all restaurants where the calculated distance is less than or equal to the provided radius.
     *
     * @param latitude The latitude of the reference point, specified in degrees.
     * @param longitude The longitude of the reference point, specified in degrees.
     * @param radius The search radius, specified in kilometers.
     * @return A list of restaurants that are within the specified radius of the reference point.
     */
    @Query(value = "SELECT * FROM restaurant r " +
            "WHERE ACOS(SIN(:latitude) * SIN(RADIANS(r.latitude)) + " +
            "COS(:latitude) * COS(RADIANS(r.latitude)) * COS(:longitude - RADIANS(r.longitude))) * 6371 <= :radius", nativeQuery = true)
    List<Restaurant> findRestaurantsNearby(
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude,
            @Param("radius") Double radius
    );


    Optional<Restaurant> findRestaurantByRestaurantAdminId(UUID id);

}
