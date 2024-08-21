package com.aksoy.person.employee;

import com.aksoy.library.Book;
import com.aksoy.library.Condition;
import com.aksoy.person.author.Author;
import com.aksoy.person.member.Member;

public interface LibrarianBookMethods {
    void removeBook(long id);
    Book searchBook(Long id);
    void searchBook(Author author);
    void searchBook(String name);
    void issueBook(Long id, long memberID);
    void takeBookBack(long bookid, long memberid, Condition condition);
}
