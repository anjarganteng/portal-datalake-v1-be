package id.co.telkomsigma.exception;

/**
 * @author satiya
 */
public class ErrorUploadException extends RuntimeException {

    private static final long serialVersionUID = -8704098103643697807L;

    public ErrorUploadException() {
    }

    public ErrorUploadException(String message) {
        super(message);
    }
}
