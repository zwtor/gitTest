package cn.kxy.course.resources.bussiness;

import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.common.utils.FilePath;
import com.lazy.httpclient.utils.UploadFile;

import java.io.File;

import static io.restassured.RestAssured.given;
public class UploadCourseware {

	public static String enterpriseUrl=  EnterpriseDataUrl.getEnterpriseUrl();
	public static String examUrl = ExamDataUrl.getNewExamUrl();
	public static String token = TokenData.getMangerToken();
	
	/**   
	 * @Title: uploadFile   
	 * @Description: TODO (课件上传文件)
	 * @param: @param name
	 * @param: @param type
	 * @param: @param isDoc
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String uploadFile(String name,String type,String isDoc,String classifyId) {
		
		
		File file = new File(FilePath.getDataDirectory() + name);
		return UploadFile.httpClientUploadFile(enterpriseUrl + "/course/resource/add?"
				+ "id=wlbhmqo70060z5zh&name="+file.getName()+"&process=0&uploadedSize=0&size="+file.length()+"&isError=false&"
				+ "type="+type+"&msg=&status=&videoId=&isDoc="+isDoc+"&isCanceling=false&binaryFile="
				+ "%7B%22id%22:%22wlbhmqo70060z5zh%22%7D&classifyId="+classifyId+"&bizType=0&access_token="+token, file);
	}
	
	/**   
	 * @Title: changeCourse   
	 * @Description: TODO   变更课件
	 * @param: @param name
	 * @param: @param type
	 * @param: @param isDoc
	 * @param: @param oldResourceId
	 * @param: @param classifyId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String changeCourse(String name,String type,String isDoc,String oldResourceId,String classifyId) {
		File file = new File(FilePath.getDataDirectory() + name);
		return given().queryParam("id", "zqj9bxk9sn3tlzi8").queryParam("name", file.getName()).queryParam("process", "0").queryParam("uploadedSize", "0")
				.queryParam("size", file.length()).queryParam("isError","false").queryParam("type",type).queryParam("isDoc",isDoc)
				.queryParam("isCanceling","false").queryParam("binaryFile","{\"id\":\"zqj9bxk9sn3tlzi8\"}").queryParam("classifyId",classifyId).queryParam("bizType","0")
		 .queryParam("access_token",token).queryParam("old_resource_id", oldResourceId).multiPart(file).when().post(enterpriseUrl + "/course/resource/add") .asString();
	}
}
