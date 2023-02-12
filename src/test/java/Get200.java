import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Get200 extends Base {

    @DataProvider
    public Object[][] endpoints() {
        return new Object[][] {
                { "/" },
                { "/rate_limit" },
                { "/search/repositories?q=java" }
        };
    }

    @Test
    public void baseUrlReturns200() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, 200);
    }

    @Test
    public void rateLimitReturns200() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");
        HttpResponse response = client.execute(get);
        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, 200);
    }

    // the number of opend conntection in apache http is 2
    // so if i don't close the 2 aboce connections this test will hang
    // so use testng hooks to open connection before test and close it after test
    @Test
    public void searchReturns200() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/search/repositories?q=java");
        HttpResponse response = client.execute(get);
        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, 200);
    }

    @Test(dataProvider = "endpoints")
    public void thePreviousTestsWithOneTest(String endpoint) throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + endpoint);
        HttpResponse response = client.execute(get);
        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, 200);
    }

}
