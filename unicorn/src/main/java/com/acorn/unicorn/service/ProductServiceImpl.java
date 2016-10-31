package com.acorn.unicorn.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.acorn.unicorn.dao.ProductDAO;
import com.acorn.unicorn.dto.ProductDTO;

@Service("productService")
public class ProductServiceImpl implements ProductService{
	Logger log = Logger.getLogger(this.getClass());
    
    @Resource(name="productDAO")
    private ProductDAO productDAO;
	
	@Override
	public List<ProductDTO> selectProductList() throws Exception {
		return productDAO.selectProductList();
	}
}
