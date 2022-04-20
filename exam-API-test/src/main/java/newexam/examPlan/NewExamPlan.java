package newexam.examPlan;

import cn.kxy.base.business.EnterpriseData;
import com.alibaba.fastjson.JSONObject;
import com.lazy.assured.utils.RestAssuredRequestHandler;
import com.lazy.common.utils.ResourceFileUtil;

public class NewExamPlan {
    private final JSONObject newExamURLObject = (new ResourceFileUtil()).parseAllJsonFiles("url");
    private RestAssuredRequestHandler requestHandler;
    private ResourceFileUtil resourceFileUtil;
    private String requestBodyFolder = "requestbody/newExamPlan";

    public NewExamPlan() {
        requestHandler = new RestAssuredRequestHandler();
        resourceFileUtil = new ResourceFileUtil();
    }

    public String getNewExamPlanList() {
        String getNewExamPlanListURL = RestAssuredRequestHandler.buildURL(newExamURLObject.getString("getNewExamPlanList"), EnterpriseData.getEnterpriseId());
        return requestHandler.sendGetRequest(getNewExamPlanListURL,  "page_number", "1", "page_size", "20","scope","all","publish_status","published","exam_status","all");
    }
    public String copyNewExamPlan(String examPlanId) {
        String copyNewExamPlanURL = RestAssuredRequestHandler.buildURL(newExamURLObject.getString("copyNewExamPlan"), EnterpriseData.getEnterpriseId(), examPlanId);
        JSONObject requestBody = ResourceFileUtil.setJsonBodyValue(resourceFileUtil.parseJsonFile(requestBodyFolder, "copyNewExamPlan.json"),
                "$.planId", examPlanId);
        return requestHandler.sendPostRequest(copyNewExamPlanURL, requestBody);
    }

}
