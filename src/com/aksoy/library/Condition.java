package com.aksoy.library;

public enum Condition {
    AS_NEW("Newly bought,almost never used.", 25),
    FINE("It has been used but looks like as new.", 20),
    GOOD("It is understandable that the book is used a lot but it still looks good.",15),
    FAIR("The look starts to depreciate, one should be careful when using it.", 10),
    POOR("The books needs extra care when used, a new one should be bought.", 5),
    UNKNOWN("If we cannot decide", 25);

    private final String description;
    private final double price;
    Condition(String description, double price){
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
