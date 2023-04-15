package com.rbs.rbstresource.exception;

public class BadCardCardReferenceException extends RuntimeException{
    public BadCardCardReferenceException(String card) {
        super("Cannot transfer to same card! Card: " + card);
    }
}
