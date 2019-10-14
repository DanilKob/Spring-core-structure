package co.spribe.corestructure.exception.handler.impl;

import co.spribe.corestructure.exception.ApiErrorMessages;
import co.spribe.corestructure.exception.CustomException;
import co.spribe.corestructure.exception.handler.ApiError;
import co.spribe.corestructure.exception.handler.ApiErrorBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private ApiErrorBuilderFactory apiErrorBuilderFactory;

    @Autowired
    public void setApiErrorBuilderFactory(ApiErrorBuilderFactory apiErrorBuilderFactory) {
        this.apiErrorBuilderFactory = apiErrorBuilderFactory;
    }

    @ExceptionHandler({RuntimeException.class, Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) {

        ApiError apiError = apiErrorBuilderFactory.builder()
                .setStatus(HttpStatus.NOT_FOUND)
                .setMessage(ApiErrorMessages.INTERNAL_SERVER_ERROR)
                .setDebugMessage(ex.getMessage())
                .build();

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<Object> handleException(CustomException ex) {

        System.out.println("Handle custom exception");

        ApiError apiError = ex.getApiError();
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError){
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
