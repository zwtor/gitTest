package study.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.group.a.business.ImageTextBusiness;
import cn.kxy.investigationresearch.business.QuestionnaireBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.StudyTaskBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

public class TestMixStudyTask extends InitStudyAuthCourse{

	String study_name="Mix Study Task"+CommonData.getStringRandom(6);
	String id = "";
	String exam_id = "";
	String user_name = UserBusiness.getUsername();
	
	String imagetext_id = "";
	@Test(description="1.创建图文课-发布接口", priority=1)
 	public void testImageTextAdd() throws UnsupportedEncodingException  {
 		
 		String title = "图文课" + CommonData.getStringRandom(5);
 		String baseCover = "https://oss.coolcollege.cn/1789917624419880960.png";
 		String contentJsonStr = "{\"blocks\":[{\"key\":\"85lj8\",\"text\":\"text test\",\"type\":\"unstyled\","
 				+ "\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}}";
 		String contentJson = JSONObject.parseObject(contentJsonStr).toJSONString();
 		String res = ImageTextBusiness.ImageTextAdd(baseCover,contentJson,"release","",title);
 		imagetext_id = (String) JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("1.创建图文课-发布接口:"+"code="+code+","+"imagetext_id="+imagetext_id);
 		Assert.assertEquals(msg, "新增课程成功","1.创建图文课-发布接口：" + res);
 	}
 	
	@Test(description="新增混合的学习任务",priority=2)
	public void testAddMixSettledExamStudyTask() {
		String res = StudyTaskBusiness.addMixSettledExamStudyTask(study_name, DateUtil.getTimeStamp(0, ""), DateUtil.getTimeStamp(1, ""), CertificateBusiness.getIdByKeyword(cert_name), 
				imagetext_id, CourseBusiness.getIdByPage(course_name), PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name),
				DateUtil.getRegularDateForHHMM(1), DateUtil.getRegularDateForHHMM(2)
				,QuestionnaireBusiness.getIdByKeyword(BaseBusiness.questionnaireName, "release"));
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！","添加线下学习任务，实际返回结果:"+res);
	}
	@Test(description="查看带有混合考的考试列表",priority=3)
	public void testgetStudyTaskList() {
		String res = StudyTaskBusiness.getStudyTaskList(study_name, "true", "0", "","");
		id= (String)JSONPath.read(res, "$.list[0].id");
		int taskType= (int)JSONPath.read(res, "$.list[0].taskType");
		boolean containExam= (boolean)JSONPath.read(res, "$.list[0].containExam");
		Assert.assertEquals(taskType, 1,"查询学习任务列表,任务类型（混合考试）"+res);
		Assert.assertEquals(containExam, true,"查询学习任务列表,是否显示（考）这个标签"+res);
	}
	
	@Test(description="查看混合考试的详情",priority=4)
	public void testQueryInfo() {
		String res = StudyTaskBusiness.queryInfo(id);
		String title = (String)JSONPath.read(res, "$.bizPlanVo.title");
		String cour_name = (String)JSONPath.read(res, "$.stageList[0].courseMappingList[1].name");
		String exam_name = (String)JSONPath.read(res, "$.stageList[0].courseMappingList[2].name");
		String que_name = (String)JSONPath.read(res, "$.stageList[0].courseMappingList[3].name");
		Assert.assertEquals(cour_name, course_name,"查看混合考试的详情--名称校验，实际返回结果："+res);
		Assert.assertEquals(exam_name, "exam","查看混合考试的详情--名称校验，实际返回结果："+res);
		Assert.assertEquals(que_name, BaseBusiness.questionnaireName,"查看混合考试的详情--名称校验，实际返回结果："+res);
		Assert.assertEquals(title, study_name,"查看混合考试的详情--名称校验，实际返回结果："+res);
	}
	
	@Test(description="未考试前，查看考试试题分析",priority=5)
	public void testQueryExamsAnalysis() {
		String res = StudyTaskBusiness.queryExamsAnalysis(id);
		String exam_name = (String)JSONPath.read(res, "$.list[0].title");
		int exam_num = (int)JSONPath.read(res, "$.list[0].exam_num");
		int qualified_num = (int)JSONPath.read(res, "$.list[0].qualified_num");
		int unqualified_num = (int)JSONPath.read(res, "$.list[0].unqualified_num");
		int un_exam_num = (int)JSONPath.read(res, "$.list[0].un_exam_num");
		int pending_count = (int)JSONPath.read(res, "$.list[0].pending_count");
		exam_id = (String)JSONPath.read(res, "$.list[0].id");
		Assert.assertEquals(qualified_num, 0,"未考试前，查看考试试题分析--考试名称"+res);
		Assert.assertEquals(un_exam_num, 1,"未考试前，查看考试试题分析--考试名称"+res);
		Assert.assertEquals(unqualified_num, 0,"未考试前，查看考试试题分析--考试名称"+res);
		Assert.assertEquals(pending_count, 0,"未考试前，查看考试试题分析--考试名称"+res);
		Assert.assertEquals(exam_num, 1,"未考试前，查看考试试题分析--考试名称"+res);
		Assert.assertEquals(exam_name, "exam","未考试前，查看考试试题分析--考试名称"+res);
	}
	
	@Test(description="未进行考试前，查看学习任务的问卷调查",priority=6)
	public void testQueryQuestionAnalysis() {
		String res = ExaminationTaskBusiness.queryQuestionAnalysis(exam_id, "", "");
		int total = (int)JSONPath.read(res, "$.data.total");
		Assert.assertEquals(total, 0,"未进行考试前，查看学习任务中考试的问卷调查,此时应该无数据"+res);
	}
	@Test(description="考试前，查看学习任务中考试的人员监控",priority=7)
	public void testQuerystionAnalysis() {
		String res = ExaminationTaskBusiness.queryPersonnelmonitorResult(exam_id, "0", "", "", "");
		String title = (String) JSONPath.read(res, "$.data.planExam.title");
		int passLine = (int) JSONPath.read(res, "$.data.planExam.passLine");
		String name = (String) JSONPath.read(res, "$.data.planUser.list[0].userName");
		String departName = (String) JSONPath.read(res, "$.data.planUser.list[0].departName");
		String creatorName = (String) JSONPath.read(res, "$.data.planExam.creatorName");
		int makeupCount = (int) JSONPath.read(res, "$.data.planUser.list[0].makeupCount");
		String statusName = (String) JSONPath.read(res, "$.data.planUser.list[0].statusName");
		BigDecimal score = (BigDecimal) JSONPath.read(res, "$.data.planUser.list[0].score");
		BigDecimal expscore = new BigDecimal("0.0");
		Assert.assertEquals(makeupCount, 0,"考试前，查看学习任务中考试的人员监控实际返回结果："+res);
		Assert.assertEquals(score, expscore,"考试前查看学习任务中考试的人员监控实际返回结果："+res);
		Assert.assertEquals(statusName, "待考试","考试前，查看学习任务中考试的人员监控--状态进行校验实际返回结果："+res);
		Assert.assertTrue(!departName.isEmpty(), "考试前，查看学习任务中考试的人员监控--进行校验(所属部门不为空)实际返回结果："+res);
		Assert.assertEquals(name, user_name,"考试前，查看学习任务中考试的人员监控--用户进行校验实际返回结果："+res);
		Assert.assertEquals(title, "exam","考试前，查看学习任务中考试的人员监控--考试名称进行校验实际返回结果："+res);
		Assert.assertEquals(passLine, 60,"考试前，查看学习任务中考试的人员监控--及格线进行校验实际返回结果："+res);
		Assert.assertEquals(creatorName,user_name,"考试前，查看学习任务中考试的人员监控--创建人进行校验实际返回结果："+res);
	}
	@Test(description="未进行学习时查看监控数据的基础信息",priority=8)
	public void testQuertGoingUserMonitors() {
		String res = StudyTaskBusiness.queryUserMonitors(id, "true", "", "", "");
		String userName =(String)JSONPath.read(res, "$.data.trainTask.createUserName");
		String trainName =(String)JSONPath.read(res, "$.data.trainTask.trainName");
		Long beginTime =(Long)JSONPath.read(res, "$.data.trainTask.beginTime");
		Long endTime =(Long)JSONPath.read(res, "$.data.trainTask.endTime");
		int progress =(int)JSONPath.read(res, "$.data.trainTask.progress");
		Assert.assertTrue(beginTime!=null, "未进行学习时查看监控数据的基础信息--开始时间："+res);
		Assert.assertTrue(endTime!=null, "未进行学习时查看监控数据的基础信息--开始时间："+res);
		Assert.assertEquals(progress,100,"未进行学习时查看监控数据的基础信息--合格标准进度"+res);
		Assert.assertEquals(trainName, study_name,"未进行学习时查看监控数据的基础信息--创建人"+res);
		Assert.assertEquals(userName, user_name,"未进行学习时查看监控数据的基础信息--创建人"+res);
	}
	
	@Test(description = "清除所有导出数据",priority = 9)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	
	@Test(description="导出学习任务的人员监控数据-all",priority=10)
	public void testGetStudyExportMonitorsAllCode() {
		int code = StudyTaskBusiness.getExportMonitorsCode(id, "0");
		Assert.assertEquals(code, 200,"导出学习任务的人员监控数据-all:"+code);
	}
	@Test(description="导出学习任务的人员监控数据-进行中",priority=11)
	public void testGetStudyExportMonitorsOngoingCode() {
		int code = StudyTaskBusiness.getExportMonitorsCode(id, "1");
		Assert.assertEquals(code, 200,"导出学习任务的人员监控数据-进行中:"+code);
	}
	@Test(description="导出学习任务的人员监控数据-已完成",priority=12)
	public void testGetStudyExportMonitorsFinishCode() {
		int code = StudyTaskBusiness.getExportMonitorsCode(id, "2");
		Assert.assertEquals(code, 200,"导出学习任务的人员监控数据-已完成:"+code);
	}
	@Test(description="导出学习任务的人员监控数据-逾期",priority=13)
	public void testGetStudyExportMonitorsCode() {
		int code = StudyTaskBusiness.getExportMonitorsCode(id, "3");
		Assert.assertEquals(code, 200,"导出学习任务的人员监控数据-逾期:"+code);
	}
	@Test(description = "导出学习计划列表",priority = 14)
	public void testExportPlanStudy() {
		String res = StudyTaskBusiness.exportPlanStudy();
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "导出学习任务成功","导出学习计划列表"+res);
	}
	
	@Test(description = "查看导出的结果",priority = 15)
	public void exportRecordList() {
		String res = PaperExportBusiness.exportRecordList();
		String status1 = (String)JSONPath.read(res, "$.list[0].status");
		String status2 = (String)JSONPath.read(res, "$.list[1].status");
		Assert.assertFalse(status1=="FAILED", "查看导出的结果，在下载中心查看"+res);	
		Assert.assertFalse(status2=="FAILED", "查看导出的结果，在下载中心查看"+res);
	}
	
	@Test(description="删除混合学习任务",priority=16)
	public void testDeleteStudyTask() {
		String res = StudyTaskBusiness.deleteStudyTask(id);
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除学习计划成功","删除混合学习任务，实际返回结果:"+res);
	}
	
	@Test(description="33.图文课删除-图文课已被删除接口", priority=17)
 	public void testCourseDeleted(){
 		String res = ImageTextBusiness.CourseDelete(imagetext_id);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("33.图文课删除-图文课已被删除接口:"+"message"+message);
 	}
}
