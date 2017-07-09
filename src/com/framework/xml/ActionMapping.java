package com.framework.xml;

import java.util.HashMap;
import java.util.Map;

/**
 * atcions的集合类
 * @author Administrator
 *
 */
public class ActionMapping {
	//保存mvc-config文件中所有的action的配置信息,key 为path
	 Map<String,ActionConfig>  configs=new HashMap<String,ActionConfig>();
    //保存mvc-config文件中所有的form的配置信息  key 为名称
	 Map<String,FormBeanConfig>  formBeanConfigs=new HashMap<String, FormBeanConfig>();
	 
	public  ActionConfig getConfig(String path) {
		return configs.get(path);
	}

	public void setConfig(String path,ActionConfig config) {
		configs.put(path, config);
	}

	public FormBeanConfig getFormBeanConfigs(String name) {
		return formBeanConfigs.get(name);
	}

	public void setFormBeanConfig(String name, FormBeanConfig formBeanConfig) {
		formBeanConfigs.put(name, formBeanConfig);
	}
	
}
