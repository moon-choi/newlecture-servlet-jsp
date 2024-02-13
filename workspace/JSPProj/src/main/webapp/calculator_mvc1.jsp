<!-- out.write로 html로 출력하지 않고 실행되어야 할 자바 코드로 쓸 때  -->

<%
/*
- 재스퍼에 html 외의 자바 코드를 넣으려면 맨 위 코드 블록에 넣음. 
- 재스퍼에게 일을 시켜서 서블릿을 만듦. 일을 시키려면 .jsp파일로.
- 재스퍼는 out.write(문자열을 출력하는 함수)를 대신 써서 밑의 경로에 저장해줌. 
- 재스퍼가 만들어낸 서블릿 코드는 여기에 저장됌. 
/Users/moonchoi/projects/뉴렉처/Servlet-JSP/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/work/Catalina/localhost/ROOT/org/apache/jsp

[4가지 코드블록]
 <%: : service 메소드 밑으로 들어감. (일반적인 코드블록은 다 _jspService 밑으로 들어감). 일반적인 로직을 위한 코드블록. 
 <%! : !를 쓰면 클래스 멤버 메소드 만들어 주고 싶을 때. (service 메소드 밑으로 안 들어감) 멤버변수. 멤버함수 정의하기 위한 코드블록. 
 <%@ : Page 지시자. langugae, contentType, pageEncoding. out.write 출력 전의 어떤 코드보다도 먼저 실행됌.  
 <%= : html 문서 내에서 변수 쓸 때. 출력을 위한 코드블록. (out.print)
 
[JSP 내장객체] 
 int page = 4; 
 page는 JSP 내장 객체이므로 변수 이름으로 못 씀. 
 내장객체 e.g: pageContext, session, application, config, out, page, request, response, session, application
*/

int x = 2;
int y = 3;

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