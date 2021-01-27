package com.project.productService.productService.model;

import lombok.*;

import javax.persistence.*;



@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class Product {
	
	@Id
	String id;
	
	@Column(name = "prod_name")
	String prodName;

//	@Column(name = "dep_id")
//	long depId;
//
//	@Column(name = "dep_name")
//	String depName;
}
