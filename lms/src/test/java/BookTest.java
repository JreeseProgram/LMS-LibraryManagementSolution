

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;

import com.lms.Book;
import com.lms.Methods;

public class BookTest {

    @Test
    public void testCreateBook(){
        Book myBook = new Book();

        myBook.createBook();
        
        Assertions.assertEquals(1, myBook.getID());
        Assertions.assertEquals("The Great Gatsby", myBook.getTitle());
        Assertions.assertEquals("John Smith", myBook.getAuthor());
    }

    @Test
    public void testDeleteBook(){
        Book myBook1 = new Book(1,"Great Gatsby", "John Smith");
        Book myBook2 = new Book(2,"Whalers On The Moon", "Jane Doe");
        ArrayList<Book> library = new ArrayList<Book>();
        library.add(myBook1);
        library.add(myBook2);

        Book.deleteBook(library, myBook2);
        for (Book book : library) {
            if(book.getID() == 2){
                Assertions.fail("Book was not deleted");
                break;
            }
        }
    }

    @Test
    public void testAddBook(){
        Book myBook = new Book(3, "Great Gatsby", "John Smith");
        Book myBook2 = new Book(3, "Whalers on the Moon", "Jane Doe");
        ArrayList<Book> library = new ArrayList<Book>();

        Book.addBook(library, myBook);
        Book.addBook(library, myBook2);
        Methods.showMessage(library.toString());
        if(library.size() != 1){
            Assertions.fail("Book was not added or validated correctly");
        }
    }
    
}
