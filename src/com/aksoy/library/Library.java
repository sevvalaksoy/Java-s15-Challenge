package com.aksoy.library;

import com.aksoy.person.author.Author;
import com.aksoy.person.employee.Employee;
import com.aksoy.person.employee.Librarian;
import com.aksoy.person.member.Member;

import java.util.*;

public class Library {
    private static Library instance = new Library(new ArrayList<>(),new HashSet<>(),new HashSet<>(),new HashSet<>(), new HashMap<>());
    private List<Book> books;
    private Set<Author> authors;
    private Set<Member> members;
    private Set<Employee> employees;
    private Map<Member, List<Book>> givenBooks;

    private Library(List<Book> books, Set<Author> authors, Set<Member> members, Set<Employee> employees, Map<Member, List<Book>> givenBooks) {
        this.books = books;
        this.authors = authors;
        this.members = members;
        this.employees = employees;
        this.givenBooks = givenBooks;
    }
    public static Library getInstance(){
        return instance;
    }
    public Librarian getLibrarian(){
        for(Employee employee: employees){
            if(employee instanceof Librarian){
                return ((Librarian) employee);
            }
        }
        return null;
    }
    public Book bringBook(String name){
        List<Book> books = getBooks();
        if (books == null) {
            System.out.println("Kitap listesi bulunamadı.");
            return null;
        }
        for (Book book : books) {
            if (book.getName().equals(name)) {
                return book;
            }
        }
        System.out.println(name + " İsminde kitap bulunamadı.");
        return null;
    }
    public void addBook(Book book){
        List<Book> list = Library.getInstance().getBooks();
        list.add(book);
        Library.getInstance().setBooks(list);
        Set<Author> yazarlar = Library.getInstance().getAuthors();
        yazarlar.add(book.getAuthor());
        Library.getInstance().setAuthors(yazarlar);
    }
    public List<Book> bringAuthorBooks(String name){
        List<Book> books = new ArrayList<>();
        for(Book book: getBooks()){
            if(book.getAuthor().equals(name)){
                books.add(book);
            }
        }
        if(books.equals(null)) {
            System.out.println("Sistemde bu yazara ait kitap bulunmamaktadır.");
            return null;
        } else {
            return books;
        }
    }
    public void bringCategory(String genre) {
        boolean result = true;
        Genre genreEnum = Genre.valueOf(genre);
        for(Book book : getBooks()){
            if(book.getGenre().equals(genreEnum)){
                System.out.println(book.getName());
                result = false;
            }
        }
        if(result) System.out.println("Bu türde kitap bulunamamıştır.");
    }
    public void deleteBook(Book kitap){
        for(Book book: getBooks()){
            if(book.equals(kitap)){
                getBooks().remove(kitap);
            }
        }
    }
    public void updateBook(Book book, boolean isAvailable, double rentPrice, Condition condition){
        book.setAvailable(isAvailable);
        book.setRentPrice(rentPrice);
        book.setCondition(condition);
    }
    public Set<String> showAuthors(){
        Set<String> names = new HashSet<>();
        for(Author author: getAuthors()){
            names.add(author.getName());
        }
        return names;
    }
    public Author findAuthor(String name){
        for(Author author: Library.getInstance().getAuthors()){
            if(author.getName().equals(name)){
                return author;
            }
        }
        return null;
    }
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public Set<Author> getAuthors() {
        return authors;
    }
    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
    public Set<Member> getMembers() {
        return members;
    }
    public void setMembers(Set<Member> members) {
        this.members = members;
    }
    public Set<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
    public Map<Member, List<Book>> getGivenBooks(){
        return givenBooks;
    }
    public void setGivenBooks(Map<Member, List<Book>> givenBooks){
        this.givenBooks = givenBooks;
    }
    @Override
    public String toString() {
        return "Library{" +
                "books=" + books +
                ", authors=" + authors +
                ", members=" + members +
                ", employees=" + employees +
                ", givenBooks=" + givenBooks +
                '}';
    }

}
