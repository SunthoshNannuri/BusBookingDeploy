package com.book.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.servlets.Model.Dbdetails;
import com.book.servlets.Model.DetailsModel;


public class LoginServletss extends HttpServlet{
	
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
    
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String fromloc=req.getParameter("from");
		String toloc=req.getParameter("to");
		System.out.println("from"+fromloc);
		System.out.println("to"+toloc);
		resp.setContentType("text/html");
		UserRepository repo=new UserRepository();
		String response=repo.checklogin(username, password,db);
		System.out.println(response);//yes
		
		if(username.equals("admin") && password.equals("admin@123"))
		{
			RequestDispatcher dispatcher = req.getRequestDispatcher("unblock.html");
	 		dispatcher.forward(req, resp);
		}
		else if(response.equals("yes"))
		{
			System.out.println("i am in loginservlet");
			System.out.println("jsp");
			DetailsModel d=new DetailsModel();
			d.setUsername(username);
			d.setPassword(password);

			RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
	 		dispatcher.forward(req, resp);
			
			
		}
		else if(response.equals("no")) 
		{
		    System.out.println("Create An account");
		    resp.setContentType("text/html");
		    
		    PrintWriter out = resp.getWriter();
		    out.println("<html><body>");
		    out.println("<script type='text/javascript'>");
		    out.println("alert(\"You Don't Have An Account. Please Register\");");
		    out.println("window.location.href = 'Signup.html';");
		    out.println("</script>");
		    out.println("</body></html>");
		    
		    out.flush();
		    out.close();
		}

		
} 

}
