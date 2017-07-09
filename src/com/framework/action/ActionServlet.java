package com.framework.action;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import com.framework.xml.ActionConfig;
import com.framework.xml.ActionMapping;
import com.framework.xml.FormBeanConfig;
import com.framework.xml.MvcConfigParser;

public class ActionServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			process(req,resp);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			process(req,resp);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void init() throws ServletException {
		//1.��xml�н����õ�ActionMapping  �������û���mvc-config�����õ� actionConfig  &  FromBeanconfig�Ķ��󼯺�
		String configPath= getInitParameter("config");
		ActionMapping  mapping=MvcConfigParser.getByXML(ActionServlet.class.getClassLoader().getResource(configPath).getPath());
		System.out.println("�õ�����·��:"+configPath);
		//2.����application
		getServletContext().setAttribute(ActionMapping.class.getName(), mapping);
		
	}
	
	public void  process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, IntrospectionException, IllegalArgumentException, InvocationTargetException {		
		//��������  xxxx/log.do
		String   url= req.getRequestURL().toString();

		//��ȡ��Ӧ��·�� /log
		String reqUrl= url.substring(url.lastIndexOf("/"),url.lastIndexOf("."));
		System.out.println("����ĵ�ַ:"+reqUrl);
		
		//����·����ȡ��Ӧ�Ĵ��������
		ActionMapping mapping=(ActionMapping)getServletContext().getAttribute(ActionMapping.class.getName());
	    // /log 
		ActionConfig  config=mapping.getConfig(reqUrl);
	    
		//��̬���ô���������ȡ������ActionForward
	    if(config==null){
	    	throw new ServletException("û�д�url�Ĵ�����");
	    }
	    
	    //��ȡ������Ҫʹ�õ�ActionForm,ͨ�������ȡ���������Ϣ,�������û���������ݴ�����Ӧ��action��
	    FormBeanConfig formBean = mapping.getFormBeanConfigs(config.getForm());
	    ActionForm  actionForm = null;
	    
	    //�����������Ҫ����Ӧ��ʵ�屣������formBean !=null���
	    if(formBean!=null){
	    	
	    	//1.ͨ�����乹��һ��ActionForm  "com.framework.entity.UserEntity"��Ӧ�Ķ��� name=null pwd=null
	    	actionForm =(ActionForm)Class.forName(formBean.getClassName()).newInstance();
	    	Class clazz = actionForm.getClass();
	    	//2.��ȡ������������� id  name pwd sex
	    	Field[] fields= clazz.getDeclaredFields();
		    for(Field f:fields){
		    	 String valueStr = req.getParameter(f.getName());
		    	 //System.out.println(f.getName()+":"+valueStr);
		    	 Object  value=null;
		    	 if(int.class ==f.getType()){
		    		 if(valueStr==null || valueStr==""){
		    			 value=0;
		    		 }else{
		    		     value=Integer.valueOf(valueStr);
		    	     }
		    	  }else{
		    		  value= valueStr;
		    	  }
		    //��ȡ��ǰ���Ե�������  setXXX()  ,getXXX()
		    PropertyDescriptor   pd=new PropertyDescriptor(f.getName(),clazz);
		    //��ȡ�����Ե�setXXX()
		    Method  method= pd.getWriteMethod();  //���Ҫ����getxxx  pd.getReadMethod();
		    //��̬����setxxx  ,���ݾ�ȫ����ֵ���� actionForm����
		    method.invoke(actionForm, new Object[]{value});
		    }
	    }
	   //���÷��乹����һ��  "com.framework.mvc.LoginAction" �Ķ���
		Action  action =(Action)Class.forName(config.getClassName()).newInstance();
		if(action==null){
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"������Ŀǰû����ɴ˴�URL������");
		}	
			
			
		ActionForward  forward = action.execute(req, resp, actionForm, null);
		if(forward!=null){
			if(forward.isRedirct()){
				resp.sendRedirect(forward.getPath());
			}else{
				req.getRequestDispatcher(forward.getPath()).forward(req, resp);
			}
		}
		//����ҳ����ת	
	}
}
