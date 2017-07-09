package com.framework.xml;

/**
 * 存储mvc-config中<form-bean>的信息
 * @author Administrator
 *
 */
public class FormBeanConfig {
	
    private  String name;
    
    private  String className;
    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public FormBeanConfig(){
    	
    }
    public FormBeanConfig(String name,String className){
    	this.name = name;
    	this.className = name;
    }
	

}
