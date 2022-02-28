package group.a.cases;

/**
 * @author wenlingzhi
 *2021年5月27日
 */

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.group.a.business.ResourceLockBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestResourceLock {	
	public static String resource_id = "";
	public static String course_id = "";
	public static String imageText_id = "";
	public static String paper_id = "";
	public static Integer product_id = null;
	public static String questionnaires_id = "";
	public static String project_id = "";
	public static String projectCourse_id = "";
	public static String partProject_id = "";
	public static String partProjectCourse_id = "";
	public static String nullProject_id = "";
	public static String nullProjectCourse_id = "";

	String title = "学习项目资源锁定" + CommonData.getStringRandom(5);
	String classification_id = ClassificationBusines.getPrimaryId();	
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	
	@Test(description="1.查询课件接口", priority=1)
 	public void testCourseResourceGetList()  {
 		String res = ResourceLockBusiness.CourseResourceGetList("0","video");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		resource_id = (String)JSONPath.read(res, "$.list[0].id");
 		System.out.println("1.查询课件接口:"+"total="+total+","+"resource_id="+resource_id);
 		Assert.assertNotEquals(total, null,"1.查询课件接口：" + res);
 	}
	
	
	@Test(description="2.查询课程接口", priority=2)
 	public void testCourseQuery()  {
 		String res = ResourceLockBusiness.CourseQuery("");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		course_id = (String)JSONPath.read(res, "$.list[0].id");
 		System.out.println("2.查询课程接口:"+"total="+total+","+"course_id="+course_id);
 		Assert.assertNotEquals(total, null,"2.查询课程接口：" + res);
 	}
	
	
	@Test(description="3.查询图文课接口", priority=3)
 	public void testCourseTextQuery()  {
 		String res = ResourceLockBusiness.CourseQuery("true");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		imageText_id = (String)JSONPath.read(res, "$.list[0].id");
 		System.out.println("3.查询图文课接口:"+"total="+total+","+"imageText_id="+imageText_id);
 		Assert.assertNotEquals(total, null,"3.查询图文课接口：" + res);
 	}
	
	
	@Test(description="4.查询试卷接口", priority=4)
 	public void testPaperGetList()  {
 		String res = ResourceLockBusiness.PaperGetList();
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		paper_id = (String)JSONPath.read(res, "$.list[0].id");
 		System.out.println("4.查询试卷接口:"+"total="+total+","+"paper_id="+paper_id);
 		Assert.assertNotEquals(total, null,"4.查询试卷接口：" + res);
 	}
	
	
	@Test(description="6.查询测训工具列表接口", priority=6)
 	public void testEvaluationToolsListQuery()  {
 		String res = ResourceLockBusiness.EvaluationToolsList("");
 		System.out.println("6.查询测训工具列表接口:");
 		//测试环境第一个金牌会报错这里避开第一个数据
 		product_id = (Integer)JSONPath.read(res, "$.list[1].product_id");
 		String is_first_page = (String)JSONPath.read(res, "$.is_first_page");
 		System.out.println("success="+is_first_page);
		Assert.assertEquals(is_first_page,"true","6.查询测训工具列表接口"+res);
 	}
	
	
	@Test(description="7.查询问卷接口", priority=7)
 	public void testQueryQuestionnaires()  {
 		String res = ResourceLockBusiness.QueryQuestionnaires();
 		System.out.println("7.查询问卷接口:");
 		questionnaires_id = (String)JSONPath.read(res, "$.questionnaires.list[0].id");
 		Integer release_count = (Integer)JSONPath.read(res, "$.release_count");
 		System.out.println("questionnaires_id="+questionnaires_id+","+"release_count="+release_count);
		Assert.assertNotEquals(release_count,null,"7.查询问卷接口"+res);
 	}
	
	
	@Test(description="8.创建学习项目-锁定全部资源接口", priority=8)
 	public void testStudyProjectSave()  {
 		String cover = "https://oss.coolcollege.cn/1810048818682974377.png";
        String base_cover= "https://oss.coolcollege.cn/1810048818682974377.png";
        String titleTemp = title +"全锁";
 		String resFirst = ResourceLockBusiness.StudyProjectSaveBaseInfo(titleTemp,classification_id,cover,base_cover);
 		project_id = (String) JSONPath.read(resFirst, "$.id"); 
 		projectCourse_id = (String) JSONPath.read(resFirst, "$.course_id"); 
 		String product_idTemp = Integer.toString(product_id);
		//转时间戳,//这里的几个时间都是毫秒的时间戳,且end_time要早于End_time
 		//开始时间不要取当前时间取未来时间，否则会过期
		long beginTimeTemp1 = System.currentTimeMillis();  
		long beginTimeTemp = beginTimeTemp1 + 24L*2*3600*1000;
		long end_timeTemp = beginTimeTemp + 24L*7*3600*1000; 
		long End_timeTemp = beginTimeTemp + 24L*8*3600*1000; 		
 		String resSecond = ResourceLockBusiness.StudyProjectSaveStageContent(project_id,resource_id,true,true,course_id,
 				imageText_id,paper_id,beginTimeTemp,end_timeTemp,beginTimeTemp,End_timeTemp,questionnaires_id,product_idTemp);		
 		String resThird = ResourceLockBusiness.StudyProjectSaveSettings(project_id);
 		String result = (String) JSONPath.read(resThird, "$.result");		
 		System.out.println("8.创建学习项目-锁定全部资源接口:"+"project_id="+project_id+","+"resSecond="+resSecond);
 		Assert.assertEquals(result, "true","8.创建学习项目-锁定全部资源接口" + resThird);
 	}
	
	
	@Test(description="9.创建学习项目-锁定部分资源接口", priority=9)
 	public void testStudyProjectSavePart()  {
 		String cover = "https://oss.coolcollege.cn/1810048818682974377.png";
        String base_cover= "https://oss.coolcollege.cn/1810048818682974377.png";
        String titleTemp = title +"部分锁";
 		String resFirst = ResourceLockBusiness.StudyProjectSaveBaseInfo(titleTemp,classification_id,cover,base_cover);
 		partProject_id = (String) JSONPath.read(resFirst, "$.id"); 
 		partProjectCourse_id = (String) JSONPath.read(resFirst, "$.course_id"); 
 		String product_idTemp = Integer.toString(product_id);
		long beginTimeTemp1 = System.currentTimeMillis();  
		long beginTimeTemp = beginTimeTemp1 + 24L*2*3600*1000;
		long end_timeTemp = beginTimeTemp + 24L*7*3600*1000; 
		long End_timeTemp = beginTimeTemp + 24L*8*3600*1000; 		
 		String resSecond = ResourceLockBusiness.StudyProjectSaveStageContent(partProject_id,resource_id,false,true,course_id,
 				imageText_id,paper_id,beginTimeTemp,end_timeTemp,beginTimeTemp,End_timeTemp,questionnaires_id,product_idTemp);		
 		String resThird = ResourceLockBusiness.StudyProjectSaveSettings(partProject_id);
 		String result = (String) JSONPath.read(resThird, "$.result");		
 		System.out.println("9.创建学习项目-锁定部分资源接口:"+"partProject_id="+partProject_id+","+"resSecond="+resSecond);
 		Assert.assertEquals(result, "true","9.创建学习项目-锁定部分资源接口" + resThird);
 	}
	
	
	@Test(description="10.创建学习项目-不锁定任何资源接口", priority=10)
 	public void testStudyProjectSaveNull()  {
// 		String cover = "https://oss.coolcollege.cn/1810048818682974377.png";
//        String base_cover= "https://oss.coolcollege.cn/1810048818682974377.png";
//        String titleTemp = title +"都不锁";
// 		String resFirst = ResourceLockBusiness.StudyProjectSaveBaseInfo(titleTemp,classification_id,cover,base_cover);
// 		nullProject_id = (String) JSONPath.read(resFirst, "$.id"); 
// 		nullProjectCourse_id = (String) JSONPath.read(resFirst, "$.course_id"); 
// 		String product_idTemp = Integer.toString(product_id);
//		long beginTimeTemp1 = System.currentTimeMillis();  
//		long beginTimeTemp = beginTimeTemp1 + 24L*2*3600*1000;
//		long end_timeTemp = beginTimeTemp + 24L*7*3600*1000; 
//		long End_timeTemp = beginTimeTemp + 24L*8*3600*1000; 		
// 		String resSecond = ResourceLockBusiness.StudyProjectSaveStageContent(nullProject_id,resource_id,false,false,course_id,
// 				imageText_id,paper_id,beginTimeTemp,end_timeTemp,beginTimeTemp,End_timeTemp,questionnaires_id,product_idTemp);		
// 		String resThird = ResourceLockBusiness.StudyProjectSaveSettings(nullProject_id);
// 		String result = (String) JSONPath.read(resThird, "$.result");		
// 		System.out.println("10.创建学习项目-不锁定任何资源接口:"+"nullProject_id="+nullProject_id+","+"resSecond="+resSecond);
// 		Assert.assertEquals(result, "true","10.创建学习项目-不锁定任何资源接口" + resThird);
 	}
	
	
	@Test(description="11.校验学习项目指派页锁定全部资源是否锁定接口", priority=11)
 	public void testQueryLock()  {
// 		String res = ResourceLockBusiness.QueryLock(project_id);
// 		System.out.println("11.校验学习项目指派页锁定全部资源是否锁定接口:");
// 		Boolean resource_lock_resource = (Boolean)JSONPath.read(res, "$.stages[0].resources[0].resource_lock");
// 		Boolean resource_lock_course = (Boolean)JSONPath.read(res, "$.stages[0].resources[1].resource_lock");
// 		Boolean resource_lock_imageText = (Boolean)JSONPath.read(res, "$.stages[0].resources[2].resource_lock");
// 		Boolean resource_lock_exam = (Boolean)JSONPath.read(res, "$.stages[0].resources[3].resource_lock");
// 		Boolean resource_lock_latoja = (Boolean)JSONPath.read(res, "$.stages[0].resources[4].resource_lock");
// 		Boolean resource_lock_questionnaires = (Boolean)JSONPath.read(res, "$.stages[0].resources[5].resource_lock");
// 		Boolean resource_lock_work = (Boolean)JSONPath.read(res, "$.stages[0].resources[6].resource_lock");
// 		Boolean resource_lock_evalution = (Boolean)JSONPath.read(res, "$.stages[0].resources[7].resource_lock");
// 		System.out.println("resource_lock_resource="+resource_lock_resource+","+"resource_lock_course="+resource_lock_course
// 				+","+"resource_lock_imageText="+resource_lock_imageText+","+"resource_lock_exam="+resource_lock_exam+","
// 				+"resource_lock_latoja="+resource_lock_latoja+","+"resource_lock_questionnaires="+resource_lock_questionnaires
// 				+","+"resource_lock_work="+resource_lock_work);
//		Assert.assertTrue(resource_lock_resource,"11.校验学习项目指派页锁定全部资源是否锁定接口"+res);
//		Assert.assertTrue(resource_lock_course,"11.校验学习项目指派页锁定全部资源是否锁定接口"+res);
//		Assert.assertTrue(resource_lock_imageText,"11.校验学习项目指派页锁定全部资源是否锁定接口"+res);
//		Assert.assertTrue(resource_lock_exam,"11.校验学习项目指派页锁定全部资源是否锁定接口"+res);
//		Assert.assertTrue(resource_lock_latoja,"11.校验学习项目指派页锁定全部资源是否锁定接口"+res);
//		Assert.assertTrue(resource_lock_questionnaires,"11.校验学习项目指派页锁定全部资源是否锁定接口"+res);
//		Assert.assertTrue(resource_lock_work,"11.校验学习项目指派页锁定全部资源是否锁定接口"+res);
 	}
	
	
	@Test(description="12.校验学习项目指派页部分资源是否锁定接口", priority=12)
 	public void testQueryLockPart()  {
// 		String res = ResourceLockBusiness.QueryLock(partProject_id);
// 		System.out.println("12.校验学习项目指派页部分资源是否锁定接口:");
// 		Boolean resource_lock_resource = (Boolean)JSONPath.read(res, "$.stages[0].resources[0].resource_lock");
// 		Boolean resource_lock_course = (Boolean)JSONPath.read(res, "$.stages[0].resources[1].resource_lock");
// 		Boolean resource_lock_imageText = (Boolean)JSONPath.read(res, "$.stages[0].resources[2].resource_lock");
// 		Boolean resource_lock_exam = (Boolean)JSONPath.read(res, "$.stages[0].resources[3].resource_lock");
// 		Boolean resource_lock_latoja = (Boolean)JSONPath.read(res, "$.stages[0].resources[4].resource_lock");
// 		Boolean resource_lock_questionnaires = (Boolean)JSONPath.read(res, "$.stages[0].resources[5].resource_lock");
// 		Boolean resource_lock_work = (Boolean)JSONPath.read(res, "$.stages[0].resources[6].resource_lock");
// 		System.out.println("resource_lock_resource="+resource_lock_resource+","+"resource_lock_course="+resource_lock_course
// 				+","+"resource_lock_imageText="+resource_lock_imageText+","+"resource_lock_exam="+resource_lock_exam+","
// 				+"resource_lock_latoja="+resource_lock_latoja+","+"resource_lock_questionnaires="+resource_lock_questionnaires
// 				+","+"resource_lock_work="+resource_lock_work);
//		Assert.assertFalse(resource_lock_resource,"12.校验学习项目指派页部分资源是否锁定接口"+res);
//		Assert.assertTrue(resource_lock_course,"12.校验学习项目指派页部分资源是否锁定接口"+res);
//		Assert.assertTrue(resource_lock_imageText,"12.校验学习项目指派页部分资源是否锁定接口"+res);
//		Assert.assertTrue(resource_lock_exam,"12.校验学习项目指派页部分资源是否锁定接口"+res);
//		Assert.assertTrue(resource_lock_latoja,"12.校验学习项目指派页部分资源是否锁定接口"+res);
//		Assert.assertTrue(resource_lock_questionnaires,"12.校验学习项目指派页部分资源是否锁定接口"+res);
//		Assert.assertTrue(resource_lock_work,"12.校验学习项目指派页部分资源是否锁定接口"+res);
 	}
	
	
	@Test(description="13.校验学习项目指派页无资源是否锁定接口", priority=13)
 	public void testQueryLockNull()  {
// 		String res = ResourceLockBusiness.QueryLock(nullProject_id);
// 		System.out.println("13.校验学习项目指派页无资源是否锁定接口:");
// 		Boolean resource_lock_resource = (Boolean)JSONPath.read(res, "$.stages[0].resources[0].resource_lock");
// 		Boolean resource_lock_course = (Boolean)JSONPath.read(res, "$.stages[0].resources[1].resource_lock");
// 		Boolean resource_lock_imageText = (Boolean)JSONPath.read(res, "$.stages[0].resources[2].resource_lock");
// 		Boolean resource_lock_exam = (Boolean)JSONPath.read(res, "$.stages[0].resources[3].resource_lock");
// 		Boolean resource_lock_latoja = (Boolean)JSONPath.read(res, "$.stages[0].resources[4].resource_lock");
// 		Boolean resource_lock_questionnaires = (Boolean)JSONPath.read(res, "$.stages[0].resources[5].resource_lock");
// 		Boolean resource_lock_work = (Boolean)JSONPath.read(res, "$.stages[0].resources[6].resource_lock");
// 		System.out.println("resource_lock_resource="+resource_lock_resource+","+"resource_lock_course="+resource_lock_course
// 				+","+"resource_lock_imageText="+resource_lock_imageText+","+"resource_lock_exam="+resource_lock_exam+","
// 				+"resource_lock_latoja="+resource_lock_latoja+","+"resource_lock_questionnaires="+resource_lock_questionnaires
// 				+","+"resource_lock_work="+resource_lock_work);
//		Assert.assertFalse(resource_lock_resource,"13.校验学习项目指派页无资源是否锁定接口"+res);
//		Assert.assertFalse(resource_lock_course,"13.校验学习项目指派页无资源是否锁定接口"+res);
//		Assert.assertFalse(resource_lock_imageText,"13.校验学习项目指派页无资源是否锁定接口"+res);
//		Assert.assertFalse(resource_lock_exam,"13.校验学习项目指派页无资源是否锁定接口"+res);
//		Assert.assertFalse(resource_lock_latoja,"13.校验学习项目指派页无资源是否锁定接口"+res);
//		Assert.assertFalse(resource_lock_questionnaires,"13.校验学习项目指派页无资源是否锁定接口"+res);
//		Assert.assertFalse(resource_lock_work,"13.校验学习项目指派页无资源是否锁定接口"+res);
 	}
	
	
	@Test(description="14.编辑学习项目资源锁定接口", priority=14)
 	public void testStudyProjectEdit()  {
		String product_idTemp = Integer.toString(product_id);
		long beginTimeTemp1 = System.currentTimeMillis();  
		long beginTimeTemp = beginTimeTemp1 + 24L*2*3600*1000;
		long end_timeTemp = beginTimeTemp + 24L*7*3600*1000; 
		long End_timeTemp = beginTimeTemp + 24L*8*3600*1000; 		
 		String resSecond = ResourceLockBusiness.StudyProjectSaveStageContent(partProject_id,resource_id,true,false,course_id,
 				imageText_id,paper_id,beginTimeTemp,end_timeTemp,beginTimeTemp,End_timeTemp,questionnaires_id,product_idTemp);		
 		String resThird = ResourceLockBusiness.StudyProjectSaveSettings(partProject_id);
 		String result = (String) JSONPath.read(resThird, "$.result");		
 		System.out.println("14.编辑学习项目资源锁定接口:"+"partProject_id="+partProject_id+","+"resSecond="+resSecond);
 		Assert.assertEquals(result, "true","14.编辑学习项目资源锁定接口" + resThird);
 	}
	
	
	@Test(description="15.校验学习项目编辑资源锁定是否有效接口", priority=15)
 	public void testQueryLockCheck()  {
// 		String res = ResourceLockBusiness.QueryLock(partProject_id);
// 		System.out.println("15.校验学习项目编辑资源锁定是否有效接口:");
// 		Boolean resource_lock_resource = (Boolean)JSONPath.read(res, "$.stages[0].resources[0].resource_lock");
// 		Boolean resource_lock_course = (Boolean)JSONPath.read(res, "$.stages[0].resources[1].resource_lock");
// 		Boolean resource_lock_imageText = (Boolean)JSONPath.read(res, "$.stages[0].resources[2].resource_lock");
// 		Boolean resource_lock_exam = (Boolean)JSONPath.read(res, "$.stages[0].resources[3].resource_lock");
// 		Boolean resource_lock_latoja = (Boolean)JSONPath.read(res, "$.stages[0].resources[4].resource_lock");
// 		Boolean resource_lock_questionnaires = (Boolean)JSONPath.read(res, "$.stages[0].resources[5].resource_lock");
// 		Boolean resource_lock_work = (Boolean)JSONPath.read(res, "$.stages[0].resources[6].resource_lock");
// 		System.out.println("resource_lock_resource="+resource_lock_resource+","+"resource_lock_course="+resource_lock_course
// 				+","+"resource_lock_imageText="+resource_lock_imageText+","+"resource_lock_exam="+resource_lock_exam+","
// 				+"resource_lock_latoja="+resource_lock_latoja+","+"resource_lock_questionnaires="+resource_lock_questionnaires
// 				+","+"resource_lock_work="+resource_lock_work);
//		Assert.assertTrue(resource_lock_resource,"15.校验学习项目编辑资源锁定是否有效接口"+res);
//		Assert.assertFalse(resource_lock_course,"15.校验学习项目编辑资源锁定是否有效接口"+res);
//		Assert.assertFalse(resource_lock_imageText,"15.校验学习项目编辑资源锁定是否有效接口"+res);
//		Assert.assertFalse(resource_lock_exam,"15.校验学习项目编辑资源锁定是否有效接口"+res);
//		Assert.assertFalse(resource_lock_latoja,"15.校验学习项目编辑资源锁定是否有效接口"+res);
//		Assert.assertFalse(resource_lock_questionnaires,"15.校验学习项目编辑资源锁定是否有效接口"+res);
//		Assert.assertFalse(resource_lock_work,"15.校验学习项目编辑资源锁定是否有效接口"+res);
 	}

	
	//分享时出二维码无接口是前端拼接的，这里只校验二维码显示接口
	@Test(description="16.学习项目分享-钉钉码接口", priority=16)
 	public void testShareDing()  {
 		String res = ResourceLockBusiness.ShareDing();
 		String status = (String) JSONPath.read(res, "$.status");
 		System.out.println("16.学习项目分享-钉钉码接口:"+"status="+status);
 		Assert.assertEquals(status, "true","16.学习项目分享-钉钉码接口" + status);
 	}
		
	
	@Test(description="17.学习项目分享-微信码-学习项目正常接口", priority=17)
 	public void testShareWx()  {
		String scene = "projectId="+projectCourse_id+"&flag=study_project&enterpriseId="+enterpriseId;
 		String res = ResourceLockBusiness.ShareWx("study_project",scene,enterpriseId);
 		String auth_code = (String) JSONPath.read(res, "$.auth_code");
 		System.out.println("17.学习项目分享-微信码-学习项目正常接口:"+"auth_code="+auth_code);
 		Assert.assertEquals(auth_code, "","17.学习项目分享-微信码-学习项目正常接口" + auth_code);
 	}
	
	
	@Test(description="18.学习项目分享-微信码-学习项目不存在接口", priority=18)
 	public void testShareWxNotExits()  {
		String scene = "projectId="+project_id+"&flag=study_project&enterpriseId="+enterpriseId;
 		String res = ResourceLockBusiness.ShareWx("study_project",scene,enterpriseId);
 		String auth_code = (String) JSONPath.read(res, "$.auth_code");
 		System.out.println("18.学习项目分享-微信码-学习项目不存在接口:"+"auth_code="+auth_code);
 		Assert.assertEquals(auth_code, "","18.学习项目分享-微信码-学习项目不存在接口" + auth_code);
 	}
	
	
	@Test(description="19.删除学习项目接口", priority=19)
 	public void testDeleteProject()  {
 		String res = ResourceLockBusiness.DeleteProject(project_id);
 		String res_part = ResourceLockBusiness.DeleteProject(partProject_id);
 		String res_null = ResourceLockBusiness.DeleteProject(nullProject_id);
 		String deleted = (String) JSONPath.read(res, "$.deleted");
 		System.out.println("19.删除学习项目接口:"+"deleted="+deleted);
 		Assert.assertEquals(deleted, "true","19.删除学习项目接口" + deleted);
 	}
	
	
	@Test(description="20.学习项目分享-微信码-学习项目已被删除接口", priority=20)
 	public void testShareWxDelete()  {
		String scene = "projectId="+projectCourse_id+"&flag=study_project&enterpriseId="+enterpriseId;
 		String res = ResourceLockBusiness.ShareWx("study_project",scene,enterpriseId);
 		String auth_code = (String) JSONPath.read(res, "$.auth_code");
 		System.out.println("20.学习项目分享-微信码-学习项目已被删除接口:"+"auth_code="+auth_code);
 		Assert.assertEquals(auth_code, "","20.学习项目分享-微信码-学习项目已被删除接口" + auth_code);
 	}
	
	
	

}
