package com.studentDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.student.model.StudentModel;
import com.student.util.SqlUtil;
import com.studentDAO.StudentDAO;

public class StudentDAOImpl implements StudentDAO {

	@Override
	public int save(StudentModel std) {
		int rs = -1;
		try {
			
			SqlUtil.Connect();
			String qry = "insert into student values("+std.getId()+",'"+std.getName()+"',"+std.getMarks()+","+std.getNum()+");";
			rs = SqlUtil.insert(qry);
			SqlUtil.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		return rs;
	}

	@Override
	public List<StudentModel> getAll() {
		List<StudentModel> stdList = new ArrayList<StudentModel>();
		try {
			SqlUtil.Connect();
			String qry = "select * from student;";
			ResultSet rs = SqlUtil.fetch(qry);
			if(rs!=null) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString(2);
					float marks = rs.getFloat(3);
					int num = rs.getInt(4);
					StudentModel std = new StudentModel(id,name,marks,num);
					stdList.add(std);
				}
			}
			SqlUtil.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stdList;
	}

	@Override
	public StudentModel getById(int id) {
		StudentModel std = null;
		try {
			SqlUtil.Connect();
			String qry = "select * from student where id ="+id+";";
			ResultSet rs = SqlUtil.fetch(qry);
			if(rs!=null) {
				while(rs.next()) {
					int ud = rs.getInt("id");
					String name = rs.getString(2);
					float marks = rs.getFloat(3);
					int num = rs.getInt(4);
					std = new StudentModel(ud,name,marks,num);
					
				}
			}
			SqlUtil.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return std;
	}

	

	@Override
	public int remove(int id) {
		int rs = -1;
		try {
			SqlUtil.Connect();
			String qry = "delete from student where id ="+id;
			rs = SqlUtil.delete(qry);
			SqlUtil.close();
			
		} catch (ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
		}		
		return rs;
	}

	@Override
	public int update(int id, StudentModel std) {
		int rs =-1;
		try {
			SqlUtil.Connect();
			String qry = "update student set name='"+std.getName()+"', marks='"+std.getMarks()+"', xyz ='"+std.getNum()+"' where id ='"+id+"';";
			rs = SqlUtil.update(qry);
			SqlUtil.close();
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}

}
