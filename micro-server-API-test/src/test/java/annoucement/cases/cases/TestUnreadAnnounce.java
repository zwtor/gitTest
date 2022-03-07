package annoucement.cases.cases;

import cn.kxy.announcements.business.AnnouncementsBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestUnreadAnnounce {
	String title ="AAFamous phrases"+CommonData.getStringRandom(5);
	String des = "Time goes by so fast, people go in and out of your life. "
			+ "You must never miss the opportunity to tell these people how much they mean to you";
	String id = "";
	@Test(description="新增公告",priority=1)
	public void testAddAnnouncements() {
		String res = AnnouncementsBusiness.addAnnouncements(title, "OPEN", des,"all","");
		String insert = (String)JSONPath.read(res, "$.insert");
		Assert.assertEquals(insert, "true","新增公告实际返回结果："+res);
	}
	
	@Test(description = "公告未读时，在app端显示小红点",priority = 2)
	public void testQueryUnReadAppList() {
		String res = AnnouncementsBusiness.queryAppList();
		id=(String)JSONPath.read(res, "$.pageInfo.list[0].id");
		String read_status = (String)JSONPath.read(res, "$.pageInfo.list[0].read_status");
		Assert.assertEquals(read_status, "UNREAD","新增公告实际返回结果："+res);
	}
	@Test(description="查询公告详情",priority=3)
	public void testQuetyInfo() {
		String res = AnnouncementsBusiness.queryInfo(id, "app");
		String name = (String)JSONPath.read(res, "$.title");
		Assert.assertEquals(name, title,"查询公告详情实际返回结果："+res);
	}
	@Test(description = "公告已读时，在app端不显示小红点",priority = 4)
	public void testQueryReadAppList() {
		String res = AnnouncementsBusiness.queryAppList();
		id=(String)JSONPath.read(res, "$.list[0].id");
		String read_status = (String)JSONPath.read(res, "$.pageInfo.list[0].read_status");
		Assert.assertEquals(read_status, "READ","新增公告实际返回结果："+res);
	}
	@Test(description = "公告为已读状态时，删除公告" ,priority = 5)
	public void testDeleteAnnouncement() {
		String res_list = AnnouncementsBusiness.queryAnnouncementsList(title);
		String del_id = (String)JSONPath.read(res_list, "$.list[0].id");
		String res = AnnouncementsBusiness.deleteAnnouncements(del_id);
		String delete = (String)JSONPath.read(res, "$.delete");
		Assert.assertEquals(delete, "true","删除公告实际返回结果："+res);
	}
}
