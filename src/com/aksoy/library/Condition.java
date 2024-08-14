package com.aksoy.library;

public enum Condition {
    AS_NEW("Newly bought,almost never used."),
    FINE("It has been used but looks like as new."),
    GOOD("It is understandable that the book is used a lot but it still looks good."),
    FAIR("The look starts to depreciate, one should be careful when using it."),
    POOR("The books needs extra care when used, a new one should be bought.");

    private final String description;
    Condition(String description){
        this.description = description;
    }
}
