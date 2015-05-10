package edu.csumb.mattw.speedread;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Matt on 5/7/2015.
 */
public class Database {
    private static Database uniqueInstance;
    private Database() {}

    //holds books with title as key
    public static HashMap<String, Book> bookHashMap = new HashMap<String, Book>();

    //hold username and password
    public static HashMap<String, String> userHashMap = new HashMap<String, String>();

    //hold log
    public static ArrayList<String> log = new ArrayList<String>();

    static {
        userHashMap.put("a@lice5", "@csit100");
        userHashMap.put("$brian7", "123abc##");
        userHashMap.put("!chris12!", "CHRIS12!!");

        Book bookOne, bookTwo, bookThree;
        bookOne = new Book("HotJava", "S. Narayanan", "123-ABC-101", .05);
        bookTwo = new Book("Fun Java", "Y. Byun", "ABCDEF-09", 1.0);
        bookThree = new Book("Algorithm for Java", "K. Alice", "CDE-777-123", .25);

    }


    public static Database getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Database();
        }
        return uniqueInstance;
    }

}
