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
		
		//서블릿이 실행돼기 전에 인코딩 완료시키기. 
		request.setCharacterEncoding("UTF-8"); 
		//모든 서블릿은 인코딩 필터가 적용됀 환경을 갖게됌. 
		//^필터에 인코딩을 적용하고 서블릿은 이제 인코딩 개입 안함. 
		
		chain.doFilter(request,  response); //요청이 오면 흐름을 넘겨서 다음 필터나 다음 서블렛이 실행됌.
		
		System.out.println("after filter"); //이 줄은 다음 서블릿이나 필터가 실행돼고 난 뒤 실행됌.
	}

}

/*
인코딩한 방법 request.setCharacterEncoding("UTF-8")
	1. Noticereg.java에서 인코딩
	2. 필터안에서 인코딩 CharacterEncodingFilter.java
	3. 어노테이션으로 @WebFilter("/*"): web.xml에서 filter-mapping 불필요해짐.
*/