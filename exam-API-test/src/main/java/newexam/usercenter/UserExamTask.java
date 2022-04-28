package newexam.usercenter;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.UserProfile;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.RestAssuredRequestHandler;
import com.lazy.common.utils.ResourceFileUtil;

public class UserExamTask {
    private final JSONObject newExamPlanURLObject = (new ResourceFileUtil()).parseJsonFile("url", "userExamTaskURL.json");
    private RestAssuredRequestHandler requestHandler;
    private ResourceFileUtil resourceFileUtil;
    private UserProfile userProfile;

    public UserExamTask() {
        requestHandler = new RestAssuredRequestHandler();
        resourceFileUtil = new ResourceFileUtil();
        userProfile = new UserProfile();
    }

    public String getExamTaskList(String keyword, String status) {
        String userId = JSONPath.read(userProfile.getUserProfile(), "$.data.id").toString();
        String getExamTaskListURL = RestAssuredRequestHandler.buildURL(newExamPlanURLObject.getString("getExamTaskList"), EnterpriseData.getEnterpriseId(), userId);
        return requestHandler.sendGetRequest(getExamTaskListURL, "keyword", keyword, "status", status, "page_size", "20", "page_number", "1");
    }

}
