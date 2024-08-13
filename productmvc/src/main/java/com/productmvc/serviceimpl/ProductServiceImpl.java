package com.productmvc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productmvc.daoimpl.ProductDAOImpl;
import com.productmvc.model.Product;
import com.productmvc.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAOImpl productDAOImpl;
	
	@Override
	public Integer save(Product product) {
		return productDAOImpl.save(product);
	}

	@Override
	public List<Product> getAll() {
		return productDAOImpl.getAll();
	}

	@Override
	public Product getById(int id) {
		return productDAOImpl.getById(id);
	}

	@Override
	public void remove(int id) {
		productDAOImpl.remove(id);
	}

}
