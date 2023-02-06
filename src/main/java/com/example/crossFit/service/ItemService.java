package com.example.crossFit.service;

import com.example.crossFit.exceptions.ResourceAlreadyIsRegistered;
import com.example.crossFit.exceptions.ResourceNotFoundException;
import com.example.crossFit.model.entity.Item;
import com.example.crossFit.model.entity.Orders;
import com.example.crossFit.repository.ItemRepo;
import com.example.crossFit.repository.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
            throw new ResourceNotFoundException("Заказов по данному номеру телефона: " + phoneNumber + " не найдено!");
        }
        List<Item> items = new ArrayList<>();
        for (Orders order : orders) {
            items.addAll(order.getItems());
            return items;
        }
        if (items.isEmpty()) {
            throw new ResourceNotFoundException("Заказанных товаров не найдено!");
        }
        return items;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public String createItem(Item item) {
        if (!itemRepo.findById(item.getId()).isPresent()) {
            itemRepo.save(item);
            return "Товар добавлен в магазин!";

        } else throw new ResourceAlreadyIsRegistered("Товар с таким id: " + item.getId() + " уже существует в базе!");
    }
}
