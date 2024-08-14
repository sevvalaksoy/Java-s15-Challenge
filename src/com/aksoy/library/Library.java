package com.aksoy.library;

import com.aksoy.person.author.Author;
import com.aksoy.person.employee.Employee;
import com.aksoy.person.member.Member;

import java.util.List;
import java.util.Set;

public class Library {
    private List<Book> books;
    private Set<Author> authors;
    private Set<Member> members;
    private Set<Employee> employees;

    public Library(List<Book> books, Set<Author> authors, Set<Member> members, Set<Employee> employees) {
        this.books = books;
        this.authors = authors;
        this.members = members;
        this.employees = employees;
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
}
