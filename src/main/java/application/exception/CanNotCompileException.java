package application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CanNotCompileException extends RuntimeException {
    public CanNotCompileException(String message) {
        super(message);
    }

    public CanNotCompileException(String message, Throwable cause) {
        super(message, cause);
    }
}
