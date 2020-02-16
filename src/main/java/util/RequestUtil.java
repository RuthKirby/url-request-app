package util;

import model.DocumentItemError;
import model.DocumentItemValid;

import java.util.List;

public class RequestUtil {

    /**
     * Checks whether or not the given URL is valid (that it begins with either http or https and that
     * it does not contain characters invalid for a URL.
     *
     * @param url - web address to be validated
     * @return validation info for the url
     */
    public static boolean validateUrl(String url) {
        return false;
    }

    /**
     * Builds a JSON formatted string from a list of valid url items
     *
     * @param documentItemValidList
     * @return JSON string
     */
    public static String buildValidURLJSONDocument(List<DocumentItemValid> documentItemValidList) {
        return null;
    }

    /**
     * Builds a JSON formatted string from a list of error url items
     *
     * @param documentItemErrorList
     * @return JSON string
     */
    public static String buildErrorURLJSONDocument(List<DocumentItemError> documentItemErrorList) {
        return null;
    }
}
