package com.productrest.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.productrest.app.constants.ResponseKey;
import com.productrest.app.constants.ResponseMessage;
import com.productrest.app.model.Product;
import com.productrest.app.service.ProductService;


//@Controller
//@ResponseBody -->any method return some value then that value will be considered as value not view name
@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private MessageSource messageSource;
	
//	@GetMapping("/")
//	public List<Product> find All() {
//		return productService.findAll();
//	}
	
	
	@GetMapping("/greet/{name}")
	public ResponseEntity<?> greet(@PathVariable("name") String name,@RequestHeader(name="Accept-Language",required=false)Locale locale){
		String message=messageSource.getMessage("greeting.message",new Object[] {name}, locale);
		HashMap<String,String> responseBody=new HashMap<>();
		responseBody.put(ResponseKey.MESSAGE, message);
		return new ResponseEntity<>(responseBody,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<Object> findAll(@RequestHeader(name="Accept-Language",required=false)Locale locale) {
		try {
			List<Product> productList= productService.findAll();
			if(productList.isEmpty()) {
				HashMap<String,String> data=new HashMap<>();
				data.put(ResponseKey.MESSAGE,messageSource.getMessage("error.productnotfound", null, locale));
				return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
			}else {
				HashMap<String,Object> data=new HashMap<>();
				data.put(ResponseKey.COUNT,productService.countAll());
				data.put(ResponseKey.PRODUCTS, productList);
				return new ResponseEntity<>(data,HttpStatus.OK);
			}
		}catch(Exception e) {
			HashMap<String,String> data=new HashMap<>();
			data.put(ResponseKey.MESSAGE,messageSource.getMessage("error.exception", null, locale));
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
//	@PostMapping("/")
//	public Product save(@RequestBody Product product) {
//		Product savedProduct=productService.save(product);
//		return savedProduct;
//	}
	
	@PostMapping("/")
	public ResponseEntity<Object> save(@RequestBody Product product) {
		try {
			Product savedProduct=productService.save(product);
			return new ResponseEntity<>(savedProduct,HttpStatus.CREATED);
		}catch(Exception e) {
			HashMap<String,String> data=new HashMap<>();
			data.put(ResponseKey.MESSAGE, ResponseMessage.SOMETHING_WENT_WRONG);
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable int id){
		HashMap<Object,Object> data=new HashMap<>();
		try {
			Optional<Product> productOptional=productService.findById(id);
			if(productOptional.isPresent()) {
				Product product=productOptional.get();
				return new ResponseEntity<>(product,HttpStatus.OK);
			}
			else {
				data.put(ResponseKey.MESSAGE, ResponseMessage.NO_PRODUCT_FOUND_BY_ID);
				return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			data.put(ResponseKey.MESSAGE,ResponseMessage.SOMETHING_WENT_WRONG );
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> remove(@PathVariable int id){
		HashMap<Object,Object> data=new HashMap<>();
		try {
			Optional<Product> productOptional=productService.findById(id);
			if(productOptional.isPresent()) {
				productService.remove(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				data.put(ResponseKey.MESSAGE, ResponseMessage.NO_PRODUCT_FOUND_BY_ID);
				return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			data.put(ResponseKey.MESSAGE, ResponseMessage.SOMETHING_WENT_WRONG);
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/by-name/{name}")
	public ResponseEntity<Object> findByName(@PathVariable String name){
		HashMap<Object,Object> data=new HashMap<>();
		try {
			List<Product> productList=productService.findByName(name);
			if(productList.isEmpty()) {
				data.put(ResponseKey.MESSAGE, ResponseMessage.NO_PRODUCT_FOUND_BY_NAME);
				return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<>(productList,HttpStatus.OK);
			}
			
		}catch(Exception e) {
			data.put(ResponseKey.MESSAGE, ResponseMessage.SOMETHING_WENT_WRONG);
			return new ResponseEntity<>(data,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
