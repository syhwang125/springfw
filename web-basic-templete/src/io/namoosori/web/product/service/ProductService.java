package io.namoosori.web.product.service;

import java.util.List;

import io.namoosori.web.product.domain.Product;

public interface ProductService {
	
	void regist(Product newProduct);
	
	Product find(String serialNumber);
	List<Product> findAll();
	
	void modify(Product newProduct);
	void remove(String serialNumber);

}
