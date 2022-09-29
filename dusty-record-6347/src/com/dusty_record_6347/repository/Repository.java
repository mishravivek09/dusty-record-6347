package com.dusty_record_6347.repository;

import java.util.List;

import com.dusty_record_6347.exceptions.AdminException;
import com.dusty_record_6347.exceptions.EngineerException;
import com.dusty_record_6347.model.Admin;
import com.dusty_record_6347.model.Engineer;

public interface Repository {
	
	//HOD methods...
	
	public Admin adminLogin(String email,String password)throws AdminException;
	public String registerEngineer(Engineer engineer);
	public List<Engineer> getListOfEngineer()throws EngineerException;
	public String removeEngineer(int id);
	
	//Engineer methods..
	
	public String loginEngineer(String email,String password);
	
}
