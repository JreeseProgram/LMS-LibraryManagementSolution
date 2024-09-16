package com.lms;
/*                    MAIN
 * Houses the main function as well as the library array
 * that stores all of the book information
 */
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Book> library = new ArrayList<Book>();
        FileOps.startupCheck(library);
        Book.bookMenu(library);
    }
}