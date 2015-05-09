package edu.csumb.mattw.speedread;

/**
 * Created by Matt on 5/7/2015.
 */
public class Book {
    public String title;
    public String author;
    public String ISBN;
    public double fee;

    Book(String title, String author, String ISBN, double fee) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.fee = fee;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.title = title;
    }

    public void setISBN(String ISBN) {
        this.title = title;
    }

    public void setTitle(double fee) {
        this.fee = fee;
    }
}
