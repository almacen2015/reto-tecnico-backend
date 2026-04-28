package backend.msaccount.domain.exception;

public class ClientInactiveException extends RuntimeException {
    public ClientInactiveException(String message) {
        super(message);
    }
}
