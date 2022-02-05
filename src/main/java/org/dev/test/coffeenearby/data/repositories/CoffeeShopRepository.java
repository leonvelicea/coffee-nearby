package org.dev.test.coffeenearby.data.repositories;

import org.dev.test.coffeenearby.data.model.CoffeeShop;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeShopRepository extends JpaRepository<CoffeeShop, Long> {

    @Query(value="SELECT * from coffee_shops where ST_DistanceSphere(CAST(geo_location AS geometry), :p) < :distanceM", nativeQuery = true)
    List<CoffeeShop> findCoffeeShopsNearby(@Param("p") Point p, @Param("distanceM") double distanceM);
}
