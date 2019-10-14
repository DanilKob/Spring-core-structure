package co.spribe.corestructure.exception;

import co.spribe.corestructure.exception.handler.ApiError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {
    private ApiError apiError;

}
