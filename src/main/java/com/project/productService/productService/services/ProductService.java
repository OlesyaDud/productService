package com.project.productService.productService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.productService.productService.model.Product;
import com.project.productService.productService.repository.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;

	public ProductService(ProductRepo productRepo) {
		super();
		this.productRepo = productRepo;
	}
	
//	add - POST
	public Product saveProduct(Product p) {
		return productRepo.save(p);
	}
	
	public List<Product> saveProducts(List<Product> p) {
		return (List<Product>) productRepo.saveAll(p);
	}
	
//	GET
	public List<Product> getProducts() {
		return(List<Product>) productRepo.findAll();
	}
	
	public Product findByProdId(int prodId) {
		return productRepo.findById(prodId).orElse(null);
	}
	
// DELETE
	public String deleteProduct(int prodId) {
		productRepo.deleteById(prodId);
		return "product removed! " + prodId;
	}
	
//	Update
	public Product updateProduct(Product p) {
		Product existingProd = productRepo.findById(p.getproductId()).orElse(null);
		existingProd.setProdName(p.getProdName());
		return productRepo.save(existingProd);
		
	}
	
	
}
