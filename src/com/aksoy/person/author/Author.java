package com.aksoy.person.author;

import com.aksoy.person.Person;

import java.util.Objects;
import java.util.Set;

public class Author extends Person {
    private Set<String> books;
    private String name;

    public Author(String name, Set<String> books){
        this.name= name;
        this.books = books;
    }

    public void showBooks(){
        for(String book: this.books){
            System.out.println(book);
        }
    }
    public void addBook(String book){
        this.books.add(book);
    }

    public void whoYouAre(){
        System.out.println("This is author " + this.getName());
    }
    public String getName(){
        return name;
    }
    public Set<String> getBooks(){
        return books;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setBooks(Set<String> books){
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "books=" + books +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;
        return Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
