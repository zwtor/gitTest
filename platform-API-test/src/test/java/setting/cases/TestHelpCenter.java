package setting.cases;

import cn.kxy.setting.bussiness.HelpCenterBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestHelpCenter {

	@Test(description = "查看帮助文档",priority = 1,dataProvider="helpkey")
	public void testGetHelpContent(String type,String key,String exp) {
		String res = HelpCenterBusiness.getHelpContent(key);
		String name = (String)JSONPath.read(res, "$.name");
		Assert.assertTrue(name.contains(exp),"查看帮助文档"+res);
	}
	
	@Test(description = "关闭帮助中心",priority = 2)
	public void changeHelpStatus() {
		String res = HelpCenterBusiness.changeHelpStatus("0");
		Assert.assertTrue(res.contains("设置成功"),""+res);
	}
	
	@Test(description = "开启设置中心",priority = 3)
	public void openHelpStatus() {
		String res = HelpCenterBusiness.changeHelpStatus("1");
		Assert.assertTrue(res.contains("设置成功"),""+res);
	}
	
	@DataProvider(name = "helpkey")
	public Object[][] monitorsQualifications() {
		Object[][] obj = new Object[][] { 
			{"查看学习项目的帮助文档","studyProject","学习项目"},{"查看定时学习模板的帮助文档","studyTemplate","定时任务管理"},
			{"查看新员工培训的帮助文档","employee","新员工任务管理"},{"查看考试任务的帮助文档","plan","考试任务"},
			{"查看定时考试模板的帮助文档","examTemplate","定时考试模板"},{"查看学习任务的帮助文档","management","学习任务管理"},
			{"查看题库练习的帮助文档","practice","练习"},{"查看试卷管理的帮助文档","paper","试卷"},
			{"查看试题管理的帮助文档","question","试题"},{"查看岗位地图的帮助文档","postMap","岗位地图"},
			{"查看试题管理的帮助文档","ability","岗位认证"},{"查看调研任务的帮助文档","investigate","调研任务"},
			{"查看课程管理的帮助文档","courseManage","线上课"},{"查看课件管理的帮助文档","library","素材库"},
			{"查看登录页的帮助文档","DomainSettings","登录设置"}
			};
		return obj;
	}
}
