package com.dusty_record_6347.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dusty_record_6347.controller.Database;
import com.dusty_record_6347.exceptions.AdminException;
import com.dusty_record_6347.exceptions.EmployeeException;
import com.dusty_record_6347.exceptions.EngineerException;
import com.dusty_record_6347.exceptions.WorkException;
import com.dusty_record_6347.model.Admin;
import com.dusty_record_6347.model.AllTasks;
import com.dusty_record_6347.model.Employee;
import com.dusty_record_6347.model.Engineer;
import com.dusty_record_6347.model.Tasks;

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
				message="Engineer removed successfully..";
			}
		} catch (SQLException e) {
			message=e.getMessage();
		}
		
		return message;
	}


	@Override
	public List<AllTasks> seeTasks() throws WorkException {
		List<AllTasks> list=new ArrayList<>();
		Connection conn=Database.provideconnection();
		try {
			PreparedStatement ps=conn.prepareStatement("select * from tasks");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int wid=rs.getInt("task_id");
				String cat=rs.getString("category");
				String status=rs.getString("status");
				int enid=rs.getInt("engid");
				int emid=rs.getInt("emid");
				AllTasks task=new AllTasks(wid,status,cat,enid,emid);
				list.add(task);
			}
		} catch (SQLException e) {
			throw new WorkException(e.getMessage());
		}
		if(list.size()==0) {
			throw new WorkException("No Tasks Today..!");
		}
		
		return list;
	}

	@Override
	public String assignTasks(int engid,int taskid){
		String message="Tasks not assigned";
		Connection conn=Database.provideconnection();
		try {
			PreparedStatement ps=conn.prepareStatement("update tasks set engid=? where task_id=?");
			ps.setInt(1, engid);
			ps.setInt(2, taskid);
			int x=ps.executeUpdate();
			if(x>0) {
				message="Task assigned successfully";
			}
		} catch (SQLException e) {
			message=e.getMessage();
		}
		
		return message;
	}

	@Override
	public Engineer loginEngineer(String email, String password) throws EngineerException {
		Engineer eng=null;
		
		Connection conn=Database.provideconnection();
		try {
			PreparedStatement ps=conn.prepareStatement("SELECT * FROM engineers WHERE email = ? AND password = ?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				eng=new Engineer(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getString("password"),rs.getString("category"));
			}else {
				throw new EngineerException("Invalid username or password");
			}
		} catch (SQLException e) {
			throw new EngineerException(e.getMessage());
		}
		
		return eng;
	}

	@Override
	public List<Tasks> viewTask(int empid) throws WorkException {
		List<Tasks> list=new ArrayList<>();
		Connection conn=Database.provideconnection();
		try {
			PreparedStatement ps=conn.prepareStatement("select t.task_id,t.status,e.name,e.category,em.name  from engineers e INNER JOIN employees em INNER JOIN tasks t ON t.emid=em.id AND e.id=t.engid AND e.id=?");
			ps.setInt(1, empid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("task_id");
				String s=rs.getString("status");
				String cat=rs.getString("category");
				String en=rs.getString("e.name");
				String em=rs.getString("em.name");
				Tasks tasks=new Tasks(id,cat,s,en,em);
				list.add(tasks);
				
			}
		} catch (SQLException e) {
			throw new WorkException(e.getMessage());
		}
		
		if(list.size()==0) {
			throw new WorkException("No tasks founds");
		}
		return list;
	}

	@Override
	public String updateStatus(int id,int eid,String status) throws WorkException {
		String message="Wrong task id";
		Connection conn=Database.provideconnection();
		try {
			PreparedStatement ps=conn.prepareStatement("update tasks set status=? where task_id=? AND engid=?");
			ps.setString(1, status);
			ps.setInt(2, id);
			ps.setInt(3, eid);
			int x=ps.executeUpdate();
			if(x>0) {
				message="Task status updated successfully..";
			}
		} catch (SQLException e) {
			message=e.getMessage();
		}
		
		return message;
	}

	@Override
	public String updatePasswd(int id,String pass) throws EngineerException {
		String message="Password didn't updated";
		Connection conn=Database.provideconnection();
		try {
			PreparedStatement ps=conn.prepareStatement("update engineers set password=? where id=?");
			ps.setString(1, pass);
			ps.setInt(2, id);
			int x=ps.executeUpdate();
			if(x>0) {
				message="Password updated successfully..";
			}
		} catch (SQLException e) {
			message=e.getMessage();
		}
		
		return message;
	}

	@Override
	public List<AllTasks> viewAttended(int id) throws WorkException {
		List<AllTasks> list=new ArrayList<>();
		Connection conn=Database.provideconnection();
		try {
			PreparedStatement ps=conn.prepareStatement("select * from tasks where engid=? AND status!='Pending'");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int tid=rs.getInt("task_id");
				String s=rs.getString("status");
				String cat=rs.getString("category");
				int en=rs.getInt("engid");
				int em=rs.getInt("emid");
				AllTasks task=new AllTasks(tid,cat,s,en,em);
				list.add(task);
			}
		} catch (SQLException e) {
			throw new WorkException(e.getMessage());
		}
		if(list.size()==0) {
			throw new WorkException("Your work list not exist");
		}
		
		return list;
	}

	@Override
	public List<AllTasks> viewPending(int id) throws WorkException {
		List<AllTasks> list=new ArrayList<>();
		Connection conn=Database.provideconnection();
		try {
			PreparedStatement ps=conn.prepareStatement("select * from tasks where engid=? AND status='Pending'");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int tid=rs.getInt("task_id");
				String s=rs.getString("status");
				String cat=rs.getString("category");
				int en=rs.getInt("engid");
				int em=rs.getInt("emid");
				AllTasks task=new AllTasks(tid,cat,s,en,em);
				list.add(task);
			}
		} catch (SQLException e) {
			throw new WorkException(e.getMessage());
		}
		if(list.size()==0) {
			throw new WorkException("Your work list not exist");
		}
		
		return list;
	}

	@Override
	public String registerNewEmployee(Employee employee) {
		String message="Not Registerd";
		
		Connection conn=Database.provideconnection();
		try {
			PreparedStatement ps=conn.prepareStatement("insert into employees(name,email,password) values(?,?,?)");
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getEmail());
			ps.setString(3, employee.getPassword());
			int res=ps.executeUpdate();
			if(res>0) {
				message="Signup Successfully..";
			}
		} catch (SQLException e) {
			message=e.getMessage();
		}
		
		return message;
	}

	@Override
	public Employee loginEmployee(String email, String password) throws EmployeeException {
		Employee employee=null;
		
		Connection conn=Database.provideconnection();
		try {
			PreparedStatement ps=conn.prepareStatement("select * from employees where email=? AND password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				employee=new Employee(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getString("password"));
			}else {
				throw new EmployeeException("Invalid Username or password..");
			}
		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}
		return employee;
		
	}

	@Override
	public int registerComplain(int id, String category) throws WorkException {
		int complainID=0;
		
		Connection conn=Database.provideconnection();
		try {
			PreparedStatement ps=conn.prepareStatement("insert into tasks(category,emid) values(?,?)");
			ps.setString(1, category);
			ps.setInt(2, id);
			int x=ps.executeUpdate();
			if(x>0) {
				PreparedStatement ps1=conn.prepareStatement("select * from tasks order by task_id desc limit 1");
				ResultSet rs=ps1.executeQuery();
				if(rs.next()) {
					complainID=rs.getInt("task_id");
				}
			}else {
				throw new WorkException("No complain found");
			}
		} catch (SQLException e) {
			throw new WorkException(e.getMessage());
		}
		
		return complainID;
	}

	@Override
	public List<Tasks> viewStatus(int id) throws WorkException {
		List<Tasks> list=new ArrayList<>();
		Connection conn=Database.provideconnection();
		try {
			PreparedStatement ps=conn.prepareStatement("select t.task_id,t.category,t.status,e.name,em.name from tasks t INNER JOIN employees em INNER JOIN engineers e ON e.id=t.engid AND t.emid=em.id AND t.task_id=?");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int ID=rs.getInt("task_id");
				String s=rs.getString("status");
				String cat=rs.getString("category");
				String en=rs.getString("e.name");
				String em=rs.getString("em.name");
				Tasks tasks=new Tasks(ID,cat,s,en,em);
				list.add(tasks);
				
			}
		} catch (SQLException e) {
			throw new WorkException(e.getMessage());
		}
		
		if(list.size()==0) {
			throw new WorkException("No record found");
		}
		return list;
	}

	@Override
	public List<Tasks> viewAllComplain(int empid) throws WorkException {
		List<Tasks> list=new ArrayList<>();
		boolean flag=false;
		Connection conn=Database.provideconnection();
		try {
			PreparedStatement ps=conn.prepareStatement("select t.task_id,t.status,e.name,e.category,em.name  from engineers e INNER JOIN employees em INNER JOIN tasks t ON t.emid=em.id AND e.id=t.engid AND em.id=?");
			ps.setInt(1, empid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				flag=true;
				int id=rs.getInt("task_id");
				String s=rs.getString("status");
				String cat=rs.getString("category");
				String en=rs.getString("e.name");
				String em=rs.getString("em.name");
				Tasks tasks=new Tasks(id,cat,s,en,em);
				list.add(tasks);
				
			}
		} catch (SQLException e) {
			throw new WorkException(e.getMessage());
		}
		
		if(!flag) {
			throw new WorkException("No record found");
		}
		return list;
	}

	@Override
	public String changePasswd(int id, String pass) throws EmployeeException {
		String message="Password didn't updated";
		Connection conn=Database.provideconnection();
		try {
			PreparedStatement ps=conn.prepareStatement("update employees set password=? where id=?");
			ps.setString(1, pass);
			ps.setInt(2, id);
			int x=ps.executeUpdate();
			if(x>0) {
				message="Password changed successfully..";
			}
		} catch (SQLException e) {
			message=e.getMessage();
		}
		
		return message;
	}

}
