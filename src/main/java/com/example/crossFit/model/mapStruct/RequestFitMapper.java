package com.example.crossFit.model.mapStruct;

import com.example.crossFit.model.dto.RequestFitDTO;
import com.example.crossFit.model.entity.RequestFit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestFitMapper {

    @Mapping(target = "requestFitId", source = "id")
    @Mapping(target = "requestFitTitle", source = "title")
    @Mapping(target = "requestFitSubId", source = "subId")
    @Mapping(target = "requestFitCoachId", source = "coachId")
    @Mapping(target = "requestFitPhoneNumber", source = "phoneNumber")
    RequestFitDTO toRequestFitDto(RequestFit requestFit);

    List<RequestFitDTO> toRequestFitListDTO(List<RequestFit> requestFit);

    @Mapping(target = "id", source = "requestFitId")
    @Mapping(target = "title", source = "requestFitTitle")
    @Mapping(target = "subId", source = "requestFitSubId")
    @Mapping(target = "coachId", source = "requestFitCoachId")
    @Mapping(target = "phoneNumber", source = "requestFitPhoneNumber")
    RequestFit toRequestFit(RequestFitDTO requestFitDTO);

    List<RequestFit> toRequestFitList(List<RequestFitDTO> requestFitDTO);

}
