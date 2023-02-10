package com.example.crossFit.service;

import com.example.crossFit.exceptions.ResourceAlreadyIsRegisteredException;
import com.example.crossFit.exceptions.ResourceNotFoundException;
import com.example.crossFit.model.entity.Item;
import com.example.crossFit.model.entity.Orders;
import com.example.crossFit.repository.ItemRepo;
import com.example.crossFit.repository.OrdersRepo;
import com.example.crossFit.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepo itemRepo;
    private final OrdersRepo ordersRepo;

    @Autowired
    public ItemService(ItemRepo itemRepo, OrdersRepo ordersRepo) {
        this.itemRepo = itemRepo;
        this.ordersRepo = ordersRepo;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional(readOnly = true)
    public List<Item> showMyItems(String phoneNumber) {
        List<Orders> orders = ordersRepo.findByPhoneNumber(phoneNumber);
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("Заказов по данному номеру телефона: "
                    + phoneNumber + " не найдено!");
        }
        List<Item> items = new ArrayList<>();
        for (Orders order : orders) {
            items.addAll(order.getItems());
            return items;
        }
        if (items.isEmpty()) {
            throw new ResourceNotFoundException("Добавленных товаров в заказ по данному номеру телефона: "
                    + phoneNumber + " не найдено!");
        }
        return items;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public SuccessResponse createItem(Item item) {
        Optional<Item> i = itemRepo.findById(item.getId());
        if (!i.isPresent()) {
            itemRepo.save(item);
            return new SuccessResponse("Товар успешно добавлен в магазин!", HttpStatus.OK.value());

        } else
            throw new ResourceAlreadyIsRegisteredException("Товар с таким id: " + item.getId() + " уже присуствует в магазине!");
    }
}
