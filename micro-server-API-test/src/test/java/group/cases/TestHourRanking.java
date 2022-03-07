package group.cases;
/**
 * @author wenlingzhi
 *2021年5月8日
 */

import cn.kxy.group.a.business.HourRankingBusiness;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestHourRanking {	
	public static String switch_id = "";
	public static String type = "";
	public static String sys_actions = "";
	public static String dept_idFirst = "";
	public static String dept_idSecond = "";

	//移动端学时排行榜不处理普通员工无学时入口权限，只处理主管理员权限
	
 	@Test(description="1.获取移动端学时排行榜按钮id接口", priority=1)
 	public void testResourceClassifyAdd()  {
 		String res = HourRankingBusiness.QuerySwitchesId();
 		System.out.println("1.获取移动端学时排行榜按钮id接口:");
 		String title = "";
 		JSONArray eventMsgArray = (JSONArray) JSONPath.read(res, "$.list");
 		for(Object obj :eventMsgArray) {
 			JSONObject jsonObj	=	(JSONObject) JSONObject.parse(obj.toString());
 			if(jsonObj.getString("type").contains("mobile_trainee_classhour_ranking")) {
 				title = jsonObj.getString("title");
 				switch_id = jsonObj.getString("switch_id");
 				sys_actions = jsonObj.getString("sys_actions");
 			}			
 		}						
// 		switch_id =  (String)JSONPath.read(res, "$.list[9].switch_id");
// 		type = (String)JSONPath.read(res, "$.list[9].type");
// 		sys_actions = (String)JSONPath.read(res, "$.list[9].sys_actions");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total+","+"switch_id="+switch_id+","+"type="+type);
		Assert.assertNotEquals(total,0,"1.获取移动端学时排行榜按钮id接口"+res);
 	}
 	
 	
 	@Test(description="2.学时排行榜按钮开关-打开接口", priority=2)
 	public void testSwitchSettingOpen()  {
 		//这里需要特别注意actions的格式jsonArray，postman里的入参直接粘贴进data里会自动添加转义符，导致actions格式错误接口返5001
 		//具体处理方式参考接口定义时的方法
// 		String res = HourRankingBusiness.SwitchSetting(switch_id,type,"OPEN",sys_actions);
// 		System.out.println("2.学时排行榜按钮开关-打开接口:");
// 		String setting = (String)JSONPath.read(res, "$.setting");
// 		System.out.println("setting="+setting);
//		Assert.assertEquals(setting,"true","2.学时排行榜按钮开关-打开接口"+res);
 	}
 	
 	
 	@Test(description="3.移动端-获取当前用户所在所有部门接口", priority=3)
 	public void testUserDepartments()  {
 		String res = HourRankingBusiness.UserDepartments();
 		System.out.println("3.移动端-获取当前用户所在所有部门接口:");
 		dept_idFirst = (String)JSONPath.read(res, "$.data[0].dept_id");
 		dept_idSecond = (String)JSONPath.read(res, "$.data[1].dept_id");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"success","3.移动端-获取当前用户所在所有部门接口"+res);
 	}
 	
 	
 	@Test(description="4.移动端-我的页面检验学时入口接口", priority=4)
 	public void testUserClassHourRanking()  {
 		String res = HourRankingBusiness.UserClassHourRanking();
 		System.out.println("4.移动端-我的页面检验学时入口接口:");
 		String studyDuration = (String)JSONPath.read(res, "$.user.studyDuration");
 		System.out.println("studyDuration="+studyDuration);
		Assert.assertNotEquals(studyDuration,null,"4.移动端-我的页面检验学时入口接口"+res);
 	}
 	
 	
 	@Test(description="5.移动端-我的学时页-我的学时和排名接口", priority=5)
 	public void testMyUserClassHourRanking()  {
 		String res = HourRankingBusiness.MyUserClassHourRanking(dept_idFirst);
 		System.out.println("5.移动端-我的学时页-我的学时和排名接口:");
 		String studyDuration = (String)JSONPath.read(res, "$.user.studyDuration");
 		Integer ranking = (Integer)JSONPath.read(res, "$.user.ranking");
 		System.out.println("studyDuration="+studyDuration+","+"ranking="+ranking);
		Assert.assertNotEquals(studyDuration,null,"5.移动端-我的学时页-我的学时和排名接口"+res);
 	}
 	
 	
 	@Test(description="6.移动端-我的学时页-全公司的学员排行接口", priority=6)
 	public void testUsersClassHourRanking()  {
 		String res = HourRankingBusiness.MyUserClassHourRanking(dept_idFirst);
 		System.out.println("6.移动端-我的学时页-全公司的学员排行接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.list.total");
 		System.out.println("total="+total);
		Assert.assertNotEquals(total,0,"6.移动端-我的学时页-全公司的学员排行接口"+res);
 	}
 	
 	
 	@Test(description="7.移动端-我的学时页-切换到子部门的学员排行接口", priority=7)
 	public void testUsersClassHourRankingDept()  {
 		String res = HourRankingBusiness.MyUserClassHourRanking(dept_idSecond);
 		System.out.println("7.移动端-我的学时页-切换到子部门的学员排行接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.list.total");
 		System.out.println("total="+total);
		Assert.assertNotEquals(total,0,"7.移动端-我的学时页-切换到子部门的学员排行接口"+res);
 	}
 	
 	
 	@Test(description="8.移动端-我的学时页-切换到子部门后我的排名接口", priority=8)
 	public void testMyUserClassHourRankingDept()  {
 		String res = HourRankingBusiness.MyUserClassHourRanking(dept_idSecond);
 		System.out.println("8.移动端-我的学时页-切换到子部门后我的排名接口:");
 		String studyDuration = (String)JSONPath.read(res, "$.user.studyDuration");
 		Integer ranking = (Integer)JSONPath.read(res, "$.user.ranking");
 		System.out.println("studyDuration="+studyDuration+","+"ranking="+ranking);
		Assert.assertNotEquals(studyDuration,null,"8.移动端-我的学时页-切换到子部门后我的排名接口"+res);
 	}
 	
 	
 	@Test(description="9.移动端-我的学时页-部门排行接口", priority=9)
 	public void testDeptClassHourRanking()  {
 		String res = HourRankingBusiness.DeptClassHourRanking(dept_idFirst);
 		System.out.println("9.移动端-我的学时页-部门排行接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
		Assert.assertNotEquals(total,0,"9.移动端-我的学时页-部门排行接口"+res);
 	}
 	
 	
 	@Test(description="10.移动端-我的学时页-部门排行切换到子部门接口", priority=10)
 	public void testDeptClassHourRankingDept()  {
 		String res = HourRankingBusiness.DeptClassHourRanking(dept_idSecond);
 		System.out.println("10.移动端-我的学时页-部门排行切换到子部门接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
		Assert.assertNotEquals(total,0,"10.移动端-我的学时页-部门排行切换到子部门接口"+res);
 	}
 	
 	
 	@Test(description="11.移动端-我的学分-学分详情有值接口", priority=11)
 	public void testGetScoreRecord()  {
 		String res = HourRankingBusiness.GetScoreRecord();
 		System.out.println("11.移动端-我的学分-学分详情接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
		Assert.assertNotEquals(total,null,"11.移动端-我的学分-学分详情接口"+res);
 	}
 	
 	
 	@Test(description="12.移动端-我的学分-学分详情无值接口", priority=12)
 	public void testGetScoreRecordNoData()  {
 		String res = HourRankingBusiness.GetScoreRecord();
 		System.out.println("11.移动端-我的学分-学分详情接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
		Assert.assertNotEquals(total,null,"11.移动端-我的学分-学分详情接口"+res);
 	}
 	
 	@Test(description="13.移动端-我的学分-今日学分有值接口", priority=13)
 	public void testScoreToday()  {
 		String res = HourRankingBusiness.ScoreToday();
 		System.out.println("13.移动端-我的学分-今日学分有值接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
		Assert.assertNotEquals(total,null,"13.移动端-我的学分-今日学分有值接口"+res);
 	}
 	
 	
 	@Test(description="14.移动端-我的学分-今日学分无值接口", priority=14)
 	public void testScoreTodayNoData()  {
 		String res = HourRankingBusiness.ScoreToday();
 		System.out.println("14.移动端-我的学分-今日学分无值接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
		Assert.assertNotEquals(total,null,"14.移动端-我的学分-今日学分无值接口"+res);
 	}
 	
 	
 	@Test(description="15.学时排行榜按钮开关-关闭接口", priority=15)
 	public void testSwitchSettingClose()  {
// 		String res = HourRankingBusiness.SwitchSetting(switch_id,type,"CLOSED",sys_actions);
// 		System.out.println("15.学时排行榜按钮开关-关闭接口:");
// 		String setting = (String)JSONPath.read(res, "$.setting");
// 		System.out.println("setting="+setting);
//		Assert.assertEquals(setting,"true","15.学时排行榜按钮开关-关闭接口"+res);
 	}
 	
 	
 	@Test(description="16.移动端-我的学时页-关闭排行榜入口-管理员权限-我的学时和排名接口", priority=16)
 	public void testMyUserClassHourRankingClose()  {
 		String res = HourRankingBusiness.MyUserClassHourRanking(dept_idFirst);
 		System.out.println("16.移动端-我的学时页-关闭排行榜入口-管理员权限-我的学时和排名接口:");
 		String studyDuration = (String)JSONPath.read(res, "$.user.studyDuration");
 		Integer ranking = (Integer)JSONPath.read(res, "$.user.ranking");
 		System.out.println("studyDuration="+studyDuration+","+"ranking="+ranking);
		Assert.assertNotEquals(studyDuration,null,"16.移动端-我的学时页-关闭排行榜入口-管理员权限-我的学时和排名接口"+res);
 	}
 	
 	
 	@Test(description="17.移动端-我的学时页-关闭排行榜入口-管理员权限-全公司的学员排行接口", priority=17)
 	public void testUsersClassHourRankingClose()  {
 		String res = HourRankingBusiness.MyUserClassHourRanking(dept_idFirst);
 		System.out.println("17.移动端-我的学时页-关闭排行榜入口-管理员权限-全公司的学员排行接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.list.total");
 		System.out.println("total="+total);
		Assert.assertNotEquals(total,0,"17.移动端-我的学时页-关闭排行榜入口-管理员权限-全公司的学员排行接口"+res);
 	}
 	
 	@Test(description="18.学时排行榜按钮开关-打开恢复数据接口", priority=18)
 	public void testSwitchSettingOpenAgain()  {
// 		String res = HourRankingBusiness.SwitchSetting(switch_id,type,"OPEN",sys_actions);
// 		System.out.println("18.学时排行榜按钮开关-打开恢复数据接口:");
// 		String setting = (String)JSONPath.read(res, "$.setting");
// 		System.out.println("setting="+setting);
//		Assert.assertEquals(setting,"true","18.学时排行榜按钮开关-打开恢复数据接口"+res);
 	}

 	

}
