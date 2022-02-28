package newemployee.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.MyExamTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.MyStudyTask;
import cn.kxy.study.business.NewEmployeeTrainBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNewEmployeeMarking {

	String name = "NewExamMarking"+CommonData.getStringRandom(5);
	@Test(description = "新增带有考试的新员工任务",priority = 1)
	public void addNewExam() {
		String res = NewEmployeeTrainBusiness.addNewExam(name, PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name));
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新建成功","新增带有考试的新员工任务"+res);
	}
	String user_id = UserBusiness.getUserId();
	String  id ;
	int status ;
	String planId ;
	@Test(description = "获取id",priority = 2)
	public void testGetId() {
		String res = NewEmployeeTrainBusiness.queryList(name, "false", "0", "0","","");
		id = (String)JSONPath.read(res, "$.list[0].id");
		status = (int)JSONPath.read(res,"$.list[0].status");
		planId = (String)JSONPath.read(res, "$.list[0].planId");
	}
	@Test(description="启用新员工培训",priority = 3)
	public void testBeginTask() {
		if (status==2) {
			String res = NewEmployeeTrainBusiness.beginAndStopTask(id, "1");
			String msg = (String)JSONPath.read(res, "$.msg");
			Assert.assertEquals(msg, "启用成功","启用新员工培训,实际返回结果："+res);
		}
	}
	@Test(description = "按照人指派新员工培训",priority = 4)
	public void testAssignNewTaskByUserId() {
		String res = NewEmployeeTrainBusiness.assignNewTask(id, "", user_id, "", "");
		String result = (String)JSONPath.read(res, "$.result");
		Assert.assertEquals(result,"指派成功", "按照人指派新员工培训："+res);
	}
	String my_id = "";
	String exam_id = "";
	@Test(description="查看我的培训任务列表",priority=5)
	public void testQueryList() {
		String res = MyStudyTask.queryList(name,"1");
		my_id = (String)JSONPath.read(res, "$.list[0].id");
	}
	@Test(description = "获取考试id",priority = 6)
	public void testGetExamId() {
		String res = MyStudyTask.queryInfo(my_id);
		exam_id = (String)JSONPath.read(res, "$.data.courseInfo[0].examId");
	}
	@Test(description = "提交试卷",priority = 7)
	public void testSubmitPassExam() {
		ExaminationTaskBusiness.submitPassByIdExam(exam_id);
	}
	String mark_id= "";
	@Test(description = "查看新员工待阅卷列表", priority = 8)
	public void testQueryExamMarking() {
		String res = NewEmployeeTrainBusiness.queryExamMarking(planId,UserBusiness.getUsername());
		mark_id = (String)JSONPath.read(res, "$.page_info.list[0].plan_id");
	}
	@Test(description = "批阅试卷",priority = 9)
	public void testSubmitMarkingPaper() {
		String res = NewEmployeeTrainBusiness.getTrainExamPlanPendingList(mark_id);
		String id =(String)JSONPath.read(res, "$.data.paperVo.showQuestionInfo[0].answer.bizQuestionOptionList[0].questionId");
		String res0 = ExaminationTaskBusiness.submitMarkingPaper(mark_id, id, "60");
		String msg = (String)JSONPath.read(res0, "$.msg");
		Assert.assertEquals(msg,"批阅成功",""+res0);
	}
	@Test(description = "查看试题分析页面是否显示重新阅卷按钮",priority = 10)
	public void testQueryAnswerAnalysis() {
		String res = MyExamTaskBusiness.queryAnswerAnalysisById(mark_id);
		String repeat_marking = (String)JSONPath.read(res, "$.repeat_marking");
		Assert.assertEquals(repeat_marking, "true",""+res);
	}
	
	@Test(description = "清除所有导出数据",priority = 11)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	
	@Test(description="导出考试结果--通过用户名查看",priority=12)
	public void testGetExportExamResultCodeByName() {
		int code = ExaminationTaskBusiness.getExportExamResultCode(mark_id);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出考试结果数据，实际状态码为："+code);
	}
	@Test(description="导出考试结果--无搜索条件的情况",priority=13)
	public void testGetExportExamResultCode() {
		int code = ExaminationTaskBusiness.getExportExamResultCode(mark_id, "");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出考试结果--无搜索条件的情况，实际状态码为："+code);
	}
	
	String user_name = UserBusiness.getUsername();
	
	@Test(description="导出学员作答明细--通过用户名查看",priority=14)
	public void testGetExportExamDetailCodeByName() {
		int code = ExaminationTaskBusiness.getExportExamDetailCode(mark_id, user_name,"0");
		Assert.assertEquals(code, 200,"导出学员作答明细--通过用户名查看，实际状态码为："+code);
	}
	@Test(description="导出学员作答明细--无搜索条件的情况",priority=15)
	public void testGetExportExamDetailCode() {
		int code = ExaminationTaskBusiness.getExportExamDetailCode(mark_id, "");
		Assert.assertEquals(code, 200,"导出学员作答明细--无搜索条件的情况，实际状态码为："+code);
	}
	@Test(description="导出试题作答明细--通过用户名查看",priority=16)
	public void testGetExportAnswerDetailCodeByName() {
		int code = ExaminationTaskBusiness.getExportAnswerDetailCode(mark_id, user_name);
		Assert.assertEquals(code, 200,"导出学员作答明细--通过用户名查看，实际状态码为："+code);
	}
	@Test(description="导出试题作答明细--无搜索条件的情况",priority=17)
	public void testGetExportAnswerDetailCode() {
		int code = ExaminationTaskBusiness.getExportAnswerDetailCode(mark_id, "");
		Assert.assertEquals(code, 200,"导出试题作答明细--无搜索条件的情况，实际状态码为："+code);
	}
	@Test(description="导出试题分析--全部试题类型的情况",priority=18)
	public void testGetExportQuestionAnalysisCodeAll() {
		int code = ExaminationTaskBusiness.getExportQuestionAnalysisCode(mark_id, "");
		Assert.assertEquals(code, 200,"导出试题分析--全部试题类型的情况，实际状态码为："+code);
	}
	@Test(description="导出试题分析--单选题类型的情况",priority=19)
	public void testGetExportQuestionAnalysisCodeSingle() {
		int code = ExaminationTaskBusiness.getExportQuestionAnalysisCode(mark_id, "1");
		Assert.assertEquals(code, 200,"导出试题分析--单选题类型的情况，实际状态码为："+code);
	}
	@Test(description="导出试题分析--多选试题类型的情况",priority=20)
	public void testGetExportQuestionAnalysisCodeMult() {
		int code = ExaminationTaskBusiness.getExportQuestionAnalysisCode(mark_id, "2");
		Assert.assertEquals(code, 200,"导出试题分析--多选试题类型的情况，实际状态码为："+code);
	}
	@Test(description="导出试题分析--判断试题类型的情况",priority=21)
	public void testGetExportQuestionAnalysisCodeJudge() {
		int code = ExaminationTaskBusiness.getExportQuestionAnalysisCode(mark_id, "3");
		Assert.assertEquals(code, 200,"导出试题分析--判断试题类型的情况，实际状态码为："+code);
	}
	
	@Test(description = "查看导出的结果",priority = 22)
	public void exportRecordList() {
		String res = PaperExportBusiness.exportRecordList();
		String status_1 = (String)JSONPath.read(res, "$.list[0].status");
		String status_2 = (String)JSONPath.read(res, "$.list[1].status");
		String status_3 = (String)JSONPath.read(res, "$.list[2].status");
		String status_4 = (String)JSONPath.read(res, "$.list[3].status");
		String status_5 = (String)JSONPath.read(res, "$.list[4].status");
		String status_6 = (String)JSONPath.read(res, "$.list[5].status");
		String status_7 = (String)JSONPath.read(res, "$.list[6].status");
		String status_8 = (String)JSONPath.read(res, "$.list[7].status");
		String status_9 = (String)JSONPath.read(res, "$.list[8].status");
		String status_10 = (String)JSONPath.read(res, "$.list[9].status");
		Assert.assertFalse(status_1=="FAILED", "查看导出的结果:"+res);	
		Assert.assertFalse(status_2=="FAILED", "查看导出的结果:"+res);	
		Assert.assertFalse(status_3=="FAILED", "查看导出的结果:"+res);	
		Assert.assertFalse(status_4=="FAILED", "查看导出的结果:"+res);	
		Assert.assertFalse(status_5=="FAILED", "查看导出的结果:"+res);	
		Assert.assertFalse(status_6=="FAILED", "查看导出的结果:"+res);	
		Assert.assertFalse(status_7=="FAILED", "查看导出的结果:"+res);	
		Assert.assertFalse(status_8=="FAILED", "查看导出的结果:"+res);	
		Assert.assertFalse(status_9=="FAILED", "查看导出的结果:"+res);	
		Assert.assertFalse(status_10=="FAILED", "查看导出的结果:"+res);	
	}
	
	@Test(description = "删除新员工任务",priority = 23)
	public void testDeleteStageEmployTrainTask() {
		String res = NewEmployeeTrainBusiness.deleteEmployTrainTask(planId);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功","删除新员工培训,实际返回结果："+res);
	}
	
}
