package org.ficus.auth.exceptions.data;

public class ExistingUserWithThatUsernameException extends RuntimeException {
    public ExistingUserWithThatUsernameException() {
        super("A user with that username already exists");
    }
}
