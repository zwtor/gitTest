package group.cases;

/**
 * @author xieteng 
  * 全站人员信息
 */

import cn.kxy.archives.business.CertificateRanksBusiness;
import cn.kxy.archives.business.ScoreRankListBusiness;
import cn.kxy.archives.business.StaffArchivesBusiness;
import cn.kxy.group.a.business.InteractionBusiness;
import cn.kxy.group.a.business.StationUserinfoBusiness;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestStationUserinfo {
	

	@Test(description = "PK赛报表人员基础信息", priority = 1)
	public void testPkReportlistUserinfo() {
		String res = InteractionBusiness.PkReportList("");
		JSONObject uesrinfo = (JSONObject) JSONPath.read(res, "$.data.list[0].user_info");
		Assert.assertTrue(uesrinfo != null, "PK赛报表人员基础信息：" + res);
	}

	@Test(description = "查询证书排行榜人员基础信息", priority = 2)
	public void testGetCertificateRankUserinfo() {
		String res = CertificateRanksBusiness.getCertificateRankList("", "", "");
		JSONObject user_info = (JSONObject) JSONPath.read(res, "$.list[0].user_info");
		Assert.assertTrue(user_info != null, "证书排行榜人员基础信息：" + res);
	}

	@Test(description = "查看认证档案人员基础信息", priority = 3)
	public void testquerySelfArchivesUserinfo() {
		String res = StaffArchivesBusiness.queryQualificationArchivesList("", "", "incumbency");
		JSONObject uesrinfo = (JSONObject) JSONPath.read(res, "$.list[0].user_info");
		Assert.assertTrue(uesrinfo != null, "查看认证档案人员基础信息" + res);
	}

	@Test(description = "查看学分排行榜人员基础信息", priority = 4)
	public void testgetScoreRankUserinfo() {
		String res = ScoreRankListBusiness.getScoreRankList("", "", "");
		JSONObject user_info = (JSONObject) JSONPath.read(res, "$.list[0].user_info");
		Assert.assertTrue(user_info != null, "查看学分排行榜人员基础信息" + res);
	}

	@Test(description = "查看自学档案人员基础信息", priority = 5)
	public void testquerySelfStudyUserinfo() {
		String res = StaffArchivesBusiness.querySelfStudyList("", "", "");
		JSONObject uesrinfo = (JSONObject) JSONPath.read(res, "$.list[0].user_info");
		Assert.assertTrue(uesrinfo != null, "查看自学档案人员基础信息" + res);
	}

	@Test(description = "查看任务档案人员基础信息", priority = 6)
	public void testgetUserTrainArchivesUserinfo() {
		String res = StaffArchivesBusiness.getUserTrainArchivesList("", "", "", "", "incumbency");
		JSONObject uesrinfo = (JSONObject) JSONPath.read(res, "$.list[0].user_info");
		Assert.assertTrue(uesrinfo != null, "查看任务档案人员基础信息" + res);
	}

	@Test(description = "查看签到列表人员基础信息", priority = 7)
	public void testSignListUserinfo() {
		String res = StationUserinfoBusiness.querySignlistUserinfo("", "", "", "", "");
		JSONObject uesrinfo = (JSONObject) JSONPath.read(res, "$.list[0].user_info");
		Assert.assertTrue(uesrinfo != null, "查看签到列表人员基础信息" + res);
	}

	@Test(description = "考试任务人员监控列表人员基础信息", priority = 8)
	public void testExamUserinfo() {
		String res = StationUserinfoBusiness.getexamUserinfo("", "", "");
		JSONObject uesrinfo = (JSONObject) JSONPath.read(res, "$.data.planUser.list[0].user_info");
		Assert.assertTrue(uesrinfo != null, "考试任务人员监控列表人员基础信息" + res);
	}

	@Test(description = "学习任务人员监控列表人员基础信息", priority = 9)
	public void testStudyUserinfo() {
		String res = StationUserinfoBusiness.getStudyTaskUserinfo("","","");
		JSONObject uesrinfo = (JSONObject) JSONPath.read(res, "$.data.voList.list[0].user_info");
		Assert.assertTrue(uesrinfo != null, "学习任务人员监控列表人员基础信息" + res);
	} 

	@Test(description = "角色管理人员监控列表人员基础信息", priority = 10)
	public void testtUsersByRoleUserinfo() {
		String res =StationUserinfoBusiness.getUsersByRoleUserinfo("");
		JSONObject uesrinfo = (JSONObject)  JSONPath.read(res, "$.userList.list[0].user_info");
		Assert.assertTrue(uesrinfo != null, "角色管理人员监控列表人员基础信息" + res);
	} 

}
