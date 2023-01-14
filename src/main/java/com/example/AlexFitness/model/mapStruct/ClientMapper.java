package com.example.AlexFitness.model.mapStruct;

import com.example.AlexFitness.model.dto.ClientDTO;
import com.example.AlexFitness.model.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ClientMapper {


    @Mapping(target = "clientId", source = "id")
    @Mapping(target = "clientName", source = "name")
    @Mapping(target = "clientPhoneNumber", source = "phoneNumber")
    @Mapping(target = "clientCoach", source = "coach")
    @Mapping(target = "clientSubscriptionId", source = "subscriptionId")
    @Mapping(target = "clientEmail", source = "email")
    ClientDTO toClientDTO(Client client);


    @Mapping(target = "id", source = "clientId")
    @Mapping(target = "name", source = "clientName")
    @Mapping(target = "phoneNumber", source = "clientPhoneNumber")
    @Mapping(target = "coach", source = "clientCoach")
    @Mapping(target = "subscriptionId", source = "clientSubscriptionId")
    @Mapping(target = "email", source = "clientEmail")
    Client toClient(ClientDTO clientDTO);
}
