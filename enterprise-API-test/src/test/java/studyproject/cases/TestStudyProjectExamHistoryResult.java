package studyproject.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.study.business.StudyHistoryResult;
import cn.kxy.study.business.StudyProjectBusiness;
import cn.kxy.study.business.StudyProjectExamHistoryResult;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class TestStudyProjectExamHistoryResult {

	String title = "StudyProjectNewHistory"+CommonData.getStringRandom(5);
	String paper_id = PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name);
	String id = "";
	@Test(description = "新增记录最新成绩的学习项目并启用",priority = 1)
	public void testAddHistoryResultProject() {
		String res = StudyProjectExamHistoryResult.addHistoryResultProject(title, paper_id, "0");
		id = (String) JSONPath.read(res, "$.id"); 
		course_id = (String)JSONPath.read(res, "$.course_id");
		String res0 = StudyProjectBusiness.updateStatus(id, "normal");
		String update = (String) JSONPath.read(res0, "$.update");
		Assert.assertEquals(update, "true", "启用学习项目后,实际返回结果：" + res0);
	}
	String course_id = "";
	String exam_id = "";
	@Test(description = "查询课程详情页",priority = 2)
	public void testQueryProjectCourse() {
		StudyProjectBusiness.loadProject(course_id);
		String res = StudyProjectBusiness.queryProjectCourse(course_id);
		exam_id = (String)JSONPath.read(res, "$.stages[0].resources[0].exam.id");
	}
	String first_ques_id = "";
	String second_ques_id = "";
	String wrong_answer_id = "";
	String right_answer_id = "";
	@Test(description = "开始考试",priority = 3)
	public void testStudyExam() {
		StudyProjectBusiness.startStudy(course_id);
		String res = StudyHistoryResult.queryExamInfo(exam_id);
		first_ques_id = (String)JSONPath.read(res, "$.questions[0].id");
		second_ques_id =  (String)JSONPath.read(res, "$.questions[1].id");
		wrong_answer_id = (String)JSONPath.read(res, "$.questions[0].options[1].id");
		right_answer_id = (String)JSONPath.read(res, "$.questions[0].options[2].id");
	}
	@Test(description = "只保存错误的单选题的答案",priority = 4)
	public void testOnlySaveSingle() {
		StudyHistoryResult.saveAnswer(exam_id, first_ques_id, wrong_answer_id);
	}
	@Test(description = "只提交错误得单选题的答案",priority = 5)
	public void testSubmit() {
		String res = StudyHistoryResult.submitAnswer(exam_id, first_ques_id, wrong_answer_id, second_ques_id, "", "1");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!",""+res);
	}
	@Test (description = "保存正确的单选题" ,priority = 6)
	public void testOnlySaveRightSingle() {
		StudyHistoryResult.queryExamInfo(exam_id);
		StudyHistoryResult.saveAnswer(exam_id, first_ques_id, right_answer_id);
		String res = StudyHistoryResult.submitAnswer(exam_id, first_ques_id, right_answer_id, second_ques_id, "", "2");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!",""+res);
	}
	@Test(description = "再次重考，得满分的情况",priority = 7)
	public void testSubmitFullMark() {
		StudyHistoryResult.queryExamInfo(exam_id);
		StudyHistoryResult.saveAnswer(exam_id, first_ques_id, right_answer_id);
		StudyHistoryResult.saveAnswer(exam_id, second_ques_id, "\"2\",\"4\"");
		String res = StudyHistoryResult.submitAnswer(exam_id, first_ques_id, right_answer_id, second_ques_id, "\"2\",\"4\"", "3");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!",""+res);
	}
	
	@Test(description = "查看在当前时间之前的历史成绩-显示为空",priority = 8)
	public void testQueryHistoricalResult() {
		String res = StudyHistoryResult.queryHistoricalResult(exam_id, DateUtil.getTimeStamp(-30, ""), DateUtil.getTimeStamp(-10, ""));
		String total = (String)JSONPath.read(res, "$.total");
		Assert.assertEquals(total,"0","查看在当前时间之前的历史成绩"+res);
	}
	@Test(description = "不输入时间查询历史成绩时，显示全部结果",priority = 9)
	public void testQueryTimeIsNullHistoricalResult() {
//		BigDecimal exp_first = new BigDecimal("100.0") ;
//		BigDecimal exp_second= new BigDecimal("40.0") ;
//		BigDecimal exp_third= new BigDecimal("0.0") ;
		String res = StudyHistoryResult.queryHistoricalResult(exam_id,"", "");
		String total = (String)JSONPath.read(res, "$.total");
//		String first_result = (String)JSONPath.read(res, "$.list[0].result");
//		BigDecimal first_score = (BigDecimal)JSONPath.read(res, "$.list[0].score");
//		String second_result = (String)JSONPath.read(res, "$.list[1].result");
//		BigDecimal second_score = (BigDecimal)JSONPath.read(res, "$.list[1].score");
//		String third_result = (String)JSONPath.read(res, "$.list[2].result");
//		BigDecimal third_score = (BigDecimal)JSONPath.read(res, "$.list[2].score");
//		Long commit_time = (Long)JSONPath.read(res, "$.list[0].commit_time");
//		int durtion = (int)JSONPath.read(res, "$.list[0].duration");
//		int socre_rule = (int)JSONPath.read(res, "$.list[0].score_rule");
//		Assert.assertEquals(socre_rule,2,"验证显示满分标签"+res);
//		Assert.assertNotNull(commit_time, "验证历史成绩的提交时间不为空"+res);
//		Assert.assertNotNull(durtion, "验证历史成绩的作答时长不为空"+res);
//		Assert.assertEquals(first_score,exp_first,"不输入时间查询历史成绩时,校验最新一次的考试分数为100"+res);
//		Assert.assertEquals(first_result, "满分","不输入时间查询历史成绩时,校验最新一次的考试结果为满分"+res);
//		Assert.assertEquals(second_score,exp_second,"不输入时间查询历史成绩时,校验第二次的考试分数为40"+res);
//		Assert.assertEquals(second_result, "不及格","不输入时间查询历史成绩时,校验第二次的考试结果为不及格"+res);
//		Assert.assertEquals(third_score,exp_third,"不输入时间查询历史成绩时,校验第一次的考试分数为0"+res);
//		Assert.assertEquals(third_result, "不及格","不输入时间查询历史成绩时,校验第一次的考试结果为不及格"+res);
		Assert.assertEquals(total,"3","不输入时间查询历史成绩时，显示全部结果"+res);
	}
	@Test(description = "查询第一次考试的结果",priority = 10)
	public void testQueryFirstExamResult() {
		String res = StudyHistoryResult.queryExamResult(exam_id, "1");
		BigDecimal score = (BigDecimal)JSONPath.read(res, "$.score");
		BigDecimal result = new BigDecimal("0.0");
		Assert.assertEquals(score, result,"查询第一次考试的结果"+res);
	}
	@Test(description = "查询第二次考试的结果",priority = 11)
	public void testQuerySecondExamResult() {
		String res = StudyHistoryResult.queryExamResult(exam_id, "2");
		BigDecimal score = (BigDecimal)JSONPath.read(res, "$.score");
		BigDecimal result = new BigDecimal("40.0");
		Assert.assertEquals(score, result,"查询第二次考试的结果"+res);
	}
	@Test(description = "查询第三次考试的结果",priority = 12)
	public void testQueryThirdResult() {
		String res = StudyHistoryResult.queryExamResult(exam_id, "3");
		BigDecimal score = (BigDecimal)JSONPath.read(res, "$.score");
		BigDecimal result = new BigDecimal("100.0");
		Assert.assertEquals(score, result,"查询第三次考试的结果"+res);
	}
	@Test(description = "删除学习项目" , priority = 13)
	public void testDeleteStudyTask() {
		String res = StudyProjectBusiness.deleteStudyProject(id);
		String msg = (String) JSONPath.read(res, "$.deleted");
		Assert.assertEquals(msg, "true", "删除学习项目，实际返回结果:" + res);
	}
}
