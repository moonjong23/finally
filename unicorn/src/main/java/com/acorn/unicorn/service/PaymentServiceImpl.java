package com.acorn.unicorn.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.acorn.unicorn.bean.BasketBean;
import com.acorn.unicorn.dao.PaymentDAO;
import com.acorn.unicorn.dto.BasketDTO;
import com.acorn.unicorn.dto.ProductDTO;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService{
	Logger log = Logger.getLogger(this.getClass());
    
    @Resource(name="paymentDAO")
    private PaymentDAO paymentDAO;
	
    @Override
    public List<BasketDTO> selectPaymentList(BasketBean bean) throws Exception {
    	return paymentDAO.selectPaymentList(bean);
    }
    
    @Override
    public int stockSub(BasketBean bean) throws Exception{
    	int re = paymentDAO.productSub(bean); 
    	return re;
    }
    
    @Override
    public int cartEmpty(BasketBean bean) throws Exception {
    	int re = paymentDAO.cartEmpty(bean);
    	return re;	
    }
    
    @Override
    public int insertPurchase(BasketBean bean) throws Exception {
    	int re = paymentDAO.insertPurchase(bean);
    	return re;	
    }
    
    @Override
    public ProductDTO stockConfig() throws Exception{
    	return paymentDAO.stockConfig();
    	
    }
    
}
