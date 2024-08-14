package com.aksoy.person.member;

public enum UserType {
    VIP("Number of books that can be purchased is doubled as well as the return time."),
    STUDENT("There will be a %25 discount to all students on all purchases."),
    STANDARD("There is no additional advantage.");

    public final String description;

    UserType(String description){
        this.description =description;
    }

}
