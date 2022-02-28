package archives.cases;

import cn.kxy.archives.business.StaffArchivesBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestQuitArchivesInfo {
	
	String user_id = "";
	@Test(description="获取离职人员的userid",priority=1)
	public void testGetUserTrainArchivesList() {
		String res = StaffArchivesBusiness.getUserTrainArchivesList("", "",  "", "","quit");
		user_id = (String)JSONPath.read(res, "$.data.detailList.list[0].userId");
	}
	
	@Test(description="查看离职员工档案全部状态列表",priority=2)
	public void testGetUserTrainArchivesDetailAllList() {
		String  res = StaffArchivesBusiness.getUserTrainArchivesDetailList("", DateUtil.getRegularDateForYYDDMM(-90),  DateUtil.getRegularDateForYYDDMM(-1), "", "0",user_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "查询成功","查看离职员工档案全部状态列表"+res);
	}
	@Test(description="查看离职员工档案的新员工列表",priority=3)
	public void testGetUserTrainArchivesDetailNewList() {
		String  res = StaffArchivesBusiness.getUserTrainArchivesDetailList("", "",  "", "", "1",user_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "查询成功","查看离职员工档案的新员工列表"+res);
	}
	@Test(description="查看离职员工档案学习列表",priority=4)
	public void testGetUserTrainArchivesDetailStudyList() {
		String  res = StaffArchivesBusiness.getUserTrainArchivesDetailList("", "",  "", "", "2",user_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "查询成功","查看离职员工档案学习列表"+res);
	}
	@Test(description="查看离职员工档案选修任务列表",priority=5)
	public void testGetUserTrainArchivesDetailList() {
		String  res = StaffArchivesBusiness.getUserTrainArchivesDetailList("", "",  "", "", "3",user_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "查询成功","查看离职员工档案选修任务列表"+res);
	}
	@Test(description="查看离职员工档案的考试列表",priority=6)
	public void testGetUserTrainArchivesDetailExamList() {
		String  res = StaffArchivesBusiness.getUserTrainArchivesDetailList("", "",  "", "", "4",user_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "查询成功","查看离职员工档案的考试列表"+res);
	}
	String quit_user_id = "";
	@Test(description="查看离职员工的自学档案列表，获取离职员工的userid",priority=7)
	public void testQuerySelfStudyquitList() {
		String res = StaffArchivesBusiness.querySelfStudyList("", "", "quit");
		quit_user_id = (String)JSONPath.read(res, "$.list[0].user_id");
	}
	
	@Test(description="查看离职员工自学档案的全部列表",priority=8)
	public void testQuerySelfStudyAllInfo() {
		String res = StaffArchivesBusiness.querySelfStudyInfo("incumbency", "",quit_user_id);
		Assert.assertTrue(res.contains("total"),"查看离职员工自学档案的全部列表"+res);
	}
	@Test(description="查看自学档案的学习中列表",priority=9)
	public void testQuerySelfStudyingInfo() {
		String res = StaffArchivesBusiness.querySelfStudyInfo("incumbency", "studying",quit_user_id);
		Assert.assertTrue(res.contains("total"),"查看自学档案的全部列表"+res);
	}
	@Test(description="查看离职员工自学档案的已完成列表",priority=10)
	public void testQuerySelfStudyFinishedInfo() {
		String res = StaffArchivesBusiness.querySelfStudyInfo("incumbency", "finished",quit_user_id);
		Assert.assertTrue(res.contains("total"),"查看离职员工自学档案的全部列表"+res);
	}
}
