package com.register;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Register extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		//get PrintWriter to get value
		PrintWriter pw = res.getWriter();
		//set content type
		res.setContentType("text/html");
		//read the pass value
		String name = req.getParameter("name");
		String eventDate = req.getParameter("eventDate");
		String eventTime = req.getParameter("eventTime");
		String mob = req.getParameter("MobileNo");
		String dist = req.getParameter("district");
		String state = req.getParameter("state");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306";
			String db = "/ems";
			String usr = "root";
			String pass = "mj1012";
			
			Connection conn = DriverManager.getConnection(url+db, usr, pass);
			
			String query = "INSERT INTO book (Name, eDate, eTime, Mob, district, state) VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement prsmt = conn.prepareStatement(query);
			prsmt.setString(1, name);
			prsmt.setString(2, eventDate);
			prsmt.setString(3, eventTime);
			prsmt.setString(4, mob);
			prsmt.setString(5, dist);
			prsmt.setString(6, state);
			
			prsmt.execute();
			
			System.out.println("Data Inserted Successfully.");
			
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pw.close();
		
	}

}
