package homepage.cases;

import cn.kxy.app.homepage.AppHomePageBusiness;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.setting.bussiness.AdvanceSettingBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.FilePath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class TestAppHomePage {
	
	@Test(description="查询移动端设置",priority=1)
	public void testQueryMobileSetting() {
		String res = AppHomePageBusiness.queryMobileSetting();
		String color = (String)JSONPath.read(res, "$.colour");
		String url = (String)JSONPath.read(res, "$.url");
		Assert.assertNotNull(color, "查询移动端设置,颜色字段进行校验"+res);
		Assert.assertNotNull(url, "查询移动端设置,URL字段进行校验"+res);
	}
	
	@Test(description="查询未开始的岗位地图的个数",priority=2)
	public void testQueryPostMapNotStartCount() {
		String res = AppHomePageBusiness.queryPostMapNotStartCount();
		int not_started_count = (int)JSONPath.read(res, "$.not_started_count");
		Assert.assertTrue(not_started_count>=0,"查询未开始的岗位地图的个数"+res);
	}
	@Test(description="查询我的任务",priority=3)
	public void testQueryTopTask() {
		String res = AppHomePageBusiness.queryTopTask();
		String create_user_name = (String)JSONPath.read(res, "$.list[0].create_user_name");
		String title = (String)JSONPath.read(res, "$.list[0].title");
		String type = (String)JSONPath.read(res, "$.list[0].type");
		Integer resource_type = (Integer)JSONPath.read(res, "$.list[0].resource_type");
		Assert.assertNotNull(resource_type,"查询我的任务，resource_type进行校验"+res);
		Assert.assertNotNull(type,"查询我的任务，类型进行校验"+res);
		Assert.assertNotNull(title,"查询我的任务，标题进行校验"+res);
		Assert.assertNotNull(create_user_name,"查询我的任务，创建人进行校验"+res);
	}
	@Test(description="查询我的语言类型",priority=4)
	public void testQueryLanguageInfo() {
		String res = AppHomePageBusiness.queryLanguageInfo();
		String language = (String)JSONPath.read(res, "$.language");
		Assert.assertNotNull(language,"查询我的语言类型"+res);
	}
	@Test(description="查看我的学分，排行",priority=5)
	public void testQueryBaseScoreInfo() {
		String res = AppHomePageBusiness.queryBaseScoreInfo();
		int rank  = (int)JSONPath.read(res, "$.rank");
		int total_score  = (int)JSONPath.read(res, "$.total_score");
		Assert.assertNotNull(total_score, "查看我的学分,total_score字段进行校验"+res);
		Assert.assertNotNull(rank, "查看我的学分,rank字段进行校验"+res);
	}
	@Test(description="查看学习动态的设置",priority=6)
	public void testQueryMobileStudySetting() {
		String res = AppHomePageBusiness.queryMobileStudySetting();
		String title = (String)JSONPath.read(res, "$.title");
		Assert.assertEquals(title, "移动端学习动态","查看学习动态的设置"+res);
	}
	
	@Test(description="查看学习动态",priority=7)
	public void testQueryDynamicStudy () {
		String res = AppHomePageBusiness.queryDynamicStudy();
		Assert.assertTrue(res.contains("title"),"查看学习动态"+res);
	}
	
	@Test(description="查看高级设置",priority=8)
	public void testQuerySetting() {
		String res = AppHomePageBusiness.querySetting();
		String title = (String)JSONPath.read(res, "$.list[9].title");
		Assert.assertNotNull(title, "查看高级设置"+res);
	}
	@Test(description="查看首页移动端学分排行",priority=9)
	public void testQueryScoresRank() {
		String res = AppHomePageBusiness.queryScoresRank("1","3");
		String name = (String)JSONPath.read(res, "$.list[0].name");
		int score = (int)JSONPath.read(res, "$.list[0].score");
		int size = (int)JSONPath.read(res, "$.size");
		Assert.assertEquals(size, 3,"查看首页移动端学分排行，个数校验"+res);
		Assert.assertTrue(score>=0,"查看首页移动端学分排行，学分校验"+res);
		Assert.assertNotNull(name, "查看首页移动端学分排行，名称校验"+res);
	}
	@Test(description="查看首页我的讲师列表",priority=10)
	public void testQueryLecturer() {
//		String res = AppHomePageBusiness.queryLecturer();
//		String lecturer_name = (String)JSONPath.read(res, "$.list[0].lecturer_name");
//		String level_name = (String)JSONPath.read(res, "$.list[0].level_name");
//		Assert.assertNotNull(level_name, "查看首页我的讲师列表，讲师等级校验"+res);
//		Assert.assertNotNull(lecturer_name, "查看首页我的讲师列表，讲师名称校验"+res);
		
	}
	@Test(description="weatherHasGuideView接口测试",priority=11)
	public void testQueryWeatherHasGuideView() {
		String res = AppHomePageBusiness.queryWeatherHasGuideView();
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "查询成功","weatherHasGuideView接口实际返回结果："+res);
	}
	@Test(description="discoveries/query接口测试",priority=12)
	public void testQueryDiscoveries() {
		String res = AppHomePageBusiness.queryDiscoveries();
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "查询成功","discoveries/query接口实际返回结果"+res);
	}
	@Test(description="queryPhonePopWindow接口测试",priority=13)
	public void testQueryPhonePopWindow() {
		String res = AppHomePageBusiness.queryPhonePopWindow();
		Assert.assertTrue(res.contains("pop"), "phone_pop_window接口实际返回结果"+res);
	}
	@Test(description="查看菜单设置",priority=14)
	public void testQueryMenus() {
		String res = AppHomePageBusiness.queryMenus();
		String coursename = (String)JSONPath.read(res, "$.pc_menus[0].name");
		Assert.assertEquals(coursename, "报表","查看菜单设置，课程商城菜单"+res);
	}
	
	@Test(description="查看考试排行榜状态",priority=15)
	public void testQueryExamRankingSetting() {
		String app_res = AppHomePageBusiness.querySetting();
		String res = AdvanceSettingBusiness.getAdvanceSetting();
		String web_status = (String)JSONPath.read(res, "$.list[6].switch_status");
		String app_status = (String)JSONPath.read(app_res, "$.list[6].switch_status");
		Assert.assertEquals(web_status, app_status,"查看考试排行榜状态："+res);
	}
	
	@Test(description = "查看客服接口",priority = 16)
	public void testQueryCustomer() {
		String res = AppHomePageBusiness.queryCustomer();
		String title = (String)JSONPath.read(res, "$.title");
		Assert.assertTrue(!title.isEmpty(), "查看客服接口,实际返回结果"+res);
	}
	
	@Test(description = "移动端查看今日学分",priority = 17)
	public void testQueryTodayScoresRank() {
		String res = AppHomePageBusiness.queryTodayScoresRank();
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0,"移动端查看今日学分，实际返回结果:"+res);
	}
	String  dep_id = "";
	@Test(description = "查看员工所属部门", priority = 18)
	public void testQueryDepart() {
		String res = AppHomePageBusiness.queryDepart();
		dep_id = (String)JSONPath.read(res, "$.data[1].dept_id");
		String dep_name = (String)JSONPath.read(res, "$.data[1].dept_name");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "success","查看员工所属部门,实际返回结果："+res);
		Assert.assertNotNull(dep_id,"查看员工所属部门,实际返回结果："+res);
		Assert.assertNotNull(dep_name,"查看员工所属部门,实际返回结果："+res);
	}
	
	@Test(description= "根据部门查看学分排行",priority=19)
	public void testQueryScoresRankBydep() {
		String res = AppHomePageBusiness.queryScoresRank(dep_id,"10");
		int score  = (int)JSONPath.read(res, "$.list[0].score");
		Assert.assertNotNull(score,"根据部门查看学分排行，实际返回结果："+res);
	}
	
	@Test(description = "查看首页的菜单",priority = 20)
	public void testGetHomeMenus() {
		String type = System.getProperty("env");
		if (type.contains("FAT")) {
			
		}else {
			int code = AppHomePageBusiness.getHomeMenus();
			Assert.assertEquals(200,code,"查看首页的菜单，实际返回结果："+code);
		}
		
	}
	
	@Test(description = "face/query接口验证",priority = 21)
	public void queryFace() {
		String res = AppHomePageBusiness.queryFace();
		Assert.assertTrue(res.contains("face"), "face/query接口验证,实际返回结果"+res);
	}
	
	@Test(description = "查看pk赛个数",priority = 22)
	public void queryPkCount() {
//		String res = AppHomePageBusiness.queryPkCount();
//		Assert.assertTrue(res.contains("message"), "查看pk赛个数,实际返回结果"+res);
	}
	
	@Test(description = "is_need接口验证",priority = 23)
	public void judgeIsNeed() {
		String res = AppHomePageBusiness.judgeIsNeed();
		Assert.assertTrue(res.contains("need"), "is_need接口验证,实际返回结果"+res);
	}
	
}