package com.lms;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Book> library = new ArrayList<Book>();
        FileOps.startupCheck(library);
        Book.bookMenu(library);
    }
}