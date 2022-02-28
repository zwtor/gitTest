package cn.kxy.examination.business;

import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.common.utils.FilePath;
import com.lazy.httpclient.utils.UploadFile;

import java.io.File;

public class UploadExamBank {

	public static String enterpriseUrl=  EnterpriseDataUrl.getEnterpriseUrl();
	public static String examUrl = ExamDataUrl.getNewExamUrl();
	public static String token = TokenData.getMangerToken();
	
	/**   
	 * @Title: upload   
	 * @Description: TODO (题库下导入试题)
	 * @param: @param name
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String upload(String name,String type) {
		File file = new File(FilePath.getDataDirectory() + name);
		return UploadFile.httpClientUploadFile(enterpriseUrl + "/poi/file/importQuestion?access_token="+token+"&fileType="+type, file);
	}
	
}
