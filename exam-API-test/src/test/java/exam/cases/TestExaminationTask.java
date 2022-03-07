package exam.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExaminationBusines;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.my.business.MyBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import init.cases.InitExam;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestExaminationTask extends InitExam {
	
	public static String random_exam_task_name = "RandomExam" + CommonData.getStringRandom(5);
	int startscore = MyBusiness.getMyTotalScore();
	String exam_task_name ="exam"+CommonData.getStringRandom(5);
	String id = "";
	@BeforeClass
	public void init() {
		ExaminationTaskBusiness.creatExamTask("1", "show", "60", "100", "2", exam_task_name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0", "0", "0",
				"0", "false", "false", UserBusiness.getUserId());
		id = ExaminationTaskBusiness.getIdByName(exam_task_name);
	}
	
	@Test(description = "查看不同类型的考试任务", dataProvider = "QueryList",priority=1)
	public void testQueryList(String type, String queryIntervalType, int exp) {
		String res = ExaminationTaskBusiness.queryList("", queryIntervalType, "false","");
		int total = (int) JSONPath.read(res, "$.total");
		Assert.assertTrue(total >= exp, "查看不同类型的考试任务返回结果：" + res);

	}

	@Test(description="新增随机考试任务",priority=2)
	public void testCreatRandomExamTask() {
		String res = ExaminationTaskBusiness.creatRandomExamTask("2", "show", "20", "1", "40", "0", "0", "0", "0", "1", "30", 
				"0", "0", ExaminationBusines.getIdByKeyword(BaseBusiness.examPassName), "2", random_exam_task_name, DateUtil.getRegularDate(0), DateUtil.getRegularDate(1), 
				"false", UserBusiness.getUserId(), "45", "0", "0", "1", CertificateBusiness.getIdByKeyword(BaseBusiness.certificate_name), "20", "0", "0", "0", "0",
				"{\"missScore\":0,\"passScore\":5,\"unPassScore\":0}", "true", "false", UserBusiness.getUserId(),"false");
		String msg = (String)JSONPath.read(res, "$.msg");
		ExaminationTaskBusiness.submitPassExam(random_exam_task_name);
		Assert.assertEquals(msg, "新增考试成功！","新增随机考试任务实际返回结果："+res);
	}
	
	@Test(description="删除随机考试任务",dependsOnMethods="testCreatRandomExamTask",priority=3)
	public void testRandomExamGetScore() {
		String res = ExaminationTaskBusiness.deleteExamTask(ExaminationTaskBusiness.getIdByKeyword(random_exam_task_name));
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除任务成功", "删除随机考试任务返回结果：" + res);
	}
	
	@Test(description="获取全部，未开始，进行中，已结束考试状态的人数",priority=4)
	public void testGetIntervalStatistical() {
		String res = ExaminationTaskBusiness.getIntervalStatistical();
		int total = (int) JSONPath.read(res, "$.totalCount");
		Assert.assertTrue(total>0,"获取不同考试状态的人数实际返回结果"+res);
	}
	
	@Test(description = "新增考试任务",priority=5)
	public void testCreatExamTask() {
		String name ="exam"+CommonData.getStringRandom(5);
		String res = ExaminationTaskBusiness.creatExamTask("1", "show", "60", "100", "2", name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0", "0", "0",
				"0", "false", "false", UserBusiness.getUserId());
		String msg = (String) JSONPath.read(res, "$.msg");
		ExaminationTaskBusiness.deleteExamTask(ExaminationTaskBusiness.getIdByKeyword(name));
		Assert.assertEquals(msg, "新增考试成功！", "新增考试任务返回结果：" + res);

	}

	
	@Test(description = "新增考试任务时，对参数进行非空校验", dataProvider = "CheckCreatExamTask",priority=11)
	public void testCheckCreatExamTask(String type, String questionMode, String showKnowledge, String passLine,
			String totalScore, String answerParsing, String title, String beginTime, String endTime, String isAllIn,
			String userIds, String paperId, String examDuration, String cheatFlag, String repeatExam, String markType,
			String examCertificateId, String passingScore, String cutScreenCount, String reExamRule,
			String reExamNumber, String scoreRule, String isGetScore, String supervisorPaper, String supervisorId,
			String exp) {
		String res = ExaminationTaskBusiness.creatExamTask(questionMode, showKnowledge, passLine, totalScore,
				answerParsing, title, beginTime, endTime, isAllIn, userIds, paperId, examDuration, cheatFlag,
				repeatExam, markType, examCertificateId, passingScore, cutScreenCount, reExamRule, reExamNumber,
				scoreRule, isGetScore, supervisorPaper, supervisorId);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, exp, "新增考试任务时，对参数进行非空校验返回结果：" + res);

	}

	@Test(description = "编辑考试任务时，对参数进行非空校验", dataProvider = "CheckEditExamTask",priority=12)
	public void testCheckEditExamTask(String type, String questionMode, String showKnowledge, String passLine,
			String totalScore, String answerParsing, String title, String beginTime, String endTime, String isAllIn,
			String userIds, String paperId, String examDuration, String cheatFlag, String repeatExam, String markType,
			String examCertificateId, String passingScore, String cutScreenCount, String reExamRule,
			String reExamNumber, String scoreRule, String isGetScore, String supervisorPaper, String supervisorId,
			String examTaskId, String status, String exp) {
		String res = ExaminationTaskBusiness.eidtExamTask(questionMode, showKnowledge, passLine, totalScore,
				answerParsing, title, beginTime, endTime, isAllIn, userIds, paperId, examDuration, cheatFlag,
				repeatExam, markType, examCertificateId, passingScore, cutScreenCount, reExamRule, reExamNumber,
				scoreRule, isGetScore, supervisorPaper, supervisorId, examTaskId, status);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, exp, "编辑考试任务时，对参数进行非空校验返回结果：" + res);

	}

	@Test(description = "查看单个考试详情", priority=13)
	public void testQueryExamTaskInfo() {
		String name ="exam"+CommonData.getStringRandom(5);
		ExaminationTaskBusiness.creatExamTask("1", "show", "60", "100", "2", name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0", "0", "0",
				"0", "false", "false", UserBusiness.getUserId());
		String res01 = ExaminationTaskBusiness.queryInfo(name);
		String name01 = (String) JSONPath.read(res01, "$.title");
		ExaminationTaskBusiness.deleteExamTask(ExaminationTaskBusiness.getIdByKeyword(name));
		Assert.assertTrue(!name01.isEmpty(), "查看单个考试详情返回结果：" + res01);

	}

	@Test(description = "崔考提醒", priority=14)
	public void testRemindExam() {
		String name ="exam"+CommonData.getStringRandom(5);
		ExaminationTaskBusiness.creatExamTask("1", "show", "60", "100", "2", name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0", "0", "0",
				"0", "false", "false", UserBusiness.getUserId());
		String res01 = ExaminationTaskBusiness.remindExam(ExaminationTaskBusiness.getIdByName(name), "2");
		String msg = (String) JSONPath.read(res01, "$.msg");
		ExaminationTaskBusiness.deleteExamTask(ExaminationTaskBusiness.getIdByKeyword(name));
		Assert.assertEquals(msg, "成功通知到1位考生", "崔考提醒返回结果：" + res01);
	}

	@Test(description = "崔考之后，再次崔考提示语校验",priority=15)
	public void testRemindAgainExam() {
		String name ="exam"+CommonData.getStringRandom(5);
		ExaminationTaskBusiness.creatExamTask("1", "show", "60", "100", "2", name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0", "0", "0",
				"0", "false", "false", UserBusiness.getUserId());
		ExaminationTaskBusiness.remindExam(ExaminationTaskBusiness.getIdByName(name), "2");
		String res01 = ExaminationTaskBusiness.remindExam(ExaminationTaskBusiness.getIdByName(name), "2");
		String msg = (String) JSONPath.read(res01, "$.msg");
		ExaminationTaskBusiness.deleteExamTask(ExaminationTaskBusiness.getIdByKeyword(name));
		Assert.assertTrue(msg.contains("60分钟之内只允许发送一次提醒信息"), "崔考之后，再次崔考的返回结果：" + res01);
	}

	@Test(description = "对崔考提醒的考试任务id进行校验", priority=16)
	public void testCheckRemindExam() {
//		String res = ExaminationTaskBusiness.remindExam("", "2");
//		String msg = (String) JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "查询不到考试计划，请联系管理员", "对崔考提醒的考试任务id进行校验的实际返回结果：" + res);
	}

	@Test(description = "对延期考试任务的延期时间不允许在当前时间之前进行校验",priority=17)
	public void testCheckTimeBeforeDelayExamTask() {
		String name ="exam"+CommonData.getStringRandom(5);
		ExaminationTaskBusiness.creatExamTask("1", "show", "60", "100", "2", name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0", "0", "0",
				"0", "false", "false", UserBusiness.getUserId());
		
		String res01 = ExaminationTaskBusiness.delayExamTask(DateUtil.getRegularDate(-5),
				ExaminationTaskBusiness.getIdByName(name));
		String msg = (String) JSONPath.read(res01, "$.msg");
		ExaminationTaskBusiness.deleteExamTask(ExaminationTaskBusiness.getIdByKeyword(name));
		Assert.assertEquals(msg, "延期时间不允许在当前时间之前", "对延期考试任务的延期时间不允许在当前时间之前进行校验的实际返回结果：" + res01);
	}

	@Test(description = "对延期考试任务的时间进行非空校验", priority=18)
	public void testCheckTimeDelayExamTask() {
		String name ="examTime"+CommonData.getStringRandom(5);
		ExaminationTaskBusiness.creatExamTask("1", "show", "60", "100", "2", name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0", "0", "0",
				"0", "false", "false", UserBusiness.getUserId());
		String res01 = ExaminationTaskBusiness.delayExamTask("", ExaminationTaskBusiness.getIdByName(name));
		String msg = (String) JSONPath.read(res01, "$.msg");
		ExaminationTaskBusiness.deleteExamTask(ExaminationTaskBusiness.getIdByKeyword(name));
		Assert.assertEquals(msg, "无效的参数", "对延期考试任务的时间进行非空校验的实际返回结果：" + res01);
	}

	@Test(description = "对延期考试任务的Id进行非空校验", priority=19)
	public void testCheckIdDelayExamTask() {
		String res = ExaminationTaskBusiness.delayExamTask(DateUtil.getRegularDate(5), "");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "无效的参数", "对延期考试任务的Id进行非空校验的实际返回结果：" + res);
	}

	@Test(description = "延期考试任务", priority=20)
	public void testDelayExamTask() {
		String name ="exam"+CommonData.getStringRandom(5);
		ExaminationTaskBusiness.creatExamTask("1", "show", "60", "100", "2", name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0", "0", "0",
				"0", "false", "false", UserBusiness.getUserId());
		String res01 = ExaminationTaskBusiness.delayExamTask(DateUtil.getRegularDate(5),
				ExaminationTaskBusiness.getIdByName(name));
		String msg = (String) JSONPath.read(res01, "$.msg");
		ExaminationTaskBusiness.deleteExamTask(ExaminationTaskBusiness.getIdByKeyword(name));
		Assert.assertEquals(msg, "延期成功", "延期考试任务的返回结果：" + res01);
	}

	@Test(description = "变更考试任务", priority=21)
	public void testChangeExamTask() {
		String name ="exam"+CommonData.getStringRandom(5);
	    ExaminationTaskBusiness.creatExamTask("1", "show", "60", "100", "2", name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0", "0", "0",
				"0", "false", "false", UserBusiness.getUserId());
		String res01 = ExaminationTaskBusiness.changeExamTask(ExaminationTaskBusiness.getIdByName(name), "0", "0", "",
				UserBusiness.getUserId(), "", "");
		String msg = (String) JSONPath.read(res01, "$.msg");
		ExaminationTaskBusiness.deleteExamTask(ExaminationTaskBusiness.getIdByKeyword(name));
		Assert.assertEquals(msg, "变更成功", "变更考试任务返回结果：" + res01);
	}
	@Test(description = "变更考试任务时，对不同参数进行校验",dataProvider="ChangeExamTask",priority=22)
	public void testCheckChangeExamTask(String type,String id, String isNoticeAll, String isAllIn, String groupIds, String userIds,
			String departmentIds, String postIds,String exp) {
		String res = ExaminationTaskBusiness.changeExamTask(id, isNoticeAll, isAllIn, groupIds, userIds, departmentIds, postIds);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(exp, msg, "变更考试任务返回结果：" + res);
	}

	@DataProvider(name = "ChangeExamTask")
	public Object[][] changeExamTask() {
		Object[][] obj = new Object[][] {
				{ "变更考试任务时，对考试的id进行非空校验","", "0", "0", "", UserBusiness.getUserId(), "", "","参数不符合要求" },
				{ "变更考试任务时，对变更类型进行非空校验",id, "0", "", "", UserBusiness.getUserId(), "", "" ,"参数不符合要求"},
				{"变更考试任务时，对userid进行非空校验", id, "0", "0", "", "", "", "","人员选择不符合要求" } };
		return obj;
	}

	@Test(description = "编辑考试任务", priority=23)
	public void testEditExamTask() {
		String res = ExaminationTaskBusiness.eidtExamTask("1", "show", "60", "20", "2", exam_task_name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0", "0", "0",
				"0", "false", "false", UserBusiness.getUserId(), id, "unfinished");

		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "修改考试成功！", "编辑考试任务返回结果：" + res);
	}

	@Test(description = "查看缺考和未考的信息", priority=24)
	public void testQueryUntestedInfo() {
		String res = ExaminationTaskBusiness.queryUntestedInfo("",id, "2");
		String status = (String) JSONPath.read(res, "$.data.status");
		int total = (int) JSONPath.read(res, "$.data.pageInfo.total");
		Assert.assertEquals(status, "on_going", "查看缺考和未考的信息返回结果：" + res);
		Assert.assertTrue(total >= 1, "查看缺考和未考的信息返回结果：" + res);

	}
	
	@Test(description = "删除考试任务时，对考试id进行非空校验")
	public void testCheckIdDeleteExamTask() {
		String res = ExaminationTaskBusiness.deleteExamTask("");
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "考试任务不存在", "删除考试任务时，对考试id进行非空校验实际返回结果：" + res);
	}
	
	@Test(description = "删除考试任务", dependsOnMethods = "testQueryUntestedInfo",priority=25)
	public void testDeleteExamTask() {
		String res = ExaminationTaskBusiness.deleteExamTask(id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除任务成功", "删除考试任务返回结果：" + res);
	}
	
	@DataProvider(name = "QueryList")
	public Object[][] QueryList() {
		Object[][] obj = new Object[][] { { "全部", "0", 0 }, { "未开始", "1", 0 }, { "进行中", "2", 0 }, { "已结束", "3", 0 } };
		return obj;
	}

	@DataProvider(name = "CheckEditExamTask")
	public Object[][] checkEditExamTask() {
		Object[][] obj = new Object[][] {

				{ "对考试名称进行非空校验", "1", "show", "60", "20", "2", "", DateUtil.getRegularDate(0),
						DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
						PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0",
						"0", "0", "0", "false", "false", UserBusiness.getUserId(), id,
						"unfinished", "考试名称不能为空！" },
				{ "对开始时间进行非空校验", "1", "show", "60", "20", "2", "fa", "", DateUtil.getRegularDate(2), "false",
						UserBusiness.getUserId(), PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "",
						"false", "1", "0", "12", "0", "0", "0", "0", "false", "false", UserBusiness.getUserId(),
						id, "unfinished", "开始时间不能为空！" },
				{ "对结束时间进行非空校验", "1", "show", "60", "20", "2", "fa", DateUtil.getRegularDate(2), "", "false",
						UserBusiness.getUserId(), PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "",
						"false", "1", "0", "12", "0", "0", "0", "0", "false", "false", UserBusiness.getUserId(),
						id, "unfinished", "结束时间不能为空！" },
				{ "对指派范围进行非空校验", "1", "show", "60", "20", "2", "fa", DateUtil.getRegularDate(1),
						DateUtil.getRegularDate(2), "", UserBusiness.getUserId(),
						PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0",
						"0", "0", "0", "false", "false", UserBusiness.getUserId(), id,
						"unfinished", "是否为全员不能为空！" },
				{ "对分配对象进行非空校验", "1", "show", "60", "20", "2", "fa", DateUtil.getRegularDate(1),
						DateUtil.getRegularDate(2), "false", "", PaperBusiness.getIdByKeyword(BaseBusiness.paper_name),
						"60", "", "false", "1", "0", "12", "0", "0", "0", "0", "false", "false",
						UserBusiness.getUserId(), id, "unfinished", "请选择分配对象为空！" },
				{ "对试卷id进行非空校验", "1", "show", "60", "20", "2", "fa", DateUtil.getRegularDate(1),
						DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(), "", "60", "", "false", "1", "0",
						"12", "0", "0", "0", "0", "false", "false", UserBusiness.getUserId(),
						id, "unfinished", "请选择试卷！" },
				{ "对试卷时长进行非空校验", "1", "show", "60", "20", "2", "fa", DateUtil.getRegularDate(1),
						DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
						PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "", "", "false", "1", "0", "12", "0",
						"0", "0", "0", "false", "false", UserBusiness.getUserId(), id,
						"unfinished", "试卷时长不能为空！" },
				{ "对考试任务id进行非空校验", "1", "show", "60", "20", "2", exam_task_name, DateUtil.getRegularDate(0),
						DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
						PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0",
						"0", "0", "0", "false", "false", UserBusiness.getUserId(), "", "unfinished", "不存在的考试" },
				{ "编辑时对考试状态进行校验", "1", "show", "60", "20", "2", exam_task_name, DateUtil.getRegularDate(0),
						DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
						PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0",
						"0", "0", "0", "false", "false", UserBusiness.getUserId(), id,
						"", "参数不符合要求" }

		};
		return obj;
	}

	@DataProvider(name = "CheckCreatExamTask")
	public Object[][] checkCreatExamTask() {
		Object[][] obj = new Object[][] {
				{ "对出题模式进行非空校验", "", "show", "60", "20", "2", exam_task_name, DateUtil.getRegularDate(0),
						DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
						PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0",
						"0", "0", "0", "false", "false", UserBusiness.getUserId(), "出题模式不能为空！" },
				{ "对及格线进行非空校验", "1", "show", "", "20", "2", exam_task_name, DateUtil.getRegularDate(0),
						DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
						PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0",
						"0", "0", "0", "false", "false", UserBusiness.getUserId(), "及格线不能为空！" },
				{ "对考试名称进行非空校验", "1", "show", "60", "20", "2", "", DateUtil.getRegularDate(0),
						DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
						PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0",
						"0", "0", "0", "false", "false", UserBusiness.getUserId(), "考试名称不能为空！" },
				{ "对开始时间进行非空校验", "1", "show", "60", "20", "2", "fa", "", DateUtil.getRegularDate(2), "false",
						UserBusiness.getUserId(), PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "",
						"false", "1", "0", "12", "0", "0", "0", "0", "false", "false", UserBusiness.getUserId(),
						"开始时间不能为空！" },
				{ "对结束时间进行非空校验", "1", "show", "60", "20", "2", "fa", DateUtil.getRegularDate(2), "", "false",
						UserBusiness.getUserId(), PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "",
						"false", "1", "0", "12", "0", "0", "0", "0", "false", "false", UserBusiness.getUserId(),
						"结束时间不能为空！" },
				{ "对指派范围进行非空校验", "1", "show", "60", "20", "2", "fa", DateUtil.getRegularDate(1),
						DateUtil.getRegularDate(2), "", UserBusiness.getUserId(),
						PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "false", "1", "0", "12", "0",
						"0", "0", "0", "false", "false", UserBusiness.getUserId(), "是否为全员不能为空！" },
				{ "对分配对象进行非空校验", "1", "show", "60", "20", "2", "fa", DateUtil.getRegularDate(1),
						DateUtil.getRegularDate(2), "false", "", PaperBusiness.getIdByKeyword(BaseBusiness.paper_name),
						"60", "", "false", "1", "0", "12", "0", "0", "0", "0", "false", "false",
						UserBusiness.getUserId(), "请选择分配对象为空！" },
				{ "对试卷id进行非空校验", "1", "show", "60", "20", "2", "fa", DateUtil.getRegularDate(1),
						DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(), "", "60", "", "false", "1", "0",
						"12", "0", "0", "0", "0", "false", "false", UserBusiness.getUserId(), "请选择试卷！" },
				{ "对试卷时长进行非空校验", "1", "show", "60", "20", "2", "fa", DateUtil.getRegularDate(1),
						DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
						PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "", "", "false", "1", "0", "12", "0",
						"0", "0", "0", "false", "false", UserBusiness.getUserId(), "试卷时长不能为空！" }

		};
		return obj;
	}

	@AfterClass
	public void end() {
		if (ExaminationTaskBusiness.getIdByName(exam_task_name) != null) {
			ExaminationTaskBusiness.deleteExamTask(id);
		}
	}
}
