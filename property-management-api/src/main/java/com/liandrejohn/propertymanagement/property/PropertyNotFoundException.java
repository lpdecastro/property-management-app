package com.liandrejohn.propertymanagement.property;

public class PropertyNotFoundException extends RuntimeException {
    
    public PropertyNotFoundException(String message) {
        super(message);
    }
}
