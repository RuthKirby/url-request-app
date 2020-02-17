package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.URLPropertiesError;
import model.URLPropertiesValid;
import org.apache.commons.validator.UrlValidator;
import org.apache.http.HttpHeaders;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for manipulating URL Properties Objects.
 */
public class URLPropertiesUtil {

    private static final String URL_KEY = "Url";
    private static final String STATUS_CODE_KEY = "Status-Code";
    private static final String ERROR_KEY = "Error";

    /**
     * Checks whether or not the given URL is valid (i.e. that it begins with either http or https and that
     * it does not contain characters invalid for a URL.
     *
     * @param url - web address to be validated
     * @return {@code true} if a valid URL, {@code false} if otherwise
     */
    public static boolean validateUrl(String url) {
        String[] schemes = {"http", "https"};
        UrlValidator urlValidator = new UrlValidator(schemes);

        return urlValidator.isValid(url);
    }

    /**
     * Creates a url properties object for the error JSON document
     *
     * @param url          - the bad url
     * @param errorMessage - the error
     * @return error url item for the error JSON document
     */
    public static URLPropertiesError createURLPropertiesError(String url, String errorMessage) {
        URLPropertiesError urlPropertiesError = new URLPropertiesError();
        urlPropertiesError.setUrl(url);
        urlPropertiesError.setError(errorMessage);
        return urlPropertiesError;
    }

    /**
     * Builds a JSON formatted string from a valid url property object
     *
     * @param urlPropertiesValid
     * @return JSON string
     */
    protected static String buildValidURLPropertiesJSONString(URLPropertiesValid urlPropertiesValid) {
        if (urlPropertiesValid == null) {
            return "";
        }
        Map<String, String> jsonMap = new LinkedHashMap<>();
        jsonMap.put(URL_KEY, urlPropertiesValid.getUrl());
        jsonMap.put(STATUS_CODE_KEY, urlPropertiesValid.getStatusCode());
        jsonMap.put(HttpHeaders.CONTENT_LENGTH, urlPropertiesValid.getContentLength());
        jsonMap.put(HttpHeaders.DATE, urlPropertiesValid.getDate());
        return parseJSONAndPrettify(jsonMap);
    }

    /**
     * Builds a pretty JSON formatted string from an error url property object.
     *
     * @param urlPropertiesError - bad URL properties object
     * @return JSON string
     */
    protected static String buildErrorURLPropertiesJSONString(URLPropertiesError urlPropertiesError) {
        if (urlPropertiesError == null) {
            return "";
        }
        Map<String, String> jsonMap = new LinkedHashMap<>();
        jsonMap.put(URL_KEY, urlPropertiesError.getUrl());
        jsonMap.put(ERROR_KEY, urlPropertiesError.getError());

        return parseJSONAndPrettify(jsonMap);
    }

    /**
     * Converts a map into a JSON formatted String.
     *
     * @param jsonMap - key/value JSON
     * @return a pretty JSON string
     */
    private static String parseJSONAndPrettify(Map<String, String> jsonMap) {
        String json = null;
        try {
            json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * Print to stdout the URL response info from a list of valid URLs.
     *
     * @param urlPropertiesValidList - valid Url items
     */
    public static void printValidURLJSONDocument(List<URLPropertiesValid> urlPropertiesValidList) {
        for (URLPropertiesValid urlPropertiesValid : urlPropertiesValidList) {
            System.out.println(buildValidURLPropertiesJSONString(urlPropertiesValid));
        }
    }

    /**
     * Print to stderror the URL error info from a list of Bad URLs
     *
     * @param urlPropertiesErrorList Bad Url items
     */
    public static void printErrorURLJSONDocument(List<URLPropertiesError> urlPropertiesErrorList) {
        for (URLPropertiesError urlPropertiesError : urlPropertiesErrorList) {
            System.err.println(buildErrorURLPropertiesJSONString(urlPropertiesError));
        }
    }

    /*
    public static void updateURLPropertiesSummaryMap(String statusCode, Map<String, Integer> summaryMap) {
        if(summaryMap.containsKey(statusCode)) {
            int currentCount = summaryMap.get(statusCode);
            summaryMap.replace(statusCode, currentCount + 1);
        }
        else {
            summaryMap.put(statusCode, 1);
        }
    }
    */
}
