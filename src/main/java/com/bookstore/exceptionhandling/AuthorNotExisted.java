package com.bookstore.exceptionhandling;

public class AuthorNotExisted extends Exception {
    private String message;
    private String details;
    AuthorNotExisted(String message, String details){
        this.message = message;
        this.details = details;
    }
}
