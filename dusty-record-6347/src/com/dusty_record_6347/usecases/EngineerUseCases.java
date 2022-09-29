package com.dusty_record_6347.usecases;

import java.util.Scanner;


import com.dusty_record_6347.repository.Repository;
import com.dusty_record_6347.repository.RepositoryImpl;

public class EngineerUseCases {
	public void loginEng() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter email id :");
		String email=sc.next();
		System.out.println("Enter password :");
		String pass=sc.next();
		Repository repo=new RepositoryImpl();
		String eng=repo.loginEngineer(email, pass);
		System.out.println(eng);
		sc.close();
	}
}
