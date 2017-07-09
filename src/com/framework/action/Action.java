package com.framework.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.framework.xml.ActionConfig;

public abstract class Action {
	private ServletContext   application;
	
	public Action(){}
	public Action(ServletContext  application){
		this.application = application;
		
	}
	
	public ServletContext getApplication() {
		return application;
	}

	public void setApplication(ServletContext application) {
		this.application = application;
	}

	public abstract ActionForward  execute(HttpServletRequest request,HttpServletResponse response,ActionForm  form,String paramter) throws IOException,ServletException;
 
}
