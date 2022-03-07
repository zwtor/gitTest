package exam.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.MyExamTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.my.business.MyBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExamNegativeScore {
	String exam_fail_task_name = "NegativeScoreExam" + CommonData.getStringRandom(10);
	String id = "";
	@Test(description="验证考试不及格时，扣减学分",priority=1)
	public void testExamFailGetScore() {
		ExaminationTaskBusiness.creatRewardExamTask("1", "show", "60", "100", "2", exam_fail_task_name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1",
				CertificateBusiness.getIdByKeyword("AdvancedAutoTester"), "12", "0", "0", "0", "0", "true", "false",
				UserBusiness.getUserId(), "{\"missScore\":4,\"passScore\":6,\"unPassScore\":-7}");
	}
	
	@Test(description = "考试交白卷",priority =2)
	public void submitFailBlankExam() {
		MyExamTaskBusiness.submitFailBlankExam(exam_fail_task_name);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test(description = "获取考试id",priority= 3)
	public void testGetId() {
		id = ExaminationTaskBusiness.getIdByKeyword(exam_fail_task_name);
	}
	

	@Test(description= "删除考试",priority = 4)
	public void endTest() {
		ExaminationTaskBusiness.deleteExamTask(id);
	}
	
	@Test(description = "考试不及格扣减分后在我的学分记录查看",priority = 5)
	public void queryScoreRecordList() {
		String res = MyBusiness.queryScoreRecordList("","");
		Assert.assertTrue(res.contains("-7"),"考试不及格扣减分后在我的学分记录查看:"+res);
	}
}
