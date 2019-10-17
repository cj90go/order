package exception;

public class OrderRunTimeException extends RuntimeException {

    public OrderRunTimeException(Throwable cause) {
        super(cause);
    }

    public OrderRunTimeException(String message) {
        super(message);
    }

    public OrderRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
