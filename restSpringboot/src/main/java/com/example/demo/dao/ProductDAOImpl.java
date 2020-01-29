package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.Product;

@Repository
public class ProductDAOImpl implements ProductDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static String readAllSQL ="select * from product_details";
	private static String readForId ="SELECT * FROM product_details where ProductId = ?";
	private static String createProdSQL ="INSERT INTO product.product_details(ProductId, Price,Benefits,location) VALUES(?,?,?,?)";
	
	@Override
	public int createProduct(Product p) {
		
		int status= jdbcTemplate.update(createProdSQL, p.getProductId(),p.getPrice(),p.getBenefits(),p.getLocation());
		 return status;
	}
	@Override
	public String deleteProduct(Product p) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Product> read(int id) {
		
		if(id!=0) {
			Product prod_details=jdbcTemplate.queryForObject(readForId, new Object[]{id}, (rs, rowNum) ->
            new Product(
                    rs.getInt("ProductId"),
                    rs.getInt("Price"),
                    rs.getString("Benefits"),
                    rs.getString("location")
            ));
			List<Product> ls=new ArrayList<Product>();
			ls.add(prod_details);
			return ls;
		}
		else {
			return jdbcTemplate.query(
	                readAllSQL,
	                (rs, rowNum) ->
	                new Product(
	                        rs.getInt("ProductId"),
	                        rs.getInt("Price"),
	                        rs.getString("Benefits"),
	                        rs.getString("location")
	                        )
	        );
		}
		
	}@Override
	public String updateProduct(Product p, String cName, Object o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
