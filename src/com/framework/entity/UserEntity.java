package com.framework.entity;

import com.framework.action.ActionForm;

public class UserEntity implements ActionForm{
  
	 private int id;
	 private String name;
	 private String pwd;
	 private String sex;
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
	
		this.pwd = pwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public UserEntity() {
	
	}
	public UserEntity(int id, String name, String pwd, String sex) {
		
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.sex = sex;
	}
	 
	 

}
