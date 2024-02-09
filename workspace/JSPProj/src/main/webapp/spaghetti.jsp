<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//Controller: 자바코드 (입력, 제어)
int num = 0; 
String num_ = request.getParameter("n");

if(num_ != null && !num_.equals("")) 
	num = Integer.parseInt(num_);

String result;

if(num % 2 != 0){	
	result = "odd number";
} else {
	result = "even number";
}

//Model: 출력 데이터 "result"

//View: html 코드 (출력 담당)
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%= result %>
</body>
</html>