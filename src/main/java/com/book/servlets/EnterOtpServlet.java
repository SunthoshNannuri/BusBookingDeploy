package com.book.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.servlets.Model.Dbdetails;
import com.book.servlets.Model.DetailsModel;
import com.book.servlets.Model.GetAllModel;

public class EnterOtpServlet extends HttpServlet{
	
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
		
		int otp=Integer.parseInt(req.getParameter("otp"));
		int emailotp=Integer.parseInt(req.getParameter("emailotp"));
		if(otp==emailotp)
		{
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
	        
			System.out.println("otp verified");
			UserRepository user2=new UserRepository();
			List<GetAllModel> listofemp=user2.getAllData(username, password,db);
			System.out.println("i am getALlData"+listofemp);
			req.setAttribute("otpVerified", true);
			req.setAttribute("list", listofemp);
			RequestDispatcher dispatcher = req.getRequestDispatcher("datarecive.jsp");
	 		dispatcher.forward(req, resp);
			
		}
		else {
		    System.out.println("Wrong OTP");
		    resp.setContentType("text/html"); 

		    PrintWriter out = resp.getWriter();
		    out.println("<html><body>");
		    out.println("<script type='text/javascript'>");
		    out.println("alert(\"OTP Is Wrong.\");"); 
		    out.println("window.location.href = 'enterotptovalidate.jsp';");
		    out.println("</script>");
		    out.println("</body></html>");

		    out.flush();
		    out.close();
		}

	}

}
