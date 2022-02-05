package org.dev.test.coffeenearby.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.test.coffeenearby.data.model.CoffeeShop;
import org.dev.test.coffeenearby.data.repositories.CoffeeShopRepository;
import org.dev.test.coffeenearby.mappers.CoffeeShopMapper;
import org.dev.test.coffeenearby.utils.DistanceUtils;
import org.dev.test.generated.model.CoffeeShopDTO;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.dev.test.coffeenearby.utils.DistanceUtils.ROUND_MATH_CONTEXT;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoffeeShopServiceImpl implements CoffeeShopService {

    private final CoffeeShopRepository coffeeShopRepository;
    private final GeometryFactory geometryFactory;
    private final CoffeeShopMapper coffeeShopMapper;

    @Override
    public Optional<List<CoffeeShopDTO>> searchForCoffeeShopsNearby(Double lat, Double lng, Integer distance) {
        log.debug("Starting to search for coffee shops near [{}/{}]", lat, lng);
        Point point = geometryFactory.createPoint(new Coordinate(lat, lng));
        List<CoffeeShop> results = coffeeShopRepository.findCoffeeShopsNearby(point, distance);
        if (!CollectionUtils.isEmpty(results)) {
            log.info("Found [{}] coffee shops", results.size());
            return Optional.of(results.stream().map(coffeeShop -> convertAndCalculateDistance(lat, lng, coffeeShop)).sorted(Comparator.comparingDouble(CoffeeShopDTO::getDistance)).collect(Collectors.toList()));
        }
        log.info("Did not find any results for given coordinates");
        return Optional.empty();
    }

    private CoffeeShopDTO convertAndCalculateDistance(Double lat, Double lng, CoffeeShop coffeeShop) {
        CoffeeShopDTO coffeeShopDTO = coffeeShopMapper.convertModel(coffeeShop);
        double distance = BigDecimal.valueOf(DistanceUtils.calculateDistance(lat, lng, coffeeShop.getGeoLocation().getX(), coffeeShop.getGeoLocation().getY())).round(ROUND_MATH_CONTEXT).doubleValue();
        coffeeShopDTO.setDistance(distance);
        return coffeeShopDTO;
    }
}
