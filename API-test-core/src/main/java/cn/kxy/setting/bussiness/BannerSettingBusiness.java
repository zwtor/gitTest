package cn.kxy.setting.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.httpclient.utils.HttpRequest;

public class BannerSettingBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String cmdb_url= EnterpriseDataUrl.getCmdbUrl();
	public static String module_setting_url =cmdb_url + "v2/"+enterprise_id+"/home_page/module_setting";
	
	public static String queryBanner () {
		return HttpRequest.get(module_setting_url).query( "access_token",token).query("department_id","1").query("key","banner").send().body();
	}
	
	public static String updateBanner(String id) {
		return HttpRequest.post(cmdb_url+"v2/"+enterprise_id+"/home_page/module_setting/update").query("access_token",token).
				data("{\r\n" + 
						"    \"operation_type\": \"update\",\r\n" + 
						"    \"department_id\": \"1\",\r\n" + 
						"    \"menus\": [\r\n" + 
						"        {\r\n" + 
						"            \"banners\": [\r\n" + 
						"                {\r\n" + 
						"                    \"id\": \"16215847220231\",\r\n" + 
						"                    \"resource_id\": \"\",\r\n" + 
						"                    \"type\": \"link\",\r\n" + 
						"                    \"url\": \"https://oss.coolcollege.cn/1798619177603436544.png\",\r\n" + 
						"                    \"link_url\": \"https://www.baidu.com\"\r\n" + 
						"                },\r\n" + 
						"                {\r\n" + 
						"                    \"id\": \"1621584722000\",\r\n" + 
						"                    \"resource_id\": \"\",\r\n" + 
						"                    \"type\": \"image\",\r\n" + 
						"                    \"url\": \"https://oss.coolcollege.cn/1798619177603436544.png\",\r\n" + 
						"                    \"title\": \"\"\r\n" + 
						"                }\r\n" + 
						"            ],\r\n" + 
						"            \"checked\": \"true\",\r\n" + 
						"            \"custom_name\": \"banner图\",\r\n" + 
						"            \"id\": \""+id+"\",\r\n" + 
						"            \"key\": \"banner\",\r\n" + 
						"            \"name\": \"banner图\"\r\n" + 
						"        }\r\n" + 
						"    ],\r\n" + 
						"    \"key\": \"banner\",\r\n" + 
						"    \"type\": \"pc\",\r\n" + 
						"    \"access_token\": \"95d5ad91f0254666dd303c4c61082d87\"\r\n" + 
						"}").send().body();
	}
	
}
