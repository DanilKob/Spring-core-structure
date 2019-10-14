package co.spribe.corestructure.exception.handler;

import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;

public interface ConstraintViolationApiErrorBuilder {
    ApiError build(ConstraintViolationException ex, WebRequest request);
}
