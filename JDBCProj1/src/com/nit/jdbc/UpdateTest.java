package com.nit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {
		Scanner scn = null;
		Connection con = null;
		Statement st = null;

		try {
			scn = new Scanner(System.in);
			String newName=null,newAdd=null;
			float newAvg=0.0f;
			int no = 0;
			if(scn!=null) {
				System.out.print("Enter the name of the student : ");
				newName=scn.next();
				System.out.print("Enter the new address : ");
				newAdd = scn.next();
				System.out.print("Enter the new Avg : ");
				newAvg = scn.nextFloat();
				System.out.print("Enter the student no : ");
				no = scn.nextInt();
			}
			//convert input values as required SQL query
			newName = "'"+newName+"'";
			newAdd = "'"+newAdd+"'";

			//load JDBC driver
			//Class.forName("oracle.jdbc.driver.OracleDriver");

			//establish connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","IMIT","IMIT");

			//create statement object
			if(con!=null)
				st = con.createStatement();
			//prepare SQL query
			//String query = "update student set sname='avi',sadd='hyd',avg='43.5' where sno = 101 ";
			String query = "UPDATE STUDENT SET SNAME="+newName+",SADD="+newAdd+",AVG="+newAvg+" WHERE SNO="+no;
			System.out.println(query);

			//send and execute query
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			//process the result
			if(count==0)
				System.out.println("No record found to update");
			else
				System.out.println("no. of record affected : "+count);

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
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