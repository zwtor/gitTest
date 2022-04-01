package init.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.annotations.BeforeSuite;

public class InitExam {
	@BeforeSuite
	public void initSuit() {
		if (ClassificationBusines.getPrimaryId() == null) {
			ClassificationBusines
					.addPrimaryClassification("class" + CommonData.getStringRandom(3) + CommonData.getStringRandom(1));
		}
		
		String res2 = CertificateBusiness.queryCertificateList(BaseBusiness.certificate_name, "1");
		int total2 = (int) JSONPath.read(res2, "$.total");
		if (total2 == 0) {
			CertificateBusiness.creatCertificate(BaseBusiness.certificate_name, "kxyTest0", "Sinpoes", "true", "chinese", "2");
		}
		
		System.out.println("Now cases is running in "+ System.getProperty("env"));
		BaseBusiness.addQuestionnaire();
		System.out.println("init add Questionnaire success!!");
		BaseBusiness.addPassExamToPractice();
		System.out.println("init add PassExamToPractice success!!");
		BaseBusiness.addPassExam();
		System.out.println("init add PassExam success!!");
		BaseBusiness.addPassPaper();
		System.out.println("init add PassPaper success!!");
		BaseBusiness.addExam();
		System.out.println("init add Exam success!!");
		BaseBusiness.addPaper();
		System.out.println("init add Paper success!!it is running test");
	}
}
