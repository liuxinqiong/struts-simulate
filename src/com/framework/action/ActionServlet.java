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
		//1.从xml中解析得到ActionMapping  并存入用户在mvc-config中配置的 actionConfig  &  FromBeanconfig的对象集合
		String configPath= getInitParameter("config");
		ActionMapping  mapping=MvcConfigParser.getByXML(ActionServlet.class.getClassLoader().getResource(configPath).getPath());
		System.out.println("得到配置路径:"+configPath);
		//2.存入application
		getServletContext().setAttribute(ActionMapping.class.getName(), mapping);
		
	}
	
	public void  process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, IntrospectionException, IllegalArgumentException, InvocationTargetException {		
		//接收请求  xxxx/log.do
		String   url= req.getRequestURL().toString();

		//获取相应的路径 /log
		String reqUrl= url.substring(url.lastIndexOf("/"),url.lastIndexOf("."));
		System.out.println("请求的地址:"+reqUrl);
		
		//根据路径获取相应的处理类对象
		ActionMapping mapping=(ActionMapping)getServletContext().getAttribute(ActionMapping.class.getName());
	    // /log 
		ActionConfig  config=mapping.getConfig(reqUrl);
	    
		//动态调用处理方法，获取处理结果ActionForward
	    if(config==null){
	    	throw new ServletException("没有此url的处理方法");
	    }
	    
	    //获取请求需要使用的ActionForm,通过反射获取对象相关信息,将界面用户输入的内容传入相应的action类
	    FormBeanConfig formBean = mapping.getFormBeanConfigs(config.getForm());
	    ActionForm  actionForm = null;
	    
	    //如果请求中需要有相应的实体保存数据formBean !=null情况
	    if(formBean!=null){
	    	
	    	//1.通过反射构建一个ActionForm  "com.framework.entity.UserEntity"对应的对象 name=null pwd=null
	    	actionForm =(ActionForm)Class.forName(formBean.getClassName()).newInstance();
	    	Class clazz = actionForm.getClass();
	    	//2.获取对象的所有属性 id  name pwd sex
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
		    //获取当前属性的描述器  setXXX()  ,getXXX()
		    PropertyDescriptor   pd=new PropertyDescriptor(f.getName(),clazz);
		    //获取到属性的setXXX()
		    Method  method= pd.getWriteMethod();  //如果要调用getxxx  pd.getReadMethod();
		    //动态调用setxxx  ,数据就全部赋值给了 actionForm对象
		    method.invoke(actionForm, new Object[]{value});
		    }
	    }
	   //利用反射构建了一个  "com.framework.mvc.LoginAction" 的对象
		Action  action =(Action)Class.forName(config.getClassName()).newInstance();
		if(action==null){
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"服务器目前没有完成此次URL的请求");
		}	
			
			
		ActionForward  forward = action.execute(req, resp, actionForm, null);
		if(forward!=null){
			if(forward.isRedirct()){
				resp.sendRedirect(forward.getPath());
			}else{
				req.getRequestDispatcher(forward.getPath()).forward(req, resp);
			}
		}
		//进行页面跳转	
	}
}
