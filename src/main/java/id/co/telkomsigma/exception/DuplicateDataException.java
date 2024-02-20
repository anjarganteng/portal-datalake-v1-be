package id.co.telkomsigma.exception;

/**
 * @author satiya
 */
public class DuplicateDataException extends RuntimeException {

    private static final long serialVersionUID = -1796340529192766399L;

    public DuplicateDataException() {
    }

    public DuplicateDataException(String message) {
        super(message);
    }
}
