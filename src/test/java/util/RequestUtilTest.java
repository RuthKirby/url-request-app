package util;

import model.DocumentItemError;
import model.DocumentItemValid;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RequestUtilTest {

    private static final String ERROR_MESSAGE = "error";
    private static final String URL = "bad:/url";
    private static final String INDENT = "  ";

    @Before
    public void setUp() {
    }

    @Test
    public void testValidateUrlParamNull() {
        boolean result = RequestUtil.validateUrl(null);
        assertFalse(result);
    }

    @Test
    public void testValidateUrlParamContainSpaces() {
        boolean result = RequestUtil.validateUrl("http:// space.com");
        assertFalse(result);
    }

    @Test
    public void testValidateUrlParamContainInvalidCharacters() {
        boolean result = RequestUtil.validateUrl("https://<character.com");
        assertFalse(result);
    }

    @Test
    public void testValidateUrlParamStartsWithHttp() {
        boolean result = RequestUtil.validateUrl("http://valid.com");
        assertTrue(result);
    }

    @Test
    public void testValidateUrlParamStartsWithHttps() {
        boolean result = RequestUtil.validateUrl("https://valid.com");
        assertTrue(result);
    }

    @Test
    public void testValidateUrlParamDoesNotStartsWithHttpsOrHttp() {
        boolean result = RequestUtil.validateUrl("ftp://nothttp");
        assertFalse(result);
    }

    @Test
    public void testCreateDocumentItemError() {
        DocumentItemError result = RequestUtil.createDocumentItemError("bad:/url", "error");
        assertEquals(URL, result.getUrl());
        assertEquals(ERROR_MESSAGE, result.getError());
    }

    @Test
    public void testCreateDocumentItemErrorNullParams() {
        DocumentItemError result = RequestUtil.createDocumentItemError(null, null);
        assertNull(result.getUrl());
        assertNull(result.getError());

        result = RequestUtil.createDocumentItemError(URL, null);
        assertEquals(URL, result.getUrl());
        assertNull(result.getError());

        result = RequestUtil.createDocumentItemError(null, ERROR_MESSAGE);
        assertEquals(ERROR_MESSAGE, result.getError());
        assertNull(result.getUrl());
    }

    @Test
    public void testBuildValidURLJSONString() {
        DocumentItemValid documentItemValid = new DocumentItemValid();
        documentItemValid.setStatusCode("200");
        documentItemValid.setContentLength("2121");
        documentItemValid.setDate("Date");

        String result = RequestUtil.buildValidURLJSONString(documentItemValid);
        String expectedResult = "{" + System.lineSeparator() +
                INDENT + "\"Url\" : null," + System.lineSeparator() +
                INDENT + "\"Status_Code\" : \"200\"," + System.lineSeparator() +
                INDENT + "\"Content-Length\" : \"2121\"," + System.lineSeparator() +
                INDENT + "\"Date\" : \"Date\"" + System.lineSeparator() +
                "}";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testBuildValidURLJSONStringFieldNull() {
        DocumentItemValid documentItemValid = new DocumentItemValid();
        documentItemValid.setStatusCode(null);
        documentItemValid.setContentLength("2121");
        documentItemValid.setDate(null);

        String result = RequestUtil.buildValidURLJSONString(documentItemValid);
        String expectedResult = "{" + System.lineSeparator() +
                INDENT + "\"Status_Code\" : null," + System.lineSeparator() +
                INDENT + "\"Content-Length\" : \"2121\"," + System.lineSeparator() +
                INDENT + "\"URL\" : null," + System.lineSeparator() +
                INDENT + "\"Date\" : null" + System.lineSeparator() +
                "}";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testBuildValidURLJSONStringParamNull() {
        String result = RequestUtil.buildValidURLJSONString(null);
        assertEquals("", result);
    }

    @Test
    public void testBuildErrorURLJSONString() {
        DocumentItemError documentItemError = new DocumentItemError();
        documentItemError.setUrl(URL);
        documentItemError.setError(ERROR_MESSAGE);

        String result = RequestUtil.buildErrorURLJSONString(documentItemError);

        String expectedResult = "{" + System.lineSeparator() +
                INDENT + "\"Url\" : " + URL + "," + System.lineSeparator() +
                INDENT + "\"Error\" : " + ERROR_MESSAGE + "," + System.lineSeparator() +
                "}";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testBuildErrorURLJSONStringFieldNull() {
        DocumentItemError documentItemError = new DocumentItemError();
        documentItemError.setUrl(null);
        documentItemError.setError(null);

        String result = RequestUtil.buildErrorURLJSONString(documentItemError);

        String expectedResult = "{" + System.lineSeparator() +
                INDENT + "\"Url\" : null," + System.lineSeparator() +
                INDENT + "\"Error\" : null," + System.lineSeparator() +
                "}";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testBuildErrorURLJSONStringParamNull() {
        String result = RequestUtil.buildErrorURLJSONString(null);
        assertEquals("", result);
    }
}