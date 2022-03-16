import com.lazy.assured.utils.RestAssuredRequestHandler;
import com.lazy.common.utils.ResourceFileUtil;
import org.testng.annotations.Test;

public class FileResourceTest {
    @Test
    void loadJSONTest() {
        System.out.println(RestAssuredRequestHandler.buildURL("test", "post", "2"));
    }

    @Test
    void loadConfigTest() {
        ResourceFileUtil resourceFileUtil = new ResourceFileUtil();
        System.out.println(resourceFileUtil.readSingleFile("config", "database.properties"));
    }
}

