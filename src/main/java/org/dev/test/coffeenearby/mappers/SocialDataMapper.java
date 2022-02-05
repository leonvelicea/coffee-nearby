package org.dev.test.coffeenearby.mappers;

import org.dev.test.coffeenearby.data.model.SocialData;
import org.dev.test.generated.model.SocialDataDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface SocialDataMapper {

    SocialDataDTO convert(SocialData socialData);
    SocialData convert(SocialDataDTO socialDataDTO);
}
