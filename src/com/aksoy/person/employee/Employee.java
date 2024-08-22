package com.aksoy.person.employee;

import com.aksoy.person.Person;

public abstract class Employee extends Person {
    private String name;
    private Key key;
    private int workHour;
    private String phoneNumber;

    public Employee(){
    }

    public Employee(String name, Key key, int workHour, String phoneNumber){
        super(name);
        this.key = key;
        this.workHour = workHour;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", key=" + key +
                ", workHour=" + workHour +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
