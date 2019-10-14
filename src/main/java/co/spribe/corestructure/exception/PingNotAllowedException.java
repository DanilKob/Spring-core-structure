package co.spribe.corestructure.exception;

public class PingNotAllowedException extends Exception {
    public PingNotAllowedException() {
    }

    public PingNotAllowedException(String message) {
        super(message);
    }

    public PingNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PingNotAllowedException(Throwable cause) {
        super(cause);
    }

    public PingNotAllowedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
