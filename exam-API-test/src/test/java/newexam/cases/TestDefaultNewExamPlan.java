package newexam.cases;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import newexam.examPlan.NewExamPlan;
import newexam.paper.NewExamPaper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//考试任务新建-考试设置默认值、学员查看设置默认值、奖励设置发放学分按状态、颁发证书
public class TestDefaultNewExamPlan {
    private NewExamPlan newExamPlan;
    private NewExamPaper newExamPaper;
    private String examPlanId;
    private String examPlanTitle;

    @BeforeClass
    public void setUp() {
        newExamPlan = new NewExamPlan();
        newExamPaper = new NewExamPaper();
        examPlanTitle = "Automation" + CommonData.getStringRandom(3);
    }

    @Test(description = "add new exam plan with default configuration", priority = 1)
    public void testAddNewExamPlan() {
        String examPaperListResponse = newExamPaper.getNewPaperList(false);
        String examPaperId = JSONPath.read(examPaperListResponse, "$.data.list[0].id").toString();
        String examPaperResponse = newExamPaper.getNewPaperDetail(examPaperId);
        JSONObject examPaperObject = JSONObject.parseObject(examPaperResponse);

        // add and publish new exam plan
        String response = newExamPlan.addDefaultNewExamPlan(examPlanTitle, "published", examPaperObject);
        examPlanId = JSONPath.read(response, "$.data.id").toString();
        Assert.assertEquals(JSONPath.read(response, "$.data.status").toString(), "published");
        Assert.assertEquals(JSONPath.read(response, "$.success").toString(), "true");
    }

    @Test(description = "get new exam plan list", priority = 2)
    public void testGetNewExamPlanList() {
        String response = newExamPlan.getNewExamPlanList();
        int count = Integer.parseInt(JSONPath.read(response,"$.data.total").toString());
        Assert.assertTrue(count > 0);
    }

    @Test(description = "copy new exam plan", dependsOnMethods ="testAddNewExamPlan",priority = 3)
    public void testCopyNewExamPlan() {
        String response = newExamPlan.copyNewExamPlan(examPlanId);
        Assert.assertEquals(JSONPath.read(response,"$.success").toString(), "true");
    }

    @Test(description = "change exam plan status", dependsOnMethods ="testAddNewExamPlan", priority = 4)
    public void changeNewExamPlanStatus() {
        // change exam plan status from published to draft
        String status = "draft";
        String response = newExamPlan.changeNewExamPlanStatus(examPlanId, status);
        Assert.assertEquals(JSONPath.read(response, "$.data.status").toString(), status);
        Assert.assertEquals(JSONPath.read(response, "$.success").toString(), "true");

        // change exam plan status from draft to published
        status = "published";
        response = newExamPlan.changeNewExamPlanStatus(examPlanId, status);
        Assert.assertEquals(JSONPath.read(response, "$.data.status").toString(), status);
        Assert.assertEquals(JSONPath.read(response, "$.success").toString(), "true");
    }

    @Test(description = "delete new exam plan", dependsOnMethods = "testAddNewExamPlan", priority = 5)
    public void deleteNewExamPlan() {
        String response = newExamPlan.deleteNewExamPlan(examPlanId);
        Assert.assertEquals(JSONPath.read(response, "$.data.status").toString(), "deleted");
        Assert.assertEquals(JSONPath.read(response, "$.success").toString(), "true");
    }

}
