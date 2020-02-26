package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.NoSuchProductExistException;
import com.example.demo.service.ProductService;

@RestController
public class ProductController {
	
	private ProductService prodService;
	
	@Autowired
	public ProductController(ProductService prodService) {
		this.prodService = prodService;
	}
	
	
	@GetMapping(value="product/get", produces = {"application/xml", "application/json"})
	public List<Product> getProducts(@RequestParam(value="id",defaultValue = "0")int id) throws NoSuchProductExistException{
		List<Product> ls=prodService.getProducts(id);
		if(ls==null) {
			throw new NoSuchProductExistException("Product Not found in the database");
		}
		else
			return ls;
	}
	
	@PostMapping(path = "product/create", consumes = "application/json", produces = "application/json")
	public Product createProduct(@RequestBody Product p) throws InternalServerException {
		return prodService.createProduct(p);
	}
	
	@DeleteMapping("product/remove/{id}")
	public HttpStatus deleteProduct(@PathVariable int id) throws NoSuchProductExistException {
		prodService.deleteProduct(id);
		return HttpStatus.OK;
	}
}
