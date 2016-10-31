package com.acorn.unicorn.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.acorn.unicorn.bean.BasketBean;
import com.acorn.unicorn.dto.BasketDTO;
import com.acorn.unicorn.dto.ProductDTO;

@Repository("paymentDAO")
public class PaymentDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public List<BasketDTO> selectPaymentList(BasketBean bean) throws Exception{
		 return selectList("payment.selectPaymentList", bean);
	}

	public int productSub(BasketBean bean) throws Exception{
		int re = update("payment.productSub", bean);
		return re;
	}

	public int cartEmpty(BasketBean bean) throws Exception{
		int re = delete("payment.cartEmpty", bean);
		return re;
	}

	public int insertPurchase(BasketBean bean) throws Exception{
		int re = insert("payment.insertPurchase", bean); 
		return re;
	}

	public ProductDTO stockConfig() throws Exception{
		return selectOne("payment.stockConfig");
	}
 
}
