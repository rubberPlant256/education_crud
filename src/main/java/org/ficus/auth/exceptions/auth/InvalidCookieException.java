package org.ficus.auth.exceptions.auth;

public class InvalidCookieException extends RuntimeException {
    public InvalidCookieException() {
        super("Invalid Cookie");
    }
}
