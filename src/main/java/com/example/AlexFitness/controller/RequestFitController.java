package com.example.AlexFitness.controller;


import com.example.AlexFitness.model.dto.RequestFitDTO;
import com.example.AlexFitness.model.entity.RequestFit;
import com.example.AlexFitness.model.mapStruct.RequestFitMapper;
import com.example.AlexFitness.service.RequestFitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /*@GetMapping("/notApproved")
    public List<RequestFitDTO> findNotApprovedRequests() {
        List<RequestFit> listRequestFit = requestFitService.findNotApprovedRequests();
        //todo return requestFitMapper.toRequestFitDto();}


    }*/


}
