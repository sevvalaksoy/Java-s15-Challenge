package com.aksoy.person.member;

import com.aksoy.library.Book;

import java.util.Set;

public interface MemberBookMethods {
    void borrowBook(String name);
    void returnBook(Book book);
    void showBooks(Set<Book> books);
}
