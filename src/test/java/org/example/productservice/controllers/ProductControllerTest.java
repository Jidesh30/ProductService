package org.example.productservice.controllers;

import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Product;
import org.example.productservice.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    ProductController productController;

    @MockitoBean("SelfProductService")
    ProductService productService;

    @Test
    void getProductByIdThrowsException() throws ProductNotFoundException {
        //Arrange
//        long productId = 1L;
//        Product product = new Product();
//        product.setId(1L);
//        product.setTitle("Test Product");
//
//        when(productService.getProductById(1L)).thenThrow(ProductNotFoundException.class);
//
//        //Act & Assert
//        Assertions.assertThrows(ProductNotFoundException.class, () -> productController.getProductById(productId));
//
//        //Assert
//        Assertions.assertEquals("Test Product", p.getTitle());

    }
}