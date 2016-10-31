package com.acorn.unicorn.service;

import java.util.List;

import com.acorn.unicorn.bean.BoardBean;
import com.acorn.unicorn.bean.CartBean;
import com.acorn.unicorn.dto.BoardDTO;
import com.acorn.unicorn.dto.CartDTO;
import com.acorn.unicorn.dto.ProductDTO;

public interface CartService {

	List<CartDTO> selectCartList(String user_id) throws Exception;

	void insertCart1(CartBean bean) throws Exception;
//
//	BoardDTO selectBoardDetail(int idx) throws Exception;

	CartDTO configProduct(CartBean bean) throws Exception;

	void updateCart(CartBean bean) throws Exception;

	void deleteCart(CartBean bean) throws Exception;

}
