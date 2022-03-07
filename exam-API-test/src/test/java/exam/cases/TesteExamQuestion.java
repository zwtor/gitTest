package exam.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExaminationBusines;
import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import cn.kxy.setting.bussiness.UserBusiness;
import init.cases.InitExam;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TesteExamQuestion extends InitExam {

	public String editExamName = "exam" + CommonData.getStringRandom(5);
	String exam_name1 = "exam01"+CommonData.getStringRandom(5);
	String exam_name2 = "exam02"+CommonData.getStringRandom(5);
	String id01 = "";
	String id02 = "";
	String class_id = ClassificationBusines.getPrimaryId();
	@Test(description = "新建不同类型可见范围题库", dataProvider = "CreateQuestionBank", priority = 1)
	public void testCreateQuestionBank(String type, String name, String isAll, String departmentIds, String userIds,
			String exp) {
		String res = ExaminationBusines.createQuestionBank(name, isAll, departmentIds, userIds);
		String msg = (String) JSONPath.read(res, "$.msg");

		Reporter.log("新建可见范围为" + type + "的题库");
		Assert.assertEquals(msg, exp, "新建可见范围为" + type + "题库实际返回结果：" + res);
	}

	@Test(description = "新建题库参数校验", dataProvider = "CheckQuestionBank")
	public void testCheckCreateQuestionBank(String type, String classifition, String name, String isAll,
			String departmentIds, String userIds, String exp) {
		String res = ExaminationBusines.createCheckQuestionBank(classifition, name, isAll, departmentIds, userIds);
		String msg = (String) JSONPath.read(res, "$.msg");

		Assert.assertEquals(msg, exp, "新建题库参数校验实际返回结果：" + res);
	}

	@Test(description = "查询单个题库信息", priority = 2)
	public void testQueryQuesyionBankInfo() {
		String list_res = ExaminationBusines.queryQuestionBankList(exam_name1, "false",
				class_id);
		id01 = (String)JSONPath.read(list_res, "$.data.list[0].id");
		String listres = ExaminationBusines.queryQuestionBankList(exam_name2, "false",
				class_id);
		id02 = (String)JSONPath.read(listres, "$.data.list[0].id");
		String res = ExaminationBusines.queryQuesyionBankInfo(id01);
		String msg = (String) JSONPath.read(res, "$.msg");
		String name = (String) JSONPath.read(res, "$.data.name");
		Assert.assertEquals(msg, "查询成功", "查询单个题库实际返回结果：" + res);
		Assert.assertEquals(name, exam_name1, "查询单个题库实际返回结果：" + res);

	}
	
	@Test(description = "删除题库时，对Id进行校验", priority = 3)
	public void testDeleteIdQuestionBank() {
		String res = ExaminationBusines.deleteQuestionBank("");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "题库不存在", "删除题库时，对Id进行校验实际返回结果：" + res);
	}


	@Test(description = "编辑题库信息", priority = 4)
	public void testEditQuestionBank() {
		String res = ExaminationBusines.editQuestionBank(editExamName, "1", "", "", id01);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "编辑成功", "编辑题库实际返回结果：" + res);
	}
	
	@Test(description = "编辑题库信息时，对Id进行校验",  priority = 5)
	public void testCheckIdEditQuestionBank() {

		String res = ExaminationBusines.editQuestionBank(editExamName, "1", "", "", "");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "题库ID为空", "编辑题库信息时，对Id进行校验实际返回结果：" + res);
	}
	
	@Test(description = "编辑题库信息时，对名称进行校验",  priority = 6)
	public void testCheckNameEditQuestionBank() {

		String res = ExaminationBusines.editQuestionBank("", "1", "", "", id01);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "题库名称不为为空", "编辑题库信息时，对名称进行校验实际返回结果：" + res);
	}
	
	@Test(description = "编辑题库信息时，对可见范围进行校验",  priority = 7)
	public void testCheckNameEditVisibilityRange() {

		String res = ExaminationBusines.editQuestionBank("test", "", "", "", id01);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "题库可见范围不能为空", "编辑题库信息时，对可见范围进行校验实际返回结果：" + res);
	}

	
	@Test(description = "查询题库列表", priority =8)
	public void testQueryQuestionBankList() {
		String res = ExaminationBusines.queryQuestionBankList(editExamName, "true",
				ClassificationBusines.getPrimaryId());
		String msg = (String) JSONPath.read(res, "$.msg");
		String name = (String) JSONPath.read(res, "$.data.list[0].name");
		Assert.assertEquals(msg, "查询成功", "查询题库列表实际返回结果：" + res);
		Assert.assertEquals(name, editExamName, "查询题库列表实际返回结果：" + res);
	}
	
	@Test(description = "设置单个题库可见范围", priority = 9)
	public void testSetVisibilityRange() {
		String res = ExaminationBusines.setVisibilityRange(id01, "3");
		Assert.assertTrue(res.contains("true") ,"设置可见范围实际返回结果：" + res);
	}
	
	@Test(description = "设置可见范围时，对题库Id进行校验", priority = 10)
	public void testCheckIdSetVisibilityRange() {
		String res = ExaminationBusines.setVisibilityRange("", "3");
		String msg = (String)JSONPath.read(res, "$.message");
		Assert.assertEquals(msg, "invalid param, please check it", "设置可见范围时，对题库Id进行校验实际返回结果：" + res);
	}
	
	@Test(description = "批量设置题库可见范围", priority = 11)
	public void testBatchSetVisibilityRange() {
		String res00 = ExaminationBusines.queryQuestionBankList("", "", "");
		String id01 = (String )JSONPath.read(res00, "$.data.list[0].id");
		String id02 = (String) JSONPath.read(res00, "$.data.list[1].id");
		String res01 = ExaminationBusines.setVisibilityRange(id01+","+id02, "3");
		Assert.assertTrue(res01.contains("true"),"批量设置题库可见范围"+res01);
	}
	
	@SuppressWarnings("null")
	@Test(description = "题库中存在试题时，不允许删除", priority = 12)
	public void testDeleteUsedQuestionBank() {
		String res = ExaminationBusines.queryQuestionBankList(BaseBusiness.examName, "", ClassificationBusines.getPrimaryId());
		String id = (String)JSONPath.read(res, "$.data.list[0].id");
		if (id!=null||!id.isEmpty()) {
			String res01 = ExaminationBusines.deleteQuestionBank(id);
			String msg = (String)JSONPath.read(res01, "$.msg");
			Assert.assertEquals("题库下有试题，请先移除试题再删除题库", msg,"题库中存在试题时删除时，实际返回结果："+res01);
		}
		
	}
	@Test(description = "删除题库", priority = 13)
	public void testDeleteQuestionBank() {
		String res = ExaminationBusines.deleteQuestionBank(id01);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功", "删除题库实际返回结果：" + res);
	}
	@Test(dataProvider = "QueryDifftypeList",description = "根据不同参数查询题库列表", priority = 14)
	public void testQueryDifftypeList(String type,String name,String onlySeeMe,String classifition) {
		String res = ExaminationBusines.queryQuestionBankList(name, onlySeeMe,
				classifition);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "查询成功", "查询题库列表实际返回结果：" + res);
	}
	@Test(description = "导出题库",priority = 15)
	public void testExportQuestion() {
		String del_res = PaperExportBusiness.deleteAllRecord();
		String res = ExaminationBusines.exportQuestion(id02);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertTrue(del_res.contains("OK"));
		Assert.assertEquals(msg, "题库信息导出成功","导出题库"+res);
	}
	@Test(description = "导出后在下载中心查看是否导出成功",priority = 16)
	public void exportRecordList() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String res = PaperExportBusiness.exportRecordList();
		String status  = (String)JSONPath.read(res, "$.list[0].status");
		Assert.assertFalse(status=="FAILED", "查看导出的结果:"+res);
	}
	@Test(description = "删除题库",priority=17)
	public void endTest() {
		String res = ExaminationBusines.deleteQuestionBank(id02);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功", "删除题库实际返回结果：" + res);
	}
	
	@DataProvider(name = "QueryDifftypeList")
	public Object[][] queryDifftypeList() {
		Object[][] obj = new Object[][] { { "name为空时", "", "false", ClassificationBusines.getPrimaryId() },{ "分类和name为空时", "", "false", "" },
			{ "只看我创建为空时", "", "true", "" },{ "所有参数都为空时", "", "", "" },
			};
		return obj;
	}

	@DataProvider(name = "CreateQuestionBank")
	public Object[][] createQuestionBank() {
		Object[][] obj = new Object[][] { { "新建全员题库", exam_name1, "1", "", "", "新增成功" },
				{ "新建仅自己可见题库", exam_name2, "3", "", "", "新增成功" } };
		return obj;
	}

	@DataProvider(name = "CheckQuestionBank")
	public Object[][] CheckQuestionBank() {
		Object[][] obj = new Object[][] {
				{ "对名称进行非空校验", ClassificationBusines.getPrimaryId(), "", "1", "", "", "题库名称不为为空" },
				{ "对可见范围进行非空校验", ClassificationBusines.getPrimaryId(), "exam01", "", "", UserBusiness.getUserId(),
						"题库可见范围不能为空" },
				{"对同名进行校验",ClassificationBusines.getPrimaryId(),BaseBusiness.examName, "1" ,"","","已存在同名题库，请修改题库名称"},
				{ "对分类进行非空校验", "", "exam02", "3", "", "", "题库所属分类不能为空" } };
		return obj;
	}
}