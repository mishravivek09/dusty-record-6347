package com.dusty_record_6347.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.dusty_record_6347.usecases.AdminUsecases;
import com.dusty_record_6347.usecases.EmployeeUsecases;
import com.dusty_record_6347.usecases.EngineerUseCases;


public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try {
			while(true){
				System.out.println("===================================================");
				System.out.println("** Welcome to Masai Service Center **");
				System.out.println("===================================================");
				System.out.println("-Select Options From Below : ");
				System.out.println("-Press 1 for HOD");
				System.out.println("-Press 2 for Engineers");
				System.out.println("-Press 3 for Employees");
				System.out.println("-Press 0 for Exit");
				System.out.print("-Enter your choice : ");
				int option =sc.nextInt();
				if(option==0) {
					System.out.println("Thankyou");
					break;
				}
				try {
				switch (option) {
				case 1: 
					AdminUsecases admin=new AdminUsecases();
					String m=admin.adminLogin();
					System.out.println("*** "+m+" ***");
					if(!m.equals("Invalid Username or password..")) {
						while(true) {
							System.out.println("===================================================");
							System.out.println("Press 1 for register an engineer");
							System.out.println("Press 2 for get all engineer");
							System.out.println("Press 3 for remove an engineer");
							System.out.println("Press 4 for see all tasks");
							System.out.println("Press 5 for assign task to any engineers");
							System.out.println("Press 0 for Exit");
							System.out.print("Enter your choice : ");
							int selected=sc.nextInt();
							System.out.println("===================================================");
							if(selected==1) {
								String m1=admin.addEnginner();
								System.out.println(m1);
								
							}else if(selected==2) {
								admin.getEngineers();
							}else if(selected==3) {
								admin.deleteEngineer();
							}else if(selected==4) {
								admin.seeTasks();
							}else if(selected==5) {
								admin.assignTasks();
							}else if(selected==0) {
								System.out.println("Thankyou !");
								break;
							}else {
								throw new IllegalArgumentException("You selected wrong option");
							}
						}
					}
					
					break;
				case 2:
					EngineerUseCases eng=new EngineerUseCases();
					int ID=eng.loginEng();
					if(ID>0) {
						while(true) {
							System.out.println("Enter 1 for view all tasks");
							System.out.println("Enter 2 for update task status");
							System.out.println("Enter 3 for see all done tasks");
							System.out.println("Enter 4 for see all pending task");
							System.out.println("Enter 5 for update your password");
							System.out.println("Enter 0 for exit");
							int selected=sc.nextInt();
							if(selected==1) {
								eng.viewTask(ID);
							}else if(selected==2) {
								eng.updateStatus(ID);
							}else if(selected==3) {
								eng.getList(ID);
							}else if(selected==4) {
								eng.pending(ID);
							}else if(selected==5) {
								eng.passwdUpdate(ID);
							}else if(selected==0) {
								System.out.println("Thankyou !");
								break;
							}else {
								throw new IllegalArgumentException("You selected wrong option");
							}
						}
					}
					break;
				case 3:
					while(true) {
						EmployeeUsecases emp=new EmployeeUsecases();
						System.out.println("Press 1 for new registration");
						System.out.println("Press 2 for signin");
						System.out.println("Press 0 for exit");
						System.out.println("Enter you choice : ");
						int selected=sc.nextInt();
						if(selected==1) {
							emp.registration();
						}else if(selected==2) {
							int id=emp.login();
							if(id>0) {
								while(true) {
									System.out.println("Press 1 for register a complain");
									System.out.println("Press 2 for get complain status");
									System.out.println("Press 3 for see all complain history");
									System.out.println("Press 4 for change current password");
									System.out.println("Press 0 for exit");
									int opt=sc.nextInt();
									if(opt==1) {
										emp.registerComplain(id);
									}else if(opt==2) {
										emp.viewStatus();
									}else if(opt==3) {
										emp.viewAllComplain(id);
									}else if(opt==4) {
										emp.changePasswd(id);
									}else if(opt==0) {
										break;
									}else {
										throw new IllegalArgumentException("You selected wrong option");
									}
								}
							}
						}else if(selected==0) {
							System.out.println("Thankyou !");
							break;
						}else {
							throw new IllegalArgumentException("You selected wrong option");
						}
					}
					break;
				default:
					throw new IllegalArgumentException("Unexpected value");
				}
			}catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			}
		}catch(InputMismatchException e) {
			System.out.println("Please enter numeric value..");
		}
	}

}
