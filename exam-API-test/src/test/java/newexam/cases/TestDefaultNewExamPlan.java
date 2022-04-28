package newexam.cases;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import newexam.examPlan.NewExamPlan;
import newexam.paper.NewExamPaper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

//考试任务新建-考试设置默认值、学员查看设置默认值、奖励设置发放学分按状态、颁发证书,按系统阅卷
//考试任务新建-阅卷设置：按人工阅卷
public class TestDefaultNewExamPlan {
    private NewExamPlan newExamPlan;
    private NewExamPaper newExamPaper;
    private String examPaperId;
    private List<String> examPlanIdList;

    @BeforeClass
    public void setUp() {
        newExamPlan = new NewExamPlan();
        newExamPaper = new NewExamPaper();
        examPlanIdList = new ArrayList<>();
    }

    @DataProvider
    public Object[][] dataProvider() {
        Object[][] provider = new Object[][] {{"system"}, {"manual"}};
        return provider;
    }

    @Test(description = "add new exam plan with default configuration", dataProvider = "dataProvider", priority = 1)
    public void testAddNewExamPlan(String markType) {
        // get the first paper which contain fill blank or show answer question
        String paperQuestionResponse = newExamPaper.getPaperWithSubjectiveQuestion();
        examPaperId = JSONPath.read(paperQuestionResponse, "$.data.id").toString();
        JSONObject paperQuestionObject = JSONObject.parseObject(paperQuestionResponse);

        // add and publish new exam plan
        String examTitle = "Automation" + CommonData.getStringRandom(3);
        String response = newExamPlan.addDefaultNewExamPlan(examTitle, markType, "published", paperQuestionObject);
        String examPlanId = JSONPath.read(response, "$.data.id").toString();

        // save generated exam plan id into list
        examPlanIdList.add(examPlanId);
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
        String response = newExamPlan.copyNewExamPlan(examPlanIdList.get(0));
        Assert.assertEquals(JSONPath.read(response,"$.success").toString(), "true");
    }

    @Test(description = "change exam plan status", dependsOnMethods ="testAddNewExamPlan", priority = 4)
    public void changeNewExamPlanStatus() {
        // change exam plan status from published to draft
        String status = "draft";
        String response = newExamPlan.changeNewExamPlanStatus(examPlanIdList.get(0), status);
        Assert.assertEquals(JSONPath.read(response, "$.data.status").toString(), status);
        Assert.assertEquals(JSONPath.read(response, "$.success").toString(), "true");

        // change exam plan status from draft to published
        status = "published";
        response = newExamPlan.changeNewExamPlanStatus(examPlanIdList.get(0), status);
        Assert.assertEquals(JSONPath.read(response, "$.data.status").toString(), status);
        Assert.assertEquals(JSONPath.read(response, "$.success").toString(), "true");
    }

    @Test(description = "delete new exam plan", dependsOnMethods = "testAddNewExamPlan", priority = 5)
    public void deleteNewExamPlan() {
        // delete new added exam plan
        for(String examPlanId : examPlanIdList) {
            String response = newExamPlan.deleteNewExamPlan(examPlanId);
            Assert.assertEquals(JSONPath.read(response, "$.data.status").toString(), "deleted");
            Assert.assertEquals(JSONPath.read(response, "$.success").toString(), "true");
        }
    }
}
