package io.namoosori.web.product.store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import io.namoosori.web.product.domain.Product;

//Todo : Spring bean 등록을 위한 내용 추가 

@Repository
public class ProductStoreLogic implements ProductStore {
	
	private Map<String, Product> productMap;
	
	public ProductStoreLogic() {
		this.productMap = new HashMap<>();
	}

	@Override
	public void create(Product newProduct) {
		this.productMap.put(newProduct.getSerialNumber(), newProduct);
	}

	@Override
	public Product retrieve(String serialNumber) {
		return this.productMap.get(serialNumber);
	}

	@Override
	public List<Product> retrieveAll() {
		return this.productMap.values().stream().collect(Collectors.toList());
	}

	@Override
	public void update(Product newProduct) {
		this.productMap.put(newProduct.getSerialNumber(), newProduct);
	}

	@Override
	public void delete(String serialNumber) {
		this.productMap.remove(serialNumber);
	}
}
