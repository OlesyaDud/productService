package com.project.productService.productService.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.productService.productService.model.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product, Integer>{
	List<Product> findAll();
}
