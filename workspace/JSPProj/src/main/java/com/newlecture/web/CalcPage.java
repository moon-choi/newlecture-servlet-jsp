package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calcpage")
public class CalcPage extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {	
		
		//response
		response.setCharacterEncoding("UTF-8"); //인코딩 방식 지정해줘야 한글 안깨짐. 쓰기해서 보내기. 
		response.setContentType("text/html; charset=UTF-8"); //text/html은 형식. 브라우저야 이대로 해석해라.
		PrintWriter out = response.getWriter();
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Insert title here</title>");
		out.write("</head>");
		out.write("<style>");
		out.write("	input { ");
		out.write("		width: 50px;");
		out.write("		height: 50px;");
		out.write("	}");
			
		out.write("	.output {");
		out.write("		width: 50px;");
		out.write("		background-color : #e9e9e9;");
		out.write("		font-size: 24px;");
		out.write("		font-weight: bold;");
		out.write("		text-align: right;");
		out.write("		padding: 0px 5px;");
		out.write("	}");
			
		out.write("</style>");
		out.write("<body>");
		out.write("	<h1>Calculator</h1>");
		out.write("	<form action=\"calc3\" method=\"post\">");
		out.write("		<table>");
		out.write("			<tr class=\"output\">");
		out.printf("				<td class=\"output\" colspan=\"4\">%d</td>", 3 + 4);
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"CE\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"C\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"⇦\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"÷\"/></td>");
		out.write("			</tr>");
					
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"7\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"8\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"9\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"x\"/></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"4\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"5\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"6\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"-\"/></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"1\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"2\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"3\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"+\"/></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"±\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"0\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"dot\" value=\".\"/></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"=\"/></td>");
		out.write("			</tr>");
		out.write("		</table>");
		out.write("	</form>");

		out.write("</body>");
		out.write("</html>");
		
	}
}
