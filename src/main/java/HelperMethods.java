
import io.restassured.RestAssured;
import org.apache.http.HttpResponse;

import java.util.*;

import static org.junit.Assert.assertEquals;
/**
 * Created by Matt James on 7/3/2017.
 */
public class HelperMethods
{
    //Verify the http response status returned. Check Status Code is 200?
    //We can use Rest Assured library's response's getStatusCode method

    public static void checkStatusIs200 ( HttpResponse res)
    {


        assertEquals("Status Check Failed!", 200, res.getStatusLine().getStatusCode());
    }
}
