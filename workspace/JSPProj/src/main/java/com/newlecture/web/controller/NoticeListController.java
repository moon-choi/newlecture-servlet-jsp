package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Notice> list  = new ArrayList<>();
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "SELECT * FROM NOTICE";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "newlec", "skdine");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);


			while(rs.next()) {	
				int id = rs.getInt("ID"); 
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regDate = rs.getDate("REGDATE");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				
				//.entity 패키지의 Notice.java
				Notice notice = new Notice( //순서 유의! 
						id, 
						title, 
						writerId, 
						regDate, 
						hit, 
						files, 
						content);
				
				//list는 notice 객체로 이루어진 배열 
				list.add(notice); //.add는 ArrayList 클래스의 주요 메소드 
				
				rs.close();
				st.close();
				con.close();
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//1. 데이터 심기 
		request.setAttribute("list", list);
		
		//2. 데이터 전달하기 
		request
		.getRequestDispatcher("/notice/list.jsp")
		.forward(request, response);
	}

}
