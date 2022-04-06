package cn.kxy.base.business;

import com.alibaba.fastjson.JSONObject;
import com.lazy.assured.utils.RestAssuredRequestHandler;
import com.lazy.common.utils.ResourceFileUtil;

public class UserProfile {
    private final JSONObject userProfileURLObject = (new ResourceFileUtil()).parseJsonFile("url", "userProfile.json");
    private RestAssuredRequestHandler requestHandler;

    public UserProfile() {
        requestHandler = new RestAssuredRequestHandler();
    }

    public String getUserProfile() {
        String getUserProfileURL = RestAssuredRequestHandler.buildURL(userProfileURLObject.getString("getUserProfile"));
        return requestHandler.sendGetRequest(getUserProfileURL);
    }
}
