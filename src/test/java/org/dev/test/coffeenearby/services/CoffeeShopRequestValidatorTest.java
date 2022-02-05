package org.dev.test.coffeenearby.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeShopRequestValidatorTest {

    private CoffeeShopRequestValidator coffeeShopRequestValidator;

    @BeforeEach
    void setupTests() {
        coffeeShopRequestValidator = new CoffeeShopRequestValidator();
    }

    @Test
    void validatePoint_NoErrors() {
        coffeeShopRequestValidator.validatePoint(47.159146, 27.572409);
    }

    @Test
    void validatePoint_ExceptionMissingLatitude() {
        assertThrows(ResponseStatusException.class, () -> coffeeShopRequestValidator.validatePoint(null, 27.572409));
    }

    @Test
    void validatePoint_ExceptionMissingLongitude() {
        assertThrows(ResponseStatusException.class, () -> coffeeShopRequestValidator.validatePoint(47.159146, null));
    }
}