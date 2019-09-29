package application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExecutionErrorException extends RuntimeException {
    public ExecutionErrorException(String message) {
        super(message);
    }

    public ExecutionErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
