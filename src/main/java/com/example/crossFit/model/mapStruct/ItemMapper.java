package com.example.crossFit.model.mapStruct;

import com.example.crossFit.model.dto.ItemDTO;
import com.example.crossFit.model.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "itemId", source = "id")
    @Mapping(target = "itemTitle", source = "title")
    @Mapping(target = "itemPrice", source = "price")
    ItemDTO toItemDTO(Item item);

    List<ItemDTO> toItemListDTO(List<Item> item);

    @Mapping(target = "id", source = "itemId")
    @Mapping(target = "title", source = "itemTitle")
    @Mapping(target = "price", source = "itemPrice")
    Item toItem(ItemDTO itemDTO);

    List<Item> toItemList(List<ItemDTO> itemDTO);


}
