package com.project.productService.productService.controller;

import java.util.List;


import com.project.productService.productService.exception.ProductNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<List<Product>> findAllProducts() {
		return  new ResponseEntity<>(pServ.getProducts(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product>findByProdId(@PathVariable("id") String id) {
		try {
			return new ResponseEntity<>(pServ.findByProdId(id), HttpStatus.OK);
		} catch ( ProductNotFound e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

//	put
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody Product p) {
		return new ResponseEntity<>(pServ.updateProduct(id, p), HttpStatus.CREATED);
	}

	//patch
	@PatchMapping("/{id}")
	public ResponseEntity<Product> patchPerson(@PathVariable("id") String id, @RequestBody Product p) {
		try {
			return new ResponseEntity<>(pServ.patchProduct(id, p), HttpStatus.OK);
		} catch (ProductNotFound e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	
//	delete
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") String id) {
		try {
			pServ.deleteProduct(id);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}catch (ProductNotFound e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
