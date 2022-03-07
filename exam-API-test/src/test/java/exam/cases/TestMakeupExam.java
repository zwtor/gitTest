package exam.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.AppExamBusiness;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.MyExamTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestMakeupExam {
	String make_up_id = "";
	String name = "MakeupExam"+CommonData.getStringRandom(5);
 	@Test(description="考试结束后 ，在考试任务的已结束列表查看",priority=1)
	public void testCheckEndExam() {
		ExaminationTaskBusiness.creatExamTask("1", "show", "60", "100", "2", name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(0), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0", "0", "0",
				"0", "false", "false", UserBusiness.getUserId());
		MyExamTaskBusiness.submitBlankExam(name);
		String res = ExaminationTaskBusiness.queryList(name, "3", "false","");
		make_up_id = ExaminationTaskBusiness.getIdByKeyword(name);
		String title = (String)JSONPath.read(res, "$.list[0].title");
		Assert.assertEquals(title, name,"考试结束后 ，在考试任务的已结束列表查看实际返回结果："+res);
	}
 	
 	@Test(description="查看补考人数列表",priority=2)
 	public void testQueryMakeUpInfo() {
 		String res = ExaminationTaskBusiness.queryUntestedInfo("",make_up_id,"1");
 		String name = (String)JSONPath.read(res, "$.data.pageInfo.list[0].name");
 		int makeup_count = (int)JSONPath.read(res, "$.data.pageInfo.list[0].makeUpCount");
 		Assert.assertNotNull(name,"查看补考人数列表,名称校验"+res);
 		Assert.assertTrue(makeup_count>=1,"查看补考人数列表,考试次数进行校验"+res);
 	}
 	
	@Test(description="查看缺考/未考人数列表",priority=3)
 	public void testQueryUntestedInfo() {
 		String res = ExaminationTaskBusiness.queryUntestedInfo("",make_up_id,"2");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Assert.assertNotSame(msg, "OK","查看缺考/未考人数列表"+res);
 	}
 	
 	@Test(description = "发送补考",priority=4)
 	public void testMakeUpExam() {
 		String res = ExaminationTaskBusiness.makeUpExam(make_up_id, DateUtil.getRegularDateForHHMMSS(0), 
 				DateUtil.getRegularDateForHHMMSS(4), "3","");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Assert.assertEquals(msg, "补考发布成功","发送补考"+res);
 	}
 	@Test(description="发送补考后再次发送补考",priority=5)
 	public void testAgainMakeUpExam() {
 		String res = ExaminationTaskBusiness.makeUpExam(make_up_id, DateUtil.getRegularDateForHHMMSS(0), 
 				DateUtil.getRegularDateForHHMMSS(4), "3","");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Assert.assertEquals(msg, "上次补考未结束不能补考","发送补考后再次发送补考"+res);
 	}
 	
 	@Test(description="发送补考后，Web端显示补考按钮",priority=6)
 	public void testMakeUpExamQueryMyExamTask() {
// 		String res = MyExamTaskBusiness.queryMyExamTask("all", name);
// 		String replenish_exam = (String)JSONPath.read(res, "$.list[0].replenish_exam");
// 		Assert.assertEquals(replenish_exam, "true","发送补考后，Web端显示补考按钮"+res);
 	}
 	
 	@Test(description="发送补考后，移动端check接口验证",priority=7)
 	public void testMakeUpExamQueryResult() {
 		String res = AppExamBusiness.checkFunctionById(make_up_id);
 		String can_exam = (String)JSONPath.read(res, "$.can_exam");
 		Assert.assertEquals(can_exam, "true","发送补考后，在移动端应显示补考按钮"+res);
 	}
 	
 	@Test(description="发送补考后，移动端Result接口验证",priority=8)
 	public void testQueryResultById() {
 		String res = AppExamBusiness.queryResultById(make_up_id);
 		String show_makeup_exam = (String)JSONPath.read(res, "$.show_makeup_exam");
 		Assert.assertEquals(show_makeup_exam, "true","发送补考后，移动端Result接口验证"+res);
 	}
	@Test(description="查看补考明细",priority=9)
 	public void testQueryMakeupExamMonitorsById () {
 		String res = ExaminationTaskBusiness.queryMakeupExamMonitorsById(make_up_id,"0","","","");
 		int makeup_count = (int)JSONPath.read(res, "$.data.planUser.list[0].makeupCount");
 		ExaminationTaskBusiness.deleteExamTask(make_up_id);
 		Assert.assertTrue(makeup_count==1,"查看补考明细,考试次数进行校验"+res);
 	}
	@Test(description="未到补考时间时，依旧不能进行重考",priority=10)
	public void testCheckEndm() {
 		String make_up_id = "";
		String name = "endexam"+CommonData.getStringRandom(5);
		ExaminationTaskBusiness.creatExamTask("1", "show", "60", "100", "2", name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(0), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0", "0", "0",
				"0", "false", "false", UserBusiness.getUserId());
		MyExamTaskBusiness.submitBlankExam(name);
		make_up_id = ExaminationTaskBusiness.getIdByKeyword(name);
		ExaminationTaskBusiness.makeUpExam(make_up_id, DateUtil.getRegularDateForHHMMSS(2), 
 				DateUtil.getRegularDateForHHMMSS(4), "3","");
		String check_res = AppExamBusiness.checkFunctionById(make_up_id);
 		String can_exam = (String)JSONPath.read(check_res, "$.can_exam");
 		ExaminationTaskBusiness.deleteExamTask(make_up_id);
 		Assert.assertEquals(can_exam, "false","未到补考时间时，依旧不能进行重考"+check_res);
	}
	
	@Test(description="发送补考时，补考开始时间不能小于当前时间",priority=10)
	public void testMakeupTime() {
		String make_up_id = "";
		String name = "endexam"+CommonData.getStringRandom(5);
		ExaminationTaskBusiness.creatExamTask("1", "show", "60", "100", "2", name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(0), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0", "0", "0",
				"0", "false", "false", UserBusiness.getUserId());
		MyExamTaskBusiness.submitBlankExam(name);
		make_up_id = ExaminationTaskBusiness.getIdByKeyword(name);
		String res = ExaminationTaskBusiness.makeUpExam(make_up_id, DateUtil.getRegularDateForHHMMSS(-1), 
 				DateUtil.getRegularDateForHHMMSS(4), "3","");
		String  msg = (String)JSONPath.read(res, "$.msg");
 		ExaminationTaskBusiness.deleteExamTask(make_up_id);
 		Assert.assertEquals(msg, "补考开始时间不能小于当前时间","发送补考时，补考开始时间不能小于当前时间"+res);
	}
	String make_up_id01 = "";
	@Test(description = "通过用户id发送补考",priority=11)
	public void testMakeUpExamByUserId() {
		
		String name = "endexam"+CommonData.getStringRandom(5);
		ExaminationTaskBusiness.creatExamTask("1", "show", "60", "100", "2", name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(0), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0", "0", "0",
				"0", "false", "false", UserBusiness.getUserId());
		MyExamTaskBusiness.submitBlankExam(name);
		make_up_id01 = ExaminationTaskBusiness.getIdByKeyword(name);
		String res = ExaminationTaskBusiness.makeUpExam(make_up_id01, DateUtil.getRegularDateForHHMMSS(0), 
 				DateUtil.getRegularDateForHHMMSS(4), "3",UserBusiness.getUserId());
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "补考发布成功",""+res);
	}
	
	@Test(description="通过用户id发送补考后，移动端Result接口验证",priority=12)
 	public void testQueryResultByIdTOUserId() {
 		String res = AppExamBusiness.queryResultById(make_up_id01);
 		String show_makeup_exam = (String)JSONPath.read(res, "$.show_makeup_exam");
 		Assert.assertEquals(show_makeup_exam, "true","发送补考后，移动端Result接口验证"+res);
 	}
	@Test(description="删除考试任务",priority=13)
	public void deleteExamTask() {
		String res = ExaminationTaskBusiness.deleteExamTask(make_up_id01);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除任务成功", "删除考试任务返回结果：" + res);
	}
	
}
