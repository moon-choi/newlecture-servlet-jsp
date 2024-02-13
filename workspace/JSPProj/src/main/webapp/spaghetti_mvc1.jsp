<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//----------입력코드----------//
//Controller: 자바코드 (입력, 제어 담당)
int num = 0; //0은 짝수 
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

//View: HTML 코드 (출력 담당)
%>

<!----------출력 코드---------->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spaghetti MVC 1 page</title>
</head>
<body>
The number you input is <%= result %>
</body>
</html>

<!-- model 1: 컨트롤러와 뷰가 물리적으로 분리되지 않은 방식. 한 jsp 파일 안에 C,M,V가다 있음. -->