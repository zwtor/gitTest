package group.cases;

import cn.kxy.group.a.business.LecturerBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestLecturer {	
	public static String lever_id = "";
	public static String outside_lever_id = "";
	
	String name = "讲师等级测试" + CommonData.getStringRandom(5);
	//String username = "内部讲师" + CommonData.getStringRandom(5);
	String username = UserBusiness.getUsername();

 	@Test(description="1.创建内部讲师等级接口", priority=1)
 	public void testLecturerLeverAdd()  {
 		String res = LecturerBusiness.LecturerLeverAdd(name,"test description","inside","1");
 		System.out.println("1.创建内部讲师等级接口:");
 		lever_id = (String) JSONPath.read(res, "$.id");
 		System.out.println("lever_id="+lever_id);
 		Assert.assertNotEquals(lever_id,null,"1.创建内部讲师等级接口：" + res);
 	}
 	
 	 	
 	@Test(description="2.创建内部讲师等级-等级名已存在接口", priority=2)
 	public void testLecturerLeverAddExits()  {
 		String res = LecturerBusiness.LecturerLeverAdd(name,"test description","inside","1");
 		System.out.println("2.创建内部讲师等级-等级名已存在接口:");
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("message="+message);
 		Assert.assertEquals(message,"level name already exist","2.创建内部讲师等级-等级名已存在接口：" + res);
 	}
 	
 	
 	public static String exits_lever_id = "";
 	@Test(description="3.创建内部讲师等级-不传部分参数接口", priority=3)
 	public void testLecturerLeverAddParams()  {
 		String name = "不传部分参数";
 		String res = LecturerBusiness.LecturerLeverAdd(name,"","inside","");
 		System.out.println("3.创建内部讲师等级-不传部分参数接口:");
 		exits_lever_id = (String) JSONPath.read(res, "$.id");
 		System.out.println("exits_lever_id="+exits_lever_id);
 		Assert.assertNotEquals(exits_lever_id,null,"3.创建内部讲师等级-不传部分参数接口：" + res);
 	}
 	
 	
 	
 	@Test(description="4.创建外部讲师等级接口", priority=4)
 	public void testLecturerLeverAddOutside()  {
 		String nameTemp = name + "外部";
 		System.out.println("4.创建外部讲师等级接口:");
 		String res = LecturerBusiness.LecturerLeverAdd(nameTemp,"test description","inside","1");
 		outside_lever_id = (String) JSONPath.read(res, "$.id");
 		System.out.println("outside_lever_id="+outside_lever_id);
 		Assert.assertNotEquals(outside_lever_id,null,"4.创建外部讲师等级接口：" + res);
 	}
 	
 	
 	@Test(description="5.编辑内部讲师等级接口", priority=5)
 	public void testLecturerLeverEdit()  {
 		String nameTemp = name + "编辑";
 		System.out.println("5.编辑内部讲师等级接口:");
 		String res = LecturerBusiness.LecturerLeverEdit(lever_id,nameTemp,"inside");
 		String id = (String) JSONPath.read(res, "$.id");
 		System.out.println("id="+id);
 		Assert.assertNotEquals(id,null,"5.编辑内部讲师等级接口：" + res);
 	}
 	
 	
 	
 	@Test(description="6.讲师等级列表查询接口", priority=6)
 	public void testLecturerLeverList()  {
 		System.out.println("6.讲师等级列表查询接口:");
 		String res = LecturerBusiness.LecturerLeverList(name,"");
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotEquals(total,0,"6.讲师等级列表查询接口：" + res);
 	}
 	
 	
 	
 	@Test(description="7.讲师等级列表-按类型查询接口", priority=7)
 	public void testLecturerLeverListQuery()  {
 		System.out.println("7.讲师等级列表-按类型查询接口:");
 		String res = LecturerBusiness.LecturerLeverList(name,"inside");
 		String resTemp = LecturerBusiness.LecturerLeverList(name,"outside");
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotEquals(total,0,"7.讲师等级列表-按类型查询接口：" + res);
 	}
 	
 	
 	
 	@Test(description="8.讲师等级列表-查看接口", priority=8)
 	public void testQueryLecturerLever()  {
 		System.out.println("8.讲师等级列表-查看接口:");
 		String res = LecturerBusiness.QueryLecturerLever(lever_id);
 		String id = (String) JSONPath.read(res, "$.id");
 		System.out.println("id="+id);
 		Assert.assertNotEquals(id,null,"8.讲师等级列表-查看接口：" + res);
 	}
 	
 	
 	public static String lecturer_id = "";
 	@Test(description="9.新建内部讲师接口", priority=9)
 	public void testAddLecturers()  {
 		System.out.println("9.新建内部讲师接口:");
 		String avatar = "https://static-legacy.dingtalk.com/media/lADPGoGu-6DKjdnNAcvNAcs_459_459.jpg";
 		long current_time = System.currentTimeMillis();
 		String start_timeTemp = String.valueOf(current_time);
		String res_username = LecturerBusiness.GetLecturter(username);
	 	lecturer_id = (String) JSONPath.read(res_username, "$.list[0].id");
	 	if(lecturer_id == null) {
	 		String res = LecturerBusiness.AddLecturers(username,avatar,"inside",lever_id,start_timeTemp);
	 		lecturer_id = (String) JSONPath.read(res, "$.id");
	 	}
	 	else{
	 		//lecturer_name = username;
	 		System.out.println("username="+username+","+"lecturer_id="+lecturer_id);
	 	}	
 		Assert.assertNotEquals(lecturer_id,null,"9.新建内部讲师接口：" + res_username);
 	}
 	
 	
 	@Test(description="10.新建内部讲师-讲师已存在接口", priority=10)
 	public void testAddLecturersExits()  {
 		System.out.println("10.新建内部讲师-讲师已存在接口:");
// 		String avatar = "https://static-legacy.dingtalk.com/media/lADPGoGu-6DKjdnNAcvNAcs_459_459.jpg";
// 		long current_time = System.currentTimeMillis();
// 		String start_timeTemp = String.valueOf(current_time);
// 		String res = LecturerBusiness.AddLecturers(username,avatar,"inside",lever_id,start_timeTemp);
// 		String message = (String) JSONPath.read(res, "$.message");
// 		System.out.println("res="+res+","+"message="+message);
// 		Assert.assertEquals(message,"inside lecturer already exist","10.新建内部讲师-讲师已存在接口：" + res);
 	}
 	
 	
 	
 	@Test(description="11.讲师列表查询接口", priority=11)
 	public void testQueryLecturers()  {
 		System.out.println("11.讲师列表查询接口:");
 		String res = LecturerBusiness.QueryLecturers("","","","","");
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotEquals(total,null,"11.讲师列表查询接口：" + res);
 	}
 	
 	
 	@Test(description="12.讲师列表-按讲师名查询接口", priority=12)
 	public void testQueryLecturersName()  {
 		System.out.println("12.讲师列表-按讲师名查询接口:");
 		String res = LecturerBusiness.QueryLecturers(username,"","","","");
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotEquals(total,null,"12.讲师列表-按讲师名查询接口：" + res);
 	}
 	
 	
 	@Test(description="13.讲师列表-按等级查询接口", priority=13)
 	public void testQueryLecturersLever()  {
 		System.out.println("13.讲师列表-按等级查询接口:");
 		String res = LecturerBusiness.QueryLecturers("","",lever_id,"","");
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotEquals(total,null,"13.讲师列表-按等级查询接口：" + res);
 	}
 	
 	
 	@Test(description="14.讲师列表-按状态查询接口", priority=14)
 	public void testQueryLecturersStatus()  {
 		System.out.println("14.讲师列表-按状态查询接口:");
 		String res = LecturerBusiness.QueryLecturers("","","","","");//全部状态
 		String res_on = LecturerBusiness.QueryLecturers("","1","","","");//已启用状态
 		String res_off = LecturerBusiness.QueryLecturers("","0","","","");//已停用状态
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotEquals(total,null,"14.讲师列表-按状态查询接口：" + res);
 	}
 	
 	
 	@Test(description="15.讲师列表-按时间查询接口", priority=15)
 	public void testQueryLecturersTime()  {
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd");
 		String beginTime = date_temp.format(new Date());
 		Calendar calendar = Calendar.getInstance();
 		calendar.add(Calendar.DATE, -7);
 		String endTime = date_temp.format(calendar.getTime());
 		System.out.println("15.讲师列表-按时间查询接口:");
 		String res = LecturerBusiness.QueryLecturers("","","",endTime,beginTime);
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotEquals(total,null,"15.讲师列表-按时间查询接口：" + res);
 	}
 		 	
 	
 	
 	@Test(description="16.讲师列表-编辑接口", priority=16)
 	public void testUpdateLecturer()  {
 		System.out.println("16.讲师列表-编辑接口:");
 		String avatar = "https://static-legacy.dingtalk.com/media/lADPGoGu-6DKjdnNAcvNAcs_459_459.jpg";
 		long current_time = System.currentTimeMillis();
 		String start_timeTemp = String.valueOf(current_time);
 		String res = LecturerBusiness.UpdateLecturer(lecturer_id,username,avatar,"inside",lever_id,start_timeTemp);
 		String id = (String) JSONPath.read(res, "$.id");
 		System.out.println("id="+id);
 		Assert.assertEquals(id,lecturer_id,"16.讲师列表-编辑接口：" + res);
 	}
 	
 	
 	
 	@Test(description="17.讲师列表-开启/停用接口", priority=17)
 	public void testUpdateStatus()  {
 		System.out.println("17.讲师列表-开启/停用接口:");
 		String res_off = LecturerBusiness.UpdateStatus(lecturer_id,"0");//停用讲师
 		String res_on = LecturerBusiness.UpdateStatus(lecturer_id,"1");//启用讲师
 		String update_off = (String) JSONPath.read(res_off, "$.update");
 		String update_on = (String) JSONPath.read(res_on, "$.update");
 		System.out.println("update_off="+update_off+","+"update_on="+update_on);
 		Assert.assertEquals(update_off,"true","17.讲师列表-开启/停用接口：" + res_off);
 		Assert.assertEquals(update_on,"true","17.讲师列表-开启/停用接口：" + res_on);
 	}
 	
 	
 	public static String course_id = "";
 	@Test(description="18.查询课程列表接口", priority=18)
 	public void testQueryCourseByPage()  {
 		System.out.println("18.查询课程列表接口:");
 		String res = LecturerBusiness.QueryCourseByPage();
 		course_id = (String) JSONPath.read(res, "$.list[0].id");
 		System.out.println("course_id="+course_id);
 		Assert.assertNotEquals(course_id,null,"18.查询课程列表接口：" + res);
 	}
 	
 	
 	
 	@Test(description="20.讲师关联课程接口", priority=20)
 	public void testCourse()  {
 		System.out.println("20.讲师关联课程接口:");
 		String res = LecturerBusiness.Course(course_id,username);
 		String update = (String) JSONPath.read(res, "$.update");
 		System.out.println("update="+update);
 		Assert.assertEquals(update,"true","20.讲师关联课程接口：" + res);
 	}
 		
 	
 	
 	@Test(description="21.讲师列表-查看-基本信息接口", priority=21)
 	public void testBaseInfo()  {
 		System.out.println("21.讲师列表-查看-基本信息接口:");
 		String res = LecturerBusiness.BaseInfo(lecturer_id);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg,"ok","21.讲师列表-查看-基本信息接口：" + res);
 	}
 	
 	
 	
 	@Test(description="22.讲师列表-查看-授课明细接口", priority=22)
 	public void testCoursesDetail()  {
 		System.out.println("22.讲师列表-查看-授课明细接口:");
 		String res = LecturerBusiness.CoursesDetail(lecturer_id,"","","");
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotEquals(total,null,"22.讲师列表-查看-授课明细接口：" + res);
 	}
 	
 	
 	@Test(description="23.讲师列表-查看-授课明细-按时间筛选接口", priority=23)
 	public void testCoursesDetailTime()  {
 		System.out.println("23.讲师列表-查看-授课明细-按时间筛选接口:");
 		long current_time = System.currentTimeMillis();
 		long endTemp = current_time + 24L*(-7)*3600*1000; 
 		String start_timeTemp = String.valueOf(current_time);
 		String end_timeTemp = String.valueOf(endTemp);
 		String res = LecturerBusiness.CoursesDetail(lecturer_id,end_timeTemp,start_timeTemp,"");
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotEquals(total,null,"23.讲师列表-查看-授课明细-按时间筛选接口：" + res);
 	}
 	
 	
 	
 	@Test(description="24.讲师列表-查看-授课明细-按类型接口", priority=24)
 	public void testCoursesDetailType()  {
 		System.out.println("24.讲师列表-查看-授课明细-按类型接口:");
 		String res = LecturerBusiness.CoursesDetail(lecturer_id,"","","0");//课程类型
 		String res1 = LecturerBusiness.CoursesDetail(lecturer_id,"","","2");//微课类型
 		String res2 = LecturerBusiness.CoursesDetail(lecturer_id,"","","3");//学习项目类型
 		String res3 = LecturerBusiness.CoursesDetail(lecturer_id,"","","6");//图文课类型
 		String res4 = LecturerBusiness.CoursesDetail(lecturer_id,"","","11");//线下课类型
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotEquals(total,null,"24.讲师列表-查看-授课明细-按类型接口：" + res);
 	}
 	
 	
 	
 	@Test(description="25.讲师列表-查看-积分明细接口", priority=25)
 	public void testScoreDetail()  {
 		long current_time = System.currentTimeMillis();
 		long endTemp = current_time + 24L*(-7)*3600*1000; 
 		String start_timeTemp = String.valueOf(current_time);
 		String end_timeTemp = String.valueOf(endTemp);
 		System.out.println("25.讲师列表-查看-积分明细接口:");
 		String res = LecturerBusiness.ScoreDetail("","");//无筛选条件
 		String res_time = LecturerBusiness.ScoreDetail(end_timeTemp,start_timeTemp);//按时间筛选积分明细
 		String msg = (String) JSONPath.read(res, "$.msg");
 		String msg_time = (String) JSONPath.read(res_time, "$.msg");
 		System.out.println("msg="+msg+","+"msg_time="+msg_time);
 		Assert.assertEquals(msg,"ok","25.讲师列表-查看-积分明细接口：" + res);
 		Assert.assertEquals(msg_time,"ok","25.讲师列表-查看-积分明细接口：" + res);
 	} 	
 	
 	
 	@Test(description="26.讲师列表-查看-课酬明细接口", priority=26)
 	public void testClassPay()  {
 		long current_time = System.currentTimeMillis();
 		long endTemp = current_time + 24L*(-7)*3600*1000; 
 		String start_timeTemp = String.valueOf(current_time);
 		String end_timeTemp = String.valueOf(endTemp);
 		System.out.println("26.讲师列表-查看-课酬明细接口:");
 		String res = LecturerBusiness.ClassPay(lecturer_id,"","");
 		String res_time = LecturerBusiness.ClassPay(lecturer_id,end_timeTemp,start_timeTemp);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg,"ok","26.讲师列表-查看-课酬明细接口：" + res);
 	}
 	
 	
 	@Test(description="27.讲师列表-查看-积分明细-导出接口", priority=27)
 	public void testExportBaseInfoScore()  {
 		System.out.println("27.讲师列表-查看-积分明细-导出接口:");
 		String res_course = LecturerBusiness.ExportBaseInfo(lecturer_id,"TEACHING");//授课明细导出
 		String res_score = LecturerBusiness.ExportBaseInfo(lecturer_id,"SCROE");//积分明细导出
 		String res_classpay = LecturerBusiness.ExportBaseInfo(lecturer_id,"CLASSPAY");//课酬明细导出
 		String msg_course = (String) JSONPath.read(res_course, "$.msg");
 		String msg_score = (String) JSONPath.read(res_score, "$.msg");
 		String msg_classpay = (String) JSONPath.read(res_classpay, "$.msg");
 		System.out.println("msg_course="+msg_course+","+"msg_score="+msg_score+","+"msg_classpay="+msg_classpay);
 		Assert.assertEquals(msg_course,"讲师授课明细导出成功","27.讲师列表-查看-授课明细-导出接口：" + res_course);
 		Assert.assertEquals(msg_score,"讲师积分明细导出成功","27.讲师列表-查看-积分明细-导出接口：" + res_score);
 		Assert.assertEquals(msg_classpay,"讲师课酬明细导出成功","27.讲师列表-查看-积分明细-导出接口：" + res_classpay);
 	}
 	
 	
 	
 	@Test(description="28.讲师列表导出接口", priority=28)
 	public void testLectureExport()  {
 		System.out.println("28.讲师列表导出接口:");
 		String res = LecturerBusiness.LectureExport(username);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg,"讲师列表汇总明细导出成功","28.讲师列表导出接口：" + res);
 	}
 	
 	
 	
 	@Test(description="29.讲师列表-明细记录导出接口", priority=29)
 	public void testExportInfo()  {
 		System.out.println("29.讲师列表-明细记录导出接口:");
 		String res_course = LecturerBusiness.ExportInfo("TEACHING",username);//授课记录
 		String res_score = LecturerBusiness.ExportInfo("SCROE",username);//积分记录
 		String res_classpay = LecturerBusiness.ExportInfo("CLASSPAY",username);//课酬记录
 		String msg_course = (String) JSONPath.read(res_course, "$.msg");
 		String msg_score = (String) JSONPath.read(res_score, "$.msg");
 		String msg_classpay = (String) JSONPath.read(res_classpay, "$.msg");
 		System.out.println("msg_course="+msg_course+","+"msg_score="+msg_score+","+"msg_classpay="+msg_classpay);
 		Assert.assertEquals(msg_course,"讲师授课明细导出成功","29.讲师列表-明细记录导出接口：" + res_course);
 		Assert.assertEquals(msg_score,"讲师积分明细导出成功","29.讲师列表-明细记录导出接口：" + res_score);
 		Assert.assertEquals(msg_classpay,"讲师课酬明细导出成功","29.讲师列表-明细记录导出接口：" + res_classpay);
 	}
 	
 	
 	@Test(description="30.讲师状态未停用直接删除接口", priority=30)
 	public void testDeleteEnabled()  {
 		System.out.println("30.讲师状态未停用直接删除接口:");
 		String res = LecturerBusiness.DeleteLecturer(lecturer_id);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("message="+message);
 		Assert.assertEquals(message,"lecturer status is enabled, can not delete","30.讲师状态未停用直接删除接口：" + res);
 	}
 	
 	
// 	@Test(description="31.删除讲师前先停用接口", priority=31)
// 	public void testUpdateStatusOff()  {
// 		System.out.println("31.删除讲师前先停用接口:");
// 		String res = LecturerBusiness.UpdateStatus(lecturer_id,"0");
// 		String update = (String) JSONPath.read(res, "$.update");
// 		System.out.println("update="+update);
// 		Assert.assertEquals(update,"true","31.讲师列表-开启/停用接口：" + res);
// 	}
 	
 	
// 	@Test(description="32.讲师删除接口", priority=32)
// 	public void testDeleteLecturer()  {
// 		System.out.println("32.讲师删除接口:");
// 		String res = LecturerBusiness.DeleteLecturer(lecturer_id);
// 		String delete = (String) JSONPath.read(res, "$.deleted");
// 		System.out.println("delete="+delete);
// 		//Assert.assertEquals(delete,"true","32.讲师删除接口：" + res);
// 	}
 	
 	
 	
 	@Test(description="33.讲师等级删除接口", priority=33)
 	public void testDeleteLecturerLever()  {
 		System.out.println("33.讲师等级删除接口:");
 		String res = LecturerBusiness.DeleteLecturerLever(lever_id);
 		String res_outside = LecturerBusiness.DeleteLecturerLever(outside_lever_id);
	    String res_exits = LecturerBusiness.DeleteLecturerLever(exits_lever_id);
 		String delete = (String) JSONPath.read(res, "$.deleted");
 		System.out.println("delete="+delete);
 		Assert.assertEquals(delete,"true","33.讲师等级删除接口：" + res);
 	}
 	
 	
 	
}
