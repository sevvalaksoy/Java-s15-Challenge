package com.aksoy.person.employee;

import com.aksoy.library.Book;
import com.aksoy.library.Library;
import com.aksoy.person.author.Author;
import com.aksoy.person.member.Member;
import com.aksoy.person.member.MemberBookMethods;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Librarian extends Employee implements LibrarianBookMethods{
    private String name;
    private Key key;
    private int workHour;
    private String phoneNumber;
    private String password;

    public Librarian(){

    }
    public Librarian(String name, Key key, int workHour, String phoneNumber, String password){
        super(name, key, workHour, phoneNumber);
        this.password = password;
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
    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "name='" + name + '\'' +
                ", key=" + key +
                ", workHour=" + workHour +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Librarian librarian)) return false;
        return Objects.equals(name, librarian.name) && Objects.equals(password, librarian.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }

    public void createMember(Member member){
        Library.getInstance().getMembers().add(member);
        Library.getInstance().getGivenBooks().put(member, null);
        System.out.println("Üyelik Başarılı bir şekilde oluşturulmuştur.");
    }

    public boolean verifyMember(String password){
        for(Member member: Library.getInstance().getMembers()){
            if(member.getPassword().equals(password)){
                System.out.println("Sisteme hoş geldiniz " + member.getName());
                System.out.println(member);
                return true;
            }
        }
        System.out.println("Sistemde böyle bir üye bulunamamıştır.");
        return false;
    }

    @Override
    public void removeBook(Book book) {
        for(Book kitap: Library.getInstance().getBooks()){
            if(kitap.equals(book)){
                List<Book> newList = Library.getInstance().getBooks();
                newList.remove(book);
                Library.getInstance().setBooks(newList);
            }
        }
    }

    @Override
    public void searchBook(Long id) {
        boolean result = true;
        for(Book kitap: Library.getInstance().getBooks()){
            if(kitap.getId() == id){
                result = false;
                System.out.println("Sistemde " + id + " idli kitap bulunmaktadır.");
                if(kitap.isAvailable()){
                    System.out.println("Kitap kiralanabilir.");
                } else {
                    System.out.println("Kitap başkası tarafından kiralanmıştır.");
                }
                break;
            }
        }
        if(result){
            System.out.println("Sistemde bu id ile bir kitap bulunamamıştır.");
        }
    }
    @Override
    public void searchBook(String name) {
        boolean result = true;
        for(Book kitap: Library.getInstance().getBooks()){
            if(kitap.getName().equals(name)){
                result = false;
                System.out.println("Sistemde " + name + " isimli kitap bulunmaktadır.");
                if(kitap.isAvailable()){
                    System.out.println("Kitap kiralanabilir.");
                    System.out.println("Kitap kiralama ücreti: " + kitap.getRentPrice());
                } else {
                    System.out.println("Kitap başkası tarafından kiralanmıştır.");
                }
                break;
            }
        }
        if(result){
            System.out.println("Sistemde bu isimde bir kitap bulunamamıştır.");
        }
    }
    @Override
    public void searchBook(Author author) {
        for(Author yazar: Library.getInstance().getAuthors()){
            if(yazar.equals(author)){
                System.out.println("Sistemde " + author.getName() + " yazarına ait kitaplar bulunmaktadır:");
                for(String book: author.getBooks()){
                    System.out.println(book);
                }
            } else {
                System.out.println("Sistemde bu yazara ait kitap bulunamamıştır.");
            }
        }
    }

    @Override
    public void issueBook(Long id, Member member) {
        if(verifyMember(member.getPassword())){
            for(Book kitap: Library.getInstance().getBooks()){
                if(kitap.getId() == id){
                    if(kitap.isAvailable()){
                        kitap.setOwner(member);
                        kitap.setAvailable(false);
                        kitap.setDateOfPurchase(new Date());
                        Library.getInstance().getBooks().remove(kitap);
                        member.incBookIssued();
                        member.getBooks().add(kitap);
                        Library.getInstance().getGivenBooks().put(member, member.getBooks());
                    } else {
                        System.out.println("Kitap başkası tarafından kiralanmıştır.");
                    }
                } else {
                    System.out.println("Sistemde bu id ile bir kitap bulunamamıştır.");
                }
            }
        } else {
            System.out.println("Girilen şifre hatalıdır, kitap kiralanamamıştır.");
        }
    }
    @Override
    public void takeBookBack(Book book, Member member){
        book.setDateOfPurchase(null);
        book.setOwner(null);
        book.setAvailable(true);
        Library.getInstance().getBooks().add(book);
        member.decBookIssued();
        member.getBooks().remove(book);
        Library.getInstance().getGivenBooks().put(member, member.getBooks());
        int diff = book.getDateOfPurchase().compareTo(new Date());
        if( diff > 30){
            Manager.getInstance().setBudget(Manager.getInstance().getBudget() + calculateFine(diff, 0.5));
        }
        System.out.println("Kitap geri alınmıştır.");
    }
    public double calculateFine(int diff, double rate) {
        return (diff-30) * rate;
    }
    public void createBill(Book book){
        System.out.println("Sisteme ödemeniz gereken tutar: " + book.getRentPrice());
    }
}
