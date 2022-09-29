package com.dusty_record_6347.main;

import java.util.List;
import java.util.Scanner;

import com.dusty_record_6347.usecases.AdminUsecases;
import com.dusty_record_6347.usecases.EngineerUseCases;


public class Main {
	public static void main(String[] args) {
		boolean flag=true;
		Scanner sc=new Scanner(System.in);
		while(flag==true) {
			System.out.println("===================================================");
			System.out.println("** Welcome to Masai Service Center **");
			System.out.println("===================================================");
			System.out.println("-Select Options From Below : ");
			System.out.println("-Press 1 for HOD");
			System.out.println("-Press 2 for Engineers");
			System.out.println("-Press 3 for Employees");
			System.out.println("-Press 0 for Exit");
			int option =sc.nextInt();
			if(option==0) {
				break;
			}else if(option==1) {
				AdminUsecases admin=new AdminUsecases();
				System.out.println("Press 1 for register a new engineer");
				System.out.println("Press 2 for see all the registerd engineers");
				System.out.println("Press 3 for remove an engineer");
				int select=sc.nextInt();
				if(select==1) {
					String res=admin.addEnginner();
					System.out.println(res);
				}else if(select==2) {
					admin.getEngineers();
				}else if(select==3) {
					admin.deleteEngineer();
				}
			}else if(option==2) {
				EngineerUseCases eng=new EngineerUseCases();
				eng.loginEng();
				
				
			}else if(option==3) {
				System.out.println("you selected 3rd option");
			}
		}
	}
}
