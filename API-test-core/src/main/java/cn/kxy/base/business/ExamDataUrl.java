package cn.kxy.base.business;

public class ExamDataUrl {
	public static String getNewExamUrl() {
		String env = System.getProperty("env");
		String url = "";
		switch (env) {
		case "GREEN":
			url="https://grcoolapi.coolcollege.cn/exam-api/";
			break;
		case "PRO":
			url="https://coolapi.coolcollege.cn/exam-api/";
			break;
		case "UAT":
			url="https://hdcoolapi.coolcollege.cn/exam-api/";
			break;
		case "FAT10":
			url="https://ct10coolapi.coolcollege.cn/exam-api/";
			break;
		case "FAT9":
			url="https://ct9coolapi.coolcollege.cn/exam-api/";
			break;
		case "FAT8":
			url="https://ct8coolapi.coolcollege.cn/exam-api/";
			break;
		case "FAT7":
			url="https://ct7coolapi.coolcollege.cn/exam-api/";
			break;
		case "FAT6":
			url="https://ct6coolapi.coolcollege.cn/exam-api/";
			break;
		case "FAT5":
			url="https://ct5coolapi.coolcollege.cn/exam-api/";
			break;
		case "FAT4":
			url="https://ct4coolapi.coolcollege.cn/exam-api/";
			break;
		case "FAT3":
			url="https://ct3coolapi.coolcollege.cn/exam-api/";
			break;
		case "FAT2":
			url="https://ct2coolapi.coolcollege.cn/exam-api/";
			break;
		case "FAT1":
			url="https://ct1coolapi.coolcollege.cn/exam-api/";
			break;
		case "DEV":
			url="https://dcoolapi.coolcollege.cn/exam-api/";
			break;
		default:
			url="https://ct1coolapi.coolcollege.cn/exam-api/";
			break;
		}

		return url;
	}

}
