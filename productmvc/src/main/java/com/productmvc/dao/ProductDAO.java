package com.productmvc.dao;

import java.util.List;

import com.productmvc.model.Product;

public interface ProductDAO {
	Integer save(Product product);
	List<Product> getAll();
	Product getById(int id);
	void remove(int id);
}
