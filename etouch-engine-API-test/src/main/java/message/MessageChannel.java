package message;

import cn.kxy.base.business.EnterpriseData;
import com.alibaba.fastjson.JSONObject;
import com.lazy.assured.utils.RestAssuredRequestHandler;
import com.lazy.common.utils.ResourceFileUtil;

public class MessageChannel {
    private final JSONObject messageChannelListURLObject = (new ResourceFileUtil()).parseAllJsonFiles("url");
    private RestAssuredRequestHandler requestHandler;

    public MessageChannel() {
        requestHandler = new RestAssuredRequestHandler();
    }


    public String getMessageChannelList() {
        String getMessageChannelListURL = RestAssuredRequestHandler.buildURL(messageChannelListURLObject.getString("getMessageChannelList"), EnterpriseData.getEnterpriseId());
        return requestHandler.sendGetRequest(getMessageChannelListURL,
                "page_size", "20",
                "page_number", "1");
    }
}
