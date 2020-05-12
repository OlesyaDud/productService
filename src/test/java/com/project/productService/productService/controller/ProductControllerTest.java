package com.project.productService.productService.controller;


import com.project.productService.productService.model.Product;
import com.project.productService.productService.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    ProductService prodService;

    @InjectMocks
    ProductController prodController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void addProduct() {
        Product p = new Product();
        when(prodService.saveProduct((Product) p)).thenReturn(new Product());
        Product result = prodController.addProduct((Product) p);
        verify(prodService).saveProduct((Product) p);

    }

    @Test
    void addProducts() {
        ArrayList<Product> listp = new ArrayList<Product>();
        when(prodService.saveProducts(listp)).thenReturn(new ArrayList<Product>());
        List<Product> result = prodController.addProducts(listp);
        verify(prodService).saveProducts(listp);
    }

    @Test
    void findAllProducts() {
        when(prodService.getProducts()).thenReturn(new ArrayList<Product>());
        List<Product> result = prodController.findAllProducts();
        verify(prodService).getProducts();
    }

    @Test
    void findProdById() {
        int id = 1;
        when(prodService.findByProdId(id)).thenReturn(new Product());
        Product result = prodController.findProdById(id);
        verify(prodService).findByProdId(id);

    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
        int id = 1;
        when(prodService.deleteProduct(id)).thenReturn(new String());
        String result = prodService.deleteProduct(id);
        verify(prodService).deleteProduct(id);
    }
}