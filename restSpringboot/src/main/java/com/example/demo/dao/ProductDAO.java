package com.example.demo.dao;

import java.util.List;

import com.example.demo.Product;
import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.NoSuchProductExistException;

public interface ProductDAO {

	public List<Product> read(int id);
	public Product createProduct(Product p) throws InternalServerException;
	public String updateProduct(Product p, String cName, Object o);
	public void deleteProduct(int pId) throws NoSuchProductExistException;
	
}
