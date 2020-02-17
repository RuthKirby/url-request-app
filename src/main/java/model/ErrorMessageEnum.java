package model;

public enum ErrorMessageEnum {
    INVALID_URL("URL is not valid."),
    EXCEPTION("Exception occurred.");

    private String message;

    ErrorMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
