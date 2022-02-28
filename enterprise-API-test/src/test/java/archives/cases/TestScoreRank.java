package archives.cases;

import cn.kxy.archives.business.ScoreRankListBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestScoreRank {

	String user_name = "exam";
	@Test(description="根据时间查询学分排行榜",priority=1)
	public void testGetScoreRankList() {
		String res = ScoreRankListBusiness.getScoreRankList("", DateUtil.getTimeStamp(-90, ""),  DateUtil.getTimeStamp(-1, ""));
		String name = (String)JSONPath.read(res, "$.list[0].name");
		Assert.assertNotNull(name, "根据时间查询学分排行榜"+res);
	}
	@Test(description="根据用户名查询学分排行榜",priority=2)
	public void testGetScoreRankListByuserName() {
		String res = ScoreRankListBusiness.getScoreRankList(user_name, "", "");
		String name = (String)JSONPath.read(res, "$.list[0].name");
		Assert.assertNotNull(name, "根据用户名查询学分排行榜"+res);
	}
	@Test(description="查询总学分",priority=3)
	public void testGetMyScoreSummary() {
		String res = ScoreRankListBusiness.getMyScoreSummary();
		int totalScore = (int)JSONPath.read(res, "$.totalScore");
		Assert.assertNotNull(totalScore, "查询总学分，实际返回结果："+res);
	}
	String id= "";
	@Test(description="查看学分排行榜的详情信息",priority=4)
	public void testGetScoreRecord() {
		String res = ScoreRankListBusiness.getScoreRecord("","");
		id=(String)JSONPath.read(res, "$.list[0].id");
	}
	@Test(description="撤销学分",priority=5)
	public void testDeleteScore() {
		String res = ScoreRankListBusiness.deleteScore(id);
		String deleted = (String)JSONPath.read(res, "$.deleted");
		Assert.assertEquals(deleted, "true","撤销学分"+res);
	}
	
	@Test(description = "清除所有导出数据",priority = 6)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	
	@Test(description="导出学分排行榜",priority=7)
	public void testGetExportScoreRankCode() {
		int code = ScoreRankListBusiness.getExportScoreRankCode();
		Assert.assertEquals(code, 200,"导出学分排行榜:"+code);
	}
	@Test(description="导出学分排行榜",priority=8)
	public void testGetExportScoreRankCodeByUser() {
		int code = ScoreRankListBusiness.getExportScoreRankCodeByUser();
		Assert.assertEquals(code, 200,"导出学分排行榜:"+code);
	}
	@Test(description="导出学分排行榜",priority=9)
	public void testGetExportScoreRankCodeByTime() {
		int code = ScoreRankListBusiness.getExportScoreRankCodeByTime(DateUtil.getTimeStamp(-90, ""),  DateUtil.getTimeStamp(-1, ""));
		Assert.assertEquals(code, 200,"导出学分排行榜:"+code);
	}
	@Test(description = "导出学分明细", priority= 10)
	public void exportScoreRecordByUser () {
		int code = ScoreRankListBusiness.exportScoreRecordByUser();
		Assert.assertEquals(code, 200,"导出学分明细:"+code);
	}
	
	@Test(description = "导出学分明细后，在下载中心查看",priority  = 11)
	public void exportRecordList() {
		String res = PaperExportBusiness.exportRecordList();
		String status = (String)JSONPath.read(res, "$.list[0].status");
		String status_1 = (String)JSONPath.read(res, "$.list[1].status");
		Assert.assertFalse(status=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_1=="FAILED", "查看导出的结果:"+res);
	}
}
