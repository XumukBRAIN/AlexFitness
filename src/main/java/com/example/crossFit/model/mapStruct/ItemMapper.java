package com.example.crossFit.model.mapStruct;

import com.example.crossFit.model.dto.ItemDTO;
import com.example.crossFit.model.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "itemId", source = "id")
    @Mapping(target = "itemTitle", source = "title")
    @Mapping(target = "itemPrice", source = "price")
    ItemDTO toItemDTO(Item item);

    @Mapping(target = "id", source = "itemId")
    @Mapping(target = "title", source = "itemTitle")
    @Mapping(target = "price", source = "itemPrice")
    Item toItem(ItemDTO itemDTO);


}
