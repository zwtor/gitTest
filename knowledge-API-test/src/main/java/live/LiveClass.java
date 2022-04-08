package live;

import cn.kxy.base.business.EnterpriseData;
import com.alibaba.fastjson.JSONObject;
import com.lazy.assured.utils.RestAssuredRequestHandler;
import com.lazy.common.utils.ResourceFileUtil;

public class LiveClass {
    private final JSONObject liveClassURLObject = (new ResourceFileUtil()).parseAllJsonFiles("url");
    private RestAssuredRequestHandler requestHandler;

    public LiveClass() {
        requestHandler = new RestAssuredRequestHandler();
    }

    public String  getLiveClassList(){
        String getLiveClassListUrl=RestAssuredRequestHandler.buildURL(liveClassURLObject.getString("getLiveClassList"),EnterpriseData.getEnterpriseId());
        return requestHandler.sendGetRequest(getLiveClassListUrl,"pageSize","20","pageNum","1","liveClassify","live","liveType","");


    }




}
