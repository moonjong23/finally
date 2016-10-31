package com.acorn.unicorn.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acorn.unicorn.bean.UserBean;
import com.acorn.unicorn.dto.UserDTO;
import com.acorn.unicorn.service.JoinService;

@Controller
public class LoginController {

	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "joinService")
	private JoinService joinService;

	@RequestMapping(value = "/views/home.do")
	public ModelAndView index_view() throws Exception {
		return new ModelAndView("/views/home");
	}

	@RequestMapping(value = "/views/Login.do", method = RequestMethod.POST)
	public ModelAndView login_config() throws Exception {
		ModelAndView mv = new ModelAndView("/login/login");
		
		return mv;
	}
	
	@RequestMapping(value = "/views/join.do", method = RequestMethod.GET)
	public ModelAndView joinProcess() throws Exception {
		ModelAndView mv = new ModelAndView("/views/join");

		return mv;
	}

	@RequestMapping(value = "/views/join.do", method = RequestMethod.POST)
	public ModelAndView joinProcess(UserBean bean) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/views/BoardList.do");

		joinService.insertUser(bean);

		return mv;
	}

	@RequestMapping(value = "/views/logincheck.do")
	public ModelAndView loginProcess(HttpSession session, HttpServletResponse response, UserBean bean)
			throws Exception {

		ModelAndView mv = new ModelAndView("redirect:/views/home.do");
		if (session.getAttribute("userid") != null) {
			session.removeAttribute("userid");
			return mv;
		}

		if (joinService.configUser(bean) != null) {
			UserDTO dto = joinService.configUser(bean);
			if (bean.getUserid().equals(dto.getUserid()) && bean.getPassword().equals(dto.getPassword()))
				session.setAttribute("userid", dto.getUserid());
		} else {
			mv.setViewName("/errors/error");
			return mv;
		}
		return mv;
	}

	@RequestMapping(value = "/erorrs/error.do", method = RequestMethod.GET)
	public ModelAndView error_view() throws Exception {
		return new ModelAndView("/errors/error2");
	}
}