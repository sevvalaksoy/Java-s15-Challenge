package com.aksoy.person.author;

import com.aksoy.library.Book;
import com.aksoy.person.Person;

import java.util.Set;

public class Author extends Person {
    private Set<Book> books;
    private String name;

    public Author(String name, Set<Book> books){
        super(name);
        this.books = books;
    }
    public void whoYouAre(){
        System.out.println(this.getName());
    }
    public String getName(){
        return name;
    }
    public Set<Book> getBooks(){
        return books;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setBooks(Set<Book> books){
        this.books = books;
    }
}
