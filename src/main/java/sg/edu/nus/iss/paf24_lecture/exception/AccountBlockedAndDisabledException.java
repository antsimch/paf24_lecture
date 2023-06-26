package sg.edu.nus.iss.paf24_lecture.exception;

public class AccountBlockedAndDisabledException extends RuntimeException {
    
    public AccountBlockedAndDisabledException() {
        super();
    }

    public AccountBlockedAndDisabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountBlockedAndDisabledException(String message) {
        super(message);
    }

    public AccountBlockedAndDisabledException(Throwable cause) {
        super(cause);
    }
}
