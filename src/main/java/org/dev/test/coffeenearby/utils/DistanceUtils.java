package org.dev.test.coffeenearby.utils;

public final class DistanceUtils {

    private DistanceUtils(){
        //NOPE
    }

    /**
     * default distance value used if no value is passed from the API
     * value is expressed in km
     */
    public static int DEFAULT_DISTANCE = 5;

    /**
     * Calculate distance between two points; value that represents distance in meters
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return a value in double
     */
    public static double calculateDistance (double lat1, double lon1,
                                            double lat2, double lon2) {
        final double R = 6378100; // Radius of Earth in meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2), 2) *
                Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }

}
