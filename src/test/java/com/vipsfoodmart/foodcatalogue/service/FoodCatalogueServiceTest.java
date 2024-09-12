package com.vipsfoodmart.foodcatalogue.service;

import com.vipsfoodmart.foodcatalogue.dto.FoodCataloguePage;
import com.vipsfoodmart.foodcatalogue.dto.FoodItemDTO;
import com.vipsfoodmart.foodcatalogue.dto.Restaurant;
import com.vipsfoodmart.foodcatalogue.entity.FoodItem;
import com.vipsfoodmart.foodcatalogue.mapper.FoodItemMapper;
import com.vipsfoodmart.foodcatalogue.repo.FoodItemRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FoodCatalogueServiceTest {


    @InjectMocks
    private FoodCatalogueService testFoodCatalogueService;

    @Mock
    private FoodItemRepo mockFoodItemRepo;


    @Mock
    private RestTemplate mockRestTemplate;



    @BeforeEach
    public void setup(){

        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testSaveFoodItem(){


        //Arrange
        FoodItemDTO mockFoodItemDTO = new FoodItemDTO();
        FoodItem mockFoodItem = new FoodItem();


        when(mockFoodItemRepo.save(any(FoodItem.class))).thenReturn(mockFoodItem);


        //Act
        FoodItemDTO result = testFoodCatalogueService.saveFoodItem(mockFoodItemDTO);

        //Assert
        verify(mockFoodItemRepo,times(1)).save(any(FoodItem.class));
        assertEquals(result, FoodItemMapper.INSTANCE.mapFoodItemTOFoodItemDto(mockFoodItem));


    }



    @Test
    public void testFetchFoodCatalogueByRestaurantId(){
        //Arrange
        Integer mockRestaurantId =123;

        List<FoodItem> mockFoodItemList = Arrays.asList(new FoodItem());
        Restaurant mockRestaurant = new Restaurant(123,"rest 1","add 1241","city 1","desc 1");



        when(mockFoodItemRepo.findByRestaurantId(mockRestaurantId)).thenReturn(mockFoodItemList);
        when(mockRestTemplate.getForObject(anyString(),eq(Restaurant.class))).thenReturn(mockRestaurant);

        //Act


        FoodCataloguePage result = testFoodCatalogueService.fetchFoodCatalogueByRestaurantId(mockRestaurantId);


        //Verify

        verify(mockFoodItemRepo,times(1)).findByRestaurantId(mockRestaurantId);
        verify(mockRestTemplate,times(1)).getForObject(anyString(),eq(Restaurant.class));

        assertEquals(result.getFoodItemsList(),mockFoodItemList);
        assertEquals(result.getRestaurant(),mockRestaurant);



    }



}
