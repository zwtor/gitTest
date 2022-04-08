package live.cases;

import com.alibaba.fastjson.JSONPath;
import live.LiveClass;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = {"CI"})
public class TestLiveClass {
    private LiveClass liveClass;

    public TestLiveClass(){liveClass=new LiveClass(); }
    @Test(description = "get live class list", priority = 1)
    public void getLiveClassList(){
        String response =liveClass.getLiveClassList();
        Integer count = Integer.valueOf(JSONPath.read(response,"$.data.total").toString());
        Assert.assertTrue(count > 0);
    }

}
