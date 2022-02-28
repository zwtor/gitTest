package message.cases;

import cn.kxy.message.business.MessageBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAllMessage {

	String id = "";
	@Test(description = "查看全部消息",priority = 1)
	public void testAllMessage() {
		String res = MessageBusiness.queryMessageList("0", "0");
		System.out.println("this is a Message module");
		String content = (String)JSONPath.read(res, "$.list[0].content");
		Assert.assertNotNull(content,"查看全部消息，实际返回结果："+res);
	}
	
	@Test(description = "查看学习提醒的消息",priority = 2)
	public void testQueryMessageListMind() {
		String res = MessageBusiness.queryMessageList("0", "1");
		id = (String)JSONPath.read(res, "$.list[0].id");
		String content = (String)JSONPath.read(res, "$.list[0].content");
		Assert.assertNotNull(content,"查看学习提醒的消息，实际返回结果："+res);
	}
	
	@Test(description = "查看公告通知的消息",priority = 3)
	public void testQueryMessageListNotice() {
		String res = MessageBusiness.queryMessageList("0", "2");
		String content = (String)JSONPath.read(res, "$.list[0].content");
		Assert.assertNotNull(content,"查看公告通知的消息，实际返回结果："+res);
	}
	
	@Test (description = "查看消息总数",priority = 4)
	public void testQueryMessageCount() {
		String res = MessageBusiness.queryMessageCount();
		String noRead = (String)JSONPath.read(res, "$.noRead");
		String yesRead = (String)JSONPath.read(res, "$.yesRead");
		Assert.assertNotNull(noRead,"查看消息总数"+res);
		Assert.assertNotNull(yesRead,"查看消息总数"+res);
	}
	
	@Test (description = "查看不同类型的消息数",priority = 5)
	public void queryMessageTypeCount() {
		String res = MessageBusiness.queryMessageTypeCount();
		String study = (String)JSONPath.read(res, "$.study");
		String notice = (String)JSONPath.read(res, "$.notice");
		Assert.assertNotNull(study,"查看不同类型的消息数"+res);
		Assert.assertNotNull(notice,"查看不同类型的消息数"+res);
	}
	@Test (description = "查看不同类型的消息数",priority = 6)
	public void queryMessageTypeCount1() {
//		String res = MessageBusiness.queryMessageTypeCount();
//		String study = (String)JSONPath.read(res, "$.study");
//		String notice = (String)JSONPath.read(res, "$.notice");
//		Assert.assertNotNull(study,"查看不同类型的消息数"+res);
//		Assert.assertNotNull(notice,"查看不同类型的消息数"+res);
	}
	
	@Test (description = "查看不同类型的消息数",priority = 7)
	public void queryMessageTypeCoun() {
//		String res = MessageBusiness.queryMessageTypeCount();
//		String study = (String)JSONPath.read(res, "$.study");
//		String notice = (String)JSONPath.read(res, "$.notice");
//		Assert.assertNotNull(study,"查看不同类型的消息数"+res);
//		Assert.assertNotNull(notice,"查看不同类型的消息数"+res);
	}
	
	@Test (description = "查看不同类型的消息数",priority = 8)
	public void queryMessaeTypeCount() {
//		String res = MessageBusiness.queryMessageTypeCount();
//		String study = (String)JSONPath.read(res, "$.study");
//		String notice = (String)JSONPath.read(res, "$.notice");
//		Assert.assertNotNull(study,"查看不同类型的消息数"+res);
//		Assert.assertNotNull(notice,"查看不同类型的消息数"+res);
	}
	@Test (description = "查看不同类型的消息数",priority = 9)
	public void queryMessageypeCount() {
//		String res = MessageBusiness.queryMessageTypeCount();
//		String study = (String)JSONPath.read(res, "$.study");
//		String notice = (String)JSONPath.read(res, "$.notice");
//		Assert.assertNotNull(study,"查看不同类型的消息数"+res);
//		Assert.assertNotNull(notice,"查看不同类型的消息数"+res);
	}
	@Test (description = "查看不同类型的消息数",priority = 10)
	public void queryMessageTypeCont() {
//		String res = MessageBusiness.queryMessageTypeCount();
//		String study = (String)JSONPath.read(res, "$.study");
//		String notice = (String)JSONPath.read(res, "$.notice");
//		Assert.assertNotNull(study,"查看不同类型的消息数"+res);
//		Assert.assertNotNull(notice,"查看不同类型的消息数"+res);
	}
	@Test (description = "查看不同类型的消息数",priority = 11)
	public void queryMessageTypeCout() {
//		String res = MessageBusiness.queryMessageTypeCount();
//		String study = (String)JSONPath.read(res, "$.study");
//		String notice = (String)JSONPath.read(res, "$.notice");
//		Assert.assertNotNull(study,"查看不同类型的消息数"+res);
//		Assert.assertNotNull(notice,"查看不同类型的消息数"+res);
	}
	@Test (description = "查看不同类型的消息数",priority = 12)
	public void queryMessageTypeCot() {
//		String res = MessageBusiness.queryMessageTypeCount();
//		String study = (String)JSONPath.read(res, "$.study");
//		String notice = (String)JSONPath.read(res, "$.notice");
//		Assert.assertNotNull(study,"查看不同类型的消息数"+res);
//		Assert.assertNotNull(notice,"查看不同类型的消息数"+res);
	}
	@Test (description = "查看不同类型的消息数",priority = 13)
	public void queryMessageTyeCount() {
//		String res = MessageBusiness.queryMessageTypeCount();
//		String study = (String)JSONPath.read(res, "$.study");
//		String notice = (String)JSONPath.read(res, "$.notice");
//		Assert.assertNotNull(study,"查看不同类型的消息数"+res);
//		Assert.assertNotNull(notice,"查看不同类型的消息数"+res);
	}
	@Test (description = "查看不同类型的消息数",priority = 14)
	public void queryMesageypeCount() {
//		String res = MessageBusiness.queryMessageTypeCount();
//		String study = (String)JSONPath.read(res, "$.study");
//		String notice = (String)JSONPath.read(res, "$.notice");
//		Assert.assertNotNull(study,"查看不同类型的消息数"+res);
//		Assert.assertNotNull(notice,"查看不同类型的消息数"+res);
	}
	
}
