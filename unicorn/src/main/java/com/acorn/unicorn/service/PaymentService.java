package com.acorn.unicorn.service;

import java.util.List;

import com.acorn.unicorn.bean.BasketBean;
import com.acorn.unicorn.dto.BasketDTO;
import com.acorn.unicorn.dto.ProductDTO;

public interface PaymentService {

	List<BasketDTO> selectPaymentList(BasketBean bean) throws Exception;

	int stockSub(BasketBean bean) throws Exception;

	int cartEmpty(BasketBean bean) throws Exception;

	int insertPurchase(BasketBean bean) throws Exception;

	ProductDTO stockConfig() throws Exception;

	//void insertCart1(CartBean bean) throws Exception;

//	BoardDTO selectBoardDetail(int idx) throws Exception;

	//CartDTO configProduct(CartBean bean) throws Exception;

	//void updateCart(CartBean bean) throws Exception;

	//void deleteCart(CartBean bean) throws Exception;

}
