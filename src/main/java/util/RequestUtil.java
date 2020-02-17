package util;

import model.DocumentItemError;
import model.DocumentItemValid;
import org.apache.commons.validator.UrlValidator;

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
