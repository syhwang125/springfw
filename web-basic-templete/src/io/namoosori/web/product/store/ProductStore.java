package io.namoosori.web.product.store;

import java.util.List;

import io.namoosori.web.product.domain.Product;

public interface ProductStore {
	
	void create(Product newProduct);
	
	Product retrieve(String serialNumber);
	List<Product> retrieveAll();
	
	void update(Product newProduct);
	void delete(String serialNumber);
}
