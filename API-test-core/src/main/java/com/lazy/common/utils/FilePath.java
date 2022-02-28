package com.lazy.common.utils;

import java.io.File;

/**
 * 
 * @ClassName:  FilePath   
 * @Description:TODO(获取文件夹路径)   
 * @author: zhanglun
 *     
 */
public class FilePath {
	/**
	 * 
	 * @Title: getHomeDirectory   
	 * @Description: TODO(获取项目主目录)   
	 * @param: @return      
	 * @return: String
	 */
	public static String getHome(){
		String homeDir = System.getProperty("user.dir");
		homeDir = homeDir.substring(0, homeDir.length() - 6);
		System.out.println(homeDir);
		return homeDir;
	}
	
	
	public static String getHomeDirectory() {
		return getHome()+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator;
	}
	
	
	public static String getLogDirectory(){
		String logpath=FilePath.getHome()+File.separator+"target"+File.separator+"logs"+File.separator;
		return logpath;
	}
	
	public static String getTestOutPutDirectory(){
		String logpath=FilePath.getHome()+File.separator+"target"+File.separator+"test-output"+File.separator;
		return logpath;
	}
	
	public static String getScreenShotDirectory(){
		String ScreenShotpath=FilePath.getHomeDirectory()+"creenShot"+File.separator;
		return ScreenShotpath;
	}
	
	
	public static String gettoolDirectory(){
		String driverpath=FilePath.getHomeDirectory()+"tool"+File.separator;
		return driverpath;
	}
	
	public static String getdriverDirectory(){
		String driverpath=FilePath.getHomeDirectory()+"drivers"+File.separator;
		return driverpath;
	}
	
	public static String getConfigDirectory(){
		String configpath=FilePath.getHomeDirectory()+"config"+File.separator;
		return configpath;
	}

	public static String getDataDirectory(){
		String configpath=FilePath.getHomeDirectory()+"testdata"+File.separator;
		return configpath;
	}
	public static void main(String[] args) {
		
		System.out.println(File.separator);

	}

}
