package com.dusty_record_6347.repository;

import java.util.List;

import com.dusty_record_6347.exceptions.AdminException;
import com.dusty_record_6347.exceptions.EmployeeException;
import com.dusty_record_6347.exceptions.EngineerException;
import com.dusty_record_6347.exceptions.WorkException;
import com.dusty_record_6347.model.Admin;
import com.dusty_record_6347.model.AllTasks;
import com.dusty_record_6347.model.Employee;
import com.dusty_record_6347.model.Engineer;
import com.dusty_record_6347.model.Tasks;

public interface Repository {
	
	//HOD methods...
	
	public Admin adminLogin(String email,String password)throws AdminException;
	public String registerEngineer(Engineer engineer);
	public List<Engineer> getListOfEngineer()throws EngineerException;
	public String removeEngineer(int id);
	public List<AllTasks> seeTasks()throws WorkException;
	public String assignTasks(int engid,int taskid);
	
	//Engineer methods..
	
	public Engineer loginEngineer(String email,String password)throws EngineerException;
	public List<Tasks> viewTask(int empid) throws WorkException;
	public String updateStatus(int id,int eid,String status)throws WorkException;
	public List<AllTasks> viewAttended(int id)throws WorkException;
	public String updatePasswd(int id,String pass)throws EngineerException;
	public List<AllTasks> viewPending(int id)throws WorkException;
	
	//Employee methods
	
	public String registerNewEmployee(Employee employee);
	public Employee loginEmployee(String email,String password)throws EmployeeException;
	public int registerComplain(int id, String category) throws WorkException;
	public List<Tasks> viewStatus(int id)throws WorkException;
	public List<Tasks> viewAllComplain(int empid) throws WorkException;
	public String changePasswd(int id,String pass)throws EmployeeException;
}
