package com.example.crossFit.service;

import com.example.crossFit.exceptions.EntityAlreadyIsRegisteredException;
import com.example.crossFit.model.entity.Item;
import com.example.crossFit.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepo itemRepo;

    @Autowired
    public ItemService(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
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
