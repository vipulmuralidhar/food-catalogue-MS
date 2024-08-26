package com.vipsfoodmart.foodcatalogue.controller;

import com.vipsfoodmart.foodcatalogue.dto.FoodCataloguePage;
import com.vipsfoodmart.foodcatalogue.dto.FoodItemDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vipsfoodmart.foodcatalogue.service.FoodCatalogueService;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/foodCatalogue")
public class FoodCatalogueController {

    private FoodCatalogueService foodCatalogueService;

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> saveFoodItem(@RequestBody FoodItemDTO foodItemDTO) {


        return new ResponseEntity<>(foodCatalogueService.saveFoodItem(foodItemDTO), HttpStatus.CREATED);
    }

    @GetMapping("/fetchfoodcatalogueByResurantId/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> getFoodCatalogueByRestaurantId(@PathVariable Integer restaurantId) {

        return new ResponseEntity<>(foodCatalogueService.fetchFoodCatalogueByRestaurantId(restaurantId), HttpStatus.OK);
    }

}
