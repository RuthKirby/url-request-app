package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.DocumentItemError;
import model.DocumentItemValid;
import org.apache.commons.validator.UrlValidator;
import org.apache.http.HttpHeaders;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RequestUtil {

    private static final String URL_KEY = "Url";
    private static final String STATUS_CODE_KEY = "Status_Code";
    private static final String ERROR_KEY = "Error";

    /**
     * Checks whether or not the given URL is valid (that it begins with either http or https and that
     * it does not contain characters invalid for a URL.
     *
     * @param url - web address to be validated
     * @return validation info for the url
     */
    public static boolean validateUrl(String url) {
        String[] schemes = {"http", "https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        boolean isValid = urlValidator.isValid(url);
        return isValid;
    }

    /**
     * Creates a url item for the error JSON document
     *
     * @param url          - the bad url
     * @param errorMessage - the error
     * @return error url item for the error JSON document
     */
    public static DocumentItemError createDocumentItemError(String url, String errorMessage) {
        DocumentItemError documentItemError = new DocumentItemError();
        documentItemError.setUrl(url);
        documentItemError.setError(errorMessage);
        return documentItemError;
    }

    /**
     * Builds a JSON formatted string from a valid url item
     *
     * @param documentItemValid
     * @return JSON string
     */
    public static String buildValidURLJSONString(DocumentItemValid documentItemValid) {
        if (documentItemValid == null) {
            return "";
        }
        Map<String, String> jsonMap = new LinkedHashMap<>();
        jsonMap.put(URL_KEY, documentItemValid.getUrl());
        jsonMap.put(STATUS_CODE_KEY, documentItemValid.getStatusCode());
        jsonMap.put(HttpHeaders.CONTENT_LENGTH, documentItemValid.getContentLength());
        jsonMap.put(HttpHeaders.DATE, documentItemValid.getDate());
        String json = null;
        try {
            json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * Builds a JSON formatted string from an error url item
     *
     * @param documentItemError
     * @return JSON string
     */
    public static String buildErrorURLJSONString(DocumentItemError documentItemError) {
        return null;
    }

    /**
     * Print the URL reponse info from a list of valid URLs.
     *
     * @param documentItemValidList - valid Url document item
     */
    public void printValidURLJSONDocument(List<DocumentItemValid> documentItemValidList) {

    }

    /**
     * Print the URL error info from a list of Bad URLs
     *
     * @param documentItemValidList Bad Url document item
     */
    public void printErrorURLJSONDocument(List<DocumentItemValid> documentItemValidList) {

    }
}
