package annoucement.cases.cases;

import cn.kxy.announcements.business.AnnouncementsBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAnnouncementsVisibleRange {

	String title ="Appreciation of famous phrases"+CommonData.getStringRandom(5);
	String des = "Time goes by so fast, people go in and out of your life. "
			+ "You must never miss the opportunity to tell these people how much they mean to you";
	String id = "";
	String user_id = UserBusiness.getUserId();
	@Test(description="新增全员可见的公告",priority=1)
	public void testAddAnnouncementsToAll() {
		String res = AnnouncementsBusiness.addAnnouncements(title, "OPEN", des,"all","");
		String insert = (String)JSONPath.read(res, "$.insert");
		Assert.assertEquals(insert, "true","新增公告实际返回结果："+res);
	}
	@Test(description = "新增全员可见的公告后，在列表页查看",priority = 2)
	public void testQueryAnnouncementsList() {
		String res = AnnouncementsBusiness.queryAnnouncementsList(title);
		String is_all = (String)JSONPath.read(res, "$.list[0].is_all");
		id = (String)JSONPath.read(res, "$.list[0].id");
		Assert.assertEquals(is_all, "all","新增全员可见的公告后，在列表页查看,实际返回结果:"+res);
	}
	
	@Test(description = "将公告编辑为部分人可见",priority = 3)
	public void testEditAnnouncements() {
		String res = AnnouncementsBusiness.editAnnouncements(id, title, "OPEN", des, id, "part",user_id);
		String update = (String)JSONPath.read(res, "$.update");
		Assert.assertEquals(update, "true","编辑公告实际返回结果："+res);
	}
	
	@Test(description = "将公告编辑为部分人可见，在列表页查看",priority = 4)
	public void testQueryAnnouncementsListPart() {
		String res = AnnouncementsBusiness.queryAnnouncementsList(title);
		String is_all = (String)JSONPath.read(res, "$.list[0].is_all");
		Assert.assertEquals(is_all, "part","将公告编辑为部分人可见，在列表页查看,实际返回结果:"+res);
	}
	@Test(description = "删除编辑后的公告" ,priority = 5)
	public void testDeleteAnnouncement() {
		String res = AnnouncementsBusiness.deleteAnnouncements(id);
		String delete = (String)JSONPath.read(res, "$.delete");
		Assert.assertEquals(delete, "true","删除公告实际返回结果："+res);
	}
	
	String title_part ="Appreciation of famous phrases"+CommonData.getStringRandom(5);
	String part_id = "";
	@Test(description = "",priority = 6)
	public void testAddAnnouncementsToPart() {
		String res = AnnouncementsBusiness.addAnnouncements(title_part, "OPEN", des,"part",user_id);
		String insert = (String)JSONPath.read(res, "$.insert");
		Assert.assertEquals(insert, "true","新增公告实际返回结果："+res);
	}
	@Test(description = "新增部分可见的公告后，在列表页查看",priority = 7)
	public void testQueryAnnouncementsPartList() {
		String res = AnnouncementsBusiness.queryAnnouncementsList(title_part);
		String is_all = (String)JSONPath.read(res, "$.list[0].is_all");
		part_id = (String)JSONPath.read(res, "$.list[0].id");
		Assert.assertEquals(is_all, "part","新增部分可见的公告后，在列表页查看,实际返回结果:"+res);
	}
	
	@Test(description = "将公告编辑为全员可见",priority = 8)
	public void testEditAnnouncementsForPart() {
		String res = AnnouncementsBusiness.editAnnouncements(part_id, title_part, "OPEN", des, part_id, "all","");
		String update = (String)JSONPath.read(res, "$.update");
		Assert.assertEquals(update, "true","编辑公告实际返回结果："+res);
	}
	
	@Test(description = "将公告编辑为全员可见，在列表页查看",priority = 9)
	public void testQueryAnnouncementsListEditPart() {
		String res = AnnouncementsBusiness.queryAnnouncementsList(title_part);
		String is_all = (String)JSONPath.read(res, "$.list[0].is_all");
		Assert.assertEquals(is_all, "all","将公告编辑为全员可见，在列表页查看,实际返回结果:"+res);
	}
	
	@Test(description = "查看移动端公告列表",priority = 10)
	public void testQueryAppList() {
		String res = AnnouncementsBusiness.queryAppList();
		String title = (String)JSONPath.read(res, "$.pageInfo.list[0].title");
		Long create_time = (Long)JSONPath.read(res, "$.pageInfo.list[0].create_time");
		Long update_time = (Long)JSONPath.read(res, "$.pageInfo.list[0].update_time");
		String app_id = (String)JSONPath.read(res, "$.pageInfo.list[0].id");
		String is_all = (String)JSONPath.read(res, "$.pageInfo.list[0].is_all");
		Assert.assertNotNull(is_all,"查看移动端公告列表,is_all不能为空：实际返回结果"+res);
		Assert.assertNotNull(app_id,"查看移动端公告列表,id不能为空：实际返回结果"+res);
		Assert.assertNotNull(create_time,"查看移动端公告列表,创建时间不能为空：实际返回结果"+res);
		Assert.assertNotNull(update_time,"查看移动端公告列表,更新时间不能为空：实际返回结果"+res);
		Assert.assertNotNull(title,"查看移动端公告列表,名称不能为空：实际返回结果"+res);
	}
	@Test(description = "删除编辑后为全员的的公告" ,priority = 11)
	public void testDeleteAllAnnouncement() {
		String res = AnnouncementsBusiness.deleteAnnouncements(part_id);
		String delete = (String)JSONPath.read(res, "$.delete");
		Assert.assertEquals(delete, "true","删除公告实际返回结果："+res);
	}
	
}
