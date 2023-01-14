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
    @Mapping(target = "coach", source = "coach")
    @Mapping(target = "subscriptionId", source = "subscriptionId")
    ClientDTO toClientDTO(Client client);


    @Mapping(target = "id", source = "clientId")
    @Mapping(target = "name", source = "clientName")
    @Mapping(target = "phoneNumber", source = "clientPhoneNumber")
    @Mapping(target = "coach", source = "coach")
    @Mapping(target = "subscriptionId", source = "subscriptionId")
    Client toClient(ClientDTO clientDTO);
}
