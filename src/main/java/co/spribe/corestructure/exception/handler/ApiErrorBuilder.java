package co.spribe.corestructure.exception.handler;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class ApiErrorBuilder {
    private final boolean isDebugEnabled;

    public ApiErrorBuilder(boolean isDebugEnabled) {
        this.isDebugEnabled = isDebugEnabled;
    }

    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;

    private List<ApiSubError> subErrors;

    public ApiError build(){
        ApiError apiError = new ApiError(status);

        apiError.setMessage(message);
        apiError.setSubErrors(subErrors);

        if(isDebugEnabled){
            LocalDateTime timestamp = LocalDateTime.now();
            apiError.setDebugMessage(debugMessage);
            apiError.setTimestamp(timestamp);
        }

        return apiError;
    }
}
