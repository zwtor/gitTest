package cn.kxy.base.business;

public class EnterpriseData {
	public static String getEnterpriseId() {
		String env = System.getProperty("env");
		String id = "";
		switch (env) {
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
		case "FAT2-1":
			id="951057547274620933";
			break;
		case "FAT1":
			id="951057547274620933";
			break;
		case "DEV":
			id="948467577997365248";
			break;
		default:
			id="1441281077904805896";
			break;
		}
		return id;
	}
}
