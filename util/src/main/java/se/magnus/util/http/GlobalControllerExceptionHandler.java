package se.magnus.util.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    public HttpErrorInfo handleNotFoundException(ServerHttpRequest request, Exception exception) {
		return createHttpErrorInfo(NOT_FOUND, request, exception);
    	
    }

	private HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus, ServerHttpRequest request, Exception exception) {
		final String path = request.getPath().pathWithinApplication().value();
	    final String message = exception.getMessage();
	    
        LOG.debug("Returning HTTP status: {} for path: {}, message: {}", httpStatus, path, message);

		return new HttpErrorInfo(httpStatus, path, message);
	}
}
