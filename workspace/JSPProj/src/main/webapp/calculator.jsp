<!-- out.write로 html로 출력하지 않고 실행되어야 할 자바 코드로 쓸 때  -->
<%
/*
 <%: : service 메소드 밑으로 들어감. (일반적인 코드블록은 다 _jspService 밑으로 들어감) 
 <%! : !를 쓰면 클래스 멤버 메서드 만들어 주고 싶을 때. (service 메소드 밑으로 안 들어감) 
 <%@ : Page 지시자. contentType, pageEncoding 
 <%= : html 문서 내에서 코드쓸 때.
*/


int x = 2;
int y = 3;
/*
 int page = 4; 
 page는 JSP 내장 객체이므로 변수 이름으로 못 씀. 
 내장객체 e.g: pageContext, session, application, config, out, page, request, response, out, session, application
*/
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calculator</title>
</head>
<style>
	input {
		width: 50px;
		height: 50px;
	}
	
	.output {
		width: 50px;
		background-color : #e9e9e9;
		font-size: 24px;
		font-weight: bold;
		text-align: right;
		padding: 0px 5px;
	}
	
</style>
<body>
	<h1>Calculator</h1>
	<form action="calc3" method="post">
		<table>
			<tr class="output">
				<td class="output" colspan="4"><%= x + y %></td>
			</tr>
			<tr>
				<td><input type="submit" name="operator" value="CE"/></td>
				<td><input type="submit" name="operator" value="C"/></td>
				<td><input type="submit" name="operator" value="⇦"/></td>
				<td><input type="submit" name="operator" value="/"/></td>
			</tr>
			
			<tr>
				<td><input type="submit" name="value" value="7"/></td>
				<td><input type="submit" name="value" value="8"/></td>
				<td><input type="submit" name="value" value="9"/></td>
				<td><input type="submit" name="operator" value="x"/></td>
			</tr>
			<tr>
				<td><input type="submit" name="value" value="4"/></td>
				<td><input type="submit" name="value" value="5"/></td>
				<td><input type="submit" name="value" value="6"/></td>
				<td><input type="submit" name="operator" value="-"/></td>
			</tr>
			<tr>
				<td><input type="submit" name="value" value="1"/></td>
				<td><input type="submit" name="value" value="2"/></td>
				<td><input type="submit" name="value" value="3"/></td>
				<td><input type="submit" name="operator" value="+"/></td>
			</tr>
			<tr>
				<td><input type="submit" name="operator" value="±"/></td>
				<td><input type="submit" name="value" value="0"/></td>
				<td><input type="submit" name="dot" value="."/></td>
				<td><input type="submit" name="operator" value="="/></td>
			</tr>
		</table>
	</form>

</body>
</html>