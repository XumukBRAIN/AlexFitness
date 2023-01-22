package com.example.crossFit.controller;

import com.example.crossFit.config.SwaggerConfig;
import com.example.crossFit.model.dto.ManagerDTO;
import com.example.crossFit.model.entity.Manager;
import com.example.crossFit.model.mapStruct.ManagerMapper;
import com.example.crossFit.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/manager")
@Api(tags = SwaggerConfig.MANAGER_TAG)
public class ManagerController {

    private final ManagerService managerService;
    private final ManagerMapper managerMapper;

    @Autowired
    public ManagerController(ManagerService managerService, ManagerMapper managerMapper) {
        this.managerService = managerService;
        this.managerMapper = managerMapper;
    }


    @ApiOperation("Метод для поиска менеджера по ID")
    @GetMapping("/getOne")
    public Optional<Manager> getManagerById(@RequestParam Integer id) {
        return managerService.getManager(id);
    }

    @ApiOperation("Метод для поиска менеджера по имени")
    @GetMapping("/getOneByName")
    public ManagerDTO getManagerByName(@RequestParam String name) {
        Manager manager = managerService.findByName(name);
        return managerMapper.toManagerDTO(manager);
    }

    @ApiOperation("Метод для добавления менеджера в базу")
    @PostMapping("/createManager")
    public void createManager(@RequestBody ManagerDTO managerDTO) {
        Manager manager = managerMapper.toManager(managerDTO);
        managerService.createManager(manager);
    }

    @ApiOperation("Метод для удаления менеджера из базы")
    @DeleteMapping("/deleteManager")
    public void deleteManager(@RequestParam Integer id) {
        managerService.deleteManager(id);
    }

}
