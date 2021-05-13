package main.java.hello;

import java.sql.Connection;
import java.sql.DriverManager;

public class Driver {
	public static void main(String[] args) {
		try {
			Connection myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo?characterEncoding=utf-8","root","Codms0408!@");
		}
		catch(Exception exc) {
			exc.printStackTrace();
		
		}
	}
}
