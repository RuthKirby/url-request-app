import model.URLPropertiesError;
import model.URLPropertiesValid;
import model.ErrorMessageEnum;
import org.apache.http.HttpResponse;
import util.URLPropertiesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class for app
 */
public class RequestManager {

    public static void main(String[] args) {
        for (String str : args) {
            RequestManager.makeUrlRequests(str);
        }
    }

    /**
     * Prints the JSON formatted status, content length and date information from
     * responses to GET requests to valid URLs and the error of bad URLs.
     *
     * @param newLineDelimitedUrls - urls to send GET requests to
     */
    public static void makeUrlRequests(String newLineDelimitedUrls) {
        String[] urls = newLineDelimitedUrls.split(System.lineSeparator());
        List<URLPropertiesValid> urlPropertiesValidList = new ArrayList<>();
        List<URLPropertiesError> urlPropertiesErrorList = new ArrayList<>();

        for (String url : urls) {
            if (!URLPropertiesUtil.validateUrl(url)) {
                urlPropertiesErrorList.add(URLPropertiesUtil.createURLPropertiesError(url, ErrorMessageEnum.INVALID_URL.getMessage()));
            } else {
                HttpResponse response = RequestSender.sendGetRequest(url);
                if (response == null) {
                    urlPropertiesErrorList.add(URLPropertiesUtil.createURLPropertiesError(url, ErrorMessageEnum.EXCEPTION.getMessage()));
                } else {
                    urlPropertiesValidList.add(RequestSender.getUrlGetRequestInfo(url, response));
                }
            }
        }
        //After looping through URLs print received info
        if (!urlPropertiesValidList.isEmpty()) {
            URLPropertiesUtil.printValidURLJSONDocument(urlPropertiesValidList);
        }

        if (!urlPropertiesErrorList.isEmpty()) {
            URLPropertiesUtil.printErrorURLJSONDocument(urlPropertiesErrorList);
        }
    }
}
