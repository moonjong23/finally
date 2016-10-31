package com.acorn.unicorn.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopClass {
	
   @Autowired
   LoginClass loginClass;
	
   @Around("execution(public * insertCart(..))")
   public Object loginAop(ProceedingJoinPoint joinPoint) throws Throwable {
	   Object obj = null;
      HttpServletResponse response = null;
      HttpServletRequest request = null;
	      
	      for(Object o : joinPoint.getArgs()){
	         if(o instanceof HttpServletRequest){
	            request = (HttpServletRequest) o;
	         }else if(o instanceof HttpServletResponse){
	            response = (HttpServletResponse) o;
	         }
	      }
	      //session check 후 로그인하지 않은경우
	      if(loginClass.loginCheck(request, response)==false){
	         obj = null;
	      }else{
	         obj = joinPoint.proceed();
	      }
	      
	      return obj;
   }
}
