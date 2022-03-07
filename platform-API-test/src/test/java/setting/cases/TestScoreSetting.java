package setting.cases;

import cn.kxy.setting.bussiness.ScoreSettingBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestScoreSetting {

	
	@Test(description="设置学分规则",priority=1)
	public void testAddScoreRule() {
		String res = ScoreSettingBusiness.addScoreRule();
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "success",""+res);
	}
	
	@Test(description="获取学分规则",priority=2)
	public void testGetScoreRule() {
		String res = ScoreSettingBusiness.getScoreRule();
		int exam_maxScore = (int)JSONPath.read(res, "$.data.examScore[0].maxScore");
		int stu_maxScore = (int)JSONPath.read(res, "$.data.studyScore[0].maxScore");
		Assert.assertEquals(exam_maxScore, 500,""+res);
		Assert.assertEquals(stu_maxScore, 500,""+res);
	}
}
