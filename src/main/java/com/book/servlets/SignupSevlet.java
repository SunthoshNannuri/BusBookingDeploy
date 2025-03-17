package com.book.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.servlets.Eceptions.InvalidAgeException;
import com.book.servlets.Eceptions.InvalidPasswordException;
import com.book.servlets.Model.Dbdetails;

public class SignupSevlet extends HttpServlet {

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
        
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		int age = Integer.parseInt(req.getParameter("age"));
		PrintWriter out = resp.getWriter();
		try {
			validateAge(age);
			validatePassword(password);
			
			UserRepository repo = new UserRepository();
			String response = repo.Signup(username, email, password, age, db);
			
           
			if (response == "yes") {
				out.println("<html><body>");
				out.println("<script type='text/javascript'>");
				out.println("alert('Your Account is Created');");
				out.println("window.location.href = 'login.html';");
				out.println("</script>");
				out.println("</body></html>");
			} else {
				out.print("Not Created");
				
			}

		} catch (InvalidAgeException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			out.println("<html><body>");
			out.println("<script type='text/javascript'>");
			out.println("alert('Age Should Be Greater Than 18');");
			out.println("window.location.href = 'Signup.html';");
			out.println("</script>");
			out.println("</body></html>");
		} catch (InvalidPasswordException e) {
			System.out.println(e.getMessage());
//			resp.sendRedirect("Siginup.html");
			out.println("<html><body>");
			out.println("<script type='text/javascript'>");
			out.println("alert('Enter Min 8 Characters');");
			out.println("window.location.href = 'Signup.html';");
			out.println("</script>");
			out.println("</body></html>");
			e.printStackTrace();
		}
	}

	private void validatePassword(String password) throws InvalidPasswordException {
		if (password.length() < 8) {
			throw new InvalidPasswordException("Enter Min 8 Characters");
		}

	}

	private void validateAge(int age) throws InvalidAgeException {
		if (age <= 18) {

			throw new InvalidAgeException("Age Should Be Greater Than 18");
		} else {
			System.out.println("ok age");
		}

	}

}
