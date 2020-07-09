package main;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionsHandler extends ResponseEntityExceptionHandler {
//    @ExceptionHandler(value = {Exception.class})
//    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
//        return new ResponseEntity<>(
//                ex,
//                new HttpHeaders(),
//                HttpStatus.INTERNAL_SERVER_ERROR
//        );
//    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

        return new ResponseEntity<Object>(ex, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
