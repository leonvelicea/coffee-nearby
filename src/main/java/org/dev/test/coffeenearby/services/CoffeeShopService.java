package org.dev.test.coffeenearby.services;

import org.dev.test.generated.model.CoffeeShopDTO;

import java.util.List;
import java.util.Optional;

public interface CoffeeShopService {

    Optional<List<CoffeeShopDTO>> searchForCoffeeShopsNearby(Double lat, Double lng, Integer distance);
}
