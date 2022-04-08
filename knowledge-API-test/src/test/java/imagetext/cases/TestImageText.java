package imagetext.cases;
import com.alibaba.fastjson.JSONPath;
import imagetext.ImageText;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestImageText {
    private ImageText imageText;

    public TestImageText(){
        imageText = new ImageText();

    }
    @Test(description = "get Image Text list", priority = 1)
    public void getImageTextList(){
        String response = imageText.getImageTextList();
        Integer count = Integer.valueOf(JSONPath.read(response,"$.total").toString());

        Assert.assertTrue(count != 0);

    }
}
