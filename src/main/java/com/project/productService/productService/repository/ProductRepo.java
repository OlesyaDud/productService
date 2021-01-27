package com.project.productService.productService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.productService.productService.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {
	List<Product> findAll();
}
