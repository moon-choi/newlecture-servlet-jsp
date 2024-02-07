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

@WebServlet("/calc2") //@WebServlet("") 안에는 내가 이동할 html 파일 이름 작성. 
public class Calc2 extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {	
		
		//request
		//application 객체는 전역 공간. session 객체는 현재 접속한 사용자. (접속자마다 공간이 달라짐)
		ServletContext application = request.getServletContext(); 
		//ServletContext는 application 에 두 가지 값 저장. v_, op.
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		//response
		response.setCharacterEncoding("UTF-8"); //인코딩 방식 지정해줘야 한글 안깨짐. 쓰기해서 보내기. 
		response.setContentType("text/html; charset=UTF-8"); //text/html은 형식. 브라우저야 이대로 해석해라.
		
		int v = 0; //값을 입력하지 않았을 경우. 
		
		if(!v_.equals("")) v = Integer.parseInt(v_);
		
		//operator가 뭔지에 따라 케이스 갈림. 
			//계산 (= 누를 때)
		if(op.equals("=")) { //application~~ 반환이 객체이므로 Integer 래퍼로 감싸줌.

//			int x = (Integer)session.getAttribute("vKey"); //값을 꺼내려는데 null임.
//			int x = (Integer)application.getAttribute("vKey"); //"첫 번째 넣은 입력 값을 가져오겠다"
			
			int x = 0;
			for(Cookie c: cookies) {
				if(c.getName().equals("vKey")) { 
					x = Integer.parseInt(c.getValue());
					break;
				}	
			}
				
			int y = v; //지금 사용자가 전달한 값. 

//			String operator = (String)application.getAttribute("opKey");
//			String operator = (String)session.getAttribute("opKey");
			String operator = "";
			
			for(Cookie c: cookies) {
				if(c.getName().equals("opKey")) { 
					operator = c.getValue();
					break;
				}	
			}
			
			int result = 0;
			
			if(operator.equals("+")) result = x + y;
			if(operator.equals("-")) result = x - y;
			
			response.getWriter().printf("result is %d\n", result);
		
			//저장 (+, - 누를 때)
		} else {//application 저장소에 저장. 

//			application.setAttribute("vKey", v); //"첫 번째 입력값을 담아 놓겠다"
//			application.setAttribute("opKey", op);
			
//			session.setAttribute("vKey", v); //"첫 번째 입력값을 담아 놓겠다"
//			session.setAttribute("opKey", op);

			Cookie vCookie = new Cookie("vKey", String.valueOf(v));//쿠키 심기 
			Cookie opCookie = new Cookie("opKey", op);
			
			vCookie.setMaxAge(60 * 60 * 24); //in seconds. 브라우저가 닫혀도 살아남아있어야 함. 
			
			vCookie.setPath("/calc2"); //어느 쿠키가 어느경우에 사용자로부터 전달되어야 하는지에 대한 경로.
			opCookie.setPath("/calc2");
			// /는 모든 페이지 요청시 쿠키를 가져오란 뜻. 
			// /notice/는 노티스가 포함됀 페이지에 쿠키를 가져오란 뜻. 
			
			response.addCookie(vCookie); //클라이언트에 전달 (reponse의 Header 형태로)
			response.addCookie(opCookie);
			
			response.sendRedirect("calc2.html");
		}
		
	
	}
}

/*
 
ServletContext
 톰캣이 실행되면서 생성됩니다.
 서블릿 컨텍스트(ServletContext)란 하나의 서블릿이 서블릿 컨테이너와 통신하기 위해서 사용되어지는 메서드들을 가지고 있는 클래스가 바로 ServletContext다.
 하나의 web application 내에 하나의 컨텍스트가 존재합니다. web application내에 있는 모든 서블릿들을 관리하며 정보공유할 수 있게 도와 주는 역할을 담당하는 놈이 바로 ServletContext다.
 쉽게 말하면 웹 애플리케이션의 등록 정보라고 볼 수 있다.
 필터와 리스너 또한 등록하여 통신 간에 활용할 수 있다.
 리스너는 서블릿 리스너, 세션 리스너 등 EventListener 구현체는 뭐든지 등록할 수 있다.
 필터는 characterEncoding 등 Filter 구현체는 뭐든지 등록할 수 있다.
ServletContext를 얻는 방법

 ServletContext는 ServletConfig의 getServletContext() 사용하여 얻는다.
 Servlet(서블릿)은 HttpServlet을 상속한다. 그리고 HttpServlet은 ServletConfig를 구현하고 있기 때문에 getServletContext() 메서드를 바로 이용할 수 있다.
 Servlet extends (HttpServlet implements ServletConfig)
 ServletContext sc = getServletContext(); //서블릿 내에서 사용가능
 

service(request, response)

- 응답에 대한 모든 내용은 service() 메서드에 구현해야 함
- Servlet이 수신한 모든 request에 대해 service() 메서드가 호출됨
- HttpServlet을 상속받은 Servlet 클래스 (이하 하위 클래스) 에서 service() 메서드를 오버라이드 하지 않았다면,
  그 부모인 HttpServlet의 service()가 호출됨   
- service() 메서드는 request의 type(HTTP Method : GET, POST, PUT, DELETE 등)에 따라 적절한 메서드
  (doGet, doPost, doPut, doDelete 등)를 호출함
- 즉, 하위 클래스에서 doGet, doPost 등의 메서드를 오버라이드 해두면 HttpServlet의 service() 메서드가 요청에
  맞는 메서드(하위 클래스에서 오버라이드한 메서드)를 알아서 호출할 수 있게 되는 것
- 메서드가 return 하면 해당 thread는 제거됨

==

HttpServletRequest request 객체

- 사용자가 HTML Form에 입력한 내용(username과 password)을 request 객체에서 받아온다.
   즉, HTTP 프로토콜의 Request 정보를 Servlet에게 전달
- 헤더 정보, 파라미터, 쿠키, URI, URL, Body의 Stream 등을 읽어 들이는 메서드가 있다.
- getHeader(“원하는 헤더 이름”) : 이 메서드를 통해 원하는 헤더 정보를 확인할 수 있다.
- getParameter() : 이 메서드를 호출하여 form parameter 값을 가져온다.
  이런 parameter 값은 URL 또는 form의 input tag를 통해서 넘어올 수 있다.
- getParameterValues()
  form parameter가 두 번 이상 나타나고 여러 개의 값을 반환할 때 이 메서드를 호출한다. (Ex. checkbox)

==

HttpServletResponse response 객체

- 인자의 내용에 맞게 동적인 HTML 코드를 생성하여 response 객체에 담아 반환한다.
- getWriter() 메서드를 호출하여 PrintWriter 객체을 가져온 후 해당 객체에서 print, println 메서드를 실행한다.
- 즉, form data를 처리한 결과를 Web Page에 생성(view 생성)하여 반환한다.

*/