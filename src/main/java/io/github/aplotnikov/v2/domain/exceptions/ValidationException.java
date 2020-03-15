package io.github.aplotnikov.v2.domain.exceptions;

public class ValidationException extends RuntimeException {

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
