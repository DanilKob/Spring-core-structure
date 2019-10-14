package co.spribe.corestructure.exception.handler;

import co.spribe.corestructure.exception.ApiErrorMessages;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Component
public class ConstraintViolationExceptionHandler {
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {

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
