/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exercise_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author nam.huynh
 */
public class Main {
    
    private static List<Book> books = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean running = true;
        
        while (running) {
            displayMenu();
            int choice = getChoice();
            
            String result = switch (choice) {
                case 1 -> addBook();
                case 2 -> displayAllBooks();
                case 3 -> searchById();
                case 4 -> searchByAuthor();
                case 5 -> searchByTitle();
                case 6 -> searchByPriceRange();
                case 7 -> updateBook();
                case 8 -> deleteBook();
                case 9 -> {
                    running = false;
                    yield "Thank you for using Book Manager!";
                }
                default -> "Invalid option! Please choose 1-9.";
            };
            
            System.out.println(result);
        }
        
        scanner.close();
    }
    
    private static void displayMenu() {
        System.out.println("\n=== BOOK MANAGER ===");
        System.out.println("1. Add Book");
        System.out.println("2. Display All Books");
        System.out.println("3. Search by ID");
        System.out.println("4. Search by Author");
        System.out.println("5. Search by Title");
        System.out.println("6. Search by Price Range");
        System.out.println("7. Update Book");
        System.out.println("8. Delete Book");
        System.out.println("9. Exit");
        System.out.print("Choose an option: ");
    }
    
    private static int getChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return choice;
    }
    
    private static String addBook() {
        System.out.println("\n=== ADD NEW BOOK ===");
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        
        System.out.print("Enter Price: ");
        long price = scanner.nextLong();
        scanner.nextLine();
        
        Book newBook = new Book(id, title, author, price);
        books.add(newBook);
        
        return "Book added successfully!";
    }
    
    private static String displayAllBooks() {
        if (books.isEmpty()) {
            return "No books available!";
        }
        
        System.out.println("\n=== ALL BOOKS ===");
        // Using stream with filter and limit
        // Filter books with valid price > 0 and limit to first 10
        books.stream()
            .filter(book -> book.getPrice() > 0) // Filter valid prices
            .limit(10) // Limit to first 10 books
            .forEach(book -> {
                System.out.println("----------------------------------------");
                book.output();
            });
        
        // Using lambda to calculate statistics with filter
        long totalPrice = books.stream()
            .filter(book -> book.getPrice() > 0) // Filter valid prices
            .mapToLong(Book::getPrice)
            .sum();
        
        double avgPrice = books.stream()
            .filter(book -> book.getPrice() > 0) // Filter valid prices
            .mapToLong(Book::getPrice)
            .average()
            .orElse(0.0);
        
        // Count expensive books (price > 50000) using filter
        long expensiveBooksCount = books.stream()
            .filter(book -> book.getPrice() > 50000)
            .count();
        
        System.out.println("----------------------------------------");
        System.out.printf("Total books: %d | Total price: %d | Average price: %.2f | Expensive books (>50000): %d%n", 
            books.size(), totalPrice, avgPrice, expensiveBooksCount);
        
        return "Display completed!";
    }
    
    private static String searchById() {
        System.out.print("\nEnter Book ID: ");
        int searchId = scanner.nextInt();
        scanner.nextLine();
        
        // Using lambda expression with Predicate
        Predicate<Book> byId = book -> book.getId() == searchId;
        
        List<Book> found = books.stream()
            .filter(byId)
            .collect(Collectors.toList());
        
        if (found.isEmpty()) {
            return "Book not found!";
        }
        
        System.out.println("\n=== SEARCH RESULT ===");
        found.forEach(Book::output); // Method reference
        
        return "Found " + found.size() + " book(s)";
    }
    
    private static String searchByAuthor() {
        System.out.print("\nEnter Author Name: ");
        String author = scanner.nextLine();
        
        // Using lambda expression with filter and limit
        Predicate<Book> byAuthor = book -> 
            book.getAuthor().equalsIgnoreCase(author);
        
        List<Book> found = books.stream()
            .filter(byAuthor)
            .filter(book -> book.getPrice() > 0) // Additional filter for valid prices
            .limit(5) // Limit results to first 5 books
            .collect(Collectors.toList());
        
        if (found.isEmpty()) {
            return "No books found by this author!";
        }
        
        System.out.println("\n=== BOOKS BY " + author.toUpperCase() + " ===");
        found.forEach(book -> {
            System.out.println("----------------------------------------");
            book.output();
        });
        
        return "Found " + found.size() + " book(s) (showing up to 5)";
    }
    
    private static String searchByTitle() {
        System.out.print("\nEnter Book Title: ");
        String title = scanner.nextLine();
        
        // Using lambda expression with filter and limit
        Predicate<Book> byTitle = book -> 
            book.getTitle().equalsIgnoreCase(title);
        
        List<Book> found = books.stream()
            .filter(byTitle)
            .filter(book -> book.getId() > 0) // Additional filter for valid IDs
            .limit(3) // Limit results to first 3 books
            .collect(Collectors.toList());
        
        if (found.isEmpty()) {
            return "No books found with this title!";
        }
        
        System.out.println("\n=== BOOKS WITH TITLE: " + title.toUpperCase() + " ===");
        found.forEach(book -> {
            System.out.println("----------------------------------------");
            book.output();
        });
        
        return "Found " + found.size() + " book(s) (showing up to 3)";
    }
    
    private static String searchByPriceRange() {
        System.out.print("\nEnter Minimum Price: ");
        long minPrice = scanner.nextLong();
        
        System.out.print("Enter Maximum Price: ");
        long maxPrice = scanner.nextLong();
        scanner.nextLine();
        
        // Using lambda expression for price range with filter and limit
        Predicate<Book> byPriceRange = book -> 
            book.getPrice() >= minPrice && book.getPrice() <= maxPrice;
        
        List<Book> found = books.stream()
            .filter(byPriceRange)
            .filter(book -> book.getPrice() > 0) // Additional filter for valid prices
            .limit(10) // Limit results to first 10 books
            .collect(Collectors.toList());
        
        if (found.isEmpty()) {
            return "No books found in this price range!";
        }
        
        System.out.println("\n=== BOOKS IN PRICE RANGE [" + minPrice + " - " + maxPrice + "] ===");
        found.forEach(book -> {
            System.out.println("----------------------------------------");
            book.output();
        });
        
        return "Found " + found.size() + " book(s) (showing up to 10)";
    }
    
    private static String updateBook() {
        System.out.print("\nEnter Book ID to update: ");
        int updateId = scanner.nextInt();
        scanner.nextLine();
        
        // Using lambda to find book with filter and limit
        Book bookToUpdate = books.stream()
            .filter(book -> book.getId() == updateId)
            .filter(book -> book.getId() > 0) // Additional filter for valid ID
            .limit(1) // Limit to first match
            .findFirst()
            .orElse(null);
        
        if (bookToUpdate == null) {
            return "Book not found!";
        }
        
        System.out.println("\nCurrent book information:");
        bookToUpdate.output();
        System.out.println("\nEnter new information (press Enter to keep current value):");
        
        System.out.print("Enter new Title: ");
        String newTitle = scanner.nextLine();
        if (!newTitle.isEmpty()) {
            bookToUpdate.setTitle(newTitle);
        }
        
        System.out.print("Enter new Author: ");
        String newAuthor = scanner.nextLine();
        if (!newAuthor.isEmpty()) {
            bookToUpdate.setAuthor(newAuthor);
        }
        
        System.out.print("Enter new Price (or 0 to keep current): ");
        long newPrice = scanner.nextLong();
        scanner.nextLine();
        if (newPrice > 0) {
            bookToUpdate.setPrice(newPrice);
        }
        
        System.out.println("\nUpdated book information:");
        bookToUpdate.output();
        
        return "Book updated successfully!";
    }
    
    private static String deleteBook() {
        System.out.print("\nEnter Book ID to delete: ");
        int deleteId = scanner.nextInt();
        scanner.nextLine();
        
        // Using lambda with removeIf
        boolean removed = books.removeIf(book -> book.getId() == deleteId);
        
        return removed ? "Book deleted successfully!" : "Book not found!";
    }
}

