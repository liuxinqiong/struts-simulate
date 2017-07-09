package com.framework.xml;

/**
 * action的配置信息
 * @author phenixchen
 *
 */
public class ActionConfig {
   
   //action的名称  eg. /log
    private String path;
   
   //action对应的类名  eg. com.framework.action
    private String className;
    
    //绑定的表单名称
    private String form;
    
    //路径中的参数名称
    private String parameter;


	public String getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public ActionConfig(){}
	
	public ActionConfig(String path,String className,String form,String parameter){
		this.path = path;
		this.className = className;
		this.form = form;
		this.parameter = parameter;
	}
   
   
   
   
   
}
