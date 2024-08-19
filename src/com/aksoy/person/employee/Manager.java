package com.aksoy.person.employee;

import com.aksoy.library.Book;
import com.aksoy.library.Library;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Manager extends Employee implements Hire{
    private static Manager instance = new Manager("Aysel", Key.ALL, 12, "5383214567", 950.0, "Kt5F3ljN");
    private String name;
    private Key key;
    private int workHour;
    private String phoneNumber;
    private double budget;
    private String password;

    private Manager(String name, Key key, int workHour, String phoneNumber, double budget, String password){
        this.name = name;
        this.key = key;
        this.workHour = workHour;
        this.phoneNumber = phoneNumber;
        this.budget = budget;
        this.password = password;
    }
    public static Manager getInstance(){
        return instance;
    }
    public double checkBudget(){
        return getBudget();
    }
    public void buyBook(Book book){
        if(book.getPrice()>checkBudget()){
            System.out.println("Bu kitabı almak için bütçeniz yetersizdir.");
        } else {
            System.out.println("Kitap kütüphaneye ekleniyor...");
            setBudget(getBudget()-book.getPrice());
            Library.getInstance().getBooks().add(book);
        }
    }
    public void paySalary(double perHourSalary){
        int totalEmployeeWorkHour = (Library.getInstance().getEmployees().size()-1)*8;
        double totalSalary = totalEmployeeWorkHour*perHourSalary;
        if(Manager.getInstance().getBudget()<totalSalary){
            System.out.println("Bu ay için gecikmeli ödeme yapılacaktır.");
        } else {
            Manager.getInstance().setBudget(getBudget()-totalSalary);
            System.out.println("Çalışanların ödemeleri yapılmıştır.");
        }
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
    public double getBudget(){
        return budget;
    }
    public String getPassword(){
        return password;
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
    public void setBudget(double budget){
        Manager.getInstance().budget = budget<=0?0:budget;
    }
    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public void hireJanitor(String name, Key key, int workHour, String phoneNumber) {
        Janitor janitor = new Janitor();
        janitor.setName(name);
        janitor.setKey(key);
        janitor.setPhoneNumber(phoneNumber);
        janitor.setWorkHour(workHour);
        Set<Employee> employees = Library.getInstance().getEmployees();
        employees.add(janitor);
        Library.getInstance().setEmployees(employees);
        System.out.println("Görevli işe alınmıştır.");
    }

    @Override
    public void hireLibrarian(String name, Key key, int workHour, String phoneNumber, String password) {
        Librarian librarian = new Librarian();
        librarian.setKey(key);
        librarian.setPhoneNumber(phoneNumber);
        librarian.setName(name);
        librarian.setPassword(password);
        librarian.setWorkHour(workHour);
        Set<Employee> employees = Library.getInstance().getEmployees();
        employees.add(librarian);
        Library.getInstance().setEmployees(employees);
        System.out.println("Kütüphaneci işe alınmıştır.");
    }
    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                ", key=" + key +
                ", workHour=" + workHour +
                ", phoneNumber=" + phoneNumber +
                ", budget=" + budget +
                ", password='" + password + '\'' +
                '}';
    }
}
