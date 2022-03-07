package setting.cases;


import cn.kxy.setting.bussiness.MobileSettingBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestMobileSetting {

	String colour="";
	String url = "";
	@Test(description="获取首页配置项",priority=1)
	public void testGetModuleSetting() {
		String res = MobileSettingBusiness.getModuleSetting();
		String banner_name = (String)JSONPath.read(res, "$.menus[0].custom_name");
		String picture_name = (String)JSONPath.read(res, "$.menus[1].custom_name");
		String message_name = (String)JSONPath.read(res, "$.menus[2].custom_name");
		String mytask_name = (String)JSONPath.read(res, "$.menus[3].custom_name");
		String rangk_name = (String)JSONPath.read(res, "$.menus[4].custom_name");
		String score_name = (String)JSONPath.read(res, "$.menus[5].custom_name");
		String teacher_name = (String)JSONPath.read(res, "$.menus[6].custom_name");
		String study_name = (String)JSONPath.read(res, "$.menus[6].custom_name");

		String study_child_name = (String)JSONPath.read(res, "$.menus[1].children[0].name");
		String exam_name = (String)JSONPath.read(res, "$.menus[1].children[1].name");
		String map_name = (String)JSONPath.read(res, "$.menus[1].children[2].name");
		String practice_name = (String)JSONPath.read(res, "$.menus[1].children[3].name");
		String auth_name = (String)JSONPath.read(res, "$.menus[1].children[4].name");
		String micro_name = (String)JSONPath.read(res, "$.menus[1].children[5].name");
		String ques_name = (String)JSONPath.read(res, "$.menus[1].children[6].name");
		String xxqd_name = (String)JSONPath.read(res, "$.menus[1].children[7].name");
		String data_name = (String)JSONPath.read(res, "$.menus[1].children[8].name");
		String lecther_name = (String)JSONPath.read(res, "$.menus[1].children[9].name");
		
		Assert.assertNotNull(study_child_name,"获取首页配置项,学习名称进行校验"+res);
		Assert.assertNotNull(exam_name,"获取首页配置项,考试名称进行校验"+res);
		Assert.assertNotNull(map_name, "获取首页配置项,岗位地图名称进行校验"+res);
		Assert.assertNotNull(practice_name, "获取首页配置项,练习名称进行校验"+res);
		Assert.assertNotNull(auth_name, "获取首页配置项,认证名称进行校验"+res);
		Assert.assertNotNull(micro_name, "获取首页配置项,微课名称进行校验"+res);
		Assert.assertNotNull(ques_name, "获取首页配置项,问卷名称进行校验"+res);
		Assert.assertNotNull(xxqd_name, "获取首页配置项,线下签到名称进行校验"+res);
		Assert.assertNotNull(data_name,"获取首页配置项,数据名称进行校验"+res);
		Assert.assertNotNull(lecther_name, "获取首页配置项,讲师名称进行校验"+res);
		Assert.assertNotNull(banner_name, "获取首页配置项,banner图名称进行校验"+res);
		Assert.assertNotNull(picture_name, "获取首页配置项,图文导航名称进行校验"+res);
		Assert.assertNotNull(message_name, "获取首页配置项,企业通知名称进行校验"+res);
		Assert.assertNotNull(mytask_name, "获取首页配置项,我的任务名称进行校验"+res);
		Assert.assertNotNull(rangk_name,"获取首页配置项,学分排行名称进行校验"+res);
		Assert.assertNotNull(score_name, "获取首页配置项,积分商城名称进行校验"+res);
		Assert.assertNotNull(teacher_name,"获取首页配置项,讲师风采名称进行校验"+res);
		Assert.assertNotNull(study_name, "获取首页配置项,学习动态名称进行校验"+res);
	}
	@Test(description="获取启动页配置",priority=2)
	public void testGetStartUpSetting() {
		String res = MobileSettingBusiness.getStartUpSetting();
		colour= (String)JSONPath.read(res, "$.colour");
		url = (String)JSONPath.read(res, "$.url");
		Assert.assertNotNull(colour, "获取启动页配置,导航栏颜色进行校验");
		Assert.assertNotNull(url, "获取启动页配置,url进行校验");
	}
	@Test(description="设置启动页配置",priority=3)
	public void testSetStartup() {
		String res = MobileSettingBusiness.setStartup(colour, url);
		Assert.assertTrue(res.contains( "true"),"设置启动页配置，实际返回结果："+res);
	}
	
	String color = "";
	@Test(description="获取移动端颜色主题配置",priority=4)
	public void testGetMobileColor () {
		String res = MobileSettingBusiness.getMobileColor();
		String type = (String)JSONPath.read(res, "$.type");
		color = (String)JSONPath.read(res, "$.colour");
		Assert.assertEquals(type, "change_colour","获取移动端颜色主题配置,实际返回结果："+res);
		Assert.assertNotNull(color,"获取移动端颜色主题配置"+res);
	}
	@Test(description="设置移动端颜色主题",priority=5)
	public void testSetColor() {
		String res = MobileSettingBusiness.setColor(color);
		String setting = (String)JSONPath.read(res, "$.setting");
		Assert.assertEquals(setting, "true","设置移动端颜色主题,实际返回结果："+res);
	}
}
