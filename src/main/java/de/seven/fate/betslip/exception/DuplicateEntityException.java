package de.seven.fate.betslip.exception;

public class DuplicateEntityException extends RuntimeException {

    public DuplicateEntityException(String message, String entityName) {
        super(String.format(message, entityName));
    }
}
