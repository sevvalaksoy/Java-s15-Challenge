package com.aksoy.person.employee;

import com.aksoy.person.Person;

public abstract class Employee extends Person {
    private String name;
    private Key key;
    private int workHour;
    private long phoneNumber;

    public Employee(String name, Key key, int workHour, long phoneNumber){
        super(name);
        this.key = key;
        this.workHour = workHour;
        this.phoneNumber = phoneNumber;
    }
}
