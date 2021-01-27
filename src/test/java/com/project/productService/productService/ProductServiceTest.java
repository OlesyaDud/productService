package com.project.productService.productService;

import static org.junit.jupiter.api.Assertions.*;

import com.project.productService.productService.exception.ProductNotFound;
import com.project.productService.productService.model.Product;
import com.project.productService.productService.repository.ProductRepo;
import com.project.productService.productService.services.ProductService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



class ProductServiceTest{

    ProductService pS;
    ProductRepo pR;

    @BeforeEach
    public void setup(){
        pR = Mockito.mock(ProductRepo.class);
        pS = new ProductService(pR);
    }

    @Test
    public void getAll_ShouldCallRepoFindAll() {
        //arrange
        List<Product> expected = Arrays.asList(
              new Product("1", "blouse", 222, "Women"),
                new Product("2", "red blouse", 111, "Men")
        );
        when(pR.findAll()).thenReturn(expected);

//        act
        pS.getProducts();

//        assert
        Mockito.verify(pR).findAll();
    }

    //    throws exception when product does not exist
    @Test
    public void getProductById_WithExceptionIfNoProductExists() {
        Assertions.assertThrows(ProductNotFound.class, () -> {
            pS.findByProdId("10");
        });
    }

    @Test
    public void getProductById_ReturnsProduct() {
//        arrange
        Product expected = new Product("1", "blouse", 222, "Women");
        when(pR.findById("1")).thenReturn(Optional.of(expected));

//        act
        Product actual = pS.findByProdId("1");

//        assert
        assertEquals(expected, actual);
        verify(pR).findById("1");
    }

    @Test
    public void putProduct() {
//        arrange
        Product input = new Product(null, "blouse", 222, "Women");
        Product expected = new Product("1", "blouse", 222, "Women");

//        act
        pS.updateProduct("1", input);

//        assert
        verify(pR).save(expected);
    }

    @Test
    public void putProduct_returnsModifiedRecord() {
//        arrange
        Product input = new Product(null, "blouse", 222, "Women");
        Product expected = new Product("1", "blouse", 222, "Women");
        when(pR.save(expected)).thenReturn(expected);

//        act
        Product actual = pS.updateProduct("1", input);

//        assert
        assertEquals(expected, actual);
    }

//    @Test
//    public void patch_updatesProdName() {
//        Product input = new Product("1", "b", 222, "Women");
//        Product fromRepo = new Product("1", "blouse", 222, "Women");
//        Product expected = new Product("1", "red blouse", 222, "Women");
//        when(pR.findById("1")).thenReturn(Optional.of(fromRepo));
//        when(pR.save(expected)).thenReturn(expected);
//
////        act
//        Product actual = pS.patchProduct("1", input);
//
////        assert
//        assertEquals(expected, actual);
//    }

//    @Test
//    public void patch_updateDepName() {
//        Product input = new Product("1", null, 222, "Children");
//        Product fromRepo = new Product("1", "blouse", 222, "Women");
//        Product expected = new Product("1", "red blouse", 222, "Children");
//        when(pR.findById("1")).thenReturn(Optional.of(fromRepo));
//        when(pR.save(expected)).thenReturn(expected);
//
//        Product actual = pS.patchProduct("1", input);
//
//        assertEquals(expected, actual);
//    }

    @Test
    public void patch_updatesAllFields() {
        Product input = new Product(null, "red blouse", 222, "Children");
        Product fromRepo = new Product("1", "blouse", 222, "Women");
        Product expected = new Product("1", "red blouse", 222, "Children");
        when(pR.findById("1")).thenReturn(Optional.of(fromRepo));
        when(pR.save(expected)).thenReturn(expected);

        Product actual = pS.patchProduct("1", input);

        assertEquals(expected, actual);
    }

    @Test
    public void delete_callsRepoDeletsRecord() {
        Product fromRepo = new Product("1", "red blouse", 222, "Children");
        when(pR.findById("1")).thenReturn(Optional.of(fromRepo));

        pS.deleteProduct("1");

        verify(pR).delete(fromRepo);
    }

//    @Test
//    public void delete_throwsErrorIfNotFound() {
//        when(pR.findById("1")).thenReturn(Optional.empty());
//
//       Assertions.assertThrows(ProductNotFound.class, () -> {
//           pS.deleteProduct("1");
//       });
//    }
}
