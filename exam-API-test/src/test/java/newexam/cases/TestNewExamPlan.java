package newexam.cases;

import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.ResourceFileUtil;
import newexam.examPlan.NewExamPlan;
import newexam.paper.NewExamPaper;
import newexam.question.NewQuestion;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = {"CI"})
public class TestNewExamPlan {
    private NewExamPlan newExamPlan;
    private String planId;

    @BeforeClass
    public void setUp() {
        newExamPlan = new NewExamPlan();
    }

    @Test(description = "get new exam exam plan list", priority = 1)
    public void testGetNewExamPaperList() {
        String response = newExamPlan.getNewExamPlanList();
        Integer count = Integer.valueOf(JSONPath.read(response,"$.data.total").toString());
        Assert.assertTrue(count > 0);
       // Assert.assertTrue(JSONPath.read(response,"$.data.list[0].title").toString().startsWith("Automation"), String.format("result of get exam list is: %s", response));
        planId = JSONPath.read(response,"$.data.list[0].id").toString();
        System.out.println("examID: " + planId);
    }
    @Test(description = "copy new exam plan", dependsOnMethods ="testGetNewExamPaperList",priority = 2)
    public void testCopyNewExamPlan() {
        String response = newExamPlan.copyNewExamPlan(planId);
        Assert.assertEquals("true", JSONPath.read(response,"$.success").toString());
    }
}
