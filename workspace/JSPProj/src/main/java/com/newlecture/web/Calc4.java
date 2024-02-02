package com.newlecture.web;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc4")
public class Calc4 extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {	
		
		//request
		Cookie[] cookies = request.getCookies();
		
		String value = request.getParameter("value"); //getParameter는 input의 name을 찾음.  
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");		
		String expression = "";
		
		

		
		if(cookies != null)
			for(Cookie c : cookies) {
				if(c.getName().equals("expression")) { 
					expression = c.getValue();
					break;
				}	
			}

		if(operator != null && operator.equals("=")) {
//			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn"); //스크립트를 실행할수 있는 엔진 			
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");
			
			try {
				if(engine != null) 
					expression = (String) engine.eval(expression); //오브젝트를 문자열로 변환해서 저장 
			} catch (ScriptException e) {
				e.printStackTrace();
			}

		} 
		
		else if(operator != null && operator.equals("C")) {
			expression = ""; //쿠키 값을 비우기.			
		}
		
		else {
			System.out.println("engine is null");
			expression += (value == null)? "" : value; 
			expression += (operator == null)? "" : operator; 
			expression += (dot == null)? "" : dot; 
		}
		
		Cookie expCookie = new Cookie("expression", expression);
		if(operator != null && operator.equals("C"))
			expCookie.setMaxAge(0); //쿠키 지우기. (바로 소멸)
	
		response.addCookie(expCookie);
		response.sendRedirect("calcpage");
	
	}
}