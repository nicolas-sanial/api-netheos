package sanial.netheos.demoapi.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sanial.netheos.demoapi.api.dto.ErrorPayload;
import sanial.netheos.demoapi.core.exception.ApiException;

@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException ex){
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(new ErrorPayload(ex.getMessage()));
    }

    @Override
    public ResponseEntity<Object> handleExceptionInternal(Exception ex, Object obj, HttpHeaders headers, HttpStatus status, WebRequest request){
        if(status == HttpStatus.INTERNAL_SERVER_ERROR){
            return buildReturnedPayload(ex, headers, status, "Internal Server Error");
        }else{
            return buildReturnedPayload(ex, headers, status, ex.getMessage());
        }
    }

    private ResponseEntity<Object> buildReturnedPayload(Exception ex, HttpHeaders headers, HttpStatus status, String msg){
        return ResponseEntity.status(status).headers(headers).contentType(MediaType.APPLICATION_JSON).body(new ErrorPayload(msg));
    }
}
