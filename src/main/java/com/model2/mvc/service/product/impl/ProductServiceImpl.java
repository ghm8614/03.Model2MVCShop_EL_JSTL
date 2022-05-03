package com.model2.mvc.service.product.impl;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;


public class ProductServiceImpl implements ProductService {

	private ProductDAO productDAO;
	
	//C
	public ProductServiceImpl() {
		productDAO = new ProductDAO();		
	}
	
	@Override
	public Product findProduct(int prodNo) throws Exception {
		return productDAO.findProduct(prodNo);
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception{
		return productDAO.getProductList(search);
	}

	@Override
	public void insertProduct(Product product) throws Exception {
		productDAO.insertProduct(product);
	}

	@Override
	public void updateProduct(Product product) throws Exception{
		productDAO.updateProduct(product);
	}

}
