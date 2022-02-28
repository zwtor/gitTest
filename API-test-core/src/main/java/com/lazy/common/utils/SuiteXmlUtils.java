package com.lazy.common.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class SuiteXmlUtils {
	static 	Document document = null;

    static {
    	try {
			document =new SAXReader().read(FilePath.getHome()+"pom.xml");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
    }
    
    //从properties文件获取测试模块的值，然后进行转换为testng.xml
   
	public static List<String> getSuit() {
		StringBuffer modules=new StringBuffer();
		List<String> moduleslist = Arrays.asList(AutoCommonUtil.readConfig("suit.properties", "module").split(","));
		
		System.out.println(modules);
		for (String model : moduleslist) {
			if (model.equals("百度")) {
				modules.append("baidu,");
			}
			if (model.equals("手机")) {
				modules.append("phone,");
			}
			if (model.equals("搜狗")) {
				modules.append("sougou,");
			}
		}
		List<String> list = Arrays.asList(modules.toString().split(","));
		return list;
	}
	
	//初始化SuiteXmlFiles，删除子节点信息
	public static void initSuiteFile() {
		
		Element suiteXmlFile =getSuiteXmlFiles().element("suiteXmlFiles");
		
	
			suiteXmlFile.getParent().remove(suiteXmlFile);
			OutPutXml(document);
	}
	
	
	
	//获取SuiteXmlFiles节点对象
	public static Element getSuiteXmlFiles() {
	
		Element rootElement = document.getRootElement();
		Element suiteXmlFile = rootElement.element("build").element("plugins").element("plugin")
				.element("configuration");
		return suiteXmlFile;
		
	}

	//设置SuiteXmlFiles节点的值
	public static void setSuite() {
		List<String> suits= getSuit();
		getSuiteXmlFiles().addElement("suiteXmlFiles");
		Element suiteXmlFile = getSuiteXmlFiles().element("suiteXmlFiles");

		for (String string : suits) {
			Element suiteFile = suiteXmlFile.addElement("suiteXmlFile");

			suiteFile.setText("suit/soa/" + string + ".xml");
		}
		OutPutXml(document);

	}

	public static void OutPutXml(Document document) {
		OutputFormat format = OutputFormat.createPrettyPrint();
		try {
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(FilePath.getHome()+"pom.xml"), format);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
