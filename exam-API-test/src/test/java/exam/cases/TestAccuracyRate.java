package exam.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.jayway.jsonpath.JsonPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("考试试题解析正确率、错误率")
public class TestAccuracyRate {
	
	static String exam_pass_name = "PassRateExam" + CommonData.getStringRandom(5);
	String id = "";
	String user_name = UserBusiness.getUsername();
	String ques_id = "";
	@Test(description = "新增考试",priority = 1)
	public void init() {
		// 新增考试
		ExaminationTaskBusiness.creatRewardExamTask("1", "hide", "10", "100", "1", exam_pass_name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), "60", "0", "false", "2",
				CertificateBusiness.getIdByKeyword(BaseBusiness.certificate_name), "12", "0", "0", "0", "0", "true", "false",
				UserBusiness.getUserId(), "{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
		id = ExaminationTaskBusiness.getIdByKeyword(exam_pass_name);
	}
	@Test(description = "提交考试",priority = 2)
	public void testSubmitPassExam() {
		// 交卷
		ExaminationTaskBusiness.submitPassByIdExam(id);
	}

	@Test(description = "获取试题的id",priority = 3)
	public void testQueryQuestionAnalysis() {
		String res = ExaminationTaskBusiness.queryQuestionAnalysis(id,"","");
		ques_id = (String)JSONPath.read(res, "$.data.list[0].id");
	}
	
	@Test(description = "查看试题错误率",priority = 4)
	public void testQueryExamRateUserByfalse() {
		String res = ExaminationTaskBusiness.queryExamRateUser(id, ques_id, "false");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "查询成功","查看试题错误率，实际返回结果："+res);
	}
	
	@Test(description = "查看试题正确率",priority = 5)
	public void testQueryExamRateUserByTrue() {
		String res = ExaminationTaskBusiness.queryExamRateUser(id, ques_id, "true");
		String name = (String)JSONPath.read(res, "$.data.list[0].name");
		Assert.assertNotNull(name, "查看试题正确率，实际返回结果："+res);
	}
	
	@Test(description = "考试参与率，合格率查看" , priority = 6)
	public void queryList() {
		String res = ExaminationTaskBusiness.queryList(exam_pass_name, "0", "false", "");
		String joinRate = JsonPath.read(res, "$.list[0].joinRate");
		String unExamRate = JsonPath.read(res, "$.list[0].unExamRate");
		Assert.assertEquals(joinRate,"100","考试参与率，合格率查看"+res);
		Assert.assertEquals(unExamRate,"0","考试参与率，合格率查看"+res);
	}
	
	@Test(description = "删除考试",priority= 7)
	public void endTest() {
		ExaminationTaskBusiness.deleteExamTask(id);
	}
	
}
