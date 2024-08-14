package com.aksoy.library;

import com.aksoy.person.author.Author;

public class Book {
    private long id;
    private Author author;
    private String name;
    private int edition;
    private boolean isAvailable;
    private double rentPrice;
    private String dateOfPurchase;
    private int pages;
    private double price;
    private Condition condition;
    private Genre genre;

    public Book(long id, Author author, String name, int edition, boolean isAvailable,
                double rentPrice, String dateOfPurchase, int pages, double price,
                Condition condition, Genre genre){
        this.id = id;
        this. author = author;
        this. name = name;
        this.edition = edition;
        this.isAvailable = isAvailable;
        this.rentPrice = rentPrice;
        this.dateOfPurchase = dateOfPurchase;
        this.pages = pages;
        this.price = price;
        this.condition = condition;
        this.genre = genre;
    }
    public long getId() {
        return id;
    }
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
    public String getDateOfPurchase() {
        return dateOfPurchase;
    }
    public void setDateOfPurchase(String dateOfPurchase) {
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
}
