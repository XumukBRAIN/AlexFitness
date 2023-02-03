package com.example.crossFit.model.mapStruct;

import com.example.crossFit.model.dto.ManagerDTO;
import com.example.crossFit.model.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ManagerMapper {

    @Mapping(target = "managerId", source = "id")
    @Mapping(target = "managerName", source = "name")
    @Mapping(target = "managerEmail", source = "email")
    ManagerDTO toManagerDTO(Manager manager);

    @Mapping(target = "id", source = "managerId")
    @Mapping(target = "name", source = "managerName")
    @Mapping(target = "password", source = "managerPassword")
    @Mapping(target = "email", source = "managerEmail")
    Manager toManager(ManagerDTO managerDTO);

}
