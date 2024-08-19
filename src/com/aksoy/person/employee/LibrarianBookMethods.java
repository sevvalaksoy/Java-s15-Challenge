package com.aksoy.person.employee;

import com.aksoy.library.Book;
import com.aksoy.person.author.Author;
import com.aksoy.person.member.Member;

public interface LibrarianBookMethods {
    void removeBook(long id);
    Book searchBook(Long id);
    void searchBook(Author author);
    void searchBook(String name);
    void issueBook(Long id, Member member);
    void takeBookBack(long bookid, long memberid);
}
