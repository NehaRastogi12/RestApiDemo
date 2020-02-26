package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.Product;
import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.NoSuchProductExistException;

/**
 * Implementation class for ProductDAO
 * Uses JdbcTemplate for performing operations on Mysql DB
 * 
 * @author Neha
 *
 */
@Repository
public class ProductDAOImpl implements ProductDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static String readAllSQL ="select * from product_details";
	private static String readForId ="SELECT * FROM product_details where ProductId = ?";
	private static String createProdSQL ="INSERT INTO product.product_details(ProductId, Price,Benefits,location) VALUES(?,?,?,?)";
	private static String readMaxProductId = "select max(productId) from product_details";
	private static String deletebyProductId = "DELETE FROM PRODUCT.PRODUCT_DETAILS WHERE ProductId = ?";
	
	/**
	 * Creates a resource - product in database
	 * If the insert is failed due to any reason, InternalServerException is thrown
	 * 
	 * @throws InternalServerException
	 */
	@Override
	public Product createProduct(Product p) throws InternalServerException {
		
		List<Map<String,Object>> ls =jdbcTemplate.queryForList(readMaxProductId);
		int maxProdIdPresent = (int) ls.get(0).get("max(productId)");
		//Create the product with productId incrementing it by 1
		int sqlReturn = jdbcTemplate.update(createProdSQL, maxProdIdPresent+1,p.getPrice(),p.getBenefits(),p.getLocation());
		if(sqlReturn==1) {
			p.setProductId(maxProdIdPresent+1);
			return p;
		}
		else
			throw new InternalServerException("Exception while creating the product. Kindly report to neha@viable.com");
	}
	
	/**
	 * Method to delete product for a given product id
	 * If number of rows updated is 0, throws NoSuchProductExistException
	 * 
	 * @throws NoSuchProductExistException
	 */
	@Override
	public void deleteProduct(int pId) throws NoSuchProductExistException {
		int i=0;
		i=jdbcTemplate.update(deletebyProductId, pId);
		if(i==0) {
			throw new NoSuchProductExistException(pId + " : ProductId not found");
		}
	}
	
	/**
	 * Returns Product information.
	 * Return a specific product details if product id passed is not equal to 0 
	 */
	@Override
	public List<Product> read(int id) {

		if(id!=0) {
			try {
				Product proddetails=jdbcTemplate.queryForObject(readForId, new Object[]{id}, (rs, rowNum) ->
				new Product(
						rs.getInt("ProductId"),
						rs.getInt("Price"),
						rs.getString("Benefits"),
						rs.getString("location")
						));
				List<Product> ls=new ArrayList<>();
				ls.add(proddetails);
				return ls;
			}
			catch(EmptyResultDataAccessException e) {
				return null;
			}
		}
		else {
			//lambda expression used for Rowmapper class - second argument for query method of jdbcTemplae class
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

	}
	
	@Override
	public String updateProduct(Product p, String cName, Object o) {
		return null;
	}
	
}
