package org.dev.test.coffeenearby.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceUtilsTest {


    @Test
    public void testCalculateDistance() {
        // center of Iasi 47.162414 / 27.589262
        // center of Brasov 45.644822 / 25.595171
        double distance = DistanceUtils.calculateDistance(47.162414, 27.589262, 45.644822, 25.595171);
        assertEquals(227954.64729844566, distance);
    }
}