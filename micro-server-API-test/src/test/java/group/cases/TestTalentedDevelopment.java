package group.cases;

/**
 * @author wenlingzhi
 *2021年9月1日
 */

import cn.kxy.group.a.business.TalentedDevelopmentBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestTalentedDevelopment {	
	public static String classify_id = "";
	public static String passage_id1 = "";
	
	String classifyname = "岗位通道分类" + CommonData.getStringRandom(5);
	String passagename = "通道" + CommonData.getStringRandom(5);
	String qualificationname = "任职资格" + CommonData.getStringRandom(5);
	String rankname = "职级" + CommonData.getStringRandom(5);
	String username = UserBusiness.getUsername();

	
	public static String classify_sort = "511";
 	@Test(description="1.新增岗位通道分类接口", priority=1)
 	public void testAddPassageClassify()  {
 		String res = TalentedDevelopmentBusiness.AddPassageClassify(classifyname,classify_sort);
 		System.out.println("1.新增岗位通道分类接口:");
 		classify_id = (String) JSONPath.read(res, "$.data");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("classify_id="+classify_id);
// 		Assert.assertEquals(msg,"ok","1.新增岗位通道分类接口：" + res);
 	}
 	
 	
 	@Test(description="2.新增岗位通道分类-该分类已存在接口", priority=2)
 	public void testAddPassageClassifyAgain()  {
 		String res = TalentedDevelopmentBusiness.AddPassageClassify(classifyname,classify_sort);
 		System.out.println("2.新增岗位通道分类-该分类已存在接口:");
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("message="+message);
// 		Assert.assertEquals(message,"name and sort already exists","2.新增岗位通道分类-该分类已存在接口：" + res);
 	}
 	
 	
 	@Test(description="3.编辑岗位通道分类接口", priority=3)
 	public void testEditPassageClassify()  {
 		String name = classifyname + "编辑";
 		String res = TalentedDevelopmentBusiness.EditPassageClassify(name,classify_sort,classify_id);
 		System.out.println("3.编辑岗位通道分类接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","3.编辑岗位通道分类接口：" + res);
 	}
 	
 	
 	@Test(description="4.查询岗位通道分类列表接口", priority=4)
 	public void testQueryPassageClassify()  {
 		String res = TalentedDevelopmentBusiness.QueryPassageClassify();
 		System.out.println("4.查询岗位通道分类列表接口:");
 		//String classify = (String) JSONPath.read(res, "$.data[0].id");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg,"ok","4.查询岗位通道分类列表接口：" + res);
 	}
 		
 	
 	public static String passage_sort = "510";
 	@Test(description="5.新增岗位通道接口", priority=5)
 	public void testAddPassage()  {
// 		String res = TalentedDevelopmentBusiness.AddPassage(passagename,passage_sort,classify_id);
// 		System.out.println("5.新增岗位通道接口:");
// 		passage_id1 = (String) JSONPath.read(res, "$.data.id");
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("passage_id1="+passage_id1);
// 		Assert.assertEquals(msg,"ok","5.新增岗位通道接口：" + res);
 	}
 	

 	
 	@Test(description="6.新增岗位通道-排序已存在接口", priority=6)
 	public void testAddPassageNotExits()  {
 		String res = TalentedDevelopmentBusiness.AddPassage(passagename,passage_sort,classify_id);
 		System.out.println("6.新增岗位通道-排序已存在接口:");
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("message="+message);
 		Assert.assertNotEquals(message,"ok","6.新增岗位通道-排序已存在接口：" + res);
 	}
 	
 	
 	String name = passagename + "编辑";
 	@Test(description="7.编辑岗位通道接口", priority=7)
 	public void testEditPassage()  {
 		String res = TalentedDevelopmentBusiness.EditPassage(name,passage_id1,classify_id);
 		System.out.println("7.编辑岗位通道接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","7.编辑岗位通道接口：" + res);
 	}
 	
 	
 	public static String rank_sort = "525";
 	public static String rank_id = "";
 	@Test(description="8.新增职级接口", priority=8)
 	public void testAddPostRanks()  {
// 		String res = TalentedDevelopmentBusiness.AddPostRanks(rankname,rank_sort);
// 		System.out.println("8.新增职级接口:");
// 		rank_id = (String) JSONPath.read(res, "$.data.id");
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("msg="+msg+","+"rank_id="+rank_id);
// 		Assert.assertNotEquals(rank_id,null,"8.新增职级接口：" + res);
 	}
 	
 	
 	@Test(description="9.编辑职级接口", priority=9)
 	public void testEditPostRanks()  {
// 		String res = TalentedDevelopmentBusiness.EditPostRanks(rankname,rank_sort,rank_id);
// 		System.out.println("9.编辑职级接口:");
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","9.编辑职级接口：" + res);
 	}
 	
 	
 	public static String rank_id1 = "";
 	public static String rank_id2 = "";
 	public static String rank_id3 = "";
 	@Test(description="10.查询职级id接口", priority=10)
 	public void testPostRanks()  {
 		String res = TalentedDevelopmentBusiness.PostRanks();
 		System.out.println("10.查询职级id接口:");
 		rank_id1 = (String) JSONPath.read(res, "$.data[0].id");
 		rank_id2 = (String) JSONPath.read(res, "$.data[1].id");
 		rank_id3 = (String) JSONPath.read(res, "$.data[2].id");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg,"ok","10.查询职级id接口：" + res);
 	}
 	
 	
 	public static String post_ids1 = "";
 	public static String post_ids2 = "";
 	public static String post_ids3 = "";
 	public static String post_name1 = "";
 	public static String post_name2 = "";
 	public static String post_name3 = "";
 	@Test(description="11.查询用户所属岗位信息接口", priority=11)
 	public void testGetUserPost()  {
 		System.out.println("-------------------"+username);
 		String res = TalentedDevelopmentBusiness.GetUserPost(username);
 		System.out.println("11.查询用户所属岗位信息接口:");
 		post_ids1 = (String) JSONPath.read(res, "$.users[0].post_ids[0]");
 		post_ids2 = (String) JSONPath.read(res, "$.users[0].post_ids[1]");
 		post_ids3 = (String) JSONPath.read(res, "$.users[0].post_ids[2]");
 		post_name1 = (String) JSONPath.read(res, "$.users[0].post_names[0]");
 		post_name2 = (String) JSONPath.read(res, "$.users[0].post_names[1]");
 		post_name3 = (String) JSONPath.read(res, "$.users[0].post_names[2]");
 		System.out.println("post_ids1="+post_ids1);
// 		Assert.assertNotEquals(post_ids1,null,"11.查询用户所属岗位信息接口：" + res);
 	}
 	
 	
 	@Test(description="12.给岗位通道添加岗位接口", priority=12)
 	public void testPostMapping()  {
 		String res1 = TalentedDevelopmentBusiness.PostMapping(rank_id1,passage_id1,post_ids1,post_name1);
 		String res2 = TalentedDevelopmentBusiness.PostMapping(rank_id2,passage_id1,post_ids2,post_name2);
 		String res3 = TalentedDevelopmentBusiness.PostMapping(rank_id3,passage_id1,post_ids3,post_name3);
 		System.out.println("12.给岗位通道添加岗位接口:");
 		String msg = (String) JSONPath.read(res1, "$.msg");
 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","12.给岗位通道添加岗位接口：" + res1);
 	}
 	
 	
 	@Test(description="13.查询岗位通道分类下的岗位通道接口", priority=13)
 	public void testQueryPostPassages()  {
 		String res = TalentedDevelopmentBusiness.QueryPostPassages(classify_id);
 		System.out.println("13.查询岗位通道分类下的岗位通道接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","13.查询岗位通道分类下的岗位通道接口：" + res);
 	}

 	
 	public static String delete_post_id1 = "";
 	public static String delete_post_id2 = "";
 	public static String delete_post_id3 = "";
 	@Test(description="14.查询删除岗位时的id接口", priority=14)
 	public void testQueryDeleteId()  {
 		String res = TalentedDevelopmentBusiness.QueryDeleteId(classify_id);
 		System.out.println("14.查询删除岗位时的id接口:");
 		delete_post_id1 = (String) JSONPath.read(res, "$.data[0].postNodeList[-1].dto_list[0].id");
 		delete_post_id2 = (String) JSONPath.read(res, "$.data[0].postNodeList[-2].dto_list[0].id");
 		delete_post_id3 = (String) JSONPath.read(res, "$.data[0].postNodeList[0].dto_list[0].id");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg+","+"delete_post_id1="+delete_post_id1);
// 		Assert.assertEquals(msg,"ok","14.查询删除岗位时的id接口：" + res);
 	}
 	
 	
 	public static String education_id = "";
 	public static String experience_id = "";
 	@Test(description="15.获取经验+教育数据接口", priority=15)
 	public void testSearch()  {
 		String res_experience = TalentedDevelopmentBusiness.Search("experience");
 		String res_education = TalentedDevelopmentBusiness.Search("education");
 		System.out.println("15.获取经验+教育数据接口:");
 		education_id = (String) JSONPath.read(res_education, "$.sys_dicts[0].id");
 		experience_id = (String) JSONPath.read(res_experience, "$.sys_dicts[0].id");
 		System.out.println("education_id="+education_id);
 		Assert.assertNotEquals(education_id,null,"15.获取经验+教育数据接口：" + res_experience);
 	}
 	
 	public static String ability_id = "";
 	public static String ability_name = "";
 	@Test(description="16.能力要求列表数据校验接口", priority=16)
 	public void testAbilityList()  {
 		String res = TalentedDevelopmentBusiness.AbilityList("true","","");
 		System.out.println("16.能力要求列表数据校验接口:");
 		ability_id = (String) JSONPath.read(res, "$.list[0].ability_levels[0].ability_id");
 		ability_name = (String) JSONPath.read(res, "$.list[0].ability_levels[0].ability_name");
 		System.out.println("ability_id="+ability_id);
 		Assert.assertNotEquals(ability_id,null,"16.能力要求列表数据校验接口：" + res);
 	}
 	
 	public static String tempqualification_id = "";
 	@Test(description="17.新增岗位任职资格接口", priority=17)
 	public void testAddQualification()  {
 		String res = TalentedDevelopmentBusiness.AddQualification(qualificationname,classify_id,education_id,experience_id,
 				ability_id,ability_name,"1");
 		System.out.println("17.新增岗位任职资格接口:");
 		tempqualification_id = (String) JSONPath.read(res, "$.data");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg+","+"tempqualification_id="+tempqualification_id);
// 		Assert.assertEquals(msg,"ok","17.新增岗位任职资格接口：" + res);
 	}
 		
 	
 	public static String qualification_id = "";
 	@Test(description="18.岗位任职资格列表接口", priority=18)
 	public void testPostQualificationsList()  {
 		String res_all = TalentedDevelopmentBusiness.PostQualificationsList("","all");//全部
 		String res_normal = TalentedDevelopmentBusiness.PostQualificationsList("","normal");//已启用
 		String res_prohibit = TalentedDevelopmentBusiness.PostQualificationsList("","prohibit");//已禁用
 		String res_name = TalentedDevelopmentBusiness.PostQualificationsList(post_name1,"all");//按岗位名查询
 		System.out.println("18.岗位任职资格列表接口:");
 		String msg = (String) JSONPath.read(res_normal, "$.msg");
 		qualification_id = (String) JSONPath.read(res_normal, "$.data.list[0].id");
 		System.out.println("msg="+msg+","+"qualification_id="+qualification_id);
// 		Assert.assertEquals(msg,"ok","18.岗位任职资格列表接口：" + res_normal);
 	}
 	
 	
 	@Test(description="19.岗位任职资格列表-关闭/开启接口", priority=19)
 	public void testDisableQualifications()  {
 		String res_close = TalentedDevelopmentBusiness.DisableQualifications(qualification_id);//关闭
 		String res_on = TalentedDevelopmentBusiness.DisableQualifications(qualification_id);//开启
 		System.out.println("19.岗位任职资格列表-关闭/开启接口:");
 		String msg = (String) JSONPath.read(res_on, "$.msg");
 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","19.岗位任职资格列表-关闭/开启接口：" + res_on);
 	}
 		
 	
 	@Test(description="20.岗位认证资格列表-关联岗位查询接口", priority=20)
 	public void testQualificationPost()  {
 		String res = TalentedDevelopmentBusiness.QualificationPost(qualification_id);
 		System.out.println("20.岗位认证资格列表-关联岗位查询接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","20.岗位认证资格列表-关联岗位查询接口：" + res);
 	}
 	
 		
 	@Test(description="21.任职资格资格分类列表接口", priority=21)
 	public void testFindStructure()  {
 		String res = TalentedDevelopmentBusiness.FindStructure();
 		System.out.println("21.任职资格资格分类列表接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg,"ok","21.任职资格资格分类列表接口：" + res);
 	}
 	
 	
 	public static String emphasis_certificate_list_id = "";
 	public static String emphasis_certificate_list_name = "";
 	public static Integer emphasis_certificate_list_sort = null;
 	public static String emphasis_certificate_list_type = "";
 	public static Integer emphasis_task_list_sort = null;
 	public static String emphasis_task_list_id = "";
 	public static String emphasis_task_list_name = "";
 	public static String emphasis_task_list_type = "";
 	public static String ability_classify_list_id = "";
 	public static String ability_classify_list_name = "";
 	public static String ability_id1 = "";
 	public static String ability_name1 = "";
 	public static Integer ability_level1 = null;
 	public static String ability_source1 = "";
 	public static String ability_id2 = "";
 	public static String ability_name2 = "";
 	public static Integer ability_level2 = null;
 	public static String ability_source2 = "";
 	public static String ability_id3 = "";
 	public static String ability_name3 = "";
 	public static Integer ability_level3 = null;
 	public static String ability_source3 = "";
 	public static String ability_classify_list_id2 = "";
 	public static String ability_classify_list_name2 = "";
 	public static String manage_ability_id1 = "";
 	public static String manage_ability_name1 = "";
 	public static Integer manage_ability_level1 = null;
 	public static String manage_ability_source1 = "";
 	public static String manage_ability_id2 = "";
 	public static String manage_ability_name2 = "";
 	public static Integer manage_ability_level2 = null;
 	public static String manage_ability_source2 = "";
 	public static String manage_ability_id3 = "";
 	public static String manage_ability_name3 = "";
 	public static Integer manage_ability_level3 = null;
 	public static String manage_ability_source3 = "";
 	public static String manage_ability_id4 = "";
 	public static String manage_ability_name4 = "";
 	public static Integer manage_ability_level4 = null;
 	public static String manage_ability_source4 = "";
 	public static String level = "";
 	public static String experience = null;
 	public static Integer status = null;
 	@Test(description="22.岗位任职资格-预览/编辑接口", priority=22)
 	public void testQualificationsDetail()  {
 		String res = TalentedDevelopmentBusiness.QualificationsDetail(qualification_id);
 		System.out.println("22.岗位任职资格-预览/编辑接口:"+"res="+res);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		level = (String) JSONPath.read(res, "$.data.level");
// 		experience = (String) JSONPath.read(res, "$.data.experience");
 		status = (Integer) JSONPath.read(res, "$.data.status");
 		emphasis_certificate_list_id = (String) JSONPath.read(res, "$.data.emphasis_certificate_list[0].id");
 		emphasis_certificate_list_name = (String) JSONPath.read(res, "$.data.emphasis_certificate_list[0].name");
 		emphasis_certificate_list_sort = (Integer) JSONPath.read(res, "$.data.emphasis_certificate_list[0].sort");
 		emphasis_certificate_list_type = (String) JSONPath.read(res, "$.data.emphasis_certificate_list[0].type");
 		emphasis_task_list_id = (String) JSONPath.read(res, "$.data.emphasis_task_list[0].id");
 		emphasis_task_list_type = (String) JSONPath.read(res, "$.data.emphasis_task_list[0].type");
 		emphasis_task_list_sort = (Integer) JSONPath.read(res, "$.data.emphasis_task_list[0].sort");
 		emphasis_task_list_name = (String) JSONPath.read(res, "$.data.emphasis_task_list[0].name");
 		ability_classify_list_id = (String) JSONPath.read(res, "$.data.ability_classify_list[0].id");
 		ability_classify_list_name = (String) JSONPath.read(res, "$.data.ability_classify_list[0].name");
 		ability_id1 = (String) JSONPath.read(res, "$.data.ability_classify_list[0].post_qualifications_ability[0].ability_id");
 		ability_name1 = (String) JSONPath.read(res, "$.data.ability_classify_list[0].post_qualifications_ability[0].ability_name");
 		ability_level1 = (Integer) JSONPath.read(res, "$.data.ability_classify_list[0].post_qualifications_ability[0].ability_level");
 		ability_source1 = (String) JSONPath.read(res, "$.data.ability_classify_list[0].post_qualifications_ability[0].source");
 		ability_id2 = (String) JSONPath.read(res, "$.data.ability_classify_list[0].post_qualifications_ability[1].ability_id");
 		ability_name2 = (String) JSONPath.read(res, "$.data.ability_classify_list[0].post_qualifications_ability[1].ability_name");
 		ability_level2 = (Integer) JSONPath.read(res, "$.data.ability_classify_list[0].post_qualifications_ability[1].ability_level");
 		ability_source2 = (String) JSONPath.read(res, "$.data.ability_classify_list[0].post_qualifications_ability[1].source");
 		ability_id3 = (String) JSONPath.read(res, "$.data.ability_classify_list[0].post_qualifications_ability[2].ability_id");
 		ability_name3 = (String) JSONPath.read(res, "$.data.ability_classify_list[0].post_qualifications_ability[2].ability_name");
 		ability_level3 = (Integer) JSONPath.read(res, "$.data.ability_classify_list[0].post_qualifications_ability[2].ability_level");
 		ability_source3 = (String) JSONPath.read(res, "$.data.ability_classify_list[0].post_qualifications_ability[2].source");
 		ability_classify_list_id2 = (String) JSONPath.read(res, "$.data.ability_classify_list[1].id");
 		ability_classify_list_name2 = (String) JSONPath.read(res, "$.data.ability_classify_list[1].name");
 		manage_ability_id1 = (String) JSONPath.read(res, "$.data.ability_classify_list[1].post_qualifications_ability[0].ability_id");
 		manage_ability_name1 = (String) JSONPath.read(res, "$.data.ability_classify_list[1].post_qualifications_ability[0].ability_id");
 		manage_ability_level1 = (Integer) JSONPath.read(res, "$.data.ability_classify_list[1].post_qualifications_ability[0].ability_level");
 		manage_ability_source1 = (String) JSONPath.read(res, "$.data.ability_classify_list[1].post_qualifications_ability[0].source");
 		manage_ability_id2 = (String) JSONPath.read(res, "$.data.ability_classify_list[1].post_qualifications_ability[1].ability_id");
 		manage_ability_name2 = (String) JSONPath.read(res, "$.data.ability_classify_list[1].post_qualifications_ability[1].ability_id");
 		manage_ability_level2 = (Integer) JSONPath.read(res, "$.data.ability_classify_list[1].post_qualifications_ability[1].ability_level");
 		manage_ability_source2 = (String) JSONPath.read(res, "$.data.ability_classify_list[1].post_qualifications_ability[1].source");
 		manage_ability_id3 = (String) JSONPath.read(res, "$.data.ability_classify_list[1].post_qualifications_ability[2].ability_id");
 		manage_ability_name3 = (String) JSONPath.read(res, "$.data.ability_classify_list[1].post_qualifications_ability[2].ability_id");
 		manage_ability_level3 = (Integer) JSONPath.read(res, "$.data.ability_classify_list[1].post_qualifications_ability[2].ability_level");
 		manage_ability_source3 = (String) JSONPath.read(res, "$.data.ability_classify_list[1].post_qualifications_ability[2].source");
 		manage_ability_id4 = (String) JSONPath.read(res, "$.data.ability_classify_list[1].post_qualifications_ability[3].ability_id");
 		manage_ability_name4 = (String) JSONPath.read(res, "$.data.ability_classify_list[1].post_qualifications_ability[3].ability_id");
 		manage_ability_level4 = (Integer) JSONPath.read(res, "$.data.ability_classify_list[1].post_qualifications_ability[3].ability_level");
 		manage_ability_source4 = (String) JSONPath.read(res, "$.data.ability_classify_list[1].post_qualifications_ability[3].source");
 		System.out.println("msg="+msg+","+"experience="+experience);
// 		Assert.assertEquals(msg,"ok","22.岗位任职资格-预览/编辑接口：" + res);
 	}
 	
 	
 	@Test(description="23.获取学习方案所有岗位组接口", priority=23)
 	public void testAbilityModelList()  {
 		String res1 = TalentedDevelopmentBusiness.AbilityModelList("","2");//全部
 		String res2 = TalentedDevelopmentBusiness.AbilityModelList("","-1");//未配置
 		String res3 = TalentedDevelopmentBusiness.AbilityModelList("","0");//草稿
 		String res4 = TalentedDevelopmentBusiness.AbilityModelList("","1");//已发布
 		String res5= TalentedDevelopmentBusiness.AbilityModelList(post_name1,"1");//按岗位名查询
 		System.out.println("23.获取学习方案所有岗位组接口:");
 		String msg1 = (String) JSONPath.read(res1, "$.msg");
 		String msg2 = (String) JSONPath.read(res5, "$.msg");
 		System.out.println("msg1="+msg1+","+"msg2="+msg2);
// 		Assert.assertEquals(msg1,"ok","23.获取学习方案所有岗位组接口：" + res1);
// 		Assert.assertEquals(msg2,"ok","23.获取学习方案所有岗位组接口：" + res5);
 	}
 	
 	
 	@Test(description="24.岗位学习方案-预览接口", priority=24)
 	public void testScanAbilityModel()  {
 		String res = TalentedDevelopmentBusiness.ScanAbilityModel(post_ids1);
 		System.out.println("24.岗位学习方案-预览接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","24.岗位学习方案-预览接口：" + res);
 	}
 		
 	
 	@Test(description="25.岗位学习方案列表-导出接口", priority=25)
 	public void testAbilityModelExport()  {
 		String res = TalentedDevelopmentBusiness.AbilityModelExport();
 		System.out.println("25.岗位学习方案列表-导出接口:");
 		String result = (String) JSONPath.read(res, "$.result");
 		System.out.println("result="+result);
 		Assert.assertEquals(result,"岗位学习方案信息导出成功","25.岗位学习方案列表-导出接口：" + res);
 	}
 	
 	
 	public static int function_id = 0;
 	public static String post_ability_mode_id = "";
 	@Test(description="26.配置方案详情接口", priority=26)
 	public void testAbilityModelDetail()  {
// 		String res = TalentedDevelopmentBusiness.AbilityModelDetail(post_ids1);
// 		System.out.println("26.配置方案详情接口:"+"res="+res);
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		post_ability_mode_id = JSONPath.read(res, "$.data.id").toString();
// 		System.out.println("msg="+msg+","+"function_id="+function_id+","+"post_ability_mode_id="+post_ability_mode_id+","
// 		+"qualification_id="+qualification_id);
// 		Assert.assertEquals(msg,"ok","26.配置方案详情接口：" + res);
 	}
 	
 	
 	@Test(description="27.配置方案-岗位信息设置接口", priority=27)
 	public void testAbilityModelBase()  {
 		String res = TalentedDevelopmentBusiness.AbilityModelBase(post_name1,passagename,post_ids2,function_id,post_name2,
 			post_ability_mode_id,post_ids1);
 		System.out.println("27.配置方案-岗位信息设置接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","27.配置方案-岗位信息设置接口：" + res);
 	}
	
 	
 	@Test(description="28.配置方案-任职资格设置接口", priority=28)
 	public void testComparison()  {
 		long current_time = System.currentTimeMillis();
 		long endTemp = current_time + 24L*(-2)*3600*1000; 
 		String create_time = String.valueOf(current_time);
 		String update_time = String.valueOf(endTemp);
 		String res = TalentedDevelopmentBusiness.Comparison(post_name1,level,classify_id,education_id,experience,create_time,emphasis_task_list_id,
 				emphasis_task_list_name,qualification_id,emphasis_task_list_sort,emphasis_certificate_list_id,emphasis_certificate_list_name,
 				emphasis_certificate_list_sort,emphasis_certificate_list_type,update_time,ability_classify_list_id,ability_classify_list_name,
 				ability_id1,ability_name1,ability_level1,ability_source1,ability_id2,ability_name2,ability_level2,ability_source2,
 				ability_id3,ability_name3,ability_level3,ability_source3,ability_classify_list_id2,ability_classify_list_name2,
 				manage_ability_id1,manage_ability_name1,manage_ability_level1,manage_ability_source1,manage_ability_id2,manage_ability_name2,
 				manage_ability_level2,manage_ability_source2,manage_ability_id3,manage_ability_name3, manage_ability_level3,
 				manage_ability_source3,manage_ability_id4,manage_ability_name4,manage_ability_level4,manage_ability_source4,post_ids1,
 				status);
 		System.out.println("28.配置方案-任职资格设置接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","28.配置方案-任职资格设置接口：" + res);
 	}
 	
 	
 	public static String project_id = "";
 	@Test(description="29.学习项目列表接口", priority=29)
 	public void testProjectList()  {
 		String res = TalentedDevelopmentBusiness.ProjectList();
 		System.out.println("29.学习项目列表接口:");
 		project_id = (String) JSONPath.read(res, "$.list[0].id");
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotEquals(total,null,"29.学习项目列表接口：" + res);
 	}


 	@Test(description="30.配置方案-学习方案接口", priority=30)
 	public void testPostProject()  {
 		String res = TalentedDevelopmentBusiness.PostProject(post_ability_mode_id,project_id);
 		System.out.println("30.配置方案-学习方案接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","30.配置方案-学习方案接口：" + res);
 	}
 	
 	
 	@Test(description="31.配置方案-确认信息提交接口", priority=31)
 	public void testSubmitAbilityModel()  {
 		String res = TalentedDevelopmentBusiness.SubmitAbilityModel(post_ability_mode_id);
 		System.out.println("31.配置方案-确认信息提交接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","31.配置方案-确认信息提交接口：" + res);
 	}
 	
 	
 	public static String evaluation_models_id = "";
 	@Test(description="32.评估结果导入-岗位能力模型左侧列表接口", priority=32)
 	public void testEvaluationModels()  {
 		String res = TalentedDevelopmentBusiness.EvaluationModels();
 		System.out.println("32.评估结果导入-岗位能力模型左侧列表接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		evaluation_models_id = (String) JSONPath.read(res, "$.data.list[0].id");
 		System.out.println("msg="+msg+","+"evaluation_models_id="+evaluation_models_id);
 		Assert.assertEquals(msg,"success","32.评估结果导入-岗位能力模型左侧列表接口：" + res);
 	}
 	
 	
 	public static String eva_result_id = "";
 	@Test(description="33.评估结果导入-能力模型右侧大列表接口", priority=33)
 	public void testEvaluationResult()  {
// 		String res = TalentedDevelopmentBusiness.EvaluationResult(evaluation_models_id);
// 		System.out.println("33.评估结果导入-能力模型右侧大列表接口:");
// 		eva_result_id = (String) JSONPath.read(res, "$.data.list[-1].id");
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","33.评估结果导入-能力模型右侧大列表接口：" + res);
 	}
 	
 	
 	@Test(description="34.评评估结果导入-能力模型右侧大列表-详情接口", priority=34)
 	public void testEvaluationResultDetail()  {
// 		String res = TalentedDevelopmentBusiness.EvaluationResultDetail(eva_result_id);
// 		System.out.println("34.评评估结果导入-能力模型右侧大列表-详情接口:");
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","34.评评估结果导入-能力模型右侧大列表-详情接口：" + res);
 	}
 	
 	
 	@Test(description="35.高级设置-参数设置接口", priority=35)
 	public void testConfigSave()  {
 		String res1 = TalentedDevelopmentBusiness.ConfigSave("2");//设置为楼梯模式
 		String res2 = TalentedDevelopmentBusiness.ConfigSave("1");//设置为登山模式
 		System.out.println("35.评评估结果导入-能力模型右侧大列表-详情接口:");
 		String msg = (String) JSONPath.read(res1, "$.msg");
 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"success","35.评评估结果导入-能力模型右侧大列表-详情接口：" + res1);
 	}
 	
 	
 	@Test(description="36.学习平台-用户所属岗位接口", priority=36)
 	public void testUserPost()  {
 		String res = TalentedDevelopmentBusiness.UserPost();
 		System.out.println("36.学习平台-用户所属岗位接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg,"ok","36.学习平台-用户所属岗位接口：" + res);
 	}
 	
 	
 	@Test(description="37.学习平台-岗位所属的岗位通道接口", priority=37)
 	public void testPostPassages()  {
// 		String res = TalentedDevelopmentBusiness.PostPassages(post_ids1,post_name1);
// 		System.out.println("37.学习平台-岗位所属的岗位通道接口:");
// 		String title_data = "";
// 		JSONArray eventMsgArray = (JSONArray) JSONPath.read(res, "$.data");
// 		for(Object obj :eventMsgArray) {
// 			JSONObject jsonObj	=	(JSONObject) JSONObject.parse(obj.toString());
// 			if(jsonObj.getString("post_passage_name").contains(name)) {
// 				title_data = jsonObj.getString("post_passage_name");
// 			}			
// 		}				 		
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("msg="+msg);
// 		Assert.assertEquals(title_data,name,"37.学习平台-岗位所属的岗位通道接口：" + res);
 	}
 	
 	
 	@Test(description="38.学习平台-岗位详情接口", priority=38)
 	public void testModelDetail()  {
// 		String res = TalentedDevelopmentBusiness.ModelDetail(post_ids1,rank_id1);
// 		System.out.println("38.学习平台-岗位详情接口:");
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","38.学习平台-岗位详情接口：" + res);
 	}
 	
 	
 	@Test(description="39.学习平台-岗位详情-查看全部证书/任务接口", priority=39)
 	public void testLinkInfo()  {
// 		String res_certificate = TalentedDevelopmentBusiness.LinkInfo(qualification_id,"certificate");//学习平台-岗位详情-查看全部所需证书
// 		String res_task = TalentedDevelopmentBusiness.LinkInfo(qualification_id,"task");//学习平台-岗位详情-查看全部所需任务
// 		System.out.println("39.学习平台-岗位详情-查看全部证书/任务接口:");
// 		String msg = (String) JSONPath.read(res_task, "$.msg");
// 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","39.学习平台-岗位详情-查看全部证书/任务接口：" + res_task);
 	}
 	
 	
 	@Test(description="39.删除职级接口", priority=39)
 	public void testDeletePostRanks()  {
// 		String res = TalentedDevelopmentBusiness.DeletePostRanks(rank_id);
// 		System.out.println("39.删除职级接口:"+"res="+res);
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","39.删除职级接口：" + res);
 	}
 	
 	
 	@Test(description="40.删除岗位接口", priority=40)
 	public void testDeletePostMapping()  {
 		String res1 = TalentedDevelopmentBusiness.DeletePostMapping(delete_post_id1);
 		String res2 = TalentedDevelopmentBusiness.DeletePostMapping(delete_post_id2);
 		String res3 = TalentedDevelopmentBusiness.DeletePostMapping(delete_post_id3);
 		System.out.println("40.删除岗位接口:");
 		String msg = (String) JSONPath.read(res1, "$.msg");
 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","40.删除岗位接口：" + res1);
 	}
 	
 	
 	@Test(description="41.删除岗位通道接口", priority=41)
 	public void testDeletePostPassages()  {
 		String res = TalentedDevelopmentBusiness.DeletePostPassages(passage_id1);
 		System.out.println("41.删除岗位通道接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","41.删除岗位通道接口：" + res);
 	}
 		
 	@Test(description="42.删除岗位通道分类接口", priority=42)
 	public void testDeletePassageClassify()  {
 		String res = TalentedDevelopmentBusiness.DeletePassageClassify(classify_id);
 		System.out.println("42.删除岗位通道分类接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","42.删除岗位通道分类接口：" + res);
 	}
 	
 	
 	@Test(description="43.删除任职资格接口", priority=43)
 	public void testDeleteQualification()  {
 		String res = TalentedDevelopmentBusiness.DeleteQualification(tempqualification_id);
 		System.out.println("43.删除任职资格接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"ok","43.删除任职资格接口：" + res);
 	}
 	
 	
}
