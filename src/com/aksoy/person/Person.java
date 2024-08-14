package com.aksoy.person;

public abstract class Person {
    private String name;
    public abstract void whoYouAre();

    public Person(String name){
        this.name = name;
    }
}
