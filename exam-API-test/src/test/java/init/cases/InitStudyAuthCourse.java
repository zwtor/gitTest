package init.cases;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.course.resources.bussiness.ArticleBusiness;
import cn.kxy.course.resources.bussiness.CourseBusiness;
import cn.kxy.course.resources.bussiness.CoursewareBusiness;
import cn.kxy.lecturer.business.LecturerListBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.FilePath;
import org.testng.annotations.BeforeSuite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class InitStudyAuthCourse {
	public static String articlename = "Astrophysics";
	public static String outer_name = "Trinity";
	public String classifyname = "CourseFolder";
	public String course_name = "Physics";
	public String cert_name = "Coo_cert";

	@BeforeSuite
	public void init() {
		String surrounding = readTxt(FilePath.getHome() + File.separator + "environmental.txt");
		System.out.println("Now cases is running in "+surrounding);
		System.out.println("start init data");
		BaseBusiness.addLecturerLevel();
		String res = LecturerListBusiness.queryLecturerList(outer_name, "1", "");
		int total = (int) JSONPath.read(res, "$.total");
		if (Integer.valueOf(total) == 0) {
			LecturerListBusiness.addLecturer(outer_name, "1", "13526231231", "outside", "", BaseBusiness.lecturerLevel,
					"auto", "jmeter");
		}
		System.out.println("add Lecturer success");
		String courseware_res = CoursewareBusiness.queryList(classifyname);
		int courseware_total = (int)JSONPath.read(courseware_res, "$.total");
		if (courseware_total ==0) {
			CoursewareBusiness.addFolder(classifyname, "0","","all");
		}
		System.out.println("add Courseware success");
		String art_id = CoursewareBusiness.getIdByKeyword(articlename);
		if (art_id==null) {
				ArticleBusiness.addArticle(articlename,"This is a description","0");
				System.out.println("add Article success");
		}
		String course_id = CourseBusiness.getIdByPage(course_name);
		if (course_id == null) {
			CourseBusiness.addCourse(course_name, "1", "this is desription",
					LecturerListBusiness.getIdByKeyword(outer_name),
					ArticleBusiness.getIdByKeyword(articlename), "1", "3", "",
					"1", "0", "release");
		}
		System.out.println("add Course success");
		String cert_id = CertificateBusiness.getIdByKeyword(cert_name);
		if (cert_id==null) {
			 CertificateBusiness.creatCertificate(cert_name, "kxyTest0", "Sinpoes", "true", "chinese", "2");
		}
		System.out.println("add Certificate success");

	}
	
	public static  String readTxt(String fileName) {
	    File file = new File(fileName);
	    BufferedReader reader = null;
	    StringBuffer sbf = new StringBuffer();
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempStr;
	        while ((tempStr = reader.readLine()) != null) {
	            sbf.append(tempStr);
	        }
	        reader.close();
	        return sbf.toString();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }
	        }
	    }
	    return sbf.toString();
	}
}
