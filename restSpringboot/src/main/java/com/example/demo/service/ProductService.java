package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Product;
import com.example.demo.dao.ProductDAO;
import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.NoSuchProductExistException;

@Service
public class ProductService {
	
	
	private ProductDAO prodDaoImpl;
	
	public ProductService(){
		
	}
	@Autowired
	public ProductService(ProductDAO prodDAOImpl) {
		this.prodDaoImpl=prodDAOImpl;
	}
	
	public List<Product> getProducts(int id){
			return prodDaoImpl.read(id);
	}
	
	public Product createProduct(Product p) throws InternalServerException {
		return prodDaoImpl.createProduct(p);
	}
	
	public void deleteProduct(int id) throws NoSuchProductExistException {
		prodDaoImpl.deleteProduct(id);
	}
}
