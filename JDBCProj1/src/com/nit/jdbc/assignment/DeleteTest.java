//delete the student data from student table based on student no

package com.nit.jdbc.assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) {
		Scanner scn = null;
		Connection con = null;
		Statement st = null;
		try {
			//read inputs
			scn = new Scanner(System.in);
			int sno=0;
			if(scn!=null) {
				System.out.print("Enter the student no : ");
				sno=scn.nextInt();
			}//if
		
			//register the jdbc driver
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//create connection obj
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","IMIT","IMIT");
			//create statement object
			if(con!=null)
				st = con.createStatement();
			//prepare sql query
			//delete from student where sno=101;
			String query="DELETE FROM STUDENT WHERE SNO="+sno;
			
			//send and execute sql query
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			//process the result
			if(count==0)
				System.out.println("No records found to delete");
			else
				System.out.println("no. of records are effected : "+count);
		}//try
		catch (SQLException se) {
			//if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				//System.out.println("Invalide col name or table name or sql Keywords");
			se.printStackTrace();
		}
		catch(Exception e) {
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