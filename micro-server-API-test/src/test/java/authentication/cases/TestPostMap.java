package authentication.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.authentication.business.PostAuthenticationBusiness;
import cn.kxy.authentication.business.PostMapBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.lazy.init.cases.InitStudyAuthCourse;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestPostMap extends InitStudyAuthCourse{

	String name ="Post certification of financial"+CommonData.getStringRandom(3);
	String des = "this is des";
	String id ="";
	String post_name1 ="settled_post1";
	String post_name2 ="settled_post2";
	String qualification_id1 ="";
	String qualification_id2 ="";
	String edit_name = "Post cert of finance"+CommonData.getStringRandom(4);
	@BeforeClass
	public void initMap () {
		String queryList1 = PostAuthenticationBusiness.queryList(post_name1, "0");
		int total1 = (int)JSONPath.read(queryList1, "$.total");
		String status1 = (String)JSONPath.read(queryList1, "$.list[0].statusName");
		qualification_id1= (String)JSONPath.read(queryList1, "$.list[0].id");
		if (total1==0) {
			String res1 = PostAuthenticationBusiness.addPostAuthentication(post_name1, des, CertificateBusiness.getIdByKeyword(cert_name), 
					"0", ArticleBusiness.getIdByKeyword(articlename), CourseBusiness.getIdByPage(course_name), 
					"sc", "This is a sc desc", "exam", "120",PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name) ,  BaseBusiness.pass_paper_name);
			String msg1 = (String)JSONPath.read(res1, "$.msg");
			if (status1=="停用") {
				PostAuthenticationBusiness.StartShutdownPost(qualification_id1, "1");
			}
			Assert.assertEquals(msg1, "新增计划成功！","添加岗位认证"+res1);
		}
		
		
		String queryList2 = PostAuthenticationBusiness.queryList(post_name2, "0");
		int total2 = (int)JSONPath.read(queryList2, "$.total");
		String status2 = (String)JSONPath.read(queryList2, "$.list[0].statusName");
		qualification_id2= (String)JSONPath.read(queryList2, "$.list[0].id");
		if (total2==0) {
			String res2 = PostAuthenticationBusiness.addPostAuthentication(post_name2, des, CertificateBusiness.getIdByKeyword(cert_name), 
					"0", ArticleBusiness.getIdByKeyword(articlename), CourseBusiness.getIdByPage(course_name), 
					"sc", "This is a sc desc", "exam", "120",PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name) ,  BaseBusiness.pass_paper_name);
			String msg2 = (String)JSONPath.read(res2, "$.msg");
			if (status2=="停用") {
				PostAuthenticationBusiness.StartShutdownPost(qualification_id2, "1");
			}
			Assert.assertEquals(msg2, "新增计划成功！","添加岗位认证"+res2);
		}
	}
	
	@Test(description="新增岗位地图",priority=1)
	public void testAddPostMap() {
		String res = PostMapBusiness.addPostMap(name,"all", des, qualification_id1, qualification_id2,"DESERT_OASIS");
		id = (String)JSONPath.read(res, "$.id");
		Assert.assertTrue(id!=null,"新增岗位地图，实际返回结果："+res);
	}
	@Test(description="查询岗位地图列表--全部",priority=2)
	public void testQueryPostMapsList() {
		String res = PostMapBusiness.queryPostMapsList(name, "");
		String title = (String)JSONPath.read(res, "$.list[0].name");
		int total = (int)JSONPath.read(res, "$.list[0].checkpoint_total_count");
		int user_count = (int)JSONPath.read(res, "$.list[0].user_count");
		Long create_time = (Long)JSONPath.read(res, "$.list[0].create_time");
		String create_user_name = (String)JSONPath.read(res, "$.list[0].create_user_name");
		String status = (String)JSONPath.read(res, "$.list[0].status");
		id=(String)JSONPath.read(res, "$.list[0].id");
		Assert.assertEquals(status, "open","查询岗位地图列表--全部,(状态进行校验)"+res);
		Assert.assertEquals(create_user_name, UserBusiness.getUsername(),"查询岗位地图列表--全部,(创建人进行校验)"+res);
		Assert.assertTrue(create_time!=null, "查询岗位地图列表--全部,（创建时间进行校验）"+res);
		Assert.assertEquals(user_count, 0,"查询岗位地图列表--全部,(参与员工数进行校验)"+res);
		Assert.assertEquals(total, 2,"查询岗位地图列表--全部,(关卡数进行校验)"+res);
		Assert.assertEquals(title, name,"查询岗位地图列表--全部（名称进行校验）"+res);
	}
	@Test (description="停用岗位地图",priority=3)
	public void testUpdateCloseStatus() {
		String res = PostMapBusiness.updateStatus(id, "close");
		String map_id = (String)JSONPath.read(res, "$.id");
		Assert.assertTrue(map_id!=null,"停用岗位地图，实际返回结果："+res);
	}
	@Test(description="查询岗位地图列表--已停用",priority=4)
	public void testQueryPostMapsCloseList() {
		String res = PostMapBusiness.queryPostMapsList(name, "close");
		String title = (String)JSONPath.read(res, "$.list[0].name");
		int total = (int)JSONPath.read(res, "$.list[0].checkpoint_total_count");
		int user_count = (int)JSONPath.read(res, "$.list[0].user_count");
		Long create_time = (Long)JSONPath.read(res, "$.list[0].create_time");
		String status = (String)JSONPath.read(res, "$.list[0].status");
		Assert.assertEquals(status, "close","查询岗位地图列表--已停用,(状态进行校验)"+res);
		Assert.assertTrue(create_time!=null, "查询岗位地图列表--已停用,（创建时间进行校验）"+res);
		Assert.assertEquals(user_count, 0,"查询岗位地图列表--已停用,(参与员工数进行校验)"+res);
		Assert.assertEquals(total, 2,"查询岗位地图列表--已停用,(关卡数进行校验)"+res);
		Assert.assertEquals(title, name,"查询岗位地图列表--已停用（名称进行校验）"+res);
	}
	
	@Test (description="启用岗位地图",priority=5)
	public void testUpdateOpenStatus() {
		String res = PostMapBusiness.updateStatus(id, "open");
		String map_id = (String)JSONPath.read(res, "$.id");
		Assert.assertTrue(map_id!=null,"启用岗位地图，实际返回结果："+res);
	}
	@Test(description="查询岗位地图列表--已启用",priority=6)
	public void testQueryPostMapsOpenList() {
		String res = PostMapBusiness.queryPostMapsList(name, "open");
		String title = (String)JSONPath.read(res, "$.list[0].name");
		int total = (int)JSONPath.read(res, "$.list[0].checkpoint_total_count");
		int user_count = (int)JSONPath.read(res, "$.list[0].user_count");
		Long create_time = (Long)JSONPath.read(res, "$.list[0].create_time");
		String status = (String)JSONPath.read(res, "$.list[0].status");
		Assert.assertEquals(status, "open","查询岗位地图列表--已启用,(状态进行校验)"+res);
		Assert.assertTrue(create_time!=null, "查询岗位地图列表--已启用,（创建时间进行校验）"+res);
		Assert.assertEquals(user_count, 0,"查询岗位地图列表--已启用,(参与员工数进行校验)"+res);
		Assert.assertEquals(total, 2,"查询岗位地图列表--已启用,(关卡数进行校验)"+res);
		Assert.assertEquals(title, name,"查询岗位地图列表--已启用（名称进行校验）"+res);
	}
	
	@Test(description = "清除所有导出数据",priority = 7)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	
	@Test(description="导出岗位地图的数据",priority=8)
	public void testGetPostMapsExportCode() {
		int code = PostMapBusiness.getPostMapsExportCode(id, "");
		Assert.assertEquals(code, 200,"导出岗位地图的数据:"+code);
	}
	
	@Test(description="导出岗位地图的数据_ByKeyWord",priority=9)
	public void testGetPostMapsExportCodeByName() {
		int code = PostMapBusiness.getPostMapsExportCode(id, UserBusiness.getUsername());
		Assert.assertEquals(code, 200,"导出岗位地图的数据_ByKeyWord:"+code);
	}
	
	@Test(description = "导出实操作业分析后，在下载中心查看",priority = 10)
	public void exportRecordList() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String res = PaperExportBusiness.exportRecordList();
		String status = (String)JSONPath.read(res, "$.list[0].status");
		String type = (String)JSONPath.read(res, "$.list[0].type");
		String status_1 = (String)JSONPath.read(res, "$.list[1].status");
		String type_1 = (String)JSONPath.read(res, "$.list[1].type");
		Assert.assertFalse(status=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_1=="FAILED", "查看导出的结果:"+res);
	}
	
	@Test(description="查看岗位地图未认证前的统计数据",priority=11)
	public void testMonitorPostMap() {
		String res = PostMapBusiness.monitorPostMap(id, "");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertNotNull(total,"查看岗位地图未认证前的统计数据，对总数进行校验，未认证前应为0"+res);
	} 
	@Test(description="编辑岗位地图",priority=12)
	public void testEditPostMap() {
		String res = PostMapBusiness.editPostMap(id, qualification_id1, qualification_id2, edit_name);
		String map_id = (String)JSONPath.read(res, "$.id");
		Assert.assertTrue(map_id!=null,"编辑岗位地图，实际返回结果："+res);
	}
	@Test(description="查看岗位地图详情",priority=13)
	public void testQueryPostMapInfo() {
		String res = PostMapBusiness.queryInfo(id);
		String title = (String)JSONPath.read(res, "$.name");
		String introduction = (String)JSONPath.read(res, "$.introduction");
		Assert.assertEquals(introduction, "this is des","查看编辑后的岗位地图详情,描述字段"+res);
		Assert.assertEquals(title, edit_name,"查看编辑后的岗位地图详情，name字段"+res);
	}
	
	@Test(description="添加岗位地图，name为null",priority=14)
	public void testAddPostMapNameIsNull() {
		String addPostMap = PostMapBusiness.addPostMap("","", "", "", "","DESERT_OASIS");
		String msg = (String)JSONPath.read(addPostMap, "$.data[0].message");
		Assert.assertEquals(msg, "name not empty","添加岗位地图，name为null，实际返回结果："+addPostMap);
	}
	
	@Test(description="添加岗位地图，岗位认证的id为null",priority=15)
	public void testAddPostMapPostmapIsNull() {
		String addPostMap = PostMapBusiness.addPostMap("aa","", "", "", "","DESERT_OASIS");
		String msg = (String)JSONPath.read(addPostMap, "$.message");
		Assert.assertEquals(msg, "Invalid postmap","添加岗位地图，岗位认证的id为null，实际返回结果："+addPostMap);
	}
	
	@Test(description="删除岗位地图",priority=16)
	public void testDeleteMap() {
		String res = PostMapBusiness.deleteMap(id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功","删除岗位地图"+res);
	}
	
	String post_all_id = "";
	@Test(description= "添加可见范围为部分的岗位地图",priority=17)
	public void testAddPostMapPart() {
		String title = "PostMapvisiblerange"+CommonData.getStringRandom(5);
		String res = PostMapBusiness.addPostMap(title,"part", des, qualification_id1, qualification_id2,"DESERT_OASIS");
		post_all_id = (String)JSONPath.read(res, "$.id");
		Assert.assertTrue(id!=null,"添加可见范围为部分的岗位地图，实际返回结果："+res);
	}
	
	@Test(description="查看可见范围为部分的详情",priority=18)
	public void testQueryAllInfo() {
		String res = PostMapBusiness.queryInfo(post_all_id);
		String visible_range = (String)JSONPath.read(res, "$.visible_range");
		Assert.assertEquals(visible_range, "part","查看可见范围为部分的详情,可见范围进行验证："+res);
	}
	
	@Test(description="编辑岗位地图，可见范围为全部",priority=19)
	public void testEditPostMapAll() {
		String title = "EditPostMapvisiblerange"+CommonData.getStringRandom(5);
		String res = PostMapBusiness.editPostMap(post_all_id, qualification_id1, qualification_id2, title);
		String map_id = (String)JSONPath.read(res, "$.id");
		Assert.assertTrue(map_id!=null,"编辑岗位地图，实际返回结果："+res);
	}
	@Test(description="查看可见范围为全部的详情",priority=20)
	public void testQueryPartInfo() {
		String res = PostMapBusiness.queryInfo(post_all_id);
		String visible_range = (String)JSONPath.read(res, "$.visible_range");
		Assert.assertEquals(visible_range, "all","查看可见范围为全部的详情,可见范围进行验证："+res);
	}
	@Test(description="删除编辑后的岗位地图",priority=21)
	public void test() {
		String res = PostMapBusiness.deleteMap(post_all_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除成功","删除岗位地图"+res);
	}
	
	
}
