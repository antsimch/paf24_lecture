package sg.edu.nus.iss.paf24_lecture.exception;

public class AccountNotActiveException extends RuntimeException {
    
    public AccountNotActiveException() {
        super();
    }

    public AccountNotActiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountNotActiveException(String message) {
        super(message);
    }

    public AccountNotActiveException(Throwable cause) {
        super(cause);
    }
}
