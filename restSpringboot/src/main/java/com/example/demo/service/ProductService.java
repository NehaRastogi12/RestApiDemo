package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.Product;
import com.example.demo.dao.ProductDAO;
import com.example.demo.dao.ProductDAOImpl;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO prodDaoImpl;
	
	public List<Product> getProducts(int id){
		return prodDaoImpl.read(id);
		//return null;
	}
	
	public int createProduct(Product p) {
		return prodDaoImpl.createProduct(p);
	}
}
