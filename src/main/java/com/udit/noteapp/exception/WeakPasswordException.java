package com.udit.noteapp.exception;

public class WeakPasswordException extends RuntimeException {
    public WeakPasswordException(String message) {
        super(message);
    }
    public WeakPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
