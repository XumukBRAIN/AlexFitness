package com.example.crossFit.model.mapStruct;

import com.example.crossFit.model.dto.OrdersDTO;
import com.example.crossFit.model.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdersMapper {


    @Mapping(target = "ordersId", source = "id")
    @Mapping(target = "ordersNumber", source = "number")
    @Mapping(target = "ordersTitle", source = "title")
    @Mapping(target = "ordersSum", source = "sum")
    OrdersDTO toOrdersDTO(Orders orders);

    List<OrdersDTO> toOrdersListDTO(List<Orders> orders);

    @Mapping(target = "id", source = "ordersId")
    @Mapping(target = "number", source = "ordersNumber")
    @Mapping(target = "title", source = "ordersTitle")
    @Mapping(target = "sum", source = "ordersSum")
    Orders toOrders(OrdersDTO ordersDTO);

    List<Orders> toOrderList(List<OrdersDTO> ordersDTO);

}
