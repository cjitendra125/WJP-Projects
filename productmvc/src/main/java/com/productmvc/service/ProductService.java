package com.productmvc.service;

import java.util.List;

import com.productmvc.model.Product;

public interface ProductService {
	Integer save(Product product);
	List<Product> getAll();
	Product getById(int id);
	void remove(int id);
}
