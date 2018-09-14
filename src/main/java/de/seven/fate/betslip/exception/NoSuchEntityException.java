package de.seven.fate.betslip.exception;

public class NoSuchEntityException extends RuntimeException {

    public NoSuchEntityException(String message, String entityName) {
        super(String.format(message, entityName));
    }
}
