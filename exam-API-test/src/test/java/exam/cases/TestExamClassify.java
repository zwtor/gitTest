package exam.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExamClassify {

	String exam_fail_task_name = "ExamClassfy" + CommonData.getStringRandom(10);
	String id = "";
	@Test(description="新增带有分类的考试",priority=1)
	public void testExamFailGetScore() {
		ExaminationTaskBusiness.creatRewardExamTask("1", "show", "60", "100", "2", exam_fail_task_name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1",
				CertificateBusiness.getIdByKeyword("AdvancedAutoTester"), "12", "0", "0", "0", "0", "true", "false",
				UserBusiness.getUserId(), "{\"missScore\":4,\"passScore\":6,\"unPassScore\":-7}","999");
	}
	
	@Test(description = "获取考试id",priority= 2)
	public void testGetId() {
		id = ExaminationTaskBusiness.getIdByKeyword(exam_fail_task_name);
	}
	
	@Test(description = "查看考试详情",priority =3)
	public void queryInfo() {
		String res = ExaminationTaskBusiness.queryInfoById(id);
		Assert.assertTrue(res.contains("999"),"查看考试详情"+res);
	}

	@Test(description= "删除考试",priority = 4)
	public void endTest() {
		ExaminationTaskBusiness.deleteExamTask(id);
	}
	
}
