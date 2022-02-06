package org.dev.test.coffeenearby.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class CoffeeShopRequestValidator {

    public void validatePoint(Double lat, Double lng) {
        validateLatitude(lat);
        validateLongitude(lng);
    }

    private void validateLatitude(Double lat) {
        if(Objects.isNull(lat)) {
            throwNotFoundException("lat");
        }
    }

    private void validateLongitude(Double lng) {
        if(Objects.isNull(lng)) {
            throwNotFoundException("lng");
        }
    }

    private void throwNotFoundException(String parameterName) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Parameter \"%s\" is mandatory", parameterName));
    }
}
