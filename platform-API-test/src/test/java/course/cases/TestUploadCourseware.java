package course.cases;

import cn.kxy.course.resources.bussiness.CoursewareBusiness;
import cn.kxy.course.resources.bussiness.UploadCourseware;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestUploadCourseware {

	String doc_path = "document/";
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
	String txt_id = "";
	@Test(description = "查询上传文档后的列表信息",priority = 2)
	public void testQueryDocumentList() {
		String res= CoursewareBusiness.queryList("regulartxt");
		txt_id= (String)JSONPath.read(res, "$.list[0].resource.id");
		String title = (String)JSONPath.read(res, "$.list[0].resource.name");
		String type = (String)JSONPath.read(res, "$.list[0].resource.type");
		String size = (String)JSONPath.read(res, "$.list[0].resource.size");
		String creatorName = (String)JSONPath.read(res, "$.list[0].resource.creatorName");
		Long createTime = (Long)JSONPath.read(res, "$.list[0].resource.createTime");
		
		Assert.assertNotNull(size,"查询上传文档后的列表信息，文件大小验证"+res);
		Assert.assertNotNull(createTime,"查询上传文档后的列表信息，创建时间验证"+res);
		Assert.assertEquals(title, "regulartxt","查询上传文档后的列表信息,title信息校验，实际返回结果:"+res);
		Assert.assertEquals(type, "txt","查询上传文档后的列表信息,txt信息校验，实际返回结果:"+res);
		Assert.assertNotNull(creatorName,"查询上传文档后的列表信息，文件大小验证"+res);
	}
	
	@Test(description = "删除上传的文档", priority = 3)
	public void testDeleteDocument() {
		String del_res = CoursewareBusiness.deleteDocument(txt_id);
		String msg = (String)JSONPath.read(del_res, "$.msg"); 
		Assert.assertEquals(msg,"删除课件成功","删除上传的文档，实际返回结果："+del_res);
	}
	
	@Test(description = "课件管理上传doc文件",priority = 4)
	public void testUploadDoc() {
		UploadCourseware.uploadFile(doc_path + "regulardoc.doc", "doc", "true","0");
	}
	String doc_id = "";
	@Test(description = "",priority = 5)
	public void getIdByregulardoc() {
		String res= CoursewareBusiness.queryList("regulardoc");
		doc_id= (String)JSONPath.read(res, "$.list[0].resource.id");
	}
	
	@Test(description = "删除doc文档",priority = 6)
	public void deleteDOcDocument() {
		String del_res = CoursewareBusiness.deleteDocument(doc_id);
		String msg = (String)JSONPath.read(del_res, "$.msg"); 
		Assert.assertEquals(msg,"删除课件成功","删除上传的doc文件，实际返回结果："+del_res);
	}
	
	@Test(description = "课件管理上传pdf文件",priority = 7)
	public void testUploadPdf() {
		UploadCourseware.uploadFile(doc_path + "regularpdf.pdf", "doc", "true","0");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	String pdf_id = "";
	@Test(description = "获取pdf id",priority = 8)
	public void getPdfId() {
		String res= CoursewareBusiness.queryList("regularpdf");
		pdf_id= (String)JSONPath.read(res, "$.list[0].resource.id");
	}
	
	@Test(description = "删除pdf文档",priority = 9)
	public void deletePdfDocument() {
		String del_res = CoursewareBusiness.deleteDocument(pdf_id);
		String msg = (String)JSONPath.read(del_res, "$.msg"); 
		Assert.assertEquals(msg,"删除课件成功","删除上传的pdf文件，实际返回结果："+del_res);
	}
	
	@Test(description = "课件管理上传ppt文件",priority = 10)
	public void testUploadPpt() {
		UploadCourseware.uploadFile(doc_path + "regularppt.ppt", "doc", "true","0");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	String ppt_id = "";
	@Test(description = "获取pptId",priority =11)
	public void getPPtId() {
		String res= CoursewareBusiness.queryList("regularppt");
		ppt_id= (String)JSONPath.read(res, "$.list[0].resource.id");
		
	}
	@Test(description = "删除ppt文档",priority = 12)
	public void deletePptDocument() {
		String del_res = CoursewareBusiness.deleteDocument(ppt_id);
		String msg = (String)JSONPath.read(del_res, "$.msg"); 
		Assert.assertEquals(msg,"删除课件成功","删除上传的ppt文件，实际返回结果："+del_res);
	}
	
	@Test(description = "课件管理上传pptx文件",priority = 13)
	public void testUploadpptx() {
		UploadCourseware.uploadFile(doc_path + "regularpptx.pptx", "doc", "true","0");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	String pptx_id = "获取pptx的id";
	@Test(description = "获取pptx  id",priority = 14)
	public void getPptxId() {
		String res= CoursewareBusiness.queryList("regularpptx");
		pptx_id= (String)JSONPath.read(res, "$.list[0].resource.id");
	}
	
	@Test(description = "删除pptx文档",priority = 15)
	public void deletePPTXDocument() {
		String del_res = CoursewareBusiness.deleteDocument(pptx_id);
		String msg = (String)JSONPath.read(del_res, "$.msg"); 
		Assert.assertEquals(msg,"删除课件成功","删除上传的docx文件，实际返回结果："+del_res);
	}
	
	@Test(description = "课件管理上传xlsx文件",priority = 16)
	public void testUploadxlsx() {
		UploadCourseware.uploadFile(doc_path + "regularxlsx.xlsx", "doc", "true","0");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	String xlsx_id = "";
	@Test(description = "获取xlsx的id",priority = 17)
	public void getXlsxId() {
		String res= CoursewareBusiness.queryList("regularxlsx");
		xlsx_id= (String)JSONPath.read(res, "$.list[0].resource.id");
	}
	
	@Test(description = "删除xlsx文档",priority =18)
	public void deleteXlsxDocument() {
		String del_res = CoursewareBusiness.deleteDocument(xlsx_id);
		String msg = (String)JSONPath.read(del_res, "$.msg"); 
		Assert.assertEquals(msg,"删除课件成功","删除上传的xlsx文件，实际返回结果："+del_res);
	}
	
	@Test(description = "课件管理上传xls文件",priority = 19)
	public void testUploadxls() {
		UploadCourseware.uploadFile(doc_path + "regularxls.xls", "doc", "true","0");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	String xls_id = "";
	@Test(description = "",priority = 20)
	public void getXlsId() {
		String res= CoursewareBusiness.queryList("regularxls");
		xls_id= (String)JSONPath.read(res, "$.list[0].resource.id");
	}
	
	@Test(description = "删除xls文档",priority = 21)
	public void deleteXlsDocument() {
		String del_res = CoursewareBusiness.deleteDocument(xls_id);
		String msg = (String)JSONPath.read(del_res, "$.msg"); 
		Assert.assertEquals(msg,"删除课件成功","删除上传的xls文件，实际返回结果："+del_res);
	}
	
	@Test(description = "课件管理上传MP3文件",priority = 22)
	public void testUploadmp3() {
		UploadCourseware.uploadFile(mp3_path + "regularmp3.mp3", "audio/mp3", "false","0");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	String mp3_id = "";
	@Test(description = "获取mp3的id",priority =23)
	public void getMp3Id() {
		String res= CoursewareBusiness.queryList("regularmp3");
		mp3_id= (String)JSONPath.read(res, "$.list[0].resource.id");
	}
	
	@Test(description = "",priority = 24)
	public void deleteMp3Document() {	
		String del_res = CoursewareBusiness.deleteDocument(mp3_id);
		String msg = (String)JSONPath.read(del_res, "$.msg"); 
		Assert.assertEquals(msg,"删除课件成功","删除上传的mp3文件，实际返回结果："+del_res);
}
	
	@Test(description = "课件管理上传wmv文件",priority = 25)
	public void testUploadwmv() {
		String res = UploadCourseware.uploadFile(video_path + "videowmv.wmv", "video", "false","0");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String msg = (String)JSONPath.read(res, "$.msg");
		String type = (String)JSONPath.read(res, "$.data.type");
 		Assert.assertEquals(type, "mp4","上传wmv文件,文件类型校验，实际返回结果:"+res);
 		Assert.assertEquals(msg, "上传成功","上传wmv文件,msg信息校验，实际返回结果:"+res);
	}
	String wmv_id = "";
	@Test(description = "查询上传wmv文档后的列表信息",priority = 26)
	public void testQueryDocumentwmvList() {
		String res= CoursewareBusiness.queryList("videowmv");
		wmv_id= (String)JSONPath.read(res, "$.list[0].resource.id");
		String size = (String)JSONPath.read(res, "$.list[0].resource.size");
		
		Assert.assertNotNull(size,"查询上传文档后的列表信息，文件大小验证"+res);
	}
	@Test(description = "删除上传的wmv文档", priority = 27)
	public void testDeleteDocumentwmv() {
		String del_res = CoursewareBusiness.deleteDocument(wmv_id);
		String msg = (String)JSONPath.read(del_res, "$.msg"); 
		Assert.assertEquals(msg,"删除课件成功","删除上传的mp4文档，实际返回结果："+del_res);
	}
	@Test(description = "课件管理上传mp4文件",priority = 28)
	public void testUploadmp4() {
		String res = UploadCourseware.uploadFile(video_path + "videomp4.mp4", "video", "false","0");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String msg = (String)JSONPath.read(res, "$.msg");
		String type = (String)JSONPath.read(res, "$.data.type");
 		Assert.assertEquals(type, "mp4","上传mp4文件,文件类型校验，实际返回结果:"+res);
 		Assert.assertEquals(msg, "上传成功","上传mp4文件,msg信息校验，实际返回结果:"+res);
	}
	String mp4_id = "";
	@Test(description = "查询上传mp4文档后的列表信息",priority = 29)
	public void testQueryDocumentMp4List() {
		String res= CoursewareBusiness.queryList("videomp4");
		mp4_id= (String)JSONPath.read(res, "$.list[0].resource.id");
		String title = (String)JSONPath.read(res, "$.list[0].resource.name");
		Assert.assertNotNull(title,"查询上传文档后的列表信息,title信息校验，实际返回结果:"+res);
	}
	@Test(description = "删除上传的mp4文档", priority = 30)
	public void testDeleteDocumentMp4() {
		String del_res = CoursewareBusiness.deleteDocument(mp4_id);
		String msg = (String)JSONPath.read(del_res, "$.msg"); 
		Assert.assertEquals(msg,"删除课件成功","删除上传的mp4文档，实际返回结果："+del_res);
	}
	
	@Test(description = "课件管理上传3gp文件",priority = 31)
	public void testUpload3gp() {
		String res = UploadCourseware.uploadFile(video_path + "video3gp.3gp", "video", "false","0");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String msg = (String)JSONPath.read(res, "$.msg");
		String type = (String)JSONPath.read(res, "$.data.type");
 		Assert.assertEquals(type, "mp4","上传3gp文件,文件类型校验，实际返回结果:"+res);
 		Assert.assertEquals(msg, "上传成功","上传3gp文件,msg信息校验，实际返回结果:"+res);
	}
	
	String gp_id = "";
	@Test(description = "查询上传3gp文档后的列表信息",priority = 32)
	public void testQueryDocument3gpList() {
		String res= CoursewareBusiness.queryList("video3gp");
		gp_id= (String)JSONPath.read(res, "$.list[0].resource.id");
		String title = (String)JSONPath.read(res, "$.list[0].resource.name");
		Assert.assertNotNull(title,"查询上传文档后的列表信息,title信息校验，实际返回结果:"+res);
	}
	@Test(description = "下载3gp文档",priority = 33)
	public void testDowloadResource() {
		int code = CoursewareBusiness.dowloadResource(gp_id);
		Assert.assertEquals(code, 200,"下载3gp资源的code实际返回结果："+code);
	}
	
	@Test(description = "删除上传的3gp文档", priority = 34)
	public void testDeleteDocument3gp() {
		String del_res = CoursewareBusiness.deleteDocument(gp_id);
		String msg = (String)JSONPath.read(del_res, "$.msg"); 
		Assert.assertEquals(msg,"删除课件成功","删除上传的mp4文档，实际返回结果："+del_res);
	}
	@Test(description = "课件管理上传avi文件",priority = 35)
	public void testUploadavi() {
		UploadCourseware.uploadFile(video_path + "videoavi.avi", "video", "false","0");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	String avi_id = "" ;
	@Test(description = "获取AVI格式的id",priority = 36)
	public void getAvid() {
		String res= CoursewareBusiness.queryList("videoavi");
		avi_id= (String)JSONPath.read(res, "$.list[0].resource.id");
	}
	
	@Test(description = "删除AVI文档",priority = 37)
	public void deleteAviDocument() {
		String del_res = CoursewareBusiness.deleteDocument(avi_id);
		String msg = (String)JSONPath.read(del_res, "$.msg"); 
		Assert.assertEquals(msg,"删除课件成功","删除上传的avi文件，实际返回结果："+del_res);
	}
	
	@Test(description = "课件管理上传flv文件",priority = 38)
	public void testUploadflv() {
		UploadCourseware.uploadFile(video_path + "videoflv.flv", "video", "false","0");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
	String flv_id = "";
	@Test(description = "获取flvId",priority = 39)
	public void getFlvId() {
		String res= CoursewareBusiness.queryList("videoflv");
		flv_id= (String)JSONPath.read(res, "$.list[0].resource.id");
	}
	
	@Test(description = "删除flv",priority = 40)
	public void deleteFlvDocument() {
		String del_res = CoursewareBusiness.deleteDocument(flv_id);
		String msg = (String)JSONPath.read(del_res, "$.msg"); 
		Assert.assertEquals(msg,"删除课件成功","删除上传的flv文件，实际返回结果："+del_res);
	}
	
	@Test(description = "课件管理上传jpg文件",priority = 41)
	public void testUploadJpg() {
		UploadCourseware.uploadFile(doc_path + "regularjpg.jpg", "picture", "false","0");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	String jpg_id = "";
	@Test(description = "获取jpgId",priority =42)
	public void getId() {
		String res= CoursewareBusiness.queryList("regularjpg");
		jpg_id= (String)JSONPath.read(res, "$.list[0].resource.id");
	}
	 
	@Test(description = "删除jpg文档",priority = 43)
	public void deleteJPGDocument() {
		String del_res = CoursewareBusiness.deleteDocument(jpg_id);
		String msg = (String)JSONPath.read(del_res, "$.msg"); 
		Assert.assertEquals(msg,"删除课件成功","删除上传的jpg文件，实际返回结果："+del_res);
	}
	
	@Test(description = "课件管理上传HTMl文件",priority = 44)
	public void testUploadHtml() {
		
		String uploadZipFile = UploadCourseware.uploadZipFile(doc_path + "html.zip");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(uploadZipFile);
	}
	String html_id = "";
	@Test(description = "获取Html_Id",priority =45)
	public void gethtml_id() {
		String res= CoursewareBusiness.queryList("html");
		html_id= (String)JSONPath.read(res, "$.list[0].resource.id");
	}
	
	@Test(description = "删除html文档",priority = 46)
	public void deleteHtmlDocument() {
		String del_res = CoursewareBusiness.deleteDocument(html_id);
		String msg = (String)JSONPath.read(del_res, "$.msg"); 
		Assert.assertEquals(msg,"删除课件成功","删除上传的html文件，实际返回结果："+del_res);
	}
	
	String scorm_id = "";
	@Test(description = "课件管理上传scorm文件",priority = 47)
	public void testUploadScorm() {
		
//		String uploadZipFile = UploadCourseware.uploadZipFile(doc_path + "scorm.zip");
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		Assert.assertTrue(uploadZipFile.contains("上传成功"));
	}
	@Test(description = "获取scorm_Id",priority =48)
	public void getscorm_id() {
//		String res= CoursewareBusiness.queryList("html");
//		scorm_id= (String)JSONPath.read(res, "$.list[0].resource.id");
	}
	
	@Test(description = "删除scorm文档",priority = 49)
	public void deletescormDocument() {
//		String del_res = CoursewareBusiness.deleteDocument(scorm_id);
//		String msg = (String)JSONPath.read(del_res, "$.msg"); 
//		Assert.assertEquals(msg,"删除课件成功","删除上传的scorm文件，实际返回结果："+del_res);
	}
	
}
