package group.a.cases;

import cn.kxy.group.a.business.StudyPayBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestStudyPay {	
	public static String StudyPlan_id = "";
	public static String projectCourse_id = "";
	public static String project_id = "";
	public static String officialPrice_project_id = "";
	public static String officialPrice_projectCourse_id = "";
	public static String auditProject_id = "";
	public static String auditProjectCourse_id = "";
	public static Integer Limit_count = 10;
	public static String enroll_id = "";


	String studyPlan_title = "学习任务付费" + CommonData.getStringRandom(5);
	String classification_id = ClassificationBusines.getPrimaryId();
	String title_project = "学习项目付费" + CommonData.getStringRandom(5);
	
	
	@Test(description="1.支付账户设置创建-支付宝接口", priority=1)
 	public void testAccountInfo()  {
		String enterprise_app_id = "2017092508925723";
		String enterprise_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCRj7yAZ+Z0i77Wa4dUkNF5MHf0EwbbxiZYJr+gIclMYY+HM+ORm3A7ZKvcBDWrVS0JT7oNWPP70RtytvjGuv2EORzSP1g2Cq5iU6sqAwGu8PHiQIo7NfNTVXokf9MeI603Y7xGIKpx97MJsVjlGJ5hj2W9A/aq1YD+BfDc1KDnEPOBOzhMma1NNAZHkaXkkIoMvuAIa0jRJf1m3CK28XfK8DCpHQSwV9Psb7PfkzRwP+ak79rpjUmXH2ktBGUGXjo2IZ1AkqtRcCMLEdebG6oZqLf9m0X+cnAHlrstXCjZNsdisJ7Ltt+G2be4jNIgCfaOs395tXNz9/DZbES4lwITAgMBAAECggEAb8XDpYYi/FNikxu2q0UY1MpXEeCaX6BkSURX2QqYnEI+CD/b+y80mybOoP/Khmm9T20dHKq1jyQew5OkrFVh3mpMq1Vg0v6YsYA16uisHj1+P5H3k0f2NINqaySMZJRYqjO3mtHyj9j4bfT0odapp5iJMv8cJkoT5vuA3NGZTFQVEDeAr6xSs9BNbRzYrZFLS71LwMrCA0TuAKecofJvQMABN6cS99Eh3v9MqYReXJOC2iZZa7vJxinwgiA7E5oePTEv8cl/bcFGIx21y+/OLZEdgvGWTB+KL2qE+4d0dNmiSXjmYsFBPopH5CfcQ+Dsi3wYM4KOAHFlAK2PI4aOYQKBgQDtdI4t+Kmr6PipqX35h2Dl1j/VbaogukG/Vym6CaOm72DT5+hm0w2SLwj1Ve8F6HDHvwRWgb5rxczjtOTGcbSEUc+FJmW1PPQbTH5GrhWKq9312COjkmHa3sI9ug34RfO8UF/obG+JLS9V5ARXoNeykDfOokZl2cJwVzZNgXyZJwKBgQCc7fKGUDVr1PxrLfelESz95vkCwoW5yst+G/VophCVCct18oxdaDoH3ntBx27znimCk0J8w6x9pHTGJ9ioy9yCrdoDk4EYOl6Bcdgnsn6X5zu8aGrURDGDufhCkqOsIKu9JIfwHzl67/BjQzU7az/GTS/Ay+cZeG8kE6qpyPhrNQKBgDKhhad7vAHx3QOgJkJMgXbAZkAF8B6mWFqKJVWLtJ9lr0m/yygRnHUtHkXhmuOxmkqdCjm/1HB6bqOm8Jj3qbwFDbCgbVNluc8QUmSTvfghiWRgP8ki70aR9OzgfRYRJFVk9e5DIM0pG0y/H8Xnf9aNNbkTlNsmwXTew69nCsBFAoGAHWoMV1iZasjVN1G6ZVo+0KiGbBoB7r0wP0cGTMMzwHvBgy4fWTK8DdWJzN6Q0WEH7s2jXLdgkXxN1waI+bqcbnIftyPiwqPOKv0nwukWCWryK3J286qEjWhCPFg4DX9iMWWzhTKVF5H5wOFMDQMhRQ4asUyG2KNWUbDOAF0HEzECgYBTPp+0hU7pIGVdXEQHDOksZHfA+MqFrI/VoDZhjYOMEoUhod6NQWVrw56YtJkYqq8Mcu0T8H4ZHCdzSoMDWXVhUMocCJod7eIC4dBCFxIituvIaYWkzCkg59KWa+ezZuMEg+tUYRD3YTaBVHWafsa9qRKn7CzXGzx/MnL6TTPvtA==";
		String enterprise_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm+iAr2ZRiA5X5KSmaXgusOZDhMxrSOOOyi20Wjd8JoxZyvJlE3TRc7ENiElMkx8lhx3wsZq/E+7P7dpdK+FxgS6qgfeErGusmRMfVJa1XvnqjXSqFjX0TQaUgpoBw3QQAOqRUPVktr+8z7K8MvEOexin+PtOM5vSFkm1oRMKr1Pe33O0M52DRy78h9CgMDbUBBelupSweVM1pmA/mixkoRwdA+ElRpnd7U2of4SzH054Qr6Y1EywR8OtQGv8Pr33x/up15FcbqGxKvG1nzrxWYHsT1Tuv15GdHNSV7vaSaZ5ikzkzzMQWlotz5ODt/9phUDicrzDw74RjRNRr+lq9QIDAQAB";
 		String res = StudyPayBusiness.AccountInfo(enterprise_app_id,enterprise_private_key,enterprise_public_key);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("1.支付账户设置创建-支付宝接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "ok","1.支付账户设置创建-支付宝接口：" + res);
 	}
	
	
	@Test(description="2.支付账户设置创建-微信接口", priority=2)
 	public void testAccountInfoCheck()  {
		String app_id = "wx0d388708592b3b8e";
		String mch_id = "1607558211";
		String mch_serial_no = "40BD2C9B8874DFC45E32E3F6A003872048ACF964";
		String api_v3_Key = "f54451e583cb11eb88f66c92bf5c1234";
		String private_key = "-----BEGIN PRIVATE KEY----- MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCpYLQVYPSXEu1+ FPgdSRTj5TsJ+/fG+L1FxhlxJsffsrlc1e71ZLlBxG5xNLmNzXXirMj/OMXMWtO4 VX+nEBTdOE7/LohitNVdW3JvVWPIhnKMoyg4OGtEWHQsY06hjqv97sjBFwsVD8Gz UwnUrATbjOzFNQEePzXf7pFku+ARkW80xQ6beDhBfoaFjRXiO5DMscV6VeWC7hr7 FQPjeZt7C78RzfG4Bwhou0HlCN0nBaDxZUp4Pg2Lu2gzGU6ozZN0FHMsLywOPdHO wr1Zkg8hez8RJgmA0Ea0M00iCOrHuhgUkW899eNZ2HvP684/0bZOtF7faxmCcElS U47bKbE5AgMBAAECgf9apGW7CyfZsUdnMdGV2oZTb1ko/q6azIJ91IXJB/fuvrZz +AjpSf3aNETmUFD8kfeEVvM5oig4NGMZQqgNv41q1bdw2HYkXKHSVB3pdsjvhwPG P4pGi+rbqHqDRqHN04w3WJIWwz4rB0Ohs86PAX+X/tK2tqbiiPFL5V/piKFgxJSu bhRZEwlvYcqmHo3XXNSQspOpIIo4wY6zaCBhEhJnuqKw2xAHF8lkXyQnkBPcOXq+ LTBFb7G3dOEVqCpn0Hhe1iMSFTsBFdQAFKQKpwIlpc8TG80GqPHDr2ydgFuXoT+o aFHxxIFEQYDEtOUi2nBICFNG5T7lPOnZvoPcUNECgYEA2QDpTA3dNpXXPcCzaXcp wmO8AVLXnIwgbSvyKczIcJ1DpKxVVnOZDXmYWlJ2pfzIkiQDsEsvsPBCPdIZiRUT feQzzJwfPnz2co00D9hyz5bMdrvxw79WB2Xzc37H0moU9k6on3Ts10cQ1auFofXQ yCCmCTGHWkWego6WZkJAbZ0CgYEAx9DNQgilf8jEwivKWGd87k+uflihafKDWN4m 6XAEaYKXcW/Dz85qlv3KFU38Su3g3GkeddXuqO9Hd9l1Btyj5KH9mafxd66WKsZm PSi/2wLqqRXTVlJeRfy49Rr8AcRxG6Et/dfSBIjzDr070uOzPkdWE7MVORaLIErr MM9qzU0CgYBkDM+DiUDc0ekTR61wishuH52m3pMaUmEA9KoKK6ieg2OUoQAXg+Sl GdLskeiyTwIKnyA0P/YVFjD0X9H2Kgl6maRoSfL91I+r8P5378u/ej963txw6962 OoMsVN+8W7yec1W7byVjfBh+6z4JllRUcPkcdAF71W1ntGYTb6hU4QKBgQDHWJoe wg+Sz9L0wMlrrztladt1M6k90POXanK5u8a7GM1MH6vkG1Qd9W5yGM6WVoJagwkf mGI06huMB0gIo4FjLPWcIX/23hf07uC7LXdS4bMJI7wnR5/EBSDlb5lxjfEmFoSS 1okxewu6bbJMcFy3ymWm26hVvxboDbZciOJNlQKBgQCz1xzwI5q6UDRe6bCLS3KD 0AlLrMfyuZyQqWGCNqHxGibvHd+YWSGjN/3sXhwZ4yp5kDMsvHRohsmHw9xPmZlm VdP/alG8cuYz/WTV7T3A58T49DLumsmZLGWM9Y3pmpmJpFGMHb2wDoQs9yGc2vzl OrbT6K+DQj9ceLIEgixrtg== -----END PRIVATE KEY-----";
 		String res = StudyPayBusiness.AccountInfoCheck(app_id,mch_id,mch_serial_no,api_v3_Key,private_key);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("2.支付账户设置创建-微信接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "success","2.支付账户设置创建-微信接口：" + res);
 	}
	
	@Test(description="3.校验支付设置-支付宝接口", priority=3)
 	public void testQueryAccountInfo()  {
 		String res = StudyPayBusiness.QueryAccountInfo();
 		String status = (String)JSONPath.read(res, "$.status");
 		System.out.println("3.校验支付设置-支付宝接口:"+"status="+status);
 		Assert.assertEquals(status, "SUCCESS","3.校验支付设置-支付宝接口：" + res);
 	}
	
	
	@Test(description="4.校验支付设置-微信接口", priority=4)
	public void testQueryWxAccountInfo()  {
 		String res = StudyPayBusiness.QueryWxAccountInfo();
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("4.校验支付设置-微信接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "","4.校验支付设置-微信接口：" + res);
 	}
	
	
	
	@Test(description="6.学习任务-付费模式接口", priority=6)
 	public void testStudyAdd()  {
		String official_price_str = "1.00";
 		String res = StudyPayBusiness.StudyAdd(studyPlan_title,official_price_str);
 		StudyPlan_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("6.学习任务-付费模式接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "新增计划成功！","6.学习任务-付费模式接口：" + res);
 	}
	
	
	@Test(description="7.PC端-校验学习任务详情页付费类型接口", priority=7)
 	public void testGetFreeType()  {
 		String res = StudyPayBusiness.GetFreeType(StudyPlan_id);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Boolean free = (Boolean)JSONPath.read(res, "$.data.free");
 		System.out.println("7.PC端-校验学习任务详情页付费类型接口:"+"msg="+msg);
 		Assert.assertFalse(free,"7.PC端-校验学习任务详情页付费类型接口：" + res);
 	}
	
	
	@Test(description="8.移动端-校验学习任务详情页付费类型接口", priority=8)
 	public void testStudyQuery()  {
 		String res = StudyPayBusiness.StudyQuery(StudyPlan_id);
 		String id = (String)JSONPath.read(res, "$.id");
 		Boolean free = (Boolean)JSONPath.read(res, "$.free");
 		System.out.println("8.移动端-校验学习任务详情页付费类型接口:"+"id="+id);
 		Assert.assertFalse(free,"8.移动端-校验学习任务详情页付费类型接口：" + res);
 	}
	
	
	@Test(description="9.PC端/移动端-校验学习任务支付前校验支付状态接口", priority=9)
 	public void testQueryOrder()  {
 		String res = StudyPayBusiness.QueryOrder(StudyPlan_id);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("9.PC端/移动端-校验学习任务支付前校验支付状态接口:"+"msg="+msg);
 		Assert.assertEquals(msg,"待支付","9.PC端/移动端-校验学习任务支付前校验支付状态接口：" + res);
 	}
	
	
	@Test(description="10.PC端/移动端-校验学习任务支持的支付方式接口", priority=10)
 	public void testAccountInfoConfigured()  {
 		String res = StudyPayBusiness.AccountInfoConfigured();
 		String wxpay = (String)JSONPath.read(res, "$.data.wxpay");
 		String alipay = (String)JSONPath.read(res, "$.data.alipay");
 		System.out.println("10.PC端/移动端-校验学习任务支付方式接口:"+"wxpay="+wxpay+","+"alipay="+alipay);
 		Assert.assertEquals(alipay,"true","10.PC端/移动端-校验学习任务支持的支付方式接口：" + res);
 	}	
	
	
	@Test(description="11.学习项目-付费模式-无划线价格-无需报名接口", priority=11)
 	public void testStudyProjectSave()  {
 		String cover = "https://oss.coolcollege.cn/1810048818682974377.png";
        String base_cover= "https://oss.coolcollege.cn/1810048818682974377.png";
 		String resFirst = StudyPayBusiness.StudyProjectSaveBaseInfo(title_project,classification_id,cover,base_cover);
 		project_id = (String) JSONPath.read(resFirst, "$.id"); 
 		projectCourse_id = (String) JSONPath.read(resFirst, "$.course_id"); 
 		String resSecond = StudyPayBusiness.StudyProjectSaveStageContent(project_id);		
 		String resThird = StudyPayBusiness.StudyProjectSaveSettings(project_id,"un_need","false","1","","1.00");
 		String result = (String) JSONPath.read(resThird, "$.result");		
 		System.out.println("11.学习项目-付费模式-无划线价格-无需报名接口:"+"project_id="+project_id);
 		Assert.assertEquals(result, "true","11.学习项目-付费模式-无划线价格-无需报名接口" + resThird);
 	}
	
	
	@Test(description="12.学习项目-付费模式-有划线价格-无需报名接口", priority=12)
 	public void testStudyProjectSavePrice()  {
 		String cover = "https://oss.coolcollege.cn/1810048818682974377.png";
        String base_cover= "https://oss.coolcollege.cn/1810048818682974377.png";
        String title = title_project +"-无划线价格";
 		String resFirst = StudyPayBusiness.StudyProjectSaveBaseInfo(title,classification_id,cover,base_cover);
 		officialPrice_project_id = (String) JSONPath.read(resFirst, "$.id"); 
 		officialPrice_projectCourse_id = (String) JSONPath.read(resFirst, "$.course_id"); 
 		String resSecond = StudyPayBusiness.StudyProjectSaveStageContent(officialPrice_project_id);		
 		String resThird = StudyPayBusiness.StudyProjectSaveSettings(officialPrice_project_id,"un_need","false","1","2.00","1.00");
 		String result = (String) JSONPath.read(resThird, "$.result");		
 		System.out.println("12.学习项目-付费模式-有划线价格-无需报名接口:"+"officialPrice_project_id="+officialPrice_project_id);
 		Assert.assertEquals(result, "true","12.学习项目-付费模式-无划线价格-无需报名接口" + resThird);
 	}
	
	
	@Test(description="13.学习项目-付费模式-无划线价格-需要报名接口", priority=13)
 	public void testStudyProjectSaveAudit()  {
 		String cover = "https://oss.coolcollege.cn/1810048818682974377.png";
        String base_cover= "https://oss.coolcollege.cn/1810048818682974377.png";
        String title = title_project +"需要报名";
        String limit_count = Integer.toString(Limit_count);
 		String resFirst = StudyPayBusiness.StudyProjectSaveBaseInfo(title,classification_id,cover,base_cover);
 		auditProject_id = (String) JSONPath.read(resFirst, "$.id"); 
 		auditProjectCourse_id = (String) JSONPath.read(resFirst, "$.course_id"); 
 		String resSecond = StudyPayBusiness.StudyProjectSaveStageContent(auditProject_id);		
 		String resThird = StudyPayBusiness.StudyProjectSaveSettings(auditProject_id,"automatic_audit","true",limit_count,"","1.00");
 		String result = (String) JSONPath.read(resThird, "$.result");		
 		System.out.println("13.学习项目-付费模式-无划线价格-需要报名名接口:"+"auditProject_id="+auditProject_id);
 		Assert.assertEquals(result, "true","13.学习项目-付费模式-无划线价格-需要报名名接口" + resThird);
 	}
	
	
	@Test(description="14.PC端/移动端-校验无划线价格的学习项目详情页报名入口+支付状态接口", priority=14)
 	public void testStudyProjectQuery()  {
 		String res = StudyPayBusiness.StudyProjectQuery(projectCourse_id);		
 		Boolean is_free = (Boolean) JSONPath.read(res, "$.is_free");
 		String have_purchased = (String) JSONPath.read(res, "$.have_purchased");
 		System.out.println("14.PC端/移动端-校验无划线价格的学习项目详情页报名入口+支付状态接口:"+"is_free="+is_free);
 		Assert.assertFalse(is_free,"14.PC端/移动端-校验无划线价格的学习项目详情页报名入口+支付状态接口"+res);
 		Assert.assertEquals(have_purchased,"false","14.PC端/移动端-校验无划线价格的学习项目详情页报名入口+支付状态接口"+res);
 	}
	
	
	@Test(description="15.PC端/移动端-校验有划线价格的学习项目详情页报名入口+支付状态接口", priority=15)
 	public void testStudyProjectQueryPrice()  {
 		String res = StudyPayBusiness.StudyProjectQuery(officialPrice_projectCourse_id);		
 		Boolean is_free = (Boolean) JSONPath.read(res, "$.is_free");
 		Integer official_price = (Integer) JSONPath.read(res, "$.official_price");
 		String have_purchased = (String) JSONPath.read(res, "$.have_purchased");
 		System.out.println("15.PC端/移动端-校验有划线价格的学习项目详情页报名入口+支付状态接口:"+"is_free="+is_free+","+"official_price="+official_price);
 		Assert.assertFalse(is_free,"15.PC端/移动端-校验有划线价格的学习项目详情页报名入口+支付状态接口"+res);
 		Assert.assertNotEquals(official_price,null,"15.PC端/移动端-校验有划线价格的学习项目详情页报名入口+支付状态接口"+res);
 		Assert.assertEquals(have_purchased,"false","15.PC端/移动端-校验有划线价格的学习项目详情页报名入口+支付状态接口"+res);
 	}
	
	
	@Test(description="16.PC端/移动端-校验有划线价格+需要报名的学习项目详情页是否需要报名入口接口", priority=16)
 	public void testStudyProjectQueryAudit()  {
 		String res = StudyPayBusiness.StudyProjectQuery(auditProjectCourse_id);		
 		String enroll_status = (String) JSONPath.read(res, "$.enroll_status");
 		Integer limit_count = (Integer) JSONPath.read(res, "$.limit_count");
 		enroll_id = (String) JSONPath.read(res, "$.enroll_id");
 		System.out.println("16.PC端/移动端-校验有划线价格+需要报名的学习项目详情页是否需要报名入口接口:"+"enroll_status="+enroll_status+","+"limit_count="+limit_count);
 		Assert.assertEquals(enroll_status,"limited","16.PC端/移动端-校验有划线价格+需要报名的学习项目详情页是否需要报名入口接口"+res);
 		Assert.assertEquals(limit_count,Limit_count,"16.PC端/移动端-校验有划线价格+需要报名的学习项目详情页是否需要报名入口接口"+res);
 	}
	
	
	@Test(description="17.PC端-学习项目报名接口", priority=17)
 	public void testEnrollments()  {
 		String res = StudyPayBusiness.Enrollments(enroll_id);		
 		String result = (String) JSONPath.read(res, "$.result");
 		System.out.println("17.PC端/移动端-学习项目报名接口:"+"result="+result);
 		Assert.assertEquals(result,"true","17.PC端/移动端-学习项目报名接口"+res);
 	}
	
	
	@Test(description="18.在移动端校验学习项目报名后的入口接口", priority=18)
 	public void testStudyProjectQueryAgain()  {
 		String res = StudyPayBusiness.StudyProjectQuery(auditProjectCourse_id);		
 		String enroll_status = (String) JSONPath.read(res, "$.enroll_status");
 		System.out.println("18.在移动端校验学习项目报名后的入口接口:"+"enroll_status="+enroll_status);
 		Assert.assertEquals(enroll_status,"pass","18.在移动端校验学习项目报名后的入口接口"+res);
 	}
	

	@Test(description="19.PC端/移动端-校验学习项目支持的支付方式接口", priority=19)
 	public void testAccountInfoConfiguredProject()  {
 		String res = StudyPayBusiness.AccountInfoConfigured();
 		String wxpay = (String)JSONPath.read(res, "$.data.wxpay");
 		String alipay = (String)JSONPath.read(res, "$.data.alipay");
 		System.out.println("15.PC端/移动端-校验学习项目支持的支付方式接口:"+"wxpay="+wxpay+","+"alipay="+alipay);
 		Assert.assertEquals(alipay,"true","15.PC端/移动端-校验学习项目支持的支付方式接口：" + res);
 		System.out.println("19.PC端/移动端-校验学习项目支持的支付方式接口:"+"wxpay="+wxpay+","+"alipay="+alipay);
 		Assert.assertEquals(alipay,"true","19.PC端/移动端-校验学习项目支持的支付方式接口：" + res);
 	}	
	
	
	
	@Test(description="20.PC端-学习任务/学习项目-支付宝生成订单接口", priority=20)
 	public void testCreateOrdeAli()  {
 		String res_studyPlan = StudyPayBusiness.CreateOrder("pc","ALIPAY",StudyPlan_id,"STUDY_TASK");
 		String res_project = StudyPayBusiness.CreateOrder("pc","ALIPAY",project_id,"STUDY_PROJECT");
 		String msg_studyPlan = (String)JSONPath.read(res_studyPlan, "$.msg");
 		String msg_project = (String)JSONPath.read(res_project, "$.msg");
 		System.out.println("20.PC端-学习任务/学习项目-支付宝生成订单接口:"+"msg_studyPlan="+msg_studyPlan+","+"msg_project="+msg_project);
 		Assert.assertEquals(msg_studyPlan,"","20.PC端-学习任务/学习项目-支付宝生成订单接口：" + res_studyPlan);
 		Assert.assertEquals(msg_project,"","20.PC端-学习任务/学习项目-支付宝生成订单接口：" + res_project);
 	}
	
	@Test(description="21.PC端-学习任务/学习项目-微信生成订单接口", priority=21)
 	public void testCreateOrderWx()  {
 		String res_studyPlan = StudyPayBusiness.CreateOrder("pc","WXPAY",StudyPlan_id,"STUDY_TASK");
 		String res_project = StudyPayBusiness.CreateOrder("pc","WXPAY",project_id,"STUDY_PROJECT");
 		String msg_studyPlan = (String)JSONPath.read(res_studyPlan, "$.msg");
 		String msg_project = (String)JSONPath.read(res_studyPlan, "$.msg");
 		System.out.println("17.PC端-学习任务/学习项目-微信生成订单接口:"+"msg_studyPlan="+msg_studyPlan+","+"msg_project="+msg_project);
 		Assert.assertTrue(res_project.contains("data"), ""+res_project);
 		System.out.println("21.PC端-学习任务/学习项目-微信生成订单接口:"+"msg_studyPlan="+msg_studyPlan+","+"msg_project="+msg_project);
// 		Assert.assertEquals(msg_studyPlan,"success","21.PC端-学习任务/学习项目-微信生成订单接口：" + res_studyPlan);
// 		Assert.assertEquals(msg_project,"success","21.PC端-学习任务/学习项目-微信生成订单接口：" + res_project);
 	}
 	
	
	@Test(description="22.钉钉小程序(不包含独立APP)-学习任务/学习项目-生成订单接口", priority=22)
 	public void testGoodsPay()  {
 		String res_studyPlan = StudyPayBusiness.GoodsPay(StudyPlan_id,"STUDY_TASK");
 		String res_project = StudyPayBusiness.GoodsPay(project_id,"STUDY_PROJECT");
 		String order_id_studyPlan = (String)JSONPath.read(res_studyPlan, "$.order_id");
 		String order_id_project = (String)JSONPath.read(res_project, "$.order_id");
 		System.out.println("22.钉钉小程序(不包含独立APP)-学习任务/学习项目-生成订单接口:"+"order_id_studyPlan="+order_id_studyPlan+","+
 		"order_id_project="+order_id_project);
 		Assert.assertNotEquals(order_id_studyPlan,null,"22.钉钉小程序(不包含独立APP)-学习任务/学习项目-生成订单接口：" + res_studyPlan);
 		Assert.assertNotEquals(order_id_project,null,"22.钉钉小程序(不包含独立APP)-学习任务/学习项目-生成订单接口：" + res_project);
 	}
	
	
	@Test(description="23.学习项目-数据监控-交易数据接口", priority=23)
 	public void testStudyProjectTransaction() throws UnsupportedEncodingException  {
		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String endTime = date_temp.format(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -30);
		String beginTime = date_temp.format(calendar.getTime());
 		String res_all = StudyPayBusiness.StudyProjectTransaction(project_id,"","","","");//全部数据
 		String res_time = StudyPayBusiness.StudyProjectTransaction(project_id,beginTime,endTime,"","");//按时间筛选
 		String res_department = StudyPayBusiness.StudyProjectTransaction(project_id,"","","1","");//按部门筛选
 		String res_userName = StudyPayBusiness.StudyProjectTransaction(project_id,"","","","test");//按人名筛选
 		String total_all = (String)JSONPath.read(res_all, "$.total");
 		String total_time = (String)JSONPath.read(res_time, "$.total");
 		String total_department = (String)JSONPath.read(res_department, "$.total");
 		String total_userName = (String)JSONPath.read(res_userName, "$.total");
 		System.out.println("23.学习项目-数据监控-交易数据接口:"+"total_all="+total_all+","+"total_time="+total_time+","+"total_department="
 		+total_department+","+"total_userName="+total_userName);
 		Assert.assertNotEquals(total_all,null,"23.学习项目-数据监控-交易数据接口：" + res_all);
 		Assert.assertNotEquals(total_time,null,"23.学习项目-数据监控-交易数据接口：" + res_time);
 		Assert.assertNotEquals(total_department,null,"23.学习项目-数据监控-交易数据接口：" + res_department);
 		Assert.assertNotEquals(total_userName,null,"23.学习项目-数据监控-交易数据接口：" + res_userName);
 	}
	
	
	@Test(description="24.学习项目-数据监控-交易数据导出接口", priority=24)
 	public void testStudyProjectTransactionExport(){
 		String res = StudyPayBusiness.StudyProjectTransactionExport(project_id,title_project);
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("24.学习项目-数据监控-交易数据导出接口:"+"msg="+msg);
 		Assert.assertEquals(msg,"导出学习项目交易数据成功","24.学习项目-数据监控-交易数据导出接口：" + res);
 	}
	
	
	@Test(description="25.支付设置-交易流水接口", priority=25)
 	public void testGoodsOrder() throws UnsupportedEncodingException  {
		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		String endTime = date_temp.format(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -30);
		String beginTime = date_temp.format(calendar.getTime());
 		String res_all = StudyPayBusiness.GoodsOrder("","","ALL");//全部
 		String res_ali = StudyPayBusiness.GoodsOrder("","","ALIPAY");//支付宝
 		String res_wx = StudyPayBusiness.GoodsOrder("","","WXPAY");//微信
 		String res_time = StudyPayBusiness.GoodsOrder(beginTime,endTime,"WXPAY");//按时间筛选
 		String total_all = (String)JSONPath.read(res_all, "$.total");
 		String total_ali = (String)JSONPath.read(res_ali, "$.total");
 		String total_wx = (String)JSONPath.read(res_wx, "$.total");
 		String total_time = (String)JSONPath.read(res_time, "$.total");
 		System.out.println("25.支付设置-交易流水接口:"+"total_all="+total_all+","+"total_ali="+total_ali+","
 		+"total_wx="+total_wx+","+"total_time="+total_time);
 		Assert.assertNotEquals(total_all,null,"25.支付设置-交易流水接口:" + res_all);
 		Assert.assertNotEquals(total_ali,null,"25.支付设置-交易流水接口:" + res_ali);
 		Assert.assertNotEquals(total_wx,null,"25.支付设置-交易流水接口:" + res_wx);
 		Assert.assertNotEquals(total_time,null,"25.支付设置-交易流水接口:" + res_time);
 	}
		
	
	@Test(description="26.学习任务删除接口", priority=26)
 	public void testStudyPlanDelete()  {
 		String res = StudyPayBusiness.StudyPlanDelete(StudyPlan_id);		
 		String msg = (String) JSONPath.read(res, "$.msg");		
 		System.out.println("26.学习任务删除接口:"+"msg="+msg);
 		Assert.assertEquals(msg,"删除学习计划成功","26.学习任务删除接口"+res);
 	}
	
	@Test(description="27.学习项目删除接口", priority=27)
 	public void testDeleteProject()  {
 		String res = StudyPayBusiness.DeleteProject(project_id);
 		String res_officialPrice = StudyPayBusiness.DeleteProject(officialPrice_project_id);
 		String res_audit = StudyPayBusiness.DeleteProject(auditProject_id);
 		String deleted = (String) JSONPath.read(res, "$.deleted");
 		System.out.println("27.学习项目删除接口:"+"deleted="+deleted);
 		Assert.assertEquals(deleted, "true","27.学习项目删除接口" + deleted);
 	}
	

}
