<!-- View단 (모델 출력을 위한 HTML만 존재) -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spaghetti MVC 2 page</title>
</head>
<body>

	<!-- EL 표기 저장소 우선순위: page > request > session > application -->
	<% pageContext.setAttribute("result", "hello (page scope)"); //페이지 내에서 저장소로 쓸수 있는 객체 %>
	
	<!-- //흐름 3.*** -->
	<%-- <%= request.getAttribute("result") %> --%> 
	${result}<br>
	
	<!--EL(Expression Language)-->
	${pageScope.result} ___ pageScope.result<br>
	${requestScope.result} ___ requestScope.result<br>
	${names[0]} ___ names[0]<br>
	${names[1]} ___ names[1]<br>
	${notice.id} ___ notice.id<br>
	${notice.title} ___ notice.title<br>
	${header.host} ____ header.host<br>
	${header.accept} ____ header.accept<br>
	<br>
	${param.n gt 7} ___ param.n lt 7<br> <!-- true/false -->
	${empty param.n} ___ empty param.n <br> <!--  param.n == null || param.n == '' -->
	${not empty param.n} ___ not empty param.n <br>
	${empty param.n? 'empty value' : param.n} ___ param.n == null || param.n == ''<br>
	${param.n/2} ___ param.n/2<br> <!-- 문자열이 숫자로 바뀜. -->
	${pageContext.request.method} ___ pageContext.request.method<br>
	
</html>

<!-- model 2: 컨트롤러와 뷰가 물리적으로 분리된 방식. 
C(.java파일 서블릿) dispatcher통해서 forwarding 사용), M와 
V(.jsp파일)가 다른 파일. -->