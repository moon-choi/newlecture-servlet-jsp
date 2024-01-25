package com.newlecture.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*") //Annotation 쓰면 .xml의 filter mapping 불필요.
public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, 
			ServletResponse response, 
			FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("before filter");
		
		request.setCharacterEncoding("UTF-8"); //모든 서블릿은 인코딩 필터 적용 후.
		//^필터에 인코딩을 적용하고 서블릿은 이제 인코딩 개입 안함. 
		chain.doFilter(request,  response); //다음 필터나 다음 서블렛이 실행됌.
		
		System.out.println("after filter");
	}

}
