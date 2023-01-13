package com.example.AlexFitness.controller;


import com.example.AlexFitness.model.dto.RequestFitDTO;
import com.example.AlexFitness.model.entity.RequestFit;
import com.example.AlexFitness.model.mapStruct.RequestFitMapper;
import com.example.AlexFitness.service.RequestFitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
