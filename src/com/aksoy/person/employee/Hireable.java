package com.aksoy.person.employee;

public interface Hireable {
    void hireJanitor(String name, Key key, int workHour, String phoneNumber);
    void hireLibrarian(String name, Key key, int workHour, String phoneNumber, String password);
}
