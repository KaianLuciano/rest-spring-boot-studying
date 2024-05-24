package br.com.rest.spring.exception.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class Exceptions {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String ex) {
            super(ex);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class RequiredObjectIsNullException extends RuntimeException {
        public RequiredObjectIsNullException(String ex) {
            super(ex);
        }

        public RequiredObjectIsNullException() {
            super("It not possible to process the request because the required object is null.");
        }
    }
}
