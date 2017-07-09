package com.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.framework.action.ActionForm;
import com.framework.action.ActionForward;
import com.framework.action.DispatchAction;

public class UserAction extends DispatchAction{
	//user.do?action=add
	public  ActionForward  add(HttpServletRequest request,
			HttpServletResponse response, ActionForm form, String paramter){
		System.out.println("调用了add 方法");
		return new ActionForward("add.jsp");
		
	}
	
	//user.do?action=modify
	public  ActionForward  modify(HttpServletRequest request,
			HttpServletResponse response, ActionForm form, String paramter){
		System.out.println("调用了modify 方法");
		return new ActionForward("modify.jsp");
		
	}

}
