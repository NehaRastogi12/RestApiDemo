package com.example.demo.dao;

import java.util.List;

import com.example.demo.Product;

public interface ProductDAO {

	public List<Product> read(int id);
	public int createProduct(Product p);
	public String updateProduct(Product p, String cName, Object o);
	public String deleteProduct(Product p);
	
}
