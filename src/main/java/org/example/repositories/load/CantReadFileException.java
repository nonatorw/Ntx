package org.example.repositories.load;

public class CantReadFileException extends RuntimeException {
    public CantReadFileException() {
    }

    public CantReadFileException(String message) {
        super(message);
    }

    public CantReadFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public CantReadFileException(Throwable cause) {
        super(cause);
    }

    public CantReadFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
