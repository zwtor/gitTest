package imagetext;

import cn.kxy.base.business.EnterpriseData;
import com.alibaba.fastjson.JSONObject;
import com.lazy.assured.utils.RestAssuredRequestHandler;
import com.lazy.common.utils.ResourceFileUtil;


public class ImageText {
    private final JSONObject imageTextURLObject = (new ResourceFileUtil()).parseAllJsonFiles("url");
    private RestAssuredRequestHandler requestHandler;

    public ImageText(){
        requestHandler = new RestAssuredRequestHandler();

    }

    public String getImageTextList(){
        String getImageTextListUrl = RestAssuredRequestHandler.buildURL(imageTextURLObject.getString("getImageTextList"),EnterpriseData.getEnterpriseId());
        return requestHandler.sendGetRequest(getImageTextListUrl,"title","","page_number","1","page_size","20","type","","classify_id","","view_range","","view_sharing","","course_status","released","course_type","all","userIds","");


    }

}
