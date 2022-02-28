package group.a.cases;
/**
 * @author wenlingzhi
 *2021年5月11日
 */

import cn.kxy.group.a.business.StudyPlanPublishBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestStudyPlanPublish {	
	public static String StudyPlan_id = "";
	public static String MyStudyPlan_id = "";
	public static String stage_id = "";
	public static String course_id = "";
	public static String mapping_id = "";
	public static String StudyPlanCancel_id = "";

	String studyPlan_title = "学习任务的发布取消发布" + CommonData.getStringRandom(5);
	String studyPlanCancel_title = "学习任务测试删除和发布" + CommonData.getStringRandom(5);
	
 	@Test(description="1.创建学习任务接口", priority=1)
 	public void testStudyAdd()  {
 		String res = StudyPlanPublishBusiness.StudyAdd(studyPlan_title,"tutor");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("1.创建学习任务接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "新增计划成功！","1.创建学习任务接口：" + res);
 	}
 	
 	
 	@Test(description="2.创建学习任务-用来测试取消发布前先删除任务接口", priority=2)
 	public void testStudyAddCancel()  {
 		String res = StudyPlanPublishBusiness.StudyAdd(studyPlanCancel_title,"tutor");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("2.创建学习任务-用来测试取消发布前先删除任务接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "新增计划成功！","2.创建学习任务-用来测试取消发布前先删除任务接口：" + res);
 	}
 	
 	@Test(description="3.学习任务列表接口", priority=3)
 	public void testStudyPlanGetList()  {
 		String res = StudyPlanPublishBusiness.StudyPlanGetList(studyPlan_title);
 		String resCancel = StudyPlanPublishBusiness.StudyPlanGetList(studyPlanCancel_title);
 		StudyPlan_id = (String) JSONPath.read(res, "$.list[0].id");
 		StudyPlanCancel_id = (String) JSONPath.read(resCancel, "$.list[0].id");
 		System.out.println("3.学习任务列表接口:"+"我的学习任务StudyPlan_id="+StudyPlan_id+","+"StudyPlanCancel_id="+StudyPlanCancel_id);
 		Assert.assertNotEquals(StudyPlan_id, null,"3.学习任务列表接口" + res);
 	}
 	
 	@Test(description="4.学员端-我的学习任务列表接口", priority=4)
 	public void testMyTaskGetList()  {
 		String res = StudyPlanPublishBusiness.MyTaskGetList("2",studyPlan_title);		
 		MyStudyPlan_id = (String) JSONPath.read(res, "$.list[0].id");		
 		System.out.println("4.学员端-我的学习任务列表接口:"+"我的学习任务MyStudyPlan_id="+MyStudyPlan_id);
 		Assert.assertNotEquals(MyStudyPlan_id,null,"4.学员端-我的学习任务列表接口"+res);
 	}
 	
 	
 	@Test(description="5.取消发布学习任务接口", priority=5)
 	public void testStudyPlanCancel()  {
 		String res = StudyPlanPublishBusiness.StudyPlanCancel(StudyPlan_id);		
 		String msg = (String) JSONPath.read(res, "$.msg");		
 		System.out.println("5.取消发布学习任务接口:"+"msg="+msg);
 		Assert.assertEquals(msg,"","5.取消发布学习任务接口"+res);
 	}
 	
 	@Test(description="6.取消发布学习任务-学习任务已取消发布接口", priority=6)
 	public void testStudyPlanCanceled()  {
 		String res = StudyPlanPublishBusiness.StudyPlanCancel(StudyPlan_id);		
 		String message = (String) JSONPath.read(res, "$.message");		
 		System.out.println("6.取消发布学习任务-学习任务已取消发布接口:"+"message="+message);
 		Assert.assertEquals(message,"plan already unpublished","6.取消发布学习任务-学习任务已取消发布接口"+res);
 	}
 	
 	
 	@Test(description="7.学习任务删除接口", priority=7)
 	public void testStudyPlanDeleted()  {
 		String res = StudyPlanPublishBusiness.StudyPlanDelete(StudyPlanCancel_id);		
 		String msg = (String) JSONPath.read(res, "$.msg");		
 		System.out.println("7.学习任务删除接口:"+"msg="+msg);
 		Assert.assertEquals(msg,"删除学习计划成功","7.学习任务删除接口"+res);
 	}
 	
 	@Test(description="8.取消发布学习任务-任务已删除接口", priority=8)
 	//原接口未校验取消发布时学习任务是否已被删除
 	public void testStudyPlanCancelDelete()  {
 		String res = StudyPlanPublishBusiness.StudyPlanCancel(StudyPlanCancel_id);		
 		String msg = (String) JSONPath.read(res, "$.msg");		
 		System.out.println("8.取消发布学习任务-任务已删除接口:"+"msg="+msg);
 		Assert.assertEquals(msg,"","8.取消发布学习任务-任务已删除接口"+res);
 	}
 	
 	
 	@Test(description="9.校验学习任务取消发布后任务的状态接口", priority=9)
 	public void testStudyPlanGetListCancel()  {
 		String res = StudyPlanPublishBusiness.StudyPlanGetList(studyPlan_title);
 		String planStatus = (String) JSONPath.read(res, "$.list[0].planStatus");
 		System.out.println("9.校验学习任务取消发布后任务的状态接口:"+"planStatus="+planStatus);
 		Assert.assertEquals(planStatus,"unpublished","9.校验学习任务取消发布后任务的状态接口" + res);
 	}
 	
 	@Test(description="10.校验学习任务取消发布后，PC/移动端-我的学习任务列表不显示该任务数据接口", priority=10)
 	public void testMyTaskGetListCancel()  {
 		String res = StudyPlanPublishBusiness.MyTaskGetList("2",studyPlan_title);		
 		Integer total = (Integer) JSONPath.read(res, "$.total");		
 		System.out.println("10.校验学习任务取消发布后，PC/移动端-我的学习任务列表不显示该任务数据接口:"+"MyStudyPlan_id="+MyStudyPlan_id);
 		Assert.assertTrue(total<1,"10.校验学习任务取消发布后，PC/移动端-我的学习任务列表不显示该任务数据接口"+res);
 	}
 	
 	
 	@Test(description="11.学习任务详情页接口", priority=11)
 	public void testStudyPlanDetail()  {
 		String res = StudyPlanPublishBusiness.StudyPlanDetail(StudyPlan_id);
 		stage_id = (String) JSONPath.read(res, "$.stageList[0].id");
 		course_id = (String) JSONPath.read(res, "$.stageList[0].courseMappingList[0].courseId");
 		mapping_id = (String) JSONPath.read(res, "$.stageList[0].courseMappingList[0].mappingId");
 		System.out.println("11.学习任务详情页接口:"+"stage_id="+stage_id+","+"course_id="+course_id+","+"mapping_id="+mapping_id);
 		Assert.assertNotEquals(stage_id,null,"11.学习任务详情页接口"+res);
 	}
 	
 	@Test(description="12.学习任务重新发布接口", priority=12)
 	public void testStudyPlanPublish()  {
 		String res = StudyPlanPublishBusiness.StudyPlanPublish(studyPlan_title,stage_id,course_id,mapping_id,StudyPlan_id);		
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("12.学习任务重新发布接口:"+"msg="+msg);
 		Assert.assertEquals(msg,"修改计划成功！","12.学习任务重新发布接口"+res);
 	}
 	
 	@Test(description="13.学习任务重新发布-已发布过接口", priority=13)
 	public void testStudyPlanPublished()  {
 		String res = StudyPlanPublishBusiness.StudyPlanPublish(studyPlan_title,stage_id,course_id,mapping_id,StudyPlan_id);		
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("13.学习任务重新发布-已发布过接口:"+"msg="+msg);
 		Assert.assertEquals(msg,"修改计划成功！","13.学习任务重新发布-已发布过接口"+res);
 	}
 	
 	
 	@Test(description="14.校验学习任务重新发布后任务的状态接口", priority=14)
 	public void testStudyPlanGetListPublish()  {
 		String res = StudyPlanPublishBusiness.StudyPlanGetList(studyPlan_title);
 		String planStatus = (String) JSONPath.read(res, "$.list[0].planStatus");
 		System.out.println("14.校验学习任务重新发布后任务的状态接口:"+"planStatus="+planStatus);
 		Assert.assertEquals(planStatus,"published","14.校验学习任务重新发布后任务的状态接口" + res);
 	}
 	
 	@Test(description="15.校验学习任务重新发布后，PC/移动端-我的学习任务列表重新显示该任务数据接口", priority=15)
 	public void testMyTaskGetListPublish()  {
 		String res = StudyPlanPublishBusiness.MyTaskGetList("2",studyPlan_title);		
 		Integer total = (Integer) JSONPath.read(res, "$.total");		
 		System.out.println("15.校验学习任务重新发布后，PC/移动端-我的学习任务列表重新显示该任务数据接口:"+"MyStudyPlan_id="+MyStudyPlan_id);
 		Assert.assertFalse(total<1,"15.校验学习任务重新发布后，PC/移动端-我的学习任务列表重新显示该任务数据接口"+res);
 	}
 	
 	
 	@Test(description="16.学习任务删除接口", priority=16)
 	public void testStudyPlanDelete()  {
 		String res = StudyPlanPublishBusiness.StudyPlanDelete(StudyPlan_id);		
 		String msg = (String) JSONPath.read(res, "$.msg");		
 		System.out.println("16.学习任务删除接口:"+"msg="+msg);
 		Assert.assertEquals(msg,"删除学习计划成功","16.学习任务删除接口"+res);
 	}


 	

}
