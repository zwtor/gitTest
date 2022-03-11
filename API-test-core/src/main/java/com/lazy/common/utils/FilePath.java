package com.lazy.common.utils;

import java.io.File;
import java.util.Properties;

public class FilePath {
	public static String getHome(){
		String homeDir = System.getProperty("user.dir");
//		homeDir = homeDir.substring(0, homeDir.length() - 6);
		return homeDir;
	}
	
	
	public static String getResourceDirectory() {
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
		String ScreenShotpath=FilePath.getResourceDirectory()+"creenShot"+File.separator;
		return ScreenShotpath;
	}
	
	
	public static String gettoolDirectory(){
		String driverpath=FilePath.getResourceDirectory()+"tool"+File.separator;
		return driverpath;
	}
	
	public static String getdriverDirectory(){
		String driverpath=FilePath.getResourceDirectory()+"drivers"+File.separator;
		return driverpath;
	}
	
	public static String getConfigDirectory(){
		String configpath=FilePath.getResourceDirectory()+"config"+File.separator;
		return configpath;
	}

	public static String getDataDirectory(){
		String configpath=FilePath.getResourceDirectory()+"testdata"+File.separator;
		return configpath;
	}

	public static String getURLFilePath(String fileName) {
		return FilePath.getResourceDirectory() + "url" + File.separator + fileName;
	}

	public static void main(String[] args) {
		Properties properties = System.getProperties();
		System.out.println(getURLFilePath("new-exam-url.json"));
	}

}
