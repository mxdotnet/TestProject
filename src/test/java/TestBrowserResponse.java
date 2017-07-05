import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Created by Matt James on 7/3/2017.
 */
public class TestBrowserResponse
{
    private final String USER_AGENT="Mozilla/5.0";
    public int GetResponse() throws ClientProtocolException, IOException
    {
        StringBuffer result=new StringBuffer();

        HttpClient client = HttpClientBuilder.create().build();
        String url="https://api.nasa.gov/planetary/sounds?q=apollo&api_key=o8h8Px0tKvp8pPSMYWWdbPAXRI2GDXyOt82hlIDf";
        HttpGet request=new HttpGet(url);
        request.addHeader("User-Agent",USER_AGENT);
        HttpResponse response=client.execute(request);
        int responseCode=response.getStatusLine().getStatusCode();
        System.out.println("Response Code: " + responseCode);
        try{
            if(responseCode==200)

            {
                System.out.println("Get Response is Successful");
                BufferedReader reader=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line="";
                while((line=reader.readLine())!=null)
                {

                    result.append(line);
                    System.out.println(result.toString());
                }
            }
            //  return  result.toString();

            return response.getStatusLine().getStatusCode();

        }
        catch(Exception ex)
        {
            result.append("Get Response Failed");
            //return result.toString();
            return response.getStatusLine().getStatusCode();
        }

    }

}
