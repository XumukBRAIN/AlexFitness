package com.example.AlexFitness.service;


import com.example.crossFit.model.entity.Client;
import com.example.crossFit.model.entity.RequestFit;
import com.example.crossFit.repository.ClientRepo;
import com.example.crossFit.repository.RequestFitRepo;
import com.example.crossFit.response.SuccessResponse;
import com.example.crossFit.service.RequestFitService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RequestFitServiceTest {

    @Mock
    private RequestFitRepo requestFitRepo;
    @InjectMocks
    private RequestFitService requestFitService;
    @Mock
    private ClientRepo clientRepo;


    private static final RequestFit REQUEST_FIT = new RequestFit("Заявка на регистрацию", 1, 1,
            LocalDateTime.now(), "89999999999");

    private static final Client CLIENT = new Client("Иван", "89999999999", 1, 1, null, "@mail", null, "123", "ROLE_USER", false, false, LocalDate.now());

    @Test
    void createRequestTest() {
        Mockito.when(requestFitRepo.findByPhoneNumber(Mockito.anyString()))
                .thenReturn(Optional.empty());

        SuccessResponse actualResponse = requestFitService.createRequest(REQUEST_FIT);
        SuccessResponse expectedResponse = new SuccessResponse("Заявка успешно зарегистрирована!", HttpStatus.OK.value());

        Assertions.assertEquals(expectedResponse.getMessage(), actualResponse.getMessage());
        Assertions.assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());

        Mockito.verify(requestFitRepo, Mockito.times(1)).findByPhoneNumber(Mockito.anyString());
    }

    @Test
    void approveTest() {
        Mockito.when(requestFitRepo.findByPhoneNumber(Mockito.anyString()))
                .thenReturn(Optional.of(REQUEST_FIT));

        Mockito.when(clientRepo.findByPhoneNumber(Mockito.anyString()))
                .thenReturn(Optional.of(CLIENT));

        SuccessResponse actualResponse = requestFitService.approve(Mockito.anyString());
        SuccessResponse expectedResponse = new SuccessResponse("Заявка успешно обработана!", HttpStatus.OK.value());

        Assertions.assertEquals(actualResponse.getMessage(), expectedResponse.getMessage());
        Assertions.assertEquals(actualResponse.getStatusCode(), expectedResponse.getStatusCode());

        Mockito.verify(requestFitRepo, Mockito.times(1)).findByPhoneNumber(Mockito.anyString());
        Mockito.verify(clientRepo, Mockito.times(1)).findByPhoneNumber(Mockito.anyString());

    }

}
