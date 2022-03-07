package exam.cases;

import cn.kxy.examination.business.AppPracticeBusiness;
import cn.kxy.examination.business.ExaminationBusines;
import cn.kxy.examination.business.ItemBankExerciseBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import init.cases.InitExam;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestItemBankExercise  extends InitExam {
	
	String EditName = "EditExercise"+CommonData.getStringRandom(5);
	
	String NewName = "NewExerc"+CommonData.getStringRandom(5);

	String user_id = UserBusiness.getUserId();
	String user_name = UserBusiness.getUsername();
	String id = "";
	
	@Test(description = "新增题库练习",priority=1)
	public void testAddPractice() {
		String res = ItemBankExerciseBusiness.addPractice(NewName, ExaminationBusines.getIdByKeyword(), "part", "");
		String status = (String) JSONPath.read(res, "$.status");
		String queryPracticeList = ItemBankExerciseBusiness.queryPracticeList(NewName, "all");
		id = (String)JSONPath.read(queryPracticeList, "$.list[0].id");
		Assert.assertEquals("true",status,"新增题库练习实际返回结果："+res);
	}
	
	

	@Test(description = "新增题库练习时，对不同参数进行校验",dataProvider="CheckAddPractice",priority=3)
	public void testCheckAddPractice(String type,String title, String question_bank, String part, String user_ids,
			String post_ids,String exp) {
		String res = ItemBankExerciseBusiness.addPractice(title, question_bank, part, user_ids, post_ids);
		String msg = (String) JSONPath.read(res, "$.message");
		Assert.assertEquals(msg,exp,"新增题库练习时，对不同参数进行校验实际返回结果："+res);
	}
	
	@Test(description = "查看全部题库练习",priority=4)
	public void testQueryAllPracticeList() {
		String res = ItemBankExerciseBusiness.queryPracticeList(NewName, "all");
		String title = (String)JSONPath.read(res, "$.list[0].title");
		int question_count = (int)JSONPath.read(res, "$.list[0].question_count");
		int participate_user_count = (int)JSONPath.read(res, "$.list[0].participate_user_count");
		String create_user_name = (String)JSONPath.read(res, "$.list[0].create_user_name");
		
		Assert.assertEquals(create_user_name, user_name,"查看全部题库练习,名称进行校验"+res);
		Assert.assertEquals(title, NewName,"查看全部题库练习,标题进行校验"+res);
		Assert.assertNotNull(question_count,"查看全部题库练习,题目数进行校验"+res);
		Assert.assertNotNull(participate_user_count, "查看全部题库练习,参与人数进行校验"+res);
	}
	
	@Test(description = "停用题库",priority=5)
	public void testStopStatus() {
		String res = ItemBankExerciseBusiness.updateStatus("not_enable",id);
		String status = (String) JSONPath.read(res, "$.status");
		Assert.assertEquals(status,"true","停用题库实际返回结果："+res);
	}
	
	@Test(description = "查看已停用题库练习",priority=6)
	public void testQueryStopPracticeList() {
		String res = ItemBankExerciseBusiness.queryPracticeList(NewName, "not_enable");
		String status = (String)JSONPath.read(res, "$.list[0].status");
		Assert.assertEquals(status,"not_enable","查看已启用题库练习实际返回："+res);
	}
	
	@Test(description="停用题库后，app端不显示停用的题库练习",priority=7)
	public void testNotOpenDisplay() {
		String res = AppPracticeBusiness.queryPracticeList(NewName);
		int total =(int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total==0,"当题库停用时，app端不显示停用的练习实际返回结果"+res);
	}
	
	@Test(description = "停用题库后，再次执行停用接口",priority=8)
	public void testCloseAgainStatus() {
		String res = ItemBankExerciseBusiness.updateStatus("not_enable",id);
		String message = (String) JSONPath.read(res, "$.message");
		Assert.assertEquals(message,"practice has not enable","停用题库后，再次执行停用接口实际返回结果："+res);
	}
	
	@Test(description = "启用题库",priority=9)
	public void testOpenStatus() {
		String res = ItemBankExerciseBusiness.updateStatus("enable",id);
		String status = (String) JSONPath.read(res, "$.status");
		Assert.assertEquals(status,"true","启用题库实际返回结果："+res);
	}
	
	@Test(description = "查看已启用题库练习",priority=10)
	public void testQueryOpenPracticeList() {
		String res = ItemBankExerciseBusiness.queryPracticeList(NewName, "enable");
		String status = (String)JSONPath.read(res, "$.list[0].status");
		Assert.assertEquals(status,"enable","查看已启用题库练习实际返回："+res);
	}
	
	
	@Test(description = "启用题库后，再次执行启用接口",priority=11)
	public void testOpenAgainStatus() {
		String res = ItemBankExerciseBusiness.updateStatus("enable",id);
		String message = (String) JSONPath.read(res, "$.message");
		Assert.assertEquals(message,"practice has enable","启用题库后，再次执行启用接口实际返回结果："+res);
	}
	
	
	@Test(description = "编辑题库练习时，对试题id参数进行校验",priority=12)
	public void testCheckpaperEditPractice() {
		String res =  ItemBankExerciseBusiness.editPractice(EditName,  "", "part", user_id, "", ItemBankExerciseBusiness.getFirstId());
		String msg = (String) JSONPath.read(res, "$.message");
		Assert.assertEquals(msg,"Practice question bank questions can not be empty","编辑题库练习时，对题库参数进行校验实际返回结果："+res);
	}
	
	@Test(description = "编辑题库练习时，对可见范围进行校验",priority=13)
	public void testCheckUserEditPractice() {
		String res =  ItemBankExerciseBusiness.editPractice(EditName,  ExaminationBusines.getFirstId(), "", "", "", ItemBankExerciseBusiness.getFirstId());
		String msg = (String) JSONPath.read(res, "$.message");
		Assert.assertEquals(msg,"practice range can not be empty","编辑题库练习时，对可见范围进行校验实际返回结果："+res);
	}
	@Test(description = "编辑题库练习",priority=14)
	public void testEditPractice() {
		String res =  ItemBankExerciseBusiness.editPractice(EditName,  ExaminationBusines.getIdByKeyword(), "part", user_id, "", id);
		String status = (String) JSONPath.read(res, "$.status");
		Assert.assertEquals(status,"true","编辑题库练习的发布实际返回结果："+res);
	}
	
	
	@Test(description = "查看监控数据,仅查看",priority=18)
	public void testQueryMonitors() {
		String res =ItemBankExerciseBusiness.queryMonitors("",id);
		String name = (String) JSONPath.read(res, "$.practice_name");
		Assert.assertTrue(!name.isEmpty());
	}
	
	@Test(description="删除题库练习",priority=19)
	public void testDeleteExce() {
		String res = ItemBankExerciseBusiness.deleteExce(id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功","删除题库练习："+res);
	}
	
	@DataProvider(name = "CheckAddPractice")
	public Object[][] checkAddPractice() {
		Object[][] obj = new Object[][] { { "对题库练习名称进行校验","", "", "part", UserBusiness.getUserId(), "","Practice question bank questions can not be empty" },
			{ "对题库练习指派范围参数进行校验", "name", ExaminationBusines.getFirstId(), "", "", "","practice range can not be empty" },
			{ "对题库练习的题库参数进行校验", "name", "", "part", UserBusiness.getUserId(), "","Practice question bank questions can not be empty" } };
		return obj;
	}
	
}
