package com.aksoy.library;

import com.aksoy.person.author.Author;
import com.aksoy.person.member.Member;

import java.util.Date;
import java.util.Objects;

public class Book {
    private long id;
    private Author author;
    private String name;
    private int edition;
    private boolean isAvailable;
    private double rentPrice;
    private Date dateOfPurchase;
    private int pages;
    private double price;
    private Condition condition;
    private Genre genre;
    private Member owner;
    public Book(){

    }
    public Book(long id, Author author, String name, int edition, boolean isAvailable,
                double rentPrice, Date dateOfPurchase, int pages, double price,
                Condition condition, Genre genre, Member owner){
        this.id = id;
        this.author = author;
        this.name = name;
        this.edition = edition;
        this.isAvailable = isAvailable;
        this.rentPrice = rentPrice;
        this.dateOfPurchase = dateOfPurchase;
        this.pages = pages;
        this.price = price;
        this.condition = condition;
        this.genre = genre;
        this.owner = new Member();
    }
    public void showOwner(){
        System.out.println(this.getAuthor());
    }
    public void display(){
        System.out.println(this);
    }
    public void updateCondition(Condition condition){
        setCondition(condition);
    }
    public void updateStatus(boolean isAvailable){
        setAvailable(isAvailable);
    }
    public void changeOwner(Member owner){
        setOwner(owner);
    }

    public long getId() {return id;}
    public void setId(long id) {
        this.id = id;
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getEdition() {
        return edition;
    }
    public void setEdition(int edition) {
        this.edition = edition;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    public double getRentPrice() {
        return rentPrice;
    }
    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }
    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }
    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
    public int getPages() {
        return pages;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Condition getCondition() {
        return condition;
    }
    public void setCondition(Condition condition) {
        this.condition = condition;
    }
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public Member getOwner(){
        return owner;
    }
    public void setOwner(Member owner){
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author=" + author +
                ", name='" + name + '\'' +
                ", edition=" + edition +
                ", isAvailable=" + isAvailable +
                ", rentPrice=" + rentPrice +
                ", dateOfPurchase='" + dateOfPurchase + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                ", condition=" + condition +
                ", genre=" + genre +
                ", owner=" + owner.getId() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return id == book.id && Objects.equals(author, book.author) && Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, name);
    }
}
