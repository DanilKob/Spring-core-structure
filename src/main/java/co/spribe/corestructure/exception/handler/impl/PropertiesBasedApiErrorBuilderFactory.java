package co.spribe.corestructure.exception.handler.impl;

import co.spribe.corestructure.exception.handler.ApiErrorBuilder;
import co.spribe.corestructure.exception.handler.ApiErrorBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesBasedApiErrorBuilderFactory implements ApiErrorBuilderFactory {

    @Value(value = "${api-error.debug-enabled}")
    private boolean debugMessageEnabled;

    @Override
    public ApiErrorBuilder builder() {
        ApiErrorBuilder apiErrorBuilder = new ApiErrorBuilder(debugMessageEnabled);
        return apiErrorBuilder;
    }
}
