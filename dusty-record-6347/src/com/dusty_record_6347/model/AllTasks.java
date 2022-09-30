package com.dusty_record_6347.model;

public class AllTasks {
	private int id;
	private String category;
	private String status; 
	private int engineer_id;
	private int employee_id;
	
	public AllTasks() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AllTasks(int id, String category, String status, int engineer_id, int employee_id) {
		super();
		this.id = id;
		this.category = category;
		this.status = status;
		this.engineer_id = engineer_id;
		this.employee_id = employee_id;
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

	public int getEngineer_id() {
		return engineer_id;
	}

	public void setEngineer_id(int engineer_id) {
		this.engineer_id = engineer_id;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	@Override
	public String toString() {
		return "AllTasks [id=" + id + ", category=" + category + ", status=" + status + ", engineer_id=" + engineer_id
				+ ", employee_id=" + employee_id + "]";
	}
	
	
	
	
	
}
