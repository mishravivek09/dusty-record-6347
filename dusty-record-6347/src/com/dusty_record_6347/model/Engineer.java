package com.dusty_record_6347.model;

public class Engineer {
	int id;
	String name;
	String email;
	String password;
	String category;
	public Engineer() {
		super();
	}
	public Engineer(int id, String name, String email, String password, String category) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.category = category;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Engineer [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", category="
				+ category + "]";
	}
	
	
}
