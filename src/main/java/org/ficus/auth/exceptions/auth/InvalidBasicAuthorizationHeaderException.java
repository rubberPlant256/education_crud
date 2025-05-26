package org.ficus.auth.exceptions.auth;

public class InvalidBasicAuthorizationHeaderException extends RuntimeException {
    public InvalidBasicAuthorizationHeaderException() {
        super("Invalid Basic Authorization Header");
    }
}
