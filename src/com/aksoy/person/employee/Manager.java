package com.aksoy.person.employee;

public class Manager extends Employee{
    private String name;
    private Key key;
    private int workHour;
    private long phoneNumber;
    private double budget;

    public Manager(String name, Key key, int workHour, long phoneNumber, double budget){
        super(name, key, workHour, phoneNumber);
        this.budget = budget;
    }
    public void whoYouAre(){
        System.out.println(getName());
    }
    public String getName(){
        return name;
    }
    public Key getKey(){
        return key;
    }
    public int getWorkHour(){
        return workHour;
    }
    public long getPhoneNumber(){
        return phoneNumber;
    }
    public double getBudget(){
        return budget;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setKey(Key key){
        this.key = key;
    }
    public void setWorkHour(int workHour){
        this.workHour = workHour;
    }
    public void setPhoneNumber(long phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setBudget(double budget){
        this.budget = getBudget()<=0?0:budget;
    }
}
