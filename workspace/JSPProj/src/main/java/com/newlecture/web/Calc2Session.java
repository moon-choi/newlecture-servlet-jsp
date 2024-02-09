package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc2session") //@WebServlet("") 안에는 내가 이동할 html 파일 이름 작성. 
public class Calc2Session extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {	
		
		//request
		
		//application 객체는 전역 공간. session 객체는 현재 접속한 사용자. (접속자마다 공간이 달라짐)
		HttpSession session = request.getSession(); //*** 1 ***

		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		//response
		response.setCharacterEncoding("UTF-8"); //인코딩 방식 지정해줘야 한글 안깨짐. 쓰기해서 보내기. 
		response.setContentType("text/html; charset=UTF-8"); //text/html은 형식. 브라우저야 이대로 해석해라.
		
		int v = 0; //값을 입력하지 않았을 경우. 
		
		if(!v_.equals("")) 
			v = Integer.parseInt(v_);
		
		//operator가 뭔지에 따라 케이스 갈림. 
			//1. 계산 (= 누를 때)
		if(op.equals("=")) { 
			
			int x = (Integer)session.getAttribute("v"); //값을 꺼내려는데 null임. //*** 2 ***	
			int y = v; //지금 사용자가 전달한 값. 
			String operator = (String)session.getAttribute("op"); //*** 3 ***
						
			int result = 0;
			
			if(operator.equals("+")) 
				result = x + y;
			if(operator.equals("-")) 
				result = x - y;
			
			response.getWriter().printf("result is %d\n", result);
		
			//2. 저장 (+, - 누를 때)
		} else {
			
			session.setAttribute("v", v); //"첫 번째 입력값을 담아 놓겠다"
			session.setAttribute("op", op); //*** 4 ***
			
			response.sendRedirect("calc2session.html");
		}
		
	
	}
}