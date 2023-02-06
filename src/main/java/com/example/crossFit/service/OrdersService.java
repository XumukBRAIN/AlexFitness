package com.example.crossFit.service;

import com.example.crossFit.exceptions.ResourceNotFoundException;
import com.example.crossFit.model.entity.Orders;
import com.example.crossFit.repository.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdersService {
    private final OrdersRepo ordersRepo;

    @Autowired
    public OrdersService(OrdersRepo ordersRepo) {
        this.ordersRepo = ordersRepo;
    }


    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @Transactional
    public String deleteOrders(Integer id) {
        if (!ordersRepo.findById(id).isPresent()) {
            throw new ResourceNotFoundException("Заказ с таким id: " + id + " не найден!");
        }
        ordersRepo.deleteById(id);
        return "Заказ удален!";
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = true)
    public List<Orders> showMyOrders(String phoneNumber) {
        List<Orders> orders = ordersRepo.findByPhoneNumber(phoneNumber);
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("По данному номеру телефона: " + phoneNumber + " не найдено заказов!");
        }
        return orders;
    }

}

