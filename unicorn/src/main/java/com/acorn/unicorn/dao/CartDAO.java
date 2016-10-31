package com.acorn.unicorn.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.acorn.unicorn.bean.BoardBean;
import com.acorn.unicorn.bean.CartBean;
import com.acorn.unicorn.dto.CartDTO;

@Repository("cartDAO")
public class CartDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public List<CartDTO> selectCartList(String user_id) throws Exception{
		 return selectList("cart.selectCartList", user_id);
	}
//	
//	@SuppressWarnings("unchecked")
//	public BoardDTO selectBoardDetail(int idx) throws Exception{
//		return selectOne("board.selectBoardDetail", idx);
//	}

	public void insertCart1(CartBean bean) {
		insert("cart.insertCart", bean);
		
	}
	
	public CartDTO configProduct(CartBean bean) throws Exception{
		return selectOne("cart.configProduct", bean);
		
	}

	public void updateCart(CartBean bean) {
		update("cart.updateCart", bean);
		
	}
	
	public void deleteCart(CartBean bean) {
		delete("cart.deleteCart", bean);
		
	}
 
}
