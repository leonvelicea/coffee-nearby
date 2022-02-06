package org.dev.test.coffeenearby.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.test.coffeenearby.services.CoffeeShopRequestValidator;
import org.dev.test.coffeenearby.services.CoffeeShopService;
import org.dev.test.coffeenearby.utils.DistanceUtils;
import org.dev.test.generated.api.CoffeeApi;
import org.dev.test.generated.model.CoffeeShopsNearby;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CoffeeShopController implements CoffeeApi {

    private final CoffeeShopService coffeeShopService;
    private final CoffeeShopRequestValidator coffeeShopRequestValidator;

    @Override
    public ResponseEntity<CoffeeShopsNearby> searchForCoffeeShops(Double lat, Double lng, Integer distance) {
        log.info("Search for coffee shops in location [{}/{}] and distance [{}]", lat, lng, distance);
        if (Objects.isNull(distance)) {
            log.warn("Distance is null, will use default value");
            distance = DistanceUtils.DEFAULT_DISTANCE;
        }
        coffeeShopRequestValidator.validatePoint(lat, lng);
        log.debug("All search data is valid, proceeding");
        return coffeeShopService.searchForCoffeeShopsNearby(lat, lng, distance).map(coffeeShops -> ResponseEntity.ok(new CoffeeShopsNearby().data(coffeeShops))).orElse(ResponseEntity.noContent().build());
    }
}
