package cn.kxy.base.business;

import com.lazy.common.utils.FilePath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EnterpriseData {
	public static String getEnterpriseId() {
		String type = readTxt(FilePath.getHome() + File.separator + "environmental.txt");
		String id = "";
		switch (type) {
		case "GREEN":
			id="1016929454485803038";
			break;
		case "PRO":
			id="1016929454485803038";
			break;
		case "UAT":
			id="1067985194709028888";
			break;
		case "FAT9":
			id="951057547274620933";
			break;
		case "FAT7":
			id="1402241878807678976";
			break;
		case "FAT6":
			id="951057547274620933";
			break;
		case "FAT5":
			id="951057547274620933";
			break;
		case "FAT4":
			id="951057547274620933";
			break;
		case "FAT3":
			id="951057547274620933";
			break;
		case "FAT2":
			id="951057547274620933";
			break;
		case "FAT1":
			id="951057547274620933";
			break;
		case "DEV":
			id="948467577997365248";
			break;
		default:
			id="1016929454485803038";
			break;
		}
		return id;
	}

	public static String readTxt(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuffer sbf = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempStr;
			while ((tempStr = reader.readLine()) != null) {
				sbf.append(tempStr);
			}
			reader.close();
			return sbf.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return sbf.toString();
	}
}
