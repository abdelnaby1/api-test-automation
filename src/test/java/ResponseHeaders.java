import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.ResponseUtils.*;

public class ResponseHeaders extends Base {

    @Test
    public void contentTypeIsJson() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        Header contentType = response.getEntity().getContentType();
        Assert.assertEquals(contentType.getValue(), "application/json; charset=utf-8");
        // or
        ContentType ct = ContentType.getOrDefault(response.getEntity());
        Assert.assertEquals(ct.getMimeType(), "application/json");
    }

    @Test
    public void serverNameExisted() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        String headerValue = getHeader2(response, "Server");
        Assert.assertEquals(headerValue, "GitHub.com");
    }

    @Test
    // etag i used for cahching
    public void eTagIsPresent() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        Assert.assertTrue(headerIsPresent(response, "ETag"));
    }

}
