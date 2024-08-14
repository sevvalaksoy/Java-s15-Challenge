package com.aksoy.person.employee;

public class Librarian extends Employee{
    private String name;
    private Key key;
    private int workHour;
    private long phoneNumber;
    private String password;

    public Librarian(String name, Key key, int workHour, long phoneNumber, String password){
        super(name, key, workHour, phoneNumber);
        this.password = password;
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
    public String getPassword(){
        return password;
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
    public void setPassword(String password){
        this.password = password;
    }
}
