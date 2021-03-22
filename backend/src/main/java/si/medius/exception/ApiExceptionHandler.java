package si.medius.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {HttpClientErrorException.class})
    public ResponseEntity<String> handleHttpClientException(HttpClientErrorException exception) {
        return new ResponseEntity<>(exception.getMessage(), exception.getResponseHeaders(), exception.getStatusCode());
    }
}
