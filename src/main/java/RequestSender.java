import model.DocumentItemValid;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class RequestSender {

    private static final int TIMEOUT = 10000;

    /**
     * Send a GET request to the given URL.
     *
     * @param url
     * @return http response
     */
    public static HttpResponse sendGetRequest(String url) {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(TIMEOUT)
                .setConnectionRequestTimeout(TIMEOUT)
                .setSocketTimeout(TIMEOUT).build();
        CloseableHttpClient httpClient =
                HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        HttpGet getMethod = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = httpClient.execute(getMethod);
        } catch (IOException e) {
            return null;
        }
        return response;
    }

    /**
     * Retrieve the status code, content length and date from a response
     * and set it it to a valid document item.
     *
     * @param url
     * @return - a valid url document item
     */
    public static DocumentItemValid getUrlGetRequestInfo(String url, HttpResponse response) {
        DocumentItemValid documentItemValid = new DocumentItemValid();
        documentItemValid.setUrl(url);
        documentItemValid.setStatusCode(String.valueOf(response.getStatusLine().getStatusCode()));
        documentItemValid.setContentLength(response.getFirstHeader(HttpHeaders.CONTENT_LENGTH.toString()).getValue());
        return documentItemValid;
    }

}
