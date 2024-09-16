package com.lms;
/*                               BOOK
 * Contains the Book object as well as some specific methods such as 
 * Delete book, Add Book, Sort book to easily perform book related tasks
 * as well as the menu for interacting with the books
 */
import java.util.ArrayList;
import java.util.Collections;

public class Book {
    private int ID;
    private String title;
    private String author;
    
    public Book(){}
    //This constructor should only be used for testing purposes/reading from files, use createBook() for new books
    public Book(int ID, String title, String author){
        this.setID(ID);
        this.setTitle(title);
        this.setAuthor(author);
    }
    
    public void createBook(){
        boolean validInput = false;
        String temp;
        //Setting ID
        do{
            temp = Methods.getInput("Please Enter This Book's ID: ");
            try{ //implemented to ensure that the integer can be parsed
                validInput = this.setID(Integer.parseInt(temp)); //setID returns if successful or not
            }
            catch(NumberFormatException nfe){ //catches anything that doesnt convert to int
                validInput = false;
                Methods.showMessage("Your input must be an Integer");
            }
        }while(!validInput);
        //Setting Title
        this.setTitle(Methods.getInput("Please Enter The Title: "));
        //Setting Author
        this.setAuthor(Methods.getInput("Please Enter The Authors Name: "));
        Methods.showMessage(this.toString());
    }

    //Setters and Getters
    public int getID(){
        return this.ID;
    }

    public boolean setID(int ID){
        if(ID > 0){
            this.ID = ID;
            return true;
        }
        else{
            Methods.showMessage("Invalid ID, must be an integer greater than 0");
            return false;
        }
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setAuthor(String author){
        this.author = author;
    }
    // statics

    //Delete book that matches a book ID from the user
    public static void deleteBook(ArrayList<Book> list){
        //Gets a book ID from the user
        boolean validInput = false;
        boolean bookFound = false;
        int bookID = -1;
        do{
            try{
            bookID = Integer.parseInt(Methods.getInput("Please Enter The Book ID: "));
            validInput = true;
            }
            catch(NumberFormatException nfe){
                validInput = false;
                Methods.showMessage("Please enter a valid ID, It should be an Integer value above 0");
            }
        }while(!validInput);
        //looks for a match and if it matches, prompts the book for deletion.
        for (Book index : list) {
            if (index.getID() == bookID){
                char confirmation;
                bookFound = true;
                Methods.showMessage("Book Found: \n" + index.toString());
                confirmation = Methods.getInput("Would you like to delete? y/n").charAt(0);
                confirmation = Character.toLowerCase(confirmation);
                switch(confirmation){
                    case('y'):
                        list.remove(index);
                        Methods.showMessage("Removed Book");
                        break;
                    case('n'):
                        Methods.showMessage("Book Not Deleted");
                        break;
                    default:
                        Methods.showMessage("Invalid Input, Ignoring changes");
                        break;

                }
                break;

            }
        }
        //give clarity to the user if a book was not found
        if (!bookFound){
            Methods.showMessage("Book was not found.");
        }
        Book.sortBook(list);
    }

    //Test verison of the method/version for explicit books
    public static void deleteBook(ArrayList<Book> list, Book book){
        //looks for a match and if it matches deletes the book.
        for (Book index : list) {
            if (index.getID() == book.getID() 
            && index.getAuthor().equals(book.getAuthor())
            && index.getTitle().equals(book.getTitle())){
                list.remove(index);
                break;
            }
                
        }
        Book.sortBook(list);
    }
    //easy way to specify a book created by the user
    public static void addBook(ArrayList<Book> list){
        boolean validID = true;
        Book book = new Book();
        book.createBook();
        //Don't attempt to iterate through an empty list, could cause an error
        if(!(list.isEmpty())){
            for (Book index : list) {
                if(index.getID() == book.getID()){
                    validID = false;
                    Methods.showMessage("Book ID already exists, please try again");
                }
            }
        }
        if(validID){
            list.add(book);
            Book.sortBook(list);
        }
    }
    //ability to add a specific premade book
    public static void addBook(ArrayList<Book> list, Book book){
        boolean validID = true;
        if(!(list.isEmpty())){
            for (Book index : list) {
                if(index.getID() == book.getID()){
                    validID = false;
                    Methods.showMessage("Book ID already exists, please try again");
                }
            }
        }
        if(validID){
            list.add(book);
            Book.sortBook(list);
        }
    }
    //simplifies the book sorting process
    public static void sortBook(ArrayList<Book> list){

        Collections.sort(list, new SortBook());
    }
    //Contains the menu used for the user to interact
    public static void bookMenu(ArrayList<Book> list){
        char userChoice = 'n';
        while(userChoice != '4'){
            Methods.showMessage("---------------------------------------------------");
            userChoice = Methods.getInput
            ("Choose One of the following Options:\n" +
            "1) Show All Active Books\n" + 
            "2) Add A Book\n" +
            "3) Delete A Book\n" +
            "4) Quit Program").charAt(0);
            Methods.showMessage("---------------------------------------------------");

            switch(userChoice){
                case('1')://Display all books
                    for (Book book : list) {
                        Methods.showMessage(book.toString());
                    }
                    break;
                case('2')://Add a book
                    Book.addBook(list);
                    break;
                case('3')://Delete a book
                    Book.deleteBook(list);
                    break;
                case('4')://Quit program
                    Methods.showMessage("Good Day!");
                    break;
                default:
                    Methods.showMessage("Invalid Selection, try again.");
            }
        }
    }

    public String toString(){
        String result = String.format("ID: %d | Title: %s | Author: %s",this.getID(), this.getTitle(), this.getAuthor());
        return result;
    }
}
