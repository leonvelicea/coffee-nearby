package org.dev.test.coffeenearby.services;

import org.dev.test.coffeenearby.SpringTestConfiguration;
import org.dev.test.coffeenearby.data.model.Address;
import org.dev.test.coffeenearby.data.model.CoffeeShop;
import org.dev.test.coffeenearby.data.model.SocialData;
import org.dev.test.coffeenearby.data.repositories.CoffeeShopRepository;
import org.dev.test.coffeenearby.mappers.AddressMapper;
import org.dev.test.coffeenearby.mappers.CoffeeShopMapper;
import org.dev.test.coffeenearby.mappers.SocialDataMapper;
import org.dev.test.generated.model.CoffeeShopDTO;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringTestConfiguration.class})
class CoffeeShopServiceImplTest {

    public static final String TEST_EMAIL = "test@test.com";
    public static final String TEST_TELEPHONE = "1234567890";
    public static final String TEST_FACEBOOK_URL = "https://facebook.com";
    public static final String TEST_INSTAGRAM_URL = "https://instagram.com";
    public static final String TEST_TRIP_ADVISOR_URL = "https://trip-advisor.com";

    public static final String TEST_STREET_NUMBER = "1";
    public static final String TEST_STREET_NAME = "Test";
    public static final String TEST_CITY_NAME = "Test";
    public static final String TEST_COUNTRY_NAME = "Test";
    public static final String TEST_POSTAL_CODE = "123456";
    public static final String TEST_COFFEE_SHOP_NAME = "Test Coffee Shop";
    private final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
    private CoffeeShopService coffeeShopService;
    private CoffeeShopRepository coffeeShopRepository;
    @Autowired
    private CoffeeShopMapper coffeeShopMapper;

    @BeforeEach
    void setupTests() {
        Mappers.getMapper(AddressMapper.class);
        Mappers.getMapper(SocialDataMapper.class);
        coffeeShopRepository = Mockito.mock(CoffeeShopRepository.class);
        coffeeShopService = new CoffeeShopServiceImpl(coffeeShopRepository, geometryFactory, coffeeShopMapper);
    }

    @Test
    void searchForCoffeeShopsNearby_Success() {
        Mockito.when(coffeeShopRepository.findCoffeeShopsNearby(ArgumentMatchers.any(), ArgumentMatchers.anyDouble())).thenReturn(getCoffeeShopsFromDb());
        Optional<List<CoffeeShopDTO>> results = coffeeShopService.searchForCoffeeShopsNearby(47.146836, 27.583606, 5);
        assertTrue(results.isPresent(), "Result lists should be present");
        assertFalse(results.get().isEmpty(), "Result lists should not be empty");
        CoffeeShopDTO coffeeShop = results.get().get(0);

        assertEquals(TEST_COFFEE_SHOP_NAME, coffeeShop.getName());
        assertEquals(TEST_STREET_NUMBER, coffeeShop.getAddress().getNumber());
        assertEquals(TEST_STREET_NAME, coffeeShop.getAddress().getStreet());
        assertEquals(TEST_POSTAL_CODE, coffeeShop.getAddress().getPostalCode());
        assertEquals(TEST_CITY_NAME, coffeeShop.getAddress().getCity());
        assertEquals(TEST_COUNTRY_NAME, coffeeShop.getAddress().getCountry());

        assertEquals(TEST_EMAIL, coffeeShop.getSocialData().getEmail());
        assertEquals(TEST_TELEPHONE, coffeeShop.getSocialData().getTelephone());
        assertEquals(TEST_FACEBOOK_URL, coffeeShop.getSocialData().getFacebookUrl());
        assertEquals(TEST_INSTAGRAM_URL, coffeeShop.getSocialData().getInstagramUrl());
        assertEquals(TEST_TRIP_ADVISOR_URL, coffeeShop.getSocialData().getTripAdvisorUrl());
        assertEquals(1611.3, coffeeShop.getDistance());
    }

    @Test
    void searchForCoffeeShopsNearby_SuccessNoContentFound() {
        Mockito.when(coffeeShopRepository.findCoffeeShopsNearby(ArgumentMatchers.any(), ArgumentMatchers.anyDouble())).thenReturn(new ArrayList<>());
        Optional<List<CoffeeShopDTO>> results = coffeeShopService.searchForCoffeeShopsNearby(47.146836, 27.583606, 5);
        assertFalse(results.isPresent(), "Result lists should not be present");
    }

    private List<CoffeeShop> getCoffeeShopsFromDb() {
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.setName(TEST_COFFEE_SHOP_NAME);
        coffeeShop.setAddress(getAddress(coffeeShop));
        coffeeShop.setSocialData(getSocialData(coffeeShop));
        coffeeShop.setGeoLocation(geometryFactory.createPoint(new Coordinate(47.159146, 27.572409)));
        return List.of(coffeeShop);
    }

    @NotNull
    private SocialData getSocialData(CoffeeShop coffeeShop) {
        SocialData socialData = new SocialData();
        socialData.setEmail(TEST_EMAIL);
        socialData.setTelephone(TEST_TELEPHONE);
        socialData.setFacebookUrl(TEST_FACEBOOK_URL);
        socialData.setInstagramUrl(TEST_INSTAGRAM_URL);
        socialData.setTripAdvisorUrl(TEST_TRIP_ADVISOR_URL);
        socialData.setCoffeeShop(coffeeShop);
        return socialData;
    }

    @NotNull
    private Address getAddress(CoffeeShop coffeeShop) {
        Address address = new Address();
        address.setNumber(TEST_STREET_NUMBER);
        address.setStreet(TEST_STREET_NAME);
        address.setCity(TEST_CITY_NAME);
        address.setCountry(TEST_COUNTRY_NAME);
        address.setPostalCode(TEST_POSTAL_CODE);
        address.setCoffeeShop(coffeeShop);
        return address;
    }
}