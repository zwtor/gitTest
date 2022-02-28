package study.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.study.business.MyStudyTask;
import cn.kxy.study.business.StudyHistoryResult;
import cn.kxy.study.business.StudyTaskBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class TestStudyNewHistoryResult {
	String title_new = "HistoryNewExamResult"+CommonData.getStringRandom(4);
	String exam_title = "RecordNewExam"+CommonData.getStringRandom(5);
	String paper_id = PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name);
	String new_id = "";
	String new_exam_id = "";
	@Test(description = "新增记录最新成绩的学习任务",priority = 1)
	public void testAddExamHistoryResult() {
		String res = StudyHistoryResult.addExamHistoryResult(title_new, exam_title, paper_id, "0");
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！","添加线上学习任务，实际返回结果:"+res);
	}
	@Test(description = "在我的任务列表获取任务Id",priority = 2)
	public void testGetNewExamId() {
		String res = MyStudyTask.queryList(title_new,"1");
		new_id = (String)JSONPath.read(res, "$.list[0].id");
	}
	@Test(description = "查看我的学习任务详情",priority = 3)
	public void testQueryInfo() {
		String res = MyStudyTask.queryInfo(new_id);
		new_exam_id = (String)JSONPath.read(res, "$.data.stageList[0].courseMappingList[0].examId");
	}
	String first_ques_id = "";
	String second_ques_id = "";
	String wrong_answer_id = "";
	String right_answer_id = "";
	@Test(description = "查看考试详情",priority = 4)
	public void testQueryExamInfo() {
		String res = StudyHistoryResult.queryExamInfo(new_exam_id);
		first_ques_id = (String)JSONPath.read(res, "$.questions[0].id");
		second_ques_id =  (String)JSONPath.read(res, "$.questions[1].id");
		wrong_answer_id = (String)JSONPath.read(res, "$.questions[0].options[1].id");
		right_answer_id = (String)JSONPath.read(res, "$.questions[0].options[2].id");
	}
	@Test(description = "只保存错误的单选题的答案",priority = 5)
	public void testOnlySaveSingle() {
		StudyHistoryResult.saveAnswer(new_exam_id, first_ques_id, wrong_answer_id);
	}
	@Test(description = "只提交错误得单选题的答案",priority = 6)
	public void testSubmit() {
		String res = StudyHistoryResult.submitAnswer(new_exam_id, first_ques_id, wrong_answer_id, second_ques_id, "", "1");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!",""+res);
	}
	
	@Test(description = "",priority = 7)
	public void testqueryExamInfo() {
		StudyHistoryResult.queryExamInfo(new_exam_id);
	}
	
	@Test(description = "保存答案",priority = 8)
	public void saveAnswer() {
		StudyHistoryResult.saveAnswer(new_exam_id, first_ques_id, right_answer_id);
	}
	
	@Test (description = "保存正确的单选题" ,priority = 9)
	public void testOnlySaveRightSingle() {
		String res = StudyHistoryResult.submitAnswer(new_exam_id, first_ques_id, right_answer_id, second_ques_id, "", "2");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!",""+res);
	}
	
	@Test(description = "",priority = 10)
	public void queryExamInfo() {
		StudyHistoryResult.queryExamInfo(new_exam_id);
	}
	
	@Test(description = "保存第一个答案",priority =11)
	public void testsaveAnswer() {
		StudyHistoryResult.saveAnswer(new_exam_id, first_ques_id, right_answer_id);
	}
	
	@Test(description = "保存第二个答案",priority = 12)
	public void testSaveAnswer() {
		StudyHistoryResult.saveAnswer(new_exam_id, second_ques_id, "\"2\",\"4\"");
	}
	
	@Test(description = "再次重考，得满分的情况",priority = 13)
	public void testSubmitFullMark() {
		String res = StudyHistoryResult.submitAnswer(new_exam_id, first_ques_id, right_answer_id, second_ques_id, "\"2\",\"4\"", "3");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!",""+res);
	}
	
	@Test(description = "查看在当前时间之前的历史成绩-显示为空",priority = 14)
	public void testQueryHistoricalResult() {
		String res = StudyHistoryResult.queryHistoricalResult(new_exam_id, DateUtil.getTimeStamp(-30, ""), DateUtil.getTimeStamp(-10, ""));
		String total = (String)JSONPath.read(res, "$.total");
		Assert.assertEquals(total,"0","查看在当前时间之前的历史成绩"+res);
	}
	@Test(description = "不输入时间查询历史成绩时，显示全部结果",priority = 15)
	public void testQueryTimeIsNullHistoricalResult() {
//		BigDecimal exp_first = new BigDecimal("100.0") ;
//		BigDecimal exp_second= new BigDecimal("40.0") ;
//		BigDecimal exp_third= new BigDecimal("0.0") ;
		String res = StudyHistoryResult.queryHistoricalResult(new_exam_id,"", "");
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
	@Test(description = "查询第一次考试的结果",priority = 16)
	public void testQueryFirstExamResult() {
		String res = StudyHistoryResult.queryExamResult(new_exam_id, "1");
		BigDecimal score = (BigDecimal)JSONPath.read(res, "$.score");
		BigDecimal result = new BigDecimal("0.0");
		Assert.assertEquals(score, result,"查询第一次考试的结果"+res);
	}
	@Test(description = "查询第二次考试的结果",priority = 17)
	public void testQuerySecondExamResult() {
		String res = StudyHistoryResult.queryExamResult(new_exam_id, "2");
		BigDecimal score = (BigDecimal)JSONPath.read(res, "$.score");
		BigDecimal result = new BigDecimal("40.0");
		Assert.assertEquals(score, result,"查询第一次考试的结果"+res);
	}
	@Test(description = "查询第三次考试的结果",priority = 18)
	public void testQueryThirdResult() {
		String res = StudyHistoryResult.queryExamResult(new_exam_id, "3");
		BigDecimal score = (BigDecimal)JSONPath.read(res, "$.score");
		BigDecimal result = new BigDecimal("100.0");
		Assert.assertEquals(score, result,"查询第一次考试的结果"+res);
	}
	@Test(description = "删除学习任务" , priority = 19)
	public void testDeleteStudyTask() {
		String res = StudyTaskBusiness.deleteStudyTask(new_id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除学习计划成功", "删除考试任务，实际返回结果:" + res);
	}
	String tltle_mark = "MarkStudyHistory"+CommonData.getStringRandom(4);
	String mark_id = "";
	@Test(description = "添加人工阅卷的学习任务--记录最新成绩",priority = 20)
	public void testAddManualMarking () {
		String res = StudyHistoryResult.addManualMarking(tltle_mark, paper_id);
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！","添加线上学习任务，实际返回结果:"+res);
	}
	String first_mark_ques_id = "";
	String second_mark_ques_id = "";
	String wrong_mark_answer_id = "";
	String right_mark_answer_id = "";
	String mark_exam_id = "";
	
	@Test(description = "获取考试的id",priority = 21)
	public void testGetExamId() {
		String res = MyStudyTask.queryList(tltle_mark,"1");
		mark_id = (String)JSONPath.read(res, "$.list[0].id");
	}
	
	@Test(description = "获取阅卷的id",priority =22)
	public void testGetMarketId() {
		String my_res = MyStudyTask.queryInfo(mark_id);
		mark_exam_id = (String)JSONPath.read(my_res, "$.data.stageList[0].courseMappingList[0].examId");
	}
	
	@Test(description = "查询人工阅卷的考试详情", priority = 23)
	public void testQueryMarkExamInfo() {
	
		String info_res = StudyHistoryResult.queryExamInfo(mark_exam_id);
		first_mark_ques_id = (String)JSONPath.read(info_res, "$.questions[0].id");
		second_mark_ques_id =  (String)JSONPath.read(info_res, "$.questions[1].id");
		wrong_mark_answer_id = (String)JSONPath.read(info_res, "$.questions[0].options[1].id");
		right_mark_answer_id = (String)JSONPath.read(info_res, "$.questions[0].options[2].id");
	}
	
	@Test(description = "再次保存答案",priority = 24)
	public void saveSeAnswer() {
		StudyHistoryResult.saveAnswer(mark_exam_id, first_mark_ques_id, second_mark_ques_id);
	}
	
	@Test(description = "人工阅卷得满分的情况",priority = 25)
	public void testSubmitFullMarking() {
		String res = StudyHistoryResult.submitAnswer(mark_exam_id, first_mark_ques_id, right_mark_answer_id, second_mark_ques_id, "\"2\",\"4\"", "1");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!",""+res);
	}
	
	@Test(description = "查询学习任务的考试待阅卷列表",priority = 26)
	public void testQueryStudyExamMark() {
		String res = StudyTaskBusiness.queryStudyExamMark(mark_id, "");
		String name = (String)JSONPath.read(res, "$.page_info.list[0].name");
		String paper_name = (String)JSONPath.read(res, "$.page_info.list[0].paper_name");
		String paper_status = (String)JSONPath.read(res, "$.page_info.list[0].paper_status");
		Assert.assertNotNull(paper_status, "查询学习任务的考试待阅卷列表,试卷状态进行验证"+res);
		Assert.assertNotNull(paper_name, "查询学习任务的考试待阅卷列表,试卷名称进行验证"+res);
		Assert.assertNotNull(name, "查询学习任务的考试待阅卷列表,姓名进行验证"+res);
	}
	@Test(description = "批阅学习项目的考试",priority = 27)
	public void testSubmitMarkingPaper() {
		String res = ExaminationTaskBusiness.submitMarkingPaper(mark_exam_id,second_mark_ques_id,"60");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "批阅成功","批阅学习项目的考试，实际返回结果："+res);
	}
	@Test(description = "人工阅卷再次得满分的情况",priority = 28)
	public void testSubmitAgainFullMarking() {
		StudyHistoryResult.queryExamInfo(mark_exam_id);
		StudyHistoryResult.saveAnswer(mark_exam_id, first_mark_ques_id, second_mark_ques_id);
		StudyHistoryResult.saveAnswer(mark_exam_id, second_mark_ques_id, "\"2\",\"4\"");
		String res = StudyHistoryResult.submitAnswer(mark_exam_id, first_mark_ques_id, right_mark_answer_id, second_mark_ques_id, "\"2\",\"4\"", "1");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交试卷成功!",""+res);
	}
	
	@Test(description = "含有人工阅卷的时候，查看历史成绩列表",priority = 29)
	public void testQueryHistoricalMarkResult() {
		String res = StudyHistoryResult.queryHistoricalResult(mark_exam_id,"", "");
		String first_result = (String)JSONPath.read(res, "$.list[0].result");
		String second_result = (String)JSONPath.read(res, "$.list[1].result");
//		Assert.assertNotNull(second_result,"含有人工阅卷的时候，查看历史成绩列表，实际返回结果："+res);
//		Assert.assertEquals(first_result, "待阅卷","含有人工阅卷的时候，查看历史成绩列表，实际返回结果："+res);
	}
	@Test(description = "导出历史成绩",priority = 30)
	public void testExportHistoryResultCode() {
//		int code = StudyHistoryResult.getExportHistoryResultCode(mark_exam_id);
//		Assert.assertEquals(code,200,"导出历史成绩,实际返回的code码："+code);
	}
	
	@Test(description = "删除人工阅卷的学习任务" , priority = 31)
	public void testDeleteMarkStudyTask() {
		StudyTaskBusiness.deleteStudyTask(mark_id);
	}
	
}
