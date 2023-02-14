package com.example.crossFit.model.mapStruct;

import com.example.crossFit.model.dto.CoachDTO;
import com.example.crossFit.model.entity.Coach;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoachMapper {

    @Mapping(target = "coachId", source = "id")
    @Mapping(target = "coachName", source = "name")
    @Mapping(target = "coachEmail", source = "email")
    @Mapping(target = "coachPhoneNumber", source = "phoneNumber")
    CoachDTO toCoachDTO(Coach coach);

    @Mapping(target = "id", source = "coachId")
    @Mapping(target = "name", source = "coachName")
    @Mapping(target = "password", source = "coachPassword")
    @Mapping(target = "email", source = "coachEmail")
    @Mapping(target = "phoneNumber", source = "coachPhoneNumber")
    Coach toCoach(CoachDTO coachDTO);

}
