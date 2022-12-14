package com.dusty_record_6347.usecases;

import java.util.List;
import java.util.Scanner;

import com.dusty_record_6347.exceptions.EngineerException;
import com.dusty_record_6347.exceptions.WorkException;
import com.dusty_record_6347.model.AllTasks;
import com.dusty_record_6347.model.Engineer;
import com.dusty_record_6347.model.Tasks;
import com.dusty_record_6347.repository.Repository;
import com.dusty_record_6347.repository.RepositoryImpl;

public class EngineerUseCases {
	public int loginEng() {
		int ID=0;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter email id :");
		String email=sc.next();
		System.out.println("Enter password :");
		String pass=sc.next();
		Repository repo=new RepositoryImpl();
		Engineer eng;
		try {
			eng = repo.loginEngineer(email, pass);
			System.out.println("===================================================");
			System.out.println("Welcome "+eng.getName());
			System.out.println("===================================================");
			ID=eng.getId();
		} catch (EngineerException e) {
			System.out.println(e.getMessage());
		}
		return ID;
	}
	public void viewTask(int id) {
		Repository repo=new RepositoryImpl();
		try {
			List<Tasks> list=repo.viewTask(id);
			System.out.println("===================================================");
			list.forEach(t-> System.out.println(t));
			System.out.println("===================================================");
		} catch (WorkException e) {
			System.out.println("===================================================");
			System.out.println(e.getMessage());
			System.out.println("===================================================");
		}
	}
	public void pending(int id) {
		Repository repo=new RepositoryImpl();
		try {
			List<AllTasks>list=repo.viewPending(id);
			System.out.println("===================================================");
			list.forEach(l-> System.out.println(l));
			System.out.println("===================================================");
		} catch (WorkException e) {
			System.out.println("===================================================");
			System.out.println(e.getMessage());
			System.out.println("===================================================");
		}
	}
	
	public void updateStatus(int id) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter task id : ");
		int tid=sc.nextInt();
		System.out.println("Enter task status");
		String s=sc.next();
		Repository repo=new RepositoryImpl();
		try {
			String m=repo.updateStatus(tid, id,s);
			System.out.println("===================================================");
			System.out.println(m);
			System.out.println("===================================================");
		} catch (WorkException e) {
			System.out.println("===================================================");
			System.out.println(e.getMessage());
			System.out.println("===================================================");
		}
	}
	
	public void getList(int id) {
		Repository repo=new RepositoryImpl();
		try {
			List<AllTasks>list=repo.viewAttended(id);
			System.out.println("===================================================");
			list.forEach(l-> System.out.println(l));
			System.out.println("===================================================");
		} catch (WorkException e) {
			System.out.println("===================================================");
			System.out.println(e.getMessage());
			System.out.println("===================================================");
		}
	}
	
	public void passwdUpdate(int id) {
		Repository repo=new RepositoryImpl();
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter new Password : ");
		String passwd=sc.next();
		try {
			String m=repo.updatePasswd(id, passwd);
			System.out.println("===================================================");
			System.out.println(m);
			System.out.println("===================================================");
		} catch (EngineerException e) {
			System.out.println("===================================================");
			System.out.println(e.getMessage());
			System.out.println("===================================================");
		}
	}
}
