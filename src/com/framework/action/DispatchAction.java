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
		
		//��ȡ�û�������Ĳ���,�������Ƿ�����  user.do?action=add  user.do?action=xx
		 String methodName = request.getParameter("action");
		 
		if(methodName!=null && "".equals(methodName)){
			
			throw new ServletException("��ǰҵ�����ľ�д���");
		}
		//���õ�ǰ�๹������  DispatchAction?
		//this:һ���ǵ�ǰ��Ķ�����?  ����������Ķ���  this:��ǰ���л����µĶ���
		
		Class clazz = this.getClass();
	  	Method method=null;
		try {
			//��ȡ��ǰ��ķ������� ,����һ��������
			method = clazz.getMethod(methodName, new Class[]{HttpServletRequest.class,HttpServletResponse.class,ActionForm.class,String.class});
			return (ActionForward)method.invoke(this, new Object[]{request,response,form,paramter});
		} catch (SecurityException e) {
			throw new ServletException("�������������");
		} catch (NoSuchMethodException e) {
			throw new ServletException("�������������");
		} catch (IllegalArgumentException e) {
			throw new ServletException("�������������");
		} catch (IllegalAccessException e) {
			throw new ServletException("�������������");
		} catch (InvocationTargetException e) {
			throw new ServletException("�������������");
		}
		
		
	}

}
