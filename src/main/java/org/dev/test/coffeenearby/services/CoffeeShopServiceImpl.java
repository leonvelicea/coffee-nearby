package org.dev.test.coffeenearby.services;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.test.coffeenearby.data.model.CoffeeShop;
import org.dev.test.coffeenearby.data.repositories.CoffeeShopRepository;
import org.dev.test.coffeenearby.mappers.CoffeeShopMapper;
import org.dev.test.coffeenearby.utils.DistanceUtils;
import org.dev.test.generated.model.CoffeeShopDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoffeeShopServiceImpl implements CoffeeShopService {

    private final CoffeeShopMapper coffeeShopMapper;
    private final CoffeeShopRepository coffeeShopRepository;
    private final GeometryFactory geometryFactory;

    @Override
    public Optional<List<CoffeeShopDTO>> searchForCoffeeShopsNearby(Double lat, Double lng, Integer distance) {
        log.debug("Starting to search for coffee shops near [{}/{}]", lat, lng);
        Point point = geometryFactory.createPoint(new Coordinate(lng, lat));
        List<CoffeeShop> results = coffeeShopRepository.findCoffeeShopsNearby(point, distance);
        if(!CollectionUtils.isEmpty(results)) {
            log.info("Found coffee [{}] shops", results.size());
            return Optional.of(results.stream().map(coffeeShop -> convertAndCalculateDistance(lat, lng, coffeeShop)).collect(Collectors.toList()));
        }
        log.info("Did not find any results for give coordinates");
        return Optional.empty();
    }

    private CoffeeShopDTO convertAndCalculateDistance(Double lat, Double lng, CoffeeShop coffeeShop) {
        CoffeeShopDTO coffeeShopDTO = coffeeShopMapper.convertModel(coffeeShop);
        coffeeShopDTO.setDistance(DistanceUtils.calculateDistance(lat, lng, coffeeShop.getGeoLocation().getX(), coffeeShop.getGeoLocation().getY()));
        return coffeeShopDTO;
    }
}
