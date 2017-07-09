package com.framework.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatchAction extends Action {

	@Override
	public final ActionForward execute(HttpServletRequest request,
			HttpServletResponse response, ActionForm form, String paramter)       
	        throws  IOException,ServletException{
		
		//获取用户的请求的参数,参数就是方法名  user.do?action=add  user.do?action=xx
		 String methodName = request.getParameter("action");
		 
		if(methodName!=null && "".equals(methodName)){
			
			throw new ServletException("当前业务参数木有处理");
		}
		//利用当前类构建对象  DispatchAction?
		//this:一定是当前类的对象吗?  可能是子类的对象  this:当前运行环境下的对象
		
		Class clazz = this.getClass();
	  	Method method=null;
		try {
			//获取当前类的方法对象 ,传递一个方法名
			method = clazz.getMethod(methodName, new Class[]{HttpServletRequest.class,HttpServletResponse.class,ActionForm.class,String.class});
			return (ActionForward)method.invoke(this, new Object[]{request,response,form,paramter});
		} catch (SecurityException e) {
			throw new ServletException("服务器处理出错");
		} catch (NoSuchMethodException e) {
			throw new ServletException("服务器处理出错");
		} catch (IllegalArgumentException e) {
			throw new ServletException("服务器处理出错");
		} catch (IllegalAccessException e) {
			throw new ServletException("服务器处理出错");
		} catch (InvocationTargetException e) {
			throw new ServletException("服务器处理出错");
		}
		
		
	}

}
