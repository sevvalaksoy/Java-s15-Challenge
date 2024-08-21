package com.aksoy.person.employee;

import com.aksoy.library.Book;
import com.aksoy.library.Condition;
import com.aksoy.library.Library;
import com.aksoy.person.author.Author;
import com.aksoy.person.member.Member;
import com.aksoy.person.member.UserType;

import java.util.*;

public class Librarian extends Employee implements LibrarianBookMethods{
    private String name;
    private Key key;
    private int workHour;
    private String phoneNumber;
    private String password;
    private Map<Member, Double> bills = new HashMap<>();

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
                System.out.println(member.getName() + " adlı üye onaylandı.");
                System.out.println(member);
                return true;
            }
        }
        System.out.println("Sistemde böyle bir üye bulunamamıştır.");
        return false;
    }

    @Override
    public void removeBook(long id) {
        Book book = searchBook(id);
        if(book == null){
            System.out.println("Aranan kitap sistemde bulunamamıştır.");
        }
        for(Book kitap: Library.getInstance().getBooks()){
            if(kitap.equals(book)){
                List<Book> newList = Library.getInstance().getBooks();
                newList.remove(book);
                Library.getInstance().setBooks(newList);
                System.out.println("Kitap başarılı bir şekilde sistemden silinmiştir.");
                break;
            }
        }
    }

    @Override
    public Book searchBook(Long id) {
        for(Book kitap: Library.getInstance().getBooks()){
            if(kitap.getId() == id){
                System.out.println("Sistemde " + id + " idli kitap bulunmaktadır.");
                if(kitap.isAvailable()){
                    System.out.println("Kitap kiralanabilir.");
                } else {
                    System.out.println("Kitap başkası tarafından kiralanmıştır.");
                }return kitap;
            }
        }
        System.out.println("Sistemde bu id ile bir kitap bulunamamıştır."); return null;
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
    public void issueBook(Long id, long memberID) {
        Member member = Library.getInstance().getMembers().stream()
                .filter(m -> m.getId() == memberID)
                .findFirst()
                .orElse(null);
        if (member == null) {
            System.out.println("Üye bulunamadı.");
            return;
        }
        if(verifyMember(member.getPassword())){
            Book book = new Book();
            for(Book kitap: Library.getInstance().getBooks()){
                if(kitap.getId() == id){
                    if(kitap.isAvailable() && member.getBooksIssued() < 5){
                        kitap.setOwner(member);
                        kitap.setAvailable(false);
                        kitap.setDateOfPurchase(new Date());
                        createBill(kitap,member);
                        book = kitap;
                        member.incBookIssued();
                        member.getBooks().add(kitap);
                        Library.getInstance().getGivenBooks().put(member, member.getBooks());
                        System.out.println("Kitap başarılı bir şekilde kiralanmıştır.");
                        System.out.println(member);
                    } else if(kitap.isAvailable() && member.getBooksIssued() >= 5) {
                        System.out.println("Üyen daha fazla kitap alamaz.");
                    }else {
                        System.out.println("Kitap başkası tarafından kiralanmıştır.");
                    }
                } else {
                    System.out.println("Sistemde bu id ile bir kitap bulunamamıştır.");
                }
            }
            Library.getInstance().getBooks().remove(book);
        } else {
            System.out.println("Üye adı veya şifre hatalıdır, kitap kiralanamamıştır.");
        }
    }
    @Override
    public void takeBookBack(long bookid, long memberid, Condition condition){
        Member member = findMember(memberid);
        List<Book> books = Library.getInstance().getGivenBooks().get(member);
        Book book = new Book();
        for(Book b:books){
            if(b.getId() == bookid){
                book = b;
            }
        }
        double prevCondition = book.getCondition().getPrice();
        updateBook(bookid, condition, book.getRentPrice());
        double lastCondition = book.getCondition().getPrice();
        Manager.getInstance().setBudget(Manager.getInstance().getBudget() + prevCondition-lastCondition);
        System.out.println("Kitabın kullanım şekli nedeni ile ödenmesi gereken tutar: " + (prevCondition-lastCondition));
        int diff = book.getDateOfPurchase().compareTo(new Date());
        if( diff > 30){
            Manager.getInstance().setBudget(Manager.getInstance().getBudget() + calculateFine(diff, 0.5));
            System.out.println("Kitap " + (diff-30) + " gün kadar geciktirilmiştir, ödenecek ceza tutarı: " + calculateFine(diff, 0.5));
        }
        book.setDateOfPurchase(null);
        book.setOwner(null);
        book.setAvailable(true);
        Library.getInstance().getBooks().add(book);
        member.decBookIssued();
        member.getBooks().remove(book);
        Library.getInstance().getGivenBooks().put(member, member.getBooks());
        bills.put(member,bills.get(member)-book.getRentPrice());
        System.out.println("Kitap geri alınmıştır.");
    }
    public double calculateFine(int diff, double rate) {
        return (diff-30) * rate;
    }
    public void createBill(Book book, Member member){
        Double price = book.getRentPrice();
        System.out.println("Sisteme ödenecek tutar: " + price);
        bills.put(member,bills.getOrDefault(member,0.0)+price);
    }
    public Member findMember(long id){
        for(Member member: Library.getInstance().getMembers()){
            if(member.getId()==id){
                return member;
            }
        }
        return null;
    }
    public void updateBook(long id, Condition condition, double price ){
        Book book = searchBook(id);
        book.setCondition(condition);
        book.setRentPrice(price);
        System.out.println("Kitap güncellenmiştir!");
    }
    public UserType createUserType(String type){
        UserType userType;
        try {
            userType = UserType.valueOf(type);
            return userType;
        } catch (IllegalArgumentException e){
            System.out.println("Bu şekilde bir seçenek bulunamadı.");
        }
        return UserType.STANDARD;
    }
}
