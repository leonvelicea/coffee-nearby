package org.dev.test.coffeenearby.data.repositories;

import com.vividsolutions.jts.geom.Point;
import org.dev.test.coffeenearby.data.model.CoffeeShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeShopRepository extends JpaRepository<CoffeeShop, Long> {

    @Query(value="SELECT * from us_cities where ST_DistanceSphere(geoLocation, :p) < :distanceM", nativeQuery = true)
    List<CoffeeShop> findCoffeeShopsNearby(Point p, double distanceM);

}
