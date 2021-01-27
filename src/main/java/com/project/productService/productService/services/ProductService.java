package com.project.productService.productService.services;

import java.util.List;
import java.util.Optional;

import com.project.productService.productService.exception.ProductNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.productService.productService.model.Product;
import com.project.productService.productService.repository.ProductRepo;

@Slf4j
@Service
public class ProductService {

	private ProductRepo productRepo;

	@Autowired
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
		return productRepo.findAll();
	}

	public Product findByProdId(String id) {
		Optional<Product> optional = productRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new ProductNotFound();
	}

	// DELETE
	public void deleteProduct(String id) {
		Optional<Product> optional = productRepo.findById(id);
		if (optional.equals(null)) {
			throw new ProductNotFound();
		} productRepo.delete(optional.get());
	}

	//	Update
//	public Product updateProduct(String id, Product p) {
//		Product existingProd = productRepo.findById(p.getproductId()).orElse(null);
//		existingProd.setProdName(p.getProdName());
//		return productRepo.save(existingProd);
//	}
	public Product updateProduct(String id, Product p) {
		p.setId(id);
		return productRepo.save(p);
	}

	public Product patchProduct(String id, Product p) {
		Optional<Product> optional = productRepo.findById(id);
		if (optional.isPresent()) {
			Product productUpdate = optional.get();

			if (p.getDepName() != null) {
				productUpdate.setDepName(p.getDepName());
			}

			if (p.getProdName() != null) {
				productUpdate.setProdName(p.getProdName());
			}

			if (p.getDepId() != 0) {
				productUpdate.setDepId(p.getDepId());
			}

			return productRepo.save(productUpdate);
		} else {
			throw new ProductNotFound();
		}
	}

//	public Product patchProduct(String id, Product p) {
//		Optional<Product> optional = productRepo.findById(id);
//		if (optional.equals(null)) {
//			throw new ProductNotFound();
//		}
//		Product productUpdate = optional.get();
//
//		if (p.getDepName() != null) {
//			productUpdate.setDepName(p.getDepName());
//		}
//
//		if (p.getProdName() != null) {
//			productUpdate.setProdName(p.getProdName());
//		}
//
//		if (p.getDepId() != 0) {
//			productUpdate.setDepId(p.getDepId());
//		}
//
//		return productRepo.save(productUpdate);
//	}
}


