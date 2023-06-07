package org.example.repositories.write;

public class CantWriteFileException extends RuntimeException {
    public CantWriteFileException() {
    }

    public CantWriteFileException(String message) {
        super(message);
    }

    public CantWriteFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public CantWriteFileException(Throwable cause) {
        super(cause);
    }

    public CantWriteFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
