/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exercise_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 *
 * @author nam.huynh
 */
public class Book {

    private int id;
    private String title;
    private String author;
    private long price;

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", author=" + author + ", price=" + price + '}';
    }

    
    public Book(){
        
    }
    
    public Book(int id, String title, String author, long price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id: ");
        this.id = scanner.nextInt();
        System.out.println("Enter title: ");
        this.title = scanner.nextLine();
        System.out.println("Enter author: ");
        this.author = scanner.nextLine();
        System.out.println("Enter price: ");
        this.price = scanner.nextLong();
    }

    public void output(){
        String msg = """
                BOOK: id = %d, title = %s, author = %s, price = %d""".formatted(this.id, this.title, this.author, this.price);
        System.out.println(msg);
    }
    
}
