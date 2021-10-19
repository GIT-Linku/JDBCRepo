package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest4 {

	public static void main(String[] args) {
		System.out.println("SelectTest4.main()");
		Scanner scn = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//read inputs
			scn = new Scanner(System.in);
			int dno = 0;
			if(scn!=null) {
				System.out.println("Enter the depp no. : ");
				dno = scn.nextInt();
			}//if
			//load jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");

			//establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","IMIT","IMIT");

			//create JDBC statement object
			if(con!=null)
				st = con.createStatement();

			//prepare SQL query
			//select * from dept where deptno=10;
			String query = "SELECT DEPTNO,DNAME,LOC FROM DEPT WHERE DEPTNO="+dno;
			System.out.println(query);

			//send and execute the query
			if(st!=null)
				rs = st.executeQuery(query);

			//process the resultset
			if(rs!=null) {
				if(rs.next())
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				else
					System.out.println("No record found");
			}//if
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close JDBC objs
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
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
