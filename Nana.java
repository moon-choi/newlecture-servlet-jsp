import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Nana extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
						throws IOException, ServletException
	{
//		OutputStream os = response.getOutputStream();
//		PrintStream out = new PrintStream(os, true); //true는 버퍼 꽉 찰 때까지 기다리지말고 flush하란 뜻.
		PrintWriter out = response.getWriter(); //다국어일 땐 Writer 씀.
		out.println("Hello Servlet!"); //원격에 있는 클라이언트(사용자)에게 전달.
	}
}