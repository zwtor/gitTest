package group.cases;

/**
 * @author xieteng 
  * 全站表头自定义
 */

import cn.kxy.group.a.business.StationCustomTableBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestStationCostomTable {

	@Test(description = "修改【个人中心-培训档案-任务档案】表格不显示所有字段", priority = 1)
	public void testNodisplayArchivescostum() {
		String table_key = "c40f443e-c723-4ab1-8921-2f5433137f5f";
		String res = StationCustomTableBusiness.savaArchives_tablehead(table_key, false);
		Object show = (Object) JSONPath.read(res, "$.data.fields[0].show");
		Assert.assertEquals(show , "false", "【个人中心-培训档案-任务档案】不显示所有字段：" + res);
	}

	@Test(description = "【个人中心-培训档案-任务档案】表格显示所有字段", priority = 2)
	public void testdisplayArchivescostum() {
		String table_key = "c40f443e-c723-4ab1-8921-2f5433137f5f";
		String res = StationCustomTableBusiness.savaArchives_tablehead(table_key, true);
		Object show = (Object) JSONPath.read(res, "$.data.fields[0].show");
		Assert.assertEquals(show, "true", "【个人中心-培训档案-任务档案】显示所有字段：" + res);
	}
	
	@Test(description = "修改【个人中心-培训档案-证书记录】表格不显示所有字段", priority = 3)
	public void testNodisplayCertificatecostum() {
		String table_key = "47dc683b-d0ad-45be-8ecd-01029a71984b";
		String res = StationCustomTableBusiness.saveCertificate_tablehead(table_key, false);
		Object show = (Object) JSONPath.read(res, "$.data.fields[0].show");
		Assert.assertEquals(show, "false", "【个人中心-培训档案-证书记录】不显示所有字段：" + res);
	}
	
	@Test(description = "【个人中心-培训档案-证书记录】列表显示所有字段", priority = 4)
	public void testdisplayCertificatecostum() {
		String table_key = "47dc683b-d0ad-45be-8ecd-01029a71984b";
		String res = StationCustomTableBusiness.saveCertificate_tablehead(table_key, true);
		Object show = (Object) JSONPath.read(res, "$.data.fields[0].show");
		Assert.assertEquals(show, "true", "【个人中心-培训档案-证书记录】显示所有字段：" + res);
	}
	
	@Test(description = "【培训-学习-学习任务】列表显示所有字段", priority = 5)
	public void testdisplayStudytaskcostum() {
		String table_key = "5863e669-72f2-4e9f-adc1-a197feb12b4f";
		String res = StationCustomTableBusiness.savaStudytask_tablehead(table_key, true);
		Object show = (Object) JSONPath.read(res, "$.data.fields[0].show");
		Assert.assertEquals(show, "true", "【培训-学习-学习任务】列表显示所有字段：" + res);
	}
	
	@Test(description = "【知识库-学习资源-学习项目】列表显示所有字段", priority = 6)
	public void testdisplayStudyprojectcostum() {
		String table_key = "262e3d8a-6bd3-498e-8dc9-026d4f5c80d6";
		String res = StationCustomTableBusiness.savaStudyproject_tablehead(table_key, true);
		Object show = (Object) JSONPath.read(res, "$.data.fields[0].show");
		Assert.assertEquals(show, "true", "【知识库-学习资源-学习项目】列表显示所有字段：" + res);
	}
	
	@Test(description = "【知识库-考试资源-试卷】列表显示所有字段", priority = 7)
	public void testdisplayexampapercostum() {
		String table_key = "9bfbd22a-38da-42b2-8bfe-9bd31d7207af";
		String res = StationCustomTableBusiness.savaexampaper_tablehead(table_key, true);
		Object show = (Object) JSONPath.read(res, "$.data.fields[0].show");
		Assert.assertEquals(show, "true", "【知识库-考试资源-试卷】列表显示所有字段：" + res);
	}
	
	@Test(description = "【培训-考试-考试任务】列表显示所有字段", priority = 8)
	public void testdisplayexamtaskcostum() {
		String table_key = "4a8a9a39-9885-4ddf-9619-83a029259b0c";
		String res = StationCustomTableBusiness.savaexamtask_tablehead(table_key, true);
		Object show = (Object) JSONPath.read(res, "$.data.fields[0].show");
		Assert.assertEquals(show, "true", "【培训-考试-考试任务】列表显示所有字段：" + res);
		}
	
	@Test(description = "【人才发展-练习-PK赛】列表显示所有字段", priority = 9)
	public void testdisplayPKmatchcostum() {
		String table_key = "bf790c6c-a098-480c-a765-27c38daa661b";
		String res = StationCustomTableBusiness.savaPKmatch_tablehead(table_key, true);
		Object show = (Object) JSONPath.read(res, "$.data.fields[0].show");
		Assert.assertEquals(show, "true", "【人才发展-练习-PK赛】列表显示所有字段：" + res);
		}
	@Test(description = "【人才发展-测评-测训任务】列表显示所有字段", priority = 10)
	public void testdisplayTraintaskcostum() {
		String table_key = "2ef3d135-560b-436a-9e50-d04cb25e6d7b";
		String res = StationCustomTableBusiness.savaTraintask_tablehead(table_key, true);
		Object show = (Object) JSONPath.read(res, "$.data.fields[0].show");
		Assert.assertEquals(show, "true", "【人才发展-测评-测训任务】列表显示所有字段：" + res);
		}
	
	@Test(description = "【人才发展-认证-岗位认证】列表显示所有字段", priority = 11)
	public void testdisplayAuthencostum() {
		String table_key = "1a371bca-cb10-4e15-ab1e-676a9e8598ba";
		String res = StationCustomTableBusiness.savaAuthen_tablehead(table_key, true);
		Object show = (Object) JSONPath.read(res, "$.data.fields[0].show");
		Assert.assertEquals(show, "true", "【人才发展-认证-岗位认证】列表显示所有字段：" + res);
		}
	
	@Test(description = "【报表-排行-学分排行榜】列表显示所有字段", priority = 12)
	public void testdisplayCreditRankcostum() {
		String table_key = "62e3b90b-686f-45d2-8a0c-0ec9a6385111";
		String res = StationCustomTableBusiness.savaCreditRank_tablehead(table_key, true);
		Object show = (Object) JSONPath.read(res, "$.data.fields[0].show");
		Assert.assertEquals(show, "true", "【报表-排行-学分排行榜】列表显示所有字段：" + res);
		}
	
}
