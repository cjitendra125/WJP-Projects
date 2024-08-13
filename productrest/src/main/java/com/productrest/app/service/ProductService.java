package com.productrest.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productrest.app.model.Product;
import com.productrest.app.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	public Optional<Product> findById(int id) {
		Optional<Product> op= productRepository.findById(id);
		return op;
	}
	
	public void remove(int id) {
		productRepository.deleteById(id);
	}
	
	public long countAll() {
		return productRepository.count();
	}
	
	public List<Product> findByName(String name){
		return productRepository.findByName(name);
	}
}
