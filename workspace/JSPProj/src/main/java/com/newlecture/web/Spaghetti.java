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


//Controller단 
@WebServlet("/spaghetti_mvc2")
public class Spaghetti extends HttpServlet{
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
		result = "odd number";
	} else {
		result = "even number";
	}
	request.setAttribute("result", result);
	
	//EL: Array
	String[] names = {"newlec", "dragon"};
	request.setAttribute("names", names);
	
	//EL: Map
	Map<String, Object> notice = new HashMap<String, Object>();
	notice.put("id", 1);
	notice.put("title", "EL is great");
	request.setAttribute("notice", notice);	
	
	//redirect: 현재 작업했던 내용과 상관없는 새로운 요청. 
	//forward: 현재 작업한 내용을 이어갈 수 있도록 공유하는 게 있음.
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("spaghetti_mvc2.jsp"); //forward
	dispatcher.forward(request, response); //request와 response를 .jsp(servlet)과 공유 
	//forward 관계에서 공유됄 수 있는 저장소는 request. 
	}
}
