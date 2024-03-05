package com.shoesclick.api.order.handler;

import com.shoesclick.api.order.exception.ElementNotFoundException;
import com.shoesclick.api.order.exception.ListNotFoundException;
import com.shoesclick.api.order.exception.ResourceException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class OpenFeingHandler implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        String requestUrl = response.request().url();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());
        Response.Body responseBody = response.body();
        switch (responseStatus) {
            case NO_CONTENT -> {
                return new ListNotFoundException(getMessage(requestUrl, responseBody));
            }
            case NOT_FOUND -> {
                return new ElementNotFoundException(getMessage(requestUrl, responseBody));
            }
            default -> {
                return new ResourceException(getMessage(requestUrl, responseBody));
            }
        }
    }

    private static String getMessage(String requestUrl, Response.Body responseBody) {
        return new StringBuilder("Error in URL: ").append(requestUrl).append(responseBody).toString();
    }
}
