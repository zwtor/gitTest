package course.cases;

import cn.kxy.course.resources.bussiness.CoursewareBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCourseware {
	String foldername = "folder"+CommonData.getStringRandom(5);
	String editname= "coursefolder"+CommonData.getStringRandom(5);
	String child_name = "childFolder"+CommonData.getStringRandom(5);
	String id= "";
	String child_id = "";
	String art_name = "My Business"+CommonData.getStringRandom(5);
	@Test(description="在课件管理页面新增文件夹",priority=1)
	public void testAddFolder() {
		String res = CoursewareBusiness.addFolder(foldername, "0","","all");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "文件夹新增成功","在课件管理页面新增文件夹实际返回结果："+res);
	}
	@Test(description="在课件管理列表页查看文件夹",priority=2)
	public void testQueryList() {
		String res= CoursewareBusiness.queryList(foldername);
		id= (String)JSONPath.read(res, "$.list[0].resource.id");
		String name = (String)JSONPath.read(res, "$.list[0].resource.name");
		String createUserName = (String)JSONPath.read(res, "$.list[0].resource.createUserName");
		Long createTime = (Long)JSONPath.read(res, "$.list[0].resource.createTime");
		Assert.assertEquals(createUserName, UserBusiness.getUsername(),"在课件管理列表页查看文件夹实际返回结果："+res);
		String visibleRange = (String)JSONPath.read(res, "$.list[0].resource.visibleRange");
		Assert.assertEquals(visibleRange, "all","在子文件列表页查询,实际返回结果："+res);
		Assert.assertTrue(createTime!=null, "在课件管理列表页查看文件夹实际返回结果："+res);
		Assert.assertEquals(name, foldername,"在课件管理列表页查看文件夹实际返回结果："+res);
	}
	@Test(description="在课件管理页面编辑文件夹",priority=3)
	public void testRenameFolder() {
		String res= CoursewareBusiness.renameFolder(id, editname);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "文件夹编辑成功","在课件管理页面编辑文件夹实际返回结果："+res);
	}
	@Test(description = "在文件夹下添加子文件",priority = 4)
	public void testAddChildFolder() {
		String res = CoursewareBusiness.addFolder(child_name, id,"","all");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "文件夹新增成功","在文件夹下添加子文件,实际返回结果："+res);
	}
	
	@Test(description = "在子文件列表页查询",priority = 5)
	public void testQueryChildFolder() {
		String res= CoursewareBusiness.queryList(child_name,id);
		child_id= (String)JSONPath.read(res, "$.list[0].resource.id");
		String name = (String)JSONPath.read(res, "$.list[0].resource.name");
		String createUserName = (String)JSONPath.read(res, "$.list[0].resource.createUserName");
		Long createTime = (Long)JSONPath.read(res, "$.list[0].resource.createTime");
		
		String visibleRange = (String)JSONPath.read(res, "$.list[0].resource.visibleRange");
		Assert.assertEquals(visibleRange, "all","在子文件列表页查询,实际返回结果："+res);
		Assert.assertEquals(createUserName, UserBusiness.getUsername(),"在子文件列表页查询,实际返回结果："+res);
		Assert.assertTrue(createTime!=null, "在子文件列表页查询,实际返回结果："+res);
		Assert.assertEquals(name, child_name,"在子文件列表页查询,实际返回结果："+res);
	}
	
	@Test(description = "删除子文件夹",priority = 6)
	public void testDeleteChildFolder() {
		String res = CoursewareBusiness.deleteFolder(child_id);
		String msg = (String)JSONPath.read(res, "$.msg"); 
		Assert.assertEquals(msg,"文件夹删除成功","删除子文件夹。夹实际返回结果："+res);
	}
	@Test(description = "删除父文件夹",priority = 7)
	public void testdeleteFattherFolder() {
		String res = CoursewareBusiness.deleteFolder(id);
		String msg = (String)JSONPath.read(res, "$.msg"); 
		Assert.assertEquals(msg,"文件夹删除成功","删除父文件夹，删除文件夹实际返回结果："+res);
	}
	@Test(description = "验证父文件夹是否删除成功",priority = 8)
	public void testCheckFatherFolder() {
		String res= CoursewareBusiness.queryList(foldername);
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total==0,"验证父文件夹是否删除成功,实际返回结果："+res);
	}
	
	@Test(description = "验证子文件夹是否删除成功",priority = 9)
	public void testCheckChildFolder() {
		String res= CoursewareBusiness.queryList(child_name);
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total==0,"验证子文件夹是否删除成功,实际返回结果："+res);
	}
	String myself_id = "";
	String myself_name = "MyselfFolder"+CommonData.getStringRandom(5);
	@Test(description = "添加仅自己可见的文件夹",priority = 10)
	public void testAddMyselfFolder() {
		String res = CoursewareBusiness.addFolder(myself_name, "0","","mySelf");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "文件夹新增成功","添加仅自己可见的文件夹,实际返回结果："+res);
	}
	@Test(description = "在文件列表页查询仅自己可见的文件",priority = 11)
	public void testQueryMyselfFolder() {
		String res= CoursewareBusiness.queryList(myself_name);
		myself_id= (String)JSONPath.read(res, "$.list[0].resource.id");
		String name = (String)JSONPath.read(res, "$.list[0].resource.name");
		String createUserName = (String)JSONPath.read(res, "$.list[0].resource.createUserName");
		Long createTime = (Long)JSONPath.read(res, "$.list[0].resource.createTime");
		
		String visibleRange = (String)JSONPath.read(res, "$.list[0].resource.visibleRange");
		Assert.assertEquals(visibleRange, "myself","在文件列表页查询仅自己可见的文件,实际返回结果："+res);
		Assert.assertEquals(createUserName, UserBusiness.getUsername(),"在文件列表页查询仅自己可见的文件,实际返回结果："+res);
		Assert.assertTrue(createTime!=null, "在文件列表页查询仅自己可见的文件,实际返回结果："+res);
		Assert.assertEquals(name, myself_name,"在文件列表页查询仅自己可见的文件,实际返回结果："+res);
	}
	@Test(description = "删除仅自己可见的文件夹",priority = 12)
	public void testDeleteMyselfFolder() {
		String res = CoursewareBusiness.deleteFolder(myself_id);
		String msg = (String)JSONPath.read(res, "$.msg"); 
		Assert.assertEquals(msg,"文件夹删除成功","删除仅自己可见的文件夹，实际返回结果："+res);
	}
	@Test(description = "验证仅自己可见的文件夹是否删除成功",priority = 13)
	public void testCheckMyslefFolder() {
		String res= CoursewareBusiness.queryList(foldername);
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total==0,"验证仅自己可见的文件夹是否删除成功,实际返回结果："+res);
	}
	
	String part_id = "";
	String part_name = "PartFolder"+CommonData.getStringRandom(5);
	@Test(description = "添加部分可见的文件夹",priority = 14)
	public void testAddPartFolder() {
		String res = CoursewareBusiness.addFolder(part_name, "0",UserBusiness.getUserId(),"part");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "文件夹新增成功","添加部分可见的文件夹,实际返回结果："+res);
	}
	@Test(description = "在文件列表页查询部分可见的文件",priority = 15)
	public void testQueryPartFolder() {
		String res= CoursewareBusiness.queryList(part_name);
		part_id= (String)JSONPath.read(res, "$.list[0].resource.id");
		String name = (String)JSONPath.read(res, "$.list[0].resource.name");
		String createUserName = (String)JSONPath.read(res, "$.list[0].resource.createUserName");
		Long createTime = (Long)JSONPath.read(res, "$.list[0].resource.createTime");
		
		String visibleRange = (String)JSONPath.read(res, "$.list[0].resource.visibleRange");
		Assert.assertEquals(visibleRange, "part","在文件列表页查询部分可见的文件,实际返回结果："+res);
		Assert.assertEquals(createUserName, UserBusiness.getUsername(),"在文件列表页查询部分可见的文件,实际返回结果："+res);
		Assert.assertTrue(createTime!=null, "在文件列表页查询部分可见的文件,实际返回结果："+res);
		Assert.assertEquals(name, part_name,"在文件列表页查询部分可见的文件,实际返回结果："+res);
	}
	@Test(description = "删除部分可见的文件夹",priority = 16)
	public void testDeletePartFolder() {
		String res = CoursewareBusiness.deleteFolder(part_id);
		String msg = (String)JSONPath.read(res, "$.msg"); 
		Assert.assertEquals(msg,"文件夹删除成功","删除部分可见的文件夹，实际返回结果："+res);
	}
	@Test(description = "验证部分可见的文件夹是否删除成功",priority = 17)
	public void testCheckPartFolder() {
		String res= CoursewareBusiness.queryList(part_name);
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total==0,"验证部分可见的文件夹是否删除成功,实际返回结果："+res);
	}
	String visible_range_id = "";
	String visible_range_name = ""+CommonData.getStringRandom(5);
	@Test(description = "获取设置课件可见范围的id",priority = 18)
	public void testGetCourseFolderId() {
		CoursewareBusiness.addFolder(visible_range_name, "0","","all");
		String res= CoursewareBusiness.queryList(visible_range_name);
		visible_range_id= (String)JSONPath.read(res, "$.list[0].resource.id");
	}
	
	@Test(description = "设置课件可见范围为全部" , priority = 19)
	public void testSetVisibleToAll() {
		String res = CoursewareBusiness.setVisible(visible_range_id, "all", "");
		Assert.assertTrue(res.contains("true"),"设置课件可见范围为全部,实际返回结果："+res);
	}
	@Test(description = "在列表页验证设置课件可见范围为全部",priority = 20)
	public void testCheckVisibleToAll() {
		String res= CoursewareBusiness.queryList(visible_range_name);
		String visibleRange = (String)JSONPath.read(res, "$.list[0].resource.visibleRange");
		Assert.assertEquals(visibleRange, "all","在列表页验证设置课件可见范围为全部,实际返回结果："+res);
	}
	
	@Test(description = "设置课件可见范围为仅自己" , priority = 21)
	public void testSetVisibleToMyself() {
		String res = CoursewareBusiness.setVisible(visible_range_id, "myself", "");
		Assert.assertTrue(res.contains("true"),"设置课件可见范围为仅自己,实际返回结果："+res);
	}
	@Test(description = "在列表页验证设置课件可见范围为仅自己",priority = 22)
	public void testCheckVisibleToMyself() {
		String res= CoursewareBusiness.queryList(visible_range_name);
		String visibleRange = (String)JSONPath.read(res, "$.list[0].resource.visibleRange");
		Assert.assertEquals(visibleRange, "myself","在列表页验证设置课件可见范围仅自己,实际返回结果："+res);
	}
	
	@Test(description = "设置课件可见范围为部分" , priority = 23)
	public void testSetVisibleToPart() {
		String res = CoursewareBusiness.setVisible(visible_range_id, "part", UserBusiness.getUserId());
		Assert.assertTrue(res.contains("true"),"设置课件可见范围为部分,实际返回结果："+res);
	}
	@Test(description = "在列表页验证设置课件可见范围为部分",priority = 24)
	public void testCheckVisibleToPart() {
		String res= CoursewareBusiness.queryList(visible_range_name);
		String visibleRange = (String)JSONPath.read(res, "$.list[0].resource.visibleRange");
		Assert.assertEquals(visibleRange, "part","在列表页验证设置课件可见范围为部分,实际返回结果："+res);
	}
	
	@Test(description = "删除课件",priority = 25)
	public void testDele() {
		String res = CoursewareBusiness.deleteFolder(visible_range_id);
		String msg = (String)JSONPath.read(res, "$.msg"); 
		Assert.assertEquals(msg,"文件夹删除成功","删除文件夹，实际返回结果："+res);
	}
	
	@Test(description="添加文章后查询",priority=26)
	public void testQueryArtList() {
//		ArticleBusiness.addArticle(art_name, "link", "http://www.baidu.com", "", "0");
//		String res= CoursewareBusiness.queryList(art_name);
//		String art_id= (String)JSONPath.read(res, "$.list[0].resource.id");
//		String name = (String)JSONPath.read(res, "$.list[0].resource.name");
//		String createUserName = (String)JSONPath.read(res, "$.list[0].resource.creatorName");
//		Long createTime = (Long)JSONPath.read(res, "$.list[0].resource.createTime");
//		int size = (int)JSONPath.read(res, "$.list[0].resource.size");
//		ArticleBusiness.deleteArticle(art_id);
//		Assert.assertTrue(size>=0, "在课件管理列表页查看文章实际返回结果:"+res);
//		Assert.assertEquals(createUserName, UserBusiness.getUsername(),"在课件管理列表页查看文章实际返回结果："+res);
//		Assert.assertTrue(createTime!=null, "在课件管理列表页查看文章实际返回结果："+res);
//		Assert.assertEquals(name, art_name,"在课件管理列表页查看文章实际返回结果："+res);
	}
	
}
