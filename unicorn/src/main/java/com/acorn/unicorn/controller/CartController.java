package com.acorn.unicorn.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.acorn.unicorn.bean.CartBean;
import com.acorn.unicorn.dto.CartDTO;
import com.acorn.unicorn.service.CartService;

@Controller
public class CartController {
	HttpSession session;
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "cartService")
	private CartService cartService;
	
	@RequestMapping(value = "/views/WishList.do")
	public ModelAndView insertCart(CartBean bean, HttpServletRequest request, HttpServletResponse response, @RequestParam("history_back") String history_back) throws Exception {
		ModelAndView mv = new ModelAndView();
		if(cartService.configProduct(bean) == null){
			cartService.insertCart1(bean);
		}else{
			cartService.updateCart(bean);
		}
		
		session = request.getSession();
		if(history_back.equals("n")) 
		mv.setViewName("redirect:/views/BasketView.do");
		else
		mv.setViewName("redirect:/views/ProductList.do");
		mv.addObject("user_id", session.getAttribute("userid"));
		return mv;
	}
	
	@RequestMapping(value = "/views/BasketView.do")
	public ModelAndView BasketView(@RequestParam("user_id") String user_id) throws Exception {
		ModelAndView mv = new ModelAndView("/views/cartlist");
		List<CartDTO> list =cartService.selectCartList(user_id);
		mv.addObject("list", list);
		return mv;
	}
	
	@RequestMapping(value = "/views/ProductDel.do")
	public ModelAndView SelectCartDel(CartBean bean, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/views/BasketView.do");
		session = request.getSession();
		cartService.deleteCart(bean);
		mv.addObject("user_id", session.getAttribute("userid"));
		return mv;
	}
	
	@RequestMapping(value = "/views/ProductUp.do")
	public ModelAndView SelectCartUp(CartBean bean, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/views/BasketView.do");
		session = request.getSession();
		cartService.updateCart(bean);
		mv.addObject("user_id", session.getAttribute("userid"));
		return mv;
	}
}