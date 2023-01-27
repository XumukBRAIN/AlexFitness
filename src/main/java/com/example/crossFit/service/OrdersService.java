package com.example.crossFit.service;

import com.example.crossFit.exceptions.EntityAlreadyIsRegisteredException;
import com.example.crossFit.exceptions.EntityNotFoundException;
import com.example.crossFit.model.entity.Orders;
import com.example.crossFit.repository.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrdersService {
    private final OrdersRepo ordersRepo;

    @Autowired
    public OrdersService(OrdersRepo ordersRepo) {
        this.ordersRepo = ordersRepo;
    }

    @Transactional
    public void createOrders(Orders orders) {
        Optional<Orders> o = ordersRepo.findById(orders.getId());
        if (!o.isPresent()) {
            ordersRepo.save(orders);
        } else throw new EntityAlreadyIsRegisteredException(HttpStatus.BAD_REQUEST,
                "Заказ с таким ID уже есть в базе");
    }

    @Transactional
    public void deleteOrders(Integer id) {
        Optional<Orders> o = ordersRepo.findById(id);
        if (!o.isPresent()) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Заказ не найден");
        }
        ordersRepo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Orders> showMyOrders(UUID id) {
        return ordersRepo.findByClientId(id);
    }
}
