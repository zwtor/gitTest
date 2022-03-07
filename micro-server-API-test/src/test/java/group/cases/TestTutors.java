package group.cases;

import cn.kxy.group.a.business.TutorOperationBusiness;
import cn.kxy.group.a.business.TutorsBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

public class TestTutors {	
	public static String lever_id = "";
	public static String LeverName ="";
	public static String tutor_id = "";
	public static String employee_train_id = "";
	public static String assign_id = "";
	public static String save_course_id = "";
	public static String choose_tutor_course_id = "";
	public static String tutorials_id = "";
	public static String operation_tutor_id = "";
	public static String getUserIdByKeyWord = "";
	public static String study_id = "";
	public static String project_plan_id = "";
	public static String MyProjectTask_id ="";
	public static String MyStudyPlanTask_id = "";
	public static String MyProjectOperation_id ="";
	public static String MyStudyPlanOperation_id = "";
	public static String operations_id = "";
	public static String project_approval_id = "";
	public static String study_plan_approval_id ="";
	public static String project_id = "";
	public static String course_id ="";
	public static String certificate_id = "";
	public static String qualifications_id = "";
	public static String qualifications_opertion_id = "";
	public static String qualifications_approval_id = "";
	public static String approval_user = "";
	public static String apprentices_id = "";
	
	String name = "导师制等级" + CommonData.getStringRandom(5);
	String train_name = "导师制的新员工培训" + CommonData.getStringRandom(5);
	String title = "任务-实操作业是导师制审批" + CommonData.getStringRandom(5);
	String classification_id = ClassificationBusines.getPrimaryId();
	String title_project = "项目-导师制审批" + CommonData.getStringRandom(5);
	String title_study_plan = title_project + "指派成学习任务";
	String title_qualifications = "认证-导师制审批" + CommonData.getStringRandom(5);
	
	//导师制一期
 	@Test(description="1.创建导师制等级接口", priority=1)
 	public void testTutorsLeverAdd()  {
 		System.out.println("1.创建导师制等级接口:");
 		String res = TutorsBusiness.TutorsLeverAdd(name);
 		lever_id = (String) JSONPath.read(res, "$.data.id");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("code="+code+","+"lever_id="+lever_id);
 		Assert.assertEquals(msg, "保存成功","1.创建导师制等级接口：" + res);
 	}
 	
 	
 	@Test(description="2.创建导师制等级-该等级已存在接口", priority=2)
 	public void testTutorsLeverAddExits()  {
 		System.out.println("2.创建导师制等级-该等级已存在接口:");
 		String res = TutorsBusiness.TutorsLeverAdd(name);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("code="+code+","+"msg="+msg);
 		Assert.assertEquals(msg, "导师等级名称已存在,请修改后再试","2.创建导师制等级-该等级已存在接口：" + res);
 	}
 	
 	
 	@Test(description="3.查询导师等级接口", priority=3)
 	public void testLeverList()  {
 		System.out.println("3.查询导师等级接口:");
 		String res = TutorsBusiness.LeverList(name);
 		LeverName = (String)JSONPath.read(res, "$.list[0].name");
 		String total = (String)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotEquals(total, "0","3.查询导师等级接口：" + total);
 	}
 	
 	@Test(description="4.编辑导师等级接口", priority=4)
 	public void testUpdateTutorsLever()  {
 		System.out.println("4.编辑导师等级接口:");
 		String res = TutorsBusiness.UpdateTutorsLever(lever_id,LeverName);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertNotEquals(msg, "","4.编辑导师等级接口：" + msg);
 	}
 	
 	
 	@Test(description="5.创建导师接口", priority=5)
 	public void testTutorsAdd()  {
 		System.out.println("5.创建导师接口:");
 		String res = TutorsBusiness.TutorsAdd(lever_id);
 		tutor_id = (String) JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("code="+code+","+"tutor_id="+tutor_id);
 		Assert.assertEquals(msg, "","5.创建导师接口：" + res);
 	}
 	
 	
 	@Test(description="6.创建导师-导师已关联接口", priority=6)
 	public void testTutorsAddExits()  {
 		System.out.println("6.创建导师-导师已关联接口:");
 		String res = TutorsBusiness.TutorsAdd(lever_id);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("code="+code);
 		Assert.assertEquals(msg, "导师已存在","6.创建导师-导师已关联接口：" + res);
 	}
 		
 	
 	@Test(description="7.导师列表-停用导师接口", priority=7)
 	public void testUpDateStatusOff()  {
 		System.out.println("7.导师列表-停用导师接口:");
 		String res = TutorsBusiness.UpDateStatus(tutor_id,"off");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("code="+code);
 		Assert.assertEquals(msg, "","7.导师列表-停用导师接口：" + res);
 	}
 	
 	
 	@Test(description="8.导师列表-开启导师接口", priority=8)
 	public void testUpDateStatusOn()  {
 		System.out.println("8.导师列表-开启导师接口:");
 		String res = TutorsBusiness.UpDateStatus(tutor_id,"on");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("code="+code);
 		Assert.assertEquals(msg, "","8.导师列表-开启导师接口：" + res);
 	}
 	
 	@Test(description="9.查询导师列表接口", priority=9)
 	public void testTutorList()  {
 		System.out.println("9.查询导师列表接口:");
 		//导师关联的user_id是唯一的，生成的导师名也是唯一的，只能查出一条数据不用按名称筛选查询
 		String res = TutorsBusiness.TutorList();
 		String total = (String)JSONPath.read(res, "$.data.total");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		approval_user = (String)JSONPath.read(res, "$.data.list[0].user_id");
 		System.out.println("total="+total);
 		Assert.assertEquals(msg, "","9.查询导师列表接口：" + res);
 	}
 	
 	@Test(description="10.编辑导师信息接口", priority=10)
 	public void testUpdateTutors()  {
 		System.out.println("10.编辑导师信息接口:");
 		String res = TutorsBusiness.UpdateTutors(tutor_id);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg, "","10.编辑导师信息接口：" + res);
 	}
 	
 	@Test(description="11.添加带教人员接口", priority=11)
 	public void testApprenticeAdd()  {
 		System.out.println("11.添加带教人员接口:");
 		String res = TutorsBusiness.ApprenticeAdd(tutor_id);
 		String data = (String) JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("code="+code+","+"data="+data);
 		Assert.assertEquals(msg, "","11.添加带教人员接口：" + res);
 	}
 	
 	@Test(description="12.新员工培训第一步接口", priority=12)
 	public void testSaveOrUpdateBaseInfo()  {
 		System.out.println("12.新员工培训第一步接口:");
 		String res = TutorsBusiness.SaveOrUpdateBaseInfo(train_name);
 		employee_train_id = (String) JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("code="+code+","+"employee_train_id="+employee_train_id);
 		Assert.assertEquals(msg, "","12.新员工培训第一步接口：" + res);
 	}
 	
 	@Test(description="13.新员工培训第二步接口", priority=13)
 	public void testUpdateContent()  {
 		System.out.println("13.新员工培训第二步接口:");
 		String res = TutorsBusiness.UpdateContent(employee_train_id);
 		String data = (String) JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("code="+code+","+"data="+data);
 		Assert.assertEquals(msg, "","13.新员工培训第二步接口：" + res);
 	}
 	
 	@Test(description="14.新员工培训第三步接口", priority=14)
 	public void testUpdateSettingPublish()  {
 		System.out.println("14.新员工培训第三步接口:");
 		String res = TutorsBusiness.UpdateSettingPublish(employee_train_id);
 		String data = (String) JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("code="+code+","+"data="+data);
 		Assert.assertEquals(msg, "","14.新员工培训第三步接口：" + res);
 	}
 	
 	@Test(description="15.获取新员工培训任务列表接口", priority=15)
 	public void testGetList()  {
 		System.out.println("15.获取新员工培训任务列表接口:");
 		String res = TutorsBusiness.GetList(train_name);
 		assign_id = (String) JSONPath.read(res, "$.list[0].id");
 		System.out.println("assign_id="+assign_id);
 		Assert.assertNotEquals(assign_id, "","15.获取新员工培训任务列表接口：" + res);
 	}
 	
 	@Test(description="16.新员工培训指派接口", priority=16)
 	public void testEmployeeTrainAssign()  {
 		System.out.println("16.新员工培训指派接口:");
 		String res = TutorsBusiness.EmployeeTrainAssign(assign_id);
 		String result = (String) JSONPath.read(res, "$.result");
 		System.out.println("result="+result);
 		Assert.assertEquals(result, "指派成功","16.新员工培训指派接口：" + res);
 	}
 	
 	@Test(description="17.获取选择导师和辅导接口路径里的id接口", priority=17)
 	public void testGetOne()  {
 		System.out.println("17.获取选择导师和辅导接口路径里的id接口");
 		String res = TutorsBusiness.GetOne(employee_train_id);
 		save_course_id = (String) JSONPath.read(res, "$.data.courseInfo[0].courseId");
 		choose_tutor_course_id = (String) JSONPath.read(res, "$.data.courseInfo[1].courseId");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("save_course_id="+save_course_id+","+"choose_tutor_course_id="+choose_tutor_course_id);
 		Assert.assertEquals(msg, "success","17.获取选择导师和辅导接口路径里的id接口：" + res);
 	}
 	

 	
 	@Test(description="18.选择导师接口", priority=18)
 	public void testChooseTutor()  {
 		System.out.println("18.选择导师接口:");
 		String res = TutorsBusiness.ChooseTutor(employee_train_id,choose_tutor_course_id);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		String total = (String)JSONPath.read(res,"$.data.total");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg, "","18.选择导师接口：" + total);
 	}
 	
 	@Test(description="19.导师带教详情接口", priority=19)
 	public void testApprenticesList()  {
 		System.out.println("19.选择辅导接口:");
 		String res = TutorsBusiness.ApprenticesList(tutor_id);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		apprentices_id = (String) JSONPath.read(res, "$.data.list[0].id");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg, "","19.选择辅导接口：" + res);
 	}
 	
 	@Test(description="20.学员端-我的任务-辅导任务-未完成接口", priority=20)
 	public void testTutorialsUnfinishedList()  {
 		System.out.println("20.学员端-我的任务-辅导任务-未完成接口:");
 		String res = TutorsBusiness.TutorialsList(train_name,"unfinished");
 		//tutorials-id 是导师辅导列表的每个记录的id
 		tutorials_id = (String)JSONPath.read(res, "$.list[0].tutorials_id");
 		System.out.println("tutorials_id="+tutorials_id);
 		String total = (String)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotEquals(total, "0","20.学员端-我的任务-辅导任务-未完成接口：" + res);
 	}
 	
 	@Test(description="21.学员端-我的任务-辅导任务-已完成接口", priority=21)
 	public void testTutorialsFinishedList()  {
 		System.out.println("21.学员端-我的任务-辅导任务-已完成接口:");
 		String res = TutorsBusiness.TutorialsList("","finished");
 		String total = (String)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotNull(total,"21.学员端-我的任务-辅导任务-已完成接口：" + res);
 	}
 	
 	@Test(description="22.学员端-我的任务-辅导任务-未完成详情页接口", priority=22)
 	public void testTutorialsDetail()  {
 		System.out.println("22.学员端-我的任务-辅导任务-未完成详情页接口:");
 		String res = TutorsBusiness.TutorialsDetail(tutorials_id,"unfinished");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg, "success","22.学员端-我的任务-辅导任务-未完成详情页接口：" + res);
 	}
 	
 	@Test(description="23.学员端-我的任务-辅导任务-未完成详情页-提交辅导接口", priority=23)
 	public void testTutorialsSubmit()  {
 		System.out.println("23.学员端-我的任务-辅导任务-未完成详情页-提交辅导接口:");
 		String res = TutorsBusiness.TutorialsSubmit(tutorials_id);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg, "success","23.学员端-我的任务-辅导任务-未完成详情页-提交辅导接口：" + res);
 	}
 	
 	@Test(description="24.学员端-学员评估辅导接口", priority=24)
 	public void testTutorialsEvaluation()  {
 		System.out.println("24.学员端-学员评估辅导接口:");
 		String res = TutorsBusiness.TutorialsEvaluation(employee_train_id,tutorials_id);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg, "","24.学员端-学员评估辅导接口：" + res);
 	}
 	
 	
 	@Test(description="25.学员端-我的任务列表-学员查看辅导详情接口", priority=25)
 	public void testTutorialsInfo()  {
 		System.out.println("25.学员端-我的任务列表-学员查看辅导详情接口:");
 		String res = TutorsBusiness.TutorialsInfo(tutorials_id,"single");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg, "","25.学员端-我的任务列表-学员查看辅导详情接口：" + res);
 	}
 	
 	
 	//导师制二期
 	@Test(description="26.创建学习任务接口", priority=26)
 	public void testStudyAdd()  {
 		String res = TutorOperationBusiness.StudyAdd(title,"tutor");
 		study_id = (String) JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("26.创建学习任务接口:"+"msg="+msg+","+"study_id="+study_id);
 		Assert.assertEquals(msg, "新增计划成功！","26.创建学习任务接口：" + res);
 	}
 	
 	
 	@Test(description="27.创建学习项目第一步接口", priority=27)
 	public void testStudyStudyProjectSaveBaseInfo()  {
 		String cover = "https://oss.coolcollege.cn/1800048808671973376.png";
        String base_cover= "https://oss.coolcollege.cn/1800048808671973376.png";
 		String res = TutorOperationBusiness.StudyProjectSaveBaseInfo(title_project,classification_id,cover,base_cover);
 		project_id = (String) JSONPath.read(res, "$.id");
 		String result = (String) JSONPath.read(res, "$.result");
 		System.out.println("27.创建学习项目第一步接口:"+"project_id="+project_id);
 		Assert.assertEquals(result, "true","27.创建学习项目第一步接口" + res);
 	}
 	
 	@Test(description="28.创建学习项目第二步接口", priority=28)
 	public void testStudyProjectSaveStageContent()  {
 		String res = TutorOperationBusiness.StudyProjectSaveStageContent(project_id);
 		String result = (String) JSONPath.read(res, "$.result");
 		System.out.println("28.创建学习项目第二步接口:"+"result="+result);
 		Assert.assertEquals(result, "true","28.创建学习项目第二步接口" + res);
 	}
 	
 	@Test(description="29.创建学习项目第三步接口", priority=29)
 	public void testStudyProjectSaveSettings()  {
 		String res = TutorOperationBusiness.StudyProjectSaveSettings(project_id);
 		String result = (String) JSONPath.read(res, "$.result");
 		System.out.println("29.创建学习项目第三步接口:"+"result="+result);
 		Assert.assertEquals(result, "true","29.创建学习项目第三步接口" + res);
 	}
 	
 	@Test(description="30.学习项目指派时查询接口", priority=30)
 	public void testStudyProjectsQuery()  {
 		String res = TutorOperationBusiness.StudyProjectsQuery(project_id);
 		course_id = (String) JSONPath.read(res, "$.stages[0].resources[0].course_id");
 		System.out.println("30.学习项目指派时查询接口:"+"course_id="+course_id);
 		Assert.assertNotEquals(course_id, "","30.学习项目指派时查询接口" + res);
 	}
 	
 	@Test(description="31.学习项目指派接口", priority=31)
 	public void testStudyPlanAdd()  {
 		String res = TutorOperationBusiness.StudyPlanAdd(title_study_plan,classification_id,course_id);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		project_plan_id = (String) JSONPath.read(res, "$.data");
 		System.out.println("31.学习项目指派接口:"+"msg="+msg);
 		Assert.assertNotEquals(msg, "新增计划成功","31.学习项目指派接口" + res);
 	}
 	
 	
 	@Test(description="32.学员端-我的学习任务列表接口", priority=32)
 	public void testMyTaskGetList()  {
 		String res = TutorOperationBusiness.MyTaskGetList("导师制审批");
 		MyProjectTask_id = (String) JSONPath.read(res, "$.list[0].id");
 		MyStudyPlanTask_id = (String) JSONPath.read(res, "$.list[1].id");
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("32.学员端-我的学习任务列表接口:"+"我的学习项目MyProjectTask_id="+MyProjectTask_id+","+"我的学习任务MyStudyPlanTask_id="+MyStudyPlanTask_id);
 		Assert.assertNotEquals(total, 0,"32.学员端-我的学习任务列表接口" + res);
 	}
 	
 	@Test(description="33.学员端-我的学习任务下实操作业接口", priority=33)
 	public void testMyTaskOperation()  {
 		String res_project = TutorOperationBusiness.MyTaskOperation(MyProjectTask_id);
 		String res_study_plan = TutorOperationBusiness.MyTaskOperation(MyStudyPlanTask_id);
 		MyProjectOperation_id = (String) JSONPath.read(res_project, "$.data.courseInfo[0].courseId");
 		MyStudyPlanOperation_id = (String) JSONPath.read(res_study_plan, "$.data.courseInfo[0].courseId");
 		String msg_project = (String) JSONPath.read(res_project, "$.msg");
 		String msg_study_plan = (String) JSONPath.read(res_study_plan, "$.msg");
 		System.out.println("34.学员端-我的学习任务下实操作业接口:"+"MyProjectOperation_id="+MyProjectOperation_id+","+"MyStudyPlanOperation_id="+MyStudyPlanOperation_id);
 		Assert.assertEquals(msg_project, "success","34.学员端-我的学习任务下实操作业接口" + res_project);
 		Assert.assertEquals(msg_study_plan, "success","34.学员端-我的学习任务下实操作业接口" + msg_study_plan);
 	}
 	
 		
 	
 	
 	@Test(description="35.提交实操作业-选择导师接口", priority=35)
 	public void testOperationsTutors()  {
 		String res_project = TutorOperationBusiness.OperationsTutors(MyProjectOperation_id);
 		String res_study_plan = TutorOperationBusiness.OperationsTutors(MyStudyPlanOperation_id);
 		String msg_project = (String) JSONPath.read(res_project, "$.msg");
 		String msg_study_plan = (String) JSONPath.read(res_study_plan, "$.msg");
 		System.out.println("35.提交实操作业-选择导师接口:"+"msg_project="+msg_project+","+"msg_study_plan="+msg_study_plan);
 		Assert.assertEquals(msg_project, "","35.提交实操作业-选择导师接口" + res_project);
 		Assert.assertEquals(msg_study_plan, "","35.提交实操作业-选择导师接口" + res_study_plan);
 	}
 	
 	
 	@Test(description="36.提交实操作业-提交接口", priority=36)
 	public void testOperationSave()  {
 		String res_project = TutorOperationBusiness.OperationSave(approval_user,MyProjectOperation_id,"1");
 		String res_study_plan = TutorOperationBusiness.OperationSave(approval_user,MyStudyPlanOperation_id,"1");
 		String msg_project = (String) JSONPath.read(res_project, "$.msg");
 		String msg_study_plan = (String) JSONPath.read(res_study_plan, "$.msg");
 		System.out.println("36.提交实操作业-提交接口:"+msg_project);
 		Assert.assertEquals(msg_project, "提交成功","36.提交实操作业-提交接口" + res_project);
 		Assert.assertEquals(msg_study_plan, "提交成功","36.提交实操作业-提交接口" + res_study_plan);
 	}
 	
 	@Test(description="37.获取审批实操作业的入参id接口", priority=37)
 	public void testApproval()  {
 		String res_project = TutorOperationBusiness.Approval(MyProjectOperation_id);
 		String res_study_plan = TutorOperationBusiness.Approval(MyStudyPlanOperation_id);
 		project_approval_id = (String) JSONPath.read(res_project, "$.data.id");
 		study_plan_approval_id = (String) JSONPath.read(res_study_plan, "$.data.id");
 		String msg_project = (String) JSONPath.read(res_project, "$.msg");
 		String msg_study_plan = (String) JSONPath.read(res_study_plan, "$.msg");
 		System.out.println("37.获取审批实操作业的入参id接口:"+"project_approval_id="+project_approval_id+","+"study_plan_approval_id="+study_plan_approval_id);
 		Assert.assertEquals(msg_project, "查询成功","37.获取审批实操作业的入参id接口" + res_project);
 		Assert.assertEquals(msg_study_plan, "查询成功","37.获取审批实操作业的入参id接口" + res_study_plan);
 	}
 	
 	@Test(description="38.审批实操作业接口", priority=38)
 	public void testSubmitOperation()  {
 		String res_project = TutorOperationBusiness.SubmitOperation(project_approval_id);
 		String res_study_plan = TutorOperationBusiness.SubmitOperation(study_plan_approval_id);
 		String msg_project = (String) JSONPath.read(res_project, "$.msg");
 		String msg_study_plan = (String) JSONPath.read(res_study_plan, "$.msg");
 		System.out.println("38.审批实操作业接口:"+"msg_project="+msg_project+","+"msg_study_plan"+msg_study_plan);
 		Assert.assertEquals(msg_project, "success","38.审批实操作业接口" + res_project);
 		Assert.assertEquals(msg_study_plan, "success","38.审批实操作业接口" + msg_study_plan);
 	}
 	
 	@Test(description="39.删除学习任务接口", priority=39)
 	public void testDeleteStudy()  {
 		String res_project = TutorOperationBusiness.DeleteStudy(project_plan_id);
 		String res_study_plan = TutorOperationBusiness.DeleteStudy(study_id);
 		String msg_project = (String) JSONPath.read(res_project, "$.msg");
 		String msg_study_plan = (String) JSONPath.read(res_study_plan, "$.msg");
 		System.out.println("39.删除学习任务接口:"+"msg_project="+msg_project+","+"msg_study_plan"+msg_study_plan);
 		Assert.assertEquals(msg_project, "删除学习计划成功","39.删除学习任务接口" + msg_project);
 		Assert.assertEquals(msg_study_plan, "删除学习计划成功","39.删除学习任务接口" + msg_study_plan);
 	}
 	
 	@Test(description="40.删除学习项目接口", priority=40)
 	public void testDeleteProject()  {
 		String res = TutorOperationBusiness.DeleteProject(project_id);
 		String deleted = (String) JSONPath.read(res, "$.deleted");
 		System.out.println("40.删除学习项目接口:"+"deleted="+deleted);
 		Assert.assertEquals(deleted, "true","40.删除学习项目接口" + deleted);
 	}
 	
 	@Test(description="41.获取证书列表接口", priority=41)
 	public void testCertificateGetList()  {
 		//任意获取一个证书id无需筛选条件
 		String res = TutorOperationBusiness.CertificateGetList();
 		certificate_id = (String) JSONPath.read(res, "$.list[0].id");
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("41.获取证书列表接口:"+"certificate_id="+certificate_id);
 		Assert.assertNotEquals(total, 0,"41.删除学习项目接口" + res);
 	}
 	
 	@Test(description="42.新建岗位认证接口", priority=42)
 	public void testQualificationsAdd() throws UnsupportedEncodingException  {
// 		String scopesTemp = "{\"department_list\":[],\"user_list\":[],\"group_list\":[],\"post_list\":[]}";
// 		String scopes = JSONObject.parseObject(scopesTemp).toJSONString();
// 		String stageJsonTemp = "[{\"content\":\"\",\"stageName\":\"阶段1\",\"isOrder\":false,\"stageSort\":1,\"stageId\":\"\","
// 				+ "\"startTime\":null,\"endTime\":null,\"course\":[{\"courseType\":10,\"courseSort\":0,\"flag\":4,\"type\":\"sc\","
// 				+ "\"id\":\"\",\"title\":\""+title_qualifications+"\",\"content\":\"test\",\"resources\":[],\"scoreSetting\":{\"scoreSwitch\":\"off\","
// 				+ "\"score\":100},\"workflowJson\":{\"type\":\"tutor\",\"userIds\":[]},\"mappingId\":\"\"}]}]";
// 		String stageJson = JSONObject.parseArray(stageJsonTemp).toJSONString();
// 		String res = TutorOperationBusiness.QualificationsAdd(certificate_id,scopes,stageJson,title_qualifications);
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		qualifications_id = (String) JSONPath.read(res, "$.data");
// 		System.out.println("42.新建岗位认证接口:"+"msg="+msg+","+"qualifications_id="+qualifications_id);
// 		Assert.assertEquals(msg, "新增计划成功！","42.新建岗位认证接口" + res);
 	}
 	
 	
 	@Test(description="43.移动端-获取实操作业提交里的id接口", priority=43)
 	public void testQualificationsItems()  {
 		String res = TutorOperationBusiness.QualificationsItems(qualifications_id);
 		qualifications_opertion_id = (String) JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].id");
 		System.out.println("43.移动端-获取实操作业提交里的id接口:"+"qualifications_opertion_id="+qualifications_opertion_id);
 		Assert.assertNotEquals(qualifications_opertion_id,"","43.移动端-获取实操作业提交里的id接口" + res);
 	}
 	
 	
 	@Test(description="44.移动端-提交实操作业-导师状态正常接口", priority=44)
 	public void testQualificationsOpertionSave()  {
// 		String res = TutorOperationBusiness.QualificationsOpertionSave(qualifications_opertion_id,approval_user);
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("44.移动端-提交实操作业-导师状态正常接口:"+"msg="+msg);
// 		Assert.assertEquals(msg,"提交成功","44.移动端-提交实操作业-导师状态正常接口" + res);
 	}
 	
 	@Test(description="45.移动端-提交实操作业-实操已提交过接口", priority=45)
 	public void testQualificationsOpertionSaved()  {
// 		String res = TutorOperationBusiness.QualificationsOpertionSave(qualifications_opertion_id,approval_user);
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("45.移动端-提交实操作业-实操已提交过接口:"+"msg="+msg);
// 		Assert.assertEquals(msg,"提交成功","45.移动端-提交实操作业-实操已提交过接口" + res);
 	}
 	
 	
 	@Test(description="46.停用导师接口", priority=46)
 	public void testUpDateStatusOffAgin()  {
 		System.out.println("46.停用导师接口:");
 		String res = TutorOperationBusiness.UpDateStatus(tutor_id,"off");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("code="+code);
 		Assert.assertEquals(msg, "","46.停用导师接口：" + res);
 	}
 	
 	@Test(description="47.移动端-提交实操作业-导师已停用接口", priority=47)
 	public void testQualificationsOpertionSaveTutorOff()  {
 		String res = TutorOperationBusiness.QualificationsOpertionSave(qualifications_opertion_id,approval_user);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("47.移动端-提交实操作业-导师已停用接口:"+"message="+message);//The tutor has been disabled
 		Assert.assertNotEquals(message,"提交成功","47.移动端-提交实操作业-导师已停用接口" + res);
 	}
 	
// 	@Test(description="27.查询导师带教关系接口", priority=27)
// 	public void testApprenticesList()  {
// 		String res = TutorOperationBusiness.ApprenticesList(tutor_id);
// 		apprentices_id = (String) JSONPath.read(res, "$.data.list[0].id");
// 		String total = (String) JSONPath.read(res, "$.data.total");
// 		Assert.assertNotEquals(total,"0","27.查询导师带教关系接口" + res);
// 	}
 	
 	
 	@Test(description="48.删除导师带教关系接口", priority=48)
 	public void testApprenticesDelete()  {
// 		String res = TutorOperationBusiness.ApprenticesDelete(apprentices_id);
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		Assert.assertEquals(msg,"","48.查询导师带教关系接口" + res);
 	}
 	

 	@Test(description="49.移动端-提交实操作业-导师不存在带教关系接口", priority=49)
 	public void testQualificationsOpertionSaveNoTutor()  {
// 		System.out.println("approval_user="+approval_user);
// 		String res = TutorOperationBusiness.QualificationsOpertionSave(qualifications_opertion_id,approval_user);
// 		String message = (String) JSONPath.read(res, "$.message");
// 		System.out.println("49.移动端-提交实操作业-导师不存在带教关系接口:"+"message="+message);
// 		Assert.assertEquals(message,"No mentor-disciple relationship found","49.移动端-提交实操作业-导师不存在带教关系接口" + res);
 	}
 	
 	
 	@Test(description="50.开启导师接口", priority=50)
 	public void testUpDateStatusOnAgin()  {
 		System.out.println("50.开启导师接口:");
 		String res = TutorOperationBusiness.UpDateStatus(tutor_id,"on");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("code="+code);
 		Assert.assertEquals(msg, "","50.开启导师接口：" + res);
 	}
 	
 	@Test(description="51.移动端(同PC)-获取审批实操作业的入参id接口", priority=51)
 	public void testApprovalQualifications()  {
// 		String res = TutorOperationBusiness.Approval(qualifications_opertion_id);
// 		qualifications_approval_id = (String) JSONPath.read(res, "$.data.id");
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("51.移动端(同PC)-获取审批实操作业的入参id接口:"+"qualifications_approval_id="+qualifications_approval_id);
// 		Assert.assertEquals(msg, "查询成功","51.移动端(同PC)-获取审批实操作业的入参id接口" + res);
 	}
 	
 	@Test(description="52.移动端-审批实操作业-导师状态正常&实操作业状态正常接口", priority=52)
 	public void testSubmitQualificationsOpertion()  {
// 		String res = TutorOperationBusiness.SubmitQualificationsOpertion(qualifications_approval_id);
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("52.移动端-审批实操作业-导师状态正常接口:"+"msg="+msg);
// 		Assert.assertEquals(msg, "success","52.移动端-审批实操作业-导师状态正常接口" + res);
 	}
 	
 	
// 	@Test(description="29.移动端-审批实操作业-导师状态异常/导师不存在接口", priority=29)
// 	public void testSubmitQualificationsOpertionTutor()  {
// 		String res = TutorOperationBusiness.SubmitQualificationsOpertion(qualifications_approval_id);
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("29.移动端-审批实操作业-导师状态异常/导师不存在接口:"+"msg="+msg);
// 		Assert.assertEquals(msg, "success","29.移动端-审批实操作业-导师状态异常/导师不存在接口" + res);
// 	}
 	
 	@Test(description="53.移动端-审批实操作业-实操已审批接口", priority=53)
 	public void testSubmitdQualificationsOpertion()  {
 		String res = TutorOperationBusiness.SubmitQualificationsOpertion(qualifications_approval_id);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("53.移动端-审批实操作业-实操已审批接口:"+"message="+message);
 		Assert.assertNotEquals(message, "success","53.移动端-审批实操作业-实操已审批接口" + res);
 	}
 	
 	
 	@Test(description="54.删除岗位认证接口", priority=54)
 	public void testDeleteQualifications()  {
// 		String res = TutorOperationBusiness.DeleteQualifications(qualifications_id);
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("54.删除岗位认证接口:"+"msg="+msg);
// 		Assert.assertEquals(msg, "能力认证删除成功","54.删除岗位认证接口" + res);
 	}
 	
 	
 	@Test(description="55.新员工培训数据监控按时间筛选接口", priority=55)
 	public void testEmployTrainMonitorsTask()  {
 		long beginTimeTemp = System.currentTimeMillis();  
		long endTimeTemp = beginTimeTemp + 24L*7*3600*1000;
		String start_time = String.valueOf(beginTimeTemp);
		String end_time = String.valueOf(endTimeTemp);
 		String res = TutorsBusiness.EmployTrainMonitorsTask(employee_train_id,start_time,end_time);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("55.新员工培训数据监控按时间筛选接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "查询成功","55.新员工培训数据监控按时间筛选接口" + res);
 	}
 	

 	
 	@Test(description="56.删除导师等级接口", priority=56)
 	public void testLeverDelete()  {
 		System.out.println("56.删除导师等级接口:");
 		String res = TutorsBusiness.LeverDelete(lever_id);
 		String data = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("data="+data);
 		Assert.assertEquals(msg, "","56.删除导师等级接口：" + res);
 	}
 	
 	
 	@Test(description="57.删除导师等级-等级已删除接口", priority =57)
 	public void testLeverDeleted()  {
 		System.out.println("57.删除导师等级-等级已删除接口:");
 		String res = TutorsBusiness.LeverDelete(lever_id);
 		String data = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("data="+data);
 		Assert.assertEquals(msg, "","57.删除导师等级-等级已删除接口：" + res);
 	}
 	
 	
 	@Test(description="58.删除导师接口", priority=58)
 	public void testToturDelete()  {
 		System.out.println("58.删除导师接口:");
 		String res = TutorsBusiness.ToturDelete(tutor_id);
 		String data = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("data="+data);
 		Assert.assertEquals(msg, "","58.删除导师接口：" + res);
 	}
 	
 	
 	@Test(description="59.删除导师-导师已删除接口", priority=59)
 	public void testToturDeleted()  {
 		System.out.println("59.删除导师-导师已删除接口:");
 		String res = TutorsBusiness.ToturDelete(tutor_id);
 		String data = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("data="+data);
 		Assert.assertEquals(msg, "","59.删除导师-导师已删除接口：" + res);
 	}
 	
 	@Test(description="60.删除新员工培训任务接口", priority=60)
 	public void testDeleteEmployTrainTask()  {
 		System.out.println("60.删除新员工培训任务接口:");
 		String res = TutorsBusiness.DeleteEmployTrainTask(employee_train_id);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg, "删除成功","60.删除新员工培训任务接口：" + res);
 	}
 	
 	@Test(description="61.删除新员工培训任务-任务已删除接口", priority=61)
 	public void testDeleteEmployTrainTaskDeleted()  {
 		System.out.println("61.删除新员工培训任务-任务已删除接口:");
 		String res = TutorsBusiness.DeleteEmployTrainTask(employee_train_id);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg, "删除成功","61.删除新员工培训任务-任务已删除接口：" + res);
 	}
 	
 	@Test(description="62.查询已启用的导师列表接口", priority=62)
 	public void testTutorListByStatus()  {
 		String res = TutorsBusiness.TutorList("on");
 		Assert.assertTrue(res.contains("msg"),"查询已启用的导师列表接口" + res);
 	}
 	
 	@Test(description="63.查询已停用的导师列表接口", priority=63)
 	public void testTutorListByOffStatus()  {
 		String res = TutorsBusiness.TutorList("off");
 		Assert.assertTrue(res.contains("msg"),"查询已停用的导师列表接口" + res);
 	}
 	
 	
 	@Test(description="64.按userid下载模板接口", priority=64)
 	public void testDownLoadTemplateUserId()  {
 		System.out.println("64.按userid下载模板接口:");
 		String res = TutorsBusiness.DownLoadTemplate("tutor_user_id");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg, "导出模板文件成功","64.按userid下载模板接口：" + res);
 	}
 	
 	@Test(description="65.按手机号下载模板接口", priority=65)
 	public void testDownLoadTemplateMobile()  {
 		System.out.println("65.按手机号下载模板接口:");
 		String res = TutorsBusiness.DownLoadTemplate("tutor_mobile");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg, "导出模板文件成功","65.按手机号下载模板接口：" + res);
 	}
 	
 	
 	@Test(description="66.按邮箱下载模板接口", priority=66)
 	public void testDownLoadTemplateEmail()  {
 		System.out.println("66.按邮箱下载模板接口:");
 		String res = TutorsBusiness.DownLoadTemplate("tutor_email");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg, "导出模板文件成功","66.按邮箱下载模板接口：" + res);
 	}
 	
 	
 	@Test(description="67.按userid下载模板接口", priority=67)
 	public void testLecturerDownLoadTemplateUserId()  {
 		System.out.println("67.按userid下载模板接口:");
 		String res = TutorsBusiness.LecturerDownLoadTemplate("lecturer_user_id");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg, "导出模板文件成功","67.按userid下载模板接口：" + res);
 	}
 	
 	@Test(description="68.按手机号下载模板接口", priority=68)
 	public void testLecturerDownLoadTemplateMobile()  {
 		System.out.println("68.按手机号下载模板接口:");
 		String res = TutorsBusiness.LecturerDownLoadTemplate("lecturer_mobile");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg, "导出模板文件成功","68.按手机号下载模板接口：" + res);
 	}
 	
 	
 	@Test(description="69.按邮箱下载模板接口", priority=69)
 	public void testLecturerDownLoadTemplateEmail()  {
 		System.out.println("67.按邮箱下载模板接口:");
 		String res = TutorsBusiness.LecturerDownLoadTemplate("lecturer_email");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg, "导出模板文件成功","67.按邮箱下载模板接口：" + res);
 	}
 	
}
