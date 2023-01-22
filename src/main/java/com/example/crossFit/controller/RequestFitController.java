package com.example.crossFit.controller;

import com.example.crossFit.config.SwaggerConfig;
import com.example.crossFit.model.dto.RequestFitDTO;
import com.example.crossFit.model.entity.RequestFit;
import com.example.crossFit.model.mapStruct.RequestFitMapper;
import com.example.crossFit.service.RequestFitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/clientRequestFit")
@Api(tags = SwaggerConfig.REQUEST_FIT_TAG)
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

    @ApiOperation("Метод для поиска всех неподтвержденных заявок ")
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

    @ApiOperation("Метод для оплаты абонемента")
    @PatchMapping("/paySub")
    public void subscriptionPayment(@RequestParam BigDecimal money, @RequestParam String email) {
        requestFitService.subscriptionPayment(money, email);
    }

}


