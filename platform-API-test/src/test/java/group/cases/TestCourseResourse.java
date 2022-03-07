package group.cases;
/**
 * @author wenlingzhi
 *2021年5月7日
 */

import cn.kxy.group.a.business.CourseResourseBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCourseResourse {	
	public static String first_resourse_id = "";
	public static String second_resourse_id = "";
	public static String doc_resourse_id = "";
	public static String video_resourse_id = "";
	public static String rar_resourse_id = "";
	public static String path_video_resourse_id = "";
	public static String path_doc_resourse_id = "";
	public static String path_rar_resourse_id = "";


	String first_name = "一级文件夹" + CommonData.getStringRandom(5);
	String second_name = "二级文件夹" + CommonData.getStringRandom(5);
	
 	@Test(description="1.创建文件夹接口", priority=1)
 	public void testResourceClassifyAdd()  {
 		String res = CourseResourseBusiness.ResourceClassifyAdd(first_name,"0");
 		System.out.println("1.创建文件夹接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"文件夹新增成功","1.创建文件夹接口"+res);
 	}
 	
 	@Test(description="2.管理后台文件夹列表接口", priority=2)
 	public void testResourceList()  {
 		String res = CourseResourseBusiness.ResourceList(first_name,"0","0");
 		System.out.println("2.管理后台文件夹列表接口:");
 		first_resourse_id = (String)JSONPath.read(res, "$.list[0].resource.id");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("first_resourse_id="+first_resourse_id+","+"total="+total);
		Assert.assertNotEquals(total,0,"2.管理后台文件夹列表接口"+res);
 	}
 	
 	@Test(description="3.创建二级文件夹接口", priority=3)
 	public void testResourceClassifyAddSecond()  {
 		String res = CourseResourseBusiness.ResourceClassifyAdd(second_name,first_resourse_id);
 		System.out.println("3.创建二级文件夹接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"文件夹新增成功","3.创建二级文件夹接口"+res);
 	}
 	
 	@Test(description="4.上传文件-文档类型接口", priority=4)
 	public void testResourceFileAdd()  {
 		String id = CommonData.getStringRandom(16);
 		String name = "testDoc"+".doc";
 		String binaryFile = "%7B%22id%22:%2208d6gywpbjbe8pdm%22%7D";
 		String res = CourseResourseBusiness.ResourceFileAdd(id,name,"100000","doc","","true",binaryFile,"doc",first_resourse_id);
 		System.out.println("4.上传文件-文档类型接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"上传成功","4.上传文件-文档类型接口"+res);
 	}
 	
 	@Test(description="5.上传文件-音视频类型接口", priority=5)
 	public void testResourceFileAddVideo()  {
 		String id = CommonData.getStringRandom(16);
 		String name = "testVideo"+".mp4";
 		String binaryFile = "%7B%22id%22:%2208d6gywpbjbe8pdm%22%7D";
 		String videoId = "bf3c2e4fe8e543b2aa31fd22450ffb80";
 		String res = CourseResourseBusiness.ResourceFileAdd(id,name,"100000","video",videoId,"false",binaryFile,"video",first_resourse_id);
 		System.out.println("5.上传文件-音视频类型接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"上传成功","5.上传文件-音视频类型接口"+res);
 	}
 	
 	
 	@Test(description="6.上传文件-压缩包类型接口", priority=6)
 	public void testResourceFileAddRar()  {
 		String id = CommonData.getStringRandom(16);
 		String name = "testRar"+".rar";
 		String binaryFile = "%7B%22id%22:%2208d6gywpbjbe8pdm%22%7D";
 		String res = CourseResourseBusiness.ResourceFileAdd(id,name,"100000","zip","","false",binaryFile,"zip",first_resourse_id);
 		System.out.println("6.上传文件-压缩包类型接口:");
 		String msg = (String)JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"上传成功","6.上传文件-压缩包类型接口"+res);
 	}
 	
 	@Test(description="7.学习平台素材库-一级文件列表接口", priority=7)
 	public void testResourceListFirst()  {
 		String res = CourseResourseBusiness.ResourceList(first_name,"0","0");
 		System.out.println("7.学习平台素材库-一级文件列表接口:");
 		String download = (String)JSONPath.read(res, "$.list[0].resource.download");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
		Assert.assertNotEquals(total,0,"7.学习平台素材库-一级文件列表接口"+res);
		Assert.assertEquals(download,null,"7.学习平台素材库-一级文件列表接口"+res);
 	}
 	
 	@Test(description="8.学习平台素材库-返回到公共区接口", priority=8)
 	public void testResourceListCommon()  {
 		String res = CourseResourseBusiness.ResourceList("","0","0");
 		System.out.println("8.学习平台素材库-返回到公共区接口:");
 		String download = (String)JSONPath.read(res, "$.list[0].resource.download");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
		Assert.assertNotEquals(total,0,"8.学习平台素材库-返回到公共区接口"+res);
		Assert.assertEquals(download,null,"8.学习平台素材库-返回到公共区接口"+res);
 	}
 	
 	
 	@Test(description="9.学习平台素材库-一级文件列表筛选接口", priority=9)
 	public void testResourceListQuery()  {
 		String res = CourseResourseBusiness.ResourceList(first_name,"0","0");
 		System.out.println("9.学习平台素材库-一级文件列表筛选接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
		Assert.assertNotEquals(total,0,"9.学习平台素材库-一级文件列表筛选接口"+res);
 	}
 	
 	@Test(description="10.学习平台素材库-进入二级文件接口", priority=10)
 	public void testResourceListSecond()  {
 		String res = CourseResourseBusiness.ResourceList("",first_resourse_id,first_resourse_id);
 		System.out.println("10.学习平台素材库-进入二级文件接口:");
 		second_resourse_id = (String)JSONPath.read(res, "$.list[0].resource.id");
 		video_resourse_id = (String)JSONPath.read(res, "$.list[1].resource.id");
 		rar_resourse_id = (String)JSONPath.read(res, "$.list[2].resource.id");
 		doc_resourse_id = (String)JSONPath.read(res, "$.list[3].resource.id");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total+","+"second_resourse_id="+second_resourse_id+","+"doc_resourse_id="+doc_resourse_id
 				+","+"video_resourse_id="+video_resourse_id+","+"rar_resourse_id="+rar_resourse_id);
		Assert.assertNotEquals(total,0,"9.学习平台素材库-二级文件列表接口"+res);
		Assert.assertNotEquals(second_resourse_id,null,"10.学习平台素材库-进入二级文件接口"+res);
		Assert.assertNotEquals(video_resourse_id,null,"10.学习平台素材库-进入二级文件接口"+res);
		Assert.assertNotEquals(rar_resourse_id,null,"10.学习平台素材库-进入二级文件接口"+res);
		Assert.assertNotEquals(doc_resourse_id,null,"10.学习平台素材库-进入二级文件接口"+res);
 	}
 	
 	
 	@Test(description="11.学习平台素材库-二级文件列表筛选接口", priority=11)
 	public void testResourceListQuerySecond()  {
 		String res = CourseResourseBusiness.ResourceList(second_name,"0","0");
 		System.out.println("11.学习平台素材库-二级文件列表筛选接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
		Assert.assertNotEquals(total,0,"11.学习平台素材库-二级文件列表筛选接口"+res);
 	}
 	
 	
 	@Test(description="12.学习平台素材库-校验课件预览接口", priority=12)
 	public void testResourceListCheckScan()  {
 		String res = CourseResourseBusiness.ResourceList("",first_resourse_id,first_resourse_id);
 		System.out.println("12.学习平台素材库-校验课件预览接口:");
 		path_video_resourse_id = (String)JSONPath.read(res, "$.list[1].resource.path");
 		path_doc_resourse_id = (String)JSONPath.read(res, "$.list[2].resource.path");
 		path_rar_resourse_id = (String)JSONPath.read(res, "$.list[3].resource.path");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total+","+"path_video_resourse_id="+path_video_resourse_id+","+"path_doc_resourse_id="+path_doc_resourse_id
 				+","+"path_rar_resourse_id="+path_rar_resourse_id);
		Assert.assertNotEquals(total,0,"12.学习平台素材库-校验课件预览接口"+res);
		Assert.assertNotEquals(path_video_resourse_id,null,"12.学习平台素材库-校验课件预览接口"+res);
		Assert.assertNotEquals(path_doc_resourse_id,null,"12.学习平台素材库-校验课件预览接口"+res);
		Assert.assertNotEquals(path_rar_resourse_id,null,"12.学习平台素材库-校验课件预览接口"+res);
 	}
 	
 	
 	@Test(description="13.学习平台素材库-二级文件列表校验课件下载接口", priority=13)
 	public void testResourceListDownload()  {
 		String res = CourseResourseBusiness.ResourceList("",first_resourse_id,first_resourse_id);
 		System.out.println("13.学习平台素材库-二级文件列表校验课件下载接口:");
 		String downloadFirst = (String)JSONPath.read(res, "$.list[1].resource.download");
 		String downloadSecond = (String)JSONPath.read(res, "$.list[2].resource.download");
 		String downloadThird = (String)JSONPath.read(res, "$.list[3].resource.download");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total+","+"second_resourse_id="+second_resourse_id+","+"doc_resourse_id="+doc_resourse_id
 				+","+"video_resourse_id="+video_resourse_id+","+"rar_resourse_id="+rar_resourse_id);
		Assert.assertNotEquals(total,0,"13.学习平台素材库-二级文件列表校验课件下载接口"+res);
		Assert.assertNotEquals(downloadFirst,null,"13.学习平台素材库-二级文件列表校验课件下载接口"+res);
		Assert.assertNotEquals(downloadSecond,null,"13.学习平台素材库-二级文件列表校验课件下载接口"+res);
		Assert.assertNotEquals(downloadThird,null,"13.学习平台素材库-二级文件列表校验课件下载接口"+res);
 	}
 	
 	@Test(description="15.删除课件接口", priority=15)
 	public void testDeleteResourse()  {		
 		String resDoc = CourseResourseBusiness.DeleteResourse(doc_resourse_id);
 		String resVideo = CourseResourseBusiness.DeleteResourse(video_resourse_id);
 		String resRar = CourseResourseBusiness.DeleteResourse(rar_resourse_id);
 		System.out.println("15.删除课件接口:");
 		String msg = (String)JSONPath.read(resDoc, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"删除课件成功","15.删除课件接口"+resDoc);
 	}
 	
 	
 	@Test(description="16.删除文件夹接口", priority=16)
 	public void testDeleteClassifyResourse()  {	
 		System.out.println("second_resourse_id="+second_resourse_id+","+"first_resourse_id="+first_resourse_id);
 		String resSecond = CourseResourseBusiness.DeleteClassifyResourse(second_resourse_id);
 		String resFirst = CourseResourseBusiness.DeleteClassifyResourse(first_resourse_id);
 		System.out.println("16.删除文件夹接口:");
 		String msg = (String)JSONPath.read(resFirst, "$.msg");
 		System.out.println("msg="+msg);
		Assert.assertEquals(msg,"文件夹删除成功","16.删除文件夹接口"+resFirst);
 	}
 	
 	@Test(description="17.删除课件和文件夹后校验学习平台素材库是否还存在该数据接口", priority=17)
 	public void testResourceListCheck()  {
 		String res = CourseResourseBusiness.ResourceList(first_name,"0","0");
 		System.out.println("17.删除课件和文件夹后校验学习平台素材库是否还存在该数据接口:");
 		Integer total = (Integer)JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
		Assert.assertTrue(total<1,"17.删除课件和文件夹后校验学习平台素材库是否还存在该数据接口"+res);
 	}

}
