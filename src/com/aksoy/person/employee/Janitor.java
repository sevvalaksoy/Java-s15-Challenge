package com.aksoy.person.employee;

public class Janitor extends Employee{
    private String name;
    private Key key;
    private int workHour;
    private String phoneNumber;

    public Janitor(){

    }
    public Janitor(String name, Key key, int workHour, String phoneNumber){
        super(name, key, workHour, phoneNumber);
    }
    public void whoYouAre(){
        System.out.println(getName());
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Key getKey(){
        return key;
    }
    public int getWorkHour(){
        return workHour;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setKey(Key key){
        this.key = key;
    }
    public void setWorkHour(int workHour){
        this.workHour = workHour;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Janitor{" +
                "name='" + name + '\'' +
                ", key=" + key +
                ", workHour=" + workHour +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
