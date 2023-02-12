import java.io.IOException;

import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ResponseUtils;

public class DeleteAndPost extends Base {
    @Test
    public void deleteIsCorrect() throws ClientProtocolException, IOException {

        HttpDelete request = new HttpDelete(BASE_ENDPOINT); // a resource to delete
        // set autherization
        request.setHeader(HttpHeaders.AUTHORIZATION, "web token");
        response = client.execute(request);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), "the correct status code");

    }
}
