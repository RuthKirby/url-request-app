import model.URLPropertiesValid;
import org.apache.http.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RequestSenderTest {

    @Mock
    HttpResponse httpResponse;

    @Mock
    StatusLine statusLine;

    @Mock
    HttpEntity httpEntity;

    @Mock
    Header dateHeader;

    private static final String DATE = new Date().toString();
    private static final String URL = "http://url.com";
    private static final String URL_S = "https://url.com";

    @Before
    public void setup() {
    }

    @Test
    public void testGetUrlGetRequestInfo() {
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(404);
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        when(httpEntity.getContentLength()).thenReturn(2010L);
        when(httpResponse.getFirstHeader(eq(HttpHeaders.DATE))).thenReturn(dateHeader);
        when(dateHeader.getValue()).thenReturn(DATE);

        URLPropertiesValid result = RequestSender.getUrlGetRequestInfo(URL, httpResponse);

        assertEquals("404", result.getStatusCode());
        assertEquals("http://url.com", result.getUrl());
        assertEquals(String.valueOf(2010L), result.getContentLength());
        assertEquals(DATE, result.getDate());
    }

    @Test
    public void testGetUrlGetRequestInfoContentLengthOverflow() {
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        when(httpEntity.getContentLength()).thenReturn(Long.MAX_VALUE + 1);
        when(httpResponse.getFirstHeader(eq(HttpHeaders.DATE))).thenReturn(dateHeader);
        when(dateHeader.getValue()).thenReturn(DATE);

        URLPropertiesValid result = RequestSender.getUrlGetRequestInfo(URL_S, httpResponse);

        assertEquals("200", result.getStatusCode());
        assertEquals(URL_S, result.getUrl());
        assertEquals(String.valueOf(Long.MIN_VALUE), result.getContentLength());
        assertEquals(DATE, result.getDate());
    }

    @Test
    public void testGetUrlGetRequestInfoHTTPResponseParamNull() {
        URLPropertiesValid result = RequestSender.getUrlGetRequestInfo(URL_S, null);

        assertNull(result.getStatusCode());
        assertNull(result.getUrl());
        assertNull(result.getContentLength());
        assertNull(result.getDate());
    }

    @Test
    public void testGetUrlGetRequestInfoAllParamsNull() {
        URLPropertiesValid result = RequestSender.getUrlGetRequestInfo(null, null);

        assertNull(result.getStatusCode());
        assertNull(result.getUrl());
        assertNull(result.getContentLength());
        assertNull(result.getDate());
    }

    @Test
    public void testGetUrlGetRequestInfoURLParamNull() {
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        when(httpEntity.getContentLength()).thenReturn(Long.MAX_VALUE + 1);
        when(httpResponse.getFirstHeader(eq(HttpHeaders.DATE))).thenReturn(dateHeader);
        when(dateHeader.getValue()).thenReturn(DATE);

        URLPropertiesValid result = RequestSender.getUrlGetRequestInfo(null, httpResponse);

        assertEquals("200", result.getStatusCode());
        assertNull(result.getUrl());
        assertEquals(String.valueOf(Long.MIN_VALUE), result.getContentLength());
        assertEquals(DATE, result.getDate());
    }

    @Test
    public void testGetUrlGetRequestInfoHTTPResponseReturnsNullStatusLine() {
        when(httpResponse.getStatusLine()).thenReturn(null);
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        when(httpEntity.getContentLength()).thenReturn(1L);
        when(httpResponse.getFirstHeader(eq(HttpHeaders.DATE))).thenReturn(dateHeader);
        when(dateHeader.getValue()).thenReturn(DATE);

        URLPropertiesValid result = RequestSender.getUrlGetRequestInfo(URL, httpResponse);

        assertNull(result.getStatusCode());
        assertEquals(URL, result.getUrl());
        assertEquals(String.valueOf(1), result.getContentLength());
        assertEquals(DATE, result.getDate());
    }

    @Test
    public void testGetUrlGetRequestInfoHTTPResponseReturnsNullHTTPEntity() {
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(418);
        when(httpResponse.getEntity()).thenReturn(null);
        when(httpResponse.getFirstHeader(eq(HttpHeaders.DATE))).thenReturn(dateHeader);
        when(dateHeader.getValue()).thenReturn(DATE);

        URLPropertiesValid result = RequestSender.getUrlGetRequestInfo(URL, httpResponse);

        assertEquals("418", result.getStatusCode());
        assertEquals(URL, result.getUrl());
        assertNull(result.getContentLength());
        assertEquals(DATE, result.getDate());
    }

    @Test
    public void testGetUrlGetRequestInfoHTTPResponseReturnsNullHeader() {
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(522);
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        when(httpEntity.getContentLength()).thenReturn(1L);
        when(httpResponse.getFirstHeader(eq(HttpHeaders.DATE))).thenReturn(null);

        URLPropertiesValid result = RequestSender.getUrlGetRequestInfo(URL, httpResponse);

        assertEquals("522", result.getStatusCode());
        assertEquals(URL, result.getUrl());
        assertEquals(String.valueOf(1), result.getContentLength());
        assertNull(result.getDate());
    }
}