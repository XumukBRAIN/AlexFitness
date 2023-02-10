package com.example.crossFit.controller;

import com.example.crossFit.config.SwaggerConfig;
import com.example.crossFit.model.dto.RequestFitDTO;
import com.example.crossFit.model.entity.RequestFit;
import com.example.crossFit.model.mapStruct.RequestFitMapper;
import com.example.crossFit.response.SuccessResponse;
import com.example.crossFit.service.RequestFitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/requestFit")
@CrossOrigin
@Api(tags = SwaggerConfig.REQUEST_FIT_TAG)
public class RequestFitController {

    private final RequestFitService requestFitService;
    private final RequestFitMapper requestFitMapper;

    @Autowired
    public RequestFitController(RequestFitService requestFitService, RequestFitMapper requestFitMapper) {
        this.requestFitService = requestFitService;
        this.requestFitMapper = requestFitMapper;
    }

    @ApiOperation("Метод для поиска заявки по номеру телефона")
    @PostMapping("/findByPhoneNumber")
    public ResponseEntity<RequestFitDTO> findByPhoneNumber(@RequestParam String phoneNumber) {
        RequestFit requestFit = requestFitService.findByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(requestFitMapper.toRequestFitDto(requestFit));
    }

    @ApiOperation("Метод для создание заявки на абонемент и тренера")
    @PostMapping("/create")
    public ResponseEntity<SuccessResponse> createRequestFit(@RequestBody RequestFitDTO requestFitDTO) {
        RequestFit requestFit = requestFitMapper.toRequestFit(requestFitDTO);
        return ResponseEntity.ok(requestFitService.createRequest(requestFit));
    }

    @ApiOperation("Метод для удаления заявки на абонемент по номеру телефона клиента")
    @DeleteMapping("/deleteRequestFit")
    public ResponseEntity<SuccessResponse> deleteRequestFit(@RequestParam String phoneNumber) {
        return ResponseEntity.ok(requestFitService.deleteRequestFit(phoneNumber));
    }

    @ApiOperation("Метод для поиска всех неподтвержденных заявок ")
    @GetMapping("/notApproved")
    public ResponseEntity<List<RequestFitDTO>> findNotApprovedRequests() {
        List<RequestFit> listRequestFit = requestFitService.showAllRequestFitIsNotApprove();
        return ResponseEntity.ok(requestFitMapper.toRequestFitListDTO(listRequestFit));
    }

    @ApiOperation("Метод для поиска всех заявок на абонемент и тренера")
    @GetMapping("/showRequestFit")
    public ResponseEntity<List<RequestFitDTO>> showAllRequestFit() {
        List<RequestFit> listRequestFit = requestFitService.findAllRequestFit();
        return ResponseEntity.ok(requestFitMapper.toRequestFitListDTO(listRequestFit));
    }

    @ApiOperation("Метод для отклонения заявки на абонемент")
    @PatchMapping("/rejectRequestFit")
    public ResponseEntity<SuccessResponse> rejectRequestFit(@RequestParam String phoneNumber) {
        return ResponseEntity.ok(requestFitService.rejectRequestFit(phoneNumber));
    }

    @ApiOperation("Метод для одобрения заявки на абонемент")
    @PatchMapping("/approveRequestFit")
    public ResponseEntity<SuccessResponse> approveRequestFit(@RequestParam String phoneNumber) {
        return ResponseEntity.ok(requestFitService.approve(phoneNumber));
    }

    @ApiOperation("Метод для оплаты абонемента")
    @PatchMapping("/paySub")
    public ResponseEntity<SuccessResponse> subscriptionPayment(@RequestParam BigDecimal money, @RequestParam String email) {
        return ResponseEntity.ok(requestFitService.subscriptionPayment(money, email));
    }

}


