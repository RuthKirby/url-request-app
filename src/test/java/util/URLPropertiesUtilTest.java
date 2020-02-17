package util;

import model.URLPropertiesError;
import model.URLPropertiesValid;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class URLPropertiesUtilTest {

    private static final String ERROR_MESSAGE = "error";
    private static final String URL = "bad:/url";
    private static final String INDENT = "  ";

    @Before
    public void setUp() {
    }

    @Test
    public void testValidateUrlParamNull() {
        boolean result = URLPropertiesUtil.validateUrl(null);
        assertFalse(result);
    }

    @Test
    public void testValidateUrlParamContainSpaces() {
        boolean result = URLPropertiesUtil.validateUrl("http:// space.com");
        assertFalse(result);
    }

    @Test
    public void testValidateUrlParamContainInvalidCharacters() {
        boolean result = URLPropertiesUtil.validateUrl("https://<character.com");
        assertFalse(result);
    }

    @Test
    public void testValidateUrlParamStartsWithHttp() {
        boolean result = URLPropertiesUtil.validateUrl("http://valid.com");
        assertTrue(result);
    }

    @Test
    public void testValidateUrlParamStartsWithHttps() {
        boolean result = URLPropertiesUtil.validateUrl("https://valid.com");
        assertTrue(result);
    }

    @Test
    public void testValidateUrlParamDoesNotStartsWithHttpsOrHttp() {
        boolean result = URLPropertiesUtil.validateUrl("ftp://nothttp");
        assertFalse(result);
    }

    @Test
    public void testCreateDocumentItemError() {
        URLPropertiesError result = URLPropertiesUtil.createURLPropertiesError("bad:/url", "error");
        assertEquals(URL, result.getUrl());
        assertEquals(ERROR_MESSAGE, result.getError());
    }

    @Test
    public void testCreateDocumentItemErrorNullParams() {
        URLPropertiesError result = URLPropertiesUtil.createURLPropertiesError(null, null);
        assertNull(result.getUrl());
        assertNull(result.getError());

        result = URLPropertiesUtil.createURLPropertiesError(URL, null);
        assertEquals(URL, result.getUrl());
        assertNull(result.getError());

        result = URLPropertiesUtil.createURLPropertiesError(null, ERROR_MESSAGE);
        assertEquals(ERROR_MESSAGE, result.getError());
        assertNull(result.getUrl());
    }

    @Test
    public void testBuildValidURLJSONString() {
        URLPropertiesValid urlPropertiesValid = new URLPropertiesValid();
        urlPropertiesValid.setStatusCode("200");
        urlPropertiesValid.setContentLength("2121");
        urlPropertiesValid.setDate("Date");

        String result = URLPropertiesUtil.buildValidURLPropertiesJSONString(urlPropertiesValid);
        String expectedResult = "{" + System.lineSeparator() +
                INDENT + "\"Url\" : null," + System.lineSeparator() +
                INDENT + "\"Status-Code\" : \"200\"," + System.lineSeparator() +
                INDENT + "\"Content-Length\" : \"2121\"," + System.lineSeparator() +
                INDENT + "\"Date\" : \"Date\"" + System.lineSeparator() +
                "}";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testBuildValidURLJSONStringFieldNull() {
        URLPropertiesValid urlPropertiesValid = new URLPropertiesValid();
        urlPropertiesValid.setStatusCode(null);
        urlPropertiesValid.setContentLength("2121");
        urlPropertiesValid.setDate(null);

        String result = URLPropertiesUtil.buildValidURLPropertiesJSONString(urlPropertiesValid);
        String expectedResult = "{" + System.lineSeparator() +
                INDENT + "\"Url\" : null," + System.lineSeparator() +
                INDENT + "\"Status-Code\" : null," + System.lineSeparator() +
                INDENT + "\"Content-Length\" : \"2121\"," + System.lineSeparator() +
                INDENT + "\"Date\" : null" + System.lineSeparator() +
                "}";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testBuildValidURLJSONStringParamNull() {
        String result = URLPropertiesUtil.buildValidURLPropertiesJSONString(null);
        assertEquals("", result);
    }

    @Test
    public void testBuildErrorURLJSONString() {
        URLPropertiesError urlPropertiesError = new URLPropertiesError();
        urlPropertiesError.setUrl(URL);
        urlPropertiesError.setError(ERROR_MESSAGE);

        String result = URLPropertiesUtil.buildErrorURLPropertiesJSONString(urlPropertiesError);

        String expectedResult = "{" + System.lineSeparator() +
                INDENT + "\"Url\" : " + "\"" + URL + "\"" + "," + System.lineSeparator() +
                INDENT + "\"Error\" : " + "\"" + ERROR_MESSAGE + "\"" + System.lineSeparator() +
                "}";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testBuildErrorURLJSONStringFieldNull() {
        URLPropertiesError urlPropertiesError = new URLPropertiesError();
        urlPropertiesError.setUrl(null);
        urlPropertiesError.setError(null);

        String result = URLPropertiesUtil.buildErrorURLPropertiesJSONString(urlPropertiesError);

        String expectedResult = "{" + System.lineSeparator() +
                INDENT + "\"Url\" : null," + System.lineSeparator() +
                INDENT + "\"Error\" : null" + System.lineSeparator() +
                "}";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testBuildErrorURLJSONStringParamNull() {
        String result = URLPropertiesUtil.buildErrorURLPropertiesJSONString(null);
        assertEquals("", result);
    }
}