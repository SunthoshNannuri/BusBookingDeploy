package com.book.servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import com.book.servlets.Model.Dbdetails;

public class TruncateServlet extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		ServletContext context = getServletContext(); 
        String urlsql = context.getInitParameter("url");
        String usernamesql = context.getInitParameter("username");
        String passwordsql = context.getInitParameter("password");

        // Store in Object
        Dbdetails db = new Dbdetails();
        db.setUrl(urlsql);
        db.setUsername(usernamesql);
        db.setPassword(passwordsql);
		UserRepository user=new UserRepository();
		String response=user.truncate(db);
		System.out.println(response);
		RequestDispatcher dispatch=req.getRequestDispatcher("vistagain.html");
		dispatch.forward(req, resp);
	}

}
