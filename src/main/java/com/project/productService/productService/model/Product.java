package com.project.productService.productService.model;

import javax.persistence.*;



@Entity
public class Product {
	
	@Id
	int productId;
	String prodName;
	int depId;
	String depName;
	
	public int getproductId() {
		return productId;
	}
	public void setproductId(int productId) {
		this.productId = productId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getDepId() {
		return depId;
	}
	public void setDepId(int depId) {
		this.depId = depId;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int productId, String prodName, int depId, String depName) {
		super();
		this.productId = productId;
		this.prodName = prodName;
		this.depId = depId;
		this.depName = depName;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", prodName=" + prodName + ", depId=" + depId + ", depName="
				+ depName + "]";
	}
	

}
