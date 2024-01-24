package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hi") //mapping 정보를 남기므로 web.xml에서 mapping 안해도 됌.
public class Nana extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8"); //인코딩 방식 지정해줘야 한글 안깨짐. 쓰기해서 보내기. 
		response.setContentType("text/html; charset=UTF-8"); //받을 때 이렇게 읽어라. text/html은 형식. 브라우저야 이대로 해석라.
		// Response header에 Content-Type: text/html;charset=UTF-8로 지정됌. 브라우저가 자의적으로 컨텐트 해석하는 것을 방지함. 
		
		PrintWriter out = response.getWriter();
		
		String query = request.getParameter("count");
		
		int count = 10;
		if(query != null && !query.equals("")) {
			count = Integer.parseInt(query); //문자열을 정수형식으로 변환.
		}
		
		for(int i  = 0; i < count; i++) {
			out.println((i + 1) + ": Hi 안녕 Servlet! <br>");			
		}
		
	}

}

/* 가능한 케이스들
 
/hi?cnt=3	| "3"
/hi?cnt=	| ""
/hi?		| null		
/hi 		| null 

*/
