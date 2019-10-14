package co.spribe.corestructure.exception.handler.impl;

import co.spribe.corestructure.exception.ApiErrorMessages;
import co.spribe.corestructure.exception.handler.ApiError;
import co.spribe.corestructure.exception.handler.ApiErrorBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class BadValidationHandler extends ResponseEntityExceptionHandler {

    private ApiErrorBuilderFactory apiErrorBuilderFactory;

    @Autowired
    public void setApiErrorBuilderFactory(ApiErrorBuilderFactory apiErrorBuilderFactory) {
        this.apiErrorBuilderFactory = apiErrorBuilderFactory;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        return handleBadRequest(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        return handleBadRequest(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleBadRequest(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleBadRequest(ex, headers, status, request);
    }


    private ResponseEntity<Object> handleBadRequest(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = apiErrorBuilderFactory.builder()
                .setStatus(HttpStatus.BAD_REQUEST)
                .setMessage(ApiErrorMessages.VALIDATION_ERROR)
                .setDebugMessage(ex.getLocalizedMessage())
                .build();

        return handleExceptionInternal(
                ex, apiError, headers, apiError.getStatus(), request);
    }
}
