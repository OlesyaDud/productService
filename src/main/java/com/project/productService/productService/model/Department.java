package com.project.productService.productService.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@Embeddable
public class Department implements Serializable {

    @Column(name = "dep_id")
    long depId;

    @Column(name = "dep_name")
    String depName;

}
