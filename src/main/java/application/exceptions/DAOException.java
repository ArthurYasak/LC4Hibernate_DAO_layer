package application.exceptions;

public class DAOException extends Exception {
    public DAOException(String message, Exception e) {
        super(message, e);
    }
}
