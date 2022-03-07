package extremely.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.authentication.business.PostAuthenticationBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.course.resources.bussiness.CoursewareBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.examination.business.TimerExamTaskBusiness;
import cn.kxy.investigationresearch.business.QuestionnaireBusiness;
import cn.kxy.lecturer.business.LecturerListBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.NewEmployeeTrainBusiness;
import cn.kxy.study.business.StudyProjectBusiness;
import cn.kxy.study.business.StudyTaskBusiness;
import cn.kxy.study.business.TimerStudyTaskTemplate;
import cn.lazy.init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestPaperDelete extends InitStudyAuthCourse {

	String delete_course_name = "PaperDeleteCourse"+CommonData.getStringRandom(4);
	String post_name= "PaperDeletePost"+CommonData.getStringRandom(4);
	String study_name = "PaperDeleteStudy"+CommonData.getStringRandom(4);
	String timer_study_name = "PaperDeleteTimerStudy"+CommonData.getStringRandom(4);
	String train_name = "PaperDeleteNewStudy"+CommonData.getStringRandom(4);
	String classification_id = ClassificationBusines.getPrimaryId();
	String study_project_name = "DeleteStudyProject"+CommonData.getStringRandom(3);
	String paper_name = "DeletePaper"+CommonData.getStringRandom(5);
	String timer_exam_name = "DeleteTimerExam"+CommonData.getStringRandom(4);
	String exam_task_name = "DeleteExamTask"+CommonData.getStringRandom(4);
	String art_id = "";
	String course_id = "";
	String post_id = "";
	String study_id = "";
	String cert_id = "";
	String paper_id = "";
	String plan_id = "";
	String time_study_id = "";
	String study_project_id = "";
	String user_id = UserBusiness.getUserId();
	String exam_id = "";
	String timer_exam_id = "";
	@BeforeClass
	public void Init() {
		PaperBusiness.creatPaper(paper_name, "instruction", "150", "90", "60",PaperBusiness.getFirstquestionid() , "100", PaperBusiness.getSecondquestionid(), "50");
	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cert_id = CertificateBusiness.getIdByKeyword(cert_name);
		paper_id = PaperBusiness.getIdByKeyword(paper_name) ;
		//获取初始化文章id
		String res= CoursewareBusiness.queryPictureArtList(articlename, "released");
		art_id= (String)JSONPath.read(res, "$.list[0].course_id");
		//添加试卷
		CourseBusiness.addCourse(delete_course_name, "1", "this is desription", LecturerListBusiness.getIdByKeyword(outer_name), 
				art_id, "0", "2", UserBusiness.getUserId(), "0", "3", "release");
		String course_res = CourseBusiness.courseManageList(delete_course_name, "all", "released","","","all");
		course_id = (String)JSONPath.read(course_res, "$.list[0].course_id");
		//添加岗位认证
		PostAuthenticationBusiness.addPostAuthentication(post_name, "desc", cert_id, "0", art_id, course_id, 
				"sc", "This is a sc desc", "exam", "120",paper_id,  BaseBusiness.pass_paper_name);
		String post_res = PostAuthenticationBusiness.queryList(post_name, "0");
		
		post_id = (String)JSONPath.read(post_res, "$.list[0].id");
		//新增学习任务
		StudyTaskBusiness.addMixSettledExamStudyTask(study_name, DateUtil.getTimeStamp(0, ""), DateUtil.getTimeStamp(1, ""), cert_id, 
				art_id, course_id, paper_id,DateUtil.getRegularDateForHHMM(1), DateUtil.getRegularDateForHHMM(2),
				LecturerListBusiness.getIdByKeyword(outer_name), DateUtil.getTimeStamp(1, "")
				,QuestionnaireBusiness.getIdByKeyword(BaseBusiness.questionnaireName, "release"));
		String study_res = StudyTaskBusiness.getStudyTaskList(study_name, "true", "0", "");
		
		study_id= (String)JSONPath.read(study_res, "$.list[0].id");
		//新增新员工培训
		NewEmployeeTrainBusiness.addNewEmployTrainByDepart(train_name, NewEmployeeTrainBusiness.getDepartmentId(), 
				DateUtil.getRegularDate(0), art_id, course_id,paper_id,"true","1");
		String new_res = NewEmployeeTrainBusiness.queryList(train_name, "false", "0", "0","","");
		plan_id = (String)JSONPath.read(new_res, "$.list[0].planId");
		//新增定时学习任务
		TimerStudyTaskTemplate.addSingleTimerStudy(timer_study_name, DateUtil.getRegularDateForHHMM(5),  
				DateUtil.getRegularDateForHHMM(5), art_id, course_id, paper_id,paper_name);
		String time_study_res = TimerStudyTaskTemplate.queryList(timer_study_name, "all");
		time_study_id = (String)JSONPath.read(time_study_res,"$.list[0].id");
		//新增学习项目
		StudyProjectBusiness.addStudyProject(study_project_name, classification_id,paper_id,cert_id, art_id,
				course_id,QuestionnaireBusiness.getIdByKeyword(BaseBusiness.questionnaireName, "release"));
		String pro_res = StudyProjectBusiness.queryLearningProjectList(study_project_name, "");
		study_project_id=  (String) JSONPath.read(pro_res, "$.list[0].id");
		//新增定时考试任务
		TimerExamTaskBusiness.creatTimerExamTask("60", "100", paper_id, timer_exam_name,
				user_id, "45", "60", "10","false");
		String timer_exam_res = TimerExamTaskBusiness.queryTimerExamTask(timer_exam_name, "all");
		timer_exam_id = (String)JSONPath.read(timer_exam_res, "$.list[0].id");
		
		//新增考试任务
		ExaminationTaskBusiness.creatExamTask("1", "show", "60", "100", "2", exam_task_name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false",user_id,
				paper_id, "60", "", "false", "1", "0", "12", "0", "0", "0",
				"0", "false", "false", user_id);
		exam_id = ExaminationTaskBusiness.getIdByKeyword(exam_task_name);
	}
	
	
	
	@Test(description="试卷被引用时，delete/check校验是否可以删除",priority=1)
	public void testDeleteCheckData() {
		String res = BaseBusiness.deleteCheckData(paper_id,"paper");
		String exam_type_name = (String)JSONPath.read(res, "$.list[0].type_name");
		String timer_exam_type_name = (String)JSONPath.read(res, "$.list[1].type_name");
		String new_type_name = (String)JSONPath.read(res, "$.list[2].type_name");
		String study_type_name = (String)JSONPath.read(res, "$.list[3].type_name");
		String study_project_type_name = (String)JSONPath.read(res, "$.list[4].type_name");
		String post_type_name = (String)JSONPath.read(res, "$.list[5].type_name");
		String timer_study_type_name = (String)JSONPath.read(res, "$.list[6].type_name");
		
		String exam_title = (String)JSONPath.read(res, "$.list[0].title");
		String timer_exam_title = (String)JSONPath.read(res, "$.list[1].title");
		String new_title = (String)JSONPath.read(res, "$.list[2].title");
		String study_title = (String)JSONPath.read(res, "$.list[3].title");
		String study_project_title = (String)JSONPath.read(res, "$.list[4].title");
		String post_title = (String)JSONPath.read(res, "$.list[5].title");
		String timer_study_title = (String)JSONPath.read(res, "$.list[6].title");
//		Assert.assertEquals(exam_title, exam_task_name,"试卷被引用时，delete/check接口，新员工培训标题进行校验"+res);
//		Assert.assertEquals(timer_exam_title, timer_exam_name,"试卷被引用时，delete/check接口，学习任务标题进行校验"+res);
//		Assert.assertEquals(new_title, train_name,"试卷被引用时，delete/check接口，新员工培训标题进行校验"+res);
//		Assert.assertEquals(study_title, study_name,"试卷被引用时，delete/check接口，学习任务标题进行校验"+res);
//		Assert.assertEquals(study_project_title, study_project_name,"试卷被引用时，delete/check接口，学习项目标题进行校验"+res);
//		Assert.assertEquals(post_title, post_name,"被引用时，delete/check接口，课岗位认证标题进行校验"+res);
//		Assert.assertEquals(timer_study_title, timer_study_name,"被引用时，delete/check接口，定时学习标题进行校验"+res);
//		
//		Assert.assertEquals(exam_type_name, "考试任务","试卷被引用时，delete/check校验是否可以删除,考试任务类型进行校验："+res);
//		Assert.assertEquals(timer_exam_type_name, "定时考试模板","试卷被引用时，delete/check校验是否可以删除,定时考试类型进行校验："+res);
//		Assert.assertEquals(new_type_name, "新员工培训","试卷被引用时，delete/check校验是否可以删除,新员工类型进行校验："+res);
//		Assert.assertEquals(study_type_name, "学习任务","试卷被引用时，delete/check校验是否可以删除,学习任务类型进行校验："+res);
//		Assert.assertEquals(study_project_type_name, "学习项目","试卷被引用时，delete/check校验是否可以删除,学习项目类型进行校验："+res);
//		Assert.assertEquals(post_type_name, "岗位认证","试卷被引用时，delete/check校验是否可以删除,岗位认证类型进行校验："+res);
//		Assert.assertEquals(timer_study_type_name, "定时学习模板","试卷被引用时，delete/check校验是否可以删除,定时学习模板类型进行校验："+res);

	}
	@Test(description="试卷被其他模块引用时，判断isReferenceWithPlan的referenceWithPlan值，此时应为true",priority=2)
	public void testIsReferenceWithPlanAll() {
		String res = PaperBusiness.isReferenceWithPlan(paper_id);
		boolean referenceWithPlan = (boolean)JSONPath.read(res, "$.referenceWithPlan");
		Assert.assertTrue(referenceWithPlan, "试卷被其他模块引用时，判断isReferenceWithPlan的referenceWithPlan值，此时应为true");
	}
	@Test(description="删除考试任务后，再次删除试卷",priority=3)
	public void testDeleteExamTask() {
		ExaminationTaskBusiness.deleteExamTask(exam_id);
		String res = BaseBusiness.deleteCheckData(paper_id,"paper");
		String timer_exam_type_name = (String)JSONPath.read(res, "$.list[0].type_name");
		String new_type_name = (String)JSONPath.read(res, "$.list[1].type_name");
		String study_type_name = (String)JSONPath.read(res, "$.list[2].type_name");
		String study_project_type_name = (String)JSONPath.read(res, "$.list[3].type_name");
		String post_type_name = (String)JSONPath.read(res, "$.list[4].type_name");
		String timer_study_type_name = (String)JSONPath.read(res, "$.list[5].type_name");
		String paper_res = PaperBusiness.isReferenceWithPlan(paper_id);
		boolean referenceWithPlan = (boolean)JSONPath.read(paper_res, "$.referenceWithPlan");
//		Assert.assertTrue(referenceWithPlan, "试卷被其他模块引用时，判断isReferenceWithPlan的referenceWithPlan值，此时应为true");
//		Assert.assertEquals(timer_exam_type_name, "定时考试模板","试卷被引用时，delete/check校验是否可以删除,定时考试任务类型进行校验："+res);
//		Assert.assertEquals(new_type_name, "新员工培训","试卷被引用时，delete/check校验是否可以删除,新员工类型进行校验："+res);
//		Assert.assertEquals(study_type_name, "学习任务","试卷被引用时，delete/check校验是否可以删除,学习任务类型进行校验："+res);
//		Assert.assertEquals(study_project_type_name, "学习项目","试卷被引用时，delete/check校验是否可以删除,学习项目类型进行校验："+res);
//		Assert.assertEquals(post_type_name, "岗位认证","试卷被引用时，delete/check校验是否可以删除,岗位认证类型进行校验："+res);
//		Assert.assertEquals(timer_study_type_name, "定时学习模板","试卷被引用时，delete/check校验是否可以删除,定时学习模板类型进行校验："+res);
	}
	@Test(description="删除定时考试后，再次删除试卷",priority=4)
	public void testDeleteTimerExamTask() {
		TimerExamTaskBusiness.deleteTimerExamTask(timer_exam_id);
		String res = BaseBusiness.deleteCheckData(paper_id,"paper");
		String new_type_name = (String)JSONPath.read(res, "$.list[0].type_name");
		String study_type_name = (String)JSONPath.read(res, "$.list[1].type_name");
		String study_project_type_name = (String)JSONPath.read(res, "$.list[2].type_name");
		String post_type_name = (String)JSONPath.read(res, "$.list[3].type_name");
		String timer_study_type_name = (String)JSONPath.read(res, "$.list[4].type_name");
		String paper_res = PaperBusiness.isReferenceWithPlan(paper_id);
		boolean referenceWithPlan = (boolean)JSONPath.read(paper_res, "$.referenceWithPlan");
//		Assert.assertTrue(referenceWithPlan, "试卷被其他模块引用时，判断isReferenceWithPlan的referenceWithPlan值，此时应为true");
//		Assert.assertEquals(new_type_name, "新员工培训","试卷被引用时，delete/check校验是否可以删除,新员工类型进行校验："+res);
//		Assert.assertEquals(study_type_name, "学习任务","试卷被引用时，delete/check校验是否可以删除,学习任务类型进行校验："+res);
//		Assert.assertEquals(study_project_type_name, "学习项目","试卷被引用时，delete/check校验是否可以删除,学习项目类型进行校验："+res);
//		Assert.assertEquals(post_type_name, "岗位认证","试卷被引用时，delete/check校验是否可以删除,岗位认证类型进行校验："+res);
//		Assert.assertEquals(timer_study_type_name, "定时学习模板","试卷被引用时，delete/check校验是否可以删除,定时学习模板类型进行校验："+res);
	}
	@Test(description="删除岗位认证后，再次删除试卷",priority=5)
	public void testDeleteAuthentication() {
		PostAuthenticationBusiness.deleteAuthentication(post_id);
		String res = BaseBusiness.deleteCheckData(paper_id,"paper");
		String new_title = (String)JSONPath.read(res, "$.list[0].title");
		String study_title = (String)JSONPath.read(res, "$.list[1].title");
		String study_project_title = (String)JSONPath.read(res, "$.list[2].title");
		String timer_study_title = (String)JSONPath.read(res, "$.list[3].title");
		String paper_res = PaperBusiness.isReferenceWithPlan(paper_id);
		boolean referenceWithPlan = (boolean)JSONPath.read(paper_res, "$.referenceWithPlan");
//		Assert.assertTrue(referenceWithPlan, "试卷被其他模块引用时，判断isReferenceWithPlan的referenceWithPlan值，此时应为true");
//		Assert.assertEquals(new_title, train_name,"试卷被引用时，删除岗位认证后，再次删除试卷，新员工培训标题进行校验"+res);
//		Assert.assertEquals(study_title, study_name,"试卷被引用时，删除岗位认证后，再次删除试卷，学习任务标题进行校验"+res);
//		Assert.assertEquals(study_project_title, study_project_name,"试卷被引用时，删除岗位认证后，再次删除试卷，学习项目标题进行校验"+res);
//		Assert.assertEquals(timer_study_title, timer_study_name,"试卷被引用时，删除岗位认证后，再次删除试卷，定时学习标题进行校验"+res);
	}
	@Test(description="删除学习任务时，再次删除试卷",priority=6)
	public void testDeleteStudyTask() {
//		StudyTaskBusiness.deleteStudyTask(study_id);
//		String res = BaseBusiness.deleteCheckData(paper_id,"paper");
//		String new_title = (String)JSONPath.read(res, "$.list[0].title");
//		String study_project_title = (String)JSONPath.read(res, "$.list[1].title");
//		String timer_study_title = (String)JSONPath.read(res, "$.list[2].title");
//		String paper_res = PaperBusiness.isReferenceWithPlan(paper_id);
//		boolean referenceWithPlan = (boolean)JSONPath.read(paper_res, "$.referenceWithPlan");
//		Assert.assertTrue(referenceWithPlan, "试卷被其他模块引用时，判断isReferenceWithPlan的referenceWithPlan值，此时应为true");
//		Assert.assertEquals(new_title, train_name,"试卷被引用时，删除学习任务后，再次删除试卷，新员工培训标题进行校验"+res);
//		Assert.assertEquals(study_project_title, study_project_name,"试卷被引用时，删除学习任务后，再次删除试卷，学习项目标题进行校验"+res);
//		Assert.assertEquals(timer_study_title, timer_study_name,"试卷被引用时，删除学习任务后，再次删除试卷，定时学习标题进行校验"+res);
	}
	@Test(description="删除新员工培训时，再次删除试卷",priority=7)
	public void testDeleteEmployTrainTask() {
		NewEmployeeTrainBusiness.deleteEmployTrainTask(plan_id);
		String res = BaseBusiness.deleteCheckData(paper_id,"paper");
		String study_project_title = (String)JSONPath.read(res, "$.list[0].title");
		String timer_study_title = (String)JSONPath.read(res, "$.list[1].title");
		String paper_res = PaperBusiness.isReferenceWithPlan(paper_id);
		boolean referenceWithPlan = (boolean)JSONPath.read(paper_res, "$.referenceWithPlan");
//		Assert.assertTrue(referenceWithPlan, "试卷被其他模块引用时，判断isReferenceWithPlan的referenceWithPlan值，此时应为true");
//		Assert.assertEquals(study_project_title, study_project_name,"试卷被引用时，删除新员工培训后，再次删除试卷，学习项目标题进行校验"+res);
//		Assert.assertEquals(timer_study_title, timer_study_name,"试卷被引用时，删除新员工培训后，再次删除试卷，定时学习标题进行校验"+res);
	}
	
	@Test(description="删除学习项目时，再次删除试卷",priority=8)
	public void testDeleteStudyProject() {
		StudyProjectBusiness.deleteStudyProject(study_project_id);
		String res = BaseBusiness.deleteCheckData(paper_id,"paper");
		String timer_study_title = (String)JSONPath.read(res, "$.list[0].title");
		String paper_res = PaperBusiness.isReferenceWithPlan(paper_id);
		boolean referenceWithPlan = (boolean)JSONPath.read(paper_res, "$.referenceWithPlan");
//		Assert.assertTrue(referenceWithPlan, "试卷被其他模块引用时，判断isReferenceWithPlan的referenceWithPlan值，此时应为true");
//		Assert.assertEquals(timer_study_title, timer_study_name,"试卷被引用时，删除学习项目后，再次删除试卷，定时学习标题进行校验"+res);
	}
	@Test(description="删除定时学习模板时，再次删除试卷",priority=9)
	public void testDeleteTimerStudy() {
		TimerStudyTaskTemplate.deleteTimerStudy(time_study_id);
		String res = BaseBusiness.deleteCheckData(paper_id,"paper");
		int total = (int)JSONPath.read(res, "$.size");
		Assert.assertTrue(total==0,"删除试卷后，再次校验课程是否可以删除"+res);
	}
	@Test(description="试卷被其他模块引用时，其他任务全部删除，判断isReferenceWithPlan的referenceWithPlan值，此时应为false",priority=10)
	public void testReferenceWithPlan() {
		String res = PaperBusiness.isReferenceWithPlan(paper_id);
		boolean referenceWithPlan = (boolean)JSONPath.read(res, "$.referenceWithPlan");
		Assert.assertFalse(referenceWithPlan, "试卷被其他模块引用时，判断isReferenceWithPlan的referenceWithPlan值，此时应为false");
	}
	@Test(description="被引用的任务删除后，试卷应可以成功删除",priority=11)
	public void testDeleteCourse() {
		String res = PaperBusiness.deletePaper(paper_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除试卷成功","删除试卷实际返回结果："+res);
	}
	@Test(description="试卷被考试引用时，判断isReferenceWithPlan的referenceWithPlan值，此时应为true",priority=12)
	public void testisReferenceWithPlan() {
		String paper_o_name  = "DelePaper"+CommonData.getStringRandom(3);
		String exam_name = "DeleteExam"+CommonData.getStringRandom(3);
		PaperBusiness.creatPaper(paper_o_name, "instruction", "150", "90", "60",PaperBusiness.getFirstquestionid() , "100", PaperBusiness.getSecondquestionid(), "50");
		
		String paper_o_id = PaperBusiness.getIdByKeyword(paper_o_name) ;
		//新增考试任务
		ExaminationTaskBusiness.creatExamTask("1", "show", "60", "100", "2", exam_name,
						DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false",UserBusiness.getUserId(),
						paper_o_id, "60", "", "false", "1", "0", "12", "0", "0", "0",
						"0", "false", "false", UserBusiness.getUserId());
		String exam_o_id = ExaminationTaskBusiness.getIdByKeyword(exam_name);
		String res = PaperBusiness.isReferenceWithPlan(paper_o_id);
		ExaminationTaskBusiness.deleteExamTask(exam_o_id);
		PaperBusiness.deletePaper(paper_o_id);
		boolean referenceWithPlan = (boolean)JSONPath.read(res, "$.referenceWithPlan");
		Assert.assertTrue(referenceWithPlan, "试卷被考试引用时，判断isReferenceWithPlan的referenceWithPlan值，此时应为true");
	}
	@AfterClass
	public void end() {
		String res = CourseBusiness.deleteCourse(course_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除课程成功","删除课程实际返回结果："+res);
	}

}
