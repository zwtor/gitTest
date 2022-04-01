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
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestPaperDelete extends InitStudyAuthCourse {

	String post_name= "PaperDeletePost"+CommonData.getStringRandom(4);
	String train_name = "PaperDeleteNewStudy"+CommonData.getStringRandom(4);
	String classification_id = ClassificationBusines.getPrimaryId();
	String study_project_name = "DeleteStudyProject"+CommonData.getStringRandom(3);
	String paper_name = "DeletePaper"+CommonData.getStringRandom(5);
	String art_id = "";
	String course_id = "";
	String post_id = "";
	String cert_id = "";
	String paper_id = "";
	String plan_id = "";
	String study_project_id = "";
	boolean referenceWithPlan;
	@BeforeClass
	public void Init() {
		//添加试卷
		PaperBusiness.creatPaper(paper_name, "instruction", "150", "90", "60",PaperBusiness.getFirstquestionid() , "100", PaperBusiness.getSecondquestionid(), "50");

		cert_id = CertificateBusiness.getIdByKeyword(cert_name);
		paper_id = PaperBusiness.getIdByKeyword(paper_name) ;
		//获取初始化文章id
		String res= CoursewareBusiness.queryPictureArtList(articlename, "released");
		art_id= (String)JSONPath.read(res, "$.list[0].course_id");

		//添加岗位认证
		PostAuthenticationBusiness.addPostAuthentication(post_name, "desc", cert_id, "0", art_id, course_id, 
				"sc", "This is a sc desc", "exam", "120",paper_id,  BaseBusiness.pass_paper_name);
		String post_res = PostAuthenticationBusiness.queryList(post_name, "0");
		
		post_id = (String)JSONPath.read(post_res, "$.list[0].id");

		//新增新员工培训
		NewEmployeeTrainBusiness.addNewEmployTrainByDepart(train_name, NewEmployeeTrainBusiness.getDepartmentId(), 
				DateUtil.getRegularDate(0), art_id, course_id,paper_id,"true","1");
		String new_res = NewEmployeeTrainBusiness.queryList(train_name, "false", "0", "0","","");
		plan_id = (String)JSONPath.read(new_res, "$.list[0].planId");

		//新增学习项目
		StudyProjectBusiness.addStudyProject(study_project_name, classification_id,paper_id,cert_id, art_id,
				course_id,QuestionnaireBusiness.getIdByKeyword(BaseBusiness.questionnaireName, "release"));
		String pro_res = StudyProjectBusiness.queryLearningProjectList(study_project_name, "");
		study_project_id=  (String) JSONPath.read(pro_res, "$.list[0].id");
	}

	@Test(description="试卷被其他模块引用时，判断isReferenceWithPlan的referenceWithPlan值，此时为false",priority=2)
	public void testIsReferenceWithPlan() {
		String res = PaperBusiness.isReferenceWithPlan(paper_id);
		referenceWithPlan = (boolean)JSONPath.read(res, "$.referenceWithPlan");
		Assert.assertFalse(referenceWithPlan, "试卷被其他模块引用时，判断isReferenceWithPlan的referenceWithPlan值，此时应为true");
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
		referenceWithPlan = (boolean)JSONPath.read(paper_res, "$.referenceWithPlan");
//		Assert.assertTrue(referenceWithPlan, "试卷被其他模块引用时，判断isReferenceWithPlan的referenceWithPlan值，此时应为true");
//		Assert.assertEquals(new_title, train_name,"试卷被引用时，删除岗位认证后，再次删除试卷，新员工培训标题进行校验"+res);
//		Assert.assertEquals(study_title, study_name,"试卷被引用时，删除岗位认证后，再次删除试卷，学习任务标题进行校验"+res);
//		Assert.assertEquals(study_project_title, study_project_name,"试卷被引用时，删除岗位认证后，再次删除试卷，学习项目标题进行校验"+res);
//		Assert.assertEquals(timer_study_title, timer_study_name,"试卷被引用时，删除岗位认证后，再次删除试卷，定时学习标题进行校验"+res);
	}

	@Test(description="删除学习项目时，再次删除试卷",priority=8)
	public void testDeleteStudyProject() {
		StudyProjectBusiness.deleteStudyProject(study_project_id);
		String res = BaseBusiness.deleteCheckData(paper_id,"paper");
		String timer_study_title = (String)JSONPath.read(res, "$.list[0].title");
		String paper_res = PaperBusiness.isReferenceWithPlan(paper_id);
		referenceWithPlan = (boolean)JSONPath.read(paper_res, "$.referenceWithPlan");
//		Assert.assertTrue(referenceWithPlan, "试卷被其他模块引用时，判断isReferenceWithPlan的referenceWithPlan值，此时应为true");
//		Assert.assertEquals(timer_study_title, timer_study_name,"试卷被引用时，删除学习项目后，再次删除试卷，定时学习标题进行校验"+res);
	}

	@Test(description="删除新员工培训时，再次删除试卷",priority=7)
	public void testDeleteEmployTrainTask() {
		NewEmployeeTrainBusiness.deleteEmployTrainTask(plan_id);
		String res = BaseBusiness.deleteCheckData(paper_id,"paper");
		String study_project_title = (String)JSONPath.read(res, "$.list[0].title");
		String timer_study_title = (String)JSONPath.read(res, "$.list[1].title");
		String paper_res = PaperBusiness.isReferenceWithPlan(paper_id);
		referenceWithPlan = (boolean)JSONPath.read(paper_res, "$.referenceWithPlan");
//		Assert.assertTrue(referenceWithPlan, "试卷被其他模块引用时，判断isReferenceWithPlan的referenceWithPlan值，此时应为true");
//		Assert.assertEquals(study_project_title, study_project_name,"试卷被引用时，删除新员工培训后，再次删除试卷，学习项目标题进行校验"+res);
//		Assert.assertEquals(timer_study_title, timer_study_name,"试卷被引用时，删除新员工培训后，再次删除试卷，定时学习标题进行校验"+res);
	}

	@Test(description="试卷被其他模块引用时，其他任务全部删除，判断isReferenceWithPlan的referenceWithPlan值，true",priority=10)
	public void testNotReferenceWithPlan() {
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
		referenceWithPlan = (boolean)JSONPath.read(res, "$.referenceWithPlan");
		Assert.assertTrue(referenceWithPlan, "试卷被考试引用时，判断isReferenceWithPlan的referenceWithPlan值，此时应为true");
	}

}
