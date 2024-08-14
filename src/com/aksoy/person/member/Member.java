package com.aksoy.person.member;

import com.aksoy.person.Person;

import java.util.Scanner;

public class Member extends Person {
    private long id;
    private String name;
    private Address address;
    private int booksIssued;
    private String dateOfMembership;
    private long phoneNumber;
    private String password;
    private UserType userType;

    public Member(String name, long id, Address address, int booksIssued, String dateOfMembership,
                  long phoneNumber, String password, UserType userType){
        super(name);
        this.id = id;
        this.address = address;
        this.booksIssued = booksIssued;
        this.dateOfMembership = dateOfMembership;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userType = userType;
    }
    public void whoYouAre(){
        System.out.println(this.getName());
    }
    public String getName(){
        return name;
    }
    public long getId(){
        return id;
    }
    public Address getAddress(){
        return address;
    }
    public int getBooksIssued(){
        return booksIssued;
    }
    public String getDateOfMembership(){
        return dateOfMembership;
    }
    public long getPhoneNumber(){
        return phoneNumber;
    }
    public String getPassword(){
        return password;
    }
    public UserType getUserType(){
        return userType;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setId(long id){
        this.id = id;
    }
    public void setAddress(Address address){
        if(getPassword().isEmpty()){
            System.out.println("You need to set up a password in order to type in your address.");
        } else {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter password");
            String psword = myObj.nextLine();
            if(psword.equals(this.password)){
                this.address = address;
            }
        }
    }
    public void setBooksIssued(int booksIssued){
        this.booksIssued = booksIssued;
    }
    public void setDateOfMembership(String dateOfMembership){
        this.dateOfMembership = dateOfMembership;
    }
    public void setPhoneNumber(long phoneNumber){
        if(getPhoneNumber() == 0L){
            System.out.println("You need to set up a password in order to enter your phone number.");
        } else {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter password");
            String psword = myObj.nextLine();
            if(psword.equals(this.password)){
                this.phoneNumber = phoneNumber;
            }
        }
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setUserType(UserType userType){
        this.userType = userType;
    }
}
