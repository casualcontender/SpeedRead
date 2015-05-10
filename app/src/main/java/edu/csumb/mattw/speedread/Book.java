package edu.csumb.mattw.speedread;

/**
 * Created by Matt on 5/7/2015.
 */
public class Book {
    private String author;
    private String ISBN;
    private double fee;
    private boolean available;

    Book(String title, String author, String ISBN, double fee) {
        this.author = author;
        this.ISBN = ISBN;
        this.fee = fee;
        this.available = true;
    }


    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(String ISBN) {
        this.author = author;
    }

    public void setTitle(double fee) {
        this.fee = fee;
    }

    public void setAvailable(boolean available){
        this.available = available;
    }
}
