package com.newlecture.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc3")
public class Calc3 extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {	
		
		//request
		//application 객체는 전역 공간. session 객체는 현재 접속한 사용자. (접속자마다 공간이 달라짐)
		Cookie[] cookies = request.getCookies();
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		//response
		response.setCharacterEncoding("UTF-8"); //인코딩 방식 지정해줘야 한글 안깨짐. 쓰기해서 보내기. 
		response.setContentType("text/html; charset=UTF-8"); //text/html은 형식. 브라우저야 이대로 해석해라.
		
		int v = 0;
		
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