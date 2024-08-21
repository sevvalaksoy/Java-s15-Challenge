package com.aksoy.person.member;

import com.aksoy.library.Book;
import com.aksoy.library.Library;
import com.aksoy.person.Person;
import com.aksoy.person.employee.Manager;

import java.util.*;
import java.util.stream.Collectors;

public class Member extends Person implements MemberBookMethods{
    private Long id;
    private String name;
    private Address address;
    private int booksIssued;
    private Date dateOfMembership;
    private String phoneNumber;
    private String password;
    private UserType userType;
    private List<Book> books;
    public Member(){

    }
    public Member(String name, Long id, Address address, int booksIssued, Date dateOfMembership,
                  String phoneNumber, String password, UserType userType, List<Book> books){
        this.name= name;
        this.id = id;
        this.address = address;
        this.booksIssued = booksIssued;
        this.dateOfMembership = dateOfMembership;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userType = userType;
        this.books = books;
    }
    public void whoYouAre(){
        System.out.println(this.getName());
    }
    public void changePassword(String passwordOld, String passwordNew){
        if(passwordOld.equals(this.getPassword())){
            setPassword(passwordNew);
        }
    }
    public List<Book> getBooks(){
        return books;
    }
    public void setBooks(List<Book> books){
        this.books = books;
    }
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public Address getAddress(){
        return address;
    }
    public int getBooksIssued(){
        return booksIssued;
    }
    public Date getDateOfMembership(){
        return dateOfMembership;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getPassword(){
        return password;
    }
    public UserType getUserType(){
        return userType;
    }
    public void setId(Long id){
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
    public void setDateOfMembership(Date dateOfMembership){
        this.dateOfMembership = dateOfMembership;
    }
    public void setPhoneNumber(String phoneNumber){
        if(getPhoneNumber().equals(null)){
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

    @Override
    public void borrowBook(String name) {
        for(Book book: Library.getInstance().getBooks()){
            if(book.getName().equals(name) && book.isAvailable() == true){
                switch (this.getUserType()){
                    case VIP :
                        if (this.getBooksIssued() <= 9) {
                            System.out.println("Aradığınız kitap sistemde mevcuttur. Kiralama için ödenecek tutar: " + book.getRentPrice());
                            this.payBill(book.getRentPrice());
                            book.setDateOfPurchase(new Date());
                            List<Book> books = Library.getInstance().getBooks();
                            books.remove(book);
                            Library.getInstance().setBooks(books);
                            List<Book> memberBooks = this.getBooks();
                            memberBooks.add(book);
                            book.changeOwner(this);
                            this.setBooks(memberBooks);
                            this.incBookIssued();
                        } else {
                            System.out.println("Sistemden totalde 10 kitap kiralanmış daha fazla almadan önce kitapları geri getirin.");
                        }
                    case STUDENT :
                        if (this.getBooksIssued() <= 4) {
                            System.out.println("Aradığınız kitap sistemde mevcuttur. Kiralama için ödenecek tutar: " + book.getRentPrice() * 0.25);
                            this.payBill(book.getRentPrice()*0.25);
                            book.setDateOfPurchase(new Date());
                            List<Book> books = Library.getInstance().getBooks();
                            books.remove(book);
                            Library.getInstance().setBooks(books);
                            List<Book> memberBooks = this.getBooks();
                            memberBooks.add(book);
                            book.changeOwner(this);
                            this.setBooks(memberBooks);
                            this.incBookIssued();
                        } else {
                            System.out.println("Sistemden toplamda 5 kitap kiralanmış daha fazla almadan önce kitapları geri getirin.");
                        }
                    case STANDARD:
                        if(this.getBooksIssued() <= 4) {
                            System.out.println("Aradığınız kitap sistemde mevcuttur. Kiralama için ödenecek tutar: " + book.getRentPrice());
                            this.payBill(book.getRentPrice());
                            book.setDateOfPurchase(new Date());
                            List<Book> books = Library.getInstance().getBooks();
                            books.remove(book);
                            Library.getInstance().setBooks(books);
                            List<Book> memberBooks = this.getBooks();
                            memberBooks.add(book);
                            book.changeOwner(this);
                            this.setBooks(memberBooks);
                            this.incBookIssued();
                        } else {
                            System.out.println("Sistemden toplamda 5 kitap kiralanmış daha fazla almadan önce kitapları geri getirin.");
                        }
                    default:
                        System.out.println("Üyenin abone tipini belirtmek zorundasınız.");
                        break;
                }
            } else if (book.getName().equals(name) && book.isAvailable() == false){
                System.out.println("Aradığınız kitap başkası tarafından alınmış bulunmaktadır.");
            }
        }
        System.out.println("Aradığınız kitap ismi ile sistemde kayıtlı kitap bulunamamıştır.");
    }
    public void incBookIssued(){
        setBooksIssued(getBooksIssued()+1);
    }
    public void decBookIssued(){
        setBooksIssued((getBooksIssued()-1) <=0 ? 0 : getBooksIssued()-1);
    }
    public void payBill(double price){
        Manager.getInstance().setBudget(Manager.getInstance().getBudget() + price);
        System.out.println("Ödeme yapılmmıştır.");
    }

    @Override
    public void returnBook(Book book) {
        int diff = book.getDateOfPurchase().compareTo(new Date());
        if( diff > 30){
            payBill((diff-30)*0.5);
        }
        List<Book> books = Library.getInstance().getBooks();
        books.add(book);
        Library.getInstance().setBooks(books);
        System.out.println("Kitap sisteme eklenmiştir.");
        this.decBookIssued();
    }

    @Override
    public void showBooks(Set<Book> books) {
        for(Book book: this.getBooks()){
            System.out.println(book.getName());
        }
    }

    @Override
    public String toString() {
        String bookNames = books.stream()
                .map(Book::getName)
                .collect(Collectors.joining(", "));
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", booksIssued=" + booksIssued +
                ", dateOfMembership='" + dateOfMembership + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", books=" + bookNames +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member member)) return false;
        return id == member.id && booksIssued == member.booksIssued && Objects.equals(name, member.name) && Objects.equals(address, member.address) && Objects.equals(dateOfMembership, member.dateOfMembership) && Objects.equals(phoneNumber, member.phoneNumber) && Objects.equals(password, member.password) && userType == member.userType && Objects.equals(books, member.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, booksIssued, dateOfMembership, phoneNumber, password, userType, books);
    }
}
