package com.rbs.rbstresource.exception;

public class NotEnoughMoneyException extends RuntimeException{
    public NotEnoughMoneyException(String errorMessage, Throwable err) {
        super("Not enough money on account! Error: " + errorMessage, err);
    }
}
