package newexam.examPlan;

import cn.kxy.base.business.EnterpriseData;
import com.alibaba.fastjson.JSONObject;
import com.lazy.assured.utils.RestAssuredRequestHandler;
import com.lazy.common.utils.ResourceFileUtil;

public class NewExamPlan {
    private final JSONObject newExamURLObject = (new ResourceFileUtil()).parseAllJsonFiles("url");
    private RestAssuredRequestHandler requestHandler;

    public NewExamPlan() {
        requestHandler = new RestAssuredRequestHandler();
    }

    public String getNewExamPlanList() {
        String getNewExamPlanListURL = RestAssuredRequestHandler.buildURL(newExamURLObject.getString("getNewExamPlanList"), EnterpriseData.getEnterpriseId());
        return requestHandler.sendGetRequest(getNewExamPlanListURL,  "page_number", "1", "page_size", "20","scope","all","publish_status","published","exam_status","all");
    }


}
