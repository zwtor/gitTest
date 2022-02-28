package group.a.cases;

/**
 * @author xieteng 
  * 岗位认证添加调研
 */

import cn.kxy.group.a.business.RepeatAddQuestionBusiness;
import cn.kxy.investigationresearch.business.QuestionnaireBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRepeatAddQuestion {
	
	String ques_name = "DeleteQues" + CommonData.getStringRandom(3);
	String ques_id = "";
	String ficate_name = "Certificate" + CommonData.getStringRandom(3);
	String ficate_id = "";
	String fications_id = "";
	String template_id = "";
	String enployName = "enployName" + CommonData.getStringRandom(3);
	String plan_id = "";
	String taskName = "taskName" + CommonData.getStringRandom(3);

	@Test(description = "添加问卷调研", priority = 1)
	public void testAddNormalQuestionnaire() {
		QuestionnaireBusiness.addNormalQuestionnaire(ques_name, "this is a description", "release");
	}

	@Test(description = "获取问卷调研的id", priority = 2)
	public void testGetqueId() {
		ques_id = QuestionnaireBusiness.getIdByKeyword(ques_name, "release");
	}

	@Test(description = "添加证书", priority = 3)
	public void testAddCertificate() {
		RepeatAddQuestionBusiness.creatCertificate(ficate_name, "23456789", "证书描述", "true", "chinese");
	}

	@Test(description = "获取证书id", priority = 4)
	public void testgetCertificateId() {
		ficate_id = RepeatAddQuestionBusiness.getcertificateId(ficate_name);
	}

	@Test(description = "创建岗位认证添加问卷", priority = 5)
	public void testAddQualifications() {
		String res = RepeatAddQuestionBusiness.creatQualifications(ques_id, ficate_id);
		fications_id = (String) JSONPath.read(res, "$.data");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！", "创建岗位认证添加问卷实际返回结果：" + res);
	}

	@Test(description = "删除岗位认证", priority = 6)
	public void testdeleteQualifications() {
		String res = RepeatAddQuestionBusiness.deletequalifications(fications_id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "能力认证删除成功", "删除岗位认证实际返回结果：" + res);
	}

	@Test(description = "创建岗位认证多次添加同一个问卷", priority = 7)
	public void testAddsQualifications() {
		String res = RepeatAddQuestionBusiness.creatTwoQualification(ques_id, ficate_id);
		fications_id = (String) JSONPath.read(res, "$.data");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！", "创建岗位认证多次添加同一个问卷问卷实际返回结果：" + res);
	}

	@Test(description = "删除岗位认证多个问卷", priority = 8)
	public void testdeletesQualifications() {
		String res = RepeatAddQuestionBusiness.deletequalifications(fications_id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "能力认证删除成功", "删除岗位认证岗位认证多个问卷实际返回结果：" + res);
	}

	@Test(description = "创建定时任务添加调研", priority = 9)
	public void testcreatTimedtask() {
	template_id = RepeatAddQuestionBusiness.creatTimedtask(taskName, ques_id);
		Assert.assertTrue(template_id != null, "创建定时任务添加调研实际返回结果：" + template_id);
	}

	@Test(description = "删除定时任务", priority = 10)
	public void testdeleteTimedtask() {
		String res = RepeatAddQuestionBusiness.deleteTimedtask(template_id);
		String status = (String) JSONPath.read(res, "$.status");
		Assert.assertEquals(status, "true" , "删除定时任务实际返回结果：" + res);
	}
	
	@Test(description = "创建新员工任务添加调研", priority = 11)
	public void testcreatEmployeeTask() {
	plan_id = RepeatAddQuestionBusiness.creatNewEmployeeTask(enployName, ques_id);
		Assert.assertTrue(template_id != null, "创建新员工任务添加调研实际返回结果：" + plan_id);
	}

	@Test(description = "删除新员工任务", priority = 12)
	public void testdeleteEmployeeTask() {
		String res = RepeatAddQuestionBusiness.deleteEmployTask(plan_id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功", "删除新员工任务实际返回结果：" + res);
	}
	@Test(description = "停用证书后删除证书", priority = 13)
	public void testDeleteCertificate() {
		String res = RepeatAddQuestionBusiness.deletecertificate(ficate_id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除证书成功！", "停用证书后删除证书实际返回结果：" + res);
	}

	@Test(description = "被引用的数据全部删除后 ，再次取消、删除问卷，应可删除", priority = 14)
	public void testDeleteQuestionnaire() {
		QuestionnaireBusiness.cancelQuestionnaire(ques_name, "release");
		String res = QuestionnaireBusiness.deleteQuestionnaire(ques_name, "draft");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "问卷删除成功", "取消问卷发布，然后删除实际返回结果：" + res);
	}
}
