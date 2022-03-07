package course.cases;

public class TestChangeCourse {
	/*String doc_path = "document/";
	String mp3_path = "mp3/";
	String video_path = "video/";
	@Test(description = "课件管理上传txt文件",priority = 1)
	public void testUploadTxt() {
		String res = UploadCourseware.uploadFile(doc_path + "regulartxt.txt", "doc", "false","0");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String msg = (String)JSONPath.read(res, "$.msg");
		String size = (String)JSONPath.read(res, "$.data.size");
		String type = (String)JSONPath.read(res, "$.data.type");
 		Assert.assertEquals(type, "txt","上传txt文件,文件类型校验，实际返回结果:"+res);
		Assert.assertNotNull(size,"上传txt文件，文件大小验证"+res);
 		Assert.assertEquals(msg, "上传成功","上传txt文件,msg信息校验，实际返回结果:"+res);
	}
	String id = "";
	@Test(description = "查询上传文档后的列表信息",priority = 2)
	public void testQueryDocumentList() {
		String res= CoursewareBusiness.queryList("regulartxt");
		id= (String)JSONPath.read(res, "$.list[0].resource.id");
	}
	*/
	
}
