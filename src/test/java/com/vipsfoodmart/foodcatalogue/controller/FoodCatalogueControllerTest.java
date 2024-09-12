package com.vipsfoodmart.foodcatalogue.controller;

import com.vipsfoodmart.foodcatalogue.dto.FoodCataloguePage;
import com.vipsfoodmart.foodcatalogue.dto.FoodItemDTO;
import com.vipsfoodmart.foodcatalogue.service.FoodCatalogueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

public class FoodCatalogueControllerTest {


    @InjectMocks
    FoodCatalogueController foodCatalogueController;

    @Mock
    FoodCatalogueService mockFoodCatalogueService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);


    }

    @Test
    public void testSaveFoodItem() {

        //Arrange
        FoodItemDTO mockFoodItemDTO = new FoodItemDTO(1, "food 1", "Spicy", true, 20, 2, 1);
        when(mockFoodCatalogueService.saveFoodItem(mockFoodItemDTO)).thenReturn(mockFoodItemDTO);

        //Act
        ResponseEntity<FoodItemDTO> response = foodCatalogueController.saveFoodItem(mockFoodItemDTO);

        //Assert
        verify(mockFoodCatalogueService, times(1)).saveFoodItem(mockFoodItemDTO);
        assert response.getStatusCode() == HttpStatus.CREATED;
        assert response.getBody() == mockFoodItemDTO;


    }

    @Test
    public void testGetFoodCatalogueByRestaurantId() {


        //Arrange
        Integer mockRestaurantId = 1;
        FoodItemDTO mockFoodItemDTO = new FoodItemDTO(1, "food 1", "Spicy", true, 20, 2, 1);

        //Arrange
        FoodCataloguePage mockFoodCataloguePage = new FoodCataloguePage();
        when(mockFoodCatalogueService.fetchFoodCatalogueByRestaurantId(mockRestaurantId)).thenReturn(mockFoodCataloguePage);

        //Act
        ResponseEntity<FoodCataloguePage> response = foodCatalogueController.getFoodCatalogueByRestaurantId(mockRestaurantId);

        //Assert
        verify(mockFoodCatalogueService, times(1)).fetchFoodCatalogueByRestaurantId(mockRestaurantId);
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == mockFoodCataloguePage;


    }


}
