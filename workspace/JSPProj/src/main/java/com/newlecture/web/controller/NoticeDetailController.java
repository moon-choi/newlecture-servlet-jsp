package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Controller 
		int id = Integer.parseInt(request.getParameter("id"));

		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "SELECT * FROM NOTICE WHERE ID=?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "skdine");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery(sql);
			rs.next();

			//Model로 만들기 
			//View단으로 넘겨야 함. 
			String title = rs.getString("TITLE");
			String writerId = rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String hit = rs.getString("HIT");
			String files = rs.getString("FILES");
			String content = rs.getString("CONTENT");

			//Overload됀 생성자를 이용해 값을 초기화. 
			Notice notice = new Notice( //순서 유의! 
					id, 
					title, 
					writerId, 
					regDate, 
					hit, 
					files, 
					content);
			
			request.setAttribute("notice", notice);
			
			//request는 전달외에 저장소 역할도 함. 
			/*
			request.setAttribute("title", title);
			request.setAttribute("writerId", writerId);
			request.setAttribute("regDate", regDate);
			request.setAttribute("hit", hit);
			request.setAttribute("files", files);
			request.setAttribute("content", content);
			*/
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//서블릿에서 다른 서블릿으로 전이시키는 방법 
		//1. redirect: 아예 다른 페이지로 
		//2. forward : 이어서 작업. 
		
		request
		.getRequestDispatcher("/notice/detail.jsp")
		.forward(request, response); //request와 response를 detail.jsp와 공유하게 됌. 
	}
}
