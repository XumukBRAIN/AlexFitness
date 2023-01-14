package com.example.AlexFitness.model.mapStruct;


import com.example.AlexFitness.model.dto.CoachDTO;
import com.example.AlexFitness.model.entity.Coach;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoachMapper {


    @Mapping(target = "coachId", source = "id")
    @Mapping(target = "coachName", source = "name")
    CoachDTO toCoachDTO(Coach coach);

    @Mapping(target = "id", source = "coachId")
    @Mapping(target = "name", source = "coachName")
    Coach toCoach(CoachDTO coachDTO);
}
