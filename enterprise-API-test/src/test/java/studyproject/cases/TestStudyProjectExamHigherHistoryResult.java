package studyproject.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.study.business.StudyHistoryResult;
import cn.kxy.study.business.StudyProjectBusiness;
import cn.kxy.study.business.StudyProjectExamHistoryResult;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class TestStudyProjectExamHigherHistoryResult {
	String title = "StudyProjectHigherHistory"+CommonData.getStringRandom(5);
	String paper_id = PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name);
	String id = "";
	@Test(description = "新增记录最高成绩的学习项目并启用",priority = 1)
	public void testAddHistoryResultProject() {
		String res = StudyProjectExamHistoryResult.addHistoryResultProject(title, paper_id, "1");
		String pro_res = StudyProjectBusiness.queryLearningProjectList(title, "");
		id = (String) JSONPath.read(pro_res, "$.list[0].id"); 
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
		exam_id = (String)JSONPath.read(res, "$.stages[0].resources[0].course_id");
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
//		String res = StudyHistoryResult.queryHistoricalResult(exam_id,"", "");
//		String total = (String)JSONPath.read(res, "$.total");
//		Assert.assertNotNull(total,"不输入时间查询历史成绩时，显示全部结果"+res);
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
	
	@Test(description = "清除所有导出数据",priority = 13)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	
	@Test(description = "导出学习项目",priority = 14)
	public void testExportStudyProjects() {
		String res = StudyProjectBusiness.exportStudyProjects("");
		Assert.assertTrue(res.contains("导出学习项目成功"));
	}
	
	@Test(description = "查看导出的结果",priority = 15)
	public void exportRecordList() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String res = PaperExportBusiness.exportRecordList();
		String status = (String)JSONPath.read(res, "$.list[0].status");
		String type = (String)JSONPath.read(res, "$.list[0].type");
		Assert.assertFalse(status=="FAILED", "查看导出的结果:"+res);
		Assert.assertEquals(type, "STUDY_PROJECTS_EXPORT");
	}
	@Test(description = "删除学习项目" , priority = 16)
	public void testDeleteStudyTask() {
		StudyProjectBusiness.deleteStudyProject(id);
	}
}
