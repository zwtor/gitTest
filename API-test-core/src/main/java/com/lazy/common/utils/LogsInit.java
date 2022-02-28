package com.lazy.common.utils;


import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * @ClassName:  LogsInit   
 * @Description:TODO(读取log配置)   
 * @author: zhanglun
 * @date:   2018年4月22日 上午10:45:55   
 *     
 */
public class LogsInit {

	public LogsInit() {
		try {
			PropertyConfigurator.configure(FilePath.getConfigDirectory()+"log4j.properties");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
