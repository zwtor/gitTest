package newemployee.cases;


import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.NewEmployeeTrainBusiness;
import init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNewEmployeeTrain extends InitStudyAuthCourse{

	String train_name ="NewEmployeeTrain"+CommonData.getStringRandom(5);
	String id = "";
	String planId = "";
	String user_name = UserBusiness.getUsername();
	@Test(description="新建指派范围为全员的新员工培训",priority=1)
	public void testAddNewEmployTrainByDepart() {
		String res = NewEmployeeTrainBusiness.addNewEmployTrainByDepart(train_name, "1", DateUtil.getRegularDate(0), 
				ArticleBusiness.getIdByKeyword(articlename), CourseBusiness.getIdByPage(course_name),
				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name),"true","1");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新建成功","在部门下新建新员工培训"+res);
	}
	int status ;
	@Test(description="查看新员工培训列表",priority=2)
	public void testQueryList() {
		String res = NewEmployeeTrainBusiness.queryList(train_name, "false", "0", "0","","");
		id = (String)JSONPath.read(res, "$.list[0].id");
		planId = (String)JSONPath.read(res, "$.list[0].planId");
	    status = (int)JSONPath.read(res,"$.list[0].status");
		String title = (String)JSONPath.read(res, "$.list[0].trainName");
		String createname = (String)JSONPath.read(res, "$.list[0].createUserName");
		int trainLimit = (int)JSONPath.read(res, "$.list[0].trainLimit");
		String trainner = (String)JSONPath.read(res, "$.list[0].trainner");
		long effectTime = (long)JSONPath.read(res, "$.list[0].effectTime");
		long updateTime = (long)JSONPath.read(res, "$.list[0].updateTime");
		int finishUserCount = (int)JSONPath.read(res, "$.list[0].finishUserCount");
		int onGoingUserCount = (int)JSONPath.read(res, "$.list[0].onGoingUserCount");
		int studyCount = (int)JSONPath.read(res, "$.list[0].studyCount");
		int examCount = (int)JSONPath.read(res, "$.list[0].examCount");
		
		
		Assert.assertNotNull(updateTime, "查看新员工培训列表,更新时间进行校验"+res);
		Assert.assertNotNull(effectTime, "查看新员工培训列表,生效时间进行校验"+res);
		Assert.assertNotNull(trainner, "查看新员工培训列表,培训对象进行校验"+res);
		Assert.assertEquals(createname,user_name,"查看新员工培训列表,名称进行校验 "+res);
		Assert.assertEquals(trainLimit,10,"查看新员工培训列表,培训期限进行校验 "+res);
		Assert.assertEquals(finishUserCount,0,"查看新员工培训列表,完成人数进行校验 "+res);
		Assert.assertEquals(onGoingUserCount,0,"查看新员工培训列表,进行中人数进行校验 "+res);
		Assert.assertEquals(studyCount,2,"查看新员工培训列表,学习场次进行校验 "+res);
		Assert.assertEquals(title,train_name,"查看新员工培训列表,名称进行校验 "+res);
		Assert.assertEquals(examCount,1,"查看新员工培训列表,考试场次进行校验 "+res);
	}
	
	@Test(description="查看新员工的数据监控",priority=3)
	public void testQueryMonitors() {
		String res = NewEmployeeTrainBusiness.queryMonitors(planId,"0");
		String title = (String)JSONPath.read(res, "$.data.trainTask.trainName");
		long createTime = (long)JSONPath.read(res, "$.data.trainTask.createTime");
		int trainLimit = (int)JSONPath.read(res, "$.data.trainTask.trainLimit");
		String createUserName = (String)JSONPath.read(res, "$.data.trainTask.createUserName");
		String trainObjectName = (String)JSONPath.read(res, "$.data.trainTask.trainObjectName");
		int progress = (int)JSONPath.read(res, "$.data.trainTask.progress");
		Assert.assertEquals(title, train_name,"查看新员工的数据监控,对标题校验"+res);
		Assert.assertEquals(trainLimit, 10,"查看新员工的数据监控,对培训期限校验"+res);
		Assert.assertEquals(createUserName, user_name,"查看新员工的数据监控,对创建人校验"+res);
		Assert.assertEquals(title, train_name,"查看新员工的数据监控,对标题校验"+res);
		Assert.assertEquals(progress, 100,"查看新员工的数据监控,对达标标准校验"+res);
		Assert.assertNotNull(trainObjectName, "查看新员工的数据监控,对培训对象校验"+res);
		Assert.assertNotNull(createTime, "查看新员工的数据监控,对创建时间校验"+res);
		
	}
	String exam_id = "";
	@Test(description="查看新员工的试题分析",priority=4)
	public void testQueryExamResult() {
		String res = NewEmployeeTrainBusiness.queryNewStudyExamInfo(planId);
		String title = (String)JSONPath.read(res, "$.list[0].title");
		exam_id = (String)JSONPath.read(res, "$.list[0].id");
		Assert.assertNotNull(title, "查看新员工的试题分析,对试题标题校验"+res);
	}
	
	@Test(description="查看新员工的考试数据",priority=5)
	public void testQueryNewStudyExamInfo() {
		String res = NewEmployeeTrainBusiness.queryExamResult(exam_id);
		String title = (String)JSONPath.read(res, "$.data.planExam.title");
		String creatorName = (String)JSONPath.read(res, "$.data.planExam.creatorName");
		int passLine = (int)JSONPath.read(res, "$.data.planExam.passLine");
		Assert.assertEquals(title, BaseBusiness.pass_paper_name,"查看新员工的考试数据,对试题名称进行校验"+res);
		Assert.assertEquals(creatorName, user_name,"查看新员工的考试数据,对创建人进行校验"+res);
		Assert.assertEquals(passLine, 60,"查看新员工的考试数据,对及格线进行校验"+res);
	}
	
	@Test(description="启用新员工培训",priority=6)
	public void testBeginTask() {
		if (status==2) {
			String res = NewEmployeeTrainBusiness.beginAndStopTask(id, "1");
			String msg = (String)JSONPath.read(res, "$.msg");
			Assert.assertEquals(msg, "启用成功","启用新员工培训,实际返回结果："+res);
		}
	}
	@Test(description="查看启用后新员工培训列表",priority=7)
	public void testQueryOpenList() {
		String res = NewEmployeeTrainBusiness.queryList(train_name, "false", "0", "0","","");
		int open_status = (int)JSONPath.read(res,"$.list[0].status");
		Assert.assertEquals(open_status,1,"查看启用后新员工培训列表,名称进行校验 "+res);
	}
	@Test(description="停用新员工培训",priority=8)
	public void testStopTask() {
		String res = NewEmployeeTrainBusiness.beginAndStopTask(id, "2");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "停用成功","停用新员工培训,实际返回结果："+res);
	}
	@Test(description="停用后查看新员工培训列表",priority=9)
	public void testQueryStopList() {
		String res = NewEmployeeTrainBusiness.queryList(train_name, "false", "0", "0","","");
		int open_status = (int)JSONPath.read(res,"$.list[0].status");
		Assert.assertEquals(open_status,2,"停用后查看新员工培训列表,名称进行校验 "+res);
	}
	@Test(description="根据部门查看不同状态的新员工培训的个数",priority=10)
	public void testGetListCount() {
		String res = NewEmployeeTrainBusiness.getListCount("0");
		int allCount = (int)JSONPath.read(res, "$.allCount");
		Assert.assertTrue(allCount>=0, "查看不同状态的新员工培训的个数"+res);
	}
	
	@Test(description="查看部门下的新员工培训详情",priority=11)
	public void testQueryInfoByDepartment() {
		String res = NewEmployeeTrainBusiness.queryInfo(id);
		String trainName =(String )JSONPath.read(res, "$.trainName");
		Assert.assertEquals(trainName,train_name ,"查看部门下的新员工培训详情"+res);
	} 
	
	@Test(description= "删除新员工培训",priority=12)
	public void testDeleteEmployTrainTask () {
		String res = NewEmployeeTrainBusiness.deleteEmployTrainTask(planId);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功","删除新员工培训,实际返回结果："+res);
	}
	@Test(description="查看企业版本信息getEnterpriseVersion",priority=13)
	public void testGetEnterpriseVersion() {
		String res = NewEmployeeTrainBusiness.getEnterpriseVersion();
		String remainDays = (String)JSONPath.read(res, "$.remainDays");
		Assert.assertTrue(remainDays!=null, "查看企业版本信息getEnterpriseVersion接口："+res);
	}
	@Test(description="查看未生效的新员工培训列表",priority=14)
	public void testQueryNoEffiectList() {
		String res = NewEmployeeTrainBusiness.queryList("", "false", "1", "0","","");
		int total = (int)JSONPath.read(res,"$.total");
		Assert.assertTrue(total>=0, "查看未生效的新员工培训列表"+res);
	}
	@Test(description="查看已生效新员工培训列表",priority=15)
	public void testQueryEffectList() {
		String res = NewEmployeeTrainBusiness.queryList("", "true", "2", "0","","");
		int total = (int)JSONPath.read(res,"$.total");
		Assert.assertTrue(total>=0, "查看已生效的新员工培训列表"+res);
	}
	@Test(description="查看已停用新员工培训列表",priority=16)
	public void testQueryStopedList() {
		String res = NewEmployeeTrainBusiness.queryList("", "false", "3", "0","","");
		int total = (int)JSONPath.read(res,"$.total");
		Assert.assertTrue(total>=0, "查看已停用的新员工培训列表"+res);
	}
	
	String name = "NewEmploy"+CommonData.getStringRandom(5);
	String post_id =  "";
	String post_plan_id = "";
	int post_status ;
	@Test(description="在岗位下新建新员工培训",priority=17)
	public void testAddNewEmployTrainByPost() {
		String res = NewEmployeeTrainBusiness.addNewEmployTrainByPost(name, NewEmployeeTrainBusiness.getPostId(),
				DateUtil.getRegularDate(1),ArticleBusiness.getIdByKeyword(articlename) );
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新建成功","在岗位下新建新员工培训"+res);
	}
	@Test(description="查询岗位下的新员工培训列表",priority=18)
	public void testQueryListByPost () {
		String res = NewEmployeeTrainBusiness.queryList(name, "false", "0", "1","","");
		post_id = (String)JSONPath.read(res, "$.list[0].id");
		post_plan_id = (String)JSONPath.read(res, "$.list[0].planId");
		post_status = (int)JSONPath.read(res,"$.list[0].status");
		String title = (String)JSONPath.read(res, "$.list[0].trainName");
		Assert.assertEquals(title,name,"查询岗位下的新员工培训列表,名称进行校验 "+res);
	}
	@Test(description="在岗位下启用新员工培训",priority=19)
	public void testBeginTaskByPost() {
		if (post_status==2) {
			String res = NewEmployeeTrainBusiness.beginAndStopTask(post_id, "1");
			String msg = (String)JSONPath.read(res, "$.msg");
			Assert.assertEquals(msg, "启用成功","在岗位下启用新员工培训,实际返回结果："+res);
		}
	}
	@Test(description="在岗位下查看启用后新员工培训列表",priority=20)
	public void testQueryOpenListByPost() {
		String res = NewEmployeeTrainBusiness.queryList(name , "false", "0", "1","","");
		int open_status = (int)JSONPath.read(res,"$.list[0].status");
		Assert.assertEquals(open_status,1,"在岗位下查看启用后新员工培训列表,名称进行校验 "+res);
	}
	@Test(description="在岗位下停用新员工培训",priority=21)
	public void testStopTaskByPost() {
		String res = NewEmployeeTrainBusiness.beginAndStopTask(post_id, "2");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "停用成功","在岗位下停用新员工培训,实际返回结果："+res);
	}
	@Test(description="在岗位下停用后查看新员工培训列表",priority=22)
	public void testQueryStopListByPost() {
		String res = NewEmployeeTrainBusiness.queryList(name, "false", "0", "1","","");
		int open_status = (int)JSONPath.read(res,"$.list[0].status");
		Assert.assertEquals(open_status,2,"在岗位下停用后查看新员工培训列表,名称进行校验 "+res);
	}
	@Test(description="在岗位下查看不同状态的新员工培训的个数",priority=23)
	public void testGetListCountByPost() {
		String res = NewEmployeeTrainBusiness.getListCount("1");
		int allCount = (int)JSONPath.read(res, "$.allCount");
		Assert.assertTrue(allCount>=0, "在岗位下查看不同状态的新员工培训的个数"+res);
	}
	
	@Test(description="查看岗位下的新员工培训详情",priority=24)
	public void testQueryInfo() {
		String res = NewEmployeeTrainBusiness.queryInfo(post_id);
		String trainName =(String )JSONPath.read(res, "$.trainName");
		Assert.assertEquals(trainName, name,"查看新员工培训详情"+res);
	} 
	
	@Test(description = "清除所有导出数据",priority = 25)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	
	@Test(description="导出新员工培训数据-All",priority=26)
	public void testGetExportMonitorsCodeAll() {
		String res = NewEmployeeTrainBusiness.getExportTrainMonitorCode(post_plan_id,"0");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新员工培训监控信息导出成功","导出新员工培训数据"+res);
	}
	@Test(description="导出新员工培训数据-未完成",priority=27)
	public void testGetExportMonitorsCodeUnStart() {
		String res = NewEmployeeTrainBusiness.getExportTrainMonitorCode(post_plan_id,"1");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新员工培训监控信息导出成功","导出新员工培训数据"+res);
	}
	@Test(description="导出新员工培训数据-进行中",priority=28)
	public void testGetExportMonitorsCodeOnGoing() {
		String res = NewEmployeeTrainBusiness.getExportTrainMonitorCode(post_plan_id,"2");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新员工培训监控信息导出成功","导出新员工培训数据"+res);
	}
	@Test(description="导出新员工培训数据-已完成",priority=29)
	public void testGetExportMonitorsCodeFinish() {
		String res = NewEmployeeTrainBusiness.getExportTrainMonitorCode(post_plan_id,"3");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新员工培训监控信息导出成功","导出新员工培训数据"+res);
	}
	
	@Test(description = "查看导出的结果",priority = 30)
	public void exportRecordList() {
		String res = PaperExportBusiness.exportRecordList();
		String status_1 = (String)JSONPath.read(res, "$.list[0].status");
		String status_2 = (String)JSONPath.read(res, "$.list[1].status");
		String status_3 = (String)JSONPath.read(res, "$.list[2].status");
		String status_4 = (String)JSONPath.read(res, "$.list[3].status");
		Assert.assertFalse(status_1=="FAILED", "查看导出的结果，在下载中心查看"+res);	
		Assert.assertFalse(status_2=="FAILED", "查看导出的结果，在下载中心查看"+res);
		Assert.assertFalse(status_3=="FAILED", "查看导出的结果，在下载中心查看"+res);
		Assert.assertFalse(status_4=="FAILED", "查看导出的结果，在下载中心查看"+res);	
	}
	
	@Test(description= "在岗位下删除新员工培训",priority=31)
	public void testDeleteEmployTrainTaskByPost () {
		String res = NewEmployeeTrainBusiness.deleteEmployTrainTask(post_plan_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功","在岗位下删除新员工培训,实际返回结果："+res);
	}
	
}
