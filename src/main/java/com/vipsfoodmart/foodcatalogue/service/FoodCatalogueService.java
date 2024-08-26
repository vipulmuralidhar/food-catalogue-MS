package com.vipsfoodmart.foodcatalogue.service;

import com.vipsfoodmart.foodcatalogue.dto.FoodCataloguePage;
import com.vipsfoodmart.foodcatalogue.dto.FoodItemDTO;
import com.vipsfoodmart.foodcatalogue.dto.Restaurant;
import com.vipsfoodmart.foodcatalogue.entity.FoodItem;
import lombok.AllArgsConstructor;
import com.vipsfoodmart.foodcatalogue.mapper.FoodItemMapper;
import org.springframework.stereotype.Service;
import com.vipsfoodmart.foodcatalogue.repo.FoodItemRepo;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
@Service
public class FoodCatalogueService {

    private FoodItemRepo foodItemRepo;

    private RestTemplate restTemplate;


    public FoodItemDTO saveFoodItem(FoodItemDTO foodItemDTO) {

        FoodItem foodItem = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDTOTOFoodItem(foodItemDTO));

        return FoodItemMapper.INSTANCE.mapFoodItemTOFoodItemDto(foodItem);
    }

    public FoodCataloguePage fetchFoodCatalogueByRestaurantId(Integer restaurantId) {

        //fectFoodItemsById
        List<FoodItem> foodItem = fetchFoodItemsById(restaurantId);
        //fetchRestaurentDetailsById
        Restaurant restaurant = fetchRestaurentDetailsById(restaurantId);
        //CreateFooCataloguePage
        FoodCataloguePage foodCataloguePage = CreateFooCataloguePage(foodItem, restaurant);

        return foodCataloguePage;
    }

    private FoodCataloguePage CreateFooCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemsList(foodItemList);
        foodCataloguePage.setRestaurant(restaurant);
        return foodCataloguePage;
    }

    private Restaurant fetchRestaurentDetailsById(Integer restaurantId) {


        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/"+ restaurantId, Restaurant.class);
    }


    private List<FoodItem> fetchFoodItemsById(Integer restaurantId) {

        return foodItemRepo.findByRestaurantId(restaurantId);
    }
}
