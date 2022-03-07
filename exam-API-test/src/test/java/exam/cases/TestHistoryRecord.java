package exam.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.MyExamTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.StudyHistoryResult;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class TestHistoryRecord {

	String exam_name = "HistoryRecord"+CommonData.getStringRandom(5);
	@Test(description="",priority=1)
	public void testFailExam() {
		ExaminationTaskBusiness.creatRewardExamTask("1", "hide", "60", "100", "4", exam_name, DateUtil.getRegularDate(0),
				DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), "60", "0", "true", "1", "0", "60", "0", "2",
				"1", "0", "false", "false", UserBusiness.getUserId(),
				"{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
		
	}
	String exam_id = "";
	@Test(description = "获取考试id",priority = 2)
	public void testGetExamId() {
		exam_id = ExaminationTaskBusiness.getIdByKeyword(exam_name);
	}
	
	@Test(description = "交卷",priority = 3)
	public void submitBlankExam() {
		String res = MyExamTaskBusiness.submitBlankExam(exam_name);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!",""+res);
	}
	
	@Test(description="重考再次交卷",priority=4)
	public void submitPassExam() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String res = ExaminationTaskBusiness.submitPassExam(exam_name);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!",""+res);
	}
	@Test(description = "查看在当前时间之前的历史成绩-显示为空",priority = 5)
	public void testQueryHistoricalResult() {
		String res = StudyHistoryResult.queryHistoricalResult(exam_id, DateUtil.getTimeStamp(-30, ""), DateUtil.getTimeStamp(-10, ""));
		String total = (String)JSONPath.read(res, "$.total");
		Assert.assertEquals(total,"0","查看在当前时间之前的历史成绩"+res);
	}
	@Test(description = "不输入时间查询历史成绩时，显示全部结果",priority = 6)
	public void testQueryTimeIsNullHistoricalResult() {
		
//		String res = StudyHistoryResult.queryHistoricalResult(exam_id,"", "");
//		String total = (String)JSONPath.read(res, "$.total");
//		Long commit_time = (Long)JSONPath.read(res, "$.list[0].commit_time");
//		int durtion = (int)JSONPath.read(res, "$.list[0].duration");
//		Assert.assertNotNull(commit_time, "验证历史成绩的提交时间不为空"+res);
//		Assert.assertNotNull(durtion, "验证历史成绩的作答时长不为空"+res);
//		Assert.assertNotNull(total,"不输入时间查询历史成绩时，显示全部结果"+res);
	}
	@Test(description = "查询第一次考试的结果",priority = 7)
	public void testQueryFirstExamResult() {
		String res = StudyHistoryResult.queryExamResult(exam_id, "1");
		BigDecimal score = (BigDecimal)JSONPath.read(res, "$.score");
		BigDecimal result = new BigDecimal("0.0");
		Assert.assertEquals(score, result,"查询第一次考试的结果"+res);
	}
	@Test(description = "查询第二次考试的结果",priority = 8)
	public void testQuerySecondExamResult() {
		String res = StudyHistoryResult.queryExamResult(exam_id, "2");
		BigDecimal score = (BigDecimal)JSONPath.read(res, "$.score");
		BigDecimal result = new BigDecimal("100.0");
		Assert.assertEquals(score, result,"查询第一次考试的结果"+res);
	}
	@Test(description = "导出历史成绩",priority = 9)
	public void testExportHistoryResultCode() {
		int code = StudyHistoryResult.getExportHistoryResultCode(exam_id);
		Assert.assertEquals(code,200,"导出历史成绩,实际返回的code码："+code);
	}
	
	@Test(description = "删除考试任务",priority = 10)
	public void deleteExamTask() {
		ExaminationTaskBusiness.deleteExamTask(exam_id);
	}
}
