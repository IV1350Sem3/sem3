package se.kth.iv1350.pos.integration;

/**
 * Thrown when the database cannot be called.
 */
public class DatabaseFailureException extends RuntimeException {

    /**
     * Creates a new instance with a message describing the database failure.
     *
     * @param message A description of the failure.
     */
    public DatabaseFailureException(String message) {
        super(message);
    }
}
