package com.example.crossFit.service;

import com.example.crossFit.exceptions.EntityAlreadyIsRegisteredException;
import com.example.crossFit.exceptions.EntityNotFoundException;
import com.example.crossFit.model.entity.Item;
import com.example.crossFit.model.entity.Orders;
import com.example.crossFit.repository.ItemRepo;
import com.example.crossFit.repository.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Transactional(readOnly = true)
    public List<Item> showMyItems(String phoneNumber) {
        List<Orders> orders = ordersRepo.findByPhoneNumber(phoneNumber);
        if (orders.isEmpty()) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Заказов по данному номеру телефона не найдено");
        }
        List<Item> items = new ArrayList<>();
        for (Orders order : orders) {
            items.addAll(order.getItems());
            return items;
        }
        if (items.isEmpty()) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Заказанных товаров не найдено");
        }
        return items;
    }

    @Transactional
    public void createItem(Item item) {
        Optional<Item> i = itemRepo.findById(item.getId());
        if (!i.isPresent()) {
            itemRepo.save(item);
        } else throw new EntityAlreadyIsRegisteredException(HttpStatus.BAD_REQUEST,
                "Товар с таким ID уже есть в базе");
    }
}
