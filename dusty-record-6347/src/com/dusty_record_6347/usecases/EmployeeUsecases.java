package com.dusty_record_6347.usecases;

import java.util.List;
import java.util.Scanner;

import com.dusty_record_6347.exceptions.EmployeeException;
import com.dusty_record_6347.exceptions.WorkException;
import com.dusty_record_6347.model.Employee;
import com.dusty_record_6347.model.Tasks;
import com.dusty_record_6347.repository.Repository;
import com.dusty_record_6347.repository.RepositoryImpl;

public class EmployeeUsecases {
	public void registration() {
		Repository repo=new RepositoryImpl();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your name");
		String name=sc.next();
		System.out.println("Enter your email");
		String email=sc.next();
		System.out.println("Enter your password");
		String passwd=sc.next();
		
		Employee emp=new Employee();
		emp.setName(name);
		emp.setEmail(email);
		emp.setPassword(passwd);
		
		String m=repo.registerNewEmployee(emp);
		System.out.println("===================================================");
		System.out.println(m);
		System.out.println("===================================================");
	}
	
	public int login() {
		int id=0;
		Repository repo=new RepositoryImpl();
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter your email :");
		String email=sc.next();
		System.out.println("Enter your password");
		String pass=sc.next();
		try {
			Employee emp=repo.loginEmployee(email, pass);
			System.out.println("===================================================");
			System.out.println("Welcome "+emp.getName());
			System.out.println("===================================================");
			id=emp.getId();
		} catch (EmployeeException e) {
			System.out.println("===================================================");
			System.out.println(e.getMessage());
			System.out.println("===================================================");
		}
		return id;
	}
	
	public void registerComplain(int id) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter complain category ( Hardware / Software )");
		String cat=sc.next();
		Repository repo=new RepositoryImpl();
		try {
			int complainId=repo.registerComplain(id, cat);
			System.out.println("===================================================");
			System.out.println("Your complain id is : "+complainId);
			System.out.println("===================================================");
		} catch (WorkException e) {
			System.out.println("===================================================");
			System.out.println(e.getMessage());
			System.out.println("===================================================");
		}
	}
	public void changePasswd(int id) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter a new Password : ");
		String pass=sc.next();
		Repository repo=new RepositoryImpl();
		try {
			String m=repo.changePasswd(id, pass);
			System.out.println("===================================================");
			System.out.println(m);
			System.out.println("===================================================");
		} catch (EmployeeException e) {
			System.out.println("===================================================");
			System.out.println(e.getMessage());
			System.out.println("===================================================");
		}
	}
	
	public void viewAllComplain(int id) {
		Repository repo=new RepositoryImpl();
		try {
			List<Tasks> list=repo.viewAllComplain(id);
			System.out.println("===================================================");
			list.forEach(l-> System.out.println(l));
			System.out.println("===================================================");
		} catch (WorkException e) {
			System.out.println("===================================================");
			System.out.println(e.getMessage());
			System.out.println("===================================================");
		}
	}
	
	public void viewStatus() {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter complain id : ");
		int id=sc.nextInt();
		Repository repo=new RepositoryImpl();
		try {
			List<Tasks> list=repo.viewStatus(id);
			System.out.println("===================================================");
			System.out.println(list);
			System.out.println("===================================================");
		} catch (WorkException e) {
			System.out.println("===================================================");
			System.out.println(e.getMessage());
			System.out.println("===================================================");
		}
	}
}

