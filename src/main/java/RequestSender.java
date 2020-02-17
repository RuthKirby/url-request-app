import model.URLPropertiesValid;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class RequestSender {

    private static final int TIMEOUT = 10000;

    /**
     * Send a GET request to the given URL. Will time out after 10 seconds.
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
    public static URLPropertiesValid getUrlGetRequestInfo(String url, HttpResponse response) {
        URLPropertiesValid urlPropertiesValid = new URLPropertiesValid();
        if (response == null) {
            return urlPropertiesValid;
        }
        urlPropertiesValid.setUrl(url);
        StatusLine statusLine = response.getStatusLine();
        if (statusLine != null) {
            urlPropertiesValid.setStatusCode(String.valueOf(statusLine.getStatusCode()));
        }
        HttpEntity httpEntity = response.getEntity();
        if (httpEntity != null) {
            urlPropertiesValid.setContentLength(String.valueOf(response.getEntity().getContentLength()));
        }
        Header header = response.getFirstHeader(HttpHeaders.DATE.toString());
        if (header != null) {
            urlPropertiesValid.setDate(header.getValue());
        }
        return urlPropertiesValid;
    }

}
