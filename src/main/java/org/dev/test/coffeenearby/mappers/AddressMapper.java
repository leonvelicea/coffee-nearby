package org.dev.test.coffeenearby.mappers;

import org.dev.test.coffeenearby.data.model.Address;
import org.dev.test.generated.model.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AddressMapper {
    AddressDTO convert(Address address);
    Address convert(AddressDTO addressDTO);
}
