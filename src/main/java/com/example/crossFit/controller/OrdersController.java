package com.example.crossFit.controller;

import com.example.crossFit.config.SwaggerConfig;
import com.example.crossFit.model.dto.OrdersDTO;
import com.example.crossFit.model.entity.Orders;
import com.example.crossFit.model.mapStruct.OrdersMapper;
import com.example.crossFit.response.SuccessResponse;
import com.example.crossFit.service.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(SwaggerConfig.ORDERS_TAG)
@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrdersController {
    private final OrdersService ordersService;
    private final OrdersMapper ordersMapper;

    @Autowired
    public OrdersController(OrdersService ordersService, OrdersMapper ordersMapper) {
        this.ordersService = ordersService;
        this.ordersMapper = ordersMapper;
    }


    @ApiOperation("Метод для удаления заказа по ID заказа")
    @DeleteMapping("/deleteOrder")
    public ResponseEntity<SuccessResponse> deleteOrders(@RequestParam Integer id) {
        return ResponseEntity.ok(ordersService.deleteOrders(id));
    }

    @ApiOperation("Метод для поиска всех заказов по ID клиента")
    @GetMapping("/myOrders")
    public ResponseEntity<List<OrdersDTO>> showMyOrders(@RequestParam String phoneNumber) {
        List<Orders> orders = ordersService.showMyOrders(phoneNumber);
        return ResponseEntity.ok(ordersMapper.toOrdersListDTO(orders));

    }
}
