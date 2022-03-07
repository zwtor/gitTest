package group.cases;

import cn.kxy.group.a.business.EvaluationBusiness;
import cn.kxy.group.a.business.TutorOperationBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestEvaluation {	
	public static String ability_id = "";
	public static String model_id = "";
	public static String tool_id = "";
	public static String product_name = "";
	public static String evaluation_tool_id = "";
	public static String evaluation_id = "";
	public static String evaluation_score_id = "";
	public static String lever_id = "";
	public static String evaluation_superior_id = "";
	public static String evaluationTool_id = "";
	public static String evaluation_draft_id = "";
	public static String evaluation_custom_id = "";
	public static String beginTime = "";
	public static String endTime = "";
	public static String course_id = "";
	public static String studyPlan_id = "";
	public static String studyPlan_evaluation_id = "";
	public static Integer product_id = null;
	public static String projectCourse_id = "";
	public static String project_id = "";
	public static String studyProject_evaluation_id = "";
	public static String user_name = "";
	public static String update_title = "";
	public static Integer update_model_id = null;
	public static String update_term_type = "";
	public static Integer update_open_result = null;
	public static Integer update_status = null;
	public static String update_is_get_score = "";
	public static String update_evaluation_tool_type = "";
	public static String update_evaluation_way_self ="";
	public static String update_evaluation_way_superior = "";

	String abilityName = "测训能力项" + CommonData.getStringRandom(5);
	String model_name = "测评模型" + CommonData.getStringRandom(5);
	String evaluation_name = "测评任务" + CommonData.getStringRandom(5);
	String classification_id = ClassificationBusines.getPrimaryId();
	String studyPlan_title = "岗位测评" + CommonData.getStringRandom(5);
	String title_project = "岗位测评" + CommonData.getStringRandom(5);
	
 	@Test(description="1.创建测评能力项接口", priority=1)
 	public void testAbilityAdd()  {
 		String res = EvaluationBusiness.AbilityAdd(abilityName);
 		System.out.println("1.创建测评能力项接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Boolean success = (Boolean)JSONPath.read(res, "$.success");
		Assert.assertTrue(success,"2.创建测评能力项-名字已被使用接口"+res);
 	}
 	
 	@Test(description="2.创建测评能力项-名称已被使用接口", priority=2)
 	public void testTutorsLeverAddNamed()  {
 		String res = EvaluationBusiness.AbilityAdd(abilityName);
 		System.out.println("2.创建测评能力项-名称已被使用接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Boolean success = (Boolean)JSONPath.read(res, "$.success");
		Assert.assertFalse(success,"2.创建测评能力项-名称已被使用接口"+res);
 	}
 	
 	@Test(description="3.查询测训能力项列表-全部接口", priority=3)
 	public void testAbilityQuery()  {
 		String res = EvaluationBusiness.AbilityQuery("",abilityName,"");
 		System.out.println("3.查询测训能力项列表-全部接口:");
 		ability_id = (String)JSONPath.read(res, "$.list[0].id");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("ability_id="+ability_id+","+"total="+total);
		Assert.assertNotEquals(total, 0 ,"3.查询测训能力项列表-全部接口"+res);
 	}
 	
 	
 	@Test(description="4.查询测训能力项列表-自建接口", priority=4)
 	public void testAbilityQuerySelf()  {
 		String res = EvaluationBusiness.AbilityQuery("self","","");
 		System.out.println("4.查询测训能力项列表-自建接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
		Assert.assertNotEquals(total, 0 ,"4.查询测训能力项列表-自建接口"+res);
 	}
 	
 	@Test(description="5.查询测训能力项列表-云端接口", priority=5)
 	public void testAbilityQueryTool()  {
 		String res = EvaluationBusiness.AbilityQuery("tool","","");
 		System.out.println("5.查询测训能力项列表-云端接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
		Assert.assertNotEquals(total, 0 ,"5.查询测训能力项列表-云端接口"+res);
 	}
 	
 	@Test(description="6.查询测训能力项列表-按名称筛选接口", priority=6)
 	public void testAbilityQueryByName()  {
 		String res = EvaluationBusiness.AbilityQuery("",abilityName,"");
 		System.out.println("6.查询测训能力项列表-按名称筛选接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
		Assert.assertNotEquals(total, 0 ,"6.查询测训能力项列表-按名称筛选接口"+res);
 	}
 	
 	
 	@Test(description="7.查询测训能力项列表-按名称筛选不到结果接口", priority=7)
 	public void testAbilityQueryNoData()  {
 		String abilityNameTemp = abilityName + "test";
 		String res = EvaluationBusiness.AbilityQuery("",abilityNameTemp,"");
 		System.out.println("7.查询测训能力项列表-按名称筛选不到结果接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
		Assert.assertNotEquals(total, 1 ,"7.查询测训能力项列表-按名称筛选不到结果接口"+res);
 	}
 	
 	@Test(description="8.查询测训能力项列表-按专业能力类型筛选接口", priority=8)
 	public void testAbilityQuerySpecial()  {
 		String res = EvaluationBusiness.AbilityQuery("","","");
 		System.out.println("8.查询测训能力项列表-按能力类型筛选接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
		Assert.assertNotEquals(total, 0 ,"8.查询测训能力项列表-按能力类型筛选接口"+res);
 	}
 	
 	@Test(description="9.查询测训能力项列表-按通用能力类型筛选接口", priority=9)
 	public void testAbilityQueryCommon()  {
 		String res = EvaluationBusiness.AbilityQuery("","","common");
 		System.out.println("9.查询测训能力项列表-按通用能力类型筛选接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
		Assert.assertNotEquals(total, 0 ,"9.查询测训能力项列表-按通用能力类型筛选接口"+res);
 	}
 	
 	@Test(description="10.编辑测评能力项接口", priority=10)
 	public void testAbilityUpdate()  {
 		abilityName = abilityName + "编辑";
 		String res = EvaluationBusiness.AbilityUpdate(ability_id,abilityName);
 		System.out.println("10.编辑测评能力项接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Boolean success = (Boolean)JSONPath.read(res, "$.success");
		Assert.assertTrue(success,"10.编辑测评能力项接口"+res);
 	}
 	
 	
 	@Test(description="11.编辑可见范围-全公司接口", priority=11)
 	public void testSettingVisible()  {
 		String res = EvaluationBusiness.SettingVisible(ability_id,"all");
 		System.out.println("11.编辑可见范围-全公司接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Boolean success = (Boolean)JSONPath.read(res, "$.success");
		Assert.assertTrue(success,"11.编辑可见范围-全公司接口"+res);
 	}
 	
 	
 	@Test(description="12.创建测评模型接口", priority=12)
 	public void testEvaluationModelSave()  {
 		String res = EvaluationBusiness.EvaluationModelSave(model_name,ability_id);
 		System.out.println("12.创建测评模型接口:");
 		model_id = (String)JSONPath.read(res, "$.data");
 		System.out.println("data="+model_id);
		Assert.assertNotEquals(model_id,null,"12.创建测评模型接口"+res);
 	}
 	
 	@Test(description="13.创建测评模型-名称已存在接口", priority=13)
 	public void testEvaluationModelSaved()  {
 		String res = EvaluationBusiness.EvaluationModelSave(model_name,ability_id);
 		System.out.println("13.创建测评模型-名称已存在接口:");
 		String data = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("data="+data+","+"msg="+msg);
		Assert.assertEquals(data,null,"13.创建测评模型-名称已存在接口"+res);
 	}
 	
 	@Test(description="14.编辑测评模型接口", priority=14)
 	public void testEvaluationModelEdit()  {
 		String model_nameTemp = model_name + "编辑";
 		String res = EvaluationBusiness.EvaluationModelEdit(model_nameTemp,model_id,ability_id);
 		System.out.println("14.编辑测评模型接口:");
 		String data = (String)JSONPath.read(res, "$.data");
 		Boolean success = (Boolean)JSONPath.read(res, "$.success");
 		System.out.println("data="+data+","+"success="+success);
		Assert.assertTrue(success,"14.编辑测评模型接口"+res);
 	}
 	
 	@Test(description="15.测评模型可见范围接口", priority=15)
 	public void testModelVisibleRangeSetting()  {
 		String res = EvaluationBusiness.ModelVisibleRangeSetting(model_id);
 		System.out.println("15.测评模型可见范围接口:");
 		Boolean success = (Boolean)JSONPath.read(res, "$.success");
 		System.out.println("success="+success);
		Assert.assertTrue(success,"15.测评模型可见范围接口"+res);
 	}
 	
 	@Test(description="16.测评模型可见范围-测训模型不存在接口", priority=16)
 	public void testModelVisibleRangeSettingNoData()  {
 		String model_idTemp = "1805012936610000000";//设置一个不存在的模型id
 		String res = EvaluationBusiness.ModelVisibleRangeSetting(model_idTemp);
 		System.out.println("16.测评模型可见范围-测训模型不存在接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Boolean success = (Boolean)JSONPath.read(res, "$.success");
 		System.out.println("msg="+msg+","+"success="+success);
		Assert.assertFalse(success,"16.测评模型可见范围-测训模型不存在接口"+res);
 	}
 	
 	
 	@Test(description="17.测训模型列表接口", priority=17)
 	public void testEvaluationModelsList()  {
 		String res = EvaluationBusiness.EvaluationModelsList("false","");
 		System.out.println("17.测训模型列表接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Boolean success = (Boolean)JSONPath.read(res, "$.success");
 		System.out.println("msg="+msg+","+"success="+success);
		Assert.assertTrue(success,"17.测训模型列表接口"+res);
 	}
 	
 	@Test(description="18.测训模型列表-只看我创建的接口", priority=18)
 	public void testEvaluationModelsListOnlySeeMe()  {
 		String res = EvaluationBusiness.EvaluationModelsList("true","");
 		System.out.println("18.测训模型列表-只看我创建的接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Boolean success = (Boolean)JSONPath.read(res, "$.success");
 		System.out.println("msg="+msg+","+"success="+success);
		Assert.assertTrue(success,"18.测训模型列表-只看我创建的接口"+res);
 	}
 	
 	@Test(description="19.测训模型列表-按测训名称筛选接口", priority=19)
 	public void testEvaluationModelsListByName()  {
 		String res = EvaluationBusiness.EvaluationModelsList("true",model_name);
 		System.out.println("19.测训模型列表-按测训名称筛选接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Boolean success = (Boolean)JSONPath.read(res, "$.success");
 		System.out.println("msg="+msg+","+"success="+success);
		Assert.assertTrue(success,"19.测训模型列表-按测训名称筛选接口"+res);
 	}
 	
 	@Test(description="20.添加测训工具时弹窗内的工具数据接口", priority=20)
 	public void testtoolsNowList()  {
 		String res = EvaluationBusiness.toolsNowList();
 		JSONArray jsonArray = JSONArray.parseArray(res);
 		System.out.println("20.添加测训工具时弹窗内的工具数据接口:");
 		JSONObject jsonObj = (JSONObject) jsonArray.get(0);
 		tool_id = jsonObj.getString("id");
 		product_name = jsonObj.getString("product_name");
		Assert.assertNotEquals(tool_id,"","20.添加测训工具时弹窗内的工具数据接口"+res);
 	}
 	
 	@Test(description="21.添加测训工具接口", priority=21)
 	public void testAddTools()  {
 		String res = EvaluationBusiness.AddTools(tool_id);
 		System.out.println("21.添加测训工具接口:");
 		String success = (String)JSONPath.read(res, "$.success");
 		System.out.println("success="+success);
		Assert.assertEquals(success,"true","21.添加测训工具接口"+res);
 	}
 	
 	@Test(description="22.添加测训工具-工具已被使用过接口", priority=22)
 	public void testAddToolsUsed()  {
 		String res = EvaluationBusiness.AddTools(tool_id);
 		System.out.println("22.添加测训工具-工具已被使用过接口:");
 		String success = (String)JSONPath.read(res, "$.success");
 		System.out.println("success="+success);
		Assert.assertEquals(success,"true","22.添加测训工具-工具已被使用过接口"+res);
 	}
 	
 	@Test(description="23.查询测训工具列表-按名称查询接口", priority=23)
 	public void testEvaluationToolsList()  {
 		String res = EvaluationBusiness.EvaluationToolsList(product_name);
 		System.out.println("23.查询测训工具列表-按名称查询接口:");
 		evaluation_tool_id = (String)JSONPath.read(res, "$.list[0].id");	
 		String is_first_page = (String)JSONPath.read(res, "$.is_first_page");
 		System.out.println("success="+is_first_page+","+"evaluation_tool_id="+evaluation_tool_id);
		Assert.assertEquals(is_first_page,"true","23.查询测训工具列表-按名称查询接口"+res);
 	}
 	
 	@Test(description="24.创建测评任务-有学分+不可查看测评报告接口", priority=24)
 	public void testEvaluationSave()  {
 		String res = EvaluationBusiness.EvaluationSave(evaluation_name,model_id,"permanent","","","0","true","false","1","model",
 				"true","false");
 		System.out.println("24.创建测评任务-有学分+不可查看测评报告接口:");
 		evaluation_id = (String)JSONPath.read(res, "$.id");
 		System.out.println("evaluation_id="+evaluation_id);
		Assert.assertNotEquals(evaluation_id,null,"24.创建测评任务-有学分+不可查看测评报告接口"+res);
 	}
 	
 	@Test(description="25.创建测评任务-名称已被使用接口", priority=25)
 	public void testEvaluationSaved()  {
 		String res = EvaluationBusiness.EvaluationSave(evaluation_name,model_id,"permanent","","","0","false","false","1","model",
 				"true","false");
 		System.out.println("25.创建测评任务-名称已被使用接口:");
 		String message = (String)JSONPath.read(res, "$.message");
 		System.out.println("message="+message);
		Assert.assertEquals(message,"The assessment task name already exists","25.创建测评任务-名称已被使用接口"+res);
 	}	
 	
 	@Test(description="26.创建测评任务-可查看测评报告接口", priority=26)
 	public void testEvaluationSaveScore()  {
 		String evaluation_nameTemp = evaluation_name + "可查看测评报告";
 		String res = EvaluationBusiness.EvaluationSave(evaluation_nameTemp,model_id,"permanent","","","1","false","false","1","model",
 				"true","false");
 		System.out.println("26.创建测评任务-可查看测评报告接口:");
 		evaluation_score_id = (String)JSONPath.read(res, "$.id");
 		System.out.println("evaluation_score_id="+evaluation_score_id);
 		Assert.assertNotEquals(evaluation_score_id,null,"26.创建测评任务-可查看测评报告接口"+res);
 	}
 	
 	@Test(description="27.创建测评任务-上级他评接口", priority=27)
 	public void testEvaluationSaveSuperior()  {
 		String evaluation_nameTemp = evaluation_name + "上级他评";
 		String res = EvaluationBusiness.EvaluationSave(evaluation_nameTemp,model_id,"permanent","","","1","true","false","1","model",
 				"false","true");
 		System.out.println("27.创建测评任务-上级他评接口:");
 		evaluation_superior_id = (String)JSONPath.read(res, "$.id");
 		System.out.println("evaluation_superior_id="+evaluation_superior_id);
 		Assert.assertNotEquals(evaluation_superior_id,null,"27.创建测评任务-上级他评接口"+res);
 	}
 	
 	
 	@Test(description="28.创建测评任务-选择测评工具接口", priority=28)
 	public void testEvaluationSaveTool()  {
 		String evaluation_nameTemp = evaluation_name + "测评工具";
 		String res = EvaluationBusiness.EvaluationSave(evaluation_nameTemp,model_id,"permanent","","","1","true","false","1","tool",
 				"false","true");
 		System.out.println("28.创建测评任务-选择测评工具接口:");
 		evaluationTool_id = (String)JSONPath.read(res, "$.id");
 		System.out.println("evaluationTool_id="+evaluationTool_id);
 		Assert.assertNotEquals(evaluationTool_id,null,"28.创建测评任务-选择测评工具接口"+res);
 	}
 	
 	
 	@Test(description="29.创建测评任务-自定义时间接口", priority=29)
 	public void testEvaluationSaveCustom()  {
 		String evaluation_nameTemp = evaluation_name + "自定义时间";
 		SimpleDateFormat date_temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		beginTime = date_temp.format(new Date());//当前时间
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 7);
		endTime = date_temp.format(calendar.getTime());
 		String res = EvaluationBusiness.EvaluationSave(evaluation_nameTemp,model_id,"custom",beginTime,endTime,"0","false","false","1",
 				"model","true","false");
 		System.out.println("29.创建测评任务-自定义时间接口:");
 		evaluation_custom_id = (String)JSONPath.read(res, "$.id");
 		System.out.println("evaluation_custom_id="+evaluation_custom_id);
		Assert.assertNotEquals(evaluation_custom_id,null,"29.创建测评任务-自定义时间接口"+res);
 	}
 	
 	
 	public static String evaluation_promotion_id = "";
 	@Test(description="30.创建测评任务-提升方案接口", priority=30)
 	public void testEvaluationSavePromotion()  {
 		String evaluation_promotion_name = "测评任务-提升方案"+ CommonData.getStringRandom(5);;
 		String res = EvaluationBusiness.EvaluationSave(evaluation_promotion_name,model_id,"permanent","","","0","true","true","1","model",
 				"true","false");
 		System.out.println("30.创建测评任务-提升方案接口:");
 		evaluation_promotion_id = (String)JSONPath.read(res, "$.id");
 		System.out.println("evaluation_promotion_id="+evaluation_promotion_id);
 		Assert.assertNotEquals(evaluation_promotion_id,null,"30.创建测评任务-提升方案接口"+res);
 	}
 	
 	
 	@Test(description="30.创建测评任务-保存为草稿接口", priority=30)
 	public void testEvaluationSaveDraft()  {
 		String evaluation_nameTemp = evaluation_name + "草稿";
 		String res = EvaluationBusiness.EvaluationSave(evaluation_nameTemp,model_id,"permanent","","","1","true","false","0","tool",
 				"false","true");
 		System.out.println("30.创建测评任务-保存为草稿接口:");
 		evaluation_draft_id = (String)JSONPath.read(res, "$.id");
 		System.out.println("evaluation_draft_id="+evaluation_draft_id);
 		Assert.assertNotEquals(evaluation_draft_id,null,"30.创建测评任务-保存为草稿接口"+res);
 	}
 	
 	
 	@Test(description="31.测评任务列表-默认是已发布的任务接口", priority=31)
 	public void testEvaluationList()  {
 		String res = EvaluationBusiness.EvaluationList("false","","1");//默认查询已发布
 		String resNoPublish = EvaluationBusiness.EvaluationList("false","","0");//按未发布筛选
 		System.out.println("31.测评任务列表-默认是已发布的任务接口:");
 		String publish_count = (String)JSONPath.read(res, "$.publish_count");
 		System.out.println("publish_count="+publish_count);
 		Assert.assertNotEquals(publish_count,"0","31.测评任务列表-默认是已发布的任务接口"+res);
 	}
 	
 	
 	@Test(description="32.测评任务列表-按名称筛选接口", priority=32)
 	public void testEvaluationListByName()  {
 		String res = EvaluationBusiness.EvaluationList("false",evaluation_name,"1");
 		System.out.println("32.测评任务列表-按名称筛选接口:");
 		String publish_count = (String)JSONPath.read(res, "$.publish_count");
 		System.out.println("publish_count="+publish_count);
 		Assert.assertNotEquals(publish_count,"0","32.测评任务列表-按名称筛选接口"+res);
 	}
 	
 	
 	@Test(description="33.测评任务列表-只看我创建的接口", priority=33)
 	public void testEvaluationListOnlySeeMe()  {
 		String res = EvaluationBusiness.EvaluationList("true","","1");
 		System.out.println("33.测评任务列表-只看我创建的接口:");
 		String publish_count = (String)JSONPath.read(res, "$.publish_count");
 		System.out.println("publish_count="+publish_count);
 		Assert.assertNotEquals(publish_count,"0","33.测评任务列表-只看我创建的接口"+res);
 	}
 	
 	@Test(description="34.测评任务列表-取消发布接口", priority=34)
 	public void testEvaluationUnpublish()  {
 		String res = EvaluationBusiness.EvaluationUnpublish(evaluation_id);
 		System.out.println("34.测评任务列表-取消发布接口:");
 		String message = (String)JSONPath.read(res, "$.message");
 		System.out.println("message="+message);
 		Assert.assertEquals(message,"SUCCESS","34.测评任务列表-取消发布接口"+res);
 	}
 	
 	
 	@Test(description="35.获取测评任务编辑页信息接口", priority=35)
 	public void testEvaluationUpdateQuery()  {
 		String res = EvaluationBusiness.EvaluationUpdateQuery(evaluation_id);
 		System.out.println("35.获取测评任务编辑页信息接口:");
 		String id = (String)JSONPath.read(res, "$.id");
 		update_title = (String)JSONPath.read(res, "$.title");
 		update_model_id = (Integer)JSONPath.read(res, "$.model_id");
 		update_term_type = (String)JSONPath.read(res, "$.term_type");
 		update_open_result = (Integer)JSONPath.read(res, "$.open_result");
 		update_status = (Integer)JSONPath.read(res, "$.status");
 		update_is_get_score = (String)JSONPath.read(res, "$.is_get_score");
 		update_evaluation_tool_type = (String)JSONPath.read(res, "$.evaluation_tool_type");
 		update_evaluation_way_self = (String)JSONPath.read(res, "$.evaluation_way_self");
 		update_evaluation_way_superior = (String)JSONPath.read(res, "$.evaluation_way_superior");
 		System.out.println("id="+id);
 		Assert.assertNotEquals(id,null,"35.获取测评任务编辑页信息接口"+res);
 	}
 	
 	@Test(description="36.测评任务列表-编辑任务接口", priority=36)
 	public void testEvaluationUpdate()  {
 		String update_model_idTemp = Integer.toString(update_model_id);
 		String update_open_resultTemp = Integer.toString(update_open_result);
 		String update_statusTemp = Integer.toString(update_status);
 		String res = EvaluationBusiness.EvaluationUpdate(evaluation_id,update_title,update_model_idTemp,update_term_type,
 				update_open_resultTemp,update_statusTemp,update_is_get_score,update_evaluation_tool_type,update_evaluation_way_self,
 				update_evaluation_way_superior);
 		System.out.println("36.测评任务列表-编辑任务接口:");
 		String status = (String)JSONPath.read(res, "$.status");
 		System.out.println("status="+status);
 		Assert.assertEquals(status,"更新成功","36.测评任务列表-编辑任务接口"+res);
 	}
 	
 	
 	@Test(description="37.测评任务-发布接口", priority=37)
 	public void testEvaluationPublish()  {
 		String res = EvaluationBusiness.EvaluationPublish(evaluation_id);
 		System.out.println("37.测评任务-发布接口:");
 		String message = (String)JSONPath.read(res, "$.message");
 		System.out.println("message="+message);
 		Assert.assertEquals(message,"SUCCESS","37.测评任务-发布接口"+res);
 	}
 		
 	
 	@Test(description="38.测评任务数据页-基础信息接口", priority=38)
 	public void testEvaluationDetail()  {
 		String res = EvaluationBusiness.EvaluationDetail(evaluation_id);
 		System.out.println("38.测评任务数据页-基础信息接口:");
 		String id = (String)JSONPath.read(res, "$.id");
 		System.out.println("id="+id);
 		Assert.assertEquals(id,evaluation_id,"38.测评任务数据页-基础信息接口"+res);
 	}
 	
 	
 	@Test(description="39.测评任务数据页-列表数据-默认数据接口", priority=39)
 	public void testEvaluationDetailList()  {
 		String res = EvaluationBusiness.EvaluationDetailList(evaluation_id,"","","");
 		System.out.println("38.测评任务数据页-列表数据-默认数据接口:");
 		user_name = (String)JSONPath.read(res, "$.list[0].user_name");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotEquals(total,0,"38.测评任务数据页-列表数据-默认数据接口"+res);
 	}
 	
 	@Test(description="40.测评任务数据页-列表数据-按完成状态筛选接口", priority=40)
 	public void testEvaluationDetailListPromoteStatus()  {
 		String res_un_start = EvaluationBusiness.EvaluationDetailList(evaluation_id,"","","un_start");//未开始
 		String res_complete = EvaluationBusiness.EvaluationDetailList(evaluation_id,"","","complete");//已完成
 		String res_on_going = EvaluationBusiness.EvaluationDetailList(evaluation_id,"","","on_going");//进行中
 		String res_user_name = EvaluationBusiness.EvaluationDetailList(evaluation_id,user_name,"","");//通过人名关键字查询
 		System.out.println("40.测评任务数据页-列表数据-按完成状态接口:");
 		Integer total = (Integer)JSONPath.read(res_un_start, "$.total");
 		Integer total_user_name = (Integer)JSONPath.read(res_user_name, "$.total");
 		System.out.println("total="+total+","+"total_user_name="+total_user_name);
 		Assert.assertNotEquals(total,null,"40.测评任务数据页-列表数据-按完成状态接口"+res_un_start);
 		Assert.assertNotEquals(total_user_name,null,"40.测评任务数据页-列表数据-按完成状态接口"+res_user_name);
 	}
 	
 	
 	@Test(description="41.测评任务数据页-列表数据-按status状态筛选接口", priority=41)
 	public void testEvaluationDetailListStatus()  {
 		String res_un_complete = EvaluationBusiness.EvaluationDetailList(evaluation_id,"","un_complete","");//未完成
 		String res_complete = EvaluationBusiness.EvaluationDetailList(evaluation_id,"","complete","");//已完成
 		String res = EvaluationBusiness.EvaluationDetailList(evaluation_id,"","","");//全部
 		System.out.println("41.测评任务数据页-列表数据-按status状态筛选接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotEquals(total,null,"41.测评任务数据页-列表数据-按status状态筛选接口"+res);
 	}
 	
 	@Test(description="42.测评任务数据页-导出接口", priority=42)
 	public void testEvaluationExport()  {
 		String res = EvaluationBusiness.EvaluationExport(evaluation_id);
 		System.out.println("42.测评任务数据页-导出接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg,"测评任务数据明细导出记录已生成","42.测评任务数据页-导出接口"+res);
 	}
 	
 	@Test(description="43.测评任务数据页-催促测评接口", priority=43)
 	public void testEvaluationRemind()  {
 		String res = EvaluationBusiness.EvaluationRemind(evaluation_id);
 		System.out.println("43.测评任务数据页-催促测评接口:");
 		Boolean success = (Boolean)JSONPath.read(res, "$.success");
 		System.out.println("success="+success);
 		Assert.assertTrue(success,"43.测评任务数据页-催促测评接口"+res);
 	}
 	
 	@Test(description="44.测评任务数据页-催促提升接口", priority=44)
 	public void testEvaluationImproveRemind()  {
 		String res = EvaluationBusiness.EvaluationImproveRemind(evaluation_id);
 		System.out.println("44.测评任务数据页-催促提升接口:");
 		Boolean success = (Boolean)JSONPath.read(res, "$.success");
 		System.out.println("success="+success);
 		Assert.assertTrue(success,"44.测评任务数据页-催促提升接口"+res);
 	}
 	
 	
 	@Test(description="45.查询能力项等级接口", priority=45)//固定的5个值
 	public void testAbilityLevels()  {
 		String res = EvaluationBusiness.AbilityLevels();
 		System.out.println("45.查询能力项等级接口:");
 		lever_id = (String)JSONPath.read(res, "$.data[2].id");
 		Boolean success = (Boolean)JSONPath.read(res, "$.success");
 		System.out.println("lever_id="+lever_id+","+"success="+success);
 		Assert.assertTrue(success,"45.查询能力项等级接口"+res);
 	}
 	
 	
 	@Test(description="46.PC端-我的测评任务列表-全部类型接口", priority=46)
 	public void testMyEvaluationList()  {
 		String res = EvaluationBusiness.MyEvaluationList("","","all");
 		System.out.println("46.PC端-我的测评任务列表-全部类型接口:");
 		Integer navigate_pages = (Integer)JSONPath.read(res, "$.navigate_pages");
 		System.out.println("navigate_pages="+navigate_pages);
 		Assert.assertNotEquals(navigate_pages,0,"46.PC端-我的测评任务列表-全部类型接口"+res);
 	}
 	
 	@Test(description="47.PC端-我的测评任务列表-未完成接口", priority=47)
 	public void testMyEvaluationListUnfinished()  {
 		String res = EvaluationBusiness.MyEvaluationList("unfinished","","all");
 		System.out.println("47.PC端-我的测评任务列表-未完成接口:");
 		Integer navigate_pages = (Integer)JSONPath.read(res, "$.navigate_pages");
 		System.out.println("navigate_pages="+navigate_pages);
 		Assert.assertNotEquals(navigate_pages,0,"47.PC端-我的测评任务列表-未完成接口"+res);
 	}
 	
 	@Test(description="48.PC端-我的测评任务列表-已完成接口", priority=48)
 	public void testMyEvaluationListFinished()  {
 		String res = EvaluationBusiness.MyEvaluationList("finished","","all");
 		System.out.println("48.PC端-我的测评任务列表-已完成接口:");
 		Integer navigate_pages = (Integer)JSONPath.read(res, "$.navigate_pages");
 		System.out.println("navigate_pages="+navigate_pages);
 		Assert.assertNotEquals(navigate_pages,0,"48.PC端-我的测评任务列表-已完成接口"+res);
 	}
 	
 	@Test(description="49.PC端-我的测评任务列表-工具类型接口", priority=49)
 	public void testMyEvaluationListTool()  {
 		String res = EvaluationBusiness.MyEvaluationList("","","tool");
 		System.out.println("49.PC端-我的测评任务列表-工具类型接口:");
 		Integer navigate_pages = (Integer)JSONPath.read(res, "$.navigate_pages");
 		System.out.println("navigate_pages="+navigate_pages);
 		Assert.assertNotEquals(navigate_pages,0,"49.PC端-我的测评任务列表-工具类型接口"+res);
 	}
 	
 	@Test(description="50.PC端-我的测评任务列表-自评类型接口", priority=50)
 	public void testMyEvaluationListSelf()  {
 		String res = EvaluationBusiness.MyEvaluationList("","","self");
 		System.out.println("50.PC端-我的测评任务列表-自评类型接口:");
 		Integer navigate_pages = (Integer)JSONPath.read(res, "$.navigate_pages");
 		System.out.println("navigate_pages="+navigate_pages);
 		Assert.assertNotEquals(navigate_pages,0,"50.PC端-我的测评任务列表-自评类型接口"+res);
 	}
 	
 	@Test(description="51.PC端-我的测评任务列表-他评类型接口", priority=51)
 	public void testMyEvaluationListSuperior()  {
 		String res = EvaluationBusiness.MyEvaluationList("","","superior");
 		System.out.println("51.PC端-我的测评任务列表-他评类型接口:");
 		Integer navigate_pages = (Integer)JSONPath.read(res, "$.navigate_pages");
 		System.out.println("navigate_pages="+navigate_pages);
 		Assert.assertNotEquals(navigate_pages,0,"51.PC端-我的测评任务列表-他评类型接口"+res);
 	}
 	
 	
 	@Test(description="52.PC端-我的测评任务列表-已逾期接口", priority=52)
 	public void testMyEvaluationListOverdue()  {
 		String res = EvaluationBusiness.MyEvaluationList("overdue","","all");
 		System.out.println("52.PC端-我的测评任务列表-已逾期接口:");
 		Integer navigate_pages = (Integer)JSONPath.read(res, "$.navigate_pages");
 		System.out.println("navigate_pages="+navigate_pages);
 		Assert.assertNotEquals(navigate_pages,0,"52.PC端-我的测评任务列表-已逾期接口"+res);
 	}
 	
 	
 	@Test(description="53.PC端-我的测评任务列表-按任务名筛选接口", priority=53)
 	public void testMyEvaluationListByName()  {
 		String res = EvaluationBusiness.MyEvaluationList("",evaluation_name,"all");
 		System.out.println("53.PC端-我的测评任务列表-按任务名筛选接口:");
 		Integer navigate_pages = (Integer)JSONPath.read(res, "$.navigate_pages");
 		System.out.println("navigate_pages="+navigate_pages);
 		Assert.assertNotEquals(navigate_pages,0,"53.PC端-我的测评任务列表-按任务名筛选接口"+res);
 	}
 	
 	
 	@Test(description="54.PC端/移动端-未测评前测评任务详情校验接口", priority=54)
 	public void testEvaluationDetailUnfinish()  {
 		String res = EvaluationBusiness.EvaluationDetailQuery(evaluation_id);
 		String res_promotion = EvaluationBusiness.EvaluationDetailQuery(evaluation_promotion_id);
 		System.out.println("54.PC端/移动端-未测评前测评任务详情校验接口:");
 		String evaluation_id = (String)JSONPath.read(res, "$.evaluation_id");
 		String qualified_status = (String)JSONPath.read(res, "$.qualified_status");
 		System.out.println("evaluation_id="+evaluation_id+","+"qualified_status="+qualified_status);
 		Assert.assertNotEquals(evaluation_id,"","54.PC端/移动端-未测评前测评任务详情校验接口"+res);
 	}
 	
 	
 	@Test(description="55.PC端/移动端-未测评前自定义时间的测评任务详情校验接口", priority=55)
 	public void testEvaluationDetailCustom()  {
 		String res = EvaluationBusiness.EvaluationDetailQuery(evaluation_custom_id);
 		System.out.println("55.PC端/移动端-未测评前自定义时间的测评任务详情校验接口:");
 		String qualified_status = (String)JSONPath.read(res, "$.qualified_status");
 		String term_type = (String)JSONPath.read(res, "$.term_type");
 		System.out.println("qualified_status="+qualified_status+","+"term_type="+term_type);
 		Assert.assertEquals(term_type,"custom","55.PC端/移动端-未测评前自定义时间的测评任务详情校验接口"+res);
 	}
 	
 	
 	@Test(description="56.PC/移动端-开始测评接口", priority=56)
 	public void testEvaluationSubmit()  {
 		String res = EvaluationBusiness.EvaluationSubmit(evaluation_id,lever_id,model_id);
 		String res_promotion = EvaluationBusiness.EvaluationSubmit(evaluation_promotion_id,lever_id,model_id);
 		System.out.println("56.PC/移动端-开始测评接口:");
 		Boolean success = (Boolean)JSONPath.read(res, "$.success");
 		System.out.println("success="+success);
 		Assert.assertTrue(success,"56.PC/移动端-开始测评接口"+res);
 	}
 	
 	
 	@Test(description="57.PC/移动端-开始测评-已测评过接口", priority=57)
 	public void testEvaluationSubmitd()  {
 		String res = EvaluationBusiness.EvaluationSubmit(evaluation_id,lever_id,model_id);
 		System.out.println("57.PC/移动端-开始测评-已测评过接口:");
 		Boolean success = (Boolean)JSONPath.read(res, "$.success");
 		System.out.println("success="+success);
 		Assert.assertTrue(success,"57.PC/移动端-开始测评-已测评过接口"+res);
 	}
 	
 	@Test(description="58.PC/移动端-查看能力模型类型的测评报告接口", priority=58)
 	public void testEvaluationModelReport()  {
 		String res = EvaluationBusiness.EvaluationModelReport(evaluation_id);
 		System.out.println("58.PC/移动端-查看能力模型类型的测评报告接口:");
 		String id = (String)JSONPath.read(res, "$.id");
 		System.out.println("id="+id);
 		Assert.assertNotEquals(id,"","58.PC/移动端-查看能力模型类型的测评报告接口"+res);
 	}
 	
 	
 	@Test(description="59.PC/移动端-查看能力模型类型的测评报告-未完成测评任务接口", priority=59)
 	public void testEvaluationModelReportNoData()  {
 		String res = EvaluationBusiness.EvaluationModelReport(evaluation_score_id);
 		System.out.println("59.PC/移动端查看能力模型类型的测评报告-未完成测评任务接口:");
 		String id = (String)JSONPath.read(res, "$.id");
 		System.out.println("id="+id);
 		Assert.assertNotEquals(id,"","59.PC/移动端-查看能力模型类型的测评报告-未完成测评任务接口"+res);
 	}
 	
 	@Test(description="60.PC/移动端-测评结束后学习进度+合格状态+不允许查看测评报告校验接口", priority=60)
 	public void testEvaluationDetailQuery()  {
 		String res = EvaluationBusiness.EvaluationDetailQuery(evaluation_id);
 		System.out.println("60.PC/移动端-测评结束后学习进度+合格状态+不允许查看测评报告校验接口:");
 		String qualified_status = (String)JSONPath.read(res, "$.qualified_status");
 		String open_result = (String)JSONPath.read(res, "$.open_result");
 		Integer evaluation_process = (Integer)JSONPath.read(res, "$.evaluation_process");
 		System.out.println("qualified_status="+qualified_status+","+"evaluation_process="+evaluation_process+","+"open_result="+open_result);
 		Assert.assertEquals(qualified_status,"true","60.PC/移动端-测评结束后学习进度+合格状态+不允许查看测评报告校验接口"+res);
 		Assert.assertEquals(open_result,"false","60.PC/移动端-测评结束后学习进度+合格状态+不允许查看测评报告校验接口"+res);
 		Assert.assertNotEquals(evaluation_process,0,"60.PC/移动端-测评结束后学习进度+合格状态+不允许查看测评报告校验接口"+res);
 	}
 	
 	
 	
 	
 	
 	
 	@Test(description="61.PC/移动端-测评完校验是否获得学分接口", priority=61)
 	public void testGetScoreRecord()  {
 		String res = EvaluationBusiness.GetScoreRecord();
 		System.out.println("61.PC/移动端-测评完校验是否获得学分接口:");
 		//把list看做一个整体，拆成多个jsonobj,匹配到包含evaluation_name的就拿这个jsonobj里的score值
 		JSONArray eventMsgArray = (JSONArray) JSONPath.read(res, "$.list");
 		Integer score = 0;
 		for(Object obj :eventMsgArray) {
 			JSONObject jsonObj	=	(JSONObject) JSONObject.parse(obj.toString());
 			if(jsonObj.getString("eventMsg").contains(evaluation_name)) {
 			score = jsonObj.getInteger("score");
 			}
 		}
 		System.out.println("score="+score);
 		Assert.assertTrue(res.contains(evaluation_name),"61.PC/移动端-测评完校验是否获得学分接口"+res);
 		Assert.assertTrue(score.equals(1),"61.PC/移动端-测评完校验是否获得学分接口"+res);
 	}
 	
 	
 	
 	@Test(description="62.移动端-我的测评任务列表-未完成接口", priority=62)
 	public void testMobileMyEvaluationListUnfinished()  {
 		String res = EvaluationBusiness.MobileMyEvaluationList("unfinished","");
 		System.out.println("62.PC端-我的测评任务列表-未完成接口:");
 		Integer navigate_pages = (Integer)JSONPath.read(res, "$.navigate_pages");
 		System.out.println("navigate_pages="+navigate_pages);
 		Assert.assertNotEquals(navigate_pages,0,"62.PC端-我的测评任务列表-未完成接口"+res);
 	}
 	
 	@Test(description="63.移动端-我的测评任务列表-已完成接口", priority=63)
 	public void testMobileMyEvaluationListFinished()  {
 		String res = EvaluationBusiness.MobileMyEvaluationList("finished","");
 		System.out.println("63.移动端-我的测评任务列表-已完成接口:");
 		Integer navigate_pages = (Integer)JSONPath.read(res, "$.navigate_pages");
 		System.out.println("navigate_pages="+navigate_pages);
 		Assert.assertNotEquals(navigate_pages,0,"63.移动端-我的测评任务列表-已完成接口"+res);
 	}
 	
 	@Test(description="64.移动端-我的测评任务列表-未逾期接口", priority=64)
 	public void testMobileMyEvaluationListOverdue()  {
 		String res = EvaluationBusiness.MobileMyEvaluationList("overdue","");
 		System.out.println("64.移动端-我的测评任务列表-未逾期接口:");
 		Integer navigate_pages = (Integer)JSONPath.read(res, "$.navigate_pages");
 		System.out.println("navigate_pages="+navigate_pages);
 		Assert.assertNotEquals(navigate_pages,0,"64.移动端-我的测评任务列表-未逾期接口"+res);
 	}
 	
 	public static String promotion_course_id = "";
 	@Test(description="66.获取课程列表接口", priority=66)
 	public void testQueryCourseByPage()  {
 		String res = EvaluationBusiness.QueryCourseByPage();
 		System.out.println("66.获取课程列表接口:");
 		promotion_course_id = (String)JSONPath.read(res, "$.list[0].id");
 		System.out.println("promotion_course_id="+promotion_course_id);
 		Assert.assertNotEquals(promotion_course_id,null,"66.获取课程列表接口"+res);
 	}
 	
 	
 	
 	@Test(description="67.设置提升方案课程接口", priority=67)
 	public void testSetAbilityTraining()  {
 		String res = EvaluationBusiness.SetAbilityTraining(evaluation_promotion_id,promotion_course_id);
 		System.out.println("67.设置提升方案课程接口:");
 		Boolean success = (Boolean)JSONPath.read(res, "$.success");
 		System.out.println("success="+success);
 		Assert.assertTrue(success,"67.设置提升方案课程接口"+res);
 	}
 	
 	
 	@Test(description="68.移动端提升方案学习接口", priority=68)
 	public void testStudyPromotionCourse()  {
 		String res = EvaluationBusiness.StudyPromotionCourse(evaluation_promotion_id,promotion_course_id);
 		System.out.println("68.移动端提升方案学习接口:");
 		String resources_id = (String)JSONPath.read(res, "$.resources[0].id");
 		System.out.println("resources_id="+resources_id);
 		Assert.assertNotEquals(resources_id,null,"68.移动端提升方案学习接口"+res);
 	}
 	
 	
 	
 	@Test(description="69.获取课件列表接口", priority=69)
 	public void testResourceGetList()  {
 		String res = EvaluationBusiness.ResourceGetList();
 		System.out.println("69.获取课件列表接口:");
 		course_id = (String)JSONPath.read(res, "$.list[0].id");
 		System.out.println("course_id="+course_id);
 		Assert.assertNotEquals(course_id,null,"69.获取课件列表接口"+res);
 	}
 	
 	
 	@Test(description="70.查询测训工具列表接口", priority=70)
 	//这里存在业务上的bug，所以需要再查一次测评工具列表接口，前边的查询是按名称查，这里随便找一个工具即可
 	public void testEvaluationToolsListQuery()  {
 		String res = EvaluationBusiness.EvaluationToolsList("");
 		System.out.println("70.查询测训工具列表接口:");
 		//测试环境第一个金牌会报错这里避开第一个数据
 		product_id = (Integer)JSONPath.read(res, "$.list[1].product_id");
 		String is_first_page = (String)JSONPath.read(res, "$.is_first_page");
 		System.out.println("success="+is_first_page);
		Assert.assertEquals(is_first_page,"true","70.查询测训工具列表接口"+res);
 	}
 	
 	
 	@Test(description="71.岗位测评关联在学习任务中接口", priority=71)
 	public void testStudyPlanAdd()  {
 		String product_idTemp = Integer.toString(product_id);
 		System.out.println("product_idTemp="+product_idTemp);
 		String res = EvaluationBusiness.StudyPlanAdd(studyPlan_title,classification_id,product_idTemp,course_id);
 		System.out.println("71.岗位测评关联在学习任务中接口:");
 		studyPlan_id = (String)JSONPath.read(res, "$.data");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg+","+"studyPlan_id="+studyPlan_id);
 		Assert.assertEquals(msg,"新增计划成功！","71.岗位测评关联在学习任务中接口"+res);
 	}
 	
 	@Test(description="72.校验学员端-我的学习任务列表有数据接口", priority=72)
 	public void testMyTaskGetList()  {
 		String res = TutorOperationBusiness.MyTaskGetList(studyPlan_title);
 		Integer total = (Integer) JSONPath.read(res, "$.total");
 		System.out.println("72.校验学员端-我的学习任务列表有数据接口:"+"total="+total);
 		Assert.assertNotEquals(total, 0,"72.校验学员端-我的学习任务列表有数据接口" + res);
 	}
 	
 	@Test(description="73.获取学习任务里岗位测评id+校验合格标准接口", priority=73)
 	public void testGetEvaluationId()  {
 		String res = EvaluationBusiness.GetEvaluationId(studyPlan_id);
 		studyPlan_evaluation_id = (String) JSONPath.read(res, "$.data.courseInfo[0].courseId");
 		String passStandard = (String) JSONPath.read(res, "$.data.passStandard");
 		System.out.println("73.获取学习任务里岗位测评id+校验合格标准接口:"+"studyPlan_evaluation_id="+studyPlan_evaluation_id);
 		Assert.assertNotEquals(studyPlan_evaluation_id,null,"73.获取学习任务里岗位测评id+校验合格标准接口" + res);
 		Assert.assertTrue(passStandard.contains("完成岗位测评"),"73.获取学习任务里岗位测评id+校验合格标准接口" + res);
 	}
 	
 	
 	@Test(description="74.创建学习项目接口", priority=74)
 	public void testStudyProjectSave()  {
 		String cover = "https://oss.coolcollege.cn/1810048818682974377.png";
        String base_cover= "https://oss.coolcollege.cn/1810048818682974377.png";
 		String resFirst = EvaluationBusiness.StudyProjectSaveBaseInfo(title_project,classification_id,cover,base_cover);
 		project_id = (String) JSONPath.read(resFirst, "$.id"); 
 		projectCourse_id = (String) JSONPath.read(resFirst, "$.course_id"); 
 		String product_idTemp = Integer.toString(product_id);
 		String resSecond = EvaluationBusiness.StudyProjectSaveStageContent(project_id,course_id,product_idTemp);		
 		String resThird = EvaluationBusiness.StudyProjectSaveSettings(project_id);
 		String result = (String) JSONPath.read(resThird, "$.result");		
 		System.out.println("74.创建学习项目接口:"+"project_id="+project_id);
 		Assert.assertEquals(result, "true","74.创建学习项目接口" + resThird);
 	}
 	
 	
 	@Test(description="75.学习平台知识库下查询学习项目-自学学习项目接口", priority=75)
 	public void testStudyProjectsQuery()  {
 		String res = EvaluationBusiness.StudyProjectsQuery(title_project);
 		Integer total = (Integer) JSONPath.read(res, "$.total"); 		
 		System.out.println("75.学习平台知识库下查询学习项目-自学学习项目接口:"+"total="+total);
 		Assert.assertNotEquals(total,null,"75.学习平台知识库下查询学习项目-自学学习项目接口" + res);
 	}
 	
 	
 	@Test(description="76.学习项目详情+校验合格标准接口", priority=76)
 	public void testStudyProjectsDetail()  {
 		System.out.println("projectCourse_id="+projectCourse_id);
 		String res = EvaluationBusiness.StudyProjectsDetail(projectCourse_id);
 		String finish_evaluation = (String) JSONPath.read(res, "$.finish_evaluation"); 
 		studyProject_evaluation_id = (String) JSONPath.read(res, "$.stages[0].resources[1].course_id");
 		System.out.println("76.学习项目详情+校验合格标准接口:"+"finish_evaluation="+finish_evaluation+","+"studyProject_evaluation_id="
 		+studyProject_evaluation_id);
 		Assert.assertEquals(finish_evaluation,"true","76.学习项目详情+校验合格标准接口" + res);
 	}
 	
 	
 	@Test(description="77.进入岗位测评前的start接口", priority=77)
 	public void testStartStudy()  {
 		String res= EvaluationBusiness.StartStudy(projectCourse_id);
 		String resPlan= EvaluationBusiness.StartStudy(studyPlan_id);
 		String result = (String) JSONPath.read(res, "$.result");
 		System.out.println("77.进入岗位测评前的start接口:"+"result="+result);
 		Assert.assertEquals(result,"true","77.进入岗位测评前的start接口" + res);
 	}
 	
 	
 	@Test(description="78.进入学习任务/学习项目的岗位测评接口", priority=78)
 	//后边的测评接口是第三方，不做处理
 	public void testOpenEvaluation ()  {
 		String resStudyPlan = EvaluationBusiness.OpenEvaluation(studyPlan_evaluation_id);
 		String resStudyProject = EvaluationBusiness.OpenEvaluation(studyProject_evaluation_id);
 		String id_StudyPlan = (String) JSONPath.read(resStudyPlan, "$.id");
 		String id_StudyProject = (String) JSONPath.read(resStudyProject, "$.id");
 		System.out.println("78.进入学习任务/学习项目的岗位测评接口:"+"id_StudyPlan="+id_StudyPlan+","+"id_StudyProject="+id_StudyProject);
 		Assert.assertNotEquals(id_StudyPlan,null,"78.进入学习任务/学习项目的岗位测评接口" + resStudyPlan);
 		Assert.assertNotEquals(id_StudyProject,null,"78.进入学习任务/学习项目的岗位测评接口" + resStudyProject);
 	}
 		
 	
 	@Test(description="79.删除学习任务接口", priority=79)
 	public void testDeleteStudy()  {
 		String res = EvaluationBusiness.DeleteStudy(studyPlan_id);
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("79.删除学习任务接口:"+"msg="+msg);
 		Assert.assertEquals(msg, "删除学习计划成功","79.删除学习任务接口" + msg);
 	}
 	
 	@Test(description="80.删除学习项目接口", priority=80)
 	public void testDeleteProject()  {
 		String res = EvaluationBusiness.DeleteProject(project_id);
 		String deleted = (String) JSONPath.read(res, "$.deleted");
 		System.out.println("80.删除学习项目接口:"+"deleted="+deleted);
 		Assert.assertEquals(deleted, "true","80.删除学习项目接口" + deleted);
 	}
 	
 	
 	@Test(description="81.删除测评任务接口", priority=81)
 	public void testEvaluationDelete()  {
 		String res = EvaluationBusiness.EvaluationDelete(evaluation_id);
 		String res_score = EvaluationBusiness.EvaluationDelete(evaluation_score_id);
 		String res_superior = EvaluationBusiness.EvaluationDelete(evaluation_superior_id);
 		String res_tool = EvaluationBusiness.EvaluationDelete(evaluationTool_id);
 		String res_custom = EvaluationBusiness.EvaluationDelete(evaluation_custom_id);
 		String res_draft = EvaluationBusiness.EvaluationDelete(evaluation_draft_id);
 		String res_promotion = EvaluationBusiness.EvaluationDelete(evaluation_promotion_id);
 		System.out.println("81.删除测评任务接口:");
 		String message = (String)JSONPath.read(res, "$.message");
 		System.out.println("message="+message);
		Assert.assertEquals(message,"SUCCESS","81.删除测评任务接口"+res);
 	}
 	
 	
 	@Test(description="82.删除测训工具接口", priority=82)
 	public void testDeleteEvaluationTool()  {
 		String res = EvaluationBusiness.DeleteEvaluationTool(evaluation_tool_id);
 		System.out.println("82.删除测训工具接口:");
 		String result = (String)JSONPath.read(res, "$.result");
 		System.out.println("result="+result);
		Assert.assertEquals(result,"true","82.删除测训工具接口"+res);
 	}
 	
 	@Test(description="83.删除测训工具-已被删除过接口", priority=83)
 	public void testDeletedEvaluationTool()  {
 		String res = EvaluationBusiness.DeleteEvaluationTool(evaluation_tool_id);
 		System.out.println("83.删除测训工具-已被删除过接口:");
 		String result = (String)JSONPath.read(res, "$.result");
 		System.out.println("result="+result);
		Assert.assertEquals(result,"true","83.删除测训工具-已被删除过接口"+res);
 	}
 	
 		
 	@Test(description="84.删除测训能力项接口", priority=84)
 	public void testDeleteAbility()  {
 		String res = EvaluationBusiness.DeleteAbility(ability_id);
 		System.out.println("84.删除测训能力项接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Boolean success = (Boolean)JSONPath.read(res, "$.success");
 		System.out.println("msg="+msg+","+"success="+success);
		Assert.assertTrue(success,"84.删除测训能力项接口"+res);
 	}
 	
 	@Test(description="85.查询能力项等级列表接口", priority=85)
 	public void testAbilityLevelsList()  {
 		String res = EvaluationBusiness.AbilityLevelsList();
 		System.out.println("85.查询能力项等级列表接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Boolean success = (Boolean)JSONPath.read(res, "$.success");
 		System.out.println("msg="+msg+","+"success="+success);
		Assert.assertTrue(success,"85.查询能力项等级列表接口"+res);
 	}
 	
 	@Test(description="86.删除测训模型接口", priority=86)
 	public void testDeleteModel()  {
 		String res = EvaluationBusiness.DeleteModel(model_id);
 		System.out.println("86.删除测训模型接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		Boolean success = (Boolean)JSONPath.read(res, "$.success");
 		System.out.println("msg="+msg+","+"success="+success);
		Assert.assertEquals(msg,"success","86.删除测训模型接口"+res);
 	}

}
