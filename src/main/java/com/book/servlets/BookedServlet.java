package com.book.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.servlets.Model.Dbdetails;
import com.book.servlets.Model.DetailsModel;



public class BookedServlet extends HttpServlet{
		
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
		{
			 String username="";
			 String password="";
			System.out.println("came to book servlet");
		String name=req.getParameter("name");	
		String email=req.getParameter("email");	
		String phone=req.getParameter("phone");	
		String from=req.getParameter("from");	
		String to=req.getParameter("to");	
		String gender=req.getParameter("gender");
		String seatno=req.getParameter("seat");
		String date=req.getParameter("date");
		System.out.println("i am in book servlet"+email);
//		UserRepository user=new UserRepository();
//		List<DetailsModel> userdetails=user.getuser();
//
//        System.out.println(userdetails);
//
//        if (userdetails != null && !userdetails.isEmpty()) {
//            // Accessing the first DetailsModel object in the list
//            DetailsModel details = userdetails.get(0);
//
// 
//            username = details.getUsername();
//            password = details.getPassword();
//
//            // Printing the values (or using them as needed)
//            System.out.println("Username: " + username);
//            System.out.println("Password: " + password);
//        } else {
//            System.out.println("No user details found.");
//        }
     // Fetch from Render Environment Variables
//        ServletContext context = getServletContext(); 
//        String urlsql = context.getInitParameter("url");
//        String usernamesql = context.getInitParameter("username");
//        String passwordsql = context.getInitParameter("password");
//
//        // Store in Object
//        Dbdetails db = new Dbdetails();
//        db.setUrl(urlsql);
//        db.setUsername(usernamesql);
//        db.setPassword(passwordsql);

		   	
//         UserRepository repo=new UserRepository();
//         repo.details(username,password,from,to,name,email,phone,gender,seatno,date,db);
         req.setAttribute("email",email);
         req.setAttribute("name",name);
         req.setAttribute("phone",phone);
         req.setAttribute("from",from);
         req.setAttribute("to",to);
         req.setAttribute("date",date);
         req.setAttribute("seat",seatno);
         req.setAttribute("gender",gender);
         System.out.println("jspp file");
         RequestDispatcher dispatcher = req.getRequestDispatcher("scanner.jsp");
 		dispatcher.forward(req, resp);
 		
         
		   	
		}

}
