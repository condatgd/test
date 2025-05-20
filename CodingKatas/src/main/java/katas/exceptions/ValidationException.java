package katas.exceptions;

public class ValidationException extends Exception {
    public ValidationException(String reason) {
        super("Validation failed. Reason: " + reason);
    }
}
