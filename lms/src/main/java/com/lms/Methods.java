package com.lms;

import java.util.Scanner;

public class Methods {

    public static void showMessage(String mess){
        System.out.println(mess);
    }
    
    public static String getInput(String request){
        Scanner in = new Scanner(System.in);
        Methods.showMessage(request);
        String result = in.nextLine();
        return result;
    }
}
