package com.rbs.rbstresource.exception;

public class BadCardAccountReferenceException extends RuntimeException{
    public BadCardAccountReferenceException(String account, String card) {
        super("Card is tight to account! Cannot transfer to same account! Account: " + account + " Card: " + card);
    }
}
