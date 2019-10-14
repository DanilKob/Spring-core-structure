package co.spribe.corestructure.exception.handler.impl;

import co.spribe.corestructure.exception.ApiErrorMessages;
import co.spribe.corestructure.exception.handler.ApiError;
import co.spribe.corestructure.exception.handler.ApiSubError;
import co.spribe.corestructure.exception.handler.ApiValidationError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ConstraintViolationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request
    ) {

        // TODO REMOVE
        ex.printStackTrace();

        ApiError apiError = buildApiError(ex, request);
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    private ApiError buildApiError(ConstraintViolationException ex, WebRequest request) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<ApiSubError> apiValidationErrors = new LinkedList<>();

        Object invalidValue;
        String field;
        String message;
        ApiValidationError apiValidationError;
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            invalidValue = constraintViolation.getInvalidValue();
            field = constraintViolation.getPropertyPath().toString();
            message = constraintViolation.getMessage();
            apiValidationError = new ApiValidationError();
            apiValidationError.setField(field)
                    .setRejectedValue(invalidValue)
                    .setMessage(message);
            apiValidationErrors.add(apiValidationError);
        }

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST, ApiErrorMessages.VALIDATION_ERROR,
                ex.getLocalizedMessage(), apiValidationErrors);

        return apiError;
    }
}
