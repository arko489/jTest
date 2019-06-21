
import java.io.IOException;
import java.net.HttpURLConnection;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;




public class CreateRepo {
	public boolean createPageConfluence(String pageTitle) throws AuthenticationException, ClientProtocolException, IOException
	{
	CloseableHttpClient client = HttpClients.createDefault();
    HttpPut httpPut = new HttpPut("https://roy.jfrog.io/roy/api/repositories"+pageTitle);
    String json;
    UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("admin", "AKCp5dK4j6kKM1SLh7BGvvAYsDQvHbvxXAmHdaqCX16awGH4NxzEXgszW238XQrq9fFQBEv55");
    json = "{\"rclass\":\"local\"}";
    StringEntity entity = new StringEntity(json);
    httpPut.addHeader(new BasicScheme().authenticate(credentials, httpPut, null));
    httpPut.setEntity(entity);
    httpPut.setHeader("Accept", "application/json");
    httpPut.setHeader("Content-type", "application/json");
    CloseableHttpResponse response = client.execute(httpPut);
    int responseCode = response.getStatusLine().getStatusCode();
    System.out.println(responseCode);
    if(responseCode == HttpURLConnection.HTTP_OK) {
        client.close();
        return true;
    }
    else {
        client.close();
        return false;
    }
	}
	public static void main(String[] args) throws AuthenticationException, ClientProtocolException, IOException 
	{
		System.out.println(new CreateRepo().createPageConfluence("GAMMA"));
	}
}



