package com.example.AlexFitness.controller;


import com.example.AlexFitness.model.dto.RequestFitDTO;
import com.example.AlexFitness.model.entity.RequestFit;
import com.example.AlexFitness.model.mapStruct.RequestFitMapper;
import com.example.AlexFitness.service.RequestFitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Контроллер для заявок на абонемент и тренера")
@RestController
@RequestMapping("/clientRequestFit")
public class RequestFitController {
    private final RequestFitService requestFitService;
    private final RequestFitMapper requestFitMapper;

    @Autowired
    public RequestFitController(RequestFitService requestFitService, RequestFitMapper requestFitMapper) {
        this.requestFitService = requestFitService;
        this.requestFitMapper = requestFitMapper;
    }

    @ApiOperation("Метод для создание заявки на абонемент и тренера")
    @PostMapping("/createRequestFit")
    public void createRequest(@RequestBody RequestFitDTO requestFitDTO) {
        RequestFit requestFit = requestFitMapper.toRequestFit(requestFitDTO);
        requestFitService.createRequest(requestFit);
    }

    @GetMapping("/notApproved")
    public List<RequestFitDTO> findNotApprovedRequests() {
        List<RequestFit> listRequestFit = requestFitService.findNotApprovedRequests();
        return requestFitMapper.toRequestFitListDTO(listRequestFit);
    }

    @ApiOperation("Метод для поиска всех созданных заявок на абонемент и тренера")
    @GetMapping("/showRequestFit")
    public List<RequestFitDTO> showRequestFit() {
        List<RequestFit> listRequestFit = requestFitService.findNotApprovedRequests();
        return requestFitMapper.toRequestFitListDTO(listRequestFit);
    }

    @ApiOperation("Метод для отклонения заявки на абонемент")
    @PatchMapping("/rejectRequestFit")
    public void rejectRequestFit(@RequestParam String phoneNumber) {
        requestFitService.rejectRequestFit(phoneNumber);
    }

    @ApiOperation("Метод для одобрения заявки на абонемент")
    @PatchMapping("/approveRequestFit")
    public void approveRequestFit(@RequestParam String phoneNumber) {
        requestFitService.approve(phoneNumber);
    }


}


