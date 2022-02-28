package study.cases;

import cn.kxy.my.business.MyOperationsBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.AppStudyBusiness;
import cn.kxy.study.business.StudyTaskBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestStudyOperationSaveDraft {

	String title = "StudyOpertion" + CommonData.getStringRandom(5);
	String pc_title = "MyOperation" + CommonData.getStringRandom(5);
	String study_id = "";
	@Test(description = "新增带有实操的学习任务",priority = 1)
	public void testAddStudyOperation() {
		String res = MyOperationsBusiness.addStudyOperation(title, DateUtil.getTimeStamp(0, ""),
				DateUtil.getTimeStamp(5, ""), UserBusiness.getUserId(), pc_title);
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！","添加线上学习任务，实际返回结果:"+res);
	}
	@Test(description = "获取学习的id",priority = 2)
	public void testGetId() {
		String res = StudyTaskBusiness.getStudyTaskList(title, "false", "0", "","");
		study_id = (String)JSONPath.read(res, "$.list[0].id");
	}
	
	String oper_id = "";
	@Test(description = "查看移动端带有实操的学习任务详情,获取实操id" , priority = 3)
	public void testQueryOperationStudyInfo() {
		String res = AppStudyBusiness.queryInfo(study_id);
		oper_id = (String)JSONPath.read(res, "$.stages[0].course_list[0].id");
	}
	
	@Test(description = "查看实操详情",priority = 4)
	public void testQueryOperationInfo() {
		String res = MyOperationsBusiness.queryOperationInfo(oper_id);
		String pc_name = (String)JSONPath.read(res, "$.data.title");
		Assert.assertNotSame(pc_title, pc_name,"查看实操详情,title进行验证："+res);
	}
	String suggest = "this is a suggestion";
	@Test(description = "保存实操到草稿箱",priority =5)
	public void saveOperationsDraft() {
		String res = MyOperationsBusiness.saveOperationsDraft(oper_id, suggest);
		Assert.assertTrue(res.contains("success"),"保存实操到草稿箱"+res);
	}
	
	@Test(description = "查询保存到草稿箱的详情",priority =6)
	public void queryOperationsDraft() {
		String res = MyOperationsBusiness.queryOperationsDraft(oper_id);
		Assert.assertTrue(res.contains("success"),"查询保存到草稿箱的详情"+res);
	}
	
	@Test(description = "保存实操",priority = 7)
	public void testSaveOperation() {
		String res = MyOperationsBusiness.saveOperation(oper_id, "0", "study");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交成功","保存实操,实际返回结果："+res);
	}
	@Test(description = "保存实操后，查看实操详情",priority = 8)
	public void testQueryOperationInfoBySave() {
		String res = MyOperationsBusiness.queryOperationInfo(oper_id);
		String operation_source = (String)JSONPath.read(res, "$.data.operation_source");
		String content  = (String)JSONPath.read(res, "$.data.steps[1].content");
		Assert.assertEquals(operation_source, "study","保存实操后，查看实操详情:"+res);
		Assert.assertNotNull(content,"保存实操后，查看实操详情:"+res);
	}
	
	@Test(description = "保存实操后，提交实操",priority = 9)
	public void testsubmitOperation() {
		String res = MyOperationsBusiness.saveOperation(oper_id, "1", "study");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "提交成功","提交实操,实际返回结果："+res);
	}
	
	@Test(description = "提交实操后，查看学习任务详情",priority = 10)
	public void testQueryStudyInfo() {
		String res = AppStudyBusiness.queryInfo(study_id);
		String status = (String)JSONPath.read(res, "$.stages[0].course_list[0].status");
		Assert.assertEquals(status,"1","提交实操后，查看学习任务详情,显示待审核标签"+res);
	}
	
	@Test(description = "提交实操后，查看实操详情",priority =11)
	public void testSavedQueryOperationInfo() {
		String res = MyOperationsBusiness.queryOperationInfo(oper_id);
		Long time = (Long)JSONPath.read(res, "$.data.submit_time");
		Assert.assertNotNull(time,"提交实操后，查看实操详情，提交时间验证"+res);
	}
	
	@Test(description="删除学习计划任务",priority= 15)
	public void testDeleteStudyTask() {
		String res = StudyTaskBusiness.deleteStudyTask(study_id);
		String msg= (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除学习计划成功","删除学习计划任务，实际返回结果:"+res);
	}
}
