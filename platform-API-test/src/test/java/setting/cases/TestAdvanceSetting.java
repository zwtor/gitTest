package setting.cases;

import cn.kxy.setting.bussiness.AdvanceSettingBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.annotations.Test;

public class TestAdvanceSetting {
	
	@Test(description="获取高级设置信息",priority=1)
	public void testSetDynamicWatermark() {
		String res = AdvanceSettingBusiness.getAdvanceSetting();
		String water_title = (String)JSONPath.read(res, "$.list[0].title");
		String prevent_cut_screen_title = (String)JSONPath.read(res, "$.list[1].title");
		String task_reminder_title = (String)JSONPath.read(res, "$.list[3].title");
		String audio_video_drag_title = (String)JSONPath.read(res, "$.list[4].title");
		String mobile_credit_ranking_title = (String)JSONPath.read(res, "$.list[5].title");
		String document_player_title = (String)JSONPath.read(res, "$.list[6].title");
		String document_duration_title = (String)JSONPath.read(res, "$.list[7].title");
		String mobile_exam_ranking_title = (String)JSONPath.read(res, "$.list[8].title");
		String course_sharing_title = (String)JSONPath.read(res, "$.list[10].title");
		String course_approval_title = (String)JSONPath.read(res, "$.list[11].title");
		String resource_classification = (String)JSONPath.read(res, "$.list[12].title");
		
		String signs_pop = (String)JSONPath.read(res, "$.list[13].title");
		String show_feed_title = (String)JSONPath.read(res, "$.list[14].title");
		String jurisdiction_setting_title = (String)JSONPath.read(res, "$.list[15].title");
		String mobile_ding_disk_choose_title = (String)JSONPath.read(res, "$.list[16].title");
		
		String print_paper_title = (String)JSONPath.read(res, "$.list[17].title");
		String create_course = (String)JSONPath.read(res, "$.list[18].title");
		String quota_receiver_title = (String)JSONPath.read(res, "$.list[19].title");
		String user_view_title = (String)JSONPath.read(res, "$.list[20].title");
//		Assert.assertEquals(print_paper_title, "打印试卷","获取高级设置信息,文档学习时长标题进行校验"+res);
//		Assert.assertEquals(create_course, "学员上传课程","获取高级设置信息,文档学习时长标题进行校验"+res);
//		Assert.assertEquals(signs_pop, "签到弹窗设置","获取高级设置信息,文档学习时长标题进行校验"+res);
//		Assert.assertEquals(document_duration_title, "文档学习时长","获取高级设置信息,文档学习时长标题进行校验"+res);
//		Assert.assertEquals(mobile_exam_ranking_title, "移动端积分排行榜","获取高级设置信息,移动端考试排行榜标题进行校验"+res);
//		Assert.assertEquals(water_title, "动态水印","获取高级设置信息,动态水印标题进行校验"+res);
//		Assert.assertEquals(prevent_cut_screen_title, "音视频防挂机","获取高级设置信息,音视频防挂机标题进行校验"+res);
//		Assert.assertEquals(task_reminder_title, "任务提醒","获取高级设置信息,任务提醒标题进行校验"+res);
//		Assert.assertEquals(audio_video_drag_title, "音视频拖拽","获取高级设置信息,音视频拖拽标题进行校验"+res);
//		Assert.assertEquals(mobile_credit_ranking_title, "移动端学分排行榜","获取高级设置信息,移动端学分排行榜标题进行校验"+res);
//		Assert.assertEquals(document_player_title, "文档播放器","获取高级设置信息,文档播放器标题进行校验"+res);
//		Assert.assertEquals(course_sharing_title, "企业课程分享","获取高级设置信息,企业课程分享标题进行校验"+res);
//		Assert.assertEquals(course_approval_title, "课程上传审核","获取高级设置信息,课程上传审核标题进行校验"+res);
//		Assert.assertEquals(show_feed_title, "移动端学习动态","获取高级设置信息,移动端学习动态标题进行校验"+res);
//		Assert.assertEquals(resource_classification, "资源分类显示设置","获取高级设置信息,资源分类显示设置标题进行校验"+res);
//		Assert.assertEquals(jurisdiction_setting_title, "管辖范围设置","获取高级设置信息,资源分类显示设置标题进行校验"+res);
//		Assert.assertEquals(quota_receiver_title, "消息接收人配置","获取高级设置信息,消息接收人配置标题进行校验"+res);
//		Assert.assertEquals(mobile_ding_disk_choose_title, "移动端钉盘选取","获取高级设置信息,消息接收人配置标题进行校验"+res);
//		Assert.assertEquals(user_view_title, "员工信息自定义字段设置","获取高级设置信息,消息接收人配置标题进行校验"+res);
	}
	
	@Test(description="查看动态水印设置",priority=2)
	public void testQueryDynamicWatermark() {
		String res = AdvanceSettingBusiness.queryDynamicWatermark();
		String type = (String)JSONPath.read(res, "$.type");
//		Assert.assertEquals(type, "dynamic_watermark","查看动态水印设置，实际返回结果;"+res);
	}
	@Test(description="同步钉钉员工信息",priority=3)
	public void testSynchronizationInfo() {
		String res = AdvanceSettingBusiness.synchronizationInfo();
		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "同步中...","同步钉钉员工信息,实际返回结果："+res);
	}
	String resp = "";
	@Test(description = "任务指派及变更字段校验",priority = 4)
	public void testTaskChange() {
		 resp = AdvanceSettingBusiness.getAdvanceSetting();
		 String dynamic_name = (String)JSONPath.read(resp, "$.list[3].action_list[0].dynamic_name");
//		 Assert.assertEquals(dynamic_name, "任务指派及变更","任务指派及变更字段校验"+resp);
	}

	@Test(description = "无提醒字段校验",priority = 5)
	public void testNoRemind() {
		 String dynamic_name = (String)JSONPath.read(resp, "$.list[3].action_list[1].dynamic_name");
//		 Assert.assertEquals(dynamic_name, "无提醒","无提醒字段校验字段校验"+resp);
	}
	@Test(description = "每日提醒字段校验",priority = 6)
	public void testEveryDayRemind() {
		 String dynamic_name = (String)JSONPath.read(resp, "$.list[3].action_list[2].dynamic_name");
//		 Assert.assertEquals(dynamic_name, "每日定时提醒","每日定时提醒字段校验"+resp);
	}
	
	@Test(description = "每周提醒字段校验",priority = 7)
	public void testEveryWeekRemind() {
		 String dynamic_name = (String)JSONPath.read(resp, "$.list[3].action_list[3].dynamic_name");
//		 Assert.assertEquals(dynamic_name, "每周提醒","每周提醒字段校验"+resp);
	}
	
	@Test(description = "钉钉代办字段校验",priority = 8)
	public void testDingWaitRemind() {
		 String dynamic_name = (String)JSONPath.read(resp, "$.list[3].action_list[4].dynamic_name");
//		 Assert.assertEquals(dynamic_name, "钉钉待办","钉钉待办字段校验"+resp);
	}
}
