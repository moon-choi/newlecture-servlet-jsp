package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notice-reg") //mapping 정보를 남기므로 web.xml에서 mapping 안해도 됌.
public class NoticeReg extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8"); //인코딩 방식 지정해줘야 한글 안깨짐. 쓰기해서 보내기. 
		response.setContentType("text/html; charset=UTF-8"); //받을 때 이렇게 읽어라. text/html은 형식. 브라우저야 이대로 해석해라.
		// Response header에 Content-Type: text/html;charset=UTF-8로 지정됌. 브라우저가 자의적으로 컨텐트 해석하는 것을 방지함. 
		
		//이것 대신 서블릿 필터를 사용함 (CharacterEncodingFilter). 
//		request.setCharacterEncoding("UTF-8"); //폼 안의 값이 한글일 때 submit 후 안깨지게끔. 
		String title = request.getParameter("title"); //reg.html 에서 name 값을 받아옴. 
		String content = request.getParameter("content");
		
		PrintWriter out = response.getWriter();
		out.println(title);
		out.println(content);

	}

}