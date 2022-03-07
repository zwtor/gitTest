package init.cases;

import org.testng.annotations.BeforeSuite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class InitExam {
	
	@BeforeSuite
	public void initSuit() {
//		if (ClassificationBusines.getPrimaryId() == null) {
//			ClassificationBusines
//					.addPrimaryClassification("class" + CommonData.getStringRandom(3) + CommonData.getStringRandom(1));
//		}
//		
//		String res2 = CertificateBusiness.queryCertificateList(BaseBusiness.certificate_name, "1");
//		int total2 = (int) JSONPath.read(res2, "$.total");
//		if (total2 == 0) {
//			CertificateBusiness.creatCertificate(BaseBusiness.certificate_name, "kxyTest0", "Sinpoes", "true", "chinese", "2");
//		}
//		
//		String surrounding = readTxt(FilePath.getHome() + File.separator + "environmental.txt");
//		System.out.println("Now cases is running in "+surrounding);
//		BaseBusiness.addQuestionnaire();
//		System.out.println("init add Questionnaire success!!");
//		BaseBusiness.addPassExamToPractice();
//		System.out.println("init add PassExamToPractice success!!");
//		BaseBusiness.addPassExam();
//		System.out.println("init add PassExam success!!");
//		BaseBusiness.addPassPaper();
//		System.out.println("init add PassPaper success!!");
//		BaseBusiness.addExam();
//		System.out.println("init add Exam success!!");
//		BaseBusiness.addPaper();
//		System.out.println("init add Paper success!!it is running test");
	}
	public static  String readTxt(String fileName) {
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
