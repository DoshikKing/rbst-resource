package com.rbs.rbstresource.exception;

public class BadAccountAccountReferenceException extends RuntimeException{
    public BadAccountAccountReferenceException(String account) {
        super("Cannot transfer to same account! Account: " + account);
    }
}
