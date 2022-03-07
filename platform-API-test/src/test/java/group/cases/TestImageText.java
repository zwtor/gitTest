package group.cases;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.group.a.business.ImageTextBusiness;
import cn.kxy.lecturer.business.AppLecturerBusiness;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.PhoneUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TestImageText {	
	public static String imagetext_id = "";
	public static String imagetext_draft_id = "";
	public static String imagetext_noTeacher_id = "";
	public static String imagetext_link_id = "";
	public static String concat_person_ids = "";
	public static String concat_person_exitsName_ids = "";
	public static String enterpriseId = EnterpriseData.getEnterpriseId();

	String title = "图文课" + CommonData.getStringRandom(5);
	String titletemp = "图文课编辑" + CommonData.getStringRandom(5);
	String teacherId = "";
 	@Test(description="1.创建图文课-发布接口", priority=1)
 	public void testImageTextAdd() throws UnsupportedEncodingException  {
 		
 		String res1 = AppLecturerBusiness.queryAppLecturersList("","");
 		teacherId = (String)JSONPath.read(res1, "$.list[0].id");
 		String title = "图文课" + CommonData.getStringRandom(5);
 		String baseCover = "https://oss.coolcollege.cn/1789917624419880960.png";
 		String contentJsonStr = "{\"blocks\":[{\"key\":\"85lj8\",\"text\":\"text test\",\"type\":\"unstyled\","
 				+ "\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}}";
 		String contentJson = JSONObject.parseObject(contentJsonStr).toJSONString();
 		String res = ImageTextBusiness.ImageTextAdd(baseCover,contentJson,"release",teacherId,title,"false","");
 		imagetext_id = (String) JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("1.创建图文课-发布接口:"+"code="+code+","+"imagetext_id="+imagetext_id);
 		Assert.assertEquals(msg, "新增课程成功","1.创建图文课-发布接口：" + res);
 	}
 	
 	@Test(description="2.创建图文课接口-缺少必填入参", priority=2)
 	public void testImageTextAddExeption() throws UnsupportedEncodingException  {
 		String title = "" ;
 		String baseCover = "https://oss.coolcollege.cn/1789917624419880960.png";
 		String contentJsonStr = "{\"blocks\":[{\"key\":\"85lj8\",\"text\":\"text test\",\"type\":\"unstyled\","
 				+ "\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}}";
 		String contentJson = JSONObject.parseObject(contentJsonStr).toJSONString();
 		String res = ImageTextBusiness.ImageTextAdd(baseCover,contentJson,"release",teacherId,title,"false","");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		String data = (String) JSONPath.read(res, "$.data");
 		System.out.println("2.创建图文课接口-缺少必填入参:"+"msg="+msg+","+"data="+data);
 		Assert.assertEquals(msg, "新增课程失败","2.创建图文课接口-缺少必填入参：" + res);
 	}
 	
 	@Test(description="3.创建图文课-无讲师接口", priority=3)
 	public void testImageTextAddNoTeacher() throws UnsupportedEncodingException  {
 		String title = "图文课" + CommonData.getStringRandom(5);
 		String baseCover = "https://oss.coolcollege.cn/1789917624419880960.png";
 		String contentJsonStr = "{\"blocks\":[{\"key\":\"85lj8\",\"text\":\"text test\",\"type\":\"unstyled\","
 				+ "\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}}";
 		String contentJson = JSONObject.parseObject(contentJsonStr).toJSONString();
 		String teacherId = "";
 		String res = ImageTextBusiness.ImageTextAdd(baseCover,contentJson,"release",teacherId,title,"false","");
 		imagetext_noTeacher_id = (String) JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("3.创建图文课接口-无讲师:"+"imagetext_noTeacher_id="+imagetext_noTeacher_id+","+"msg="+msg);
 		Assert.assertEquals(msg, "新增课程成功","3.创建图文课接口-无讲师：" + res);
 	}
 	
 	
 	@Test(description="28.创建图文课-有外链接口", priority=28)
 	public void testImageTextAddUrl() throws UnsupportedEncodingException  {
 		String title = "图文课有外链" + CommonData.getStringRandom(5);
 		String baseCover = "https://oss.coolcollege.cn/1789917624419880960.png";
 		String contentJsonStr = "{\"blocks\":[{\"key\":\"85lj8\",\"text\":\"text test\",\"type\":\"unstyled\","
 				+ "\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}}";
 		String contentJson = JSONObject.parseObject(contentJsonStr).toJSONString();
 		String url = "https://www.baidu.com";
 		String res = ImageTextBusiness.ImageTextAdd(baseCover,contentJson,"release",teacherId,title,"true",url);
 		imagetext_link_id = (String) JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("28.创建图文课-有外链接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "新增课程成功","28.创建图文课-有外链接口：" + res);
 	}
 	
 	
 	@Test(description="29.创建图文课-启用外链但未输入链接接口", priority=29)
 	public void testImageTextAddNoLink() throws UnsupportedEncodingException  {
 		String title = "图文课" + CommonData.getStringRandom(5);
 		String baseCover = "https://oss.coolcollege.cn/1789917624419880960.png";
 		String contentJsonStr = "{\"blocks\":[{\"key\":\"85lj8\",\"text\":\"text test\",\"type\":\"unstyled\","
 				+ "\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}}";
 		String contentJson = JSONObject.parseObject(contentJsonStr).toJSONString();
 		String res = ImageTextBusiness.ImageTextAdd(baseCover,contentJson,"release",teacherId,title,"true","");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		String data = (String)JSONPath.read(res, "$.data");
 		System.out.println("29.创建图文课-启用外链但未输入链接接口:"+"msg="+msg+","+"data="+data);
 		Assert.assertEquals(msg, "新增课程失败","29.创建图文课-启用外链但未输入链接接口：" + res);
 	}
 	
 	@Test(description="4.创建图文课-存草稿接口", priority=4)
 	public void testImageTextAddDraft() throws UnsupportedEncodingException  {
 		String title = "图文课" + CommonData.getStringRandom(5);
 		String baseCover = "https://oss.coolcollege.cn/1789917624419880960.png";
 		String contentJsonStr = "{\"blocks\":[{\"key\":\"85lj8\",\"text\":\"text test\",\"type\":\"unstyled\","
 				+ "\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}}";
 		String contentJson = JSONObject.parseObject(contentJsonStr).toJSONString();
 		String res = ImageTextBusiness.ImageTextAdd(baseCover,contentJson,"draft",teacherId,title,"false","");
 		imagetext_draft_id = (String) JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("4.创建图文课-存草稿接口:"+"code="+code+","+"imagetext_draft_id="+imagetext_draft_id);
 		Assert.assertEquals(msg, "保存草稿成功","4.创建图文课-存草稿接口：" + res);
 	}
 	
 	@Test(description="30.编辑图文课-关闭外链接口", priority=30)
 	public void testImageTextEditUrl() throws UnsupportedEncodingException  {
 		String baseCover = "https://oss.coolcollege.cn/1789917624419880960.png";
 		String contentJsonStr = "{\"blocks\":[{\"key\":\"85lj8\",\"text\":\"text test\",\"type\":\"unstyled\","
 				+ "\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}}";
 		String contentJson = JSONObject.parseObject(contentJsonStr).toJSONString();
 		title = titletemp;
 		String res = ImageTextBusiness.ImageTextEdit(baseCover,contentJson,teacherId,title,imagetext_id,"false","");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("30.编辑图文课-关闭外链接口:"+"code="+code+","+"msg="+msg);
 		Assert.assertEquals(msg, "编辑课程成功","30.编辑图文课-关闭外链接口：" + res);
 	}
 	
 	
 	@Test(description="5.编辑图文课-启用外链接口", priority=5)
 	public void testImageTextEdit() throws UnsupportedEncodingException  {
 		String baseCover = "https://oss.coolcollege.cn/1789917624419880960.png";
 		String contentJsonStr = "{\"blocks\":[{\"key\":\"85lj8\",\"text\":\"text test\",\"type\":\"unstyled\","
 				+ "\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}}";
 		String contentJson = JSONObject.parseObject(contentJsonStr).toJSONString();
 		title = titletemp;
 		String url = "https://www.baidu.com";
 		String res = ImageTextBusiness.ImageTextEdit(baseCover,contentJson,teacherId,title,imagetext_id,"true",url);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Integer code = (Integer) JSONPath.read(res, "$.code");
 		System.out.println("5.编辑图文课-启用外链接口:"+"code="+code+","+"msg="+msg);
 		Assert.assertEquals(msg, "编辑课程成功","5.编辑图文课-启用外链接口：" + res);
 	}
 	
 	@Test(description="6.已发布的图文课列表接口", priority=6)
 	public void testImageTextReleasedList(){
 		String res = ImageTextBusiness.ImageTextReleasedList("","","released");
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("6.已发布的图文课列表接口:"+"total="+total);
 		Assert.assertNotEquals(total, 0 ,"6.已发布的图文课列表接口：" + res);
 	}
 	
 	@Test(description="7.已发布的图文课列表-只看分享中的数据接口", priority=7)
 	public void testImageTextReleasedSharedList(){
 		String res = ImageTextBusiness.ImageTextReleasedList("","","released");
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("7.已发布的图文课列表-只看分享中的数据接口:"+"total="+total);
 		Assert.assertNotEquals(total, 0 ,"7.已发布的图文课列表-只看分享中的数据接口：" + res);
 	}

 	
 	@Test(description="8.按图文课名称查询接口", priority=8)
 	public void testImageTextByName() throws UnsupportedEncodingException{
 		String titleByName = URLEncoder.encode(String.valueOf(titletemp), "utf-8");
 		String res = ImageTextBusiness.ImageTextReleasedList(titleByName,"","released");
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("8.按图文课名称查询接口:"+"total="+total);
 		Assert.assertNotEquals(total, 0 ,"8.按图文课名称查询接口：" + res);
 	}
 	
 	@Test(description="9.未发布的图文课列表接口", priority=9)
 	public void testImageTextUnreleasedList(){
 		String res = ImageTextBusiness.ImageTextReleasedList("","","unreleased");
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("9.未发布的图文课列表接口:"+"total="+total);
 		Assert.assertNotEquals(total, 0 ,"9.未发布的图文课列表接口：" + res);
 	}
 	
 	@Test(description="10.添加联系人接口", priority=10)
 	public void testAddConcatperson(){
 		String res = ImageTextBusiness.AddConcatperson("汶灵芝",phone_num_02);
 		concat_person_ids = (String) JSONPath.read(res, "$.data.id");
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("10.添加联系人接口:"+"message="+message+"concat_person_ids="+concat_person_ids);
 		Assert.assertEquals(message, "success" ,"10.添加联系人接口：" + res);
 	}
 	String phone_num_01 = PhoneUtil.getPhoneNum();
 	String phone_num_02 = PhoneUtil.getPhoneNum();
 	@Test(description="11.添加联系人-姓名已存在接口", priority=11)
 	public void testAddConcatpersonExitsName(){
 		String res = ImageTextBusiness.AddConcatperson("汶灵芝",phone_num_01);
 		concat_person_exitsName_ids = (String) JSONPath.read(res, "$.data.id");
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("11.添加联系人接口-姓名已存在:"+"message="+message+"concat_person_exitsName_ids="+concat_person_exitsName_ids);
 		Assert.assertEquals(message, "success" ,"11.添加联系人接口-姓名已存在：" + res);
 	}
 	
 	@Test(description="12.添加联系人接口-手机号已存在", priority=12)
 	public void testAddConcatpersonExitsPhone(){
 		String res = ImageTextBusiness.AddConcatperson("汶测试",phone_num_02);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("12.添加联系人接口-手机号已存在:"+"message="+message);
 		Assert.assertEquals(message, "电话号码已存在" ,"12.添加联系人接口-手机号已存在：" + res);
 	}
 	
 		
 	@Test(description="13.查询联系人接口", priority=13)
 	public void testGroupUserInfo(){
 		String res = ImageTextBusiness.GroupUserInfo();
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("13.查询联系人接口:"+"message="+message);
 		Assert.assertEquals(message, "success" ,"13.查询联系人接口：" + res);
 	}
 	
 	@Test(description="14.置顶图文课", priority=14)
 	public void testRecommendSet(){
 		String res = ImageTextBusiness.RecommendSet(imagetext_id);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("14.置顶图文课:"+"msg="+msg);
 		Assert.assertTrue(msg.contains("re") ,"14.置顶图文课：" + res);
 	}
 	
 	@Test(description="15.取消置顶图文课", priority=15)
 	public void testRecommendRemove(){
 		String res = ImageTextBusiness.RecommendRemove(imagetext_id);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("15.取消置顶图文课:"+"msg="+msg);
 		Assert.assertEquals(msg, "recommend sucess" ,"15.取消置顶图文课：" + res);
 	}
 	
 	@Test(description="16.图文课发布接口", priority=16)
 	public void testCourseRelease(){
 		String res = ImageTextBusiness.CourseCancel(imagetext_draft_id,"release");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("16.图文课发布接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "发布成功" ,"16.图文课发布接口：" + res);
 	}
 	
 	@Test(description="17.图文课分享接口", priority=17)
 	public void testAddShare() throws UnsupportedEncodingException{
 		String share_image_url = "https://oss.coolcollege.cn/1794903518562357248.png";
 		String res = ImageTextBusiness.AddShare(imagetext_id,"TRUE","TRUE","customer_training","wx468a3ff4a7dbb5d0",share_image_url,
 				concat_person_ids,"FALSE",enterpriseId);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("17.图文课分享接口:"+"message="+message);
 		Assert.assertEquals(message, "success" ,"17.图文课分享接口：" + res);
 	}
 	
 	
 	@Test(description="18.图文课分享-已经分享过的图文课接口", priority=18)
 	public void testAddShareShared() throws UnsupportedEncodingException{
 		String share_image_url = "https://oss.coolcollege.cn/1794903518562357248.png";
 		String res = ImageTextBusiness.AddShare(imagetext_id,"TRUE","TRUE","customer_training","wx468a3ff4a7dbb5d0",share_image_url,
 				concat_person_ids,"FALSE",enterpriseId);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("18.图文课分享-已经分享过的图文课接口:"+"message="+message);
 		Assert.assertEquals(message, "add failed:{}" ,"18.图文课分享-已经分享过的图文课接口：" + res);
 	}
 	
 	@Test(description="19.图文课数据监控-学员学习数据接口", priority=19)
 	public void testSelectImageTextRecord(){
 		String res = ImageTextBusiness.SelectImageTextRecord(imagetext_id);
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("19.图文课数据监控-学员学习数据接口:"+"total="+total);
 		Assert.assertNotEquals(total, null ,"19.图文课数据监控-学员学习数据接口：" + res);
 	}
 	
 	
 	@Test(description="20.图文课数据监控-客户学习数据接口", priority=20)
 	public void testStudyInfo(){
 		String res = ImageTextBusiness.StudyInfo(imagetext_id);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("20.图文课数据监控-客户学习数据接口:"+"message="+message);
 		Assert.assertEquals(message, "success" ,"20.图文课数据监控-客户学习数据接口：" + res);
 	}
 	
 	
 	@Test(description="21.图文课数据监控-报名管理接口", priority=21)
 	public void testListApply(){
 		String res = ImageTextBusiness.ListApply(imagetext_id);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("21.图文课数据监控-报名管理接口:"+"message="+message);
 		Assert.assertEquals(message, "success" ,"21.图文课数据监控-报名管理接口：" + res);
 	}
 	
 	
 	
 	
 	
 	
 	
 	@Test(description="24.图文课取消发布接口", priority=24)
 	public void testCourseCancel(){
 		String res = ImageTextBusiness.CourseCancel(imagetext_id,"cancel");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("24.图文课取消发布接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "取消发布成功" ,"24.图文课取消发布接口：" + res);
 	}
 	
 	@Test(description="25.图文课取消发布-存为草稿发布后再取消发布接口", priority=25)
 	public void testCourseCancelGraft(){
 		String res = ImageTextBusiness.CourseCancel(imagetext_draft_id,"cancel");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("25.图文课取消发布-存为草稿发布后再取消发布接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "取消发布成功" ,"25.图文课取消发布-存为草稿发布后再取消发布接口：" + res);
 	}
 	
 	@Test(description="26.图文课取消发布-无讲师接口", priority=26)
 	public void testCourseCancelNoTeacher(){
 		String res = ImageTextBusiness.CourseCancel(imagetext_noTeacher_id,"cancel");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("26.图文课取消发布-无讲师接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "取消发布成功" ,"26.图文课取消发布-无讲师接口：" + res);
 	}
 	
 	@Test(description="27.图文课取消发布-图文课已被取消接口", priority=27)
 	public void testCourseCancelCanceled(){
 		String res = ImageTextBusiness.CourseCancel(imagetext_id,"cancel");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("27.图文课取消发布-图文课已被取消接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "course status is not release" ,"27.图文课取消发布-图文课已被取消接口：" + res);
 	}
 	
// 	@Test(description="28.图文课取消发布-图文课已被引用接口", priority=28)
// 	public void testCourseCancelUsed(){
// 		String res = ImageTextBusiness.CourseCancel(imagetext_id);
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("28.图文课取消发布-图文课被引用接口:"+"msg="+msg);
// 		Assert.assertEquals(msg, "course status is not release" ,"28.图文课取消发布-图文课被引用接口：" + res);
// 	}
 	
// 	@Test(description="29.图文课发布接口", priority=29)
// 	public void testCourseCancelUsed(){
// 		String res = ImageTextBusiness.CourseCancel(imagetext_id);
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("29.图文课发布接口:"+"msg="+msg);
// 		Assert.assertEquals(msg, "course status is not release" ,"29.图文课发布接口：" + res);
// 	}
 	
 	@Test(description="33.图文课删除接口", priority=33)
 	public void testCourseDelete(){
 		String res = ImageTextBusiness.CourseDelete(imagetext_id);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("33.图文课删除接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "删除课程成功" ,"33.图文课删除接口：" + res);
 	}
 	
 	@Test(description="34.图文课删除-存为草稿发布后再取消发布再删除接口", priority=34)
 	public void testCourseDeleteDraft(){
 		String res = ImageTextBusiness.CourseDelete(imagetext_draft_id);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("34.图文课删除-存为草稿发布后再取消发布再删除接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "删除课程成功" ,"34.图文课删除-存为草稿发布后再取消发布再删除接口：" + res);
 	}
 	
 	@Test(description="35.图文课删除-无讲师接口", priority=35)
 	public void testCourseDeleteNoTeacher(){
 		String res = ImageTextBusiness.CourseDelete(imagetext_noTeacher_id);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("35.图文课删除-无讲师接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "删除课程成功" ,"35.图文课删除-无讲师接口：" + res);
 	}
 	
 	
 	@Test(description="36.图文课删除-有外链接口", priority=36)
 	public void testCourseDeleteUrl(){
 		String res = ImageTextBusiness.CourseDelete(imagetext_link_id);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("36.图文课删除-有外链接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "删除课程成功" ,"36.图文课删除-有外链接口：" + res);
 	}
 	
 	@Test(description="37.图文课删除-图文课已被删除接口", priority=37)
 	public void testCourseDeleted(){
 		String res = ImageTextBusiness.CourseDelete(imagetext_id);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("37.图文课删除-图文课已被删除接口:"+"message"+message);
 		Assert.assertEquals(message, "Course has been deleted" ,"37.图文课删除-图文课已被删除接口：" + res);
 	}
 	
 	@Test(description="38.删除联系人接口", priority=38)
 	public void testDeleteConcatPersonInfo(){
 		String res = ImageTextBusiness.DeleteConcatPersonInfo(concat_person_ids);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("38.删除联系人接口:"+"message="+message);
 		Assert.assertEquals(message, "success" ,"38.删除联系人接口：" + res);
 	}
 	
 	@Test(description="39.删除联系人-添加的已存在姓名的删除接口", priority=39)
 	public void testDeleteConcatPersonInfoExitsName(){
 		String res = ImageTextBusiness.DeleteConcatPersonInfo(concat_person_exitsName_ids);
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("39.删除联系人-添加的已存在姓名的删除接口:"+"message="+message);
 		Assert.assertEquals(message, "success" ,"39.删除联系人-添加的已存在姓名的删除接口：" + res);
 	}
}
