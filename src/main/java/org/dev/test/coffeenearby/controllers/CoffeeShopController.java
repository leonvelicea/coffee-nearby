package org.dev.test.coffeenearby.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.test.coffeenearby.services.CoffeeShopRequestValidator;
import org.dev.test.coffeenearby.services.CoffeeShopService;
import org.dev.test.generated.api.CoffeeApi;
import org.dev.test.generated.model.CoffeeShopDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CoffeeShopController implements CoffeeApi {

    private final CoffeeShopService coffeeShopService;
    private final CoffeeShopRequestValidator coffeeShopRequestValidator;

    @Override
    public ResponseEntity<List<CoffeeShopDTO>> searchForCoffeeShops(Double lat, Double lng, Integer distance) {
        log.info("Search for coffee shops in location [{}/{}] and distance [{}]", lat, lng, distance);
        coffeeShopRequestValidator.validatePoint(lat, lng);
        log.debug("All search data is valid, proceeding");
        return coffeeShopService.searchForCoffeeShopsNearby(lat, lng, distance).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }
}
