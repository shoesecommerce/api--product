package com.shoesclick.api.product.handler;


import com.shoesclick.api.product.entity.Status;
import com.shoesclick.api.product.exception.BusinessException;
import com.shoesclick.api.product.exception.ElementNotFoundException;
import com.shoesclick.api.product.exception.ListNotFoundException;
import com.shoesclick.api.product.openapi.controller.ProductApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;




@ControllerAdvice(assignableTypes = {ProductApi.class })
public class ControllerErrorHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LogManager.getLogger(ControllerErrorHandler.class);

    @ExceptionHandler(ElementNotFoundException.class)
    public final ResponseEntity<Status> handleElementNotFound(ElementNotFoundException exception, WebRequest request) {
    	var status = new Status(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    	LOGGER.error(exception.getMessage());
        return new ResponseEntity<Status>(status,HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(ListNotFoundException.class)
    public final ResponseEntity<Status> handleListNotFound(ListNotFoundException exception, WebRequest request) {
        var status = new Status(HttpStatus.NO_CONTENT.value(), exception.getMessage());
        LOGGER.error(exception.getMessage());
        return new ResponseEntity<Status>(status,HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<Status> handleBusinessError(BusinessException exception, WebRequest request) {
        var status = new Status(HttpStatus.EXPECTATION_FAILED.value(), exception.getMessage());
        LOGGER.error(exception.getMessage());
        return new ResponseEntity<Status>(status,HttpStatus.EXPECTATION_FAILED);
    }

}
