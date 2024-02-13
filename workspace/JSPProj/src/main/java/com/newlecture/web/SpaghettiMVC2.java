package com.newlecture.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//Controller단 (서블릿)
@WebServlet("/spaghetti_mvc2")
public class SpaghettiMVC2 extends HttpServlet{
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//Controller: 자바코드 (입력, 제어)
	int num = 0; 
	String num_ = request.getParameter("n");

	if(num_ != null && !num_.equals("")) 
		num = Integer.parseInt(num_);

	//EL: String
	String result;
	if(num % 2 != 0){	
		result = "odd number (request scope)";
	} else {
		result = "even number (request scope)";
	}
	request.setAttribute("result", result); //흐름 1.***
	
	//EL: Array
	String[] names = {"newlec", "dragon"};
	request.setAttribute("names", names);
	
	//EL: Map<key, value>
	Map<String, Object> notice = new HashMap<String, Object>();
	//값 심기 
	notice.put("id", 1); 
	notice.put("title", "EL is great");
	request.setAttribute("notice", notice);	
	
	//redirect: 현재 작업했던 내용과 상관없는 새로운 요청. 
	//forward: 현재 작업한 내용을 이어갈 수 있도록 공유하는 게 있음.
	
	//forward를 위해서는 dispatcher 써야함. (result를 spaghetti_mvc2.jsp파일로 전달하기 위해) 
	RequestDispatcher dispatcher = request.getRequestDispatcher("spaghetti_mvc2.jsp"); //흐름 2.*** 
	dispatcher.forward(request, response); //request와 response를 .jsp(servlet)과 공유 
	//forward 관계에서 공유됄 수 있는 저장소는 request (result를 저장). 
	}

/*
 [상태저장소]
 pageContext (페이지 안에서만)
 request (forward 관계)
 session
 cookie (클라이언트에 저장)
 
하나의 서블릿 페이지에 대한 저장소는 pageContext
하나의 Web application에 대한 저장소는 ServletContext(전역 변수와 비슷한 느낌)
forward 관계에서 사용되는 저장소는 Request
특정 Session에 대한 저장소는 Session
Client에 저장하는 저장소는 Cookie
 */
}
