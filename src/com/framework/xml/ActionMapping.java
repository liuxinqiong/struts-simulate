package com.framework.xml;

import java.util.HashMap;
import java.util.Map;

/**
 * atcions�ļ�����
 * @author Administrator
 *
 */
public class ActionMapping {
	//����mvc-config�ļ������е�action��������Ϣ,key Ϊpath
	 Map<String,ActionConfig>  configs=new HashMap<String,ActionConfig>();
    //����mvc-config�ļ������е�form��������Ϣ  key Ϊ����
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
