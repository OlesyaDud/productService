package com.project.productService.productService.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.productService.productService.model.Product;
import com.project.productService.productService.services.ProductService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/products")
public class ProductController {

	
	@Autowired
	private ProductService pServ;

	public ProductController(ProductService productService) {
		super();
		this.pServ = productService;
	}
	

	
//	post
	
	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product p) {
		return pServ.saveProduct(p);
	}
	
	
	@PostMapping("/addProducts")
	public List<Product> addProducts(@RequestBody List<Product> p) {
		return pServ.saveProducts(p);
	}

//	get	
	@GetMapping
	public List<Product> findAllProducts() {
		return pServ.getProducts();
	}
	
	@GetMapping("/{prodId}")
	public Product findProdById(@PathVariable int prodId) {
		return pServ.findByProdId(prodId);
	}
	
//	put
	@PutMapping
	public Product updateProduct(@RequestBody Product p) {
		return pServ.updateProduct(p);
	}
	
//	delete
	@DeleteMapping("{prodId}")
	public String deleteProduct(@PathVariable int prodId) {
		return pServ.deleteProduct(prodId);
	}
	
	


}
