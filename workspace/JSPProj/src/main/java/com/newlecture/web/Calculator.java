package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		if(req.getMethod().equals("GET")) { //구분자는 대문자로.
//			System.out.println("GET request was made.");
//		} else if(req.getMethod().equals("POST")) {
//			System.out.println("POST request was made.");
//		}
//		super.service(req, res); 
//		//form method가 get요청이면 doGet을 찾고, post 요청이면 doPost를 찾음. 
//		//404: url있음. 405: url은 있으나 로직이 없음. (메소드를 처리하는 것이 없음)
		//서비스 함수 통해서 doGet, doPost가 호출됌. 
		//공통으로 처리하는 것이 있으면 super.service로 처리하고 따로 하려면 doGet, doPost 활용.
//	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGET method was called.");
		
		Cookie[] cookies = request.getCookies();
		String expression = "0";
		
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("expression")) { 
					expression = c.getValue();
					break;
				}	
			}
		}
			
		//response
		response.setCharacterEncoding("UTF-8"); //인코딩 방식 지정해줘야 한글 안깨짐. 쓰기해서 보내기. 
		response.setContentType("text/html; charset=UTF-8"); //text/html은 형식. 브라우저야 이대로 해석해라.
		PrintWriter out = response.getWriter();
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Calc Page</title>");
		out.write("</head>");
		out.write("<style>");
		out.write("	input { ");
		out.write("		width: 50px;");
		out.write("		height: 50px;");
		out.write("	}");
			
		out.write("	.output {");
		out.write("		width: 50px;");
		out.write("		background-color : #e9e9e9;");
		out.write("		font-size: 24px;");
		out.write("		font-weight: bold;");
		out.write("		text-align: right;");
		out.write("		padding: 0px 5px;");
		out.write("	}");
			
		out.write("</style>");
		out.write("<body>");
		out.write("	<h1>Calculator</h1>");
		out.write("	<form method=\"post\">");
		out.write("		<table>");
		out.write("			<tr class=\"output\">");
		out.printf("				<td class=\"output\" colspan=\"4\">%s</td>", expression);
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"CE\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"C\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"⇦\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"/\"/></td>");
		out.write("			</tr>");
					
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"7\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"8\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"9\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"*\"/></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"4\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"5\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"6\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"-\"/></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"1\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"2\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"3\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"+\"/></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"±\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"0\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"dot\" value=\".\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"=\"/></td>");
		out.write("			</tr>");
		out.write("		</table>");
		out.write("	</form>");

		out.write("</body>");
		out.write("</html>");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPOST method was called.");
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
	
		expCookie.setPath("/calculator"); // /calculator만 쿠키 사용 
		response.addCookie(expCookie);
		response.sendRedirect("calculator"); //get 요청 
	}
	
}

