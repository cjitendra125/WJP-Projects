package com.productrest.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productrest.app.model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{
	List<Product> findByName(String name);
}
