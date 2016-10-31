package com.acorn.unicorn.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.acorn.unicorn.bean.CartBean;
import com.acorn.unicorn.dao.CartDAO;
import com.acorn.unicorn.dto.CartDTO;

@Service("cartService")
public class CartServiceImpl implements CartService{
	Logger log = Logger.getLogger(this.getClass());
    
    @Resource(name="cartDAO")
    private CartDAO cartDAO;
	
    @Override
    public void insertCart1(CartBean bean) throws Exception {
    	cartDAO.insertCart1(bean);	
    }
    
    @Override
    public List<CartDTO> selectCartList(String user_id) throws Exception {
    	return cartDAO.selectCartList(user_id);
    }
    
    @Override
    public CartDTO configProduct(CartBean bean) throws Exception{
    	return cartDAO.configProduct(bean);
    }
    
    @Override
    public void updateCart(CartBean bean) throws Exception {
    	cartDAO.updateCart(bean);
    }
    @Override
    public void deleteCart(CartBean bean) throws Exception {
    	cartDAO.deleteCart(bean);
    	
    }
}
