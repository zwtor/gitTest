package group.cases;

import cn.kxy.group.a.business.CertificateBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

public class TestCertificate {	
	public static String template_id = "";
	public static String certificate_id = "";
	
	String name = "证书改版" + CommonData.getStringRandom(5);
 	String username = UserBusiness.getUsername();
 	String studyplan_title  = "证书关联在任务中" + CommonData.getStringRandom(5);
 	
 	
 	
 	@Test(description="1.查询证书样式接口", priority=1)
 	public void testCertificateTemplates()  {
 		String res = CertificateBusiness.CertificateTemplates();
 		System.out.println("1.查询证书样式接口:");
 		template_id = (String) JSONPath.read(res, "$.list[0].id");
 		System.out.println("template_id="+template_id);
 		Assert.assertNotEquals(template_id,null,"1.查询证书样式接口：" + res);
 	}

 	
 	@Test(description="2.新增证书接口", priority=2)
 	public void testAddCertificate()  {
 		//String name,String language_type,String image_url,String template_id,String logo_url,String common_seal_url
 		String language_type = "chinese";
 		String image_url = "https://oss.coolcollege.cn/1809008230358192128.png";
 		String logo_url = "https://oss.coolcollege.cn/1814376318658285568.png";
 		String common_seal_url = "https://sdn.coolcollege.cn/assets/kuxueyuan/certificate/seal-zh.png";
 		String res = CertificateBusiness.AddCertificate(name,language_type,image_url,template_id,logo_url,common_seal_url);
 		System.out.println("2.新增证书接口:");
 		certificate_id = (String) JSONPath.read(res, "$.data");
 		String success = (String) JSONPath.read(res, "$.success");
 		System.out.println("certificate_id="+certificate_id+","+"success="+success);
 		Assert.assertEquals(success,"true","2.新增证书接口：" + res);
 	}
 	
 	
 	@Test(description="3.证书列表查询接口", priority=3)
 	public void testCertificateList()  {
 		String res_all = CertificateBusiness.CertificateList("","");//全部证书
 		String res_on = CertificateBusiness.CertificateList("","1");//已启用的证书
 		String res_stop = CertificateBusiness.CertificateList("","0");//已停用的证书
 		String res_name = CertificateBusiness.CertificateList(name,"");//按名称查询
 		System.out.println("3.证书列表查询接口:");
 		Integer total = (Integer) JSONPath.read(res_all, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotEquals(total,null,"3.证书列表查询接口：" + res_all);
 	}
 	
 	
 	@Test(description="4.证书列表-查看-有效人员接口", priority=4)
 	public void testCertificateDetail()  {
 		String res = CertificateBusiness.CertificateDetail(certificate_id);
 		System.out.println("4.证书列表-查看-有效人员接口:");
 		Integer status = (Integer) JSONPath.read(res, "$.status");
 		System.out.println("status="+status);
 		Assert.assertNotEquals(status,null,"4.证书列表-查看-有效人员接口：" + res);
 	}
 	
 	
 	@Test(description="5.证书列表-查看-关联关系接口", priority=5)
 	public void testQueryRelation()  {
 		String res = CertificateBusiness.QueryRelation(certificate_id);
 		System.out.println("5.证书列表-查看-关联关系接口:");
 		String total = (String) JSONPath.read(res, "$.total");
 		System.out.println("total="+total);
 		Assert.assertNotEquals(total,null,"5.证书列表-查看-关联关系接口：" + res);
 	}
 	
 	
 	@Test(description="6.证书列表导出接口", priority=6)
 	public void testCertificatesExport()  {
 		String res = CertificateBusiness.CertificatesExport("2","");
 		System.out.println("6.证书列表导出接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg,"导出证书列表成功","6.证书列表导出接口：" + res);
 	}
 	
 	
 	@Test(description="7.编辑证书接口", priority=7)
 	public void testEditCertificate()  {
 		String logo_url = "https://oss.coolcollege.cn/1814376318658285568.png";
 		String title = name +"编辑";
 		long current_time = System.currentTimeMillis();
 		String create_time = String.valueOf(current_time);
 		String image_url = "https://oss.coolcollege.cn/1809008230358192128.png";
 		String preview_url = "https://oss.coolcollege.cn/1814493705193787392.png";
 		String common_seal_url = "https://sdn.coolcollege.cn/assets/kuxueyuan/certificate/seal-zh.png";
 		String res = CertificateBusiness.EditCertificate(logo_url,title,certificate_id,create_time,image_url,"chinese",preview_url,
 				template_id,common_seal_url);
 		System.out.println("7.编辑证书接口:");
 		String message = (String) JSONPath.read(res, "$.message");
 		System.out.println("message="+message);
 		Assert.assertEquals(message,"success","7.编辑证书接口：" + res);
 	}
 	
 	
 	@Test(description="8.颁发证书接口", priority=8)
 	public void testSaveCertificates() throws InterruptedException  {
 		long current_time = System.currentTimeMillis();
 		String create_time = String.valueOf(current_time);
 		String res = CertificateBusiness.SaveCertificates(create_time,create_time,certificate_id);
 		System.out.println("8.颁发证书接口:");
 		JSONArray biz_certificate_user_mapping_ids = (JSONArray) JSONPath.read(res, "$.biz_certificate_user_mapping_ids");
 		System.out.println("biz_certificate_user_mapping_ids="+biz_certificate_user_mapping_ids);
 		Assert.assertNotEquals(biz_certificate_user_mapping_ids,null,"8.颁发证书接口：" + res);
 		TimeUnit.SECONDS.sleep(3);
 	}
 	
 	
 	public static String record_id = "";
 	@Test(description="9.证书列表-查看-有效人员数据接口", priority=9)
 	public void testCertificatesUsers() throws UnsupportedEncodingException  {
// 		String res = CertificateBusiness.CertificatesUsers(certificate_id,"","","0","0","","validity");
// 		System.out.println("9.证书列表-查看-有效人员数据接口:");	
// 		record_id = (String) JSONPath.read(res, "$.list[0].id");
// 		System.out.println("record_id="+record_id+","+"res="+res);
// 		Assert.assertNotEquals(record_id,null,"9.证书列表-查看-有效人员数据接口：" + res);
 	}
	
 	
 	@Test(description="10.证书列表-查看-有效人员-提醒接口", priority=10)
 	public void testRemindCertificates()  {
// 		String res = CertificateBusiness.RemindCertificates(record_id);
// 		System.out.println("10.证书列表-查看-有效人员-提醒接口:");
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"证书:为永久有效证书,本次未发送通知;","10.证书列表-查看-有效人员-提醒接口：" + res);
 	}
 	
 	
 	@Test(description="11.证书列表-查看-有效人员-导出数据接口", priority=11)
 	public void testUserExport()  {
 		String res = CertificateBusiness.UserExport(certificate_id,"","0","0","issue","validity");
 		System.out.println("11.证书列表-查看-有效人员-导出数据接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg,"导出学习项目成功","11.证书列表-查看-有效人员-导出数据接口：" + res);
 	}
 	
 	
 	@Test(description="12.证书列表-查看-有效人员-撤销接口", priority=12)
 	public void testDeleteUser()  {
// 		String res = CertificateBusiness.DeleteUser(certificate_id,record_id);
// 		System.out.println("12.证书列表-查看-有效人员-撤销接口:");
// 		String deleted = (String) JSONPath.read(res, "$.deleted");
// 		System.out.println("deleted="+deleted);
// 		Assert.assertEquals(deleted,"true","12.证书列表-查看-有效人员-撤销接口：" + res);
 	}
 	
 	
 	@Test(description="15.我的证书记录-校验撤销后证书状态接口", priority=15)
 	public void testRevoke()  {
// 		String res = CertificateBusiness.CertificatesUser("0","","issue",name,"");
// 		System.out.println("15.我的证书记录-校验撤销后证书状态接口:");
// 		String status = (String)JSONPath.read(res, "$.list[0].status");
// 		System.out.println("status="+status);
// 		Assert.assertEquals(status,"deleted","15.我的证书记录-校验撤销后证书状态接口"+res);
 	}
 	
 	
 	public static String course_id = "";
 	@Test(description="16.获取课件列表接口", priority=16)
 	public void testResourceGetList()  {
// 		String res = CertificateBusiness.ResourceGetList("");
// 		System.out.println("16.获取课件列表接口:");
// 		course_id = (String)JSONPath.read(res, "$.list[0].id");
// 		System.out.println("course_id="+course_id);
// 		Assert.assertNotEquals(course_id,null,"16.获取课件列表接口"+res);
 	}
 	
 	
 	public static String studyplan_id = "";
 	@Test(description="17.创建有证书的学习任务接口", priority=17)
 	public void testStudyAdd()  {
// 		String res = CertificateBusiness.StudyPlanAdd(studyplan_title,certificate_id,course_id);
// 		System.out.println("17.创建有证书的学习任务接口:");
// 		studyplan_id = (String)JSONPath.read(res, "$.data");
//		String msg = (String)JSONPath.read(res, "$.msg");
// 		System.out.println("msg="+msg+","+"studyplan_id="+studyplan_id);
// 		Assert.assertEquals(msg,"新增计划成功！","17.创建有证书的学习任务接口"+res);
 	}
 	
 	
 	@Test(description="18.学习任务详情页接口", priority=18)
 	public void testGetOne()  {
// 		String res = CertificateBusiness.GetOne(studyplan_id);
// 		System.out.println("18.学习任务详情页接口:");
// 		String msg = (String)JSONPath.read(res, "$.msg");
// 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"success","18.学习任务详情页接口"+res);
 	}
 	
	 	 	
 	@Test(description="19.完成学习任务的学习接口", priority=19)
 	public void testSaveProcess()  {
// 		long currenttime = System.currentTimeMillis();  
//		String tempTime = String.valueOf(currenttime);
// 		String res = CertificateBusiness.SaveProcess(studyplan_id,course_id,tempTime);
// 		System.out.println("19.完成学习任务的学习接口:");
// 		Integer progress = (Integer)JSONPath.read(res, "$.progress");
// 		System.out.println("progress="+progress);
// 		Assert.assertSame(progress,100,"19.完成学习任务的学习接口"+res);
 	}
 	
 	
 	@Test(description="20.我的证书记录-校验是否获得证书接口", priority=20)
 	public void testCertificatesUser()  {
// 		String res_name = CertificateBusiness.CertificatesUser("0","","issue",name,"");//按名称查询
// 		String res_all = CertificateBusiness.CertificatesUser("0","","issue","","");//全部类型
// 		String res_system = CertificateBusiness.CertificatesUser("1","","issue","","");//系统颁发
// 		String res_manual = CertificateBusiness.CertificatesUser("3","","issue","","");//手动颁发
// 		System.out.println("20.我的证书记录-校验是否获得证书接口:");
// 		Integer total = (Integer)JSONPath.read(res_all, "$.total");
// 		System.out.println("total="+total);
// 		Assert.assertNotEquals(total,0,"20.我的证书记录-校验是否获得证书接口"+res_all);
 	}
 	
 	
 	@Test(description="21.删除学习任务接口", priority=21)
 	public void testDeleteStudy()  {
// 		String res = CertificateBusiness.DeleteStudy(studyplan_id);
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("21.删除学习任务接口:"+"msg="+msg);
// 		Assert.assertEquals(msg, "删除学习计划成功","21.删除学习任务接口" + res);
 	}
 	
 	
 	@Test(description="22.修改证书状态接口", priority=22)
 	public void testStatusCertificates()  {
 		String res = CertificateBusiness.StatusCertificates(certificate_id);//停用证书
 		String res_on = CertificateBusiness.StatusCertificates(certificate_id);//启用证书
 		System.out.println("22.修改证书状态接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg,"修改证书状态成功！","22.修改证书状态接口：" + res);
 	}
 	
 	
 	@Test(description="23.删除证书-不撤销已获得学员的证书接口", priority=23)
 	public void testDeleteCertificates()  {
 		String res = CertificateBusiness.DeleteCertificates(certificate_id,false);
 		System.out.println("23.删除证书-不撤销已获得学员的证书接口:");
 		String msg = (String) JSONPath.read(res, "$.msg");
 		System.out.println("msg="+msg);
 		Assert.assertEquals(msg,"删除证书成功！","23.删除证书-不撤销已获得学员的证书接口：" + res);
 	}
 	
 	
// 	@Test(description="24.删除证书-撤销已获得学员的证书接口", priority=24)
// 	public void testDeleteCertificates()  {
// 		String res = CertificateBusiness.DeleteCertificates(certificate_id,true);
// 		System.out.println("24.删除证书-撤销已获得学员的证书接口:");
// 		String msg = (String) JSONPath.read(res, "$.msg");
// 		System.out.println("msg="+msg);
// 		Assert.assertEquals(msg,"删除证书成功！","24.删除证书-撤销已获得学员的证书接口：" + res);
// 	}
 	
 	
 	
}
