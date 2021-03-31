package io.namoosori.web.product.domain;

public class Product {
	
	private String serialNumber;
	private String name;
	private String vendor;
	private String introduce;
	private int price;
	
	public Product() {}

	public Product(String name, String vendor, String introduce, int price) {
		this.name = name;
		this.vendor = vendor;
		this.introduce = introduce;
		this.price = price;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
}
