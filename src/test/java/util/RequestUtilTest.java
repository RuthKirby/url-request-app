package util;

import model.DocumentItemValid;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RequestUtilTest {

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
}