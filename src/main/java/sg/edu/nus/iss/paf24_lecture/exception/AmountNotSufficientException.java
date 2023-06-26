package sg.edu.nus.iss.paf24_lecture.exception;

public class AmountNotSufficientException extends RuntimeException {
    
    public AmountNotSufficientException() {
        super();
    }

    public AmountNotSufficientException(String message, Throwable cause) {
        super(message, cause);
    }

    public AmountNotSufficientException(String message) {
        super(message);
    }

    public AmountNotSufficientException(Throwable cause) {
        super(cause);
    }
}
