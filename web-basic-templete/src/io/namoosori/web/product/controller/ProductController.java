package io.namoosori.web.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import io.namoosori.web.product.domain.Product;
import io.namoosori.web.product.service.ProductService;

// http://localhost:8080/namooquiz-web-basic/list
// TODO : Spring bean 등록을 위한 내용 추가 
@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

//	TODO : ProductService 필드에 대한 Spring DI 적용을 위한 생성자  추가 

	@GetMapping("/")
	public String home() {
		return "redirect:list";
	}

//	TODO : 메소드 구현 
	@RequestMapping("/list")
	public ModelAndView showProductList() {
//		ModelAndView modelAndView = new ModelAndView("/views/list.jsp");
		List<Product> products = productService.findAll();
		return new ModelAndView("/views/list.jsp", "products", products);
	}

//	TODO : 메소드 구현 
	@RequestMapping("/detail")
	public ModelAndView showProductDetail(@RequestParam String serialNumber) {
		Product product = productService.find(serialNumber);
		return new ModelAndView("/views/detail.jsp", "product", product);
	}

//	TODO : 메소드 구현 
	@RequestMapping("/regist")
	public String showRegistForm() {
		return "redirect:/views/registerForm.jsp";
	}

//	TODO : 메소드 구현 
	@PostMapping("/regist")  
	public String registProduct(Product newProduct) {
		productService.regist(newProduct);
		return "redirect:/list";
	}

//	TODO : 메소드 구현 
	@RequestMapping("/modify")
	public ModelAndView showModifyForm(@RequestParam String serialNumber) {
		Product product = productService.find(serialNumber);
		return new ModelAndView("/views/modifyForm.jsp", "product", product);
	}

//	TODO : 메소드 구현 
	@PostMapping("/modify")
	public String modifyProduct(Product newProduct) {
		productService.modify(newProduct);
		return "redirect:/list";
	}

//	TODO : 메소드 구현 
	@RequestMapping("/remove")
	public String removeProduct(@RequestParam String serialNumber) {
		productService.remove(serialNumber);
		return "redirect:/list";
	}

}
