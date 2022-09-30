package com.dusty_record_6347.model;

public class Tasks {
	private int id;
	private String category;
	private String status;
	private String engineer_name;
	private String employee_name;
	
	
	public Tasks() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Tasks(int id, String category, String status, String engineer_name, String employee_name) {
		super();
		this.id = id;
		this.category = category;
		this.status = status;
		this.engineer_name = engineer_name;
		this.employee_name = employee_name;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getEngineer_name() {
		return engineer_name;
	}


	public void setEngineer_name(String engineer_name) {
		this.engineer_name = engineer_name;
	}


	public String getEmployee_name() {
		return employee_name;
	}


	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}


	@Override
	public String toString() {
		return "Tasks [id=" + id + ", category=" + category + ", status=" + status + ", engineer_name=" + engineer_name
				+ ", employee_name=" + employee_name + "]";
	}
	
	
	
	
	
	
	
}
