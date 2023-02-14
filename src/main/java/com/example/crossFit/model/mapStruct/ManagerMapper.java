package com.example.crossFit.model.mapStruct;

import com.example.crossFit.model.dto.ManagerDTO;
import com.example.crossFit.model.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ManagerMapper {

    @Mapping(target = "managerId", source = "id")
    @Mapping(target = "managerName", source = "name")
    @Mapping(target = "managerEmail", source = "email")
    @Mapping(target = "managerPhoneNumber", source = "phoneNumber")
    ManagerDTO toManagerDTO(Manager manager);

    List<ManagerDTO> toListManagerDTO(List<Manager> list);

    @Mapping(target = "id", source = "managerId")
    @Mapping(target = "name", source = "managerName")
    @Mapping(target = "password", source = "managerPassword")
    @Mapping(target = "email", source = "managerEmail")
    @Mapping(target = "phoneNumber", source = "managerPhoneNumber")
    Manager toManager(ManagerDTO managerDTO);

    List<Manager> toListManager(List<ManagerDTO> list);

}
