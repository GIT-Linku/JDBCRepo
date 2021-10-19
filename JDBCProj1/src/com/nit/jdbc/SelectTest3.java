package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest3 {

	public static void main(String[] args) {
		System.out.println("SelectTest3.main()");
		Scanner scn=null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//read inputs
			scn = new Scanner(System.in);
			String initChar = null;
			if(scn!=null) {
				System.out.print("Enter the first Character of employee name : ");
				initChar = scn.next();
			}
			//convert input value as required for the SQL query
			initChar = "'"+initChar+"%'"; //gives 'a%'
			
			//register the JDBC driver by loading JDBC driver class
		//  Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the coonection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","IMIT","IMIT");
			
			//create statement object
			if(con!=null)
				st = con.createStatement();
			//prepare SQL query
			
			//select empno,ename,job,sal from emp where ename like's%';
			String query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE "+initChar;
			System.out.println(query);
			
			//send and execute SQL query in DB s/w
			if(st!=null)
				rs = st.executeQuery(query);
			
			//process the result set
			if(rs!=null) {
				boolean flag = false;
				while (rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));					
				}//while
				
				if(flag==false)
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
			//close jdbc objs
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
			}//finally
		}//main

	}//class

}
