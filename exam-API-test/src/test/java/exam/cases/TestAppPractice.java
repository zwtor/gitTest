package exam.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.AppPracticeBusiness;
import cn.kxy.examination.business.ExaminationBusines;
import cn.kxy.examination.business.ItemBankExerciseBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.setting.bussiness.TraineeBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import init.cases.InitExam;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class TestAppPractice extends InitExam {
	String ErrorEliminationName = "ErrorEliminationPractice"+CommonData.getStringRandom(5);
	String name = "RandomPractice" + CommonData.getStringRandom(5);

	String id = "";
	String bank_id = ExaminationBusines.getIdByKeyword(BaseBusiness.examPassName);
	@BeforeClass
	public void initPractice() {
		String res = ItemBankExerciseBusiness.addPractice(name,bank_id, "part", "");
		System.out.println("初始化题库练习,实际返回结果" + res);
	}

	@Test(description = "App端通过关键词查询练习的列表页",priority = 1)
	public void testQueryList() {
		String res = AppPracticeBusiness.queryPracticeList(name);
		id =(String)JSONPath.read(res, "$.list[0].id");
		String title =(String)JSONPath.read(res, "$.list[0].title");
		Assert.assertEquals(title, name,"通过关键词查询练习的列表页实际返回结果："+res);
	}

	@Test(description = "App端不输入关键词查询练习的列表页",priority=2)
	public void testNotInputQueryList() {
		String res = AppPracticeBusiness.queryPracticeList("");
		String title =(String)JSONPath.read(res, "$.list[0].title");
		int total =(int)JSONPath.read(res, "$.total");
		Assert.assertNotNull(title,"不输入关键词查询练习的列表页实际返回结果："+res);
		Assert.assertTrue(total>=1,"不输入关键词查询练习的列表页实际返回结果："+res);
	}
	
	@Test(description = "App端输入不存在的关键词，查询练习的列表页",priority=3)
	public void testInputEmptyQueryList() {
		String res = AppPracticeBusiness.queryPracticeList("000000");
		int total =(int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total==0,"不输入关键词查询练习的列表页实际返回结果："+res);
	}
	
	@Test(description = "App端未进行练习时，查看错题消除详情，不应报错",priority=4)
	public void testUnDoWrongTip() {
		String res = AppPracticeBusiness.queryWrongInfo("fixed", "false", id);
		String title =(String)JSONPath.read(res, "$.title");
		@SuppressWarnings("rawtypes")
		List questions =(List)JSONPath.read(res, "$.questions");
		Assert.assertEquals(title, name,"未进行练习时，查看错题消除详情，不应报错,实际返回结果:"+res);
		Assert.assertTrue(questions.isEmpty(),"未进行练习时，查看错题消除详情，不应报错,实际返回结果:"+res);
		
	}
	
	@Test(description = "App端未进行练习时，输入不存在的name，查看错题消除详情，",priority=5)
	public void testUnDoWrongIsExist() {
		String res = AppPracticeBusiness.queryWrongInfo("fixed", "false", "00.0");
		String msg =(String)JSONPath.read(res, "$.message");
		Assert.assertEquals(msg, "resource not found","未进行练习时，查看错题消除详情，不应报错,实际返回结果:"+res);
		
	}
	
	@Test(description="App端选择题库练习时，查看题库列表的名称是否正确",priority=6)
	public void testQueryQuestionBank() {
		String res = AppPracticeBusiness.query_question_bank("false", id);
		String title = (String)JSONPath.read(res, "$.list[0].question_bank_name");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertEquals(title, BaseBusiness.examPassName);
		Assert.assertTrue(total>=1, "进行选择题库练习时，查看题库列表实际返回结果："+res);
	}
	
	@Test(description="App端通过随机类型查看练习详情",priority=7)
	public void testRandomQueryInfo() {
		String res = AppPracticeBusiness.queryPracticeInfo("random", "false", id,"");
		String title = (String)JSONPath.read(res, "$.title");
		Assert.assertEquals(title, name,"通过随机类型查看练习详情实际返回结果："+res);
	} 
	
	@Test(description="App端通过顺序类型查看练习详情",priority=8)
	public void testOrderQueryInfo() {
		String res = AppPracticeBusiness.queryPracticeInfo("fixed", "false", id,"");
		String title = (String)JSONPath.read(res, "$.title");
		Assert.assertEquals(title, name,"通过顺序类型查看练习详情实际返回结果："+res);
	} 
	
	@Test(description="App端通过选择题库类型查看练习详情",priority=9)
	public void testOrderQueryInfoByItemQuestion() {
		String res = AppPracticeBusiness.queryPracticeInfo("bank", "true", id,AppPracticeBusiness.getBankIdByName(id));
		String title = (String)JSONPath.read(res, "$.title");
		Assert.assertEquals(title, name,"通过选择题库类型查看练习详情实际返回结果："+res);
	} 
	
	@Test(description="App端查看练习详情，传入不存在的id时，进行校验",priority=10)
	public void testNotNameQueryInfo() {
		String res = AppPracticeBusiness.queryPracticeInfo("fixed", "false", "030","");
		String title = (String)JSONPath.read(res, "$.message");
		Assert.assertEquals(title, "resource not found","查看练习详情，传入不存在的id时，进行校验实际返回结果："+res);
	} 
	
	@Test(description="App端查看练习详情，不传类型时，进行校验",priority=11)
	public void testNotTypeQueryInfo() {
		String res = AppPracticeBusiness.queryPracticeInfo("", "false", id,"");
		String title = (String)JSONPath.read(res, "$.message");
		Assert.assertEquals(title, "invalid params of type, please check it","查看练习详情，不传类型时，进行校验实际返回结果："+res);
	} 
	
	@Test(description="App端验证进入错题消除后，是否有alert",priority=12) 
	public void testAlertValue() {
		String res = AppPracticeBusiness.queryIsAlert("practice");
		String status= (String)JSONPath.read(res, "$.status");
		Assert.assertTrue(!status.isEmpty(), "验证进入错题消除后，是否有alert实际返回结果："+res);
	}
	@Test(description="App端保存题库练习的答案",priority=13,dependsOnMethods="testAlertValue")
	public void  testSubmit() {
		String res  = AppPracticeBusiness.submitFirst(id,AppPracticeBusiness.getFirstFalseAnwserIdByQusetion(id),"false","1");
		String ststus = (String)JSONPath.read(res, "$.status");
		Assert.assertEquals(ststus, "true","保存题库练习的答案实际返回结果："+res);
	}
	
	@Test(description="App端未完成练习时，查询错题详情返回结果为空",priority=14)
	public void testWrongInfo() {
		String res = AppPracticeBusiness.queryWrongInfo("fixed", "false", id);
		String title =(String)JSONPath.read(res, "$.title");
		Assert.assertEquals(title, name,"查看错题实际返回结果"+res);
	}
	
	@Test(description="App端对练习进行数据监控,仅对数据是否存在进行校验",priority=15)
	public void testMonitors() {
		String res = ItemBankExerciseBusiness.queryMonitors("",id);
		int total= (int)JSONPath.read(res, "$.practice_monitors.total");
		Assert.assertTrue(total>=1,"对练习进行数据监控："+res);
	}
	String new_id  = "";
	
	@Test(description="App端错题消除的显示新[新]（tab标签）",priority=16)
	public void testNewIsAppear() {
		ItemBankExerciseBusiness.addPractice(ErrorEliminationName,bank_id, "part",  "");
		String res = AppPracticeBusiness.queryPracticeList(ErrorEliminationName);
		new_id =(String)JSONPath.read(res, "$.list[0].id");
		String show_new =(String)JSONPath.read(res, "$.list[0].show_new");
		Assert.assertEquals(show_new, "true","通过关键词查询练习的列表页实际返回结果："+res);
	}
	@Test(description="App端查看错题消除的错误个数",priority=17,dependsOnMethods="testNewIsAppear")
	public void testWrongCount() {
		
		AppPracticeBusiness.submitFirst(new_id,AppPracticeBusiness.getFirstFalseAnwserIdByQusetion(new_id),"false","0");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		AppPracticeBusiness.submitSecond(new_id,AppPracticeBusiness.getSecondRightAnwserIdByQusetion(new_id),"true","1");
		String res = AppPracticeBusiness.queryPracticeList(ErrorEliminationName);
		int total =(int)JSONPath.read(res, "$.list[0].wrong_question_count");
		Assert.assertEquals(total, 1,"错题消除的错误个数实际返回结果："+res);
	}
	
	
	@Test(description="存在错题时，web端校验错题消除监控数据是否正确",priority=18,dependsOnMethods="testNewIsAppear")
	public void testClearWrongmonitor() {
		String res = ItemBankExerciseBusiness.queryMonitors(UserBusiness.getUsername(),new_id);
		String name = (String)JSONPath.read(res, "$.practice_monitors.list[0].name");
		int complete_question_count = (int)JSONPath.read(res, "$.practice_monitors.list[0].complete_question_count");
		int correct_question_count = (int)JSONPath.read(res, "$.practice_monitors.list[0].correct_question_count");
		int wrong_question_count = (int)JSONPath.read(res, "$.practice_monitors.list[0].wrong_question_count");
		String correct_rate = (String)JSONPath.read(res, "$.practice_monitors.list[0].correct_rate");
		Long create_time = (Long)JSONPath.read(res, "$.practice_monitors.list[0].create_time");
		
		Assert.assertTrue(create_time!=null, "存在错题时，校验错题消除监控数据是否正确实际返回结果："+res);
		Assert.assertEquals(correct_rate, "0.50","存在错题时，校验错题消除监控数据是否正确实际返回结果："+res);
		Assert.assertEquals(wrong_question_count, 1,"存在错题时，校验错题消除监控数据是否正确实际返回结果："+res);
		Assert.assertEquals(correct_question_count, 1,"存在错题时，校验错题消除监控数据是否正确实际返回结果："+res);
		Assert.assertEquals(complete_question_count, 2,"存在错题时，校验错题消除监控数据是否正确实际返回结果："+res);
		Assert.assertEquals(name, UserBusiness.getUsername(),"存在错题时，校验错题消除监控数据是否正确实际返回结果："+res);
	}
	@Test(description="web端题库练习列表页人数校验",priority=19,dependsOnMethods="testClearWrongmonitor")
	public void testPracticePeople() {
		String res = ItemBankExerciseBusiness.queryPracticeList(ErrorEliminationName, "all");
		int total = (int)JSONPath.read(res, "$.list[0].participate_user_count");
		Assert.assertEquals(total, 1,"题库练习列表页人数校验实际返回结果："+res);
	}
	
	@Test(description="存在错题时，App端查看错题消除详情",priority=20,dependsOnMethods="testNewIsAppear")
	public void testExistWronginfo() {
		String res = AppPracticeBusiness.queryWrongInfo("fixed", "false", new_id);
		String analysis = (String)JSONPath.read(res, "$.questions[0].analysis");
		String title = (String)JSONPath.read(res, "$.questions[0].title");
		Assert.assertTrue(!title.isEmpty(), "存在错题时，查看错题消除详情实际返回结果："+res);
		Assert.assertTrue(!analysis.isEmpty(), "存在错题时，查看错题消除详情实际返回结果："+res);
	}
	
	@Test(description="App端错题消除的不显示[新]（tab标签）",priority=21,dependsOnMethods="testNewIsAppear")
	public void testNewIsDisappear() {
		String res = AppPracticeBusiness.queryPracticeList(ErrorEliminationName);
		String show_new =(String)JSONPath.read(res, "$.list[0].show_new");
		Assert.assertEquals(show_new, "false","通过关键词查询练习的列表页实际返回结果："+res);
	}
	
	@Test(description="对错题进行消除后,App端列表页显示错题的个数",priority=22,dependsOnMethods="testNewIsAppear")
	public void testClearWrong() {
		AppPracticeBusiness.submitFirst(new_id,AppPracticeBusiness.getFirstRightAnwserIdByQusetion(new_id),"true","1");
		String res = AppPracticeBusiness.queryPracticeList(ErrorEliminationName);
		int total = (int)JSONPath.read(res, "$.list[0].wrong_question_count");
		Assert.assertEquals(total, 0,"对错题进行消除，列表页错题数实际返回结果："+res);
	}
	
	@Test(description="错题消除完后，校验web端错题消除监控数据是否正确",priority=23,dependsOnMethods="testNewIsAppear")
	public void testWrongmonitor() {
		String res = ItemBankExerciseBusiness.queryMonitors(UserBusiness.getUsername(),new_id);
		String name = (String)JSONPath.read(res, "$.practice_monitors.list[0].name");
		int complete_question_count = (int)JSONPath.read(res, "$.practice_monitors.list[0].complete_question_count");
		int correct_question_count = (int)JSONPath.read(res, "$.practice_monitors.list[0].correct_question_count");
		int wrong_question_count = (int)JSONPath.read(res, "$.practice_monitors.list[0].wrong_question_count");
		String correct_rate = (String)JSONPath.read(res, "$.practice_monitors.list[0].correct_rate");
		Long create_time = (Long)JSONPath.read(res, "$.practice_monitors.list[0].create_time");
		
		String department_name = (String)JSONPath.read(res, "$.practice_monitors.list[0].department_name");
		Assert.assertTrue(!department_name.isEmpty(), "存在错题时，校验错题消除监控数据是否正确实际返回结果："+res);
		Assert.assertTrue(create_time!=null, "存在错题时，校验错题消除监控数据是否正确实际返回结果："+res);
		Assert.assertEquals(correct_rate, "1.00","存在错题时，校验错题消除监控数据是否正确实际返回结果："+res);
		Assert.assertEquals(wrong_question_count, 0,"存在错题时，校验错题消除监控数据是否正确实际返回结果："+res);
		Assert.assertEquals(correct_question_count, 2,"存在错题时，校验错题消除监控数据是否正确实际返回结果："+res);
		Assert.assertEquals(complete_question_count, 2,"存在错题时，校验错题消除监控数据是否正确实际返回结果："+res);
		Assert.assertEquals(name, UserBusiness.getUsername(),"存在错题时，校验错题消除监控数据是否正确实际返回结果："+res);
	}
	@Test(description="web端错题消除监控数据使用岗位和部门搜索进行校验，（postId，departmentid都存在value时",priority=24,dependsOnMethods="testNewIsAppear")
	public void testmonitorByValue() {
		String traine = TraineeBusiness.queryPostsList();
		String post_id = (String)JSONPath.read(traine, "$.posts[0].id");
		String res = ItemBankExerciseBusiness.queryMonitors(UserBusiness.getUsername(),new_id,"1",post_id);
		int complete_question_count = (int)JSONPath.read(res, "$.practice_monitors.total");
		Assert.assertTrue(complete_question_count>=0,"web端错题消除监控数据使用岗位和部门搜索进行校验，（postId，departmentid都存在value时实际返回结果："+res);
	}
	
	@Test(description="APP端进行练习后，对参与人数进行校验",priority=25)
	public void testQueryPracticeList() {
		String res = ItemBankExerciseBusiness.queryPracticeList(ErrorEliminationName, "all");
		int participate_user_count = (int)JSONPath.read(res, "$.list[0].participate_user_count");
		Assert.assertEquals(participate_user_count, 1,"查看全部题库练习,参与人数进行校验"+res);
	}
	
	@Test(description="错题消除后，查看App端错题消除详情",priority=26,dependsOnMethods="testWrongmonitor")
	public void testQueryWrongInfo() {
		String res = AppPracticeBusiness.queryWrongInfo("fixed", "false", new_id);
		String title =(String)JSONPath.read(res, "$.title");
		Assert.assertEquals(title, ErrorEliminationName,"错题消除后，查看错题消除详情实际返回结果："+res);
	}
	
	@Test(description = "清除所有导出数据",priority = 27)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	
	@Test(description="导出题库练习的数据--通过用户名查看",priority=28)
	public void testGetExportCodeByName() {
		String res = ItemBankExerciseBusiness.exportPractice(id, UserBusiness.getUsername());
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "题库练习题导出成功","导出题库练习的数据，实际结果为："+res);
	}
	@Test(description="导出题库练习的数据--无搜索条件的情况",priority=29)
	public void testGetExportCode() {
		String  res = ItemBankExerciseBusiness.exportPractice(id, "");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "题库练习题导出成功","导出题库练习的数据，实际结果为："+res);
	}
	

	@Test(description = "查看导出的结果",priority = 30)
	public void exportRecordList() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String res = PaperExportBusiness.exportRecordList();
		String status = (String)JSONPath.read(res, "$.list[0].status");
		Assert.assertFalse(status=="FAILED", "查看导出的结果:"+res);
	}
	
	@Test(description="删除移动端题库练习",priority=31)
	public void testDelete() {
		ItemBankExerciseBusiness.deleteExce(id);
		String res = ItemBankExerciseBusiness.deleteExce(new_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功","删除题库练习："+res);
	}
	
}
