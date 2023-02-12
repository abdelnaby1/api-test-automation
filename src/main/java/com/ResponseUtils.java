package com;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseUtils {
    public static String getHeader(CloseableHttpResponse response, String headerName) {
        Header[] headers = response.getAllHeaders();
        List<Header> httpHeaders = Arrays.asList(headers);
        String returnHeader = "";
        for (Header header : httpHeaders) {
            if (headerName.equalsIgnoreCase(header.getName())) {
                returnHeader = header.getValue();
            }
        }
        if (returnHeader.isEmpty()) {
            throw new RuntimeException("Don't find the header " + headerName);
        }
        return returnHeader;
    }

    public static String getHeader2(CloseableHttpResponse response, String headerName) {
        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());

        Header matchedHeader = httpHeaders.stream()
                .filter(header -> headerName.equalsIgnoreCase(header.getName()))
                .findFirst().orElseThrow(() -> new RuntimeException("Don't find the header " + headerName));
        return matchedHeader.getValue();
    }

    public static Boolean headerIsPresent(CloseableHttpResponse response, String headerName) {
        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());

        return httpHeaders.stream()
                .anyMatch(header -> headerName.equalsIgnoreCase(header.getName()));
    }

    public static <T> T unmarshaall(CloseableHttpResponse response, Class<T> clazz)
            throws ParseException, IOException {
        String jsonBody = EntityUtils.toString(response.getEntity());

        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonBody, clazz);
    }
}
