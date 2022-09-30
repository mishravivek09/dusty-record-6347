package com.dusty_record_6347.usecases;

import java.util.List;
import java.util.Scanner;

import com.dusty_record_6347.exceptions.AdminException;
import com.dusty_record_6347.exceptions.EngineerException;
import com.dusty_record_6347.exceptions.WorkException;
import com.dusty_record_6347.model.Admin;
import com.dusty_record_6347.model.AllTasks;
import com.dusty_record_6347.model.Engineer;
import com.dusty_record_6347.model.Tasks;
import com.dusty_record_6347.repository.Repository;
import com.dusty_record_6347.repository.RepositoryImpl;

public class AdminUsecases {
	public String adminLogin() { 
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter username");
		String email=sc.next();
		System.out.println("Enter password");
		String passwd=sc.next();
		String message=null;
		Repository newUser=new RepositoryImpl();
		try {
			Admin admin=newUser.adminLogin(email, passwd);
			message="Welcome "+admin.getName();
		} catch (AdminException e) {
			message=e.getMessage();
		}
		return message;
	}
	
	public String addEnginner() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter engineer name :");
		String name=sc.next();
		System.out.println("Enter engineer email :");
		String email=sc.next();
		System.out.println("Enter engineer password :");
		String pass=sc.next();
		System.out.println("Enter engineer category :");
		String cat=sc.next();
		Repository addEngineer=new RepositoryImpl();
		
		Engineer engineer =new Engineer();
		
		engineer.setName(name);
		engineer.setEmail(email);
		engineer.setPassword(pass);
		engineer.setCategory(cat);
		
		String res=addEngineer.registerEngineer(engineer);
		return res;
	}
	
	public void getEngineers() {
		Repository getList=new RepositoryImpl();
		try {
			List<Engineer> list=getList.getListOfEngineer();
			list.forEach(e ->System.out.println(e));
		} catch (EngineerException e) {
			System.out.println(e.getMessage());
		}
	}
	public void deleteEngineer() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter engineers id :");
		int id=sc.nextInt();
		Repository delEngineer=new RepositoryImpl();
		String res=delEngineer.removeEngineer(id);
		System.out.println(res);
	}
	
	public void seeTasks() {
		Repository repo=new RepositoryImpl();
		try {
			List<AllTasks> list=repo.seeTasks();
			list.forEach(w-> System.out.println(w));
		} catch (WorkException e) {
			System.out.println(e.getMessage());
		}
	}
	public void assignTasks() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter engineer id to assign task");
		int eid=sc.nextInt();
		System.out.println("Enter task id that you want to assign engineer");
		int tid=sc.nextInt();
		Repository repo=new RepositoryImpl();
		String message=repo.assignTasks(eid, tid);
		System.out.println(message);
	}
}
