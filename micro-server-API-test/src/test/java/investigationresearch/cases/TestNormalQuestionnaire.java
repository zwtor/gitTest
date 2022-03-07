package investigationresearch.cases;

import cn.kxy.investigationresearch.business.QuestionnaireBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import cn.kxy.setting.bussiness.UserBusiness;
import init.cases.InitExam;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNormalQuestionnaire extends InitExam{

	String title = "Questionnaire"+CommonData.getStringRandom(5);
	@Test(description="新增问卷保存到草稿箱",priority=1)
	public void testAddQuestionnaire() {
		String res = QuestionnaireBusiness.addNormalQuestionnaire(title, "this is a description", "draft");
		String msg = (String)JSONPath.read(res, "$.msg") ;
		System.out.println("this is a Questionnaire module");
		Assert.assertEquals(msg, "问卷新增成功","新增问卷保存到草稿箱实际返回结果："+res);
	}
	
	@Test(description="查看问卷草稿箱列表",priority=2)
	public void testQueryDraftList() {
		String res = QuestionnaireBusiness.queryLsit(title, "all", ClassificationBusines.getPrimaryId(), "draft");
		Assert.assertTrue(res.contains("total"),"查看问卷草稿箱列表实际返回结果："+res);
		
	}
	
	@Test(description="在草稿箱页面发布问卷",priority=3)
	public void testPublicQuestionnaire() {
		String res = QuestionnaireBusiness.publicQuestionnaire(title, "draft");
		String msg = (String)JSONPath.read(res, "$.msg") ;
		Assert.assertEquals(msg, "问卷发布成功","在草稿箱页面发布问卷实际返回结果："+res);
	}
	
	@Test(description="查看已发布问卷列表",priority=4)
	public void testQueryReleaseList() {
		String res = QuestionnaireBusiness.queryLsit(title, "own", ClassificationBusines.getPrimaryId(), "release");
		Assert.assertTrue(res.contains("total"),"查看已发布问卷列表实际返回结果："+res);
		
	}
	
	@Test(description="在已发布页面取消问卷",priority=5)
	public void testCancleQuestionnaire() {
		String res = QuestionnaireBusiness.cancelQuestionnaire(title, "release");
		String msg = (String)JSONPath.read(res, "$.msg") ;
		Assert.assertEquals(msg, "问卷取消发布成功","在已发布页面取消问卷实际返回结果："+res);
	}
	@Test(description="在草稿页面查看问卷详情",priority=6)
	public void testQueryQuestionnaireInfo() {
		String res = QuestionnaireBusiness.queryInfo(title, "draft");
		String name = (String)JSONPath.read(res, "$.title");
		String description = (String)JSONPath.read(res, "$.description");
		String questions_title = (String)JSONPath.read(res, "$.questions[0].title");
		
		Assert.assertEquals(questions_title, "can you use selenium","在草稿页面查看问卷详情实际返回结果："+res);
		Assert.assertEquals(description, "this is a description","在草稿页面查看问卷详情实际返回结果："+res);
		Assert.assertEquals(name, title,"在草稿页面查看问卷详情实际返回结果："+res);
	}
	
	@Test(description="在草稿页面删除问卷",priority=7)
	public void testDeleteQuestionnaire() {
		String res = QuestionnaireBusiness.deleteQuestionnaire(title, "draft");
		String msg = (String)JSONPath.read(res, "$.msg") ;
		Assert.assertEquals(msg, "问卷删除成功","在草稿页面删除问卷实际返回结果："+res);
	}
	
	@Test(description="直接发布问卷，然后取消，删除",priority = 8)
	public void testPublic_Cancel_Delete_Questionnaire() {
		String name = "Questionnaire"+CommonData.getStringRandom(3);
		QuestionnaireBusiness.addNormalQuestionnaire(name, "this is a description", "release");
		QuestionnaireBusiness.cancelQuestionnaire(name, "release");
		String res = QuestionnaireBusiness.deleteQuestionnaire(name, "draft");
		String msg = (String)JSONPath.read(res, "$.msg") ;
		Assert.assertEquals(msg, "问卷删除成功","直接发布问卷，然后取消，删除实际返回结果："+res);
	}
	
	@Test(description="编辑问卷后，保存到草稿箱，然后删除",priority = 9)
	public void testEditQuestionnaire() {
		String name = "Questionnaire"+CommonData.getStringRandom(3);
		String edit_name = "Questionnaire"+CommonData.getStringRandom(3);
		QuestionnaireBusiness.addNormalQuestionnaire(name, "this is a description", "draft");
		String res = QuestionnaireBusiness.editQuestionnaire(edit_name, "description", "draft",
				QuestionnaireBusiness.getIdByKeyword(name, "draft"));
		String msg = (String)JSONPath.read(res, "$.msg");
		QuestionnaireBusiness.deleteQuestionnaire(edit_name, "draft");
		Assert.assertEquals(msg, "问卷编辑成功","编辑问卷，保存到草稿箱，然后删除实际返回结果："+res);
	}
	String name = "Questionnaire"+CommonData.getStringRandom(3);
	String edit_name = "Questionnaire"+CommonData.getStringRandom(3);
	String id = "";
	@Test(description="新增问卷",priority = 10)
	public void testEditQuestionnaireToRelease() {
		QuestionnaireBusiness.addNormalQuestionnaire(name, "this is a description", "draft");
		id = QuestionnaireBusiness.getIdByKeyword(name, "draft");
	}
	
	@Test(description="在草稿列表页，设置问卷可见范围为全部",priority=11)
	public void testSetVisibleForAll() {
		String res = QuestionnaireBusiness.setVisible(id, "all", "");
		String result = (String)JSONPath.read(res, "$.result");
		Assert.assertEquals(result, "true","在草稿列表页，设置问卷可见范围为全部"+res);
	}
	
	@Test(description="在草稿列表页，查看可见范围为全部的问卷详情",priority=12)
	public void testQueryAllInfo() {
		String res = QuestionnaireBusiness.queryInfo(id);
		String visible_range = (String)JSONPath.read(res, "$.visible_range");
		Assert.assertEquals(visible_range, "all","在草稿列表页，查看可见范围为全部的问卷详情"+res);
	}
	
	@Test(description="在草稿列表页，设置问卷可见范围为仅自己",priority=13)
	public void testSetVisibleFormyself() {
		String res = QuestionnaireBusiness.setVisible(id, "myself", "");
		String result = (String)JSONPath.read(res, "$.result");
		Assert.assertEquals(result, "true","在草稿列表页，设置问卷可见范围为仅自己"+res);
	}
	
	@Test(description="在草稿列表页，查看可见范围为仅自己的问卷详情",priority=14)
	public void testQueryMyselfInfo() {
		String res = QuestionnaireBusiness.queryInfo(id);
		String visible_range = (String)JSONPath.read(res, "$.visible_range");
		Assert.assertEquals(visible_range, "myself","在草稿列表页，查看可见范围为仅自己的问卷详情"+res);
	}
	
	@Test(description="在草稿列表页，设置问卷可见范围为部分",priority=15)
	public void testSetVisibleForPart() {
		String res = QuestionnaireBusiness.setVisible(id, "part", UserBusiness.getUserId());
		String result = (String)JSONPath.read(res, "$.result");
		Assert.assertEquals(result, "true","在草稿列表页，设置问卷可见范围为部分"+res);
	}
	
	@Test(description="在草稿列表页，查看可见范围为部分的问卷详情",priority=16)
	public void testQueryPartInfo() {
		String res = QuestionnaireBusiness.queryInfo(id);
		String visible_range = (String)JSONPath.read(res, "$.visible_range");
		Assert.assertEquals(visible_range, "part","在草稿列表页，查看可见范围为部分的问卷详情"+res);
	}
	
	
	@Test(description="编辑问卷，保存到已发布",priority = 17)
	public void testEditSaveQuestionnaire() {
		String res = QuestionnaireBusiness.editQuestionnaire(edit_name, "description", "release",id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "问卷编辑成功","编辑问卷，实际返回结果："+res);
	}
	@Test(description="在已发布列表页，设置问卷可见范围为全部",priority=18)
	public void testSetreleaseVisibleForAll() {
		String res = QuestionnaireBusiness.setVisible(id, "all", "");
		String result = (String)JSONPath.read(res, "$.result");
		Assert.assertEquals(result, "true","在已发布列表页，设置问卷可见范围为全部"+res);
	}
	
	@Test(description="在已发布列表页，查看可见范围为全部的问卷详情",priority=19)
	public void testQueryreleaseAllInfo() {
		String res = QuestionnaireBusiness.queryInfo(id);
		String visible_range = (String)JSONPath.read(res, "$.visible_range");
		Assert.assertEquals(visible_range, "all","在已发布列表页，查看可见范围为全部的问卷详情"+res);
	}
	
	@Test(description="在已发布列表页，设置问卷可见范围为仅自己",priority=20)
	public void testSetreleaseVisibleFormyself() {
		String res = QuestionnaireBusiness.setVisible(id, "myself", "");
		String result = (String)JSONPath.read(res, "$.result");
		Assert.assertEquals(result, "true","在已发布列表页，设置问卷可见范围为仅自己"+res);
	}
	
	@Test(description="在已发布列表页，查看可见范围为仅自己的问卷详情",priority=21)
	public void testQueryreleaseMyselfInfo() {
		String res = QuestionnaireBusiness.queryInfo(id);
		String visible_range = (String)JSONPath.read(res, "$.visible_range");
		Assert.assertEquals(visible_range, "myself","在已发布列表页，查看可见范围为仅自己的问卷详情"+res);
	}
	
	@Test(description="在已发布列表页，设置问卷可见范围为部分",priority=22)
	public void testSetreleaseVisibleForPart() {
		String res = QuestionnaireBusiness.setVisible(id, "part", UserBusiness.getUserId());
		String result = (String)JSONPath.read(res, "$.result");
		Assert.assertEquals(result, "true","在已发布列表页，设置问卷可见范围为部分"+res);
	}
	
	@Test(description="在已发布列表页，查看可见范围为部分的问卷详情",priority=23)
	public void testQueryreleasePartInfo() {
		String res = QuestionnaireBusiness.queryInfo(id);
		String visible_range = (String)JSONPath.read(res, "$.visible_range");
		Assert.assertEquals(visible_range, "part","在已发布列表页，查看可见范围为部分的问卷详情"+res);
	}
	
	@Test(description="取消问卷",priority = 24)
	public void testCancelQuestionnaire() {
		QuestionnaireBusiness.cancelQuestionnaire(id);
	}
	
	@Test(description="删除问卷",priority = 25)
	public void testDeleteeditQuestionnaire() {
		QuestionnaireBusiness.deleteQuestionnaire(id);
	}
}
