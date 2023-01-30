package com.example.crossFit.model.mapStruct;

import com.example.crossFit.model.dto.ManagerDTO;
import com.example.crossFit.model.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ManagerMapper {

    @Mapping(target = "managerId", source = "id")
    @Mapping(target = "managerName", source = "name")
    ManagerDTO toManagerDTO(Manager manager);

    @Mapping(target = "id", source = "managerId")
    @Mapping(target = "name", source = "managerName")
    Manager toManager(ManagerDTO managerDTO);

}
