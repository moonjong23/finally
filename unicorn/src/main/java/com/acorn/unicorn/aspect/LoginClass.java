package com.acorn.unicorn.aspect;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class LoginClass {
	public boolean loginCheck(HttpServletRequest request, HttpServletResponse response) throws Exception{
		RequestDispatcher rd = request.getRequestDispatcher("/views/Login.do");
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("userid"));
		if(session.getAttribute("userid") == null){
			rd.forward(request, response);
			return false;
		}else{
			return true;
		}
	}
}
