package com.student.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class SqlUtil {
	static Statement stmt;
	static Connection con;
	static public void Connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con =DriverManager.getConnection("jdbc:mysql://localhost:3306/school","root","cdac");
		stmt = con.createStatement();
	}
	static public int insert(String qry) throws SQLException {
		int rs=-1;
		if(qry!="") {
			rs= stmt.executeUpdate(qry);
		}
		return rs;
		
	}
	static public int update (String qry) throws SQLException {
		int rs=-1;
		if(qry!="") {
			rs= stmt.executeUpdate(qry);
		}
		return rs;
	} 
	static public int delete(String qry) throws SQLException {
		int rs=-1;
		if(qry!="") {
			rs= stmt.executeUpdate(qry);
		}
		return rs;
	}
	
	static public ResultSet fetch(String qry) throws SQLException {
		ResultSet rs = null;   //select * from student where id = $id;
		if(qry!="") {
			rs = stmt.executeQuery(qry);
		}
		return rs;
	}
	
	static public void close() throws SQLException {
		if(con!=null && stmt != null) {
			stmt.close();
			con.close();
		}
	}
	
}
