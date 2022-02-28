package study.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.study.business.MyStudyTask;
import cn.kxy.study.business.StudyHistoryResult;
import cn.kxy.study.business.StudyTaskBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class TestStudyHigherExamResult {
	String title_high = "HistoryNewExamResult"+CommonData.getStringRandom(4);
	String exam_title = "RecordNewExam"+CommonData.getStringRandom(5);
	String paper_id = PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name);
	String high_id = "";
	String high_exam_id = "";
	@Test(description = "新增记录最新成绩的学习任务",priority = 1)
	public void testAddExamHistoryResult() {
		String res = StudyHistoryResult.addExamHistoryResult(title_high, exam_title, paper_id, "1");
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！","添加线上学习任务，实际返回结果:"+res);
	}
	@Test(description = "在我的任务列表获取任务Id",priority = 2)
	public void testGetNewExamId() {
		String res = MyStudyTask.queryList(title_high,"1");
		high_id = (String)JSONPath.read(res, "$.list[0].id");
	}
	@Test(description = "查看我的学习任务详情",priority = 3)
	public void testQueryInfo() {
		String res = MyStudyTask.queryInfo(high_id);
		high_exam_id = (String)JSONPath.read(res, "$.data.stageList[0].courseMappingList[0].examId");
	}
	String first_ques_id = "";
	String second_ques_id = "";
	String wrong_answer_id = "";
	String right_answer_id = "";
	@Test(description = "查看考试详情",priority = 4)
	public void testQueryExamInfo() {
		String res = StudyHistoryResult.queryExamInfo(high_exam_id);
		first_ques_id = (String)JSONPath.read(res, "$.questions[0].id");
		second_ques_id =  (String)JSONPath.read(res, "$.questions[1].id");
		wrong_answer_id = (String)JSONPath.read(res, "$.questions[0].options[1].id");
		right_answer_id = (String)JSONPath.read(res, "$.questions[0].options[2].id");
	}
	
	@Test(description = "保存第一个问题的答案",priority = 5)
	public void saveAnswer() {
		StudyHistoryResult.saveAnswer(high_exam_id, first_ques_id, right_answer_id);
	}
	
	@Test(description = "保存第二个问题的答案",priority = 6)
	public void saveSecondAnswer() {
		StudyHistoryResult.saveAnswer(high_exam_id, second_ques_id, "\"2\",\"4\"");
		
	}
	
	@Test(description = "得满分的情况",priority = 7)
	public void testSubmitFullMark() {
		String res = StudyHistoryResult.submitAnswer(high_exam_id, first_ques_id, right_answer_id, second_ques_id, "\"2\",\"4\"", "3");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!",""+res);
	}
	
	@Test(description = "",priority = 8)
	public void queryExamInfo() {
		StudyHistoryResult.queryExamInfo(high_exam_id);
	}
	@Test(description = "只保存错误的单选题的答案",priority = 9)
	public void testOnlySaveSingle() {
		StudyHistoryResult.saveAnswer(high_exam_id, first_ques_id, wrong_answer_id);
	}
	
	@Test(description = "只提交错误得单选题的答案",priority = 10)
	public void testSubmit() {
		String res = StudyHistoryResult.submitAnswer(high_exam_id, first_ques_id, wrong_answer_id, second_ques_id, "", "1");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!",""+res);
	}
	
	@Test(priority = 11)
	public void test() {
		StudyHistoryResult.queryExamInfo(high_exam_id);
	}
	
	@Test(description = "第二次保存答案",priority = 12)
	public void testsaveAnswer() {
		StudyHistoryResult.saveAnswer(high_exam_id, first_ques_id, right_answer_id);
	}
	
	@Test (description = "保存正确的单选题" ,priority = 13)
	public void testOnlySaveRightSingle() {
		
		String res = StudyHistoryResult.submitAnswer(high_exam_id, first_ques_id, right_answer_id, second_ques_id, "", "2");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!",""+res);
	}
	@Test(description = "不输入时间查询历史成绩时，显示全部结果",priority = 14)
	public void testQueryTimeIsNullHistoricalResult() {
		String res = StudyHistoryResult.queryHistoricalResult(high_exam_id,"", "");
		Long commit_time = (Long)JSONPath.read(res, "$.list[0].commit_time");
		int durtion = (int)JSONPath.read(res, "$.list[0].duration");
		Assert.assertNotNull(commit_time, "验证历史成绩的提交时间不为空"+res);
		Assert.assertNotNull(durtion, "验证历史成绩的作答时长不为空"+res);
	}
	@Test(description = "查询第一次考试的结果",priority = 15)
	public void testQueryFirstExamResult() {
		String res = StudyHistoryResult.queryExamResult(high_exam_id, "1");
		BigDecimal score = (BigDecimal)JSONPath.read(res, "$.score");
		BigDecimal result = new BigDecimal("100.0");
		Assert.assertEquals(score, result,"查询第一次考试的结果"+res);
	}
	@Test(description = "查询第二次考试的结果",priority = 16)
	public void testQuerySecondExamResult() {
		String res = StudyHistoryResult.queryExamResult(high_exam_id, "2");
		BigDecimal score = (BigDecimal)JSONPath.read(res, "$.score");
		BigDecimal result = new BigDecimal("0.0");
		Assert.assertEquals(score, result,"查询第一次考试的结果"+res);
	}
	@Test(description = "查询第三次考试的结果",priority = 17)
	public void testQueryThirdResult() {
		String res = StudyHistoryResult.queryExamResult(high_exam_id, "3");
		BigDecimal score = (BigDecimal)JSONPath.read(res, "$.score");
		BigDecimal result = new BigDecimal("40.0");
		Assert.assertEquals(score, result,"查询第一次考试的结果"+res);
	}
	@Test(description = "删除学习任务" , priority = 18)
	public void testDeleteStudyTask() {
		String res = StudyTaskBusiness.deleteStudyTask(high_id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除学习计划成功", "删除考试任务，实际返回结果:" + res);
	}
}
