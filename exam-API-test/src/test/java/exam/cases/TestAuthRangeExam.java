package exam.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.ExaminationBusines;
import cn.kxy.examination.business.ExaminationTaskBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAuthRangeExam {
	
	String exam_id = "";
	String random_exam_task_name = "AuthTrueExam"+CommonData.getStringRandom(5);
	@Test(description="新增考试任务--勾选监督人只能查看管辖范围内数据",priority=1)
	public void testCreatRandomExamTask() {
		String res = ExaminationTaskBusiness.creatRandomExamTask("2", "show", "20", "1", "40", "0", "0", "0", "0", "1", "30", 
				"0", "0", ExaminationBusines.getIdByKeyword(BaseBusiness.examPassName), "2", random_exam_task_name, DateUtil.getRegularDate(0), DateUtil.getRegularDate(1), 
				"false", UserBusiness.getUserId(), "45", "0", "0", "1", CertificateBusiness.getIdByKeyword(BaseBusiness.certificate_name), "20", "0", "0", "0", "0",
				"{\"missScore\":0,\"passScore\":5,\"unPassScore\":0}", "true", "false", UserBusiness.getUserId(),"true");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增考试成功！","新增考试任务--勾选监督人只能查看管辖范围内数据实际返回结果："+res);
	}
	
	@Test(description = "获取考试Id",priority = 2)
	public void testGetTrueId() {
		exam_id = ExaminationTaskBusiness.getIdByName(random_exam_task_name);
	}
	
	@Test(description = "查看考试详情，勾选监督人只能查看管辖范围内数据",priority = 3)
	public void testQueryInfoTrue() {
		String res = ExaminationTaskBusiness.queryInfoById(exam_id);
		String authority_range = (String)JSONPath.read(res, "$.authority_range");
		Assert.assertEquals(authority_range, "true","新增考试任务--勾选监督人只能查看管辖范围内数据"+res);
	}
	
	@Test(description="查看缺考/未考人数列表,显示数据受管辖范围影响",priority= 4)
 	public void testQueryUntestedInfo() {
 		String res = ExaminationTaskBusiness.queryUntestedInfo("",exam_id,"2");
 		String authority_range = (String)JSONPath.read(res, "$.data.planExam.authorityRange");
 		Assert.assertEquals(authority_range, "true","查看缺考/未考人数列表,显示数据受管辖范围影响"+res);
 	}
	
	@Test(description="删除随机考试任务",priority= 5 )
	public void testRandomExamGetScore() {
		String res = ExaminationTaskBusiness.deleteExamTask(exam_id);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除任务成功", "删除随机考试任务返回结果：" + res);
	}
	
	String exam_id_1 = "";
	String random_exam_task_name_1 = "AuthFalseExam"+CommonData.getStringRandom(5);
	@Test(description="新增考试任务--不勾选监督人只能查看管辖范围内数据",priority=6)
	public void testCreatRandomExamFasleTask() {
		String res = ExaminationTaskBusiness.creatRandomExamTask("2", "show", "20", "1", "40", "0", "0", "0", "0", "1", "30", 
				"0", "0", ExaminationBusines.getIdByKeyword(BaseBusiness.examPassName), "2", random_exam_task_name_1, DateUtil.getRegularDate(0), DateUtil.getRegularDate(1), 
				"false", UserBusiness.getUserId(), "45", "0", "0", "1", CertificateBusiness.getIdByKeyword(BaseBusiness.certificate_name), "20", "0", "0", "0", "0",
				"{\"missScore\":0,\"passScore\":5,\"unPassScore\":0}", "true", "false", UserBusiness.getUserId(),"false");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增考试成功！","新增考试任务--不勾选监督人只能查看管辖范围内数据实际返回结果："+res);
	}
	
	@Test(description = "获取考试Id",priority = 7)
	public void testGetFalseId() {
		exam_id_1 = ExaminationTaskBusiness.getIdByName(random_exam_task_name_1);
	}
	
	@Test(description = "查看考试详情，不勾选监督人只能查看管辖范围内数据",priority = 8)
	public void testQueryInfoFalse() {
		String res = ExaminationTaskBusiness.queryInfoById(exam_id_1);
		String authority_range = (String)JSONPath.read(res, "$.authority_range");
		Assert.assertEquals(authority_range, "false","新增考试任务--补勾选监督人只能查看管辖范围内数据"+res);
	}
	
	@Test(description="查看缺考/未考人数列表,补显示数据受管辖范围影响",priority= 9)
 	public void testQueryFalseInfo() {
 		String res = ExaminationTaskBusiness.queryUntestedInfo("",exam_id_1,"2");
 		String authority_range = (String)JSONPath.read(res, "$.data.planExam.authorityRange");
 		Assert.assertEquals(authority_range, "false","查看缺考/未考人数列表,不显示数据受管辖范围影响"+res);
 	}
	
	@Test(description="删除随机考试任务",priority= 10 )
	public void testDeleteExamTask() {
		String res = ExaminationTaskBusiness.deleteExamTask(exam_id_1);
		String msg = (String) JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除任务成功", "删除随机考试任务返回结果：" + res);
	}
	
}
