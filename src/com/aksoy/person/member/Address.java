package com.aksoy.person.member;

public class Address {
    private String city;
    private String street;
    private int zipCode;
    private String apartment;
    private int flatNo;

    public Address(String city, String street, int zipCode, String apartment, int flatNo){
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.apartment = apartment;
        this.flatNo = flatNo;
    }

    public String getCity(){
        return city;
    }
    public String getStreet(){
        return street;
    }
    public int getZipCode(){
        return zipCode;
    }
    public String getApartment(){
        return apartment;
    }
    public int getFlatNo(){
        return flatNo;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setStreet(String street){
        this.street = street;
    }
    public void setZipCode(int zipCode){
        this.zipCode = zipCode;
    }
    public void setApartment(String apartment){
        this.apartment = apartment;
    }
    public void setFlatNo(int flatNo){
        this.flatNo = flatNo;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipCode=" + zipCode +
                ", apartment='" + apartment + '\'' +
                ", flatNo=" + flatNo +
                '}';
    }
}
