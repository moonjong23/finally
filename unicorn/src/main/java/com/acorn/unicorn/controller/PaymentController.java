package com.acorn.unicorn.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acorn.unicorn.bean.BasketBean;
import com.acorn.unicorn.dto.BasketDTO;
import com.acorn.unicorn.dto.ProductDTO;
import com.acorn.unicorn.service.PaymentService;

@Controller
public class PaymentController {
	HttpSession session;
	Logger log = Logger.getLogger(this.getClass());
	@Autowired
	TransactionTemplate transactionTemplate;
	
	@Resource(name = "paymentService")
	private PaymentService paymentService;
	
	
	@RequestMapping(value = "/views/PaymentList.do", method=RequestMethod.POST)
	public ModelAndView paymentView(BasketBean bean, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		session = request.getSession();
		bean.setProduct_id(bean.getProduct_id().substring(0, bean.getProduct_id().length()-1));
		String[] modi_num = bean.getNumber().substring(0, bean.getNumber().length()-1).split(",");
		List<BasketDTO> list = paymentService.selectPaymentList(bean);
		mv.setViewName("/views/paymentlist");
		mv.addObject("list", list);
		mv.addObject("modi_num", modi_num);
		mv.addObject("user_id", session.getAttribute("userid"));
		return mv;
	}
	
	@RequestMapping(value = "/views/PaymentProcess.do", method=RequestMethod.POST)
	public ModelAndView paymentSub(final BasketBean bean, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		session = request.getSession();
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			boolean b = true; 
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status){
				// TODO Auto-generated method stub
				System.out.println(bean.getStock().substring(0, bean.getStock().length()-1));
				String[] str1 = bean.getStock().substring(0, bean.getStock().length()-1).split(",");  //스탁 안읽혀짐 
				String[] str2 = bean.getProduct_id().substring(0, bean.getProduct_id().length()-1).split(",");
				String[] str3 = bean.getPrice().substring(0, bean.getPrice().length()-1).split(",");
				Timestamp time = new Timestamp(System.currentTimeMillis()); 
				bean.setCreated(time.toString());
				for (int i = 0; i < str3.length; i++) {
					try {
						bean.setStock(str1[i]);
						bean.setProduct_id(str2[i]); 
						bean.setPrice(str3[i]);
//						paymentService.stockSub(bean);
//						paymentService.cartEmpty(bean);
//						paymentService.insertPurchase(bean);
//						paymentService.stockConfig(); 
						ProductDTO dto = paymentService.stockConfig(); //여기문제
						
						if(paymentService.stockSub(bean) == 0 || paymentService.cartEmpty(bean) == 0 || paymentService.insertPurchase(bean) == 0 || dto.getStock() > 0){
							System.out.println("재고부족");
							b = false;
							status.setRollbackOnly();
						}
						
					}catch (Exception e) {
						System.out.println(e);
					}
				}
				
			}
		});
		//List<BasketDTO> list = paymentService.selectPaymentList(bean);
		mv.setViewName("redirect:/views/ProductList.do");
		//mv.addObject("list", list);
		mv.addObject("user_id", session.getAttribute("userid"));
		return mv;
	}
}