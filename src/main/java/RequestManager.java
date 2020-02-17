import model.DocumentItemError;
import model.DocumentItemValid;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestManager {

    private static final int TIMEOUT = 10000;

    public static void main(String[] args) {
        RequestManager requestManager = new RequestManager();
        requestManager.makeUrlRequests("http://valid.com" + System.lineSeparator() + "https://valid.com");
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

    /**
     * Prints the JSON formatted status, content length and date information from
     * responses to GET requests
     *
     * @param newLineDelimitedUrls - urls to send GET requests to
     */
    public void makeUrlRequests(String newLineDelimitedUrls) {
        String[] urls = newLineDelimitedUrls.split(System.lineSeparator());
        List<DocumentItemValid> documentItemValidList = new ArrayList<>();
        List<DocumentItemError> documentItemErrorList = new ArrayList<>();

        for (String url : urls) {

        }
    }
}
