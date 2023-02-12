import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.entities.User.*;

public class BodyTests extends Base {
    @Test
    public void returnCorrectName() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/abdelnaby1");
        response = client.execute(get);

        String jsonBody = EntityUtils.toString(response.getEntity());
        // System.out.println(jsonBody);
        // it's a map
        JSONObject jsonObject = new JSONObject(jsonBody);
        String loginValue = (String) getValue(jsonObject, LOGIN);
        Assert.assertEquals(loginValue, "abdelnaby1");
    }

    @Test
    public void returnCorrectID() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/abdelnaby1");
        response = client.execute(get);

        String jsonBody = EntityUtils.toString(response.getEntity());
        // System.out.println(jsonBody);
        // it's a map
        JSONObject jsonObject = new JSONObject(jsonBody);
        Integer loginValue = (Integer) getValue(jsonObject, ID);
        Assert.assertEquals(loginValue, 44681514);
    }

    private Object getValue(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }
}
