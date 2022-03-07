package investigationresearch.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.investigationresearch.business.AppInvestigatesBussiness;
import cn.kxy.investigationresearch.business.InvestigatesBusiness;
import cn.kxy.investigationresearch.business.QuestionnaireBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAppInvestigates {
	String title_one = "AppInvestigates"+CommonData.getStringRandom(5);
	String title_two = "AppInvestigates"+CommonData.getStringRandom(5);
	@Test(description="App端在未完成列表页（匿名，可查看统计结果--信息校验），查看调研任务列表",priority= 1)
	public void testQueryAppInvestigatesList () {
		InvestigatesBusiness.addNomalInvestigates(BaseBusiness.questionnaireName, title_one, "release");
		String res	= AppInvestigatesBussiness.queryAppInvestigatesList("unfinished");
		String name = (String)JSONPath.read(res, "$.list[0].title");
		String open_result = (String)JSONPath.read(res, "$.list[0].open_result");
		String term_type = (String)JSONPath.read(res, "$.list[0].term_type");
		Assert.assertEquals(name, title_one,"App端在未完成列表页（匿名，可查看统计结果--信息校验），查看调研任务列表实际返回结果："+res);
		Assert.assertEquals(open_result, "true","App端在未完成列表页（匿名，可查看统计结果--信息校验），查看调研任务列表实际返回结果："+res);
		Assert.assertEquals(term_type, "permanent","App端在未完成列表页（匿名，可查看统计结果--信息校验），查看调研任务列表实际返回结果："+res);
	}
	@Test(description="App端在未完成列表页（匿名，可查看统计结果--信息校验），查看调研任务详情",dependsOnMethods="testQueryAppInvestigatesList",priority= 2)
	public void testQueryAppInvestigatesInfo() {
		String res = AppInvestigatesBussiness.queryAppInvestigatesInfo("unfinished");
		String name = (String)JSONPath.read(res, "$.title");
		String description = (String)JSONPath.read(res, "$.description");
		String anonymous = (String)JSONPath.read(res, "$.anonymous");
		
		String title01 = (String)JSONPath.read(res, "$.question[0].title");
		String title04 = (String)JSONPath.read(res, "$.question[3].title");
//		Assert.assertEquals(anonymous, "true","App端在未完成列表页（匿名，可查看统计结果--信息校验），查看调研任务详情实际返回结果："+res);
//		Assert.assertEquals(title04, "What's your score for selenium","App端在未完成列表页（匿名，可查看统计结果--信息校验），查看调研任务详情实际返回结果："+res);
//		Assert.assertEquals(title01, "can you use selenium","App端在未完成列表页（匿名，可查看统计结果--信息校验），查看调研任务详情实际返回结果："+res);
//		Assert.assertEquals(description, "Questionnaire Description","App端在未完成列表页（匿名，可查看统计结果--信息校验），查看调研任务详情实际返回结果："+res);
//		Assert.assertEquals(name, title_one,"App端在未完成列表页（匿名，可查看统计结果--信息校验），查看调研任务详情实际返回结果："+res);
	}
	@Test(description="匿名，可查看统计结果，提交问卷",priority=3)
	public void testSubmitAnonymousStatistics() {
		String res = AppInvestigatesBussiness.submit("unfinished", "test", "4");
		String operate_result= (String)JSONPath.read(res, "$.operate_result");
		Assert.assertEquals(operate_result, "success","匿名，可查看统计结果，提交问卷实际返回结果："+res);
	}
	
	@Test(description="App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务列表",priority=4)
	public void testQueryInvestigatesFinishedList() {
		String res	= AppInvestigatesBussiness.queryAppInvestigatesList("finished");
		String name = (String)JSONPath.read(res, "$.list[0].title");
		String open_result = (String)JSONPath.read(res, "$.list[0].open_result");
		Assert.assertEquals(name, title_one,"App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务列表实际返回结果："+res);
		Assert.assertEquals(open_result, "true","App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务列表实际返回结果："+res);
	}
	
	@Test(description="App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务的统计结果",priority= 5)
	public void testQueryInvestigatesInfoFinishedInfo() {
		String res = AppInvestigatesBussiness.queryAppStatistics("finished");
		String first_proportion = (String)JSONPath.read(res, "$.statistics[0].result[0].proportion");
		int first_total_count = (int)JSONPath.read(res, "$.statistics[0].result[0].total_count");
		String second_proportion = (String)JSONPath.read(res, "$.statistics[1].result[0].proportion");
		int second_total_count = (int)JSONPath.read(res, "$.statistics[1].result[0].total_count");
		String second_proportion1 = (String)JSONPath.read(res, "$.statistics[1].result[1].proportion");
		int second_total_count1 = (int)JSONPath.read(res, "$.statistics[1].result[1].total_count");
		String third_proportion = (String)JSONPath.read(res, "$.statistics[2].result[3].proportion");
		int third_total_count = (int)JSONPath.read(res, "$.statistics[2].result[3].total_count");
		InvestigatesBusiness.cancelDeleteInvestigates(title_one);
//		Assert.assertEquals(first_proportion, "100","App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务的统计结果实际返回结果："+res);
//		Assert.assertEquals(first_total_count, 1,"App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务的统计结果实际返回结果："+res);
//		Assert.assertEquals(second_proportion1, "100","App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务的统计结果实际返回结果："+res);
//		Assert.assertEquals(second_total_count1, 1,"App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务的统计结果实际返回结果："+res);
//		Assert.assertEquals(second_proportion, "100","App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务的统计结果实际返回结果："+res);
//		Assert.assertEquals(second_total_count, 1,"App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务的统计结果实际返回结果："+res);
//		Assert.assertEquals(third_proportion, "100","App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务的统计结果实际返回结果："+res);
//		Assert.assertEquals(third_total_count, 1,"App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务的统计结果实际返回结果："+res);
		
	}
	
	@Test(description="App端在未完成列表页（实名，不可查看统计结果）查看调研任务列表",priority= 6)
	public void testQueryAppRealNameInvestigatesList () {
		InvestigatesBusiness.addRealNameInvestigates(title_two,"release");
		String res	= AppInvestigatesBussiness.queryAppInvestigatesList("unfinished");
		String open_result = (String)JSONPath.read(res, "$.list[0].open_result");
		String term_type = (String)JSONPath.read(res, "$.list[0].term_type");
		Assert.assertEquals(open_result, "false","App端在未完成列表页（实名，不可查看统计结果）查看调研任务列表务实际返回结果："+res);
		Assert.assertEquals(term_type, "permanent","App端在未完成列表页（实名，不可查看统计结果）查看调研任务列表实际返回结果："+res);
	}
	@Test(description="App端在未完成列表页（实名，不可查看统计结果）查看调研任务详情",dependsOnMethods="testQueryAppRealNameInvestigatesList",priority= 7)
	public void testQueryAppRealNameInvestigatesInfo() {
		String res = AppInvestigatesBussiness.queryAppInvestigatesInfo("unfinished");
		String name = (String)JSONPath.read(res, "$.title");
		String description = (String)JSONPath.read(res, "$.description");
		String anonymous = (String)JSONPath.read(res, "$.anonymous");
		
		String title01 = (String)JSONPath.read(res, "$.question[0].title");
		String title04 = (String)JSONPath.read(res, "$.question[3].title");
		Assert.assertEquals(anonymous, "false","App端在未完成列表页（实名，不可查看统计结果）查看调研任务详情实际返回结果："+res);
		Assert.assertEquals(title04, "What's your score for selenium","App端在未完成列表页（实名，不可查看统计结果）查看调研任务详情实际返回结果："+res);
		Assert.assertEquals(title01, "can you use selenium","App端在未完成列表页（实名，不可查看统计结果）查看调研任务详情实际返回结果："+res);
		Assert.assertEquals(description, "Questionnaire Description","App端在未完成列表页（实名，不可查看统计结果）查看调研任务详情实际返回结果："+res);
		Assert.assertEquals(name, title_two,"App端在未完成列表页（实名，不可查看统计结果）查看调研任务详情实际返回结果："+res);
	}
	
	@Test(description="App端在已完成列表页（实名，不查看统计结果--信息校验），查看调研任务列表",priority=8)
	public void testSubmitRealnameStatistics() {
		AppInvestigatesBussiness.submit("unfinished", "test", "4");
		String res	= AppInvestigatesBussiness.queryAppInvestigatesList("finished");
		String open_result = (String)JSONPath.read(res, "$.list[0].open_result");
		Assert.assertEquals(open_result, "false","App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务列表实际返回结果："+res);
		
	}
	@Test(description="App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务的统计结果",priority=9)
	public void testRealNameInvestigates() {
		String res = AppInvestigatesBussiness.queryAppStatistics("finished");
		String first_proportion = (String)JSONPath.read(res, "$.statistics[0].result[0].proportion");
		int first_total_count = (int)JSONPath.read(res, "$.statistics[0].result[0].total_count");
		String second_proportion = (String)JSONPath.read(res, "$.statistics[1].result[0].proportion");
		int second_total_count = (int)JSONPath.read(res, "$.statistics[1].result[0].total_count");
		String second_proportion1 = (String)JSONPath.read(res, "$.statistics[1].result[1].proportion");
		int second_total_count1 = (int)JSONPath.read(res, "$.statistics[1].result[1].total_count");
		String third_proportion = (String)JSONPath.read(res, "$.statistics[2].result[3].proportion");
		int third_total_count = (int)JSONPath.read(res, "$.statistics[2].result[3].total_count");
		Assert.assertEquals(first_proportion, "100","App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务的统计结果实际返回结果："+res);
		Assert.assertEquals(first_total_count, 1,"App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务的统计结果实际返回结果："+res);
		Assert.assertEquals(second_proportion1, "100","App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务的统计结果实际返回结果："+res);
		Assert.assertEquals(second_total_count1, 1,"App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务的统计结果实际返回结果："+res);
		Assert.assertEquals(second_proportion, "100","App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务的统计结果实际返回结果："+res);
		Assert.assertEquals(second_total_count, 1,"App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务的统计结果实际返回结果："+res);
		Assert.assertEquals(third_proportion, "100","App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务的统计结果实际返回结果："+res);
		Assert.assertEquals(third_total_count, 1,"App端在已完成列表页（匿名，可查看统计结果--信息校验），查看调研任务的统计结果实际返回结果："+res);
		
	}
	@Test(description="web端，调研任务参与的人数校验",priority=10)
	public void testInvestigate_count() {
		String res = InvestigatesBusiness.queryLsit(title_two, "all", "release");
		int total = (int)JSONPath.read(res, "$.investigates.list[0].investigate_count"); 
		InvestigatesBusiness.cancelDeleteInvestigates(title_two);
		Assert.assertEquals(total, 1,"web端，调研任务参与的人数校验实际返回结果："+res);
	}
	
	@Test(description="App端在已逾期列表页查看调研任务",priority=11)
	public void testOverdueInvestigatesList() {
//		String title = "Investigates007";
//		InvestigatesBusiness.addShortTermInvestigates(title, DateUtil.getRegularDate(0), DateUtil.getRegularDate(1));
//		String res	= AppInvestigatesBussiness.queryAppInvestigatesList("overdue");
//		String type = (String)JSONPath.read(res, "$.list[0].type");
//		Assert.assertTrue(res.contains("total"),"App端在已逾期列表页查看调研任务实际返回结果："+res);
	}
	@Test(description="查看App端已逾期的调研任务详情，不报错即可",dependsOnMethods="testOverdueInvestigatesList",priority=12)
	public void testOverdueInvestigatesInfo() {
//		String res = AppInvestigatesBussiness.queryAppInvestigatesInfo("overdue");
//		String name = (String)JSONPath.read(res, "$.title");
//		InvestigatesBusiness.cancelInvestigates("Investigates007", "release");
//		String res02= InvestigatesBusiness.deleteInvestigates("Investigates007", "draft");
//		Assert.assertEquals(name, "Investigates007","查看App端已逾期的调研任务详情，不报错即可实际返回结果："+res+",测完后是否删除成功："+res02);
	}
	

	@Test(description="校验所有选项都是必填项",priority=13)
	public void testMust_fill() {
		String name= "Investates"+CommonData.getStringRandom(5);
		InvestigatesBusiness.addNomalInvestigates(BaseBusiness.questionnaireName, name, "release");
		String res = AppInvestigatesBussiness.queryAppInvestigatesInfo("unfinished");
		String ismust0 = (String)JSONPath.read(res, "$.question[0].is_must");
		String ismust1 = (String)JSONPath.read(res, "$.question[1].is_must");
		String ismust2 = (String)JSONPath.read(res, "$.question[2].is_must");
		String ismust3 = (String)JSONPath.read(res, "$.question[3].is_must");
		String is_multiple_rows = (String)JSONPath.read(res, "$.question[2].is_multiple_rows");
		InvestigatesBusiness.cancelDeleteInvestigates(name);
		
		Assert.assertEquals(is_multiple_rows, "true","校验所有选项都是必填项实际返回结果:"+res);
		Assert.assertEquals(ismust0, "true","校验所有选项都是必填项实际返回结果:"+res);
		Assert.assertEquals(ismust1, "true","校验所有选项都是必填项实际返回结果:"+res);
		Assert.assertEquals(ismust2, "true","校验所有选项都是必填项实际返回结果:"+res);
		Assert.assertEquals(ismust3, "true","校验所有选项都是必填项实际返回结果:"+res);
		
	}
	
	@Test(description="校验所有选项都是非必填项",priority=14)
	public void testMust_fill_Non() {
		String q_name= "Quesnaire"+CommonData.getStringRandom(5);
		String name= "Investis"+CommonData.getStringRandom(5);
		QuestionnaireBusiness.addQuestionnaire(q_name, "false", "false", "false", "false", "false");
		InvestigatesBusiness.addNomalInvestigates(q_name, name, "release");
		String res = AppInvestigatesBussiness.queryAppInvestigatesInfo("unfinished");
		String ismust0 = (String)JSONPath.read(res, "$.question[0].is_must");
		String ismust1 = (String)JSONPath.read(res, "$.question[1].is_must");
		String ismust2 = (String)JSONPath.read(res, "$.question[2].is_must");
		String ismust3 = (String)JSONPath.read(res, "$.question[3].is_must");
		String is_multiple_rows = (String)JSONPath.read(res, "$.question[2].is_multiple_rows");
		
		InvestigatesBusiness.cancelDeleteInvestigates(name);
		QuestionnaireBusiness.cancelQuestionnaire(q_name, "release");
		QuestionnaireBusiness.deleteQuestionnaire(q_name, "draft");
		Assert.assertEquals(is_multiple_rows, "false","校验所有选项都是非必填项实际返回结果:"+res);
		Assert.assertEquals(ismust0, "false","校验所有选项都是非必填项实际返回结果:"+res);
		Assert.assertEquals(ismust1, "false","校验所有选项都是非必填项实际返回结果:"+res);
		Assert.assertEquals(ismust2, "false","校验所有选项都是非必填项实际返回结果:"+res);
		Assert.assertEquals(ismust3, "false","校验所有选项都是非必填项实际返回结果:"+res);
		
	}
	
}
