package com.framework.mvc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.framework.action.Action;
import com.framework.action.ActionForm;
import com.framework.action.ActionForward;
import com.framework.entity.UserEntity;

public class LoginAction extends Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response, ActionForm form, String paramter) throws IOException {
            if(form==null){
            	return new ActionForward("/error.jsp");
            }
            UserEntity entity =(UserEntity)form;
            System.out.println(entity.getName()+","+entity.getPwd());
            if("admin".equals(entity.getName()) && "123".equals(entity.getPwd())){
            	 return new ActionForward("logsucc.jsp");
            }else{
            	response.setContentType("text/html;charset=utf-8");
            	PrintWriter out= response.getWriter();
                out.println("<script>alert('用户名或密码错误!');location.href='login.html';</script>");
            	return null;
            }
		  
	}

}
