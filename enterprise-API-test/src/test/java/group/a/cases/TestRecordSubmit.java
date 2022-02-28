package group.a.cases;

/**
 * @author xieteng 
  * 申请提报
 */

import cn.kxy.group.a.business.SubmitRecordBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRecordSubmit {

	String recordName = "recordName" + CommonData.getStringRandom(3);
	String recordAddress = "recordAddress" + CommonData.getStringRandom(3);
	String recordContent = "recordContent" + CommonData.getStringRandom(3);
	String record_id = "";
	String instance_id = "";

	@Test(description = "查看个人中心-提报记录列表", priority = 1)
	public void testRecordList() {

		String res = SubmitRecordBusiness.recordList("", "");
		Integer code = (Integer) JSONPath.read(res, "$.code");
		Assert.assertTrue(code == 0, "查看个人中西-提报记录列表实际返回：" + res);
	}

	@Test(description = "关闭提报审核开关", priority = 2)
	public void testClosedRecordPre() {

		String res = SubmitRecordBusiness.ClosedRecordPre("CLOSED");
		String setting = (String) JSONPath.read(res, "$.setting");
		Assert.assertEquals(setting, "true", "关闭提报审核开关实际返回：" + res);
	}

	@Test(description = "无审核人创建提报", priority = 3)
	public void testCreatRecordPre() {

		String res = SubmitRecordBusiness.creatRecord(recordName, recordAddress, recordContent, "submitted");
		record_id = (String) JSONPath.read(res, "$.data.id");
		String record_status = (String) JSONPath.read(res, "$.data.status");
		Assert.assertEquals(record_status, "pass", "无审核人创建提报实际返回：" + res);
	}

	@Test(description = "删除提报", priority = 4)
	public void testDeleteRecordPre() {

		String res = SubmitRecordBusiness.deleteRecord(record_id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功", "删除成功实际返回：" + res);
	}

	@Test(description = "开启提报审核开关", priority = 5)
	public void testOpenRecordPre() {

		String res = SubmitRecordBusiness.ClosedRecordPre("OPEN");
		String setting = (String) JSONPath.read(res, "$.setting");
		Assert.assertEquals(setting, "true", "开启提报审核开关实际返回：" + res);
	}

	@Test(description = "有审核人创建提报保存草稿", priority = 6)
	public void testCreatRecordSave() {

		String res = SubmitRecordBusiness.creatRecord(recordName, recordAddress, recordContent, "draft");
		record_id = (String) JSONPath.read(res, "$.data.id");
		String record_status = (String) JSONPath.read(res, "$.data.status");
		Assert.assertEquals(record_status, "draft", "有审核人创建提报保存草稿实际返回：" + res);
	}

	@Test(description = "删除草稿状态提报", priority = 7)
	public void testDeleteRecordSave() {

		String res = SubmitRecordBusiness.deleteRecord(record_id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功", "删除草稿状态提报实际返回：" + res);
	}

	@Test(description = "有审核人创建提报", priority = 8)
	public void testCreatRecordReview() {

		String res = SubmitRecordBusiness.creatRecord(recordName, recordAddress, recordContent, "submitted");
		record_id = (String) JSONPath.read(res, "$.data.id");
		instance_id = (String) JSONPath.read(res, "$.data.instance_id");
		String record_status = (String) JSONPath.read(res, "$.data.status");
		Assert.assertEquals(record_status, "wait", "有审核人创建提报实际返回：" + res);
	}

	@Test(description = "审批员驳回提报", priority = 9)
	public void testRejectRecordSave() {
		String res = SubmitRecordBusiness.recordReview(record_id, instance_id, "", 2);
		SubmitRecordBusiness.deleteRecord(record_id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "审核成功", "审批员驳回提报实际返回：" + res);
	}

	@Test(description = "审批员通过提报", priority = 10)
	public void testAdoptRecordSave() {
		String creat_res = SubmitRecordBusiness.creatRecord(recordName, recordAddress, recordContent, "submitted");
		record_id = (String) JSONPath.read(creat_res, "$.data.id");
		instance_id = (String) JSONPath.read(creat_res, "$.data.instance_id");
		String res = SubmitRecordBusiness.recordReview(record_id, instance_id, "2", 3);
		SubmitRecordBusiness.deleteRecord(record_id);
		String msg = (String) JSONPath.read(res, "$.msg");
		System.out.println(res);
		Assert.assertEquals(msg, "审核成功", "审批员通过提报实际返回：" + res);
		
	}
	@Test(description = "查看提报管理全部列表", priority = 11)
	public void testManagementRecordSave() {
		String res = SubmitRecordBusiness.recordManagementList("", "", "", "");
		System.out.println(res);
		int code = (Integer) JSONPath.read(res, "$.code");
		Assert.assertTrue(code == 0 , "查看提报管理全部列表实际返回：" + res);
	}
	@Test(description = "查看提报管理通过列表", priority = 12)
	public void testManagementRecordSavePass() {
		String res = SubmitRecordBusiness.recordManagementList("pass", "", "", "");
		System.out.println(res);
		int code = (Integer) JSONPath.read(res, "$.code");
		Assert.assertTrue(code == 0 , "查看提报管理全部列表实际返回：" + res);
	}
	@Test(description = "查看提报管理通过列表", priority = 13)
	public void testManagementRecordSaveDiss() {
		String res = SubmitRecordBusiness.recordManagementList("reject", "", "", "");
		System.out.println(res);
		int code = (Integer) JSONPath.read(res, "$.code");
		Assert.assertTrue(code == 0 , "查看提报管理全部列表实际返回：" + res);
	}
	@Test(description = "查看提报管理审核中列表", priority = 14)
	public void testManagementRecordSavePending() {
		String res = SubmitRecordBusiness.recordManagementList("wait", "", "", "");
		System.out.println(res);
		int code = (Integer) JSONPath.read(res, "$.code");
		Assert.assertTrue(code == 0 , "查看提报管理全部列表实际返回：" + res);
	}
	
	@Test(description = "查看个人中心-提报记录列表", priority = 15)
	public void testRecordListpass() {

		String res = SubmitRecordBusiness.recordList("pass", "");
		Integer code = (Integer) JSONPath.read(res, "$.code");
		Assert.assertTrue(code == 0, "查看个人中西-提报记录列表实际返回：" + res);
	}
	@Test(description = "查看个人中心-提报记录列表驳回", priority = 16)
	public void testRecordListreject() {

		String res = SubmitRecordBusiness.recordList("reject", "");
		Integer code = (Integer) JSONPath.read(res, "$.code");
		Assert.assertTrue(code == 0, "查看个人中西-提报记录列表实际返回：" + res);
	}
	@Test(description = "查看个人中心-提报记录列表待审核", priority = 17)
	public void testRecordListwait() {
		String res = SubmitRecordBusiness.recordList("wait", "");
		Integer code = (Integer) JSONPath.read(res, "$.code");
		Assert.assertTrue(code == 0, "查看个人中西-提报记录列表实际返回：" + res);
	}
	@Test(description = "查看个人中心-提报记录列表草稿", priority = 18)
	public void testRecordListdraft() {
		String res = SubmitRecordBusiness.recordList("draft", "");
		Integer code = (Integer) JSONPath.read(res, "$.code");
		Assert.assertTrue(code == 0, "查看个人中西-提报记录列表实际返回：" + res);
	}
	
	@Test(description = "查看个人中心-提报记录列表关键字", priority = 19)
	public void testRecordListKeyword() {
		String res = SubmitRecordBusiness.recordList("", "r532f");
		Integer code = (Integer) JSONPath.read(res, "$.code");
		Assert.assertTrue(code == 0, "查看个人中西-提报记录列表实际返回：" + res);
	}
	
	@Test(description = "查看提报管理审核中列表", priority = 20)
	public void testManagementRecordSaveKeyword() {
		String res = SubmitRecordBusiness.recordManagementList("", "", "", "");
		System.out.println(res);
		int code = (Integer) JSONPath.read(res, "$.code");
		Assert.assertTrue(code == 0 , "查看提报管理全部列表实际返回：" + res);
	}
	
	@Test(description = "查看提报管理审核中列表根据时间 ", priority = 21)
	public void testManagementRecordSaveKeywordtime() {
		String res = SubmitRecordBusiness.recordManagementList("", "", DateUtil.getTimeStamp(-7, ""), DateUtil.getTimeStamp(0, ""));
		System.out.println(res);
		int code = (Integer) JSONPath.read(res, "$.code");
		Assert.assertTrue(code == 0 , "查看提报管理全部列表实际返回：" + res);
	}
}
