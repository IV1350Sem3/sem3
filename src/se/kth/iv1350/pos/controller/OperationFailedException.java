package se.kth.iv1350.pos.controller;

/**
 * Thrown when an operation fails due to technical issues.
 */
public class OperationFailedException extends Exception {

    /**
     * Creates a new instance with a message and the cause of the exception.
     *
     * @param message Description of the error.
     * @param cause The exception that caused this exception.
     */
    public OperationFailedException(String message, Exception cause) {
        super(message, cause);
    }
}
