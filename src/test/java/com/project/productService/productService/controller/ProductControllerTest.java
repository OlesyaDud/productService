package com.project.productService.productService.controller;


import com.project.productService.productService.exception.ProductNotFound;
import com.project.productService.productService.model.Product;
import com.project.productService.productService.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;




class ProductControllerTest {

    ProductService pS;
    ProductController pC;

    @BeforeEach
    public void setup(){
        pS = Mockito.mock(ProductService.class);
        pC = new ProductController(pS);
    }

    @Test
    public void getAll_CallsService() {
        //arrange
        List<Product> expected = Arrays.asList(
                new Product("1", "blouse", 222, "Women"),
                new Product("2", "red blouse", 111, "Men")
        );
        Mockito.when(pS.getProducts()).thenReturn(expected);

//        act
        List<Product>actual = pC.findAllProducts().getBody();

//        assert
        Mockito.verify(pS).getProducts();
        assertEquals(expected, actual);
    }

    @Test
    public void getId_CallsServiceOKResult() {
        Product expected = new Product("1", "blouse", 222, "Women");
        Mockito.when(pS.findByProdId("1")).thenReturn(expected);

        ResponseEntity<Product> response = pC.findByProdId("1");

        Mockito.verify(pS).findByProdId("1");
        assertEquals(expected,response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getById_returnsNotFoundIfNotPresent() {
        Mockito.when(pS.findByProdId("1")).thenThrow(new ProductNotFound());

//        act
        ResponseEntity<Product> response = pC.findByProdId("1");

//        assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void parch_shouldReturnUpdatedRecordandOK() {
        Product input = new Product(null, null, 222, "Women");
        Product expected = new Product("1", "blouse", 222, "Women");
        Mockito.when(pS.patchProduct("1", input)).thenReturn(expected);

        ResponseEntity<Product> response = pC.patchPerson("1", input);

        Mockito.verify(pS).patchProduct("1", input);
        assertEquals(expected, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void patch_Returns404WhenNotPresent() {
        Product input = new Product();
        Mockito.when(pS.patchProduct("1", input)).thenThrow(new ProductNotFound());

        ResponseEntity<Product> response = pC.patchPerson("1", input);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void put_shouldReturnSavedRecordAndCreated() {
// arrange
        Product expected = new Product("1", "blouse", 222, "Women");
        Mockito.when(pS.updateProduct("1", expected)).thenReturn(expected);
//act
        ResponseEntity<Product> response = pC.updateProduct("1", expected);
//assert
        Mockito.verify(pS).updateProduct("1", expected);
        assertEquals(expected, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void delete_shouldReturn404WhenNotFound() {
        Mockito.doThrow(ProductNotFound.class).when(pS).deleteProduct("1");

        ResponseEntity<Product> response = pC.deleteProduct("1");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }
}