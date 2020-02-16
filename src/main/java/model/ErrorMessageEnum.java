package model;

public enum ErrorMessageEnum {
    INVALID_URL("URL is not valid."),
    TIMEOUT("Timeout"),
    EXCEPTION("Exception occurred.");

    private String message;

    ErrorMessageEnum(String message) {
        this.message = message;
    }
}
