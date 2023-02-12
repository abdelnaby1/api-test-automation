import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ResponseUtils;

public class Options204 extends Base {

    @Test
    public void optionsReturnsCorrectMethodsList() throws ClientProtocolException, IOException {
        String header = "Access-Control-Allow-Methods";
        String expectedReply = "GET, POST, PATCH, PUT, DELETE";

        HttpOptions request = new HttpOptions(BASE_ENDPOINT);
        response = client.execute(request);
        String actualValue = ResponseUtils.getHeader(response, header);
        Assert.assertEquals(actualValue, expectedReply);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 204);
    }
}
