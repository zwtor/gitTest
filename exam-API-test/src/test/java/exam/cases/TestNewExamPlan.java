package exam.cases;

import com.alibaba.fastjson.JSONPath;
import newexam.examPlan.NewExamPlan;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = {"CI"})
public class TestNewExamPlan {
    private NewExamPlan newExamPlan;

    public TestNewExamPlan() {
        newExamPlan = new NewExamPlan();
    }

    @Test(description = "get new exam exam plan list", priority = 1)
    public void testGetNewExamPaperList() {
        String response = newExamPlan.getNewExamPlanList();
        Integer count = Integer.valueOf(JSONPath.read(response,"$.data.total").toString());
        Assert.assertTrue(count > 0);
//        Assert.assertTrue(JSONPath.read(response,"$.data.list[0].title").toString().startsWith("Automation"), String.format("result of get exam list is: %s", response));
    }
}
