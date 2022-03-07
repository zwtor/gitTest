package authentication.cases;

import cn.kxy.authentication.business.AppPostAuthenticationBusiness;
import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.authentication.business.PostAuthenticationBusiness;
import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.lazy.init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPostAuthTrick extends InitStudyAuthCourse{

	String title_01 = "PostTrick"+CommonData.getStringRandom(5);
	String art_id_01 = "";
	String art_id_02 = "";
	String art_id_03 = "";
	String qualifications_id_01 = "";
	String art_name_01 = "painting"+CommonData.getStringRandom(3);
	String art_name_02 = "Computer"+CommonData.getStringRandom(3);
	@Test(description = "添加供学习任务使用的课件",priority = 1)
	public void testAddArticle_01() {
		ArticleBusiness.addArticle(art_name_01,"This is a description","0");
	}
	@Test(description = "添加供学习任务使用的课件",priority = 2)
	public void testAddArticle_02() {
		ArticleBusiness.addArticle(art_name_02,"This is a description","0");
	}
	@Test(description = "获取第一个课件Id",priority = 3)
	public void testGetFirstPostId() {
		 art_id_01 = ArticleBusiness.getIdByKeyword(art_name_01);
	}
	@Test(description = "获取第二个课件Id",priority = 4)
	public void testGetSecondId() {
		 art_id_02 = ArticleBusiness.getIdByKeyword(art_name_02);
	}
	@Test(description = "获取第三个课件Id",priority = 5)
	public void testGetThirdId() {
		 art_id_03 = ArticleBusiness.getIdByKeyword(articlename);
	}
	@Test(description = "添加阶段间闯关的岗位认证",priority = 6)
	public void testAddPostTrick() {
		art_id_03 = ArticleBusiness.getIdByKeyword(articlename);
		String res = PostAuthenticationBusiness.addPostTrick(title_01, CertificateBusiness.getIdByKeyword(cert_name),
				"true", art_id_01,art_id_02,art_id_03);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！","添加岗位认证"+res);
	}
	@Test(description = "获取岗位认证的id",priority = 7)
	public void testGetFirstId() {
		String res = PostAuthenticationBusiness.queryList(title_01, "0");
		qualifications_id_01 = (String)JSONPath.read(res, "$.list[0].id");
	}
	
	@Test(description = "查询App端岗位详情，各个阶段任务的can_start_study字段校验",priority = 8)
	public void testQueryFirstInfo() {
		String res =AppPostAuthenticationBusiness.queryAppPostItems(qualifications_id_01);
		AppPostAuthenticationBusiness.loadResource(qualifications_id_01);
		String first_can = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].can_start_study");
		String second_can = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[1].can_start_study");
		String third_can = (String)JSONPath.read(res, "$.stage_list[1].course_mapping_list[0].can_start_study");
		Assert.assertEquals(first_can, "true","查询App端岗位详情，第一个阶段第一个任务的can_start_study字段校验"+res);
		Assert.assertEquals(second_can, "false","查询App端岗位详情，第一个阶段第二个任务的can_start_study字段校验"+res);
		Assert.assertEquals(third_can, "false","查询App端岗位详情，第二个阶段第一个任务的can_start_study字段校验"+res);
	}
	@Test(description = "保存第一阶段的第一个任务",priority = 9)
	public void testSaveFirstProgress() {
		String res = AppPostAuthenticationBusiness.saveProgress("100", qualifications_id_01, art_id_01, art_id_01);
		int progress = (int)JSONPath.read(res, "$.progress");
		Assert.assertEquals(progress, 100,"保存第一阶段的第一个任务,实际返回结果："+res);
	}
	
	@Test(description = "保存第一阶段的第一个任务,查询App端岗位详情,各个阶段任务的can_start_study字段校验",priority = 10)
	public void testQuerySecondInfo() {
		String res =AppPostAuthenticationBusiness.queryAppPostItems(qualifications_id_01);
		String first_can = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].can_start_study");
		String second_can = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[1].can_start_study");
		String third_can = (String)JSONPath.read(res, "$.stage_list[1].course_mapping_list[0].can_start_study");
		Assert.assertEquals(first_can, "true","查询App端岗位详情，第一个阶段第一个任务的can_start_study字段校验"+res);
		Assert.assertEquals(second_can, "true","查询App端岗位详情，第一个阶段第二个任务的can_start_study字段校验"+res);
		Assert.assertEquals(third_can, "false","查询App端岗位详情，第二个阶段第一个任务的can_start_study字段校验"+res);

	}
	@Test(description = "保存第一阶段的第二个任务",priority = 11)
	public void testSaveSecondProgress() {
		String res = AppPostAuthenticationBusiness.saveProgress("100", qualifications_id_01, art_id_02, art_id_02);
		int progress = (int)JSONPath.read(res, "$.progress");
		Assert.assertEquals(progress, 100,"保存第一阶段的第二个任务,实际返回结果："+res);
	}
	
	@Test(description = "保存第一阶段的第二个任务,查询App端岗位详情,各个阶段任务的can_start_study字段校验",priority = 12)
	public void testQueryThirdInfo() {
		String res =AppPostAuthenticationBusiness.queryAppPostItems(qualifications_id_01);
		String first_can = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].can_start_study");
		String second_can = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[1].can_start_study");
		String third_can = (String)JSONPath.read(res, "$.stage_list[1].course_mapping_list[0].can_start_study");
		Assert.assertEquals(first_can, "true","查询App端岗位详情，第一个阶段第一个任务的can_start_study字段校验"+res);
		Assert.assertEquals(second_can, "true","查询App端岗位详情，第一个阶段第二个任务的can_start_study字段校验"+res);
		Assert.assertEquals(third_can, "true","查询App端岗位详情，第二个阶段第一个任务的can_start_study字段校验"+res);

	}
	
	@Test(description = "保存第二阶段的第一个任务",priority = 13)
	public void testSaveThirdProgress() {
		String res = AppPostAuthenticationBusiness.saveProgress("100", qualifications_id_01, art_id_03, art_id_03);
		int progress = (int)JSONPath.read(res, "$.progress");
		Assert.assertEquals(progress, 100,"保存第二阶段的第一个任务,实际返回结果："+res);
	}
	
	@Test(description = "保存第二阶段的第一个任务,查询App端岗位详情,各个阶段任务的can_start_study字段校验",priority = 14)
	public void testQueryThirdPostInfo() {
		String res =AppPostAuthenticationBusiness.queryAppPostItems(qualifications_id_01);
		String first_can = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].can_start_study");
		String second_can = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[1].can_start_study");
		String third_can = (String)JSONPath.read(res, "$.stage_list[1].course_mapping_list[0].can_start_study");
		Assert.assertEquals(first_can, "true","查询App端岗位详情，第一个阶段第一个任务的can_start_study字段校验"+res);
		Assert.assertEquals(second_can, "true","查询App端岗位详情，第一个阶段第二个任务的can_start_study字段校验"+res);
		Assert.assertEquals(third_can, "true","查询App端岗位详情，第二个阶段第一个任务的can_start_study字段校验"+res);

	}
	
	@Test(description = "删除岗位认证",priority = 15)
	public void testDeleteAuthentication() {
		String res = PostAuthenticationBusiness.deleteAuthentication(qualifications_id_01);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "能力认证删除成功","删除岗位认证，实际返回结果："+res);
	}
	String title_02 = "TrickPost"+CommonData.getStringRandom(5);
	String qualifications_id_02 = "";
	@Test(description = "新增stagePass为false的岗位认证",priority = 16)
	public void testAddPostTrickStagePassIsFalse() {
		art_id_03 = ArticleBusiness.getIdByKeyword(articlename);
		String res = PostAuthenticationBusiness.addPostTrick(title_02, CertificateBusiness.getIdByKeyword(cert_name),
				"false", art_id_01,art_id_02,art_id_03);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "新增计划成功！","添加岗位认证"+res);
	}
	
	@Test(description = "StagePassIsFalse查询App端岗位详情，各个阶段任务的can_start_study字段校验",priority = 17)
	public void testQueryFirstInfoStagePassIsFalse() {
		String list_res = PostAuthenticationBusiness.queryList(title_02, "0");
		qualifications_id_02 = (String)JSONPath.read(list_res, "$.list[0].id");
		String res =AppPostAuthenticationBusiness.queryAppPostItems(qualifications_id_02);
		AppPostAuthenticationBusiness.loadResource(qualifications_id_02);
		String first_can = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].can_start_study");
		String second_can = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[1].can_start_study");
		String third_can = (String)JSONPath.read(res, "$.stage_list[1].course_mapping_list[0].can_start_study");
		Assert.assertEquals(first_can, "true","查询App端岗位详情，第一个阶段第一个任务的can_start_study字段校验"+res);
		Assert.assertEquals(second_can, "false","查询App端岗位详情，第一个阶段第二个任务的can_start_study字段校验"+res);
		Assert.assertEquals(third_can, "true","查询App端岗位详情，第二个阶段第一个任务的can_start_study字段校验"+res);
	}
	@Test(description = "StagePassIsFalse保存第一阶段的第一个任务",priority = 18)
	public void testSaveFirstProgressStagePassIsFalse() {
		String res = AppPostAuthenticationBusiness.saveProgress("100", qualifications_id_02, art_id_01, art_id_01);
		int progress = (int)JSONPath.read(res, "$.progress");
		Assert.assertEquals(progress, 100,"保存第一阶段的第一个任务,实际返回结果："+res);
	}
	
	@Test(description = "StagePassIsFalse保存第一阶段的第一个任务,查询App端岗位详情,各个阶段任务的can_start_study字段校验",priority = 19)
	public void testQuerySecondInfoStagePassIsFalse() {
		String res =AppPostAuthenticationBusiness.queryAppPostItems(qualifications_id_02);
		String first_can = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].can_start_study");
		String second_can = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[1].can_start_study");
		String third_can = (String)JSONPath.read(res, "$.stage_list[1].course_mapping_list[0].can_start_study");
		Assert.assertEquals(first_can, "true","查询App端岗位详情，第一个阶段第一个任务的can_start_study字段校验"+res);
		Assert.assertEquals(second_can, "true","查询App端岗位详情，第一个阶段第二个任务的can_start_study字段校验"+res);
		Assert.assertEquals(third_can, "true","查询App端岗位详情，第二个阶段第一个任务的can_start_study字段校验"+res);

	}
	@Test(description = "StagePassIsFalse保存第一阶段的第二个任务",priority = 20)
	public void testSaveSecondProgressStagePassIsFalse() {
		String res = AppPostAuthenticationBusiness.saveProgress("100", qualifications_id_02, art_id_02, art_id_02);
		int progress = (int)JSONPath.read(res, "$.progress");
		Assert.assertEquals(progress, 100,"保存第一阶段的第二个任务,实际返回结果："+res);
	}
	
	@Test(description = "StagePassIsFalse保存第一阶段的第二个任务,查询App端岗位详情,各个阶段任务的can_start_study字段校验",priority = 21)
	public void testQueryThirdInfoStagePassIsFalse() {
		String res =AppPostAuthenticationBusiness.queryAppPostItems(qualifications_id_02);
		String first_can = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].can_start_study");
		String second_can = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[1].can_start_study");
		String third_can = (String)JSONPath.read(res, "$.stage_list[1].course_mapping_list[0].can_start_study");
		Assert.assertEquals(first_can, "true","查询App端岗位详情，第一个阶段第一个任务的can_start_study字段校验"+res);
		Assert.assertEquals(second_can, "true","查询App端岗位详情，第一个阶段第二个任务的can_start_study字段校验"+res);
		Assert.assertEquals(third_can, "true","查询App端岗位详情，第二个阶段第一个任务的can_start_study字段校验"+res);

	}
	
	@Test(description = "StagePassIsFalse保存第二阶段的第一个任务",priority = 22)
	public void testSaveThirdProgressStagePassIsFalse() {
		String res = AppPostAuthenticationBusiness.saveProgress("100", qualifications_id_02, art_id_03, art_id_03);
		int progress = (int)JSONPath.read(res, "$.progress");
		Assert.assertEquals(progress, 100,"保存第二阶段的第一个任务,实际返回结果："+res);
	}
	
	@Test(description = "StagePassIsFalse保存第二阶段的第一个任务,查询App端岗位详情,各个阶段任务的can_start_study字段校验",priority = 23)
	public void testQueryThirdPostInfoStagePassIsFalse() {
		String res =AppPostAuthenticationBusiness.queryAppPostItems(qualifications_id_02);
		String first_can = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[0].can_start_study");
		String second_can = (String)JSONPath.read(res, "$.stage_list[0].course_mapping_list[1].can_start_study");
		String third_can = (String)JSONPath.read(res, "$.stage_list[1].course_mapping_list[0].can_start_study");
		Assert.assertEquals(first_can, "true","查询App端岗位详情，第一个阶段第一个任务的can_start_study字段校验"+res);
		Assert.assertEquals(second_can, "true","查询App端岗位详情，第一个阶段第二个任务的can_start_study字段校验"+res);
		Assert.assertEquals(third_can, "true","查询App端岗位详情，第二个阶段第一个任务的can_start_study字段校验"+res);

	}
	@Test(description = "StagePassIsFalse删除岗位认证",priority = 24)
	public void testStagePassIsFalseDeleteAuthentication() {
		String res = PostAuthenticationBusiness.deleteAuthentication(qualifications_id_02);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "能力认证删除成功","删除岗位认证，实际返回结果："+res);
	}
	
	@Test(description =  "删除引用的第一个课件",priority = 25)
	public void testDeleteFirstArticle() {
		String del_res = ArticleBusiness.deleteArticle(art_id_01);
		String msg = (String) JSONPath.read(del_res, "$.msg"); 
		Assert.assertEquals(msg,"删除课件成功", "删除文章实际返回结果：" + del_res); 
	}
	@Test(description = "删除引用的第二个课件",priority = 26)
	public void testDeleteSecondArticle() {
		String del_res = ArticleBusiness.deleteArticle(art_id_02);
		String msg = (String) JSONPath.read(del_res, "$.msg"); 
		Assert.assertEquals(msg,"删除课件成功", "删除文章实际返回结果：" + del_res); 
	}
	
}
