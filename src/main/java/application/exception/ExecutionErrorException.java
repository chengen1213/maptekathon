package application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExecutionErrorException extends RuntimeException {
    public ExecutionErrorException(String message) {
        super(message);
    }

    public ExecutionErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
