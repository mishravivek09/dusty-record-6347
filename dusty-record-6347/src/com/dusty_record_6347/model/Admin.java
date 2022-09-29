package com.dusty_record_6347.model;

public class Admin {
	int id;
	String name;
	String email;
	String password;
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(int id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Engineer [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
	
}
