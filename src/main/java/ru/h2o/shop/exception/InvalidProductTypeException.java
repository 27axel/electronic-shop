package ru.h2o.shop.exception;

public class InvalidProductTypeException extends RuntimeException {
    public InvalidProductTypeException(String type) {
        super("Invalid product type: " + type);
    }
}
