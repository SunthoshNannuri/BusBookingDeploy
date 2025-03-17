package com.book.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import com.book.servlets.Model.LoginModel;
import com.book.servlets.Model.AllInfoModel;
import com.book.servlets.Model.Dbdetails;
import com.book.servlets.Model.DetailsModel;
import com.book.servlets.Model.GetAllModel;

public class UserRepository {
	public String checklogin(String username, String password, Dbdetails db) {
		System.out.println("i am in checklogin");
		String query = "SELECT * FROM logindata WHERE username = ? AND password = ?";
		String query3 = "INSERT INTO duplicatetable(username, password) VALUES(?, ?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(db.getUrl(), db.getUsername(), db.getPassword());
			PreparedStatement pr = con.prepareStatement(query);
			pr.setString(1, username);
			pr.setString(2, password);
			ResultSet result = pr.executeQuery();
			if (result.next()) {
				System.out.println("Login successful");
				PreparedStatement pr3 = con.prepareStatement(query3);
				pr3.setString(1, username);
				pr3.setString(2, password);
				pr3.executeUpdate();
				return "yes";
			} else {
				System.out.println("Incorrect credentials");
				return "no";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "no";
	}

	public String Signup(String username, String email, String password, int age, Dbdetails db) {
		String query = "insert into logindata values(?,?,?,?)";
		System.out.println("i came to signup method");
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(db.getUrl(), db.getUsername(), db.getPassword());

			PreparedStatement pr = con.prepareStatement(query);
			// step 4 execution
			pr.setString(1, username);
			pr.setString(2, email);
			pr.setString(3, password);
			pr.setInt(4, age);
			pr.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return "yes";

	}

	public String details(String username, String password, String from, String to, String name,
			 String email, String number,String gender,String seatno,String date,Dbdetails db) {
		try {
			
			// SQL query with placeholders (?)
			String query = "INSERT INTO dupli(username, password, fromloc, toloc, name, email, number, gender, seatNo,date) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

			// Load MySQL driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the database connection
			Connection con = DriverManager.getConnection(db.getUrl(), db.getUsername(), db.getPassword());

			// Prepare the statement
			PreparedStatement pr = con.prepareStatement(query);

			// Set values for each placeholder (?)
			pr.setString(1, username); // username
			pr.setString(2, password); // password
			pr.setString(3, from); // fromloc
			pr.setString(4, to); // toloc
			pr.setString(5, name); // name
			pr.setString(6, email); // email
			pr.setString(7, number); // number
			pr.setString(8, gender); // gender
			pr.setString(9, seatno); // seatNo
			pr.setString(10,date);
			// Execute the update
			pr.executeUpdate();
			System.out.println("Record inserted successfully");

		} catch (Exception e) {
			// Handle exceptions
			System.out.println(e);
		}

		return "yes";
	}

	public List<DetailsModel> getuser(Dbdetails db) {

		List<DetailsModel> list = new ArrayList<DetailsModel>();
		String query = "select * from duplicatetable";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(db.getUrl(), db.getUsername(), db.getPassword());
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				DetailsModel emp = new DetailsModel();
				emp.setUsername(rs.getString("username"));
				emp.setPassword(rs.getString("password"));
				list.add(emp);

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return list;

	}

	public List<AllInfoModel> AllInfo(String username, String password) {
		String query = "select * from dupli where password='" + username + "' and gender='" + password + "'";
		List<AllInfoModel> list = new ArrayList<AllInfoModel>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql:///busbooking", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				AllInfoModel all = new AllInfoModel();
				all.setUsername(rs.getString("username"));
				all.setPassword(rs.getString("password"));
				all.setFromloc(rs.getString("fromloc"));
				all.setToloc(rs.getString("toloc"));
				all.setEmail(rs.getString("email"));
				all.setNumber(rs.getString("number"));
				all.setName(rs.getString("name"));
				list.add(all);

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return list;

	}

	public String truncate(Dbdetails db) {
		String query = "truncate duplicatetable";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(db.getUrl(), db.getUsername(), db.getPassword());
			PreparedStatement pr = con.prepareStatement(query);
			pr.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return "yes";

	}

	public List<GetAllModel> getAllData(String username,String password, Dbdetails db) {
		List<GetAllModel> list = new ArrayList<GetAllModel>();
		String query = "select * from dupli where username='" + username + "' and password='" + password + "'";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(db.getUrl(), db.getUsername(), db.getPassword());			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				GetAllModel gall = new GetAllModel();
				gall.setUsername(rs.getString("username"));
				gall.setPassword(rs.getString("password"));
				gall.setFromloc(rs.getString("fromloc"));
				gall.setToloc(rs.getString("toloc"));
				gall.setName(rs.getString("name"));
				gall.setEmail(rs.getString("email"));
				gall.setNumber(rs.getString("number"));
				gall.setGender(rs.getString("gender"));
				gall.setSeatno(rs.getString("seatNo"));
				gall.setDate(rs.getString("date"));
				list.add(gall);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return list;
	}

}