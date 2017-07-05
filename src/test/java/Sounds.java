//JAVA Classes
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;


//REST assured is a popular library, very useful
import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;

//Apache Libraries
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;

//HAMCREST
import org.hamcrest.Matchers;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

//JUNIT
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.json.JSONObject;

//SELENIUM
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
 * Created by Matt James on 7/3/2017.
 */

public class Sounds {
    //First, I declared Response and JsonPath objects.
    // private Response res = null; //Response object
    private JsonPath jp = null; //JsonPath object
    // HttpResponse res;

    @Before
    public void setUp() throws Exception {
        // driver = new FirefoxDriver();
        //  baseUrl = "https://api.nasa.gov/planetary/sounds?q=apollo&api_key=DEMO_KEY";
        //  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws Exception {
        // driver.close();
        //driver.quit();
    }

    //This test helps ensure that users
    //can only access the API with a valid key
    @Test
    public void TC1_givenProperRequest_whenRequestIsExecuted_thenResponseStatusFORBIDDEN()
            throws ClientProtocolException, IOException {

        // Given
        HttpUriRequest request = new HttpGet("https://api.nasa.gov/planetary/sounds?q=apollo&api_key=TEST");

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_FORBIDDEN));

    }

    //This test helps ensure that users
    //can access the API with a valid key
    @Test
    public void TC2_givenProperRequest_whenRequestIsExecuted_thenResponseStatusOK()
            throws ClientProtocolException, IOException
    {

        // Given
        HttpUriRequest request = new HttpGet("https://api.nasa.gov/planetary/sounds?q=apollo&api_key=o8h8Px0tKvp8pPSMYWWdbPAXRI2GDXyOt82hlIDf");

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    //This negative test helps ensure that users
    //enter valid query parameter to access API with a valid key
    @Test
    public void TC3_givenBADRequest_whenRequestIsExecuted_thenResponseStatusBADREQUEST()
            throws ClientProtocolException, IOException
    {

        // Given
        HttpUriRequest request = new HttpGet("https://api.nasa.gov/planetary/sounds?api_key=DEMO_KEY");

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_BAD_REQUEST));

        //Note: This test fails and it should pass. We still get a JSON response when we don't pass in the
        //query parameter. Maybe by design on their part, but typically you'd want ?q=<something>
    }

    //This test helps ensure that we get
    //JSON... don't want xml if expecting JSON

    @Test
    public void TC4_givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson()
            throws ClientProtocolException, IOException {

        // Given
        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet("https://api.nasa.gov/planetary/sounds?q=apollo&api_key=o8h8Px0tKvp8pPSMYWWdbPAXRI2GDXyOt82hlIDf");

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        // Then
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        assertEquals(jsonMimeType, mimeType);
    }

    public static Response theResponse;
    public static String jsonAsString;

    //This test helps ensure that we get
    //default count of 10 when no limit param provided
    @Test
    public void TC5_givenRequestWithNoAcceptHeader_whenLimitParamNotProvided_thenDefaultResponseCountIs10()
            throws ClientProtocolException, IOException {

        URL url = new URL("https://api.nasa.gov/planetary/sounds?q=apollo&api_key=o8h8Px0tKvp8pPSMYWWdbPAXRI2GDXyOt82hlIDf");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        Scanner scan = new Scanner(url.openStream());
        String entireResponse = new String();
        while (scan.hasNext())
            entireResponse += scan.nextLine();

        System.out.println(new StringBuilder().append("Response : ").append(entireResponse).toString());

        scan.close();

        JSONObject obj = new JSONObject(entireResponse );

        Integer recordCount = obj.getInt("count");

        // Then
        assertThat(recordCount, equalTo(10));
    }

    //This test helps ensure that we get
    //same count of 25 when limit param of 25 is provided
    @Test
    public void TC6_givenRequestWithNoAcceptHeader_whenLimitParamProvided_thenDefaultResponseCountIsSame()
            throws ClientProtocolException, IOException {

        URL url = new URL("https://api.nasa.gov/planetary/sounds?q=apollo&api_key=o8h8Px0tKvp8pPSMYWWdbPAXRI2GDXyOt82hlIDf&limit=25");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        Scanner scan = new Scanner(url.openStream());
        String entireResponse = new String();
        while (scan.hasNext())
            entireResponse += scan.nextLine();

        System.out.println(new StringBuilder().append("Response : ").append(entireResponse).toString());

        scan.close();

        JSONObject obj = new JSONObject(entireResponse );

        Integer recordCount = obj.getInt("count");

        // Then
        assertThat(recordCount, equalTo(25));
    }


    //This test helps ensure that users
    //can not exceed any API call limits imposed
    //Note: we use the DEMO_KEY param since it's limited to 40 calls per day
    @Test
   public void TC7_givenRequest_whenRequestExeceeds_thenResponseStatus429()
            throws ClientProtocolException, IOException
    {

       for (int i = 0; i < 45; i++)
       {
           // Given
           HttpUriRequest request = new HttpGet("https://api.nasa.gov/planetary/sounds?q=apollo&api_key=DEMO_KEY");

           // When
           HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

           // Then
           assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(429));

       }


    }

}