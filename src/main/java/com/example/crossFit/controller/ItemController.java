package com.example.crossFit.controller;

import com.example.crossFit.config.SwaggerConfig;
import com.example.crossFit.model.dto.ItemDTO;
import com.example.crossFit.model.entity.Item;
import com.example.crossFit.model.mapStruct.ItemMapper;
import com.example.crossFit.response.SuccessResponse;
import com.example.crossFit.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(SwaggerConfig.ITEM_TAG)
@RestController
@RequestMapping("/items")
@CrossOrigin
public class ItemController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemController(ItemService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }


    @ApiOperation("Метод для добавления товара на витрину интернет-магазина")
    @PostMapping("/createItem")
    public ResponseEntity<SuccessResponse> createItem(@RequestBody ItemDTO itemDTO) {
        Item item = itemMapper.toItem(itemDTO);
        return ResponseEntity.ok(itemService.createItem(item));
    }


    @ApiOperation("Метод для просмотра всех заказов клиента по номеру телефона")
    @GetMapping("/showMyItems")
    public ResponseEntity<List<ItemDTO>> showMyItems(@RequestParam String phoneNumber) {
        List<Item> items = itemService.showMyItems(phoneNumber);
        return ResponseEntity.ok(itemMapper.toItemListDTO(items));
    }
}
