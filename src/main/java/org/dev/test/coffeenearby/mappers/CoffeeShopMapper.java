package org.dev.test.coffeenearby.mappers;

import org.dev.test.coffeenearby.data.model.CoffeeShop;
import org.dev.test.generated.model.CoffeeShopDTO;
import org.dev.test.generated.model.ScheduleDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, SocialDataMapper.class}, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CoffeeShopMapper {

    CoffeeShop convertDTO(CoffeeShopDTO coffeeShopDTO);

    List<CoffeeShop> convertDTOS(List<CoffeeShopDTO> coffeeShopDTOs);

    CoffeeShopDTO convertModel(CoffeeShop coffeeShop);

    List<CoffeeShopDTO> convertModels(List<CoffeeShop> coffeeShops);

    @AfterMapping
    default void setLocationAndSchedule(@MappingTarget CoffeeShopDTO coffeeShopDTO, CoffeeShop coffeeShop) {
        coffeeShopDTO.setLocation(String.format("(lat, lng)=(%f, %f)", coffeeShop.getGeoLocation().getX(), coffeeShop.getGeoLocation().getY()));
        // for the sake of simplicity
        coffeeShopDTO.setSchedule(new ScheduleDTO().openHour("08:00").closeHour("20:00"));
    }
}
