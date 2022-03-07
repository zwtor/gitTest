package course.cases;

import cn.kxy.course.resources.bussiness.CourseBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCoureswareDuration {

	@Test(description = "查看视频的时长",priority = 1)
	public void testGetCourseVideoList() {
		String res = CourseBusiness.getCourseList("0","","video","");
		String druing = (String)JSONPath.read(res, "$.list[0].duration");
		Assert.assertTrue(Integer.valueOf(druing)>0,"查看视频的时长，实际返回结果："+res);
		
	}
	
	@Test(description = "查看音频的时长",priority = 2)
	public void testGetCourseAudioList() {
		String res = CourseBusiness.getCourseList("0","","audio","");
		String druing = (String)JSONPath.read(res, "$.list[0].duration");
		Assert.assertTrue(Integer.valueOf(druing)>0,"查看音频的时长，实际返回结果："+res);
	}
	
}
