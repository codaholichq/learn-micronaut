package microstar;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.http.server.exceptions.response.ErrorContext;
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Produces
@Singleton
@Requires(classes = { ResourceNotFoundException.class})
@RequiredArgsConstructor
public class ResourceNotFoundExceptionHandler implements ExceptionHandler<ResourceNotFoundException, HttpResponse<?>> {
    private final ErrorResponseProcessor<?> errorResponseProcessor;
    @Override
    public HttpResponse<?> handle(HttpRequest request, ResourceNotFoundException exception) {
        return errorResponseProcessor.processResponse(
                ErrorContext.builder(request)
                        .cause(exception)
                        .errorMessage(exception.getMessage())
                        .build(),
                HttpResponse.notFound()
        );
    }
}