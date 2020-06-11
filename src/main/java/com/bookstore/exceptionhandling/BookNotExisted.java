package com.bookstore.exceptionhandling;

public class BookNotExisted extends Exception {
    private String message;
    private String details;
    public BookNotExisted(String message, String details){
        this.message = message;
        this.details = details;
    }
}
