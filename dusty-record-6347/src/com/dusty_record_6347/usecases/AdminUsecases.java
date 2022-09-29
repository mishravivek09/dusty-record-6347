package com.dusty_record_6347.usecases;

import java.util.List;
import java.util.Scanner;

import com.dusty_record_6347.exceptions.AdminException;
import com.dusty_record_6347.exceptions.EngineerException;
import com.dusty_record_6347.model.Admin;
import com.dusty_record_6347.model.Engineer;
import com.dusty_record_6347.repository.Repository;
import com.dusty_record_6347.repository.RepositoryImpl;

public class AdminUsecases {
	public void adminLogin() {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter email id : ");
		String email=sc.next();
		System.out.print("Enter password : ");
		String passwd=sc.next();
		sc.close();
		Repository new_user=new RepositoryImpl();
		try {
			Admin admin=new_user.adminLogin(email, passwd);
			System.out.println("Welcome "+admin.getName());
		} catch (AdminException e) {
			System.out.println(e.getMessage());
		}
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
			e.printStackTrace();
		}
	}
	public void deleteEngineer() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter engineers id :");
		int id=sc.nextInt();
		Repository delEngineer=new RepositoryImpl();
		String res=delEngineer.removeEngineer(id);
		System.out.println(res);
		sc.close();
	}
}
