package com.framework.xml;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * mvc�����ļ���ת����
 * @author Administrator
 *
 */
public class MvcConfigParser {
	
	/**
	 * ��ȡ<action-mapping>�µ�����������Ϣ
	 * @return ���е�������Ϣ
	 * ʹ��sax������ԭ�����ٶȿ�
	 */
	public static ActionMapping  getByXML(String filePath){
		SAXReader   reader = new SAXReader();
		
	    try {
			Document doc = reader.read(filePath);
			return iteratorElement(doc);
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;
	}
	public static ActionMapping  iteratorElement(Document doc){
		//1.�����յ�ActionMapping
		ActionMapping  mapping=new ActionMapping();
		
		//2.�������е�form-beans
		List<?> formBeanNodes= doc.selectNodes("mvc-config/form-beans/form-bean");
		for(int i=0;i<formBeanNodes.size();i++){
			Element element= (Element)formBeanNodes.get(i);
			FormBeanConfig  formBean=new FormBeanConfig();
			formBean.setName(element.attributeValue("name"));
			formBean.setClassName( element.attributeValue("class"));
			mapping.setFormBeanConfig(formBean.getName(), formBean);
		}
		
		
		
		//3.�������е�action-mapping�µ����нڵ�,ÿ����һ�������һ��config����
		List<?>  actionNodes= doc.selectNodes("mvc-config/action-mappings/action");
		
		for(int i=0;i<actionNodes.size();i++){
			Element  element =(Element)actionNodes.get(i);
			ActionConfig  config=new ActionConfig();
			config.setPath(element.attributeValue("path"));
			config.setClassName(element.attributeValue("class"));
			config.setForm(element.attributeValue("name"));
			config.setParameter(element.attributeValue("parameter"));
			mapping.setConfig(config.getPath(), config);
			
		}
		
	
		return mapping;
	}
	public static void main(String[] args) {
		String path= MvcConfigParser.class.getClassLoader().getResource("mvc-config.xml").getPath();
		ActionMapping  mapping = getByXML(path);
		ActionConfig  config =mapping.getConfig("/log");
		System.out.println("class:"+config.getClassName());
		System.out.println("form:"+config.getForm());
		
		System.out.println(mapping.getFormBeanConfigs("userBean").getClassName());
	}
	

}
