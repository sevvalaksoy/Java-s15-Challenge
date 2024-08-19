package com.aksoy.person;

public abstract class Person {
    private String name;
    public abstract void whoYouAre();
    public Person(){

    }
    public Person(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
