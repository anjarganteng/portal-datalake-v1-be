package id.co.telkomsigma.exception;

/**
 * @author satiya
 */
public class PrimaryKeyNullException extends RuntimeException {

    private static final long serialVersionUID = 3977791160113435491L;

    public PrimaryKeyNullException() {
    }

    public PrimaryKeyNullException(String message) {
        super(message);
    }
}
