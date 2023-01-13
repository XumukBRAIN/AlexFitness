package com.example.AlexFitness.model.mapStruct;


import com.example.AlexFitness.model.dto.RequestFitDTO;
import com.example.AlexFitness.model.entity.RequestFit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RequestFitMapper {

    @Mapping(target = "requestFitId", source = "id")
    @Mapping(target = "requestFitTitle", source = "title")
    @Mapping(target = "requestFitSubId", source = "subId")
    @Mapping(target = "requestFitCoachId", source = "coachId")
    @Mapping(target = "requestFitPhoneNumber", source = "phoneNumber")
    RequestFitDTO toRequestFitDto(RequestFit requestFit);


    @Mapping(target = "id", source = "requestFitId")
    @Mapping(target = "title", source = "requestFitTitle")
    @Mapping(target = "subId", source = "requestFitSubId")
    @Mapping(target = "coachId", source = "requestFitCoachId")
    @Mapping(target = "phoneNumber", source = "requestFitPhoneNumber")
    RequestFit toRequestFit(RequestFitDTO requestFitDTO);
}
