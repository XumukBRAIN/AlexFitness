package com.example.crossFit.model.mapStruct;

import com.example.crossFit.model.dto.SubscriptionDTO;
import com.example.crossFit.model.entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    @Mapping(target = "subId", source = "id")
    @Mapping(target = "subTitle", source = "title")
    @Mapping(target = "subPrice", source = "price")
    @Mapping(target = "subDescription", source = "description")
    SubscriptionDTO toSubscriptionDTO(Subscription subscription);

    List<SubscriptionDTO> toListSubscriptionDTO(List<Subscription> list);

    @Mapping(target = "id", source = "subId")
    @Mapping(target = "title", source = "subTitle")
    @Mapping(target = "price", source = "subPrice")
    @Mapping(target = "description", source = "subDescription")
    Subscription toSubscription(SubscriptionDTO subscriptionDTO);

    List<Subscription> toListSubscription(List<SubscriptionDTO> list);

}
