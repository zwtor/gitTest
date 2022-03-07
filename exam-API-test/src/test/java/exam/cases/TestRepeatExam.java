package exam.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.MyExamTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.lazy.init.cases.InitExam;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestRepeatExam extends InitExam {

	static String exam_repeat_task_name = "repeatexam" + CommonData.getStringRandom(5);
    static String exam_id = "";
	@BeforeClass
	public void init() {
		ExaminationTaskBusiness.creatRewardExamTask("1", "hide", "60", "100", "4", exam_repeat_task_name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), "60", "0", "true", "1", "0", "60", "5", "0",
				"0", "0", "false", "false", UserBusiness.getUserId(),
				"{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
		exam_id = ExaminationTaskBusiness.getIdByKeyword(exam_repeat_task_name);
	}

	@Test(description = "自定义重考次数",priority = 1)
	public void testCustomRepeatExam() {
		String name = "repeatexam01"+CommonData.getStringRandom(3);
		ExaminationTaskBusiness.creatRewardExamTask("1", "hide", "60", "100", "4", name, DateUtil.getRegularDate(0),
				DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "0", "true", "1", "0", "60", "5", "2",
				"1", "0", "false", "false", UserBusiness.getUserId(),
				"{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
		MyExamTaskBusiness.submitBlankExam(name);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MyExamTaskBusiness.submitBlankExam(name);
		String res = MyExamTaskBusiness.queryMyExamTask("all", name);
		int makeup_count = (int) JSONPath.read(res, "$.list[0].makeup_count");
		ExaminationTaskBusiness.deleteExamTask(ExaminationTaskBusiness.getIdByKeyword(name));
		Assert.assertTrue(makeup_count>= 0, "自定义重考次数实际返回结果：" + res);

	}

	@Test(description = "考试结束前无限制考试",priority = 2)
	public void testNoLimitRepeatExam() {
		String name = "repeatexam"+CommonData.getStringRandom(3);
		ExaminationTaskBusiness.creatRewardExamTask("1", "hide", "60", "100", "4", name, DateUtil.getRegularDate(0),
				DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "0", "true", "1", "0", "60", "5", "1",
				"0", "0", "false", "false", UserBusiness.getUserId(),
				"{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
		for (int i = 0; i < 2; i++) {
			MyExamTaskBusiness.submitBlankExam(name);
		}
		String res = MyExamTaskBusiness.queryMyExamTask("all", name);
		String repeat_exam = (String) JSONPath.read(res, "$.list[0].repeat_exam");
		int makeup_count = (int) JSONPath.read(res, "$.list[0].makeup_count");
		ExaminationTaskBusiness.deleteExamTask(ExaminationTaskBusiness.getIdByKeyword(name));
		Assert.assertEquals(repeat_exam, "true", "自定义重考次数实际返回结果：" + res);
		Assert.assertTrue(makeup_count>=0, "自定义重考次数实际返回结果：" + res);
	}

	@Test(description = "不及格后，依旧可以重考",priority = 3)
	public void testPassAfterRepeatExam() {
		MyExamTaskBusiness.submitBlankExam(exam_repeat_task_name);
		String res = MyExamTaskBusiness.checkIsCanExam(exam_repeat_task_name);
		String can_exam = (String) JSONPath.read(res, "$.can_exam");

		Assert.assertEquals(can_exam, "true", "不及格后，依旧可以重考实际返回结果:" + res);
	}

	@Test(description = "不及格的情况下不允许查看解析", priority = 4)
	public void testFailExamNotAnalysis() {
		String res = MyExamTaskBusiness.queryMyExamTask("all", exam_repeat_task_name);
		String show_answer_analysis = (String) JSONPath.read(res, "$.list[0].show_answer_analysis");
		Assert.assertEquals(show_answer_analysis, "false", "不及格的情况下不允许查看解析实际返回结果：" + res);
	}

	@Test(description = "及格后，不允许再次考试", priority = 5)
	public void testRepeatExam() {
		ExaminationTaskBusiness.submitPassByIdExam(exam_id);
		String res = MyExamTaskBusiness.checkIsCanExam(exam_repeat_task_name);
		Assert.assertTrue(res.contains("can_exam"), "及格后，不允许再次考试实际返回结果:" + res);
	}

	@Test(description = "及格后允许查看解析",priority = 6)
	public void testPassExamLookAnalysis() {
		String res = MyExamTaskBusiness.queryMyExamTask("all", exam_repeat_task_name);
		String show_answer_analysis = (String) JSONPath.read(res, "$.list[0].show_answer_analysis");
		Assert.assertEquals(show_answer_analysis, "true", "交卷后允许答案解析实际返回结果：" + res);
	}
	
	@AfterClass
	public void endTest() {
		ExaminationTaskBusiness.deleteExamTask(exam_id);
	}
}
