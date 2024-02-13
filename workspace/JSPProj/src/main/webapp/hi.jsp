<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//.jsp 파일의 이름은 url로 맵핑돼므로 .java 파일과 달리 첫글자를 소문자로 지음. 

String query = request.getParameter("count"); //request: 입력도구 

int count = 5;
String greeting = "Hi 안녕 JSP! <br>";
if(query != null && !query.equals("")) { //조건 검사 1) count가 없는 경우 2) count 있지만 빈 문자열인 경우  
	count = Integer.parseInt(query); //문자열을 정수형식으로 변환.
} 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hi page</title>
</head>
<body>
<% for (int i = 0; i < count; i++) {%>
	<%= greeting %>
<% } %>
</body>
</html>