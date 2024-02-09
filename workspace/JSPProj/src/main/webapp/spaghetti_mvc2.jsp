<!-- View단  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- EL 표기 저장소 우선순위: page > request >session > application -->
	<% pageContext.setAttribute("result", "hello"); %>
	<%-- <%= request.getAttribute("result") %> --%>
	
	<!--EL(Expression Language)-->
	requestScope.result ___ ${requestScope.result}<br>
	${names[0]} ___ names[0]<br>
	${names[1]} ___ names[1]<br>
	${notice.id} ___ notice.id<br>
	${notice.title} ___ notice.title<br>
	${pageScope.result} ___ pageScope.result<br>
	${param.n lt 7} ___ param.n lt 7<br> <!-- true/false -->
	${empty param.n} ___ empty param.n <br> <!--  param.n == null || param.n == '' -->
	${not empty param.n} ___ not empty param.n <br>
	${empty param.n? 'empty value' : param.n} ___ param.n == null || param.n == ''<br>
	${param.n/2} ___ param.n/2<br> <!-- 문자열이 숫자로 바뀜. -->
	${pageContext.request.method} ___ pageContext.request.method<br>
	
</body>
</html>