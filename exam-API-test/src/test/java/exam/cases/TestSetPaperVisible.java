package exam.cases;

import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestSetPaperVisible {

	String paper_name = "paper_name"+CommonData.getStringRandom(5);
	
	String paper_id = "";
	@Test(description="新增试卷",priority=1)
	public void testCreatPaper() {
		String res = PaperBusiness.creatPaper(paper_name, "instruction", "150", "90", "60",PaperBusiness.getFirstquestionid() , "100", PaperBusiness.getSecondquestionid(), "50");
		String msg = (String) JSONPath.read(res, "$.msg");
		String list_res = PaperBusiness.queryList(paper_name, "false");
		paper_id = (String)JSONPath.read(list_res, "$.list[0].id");
		Assert.assertEquals(msg, "新增试卷成功！" ,"新增试卷返回结果："+res);
	}
	@Test(description="设置试卷的可见范围为全部",priority=2)
	public void testUpdatePaperVisibleForAll() {
		String res = PaperBusiness.updatePaperVisible(paper_id, "all", "");
		Assert.assertTrue(res.contains("result"), "设置试卷的可见范围为全部,实际返回结果："+res);
	}
	@Test(description="设置试卷的可见范围为仅自己",priority=3)
	public void testUpdatePaperVisibleForMySelf() {
		String res = PaperBusiness.updatePaperVisible(paper_id, "myself", "");
		Assert.assertTrue(res.contains("result"), "设置试卷的可见范围为仅自己,实际返回结果："+res);
	}
	@Test(description="设置试卷的可见范围为部门",priority=4)
	public void testUpdatePaperVisibleForPart() {
		String res = PaperBusiness.updatePaperVisible(paper_id, "part", UserBusiness.getUserId());
		Assert.assertTrue(res.contains("result"), "设置试卷的可见范围为部门,实际返回结果："+res);
	}
	@Test(description="删除设置可见范围后的试卷",priority=5)
	public void testDeletePaper() {
		String res = PaperBusiness.deletePaper(paper_id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除试卷成功","删除试卷返回结果："+res);
	}
}
