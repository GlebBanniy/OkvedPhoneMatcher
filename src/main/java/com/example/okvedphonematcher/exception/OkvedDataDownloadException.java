package com.example.okvedphonematcher.exception;

public class OkvedDataDownloadException extends RuntimeException {
    public OkvedDataDownloadException(String message) {
        super(message);
    }

    public OkvedDataDownloadException(Throwable cause) {
        super(cause);
    }
}
