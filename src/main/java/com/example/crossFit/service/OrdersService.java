package com.example.crossFit.service;

import com.example.crossFit.exceptions.EntityNotFoundException;
import com.example.crossFit.model.entity.Orders;
import com.example.crossFit.repository.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {
    private final OrdersRepo ordersRepo;

    @Autowired
    public OrdersService(OrdersRepo ordersRepo) {
        this.ordersRepo = ordersRepo;
    }


    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @Transactional
    public void deleteOrders(Integer id) {
        Optional<Orders> o = ordersRepo.findById(id);
        if (!o.isPresent()) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Заказ не найден");
        }
        ordersRepo.deleteById(id);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = true)
    public List<Orders> showMyOrders(String phoneNumber) {
        List<Orders> orders = ordersRepo.findByPhoneNumber(phoneNumber);
        if (orders.isEmpty()) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "По данному телефону не найдено ни одного заказа");
        }
        return orders;
    }

}

