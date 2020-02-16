import model.DocumentItemValid;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class RequestManager {

    private static final int TIMEOUT = 10000;

    public static void main(String[] args) {

    }

    /**
     * Send a GET request to the given URL.
     *
     * @param url
     * @return http response
     */
    public HttpResponse sendGetRequest(String url) {
        return null;
    }

    /**
     * Retrieve the status code, content length and date from a response
     * and set it it to a valid document item.
     *
     * @param response - http response
     * @return - a valid url document item
     */
    public DocumentItemValid parseResponseInfo(HttpResponse response) {
        return null;
    }
}
