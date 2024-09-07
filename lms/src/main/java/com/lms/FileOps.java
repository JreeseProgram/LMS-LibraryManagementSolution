package com.lms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileOps {

    

    static void startupCheck(ArrayList<Book> list){
        //Ensure files exists
        Path folder = Paths.get("./BooksOnStartup/");
        Path filePath = Paths.get("./BooksOnStartup/bookStartup.txt");
       
        try{
            if(Files.notExists(folder)){
                Files.createDirectories(folder);
            }
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
        }
        catch(IOException IOE){
            Methods.showMessage("Failed to create file");
        }

        //if the file exists it will then attempt to read and delimit the file
        try(BufferedReader bReader = new BufferedReader(new FileReader(filePath.toString()))){
            ArrayList<String[]> fromFile = new ArrayList<String[]>();
            fromFile.add(bReader.readLine().split(","));
            if(!(fromFile.size() == 0))
            //takes the new delimited strings and passes them into the book constructor
            for (String[] strings : fromFile) {
                list.add(new Book(Integer.parseInt(strings[0]), strings[1],strings[2]));
            }
        }
        catch(IOException IOE){
            Methods.showMessage(IOE.toString());
        }
        catch(IndexOutOfBoundsException IOOBE){
            Methods.showMessage("List had an out of bounds exception");
        }
        catch(NumberFormatException NFE){
            Methods.showMessage("When Creating a new book, a non-number was in the ID, ignoring item");
        }
        catch(NullPointerException NPE){
            Methods.showMessage("Startup File is empty, Ignoring file");
        }
    }
    
}
