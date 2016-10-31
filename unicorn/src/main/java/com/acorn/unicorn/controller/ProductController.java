package com.acorn.unicorn.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.acorn.unicorn.dto.ProductDTO;
import com.acorn.unicorn.service.ProductService;

@Controller
public class ProductController {

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "productService")
	private ProductService productService;
	
	@RequestMapping(value = "/views/ProductList.do")
	public ModelAndView ProductList(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("/views/productlist");
		
		List<ProductDTO> list = (List<ProductDTO>) productService.selectProductList();
		mv.addObject("list", list);
		
		return mv;
	}
}