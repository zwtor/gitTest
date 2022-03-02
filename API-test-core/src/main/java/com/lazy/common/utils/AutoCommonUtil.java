package com.lazy.common.utils;

import java.awt.*;
import java.io.*;
import java.util.Properties;

/**
 * 
 * @ClassName: AutoCommonUtil
 * @Description:TODO(自动化常用的方法)
 * @author: zhanglun
 * 
 */
public class AutoCommonUtil {

	public static Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    
	public static void deleteAllFilesOfDir(File path) {
		if (!path.exists())
			return;
		if (path.isFile()) {
			path.delete();
			return;
		}
		File[] files = path.listFiles();
		for (int i = 0; i < files.length; i++) {
			deleteAllFilesOfDir(files[i]);
		}
	}

	@SuppressWarnings("unused")
	private static void is_exit(String dir_name) {
		if (!(new File(dir_name).isDirectory())) {
			new File(dir_name).mkdir();
		}
	}

	public static void killProcess(String processName) {

		try {
			while (isProcessRunning(processName)) {
				Runtime.getRuntime().exec("taskkill /IM " + processName + " /F");
				Thread.sleep(500);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static boolean isProcessRunning(String processName) {
		Process p = null;
		try {
			p = Runtime.getRuntime().exec("tasklist");
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				if (line.toLowerCase().contains(processName)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static int getRandom(int max) throws Exception {
		return (int) (Math.random() * max);
	}

	// 生成任意长度的随机字符

	public static String getRandomString(int length) throws Exception {
		String str = "";
		for (int a = 0; a < length; a++) {
			char ch = (char) ('a' + Math.random() * ('z' - 'a' + 1));
			str = str + String.valueOf(ch);
		}
		return str;
	}

	public static String readConfig(String fileName, String keyName) {
		String filepath = FilePath.getConfigDirectory() + fileName;
		Properties properties = new Properties();
		// 使用InPutStream流读取properties文件
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
			properties.load(bufferedReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String value = properties.getProperty(keyName);
		return value;
	}
}
