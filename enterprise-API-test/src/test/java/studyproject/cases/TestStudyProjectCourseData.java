package studyproject.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.investigationresearch.business.QuestionnaireBusiness;
import cn.kxy.setting.bussiness.AuthUserBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.*;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = {"CI"})
public class TestStudyProjectCourseData extends InitStudyAuthCourse{
	String self_name = "StudyProjectCourse"+CommonData.getStringRandom(5);
	String classification_id = ClassificationBusines.getPrimaryId();
	String study_course_id = "";
	String user_name = UserBusiness.getUsername();
	String courseId="";
	String exam_id ="";
	String ins_id= "";
	String ques_id ="";
	String study_project_id = "";
	String mon_id = "";

	@Test(description = "新增学习项目", priority = 1)
	public void testAddStudyProject() {
		//新增学习项目
		String res = StudyProjectNewBusinesss.addStudyProject(self_name, classification_id,
				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name),
				CertificateBusiness.getIdByKeyword(cert_name),
				CourseBusiness.getIdByPage(course_name),
				QuestionnaireBusiness.getIdByKeyword(BaseBusiness.questionnaireName, "release"));
				study_project_id = (String) JSONPath.read(res, "$.id");
				study_course_id = (String)JSONPath.read(res, "$.course_id");
		Assert.assertNotNull(study_project_id, "新增学习项目" + res);
	}
	
	@Test(description = "启用学习项目", priority = 2)
	public void testUpdateOpenStatus() {
//		//启用学习项目
//		String res = StudyProjectBusiness.updateStatus(study_project_id, "normal");
//		String update = (String) JSONPath.read(res, "$.update");
//		Assert.assertEquals(update, "true", "启用学习项目后,实际返回结果：" + res);
	}
	
	@Test(description = "查看学习项目列表", priority = 3)
	public void testQueryLearningProjectList() {
		String res = StudyProjectBusiness.queryLearningProjectList(self_name, "");
		mon_id = (String)JSONPath.read(res, "$.list[0].course_id");
	}
	
	@Test(description = "查看学习项目在课程列表的详情,获取不同课程的id",priority = 4)
	public void testQueryStudyCourseInfo() {
		String res = MySelfStudyBusiness.queryStudyCourseInfo(study_course_id);
		
		courseId = (String)JSONPath.read(res, "$.stages[0].resources[0].course_id");
		exam_id = (String )JSONPath.read(res, "$.stages[0].resources[1].exam.id");
		ins_id = (String)JSONPath.read(res, "$.stages[0].resources[2].course_id");
		ques_id = (String)JSONPath.read(res, "$.stages[0].resources[2].questionnaire_id"); 
	}
	@Test(description="学习项目，在课程页开始学习----start_study接口",priority= 5)
	public void testStartStudy() {
		String res = MySelfStudyBusiness.startStudy(study_course_id);
		String result = (String)JSONPath.read(res, "$.result");
		Assert.assertEquals(result, "true","学习项目，自学后，在课程页开始学习"+res);
	}
	@Test(description="学习项目，在课程查看课件学习进度",priority= 6)
	public void testGetSingleResourceProgress() {
//		String res = MySelfStudyBusiness.getSingleResourceProgress(study_course_id, resourceId, resourceId);
//		String name = (String)JSONPath.read(res, "$.data.name");
//		Assert.assertEquals(name,articlename,"学习项目，自学后，在课程查看课件学习进度，实际返回结果："+res);
	}
	@Test(description = "保存进度学习项目的第一个课程进度",priority = 7)
	public void testSaveFirstProgress() {
//		MySelfStudyBusiness.saveProgress("100",study_course_id, resourceId, resourceId);
	}
	
	@Test(description = "保存进度学习项目的第一个课程进度",priority = 8)
	public void testSaveSecondProgress() {
//		MySelfStudyBusiness.saveProgress("100",study_course_id, courseId,resourceId);
		
	}
	@Test(description="学习项目，自学后，在课程提交考试",priority= 9)
	public void testSubmitPassByIdExam () {
		String res = ExaminationTaskBusiness.submitPassByIdExam(exam_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg,"提交试卷成功!","学习项目，自学后，在课程提交考试"+res);
	}
	String questionId_one = "";
	String options_one = "";
	String questionId_two = "";
	String options_two = "";
	String questionId_three = "";
	String questionId_four = "";
	@Test(description="学习项目，自学后，在课程查看调研任务详情",priority= 10)
	public void testQueryInvestigatesInfo() {
		String res = MySelfStudyBusiness.queryInvestigatesInfo(study_course_id, ins_id);
		String title = (String)JSONPath.read(res, "$.title");
		questionId_one = (String) JSONPath.read(res, "$.question[0].id");
		options_one = (String) JSONPath.read(res, "$.question[0].options[0].id");
		questionId_two = (String) JSONPath.read(res, "$.question[1].id");
		options_two = (String) JSONPath.read(res, "$.question[1].options[0].id");
		questionId_three = (String) JSONPath.read(res, "$.question[2].id");
		questionId_four = (String) JSONPath.read(res, "$.question[3].id");
		Assert.assertEquals(title, BaseBusiness.questionnaireName,"学习项目，自学后，在课程查看调研任务详情"+res);
	}
	@Test(description = "学习项目，自学后，在课程列表提交问卷", priority = 11)
	public void testSubmitAnonymousStatistics() {
		String res = MySelfStudyBusiness.submit(study_course_id, ins_id, questionId_one, options_one, questionId_two,
				options_two, questionId_three, questionId_four, "test", "4");
		String operate_result = (String) JSONPath.read(res, "$.operate_result");
		Assert.assertEquals(operate_result, "success", "学习项目，自学后，在课程提交问卷，实际返回结果：" + res);
	}
	
	String dep_id = "";
	String post_id = "";
	@Test(description = "获取部门id",priority = 12)
	public void testGetDepartmentList(){
		String res = AuthUserBusiness.GetDepartmentList();
		dep_id = (String)JSONPath.read(res, "$.children[0].id");
	}
	@Test(description = "获取岗位id",priority = 13)
	public void testGetPostId() {
		String res = AuthUserBusiness.queryUsePostList();
		post_id = (String)JSONPath.read(res, "$.posts[0].id");
	}
	
	String user_id = UserBusiness.getUserId();
	
	@Test(description = "查看学习项目的人员监控--全部",priority = 14)
	public void testQueryStudyProjectUserMonitorsByAll() {
		String res = StudyProjectBusiness.queryStudyProjectUserMonitors(mon_id, "", "", "", "", "");
		String name = (String)JSONPath.read(res, "$.monitor_list.list[0].user_name");
		Assert.assertNotNull(name, "查看学习项目的人员监控--全部,用户名验证"+res);
	}
	@Test(description ="查看学习项目的人员监控--进行中",priority = 15)
	public void testQueryStudyProjectUserMonitorsByOngoing() {
		String res = StudyProjectBusiness.queryStudyProjectUserMonitors(mon_id, "unfinished", "", "", "", "");
		String title = (String)JSONPath.read(res, "$.study_project.title");
		Assert.assertNotNull(title, "查看学习项目的人员监控--进行中,title验证"+res);
	}
	@Test(description ="查看学习项目的人员监控--已完成",priority = 16)
	public void testQueryStudyProjectUserMonitorsByFinished() {
//		String res = StudyProjectBusiness.queryStudyProjectUserMonitors(mon_id, "finished", "", "", "", "");
//		String name = (String)JSONPath.read(res, "$.monitor_list.list[0].user_name");
//		Assert.assertNotNull(name, "查看学习项目的人员监控--已完成,用户名验证"+res);
	}
	@Test(description ="查看学习项目的人员监控--及格状态（全部）",priority = 17)
	public void testQueryStudyProjectUserMonitorsByAllPass() {
		String res = StudyProjectBusiness.queryStudyProjectUserMonitors(mon_id, "", "0", "", "", "");
		String name = (String)JSONPath.read(res, "$.monitor_list.list[0].user_name");
		Assert.assertNotNull(name, "查看学习项目的人员监控--及格状态（全部）,用户名验证"+res);
	}
	@Test(description ="查看学习项目的人员监控--及格状态（及格）",priority = 18)
	public void testQueryStudyProjectUserMonitorsByPass() {
//		String res = StudyProjectBusiness.queryStudyProjectUserMonitors(mon_id, "", "1", "", "", "");
//		String name = (String)JSONPath.read(res, "$.monitor_list.list[0].user_name");
//		Assert.assertNotNull(name, "查看学习项目的人员监控--及格状态（及格）,用户名验证"+res);
	}
	@Test(description ="查看学习项目的人员监控--及格状态（不及格）",priority = 19)
	public void testQueryStudyProjectUserMonitorsByFailed() {
		String res = StudyProjectBusiness.queryStudyProjectUserMonitors(mon_id, "", "2", "", "", "");
		String title = (String)JSONPath.read(res, "$.study_project.title");
		Assert.assertNotNull(title, "查看学习项目的人员监控--及格状态（不及格）,title验证"+res);
	}
	@Test(description ="查看学习项目的人员监控--根据关键字查询",priority = 20)
	public void testQueryStudyProjectUserMonitorsByKeyword() {
		String res = StudyProjectBusiness.queryStudyProjectUserMonitors(mon_id, "", "0", "aaa", "", "");
		String title = (String)JSONPath.read(res, "$.study_project.title");
		Assert.assertNotNull(title, "查看学习项目的人员监控--根据关键字查询,title验证"+res);
	}
	@Test(description ="查看学习项目的人员监控--根据部门查询",priority = 21)
	public void testQueryStudyProjectUserMonitorsByDep() {
//		String res = StudyProjectBusiness.queryStudyProjectUserMonitors(mon_id, "", "0", "", dep_id, "");
//		String title = (String)JSONPath.read(res, "$.study_project.title");
//		Assert.assertNotNull(title, "查看学习项目的人员监控--根据部门查询,title验证"+res);
	}
	@Test(description ="查看学习项目的人员监控--根据岗位查询",priority = 22)
	public void testQueryStudyProjectUserMonitorsByPost() {
		String res = StudyProjectBusiness.queryStudyProjectUserMonitors(mon_id, "", "0", "", "", post_id);
		String title = (String)JSONPath.read(res, "$.study_project.title");
		Assert.assertNotNull(title, "查看学习项目的人员监控--根据岗位查询,title验证"+res);
	}
	
	@Test(description= "查看学习项目线下签到的监控",priority = 23)
	public void testQueryLatojaMonitors() {
		String res = StudyProjectBusiness.queryLatojaMonitors(mon_id);
		Assert.assertTrue(res.contains("total"),"查看学习项目线下签到的监控"+res);
	}
	
	String in_id = "";
	@Test(description = "查看学习项目问卷调研的监控",priority = 24)
	public void testQueryInvestigateMonitors() {
		String res = StudyProjectBusiness.queryInvestigateMonitors(mon_id);
		String title = (String)JSONPath.read(res, "$.list[0].title");
		int investigate_count = (int)JSONPath.read(res, "$.list[0].investigate_count");
		in_id = (String)JSONPath.read(res, "$.list[0].id");
		Assert.assertTrue(investigate_count==1,"查看学习项目线下签到的监控"+res);
		Assert.assertNotNull(title, "查看学习项目问卷调研的监控,问卷的title验证"+res);
	}
	
	@Test(description = "查看学习项目问卷调研的监控的问卷明细",priority = 25)
	public void testQueryInvestigateResultMonitors() {
		String res = StudyProjectBusiness.queryInvestigateResultMonitors(mon_id,in_id);
		String title = (String)JSONPath.read(res, "$.questions[0].title");
		Assert.assertNotNull(title, "查看学习项目问卷调研的监控,问卷的user_name验证"+res);
	}
	
	@Test(description = "查看学习项目问卷调研的监控的用户列表",priority = 26)
	public void testQueryInvestigateUsersMonitors() {
		String res = StudyProjectBusiness.queryInvestigateUsersMonitors(mon_id,in_id);
		String user_name = (String)JSONPath.read(res, "$.page_info.list[0].user_name");
		Assert.assertNotNull(user_name, "查看学习项目问卷调研的监控,问卷的user_name验证"+res);
	}
	String exam_res_id = "";
	@Test(description = "查看学习项目考试监控的用户列表",priority = 27)
	public void testQueryExamMonitors() {
		String res = StudyProjectBusiness.queryExamMonitors(mon_id);
		String title = (String)JSONPath.read(res, "$.list[0].title");
		exam_res_id = (String)JSONPath.read(res, "$.list[0].id");
		Assert.assertNotNull(title, "查看学习项目问卷调研的监控,考试的title验证"+res);
	}
	@Test(description = "查看学习项目的考试人员监控",priority = 28)
	public void testQueryExamResult() {
		String res = NewEmployeeTrainBusiness.queryExamResult(exam_res_id);
		Long date = (Long)JSONPath.read(res, "$.data.planUser.list[0].commitTime");
		Assert.assertNotNull(date,"查看学习项目的考试人员监控，提交时间不为空验证："+res);
	}
	
	@Test(description = "查看学习项目的考试人员的试题分析",priority = 29)
	public void testQueryQuestionAnalysis() {
		String res = ExaminationTaskBusiness.queryQuestionAnalysis(exam_res_id,"","");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "查询成功","查看学习项目的考试人员的试题分析，实际返回结果："+res);
	}
	
	@Test(description = "清除所有导出数据",priority = 30)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	
	@Test(description="导出学习项目的人员监控数据-All",priority=31)
	public void testGetExportMonitorsCode() {
		String res = StudyProjectBusiness.getExportMonitorsCode(mon_id,"");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "导出学习项目人员监控成功","导出学习项目的人员监控数据-All:"+res);
	}
	
	@Test(description="导出学习项目的人员监控数据-unfinished",priority=32)
	public void testGetExportMonitorsCodeUnfinished() {
		String res = StudyProjectBusiness.getExportMonitorsCode(mon_id,"unfinished");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "导出学习项目人员监控成功","导出学习项目的人员监控数据-unfinished:"+res);
	}
	
	@Test(description="导出学习项目的人员监控数据-finished",priority=33)
	public void testGetExportMonitorsCodeFinished() {
		String res = StudyProjectBusiness.getExportMonitorsCode(mon_id,"finished");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "导出学习项目人员监控成功","导出学习项目的人员监控数据-finished:"+res);
	}
	
	
	@Test(description = "导出问卷调研统计的数据",priority = 34)
	public void statisticsExport() {
		String res = AppStudyBusiness.statisticsExport(in_id);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "问卷导出成功","导出问卷调研统计的数据"+res);
	}
	@Test(description = "导出问卷明细的数据",priority = 35)
	public void testExportInvestigatesDetailList() {
		String res = AppStudyBusiness.exportInvestigatesDetailList(mon_id, in_id,"study_project");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "问卷调研明细数据","导出问卷明细的数据"+res);
	
	}
	
	@Test(description="导出考试结果--通过用户名查看",priority=36)
	public void testGetExportExamResultCodeByName() {
		int code = ExaminationTaskBusiness.getExportExamResultCode(exam_id, user_name);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出考试结果数据，实际状态码为："+code);
	}
	@Test(description="导出考试结果--无搜索条件的情况",priority=37)
	public void testGetExportExamResultCode() {
		int code = ExaminationTaskBusiness.getExportExamResultCode(exam_id, "");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出考试结果--无搜索条件的情况，实际状态码为："+code);
	}
	@Test(description="导出学员作答明细--通过用户名查看",priority = 38)
	public void testGetExportExamDetailCodeByName() {
		int code = ExaminationTaskBusiness.getExportExamDetailCode(exam_id, user_name,"0");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出学员作答明细--通过用户名查看，实际状态码为："+code);
	}
	@Test(description="导出学员作答明细--无搜索条件的情况",priority=39)
	public void testGetExportExamDetailCode() {
		int code = ExaminationTaskBusiness.getExportExamDetailCode(exam_id, "");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出学员作答明细--无搜索条件的情况，实际状态码为："+code);
	}
	@Test(description="导出试题作答明细--通过用户名查看",priority = 40)
	public void testGetExportAnswerDetailCodeByName() {
		int code = ExaminationTaskBusiness.getExportAnswerDetailCode(exam_id, user_name);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出学员作答明细--通过用户名查看，实际状态码为："+code);
	}
	@Test(description="导出试题作答明细--无搜索条件的情况",priority=41)
	public void testGetExportAnswerDetailCode() {
		int code = ExaminationTaskBusiness.getExportAnswerDetailCode(exam_id, "");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出试题作答明细--无搜索条件的情况，实际状态码为："+code);
	}
	@Test(description="导出试题分析--全部试题类型的情况",priority=42)
	public void testGetExportQuestionAnalysisCodeAll() {
		int code = ExaminationTaskBusiness.getExportQuestionAnalysisCode(exam_id, "");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出试题分析--全部试题类型的情况，实际状态码为："+code);
	}
	@Test(description="导出试题分析--单选题类型的情况",priority=43)
	public void testGetExportQuestionAnalysisCodeSingle() {
		int code = ExaminationTaskBusiness.getExportQuestionAnalysisCode(exam_id, "1");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出试题分析--单选题类型的情况，实际状态码为："+code);
	}
	@Test(description="导出试题分析--多选试题类型的情况",priority=44)
	public void testGetExportQuestionAnalysisCodeMult() {
		int code = ExaminationTaskBusiness.getExportQuestionAnalysisCode(exam_id, "2");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出试题分析--多选试题类型的情况，实际状态码为："+code);
	}
	@Test(description="导出试题分析--判断试题类型的情况",priority=45)
	public void testGetExportQuestionAnalysisCodeJudge() {
		int code = ExaminationTaskBusiness.getExportQuestionAnalysisCode(exam_id, "3");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200,"导出试题分析--判断试题类型的情况，实际状态码为："+code);
	}
	
	@Test(description = "查看导出的结果",priority = 62)
	public void exportRecordList() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
		String status_11 = (String)JSONPath.read(res, "$.list[10].status");
		String status_12 = (String)JSONPath.read(res, "$.list[11].status");
		
		String status_13 = (String)JSONPath.read(res, "$.list[12].status");
		String status_14 = (String)JSONPath.read(res, "$.list[13].status");
	
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
		Assert.assertFalse(status_11=="FAILED", "查看导出的结果:"+res);	
		Assert.assertFalse(status_12=="FAILED", "查看导出的结果:"+res);	
		Assert.assertFalse(status_13=="FAILED", "查看导出的结果:"+res);	
		Assert.assertFalse(status_14=="FAILED", "查看导出的结果:"+res);	
	}
	
	@Test(description = "删除学习项目",priority = 47)
	public void testDeleteStudyProject() {
		String del_res =StudyProjectBusiness.deleteStudyProject(study_project_id);
//		String deleted = (String) JSONPath.read(del_res, "$.deleted");
//		Assert.assertEquals(deleted, "true", "删除学习项目,实际返回结果：" + del_res);
	}
	
}
