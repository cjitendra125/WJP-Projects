package com.productmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.productmvc.model.Product;
import com.productmvc.serviceimpl.ProductServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ProductController {

	@Autowired
	private ProductServiceImpl productServiceImpl;
	
//	@RequestMapping(value="/save-product", method = RequestMethod.POST)
	@PostMapping("/save-product")
	public String save(@ModelAttribute Product product) {
		int result = productServiceImpl.save(product);
		System.out.println(result);
		return "redirect:/products-list";
	}
	
	@GetMapping("/products-list")
	public ModelAndView getAll() {
		List<Product> products = productServiceImpl.getAll();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("product-list");
		mv.addObject("list",products);
		return mv;
	}
	
	@GetMapping(value="/remove-product/{id}")
	public String remove(@PathVariable("id") int id) {
		productServiceImpl.remove(id);
		return "redirect:/products-list";
	}
}
