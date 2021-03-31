package io.namoosori.web.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import io.namoosori.web.product.domain.Product;
import io.namoosori.web.product.service.ProductService;

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
//		ModelAndView model = new ModelAndView("list"); 
		List<Product> products = productService.findAll();
//		model.addObject("list", products);
//		return model;
		return new ModelAndView("list", "Product", products);
	}

//	TODO : 메소드 구현 
	@RequestMapping("/detail")
	public ModelAndView showProductDetail(@RequestParam String serialNumber) {
//		ModelAndView model = new ModelAndView("detail");
		Product product = productService.find(serialNumber);
//	    model.addObject("detail", product);
	    return new ModelAndView("detail","Product", product); //viewname, modelname, modelobject
	}

//	TODO : 메소드 구현 
	@RequestMapping("/registerForm")
	public String showRegistForm() {
		return null;
	}

//	TODO : 메소드 구현 
	@RequestMapping("/regist")
	public String registProduct(Product newProduct) {
		productService.regist(newProduct);
		return "redirec:/list";
	}

//	TODO : 메소드 구현 
	@RequestMapping("/modifyForm")
	public ModelAndView showModifyForm(@RequestParam String serialNumber) {
		ModelAndView model = new ModelAndView();
//		model.addObject("id",30);
//		model.setViewName("board/reqply");
		return model;
	}

//	TODO : 메소드 구현 
	@RequestMapping("/getUserList")
	public String modifyProduct(Product newProduct) {
		productService.modify(newProduct);
		return "redirect:/list";
	}

//	TODO : 메소드 구현 
	@RequestMapping("/remove/{serialNumber}")
	public String removeProduct(@RequestParam String serialNumber) {
		productService.remove(serialNumber);
		return "redirect:/list";
	}

}
