package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService prodService;
	
	/*
	 * @GetMapping(value="users") public List<User> getUsers(){ List<User> userList
	 * = new ArrayList<User>(); User user1 = new User("Neha", "1"); User user2 = new
	 * User("Deepak", "2"); userList.add(user1); userList.add(user2); return
	 * userList; }
	 */
	
	@GetMapping(value="product/get")
	public List<Product> getProducts(@RequestParam(value="id",defaultValue = "0")int id){
		
		//List<Product> returnList=new ArrayList<Product>();
		return prodService.getProducts(id);
		//return null;
	
	}
	
	@PostMapping(path = "product/create", consumes = "application/json", produces = "application/json")
	public HttpStatus createProduct(@RequestBody Product p) {
		int sqlReturn =prodService.createProduct(p);
		if(sqlReturn==1) {
			return HttpStatus.CREATED;
		}
		else
			return HttpStatus.INTERNAL_SERVER_ERROR;
	}
}
