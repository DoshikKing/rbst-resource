package com.rbs.rbstresource.exception;

public class NotEnoughMoneyException extends RuntimeException{
    public NotEnoughMoneyException(String account) {
        super("Not enough money on account! Account: " + account);
    }
}
