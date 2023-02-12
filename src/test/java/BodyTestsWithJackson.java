import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.entities.NotFound;
import com.entities.RateLimit;
import com.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.ResponseUtils.*;

public class BodyTestsWithJackson extends Base {

    @Test
    public void returnCorrectLogin() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/abdelnaby1");
        response = client.execute(get);
        User user = unmarshaall(response, User.class);
        Assert.assertEquals(user.getLogin(), "abdelnaby1");
    }

    @Test
    public void returnCorrectId() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/abdelnaby1");
        response = client.execute(get);
        User user = unmarshaall(response, User.class);
        Assert.assertEquals(user.getId(), "44681514");
    }

    @Test
    public void notFoundMessageIsCorrect() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/notexist");
        response = client.execute(get);
        NotFound notFound = unmarshaall(response, NotFound.class);
        Assert.assertEquals(notFound.getMessage(), "Not Found");
    }

    @Test
    public void correctRateLimitaAreSet() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");
        response = client.execute(get);
        RateLimit rateLimits = unmarshaall(response, RateLimit.class);
        Assert.assertEquals(rateLimits.getCoreLimit(), 60);
        Assert.assertEquals(rateLimits.getSearchLimit(), 10);

    }

}
