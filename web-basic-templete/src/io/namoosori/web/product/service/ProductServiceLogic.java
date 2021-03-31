package io.namoosori.web.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.namoosori.web.product.domain.Product;
import io.namoosori.web.product.store.ProductStore;

//Todo : Spring bean 등록을 위한 내용 추가 
@Service
public class ProductServiceLogic implements ProductService{
	
	@Autowired
	private ProductStore productStore;
	
//	Todo : ProductStore 필드에 대한 Spring DI 적용을 위한 생성자  추가 

	@Override
	public void regist(Product newProduct) {
		String serialNumber = this.generateSerialNumber();
		newProduct.setSerialNumber(serialNumber);
		System.out.println("newProduct.getName" + newProduct.getName());
		this.productStore.create(newProduct);
	}
	
	private String generateSerialNumber() {
		List<Product> allProducts = this.productStore.retrieveAll();
		if(allProducts.isEmpty()) {
			return "0";
		}
		
		int max = Integer.MIN_VALUE;
		for(Product product : allProducts) {
			int sn = Integer.parseInt(product.getSerialNumber());
			if(max < sn) {
				max = sn;
			}
		}
		return String.valueOf(max + 1);
	}

	@Override
	public Product find(String serialNumber) {
		return this.productStore.retrieve(serialNumber);
	}

	@Override
	public List<Product> findAll() {
		return this.productStore.retrieveAll();
	}

	@Override
	public void modify(Product newProduct) {
		this.productStore.update(newProduct);
	}

	@Override
	public void remove(String serialNumber) {
		this.productStore.delete(serialNumber);
	}
}
