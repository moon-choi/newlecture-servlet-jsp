package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc2cookie") //@WebServlet("") 안에는 내가 이동할 html 파일 이름 작성. 
public class Calc2Cookie extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {	
		
		//request
		
		//쿠키 읽기 (request)
		Cookie[] cookies = request.getCookies(); //복수라서 배열로 옴
		
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
			
			int x = 0; //값 초기화 
			int y = v; //지금 사용자가 전달한 값. 	
			//쿠키 꺼내기 
			for(Cookie c : cookies)
				if(c.getName().equals("v")) { //기존의 v
					x = Integer.parseInt(c.getValue()); //c.getValue는 스트링을 반환하므로 정수형으로 바꿔줌. 
					break;
				}	
							
			String operator = ""; //값 초기화 	
			for(Cookie c : cookies)
				if(c.getName().equals("op")) { 
					operator = c.getValue();
					break;
				}	
			
			int result = 0; //값 초기화 
			
			if(operator.equals("+")) 
				result = x + y;
			if(operator.equals("-")) 
				result = x - y;
			
			response.getWriter().printf("result is %d\n", result);
		
			//2. 저장 (+, - 누를 때)
		} else { 

			//쿠키 심기 
			Cookie vCookie = new Cookie("v", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			
//			vCookie.setMaxAge(60 * 60 * 24); //in seconds. 브라우저가 닫혀도 살아남아있어야 함. 
			
			vCookie.setPath("/calc2cookie"); //어느 쿠키가 어느경우에 사용자로부터 전달되어야 하는지에 대한 경로.
			opCookie.setPath("/calc2cookie");
			// /는 모든 페이지 요청시 쿠키를 가져오란 뜻. 
			// /notice/는 노티스가 포함됀 페이지에 쿠키를 가져오란 뜻. 
			
			//쿠키 전달하기 (response) 
			response.addCookie(vCookie); //클라이언트에 전달 (response의 Header에 심어진 형태로)
			response.addCookie(opCookie);
			
			response.sendRedirect("calc2cookie.html");
		}
		
	
	}
}

/*
 서버에 저장: Application, session
 클라이언트에 저장: Cookie
*/