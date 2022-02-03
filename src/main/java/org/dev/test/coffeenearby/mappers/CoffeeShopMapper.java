package org.dev.test.coffeenearby.mappers;

import org.dev.test.coffeenearby.data.model.CoffeeShop;
import org.dev.test.generated.model.CoffeeShopDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoffeeShopMapper {

    CoffeeShop convertDTO(CoffeeShopDTO coffeeShopDTO);

    List<CoffeeShop> convertDTOS(List<CoffeeShopDTO> coffeeShopDTOs);

    CoffeeShopDTO convertModel(CoffeeShop coffeeShop);

    List<CoffeeShopDTO> convertModels(List<CoffeeShop> coffeeShops);
}
