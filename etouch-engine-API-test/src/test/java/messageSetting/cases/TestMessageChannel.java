package messageSetting.cases;


import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.ResourceFileUtil;
import message.MessageChannel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Test(groups = {"CI"})
public class TestMessageChannel {
    private MessageChannel messageChannel;
    private ResourceFileUtil resourceFileUtil;
    private String paperTitle;
    private String paperId;

    @BeforeClass
    public void setUp() {
        messageChannel = new MessageChannel();
        resourceFileUtil = new ResourceFileUtil() ;
//        paperTitle = "Automation" + CommonData.getStringRandom(3);
    }

    @Test(description = "get new exam papers list created by me",  priority = 1)
    public void testGetNewExamPaperList() {
        String response = messageChannel.getMessageChannelList();
        Integer count = Integer.valueOf(JSONPath.read(response,"$.data.total").toString());
        String msg= (String)JSONPath.read(response, "$.success");
        Assert.assertTrue(count > 0);
        Assert.assertEquals(msg, "true");
    }


}
