package com.dusty_record_6347.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dusty_record_6347.controller.Database;
import com.dusty_record_6347.exceptions.AdminException;
import com.dusty_record_6347.exceptions.EngineerException;
import com.dusty_record_6347.model.Admin;
import com.dusty_record_6347.model.Engineer;

public class RepositoryImpl implements Repository{

	@Override
	public Admin adminLogin(String email, String password) throws AdminException{
		Admin admin=null;
		
		Connection conn=Database.provideconnection();
		try {
			PreparedStatement ps=conn.prepareStatement("select * from admin where email=? AND password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				admin=new Admin(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getString("password"));
			}else {
				throw new AdminException("Invalid Username or password..");
			}
		} catch (SQLException e) {
			throw new AdminException(e.getMessage());
		}
		
		return admin;
	}

	@Override
	public String registerEngineer(Engineer engineer) {
		String message="Not Registerd";
		
		Connection conn=Database.provideconnection();
		try {
			PreparedStatement ps=conn.prepareStatement("insert into engineers(name,email,password,category) values(?,?,?,?)");
			ps.setString(1, engineer.getName());
			ps.setString(2, engineer.getEmail());
			ps.setString(3, engineer.getPassword());
			ps.setString(4, engineer.getCategory());
			int res=ps.executeUpdate();
			if(res>0) {
				message="Engineer added Successfully..";
			}
		} catch (SQLException e) {
			message=e.getMessage();
		}
		
		return message;
	}

	@Override
	public List<Engineer> getListOfEngineer() throws EngineerException {
		List<Engineer> list=new ArrayList<>();
		Connection conn=Database.provideconnection();
		try {
			PreparedStatement ps=conn.prepareStatement("select * from engineers");
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String email=rs.getString("email");
				String pass=rs.getString("password");
				String cat=rs.getString("category");
				
				Engineer engineer=new Engineer(id,name,email,pass,cat);
				list.add(engineer);
			}
		} catch (SQLException e) {
			throw new EngineerException(e.getMessage());
		}
		if(list.size()==0) {
			throw new EngineerException("Engineers not available");
		}
		
		return list;
	}

	@Override
	public String removeEngineer(int id) {
		String message="Didn't remove";
		
		Connection conn=Database.provideconnection();
		try {
			PreparedStatement ps=conn.prepareStatement("delete from engineers where id=?");
			ps.setInt(1, id);
			int x=ps.executeUpdate();
			if(x>0) {
				message="Employee deleted successfully..";
			}
		} catch (SQLException e) {
			message=e.getMessage();
		}
		
		return message;
	}

	@Override
	public String loginEngineer(String email, String password) {
		String str=null;
		
		Connection conn=Database.provideconnection();
		try {
			PreparedStatement ps=conn.prepareStatement("SELECT * FROM engineers WHERE email = ? AND password = ?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				str="login successful";
			}else {
				str="Invalid username or password";
			}
		} catch (SQLException e) {
			str=e.getMessage();
		}
		
		return str;
	}




}
