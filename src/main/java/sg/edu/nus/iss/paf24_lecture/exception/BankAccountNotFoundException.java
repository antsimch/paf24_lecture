package sg.edu.nus.iss.paf24_lecture.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BankAccountNotFoundException extends RuntimeException {
    
    public BankAccountNotFoundException() {
        super();
    }

    public BankAccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankAccountNotFoundException(String message) {
        super(message);
    }

    public BankAccountNotFoundException(Throwable cause) {
        super(cause);
    }
}
