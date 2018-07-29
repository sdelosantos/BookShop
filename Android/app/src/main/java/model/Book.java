package model;

import java.util.List;

public class Book {
    public String bookId;
    public String ISBN;
    public String title;
    public String cover;
    public Author author;
    public String pusblisher;
    public String description;
    public String language;
    public Shipping shipping;
    public double price;
    public int pages;
    public String format;
    public Book(){
         shipping = new Shipping();
         author = new Author();
    }
}