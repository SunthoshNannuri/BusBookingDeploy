package com.book.servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import com.book.servlets.Model.AllInfoModel;
import com.book.servlets.Model.Dbdetails;
import com.book.servlets.Model.DetailsModel;
import com.book.servlets.Model.GetAllModel;

public class ViewServlet extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext context = getServletContext(); 
        String urlsql = context.getInitParameter("url");
        String usernamesql = context.getInitParameter("username");
        String passwordsql = context.getInitParameter("password");

        // Store in Object
        Dbdetails db = new Dbdetails();
        db.setUrl(urlsql);
        db.setUsername(usernamesql);
        db.setPassword(passwordsql);
		
		UserRepository user = new UserRepository();
		List<DetailsModel> userdetails = user.getuser(db);

		System.out.println(userdetails);
		String username = "";
		String password = "";
		if (userdetails != null && !userdetails.isEmpty()) {
			// Accessing the first DetailsModel object in the list
			DetailsModel details = userdetails.get(0);

			username = details.getUsername();
			password = details.getPassword();

			// Printing the values (or using them as needed)
			System.out.println("Username: " + username);
			System.out.println("Password: " + password);
		} else {
			System.out.println("No user details found.");
		}
		
        
		UserRepository user1 = new UserRepository();
		List<GetAllModel> allinfo = user.getAllData(username, password,db);
		req.setAttribute("list", allinfo);
		System.out.println(allinfo);
		RequestDispatcher dispatche = req.getRequestDispatcher("view.jsp");
		dispatche.forward(req, resp);
	}

}
