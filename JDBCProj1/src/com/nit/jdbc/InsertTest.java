package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {

	public static void main(String[] args) {
		Scanner scn = null;
		Connection con = null;
		Statement st = null;
		try {
			String name = null,addrs = null;
			float avg = 0.0f;
			int no = 0;
			//read inputs
			scn = new Scanner(System.in);
			if(scn!=null) {
				System.out.print("Enter the student no : ");
				no = scn.nextInt();
				System.out.print("Enter the student name : ");
				name = scn.next();
				System.out.print("Enter the student address : ");
				addrs = scn.next();
				System.out.print("Enter the avg : ");
				avg = scn.nextFloat();
			}
			//convert the input that required for SQL query
			name = "'"+name+"'";
			addrs = "'"+addrs+"'";
			
			//load JDBC driver
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","IMIT","IMIT");
			
			//create the statement object
			if(con!=null)
				st = con.createStatement();
			
			//prepare SQL query
			String query ="INSERT INTO STUDENT VALUES("+no+","+name+","+addrs+","+avg+")";
			
			//execute the query
			int count=0;
			if(st!=null)
				count =st.executeUpdate(query);
			//process the result
			if(count==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record inserted");
				
		}//try
		catch (SQLException se) {
			if(se.getErrorCode()==1)
				System.out.println("Duplicate cannot inserted to PK column");
			if(se.getErrorCode()==1400)
				System.out.println("Null can not inserted to PK column");
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("invalid col names or table names or SQL query");
			if(se.getErrorCode()==12899)
				System.out.println("value too large for column");
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close JDBC objs
			try {
				if(st!=null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(scn!=null)
					scn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//finally

	}//main

}//class
