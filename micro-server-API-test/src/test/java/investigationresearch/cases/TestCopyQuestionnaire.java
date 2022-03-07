package investigationresearch.cases;

import cn.kxy.investigationresearch.business.QuestionnaireBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCopyQuestionnaire{

	String name = "CopyQuestionnaire"+CommonData.getStringRandom(5);
	String edit_name = "CopyQuestionnaire"+CommonData.getStringRandom(5);
	String id = "";
	String copy_id = "";
	@Test(description="新增复制的问卷",priority = 1)
	public void testEditQuestionnaireToRelease() {
		String res = QuestionnaireBusiness.addNormalQuestionnaire(name, "this is a description", "draft");
		id = QuestionnaireBusiness.getIdByKeyword(name, "draft");
		
	}
	
	@Test(description= "在草稿箱复制复制的问卷",priority = 2)
	public void copyQuestionnaire() {
		String res = QuestionnaireBusiness.copyQuestionnaire(id);
		copy_id = (String)JSONPath.read(res, "$.data");
		Assert.assertTrue(res.contains("问卷复制成功"),"在草稿箱复制复制的问卷"+res);
	}
	
	@Test(description="在草稿列表页，设置复制的问卷可见范围为全部",priority=3)
	public void testSetVisibleForAll() {
		String res = QuestionnaireBusiness.setVisible(copy_id, "all", "");
		String result = (String)JSONPath.read(res, "$.result");
		Assert.assertEquals(result, "true","在草稿列表页，设置复制的问卷可见范围为全部"+res);
	}
	
	@Test(description="在草稿列表页，查看可见范围为全部的复制的问卷详情",priority=4)
	public void testQueryAllInfo() {
		String res = QuestionnaireBusiness.queryInfo(copy_id);
		String visible_range = (String)JSONPath.read(res, "$.visible_range");
		Assert.assertEquals(visible_range, "all","在草稿列表页，查看可见范围为全部的复制的问卷详情"+res);
	}
	
	@Test(description="在草稿列表页，设置复制的问卷可见范围为仅自己",priority=5)
	public void testSetVisibleFormyself() {
		String res = QuestionnaireBusiness.setVisible(copy_id, "myself", "");
		String result = (String)JSONPath.read(res, "$.result");
		Assert.assertEquals(result, "true","在草稿列表页，设置复制的问卷可见范围为仅自己"+res);
	}
	
	@Test(description="在草稿列表页，查看可见范围为仅自己的复制的问卷详情",priority=6)
	public void testQueryMyselfInfo() {
		String res = QuestionnaireBusiness.queryInfo(copy_id);
		String visible_range = (String)JSONPath.read(res, "$.visible_range");
		Assert.assertEquals(visible_range, "myself","在草稿列表页，查看可见范围为仅自己的复制的问卷详情"+res);
	}
	
	@Test(description="在草稿列表页，设置复制的问卷可见范围为部分",priority=7)
	public void testSetVisibleForPart() {
		String res = QuestionnaireBusiness.setVisible(copy_id, "part", UserBusiness.getUserId());
		String result = (String)JSONPath.read(res, "$.result");
		Assert.assertEquals(result, "true","在草稿列表页，设置复制的问卷可见范围为部分"+res);
	}
	
	@Test(description="在草稿列表页，查看可见范围为部分的复制的问卷详情",priority=8)
	public void testQueryPartInfo() {
		String res = QuestionnaireBusiness.queryInfo(copy_id);
		String visible_range = (String)JSONPath.read(res, "$.visible_range");
		Assert.assertEquals(visible_range, "part","在草稿列表页，查看可见范围为部分的复制的问卷详情"+res);
	}
	
	
	@Test(description="编辑复制的问卷，保存到已发布",priority = 9)
	public void testEditSaveQuestionnaire() {
		String res = QuestionnaireBusiness.editQuestionnaire(edit_name, "description", "release",copy_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "问卷编辑成功","编辑复制的问卷，实际返回结果："+res);
	}
	@Test(description="在已发布列表页，设置复制的问卷可见范围为全部",priority=10)
	public void testSetreleaseVisibleForAll() {
		String res = QuestionnaireBusiness.setVisible(copy_id, "all", "");
		String result = (String)JSONPath.read(res, "$.result");
		Assert.assertEquals(result, "true","在已发布列表页，设置复制的问卷可见范围为全部"+res);
	}
	
	@Test(description="在已发布列表页，查看可见范围为全部的复制的问卷详情",priority=11)
	public void testQueryreleaseAllInfo() {
		String res = QuestionnaireBusiness.queryInfo(copy_id);
		String visible_range = (String)JSONPath.read(res, "$.visible_range");
		Assert.assertEquals(visible_range, "all","在已发布列表页，查看可见范围为全部的复制的问卷详情"+res);
	}
	
	@Test(description="在已发布列表页，设置复制的问卷可见范围为仅自己",priority=12)
	public void testSetreleaseVisibleFormyself() {
		String res = QuestionnaireBusiness.setVisible(copy_id, "myself", "");
		String result = (String)JSONPath.read(res, "$.result");
		Assert.assertEquals(result, "true","在已发布列表页，设置复制的问卷可见范围为仅自己"+res);
	}
	
	@Test(description="在已发布列表页，查看可见范围为仅自己的复制的问卷详情",priority=13)
	public void testQueryreleaseMyselfInfo() {
		String res = QuestionnaireBusiness.queryInfo(copy_id);
		String visible_range = (String)JSONPath.read(res, "$.visible_range");
		Assert.assertEquals(visible_range, "myself","在已发布列表页，查看可见范围为仅自己的复制的问卷详情"+res);
	}
	
	@Test(description="在已发布列表页，设置复制的问卷可见范围为部分",priority=14)
	public void testSetreleaseVisibleForPart() {
		String res = QuestionnaireBusiness.setVisible(copy_id, "part", UserBusiness.getUserId());
		String result = (String)JSONPath.read(res, "$.result");
		Assert.assertEquals(result, "true","在已发布列表页，设置复制的问卷可见范围为部分"+res);
	}
	
	@Test(description="在已发布列表页，查看可见范围为部分的复制的问卷详情",priority=15)
	public void testQueryreleasePartInfo() {
		String res = QuestionnaireBusiness.queryInfo(copy_id);
		String visible_range = (String)JSONPath.read(res, "$.visible_range");
		Assert.assertEquals(visible_range, "part","在已发布列表页，查看可见范围为部分的复制的问卷详情"+res);
	}
	
	@Test(description="取消复制的复制的问卷复制的问卷",priority = 16)
	public void testCancelCopyQuestionnaire() {
		String res = QuestionnaireBusiness.cancelQuestionnaire(copy_id);
		Assert.assertTrue(res.contains("问卷取消发布成功"),"在草稿箱复制复制的问卷"+res);
	}
	
	@Test(description="删除复制的问卷",priority = 17)
	public void testDeleteeditCopyQuestionnaire() {
		String res = QuestionnaireBusiness.deleteQuestionnaire(copy_id);
		Assert.assertTrue(res.contains("问卷删除成功"),"在草稿箱复制复制的问卷"+res);
	}
	
	@Test(description="取消问卷",priority = 18)
	public void testCancelQuestionnaire() {
		 QuestionnaireBusiness.cancelQuestionnaire(id);
	}
	
	@Test(description="删除问卷",priority = 19)
	public void testDeleteeditQuestionnaire() {
		QuestionnaireBusiness.deleteQuestionnaire(id);
	}
}
