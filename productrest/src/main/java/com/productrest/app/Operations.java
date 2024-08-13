package com.productrest.app;

import org.springframework.stereotype.Component;

@Component
public class Operations {
	public int add(int a,int b) {
		return a+b;
	}
	public int multiply(int a,int b) {
		return a*b;
	}
	
	public boolean compare(int x,int y) {
		return x==y;
	}
}
