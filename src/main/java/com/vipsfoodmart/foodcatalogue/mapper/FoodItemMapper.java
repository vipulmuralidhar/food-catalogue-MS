package com.vipsfoodmart.foodcatalogue.mapper;

import com.vipsfoodmart.foodcatalogue.dto.FoodItemDTO;
import com.vipsfoodmart.foodcatalogue.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodItemMapper {

    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);

    FoodItem mapFoodItemDTOTOFoodItem(FoodItemDTO foodItemDTO);
    FoodItemDTO mapFoodItemTOFoodItemDto(FoodItem foodItem);


}
