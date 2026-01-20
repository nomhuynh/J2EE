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
                    yield "Cam on ban da su dung Quan ly Sach!";
                }
                default -> "Lua chon khong hop le! Vui long chon tu 1-9.";
            };

            System.out.println(result);
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n=== QUAN LY SACH ===");
        System.out.println("1. Them Sach");
        System.out.println("2. Hien thi Tat ca Sach");
        System.out.println("3. Tim kiem theo ID");
        System.out.println("4. Tim kiem theo Tac gia");
        System.out.println("5. Tim kiem theo Tieu de");
        System.out.println("6. Tim kiem theo Khoang gia");
        System.out.println("7. Cap nhat Sach");
        System.out.println("8. Xoa Sach");
        System.out.println("9. Thoat");
        System.out.print("Chon mot tuy chon: ");
    }

    private static int getChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    private static String addBook() {
        System.out.println("\n=== THEM SACH MOI ===");
        System.out.print("Nhap ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhap Tieu de: ");
        String title = scanner.nextLine();

        System.out.print("Nhap Tac gia: ");
        String author = scanner.nextLine();

        System.out.print("Nhap Gia: ");
        long price = scanner.nextLong();
        scanner.nextLine();

        Book newBook = new Book(id, title, author, price);
        books.add(newBook);

        return "Them sach thanh cong!";
    }

    private static String displayAllBooks() {
        if (books.isEmpty()) {
            return "Khong co sach nao!";
        }

        System.out.println("\n=== TAT CA SACH ===");
        books.stream()
                .filter(book -> book.getPrice() > 0)
                .limit(10)
                .forEach(book -> {
                    System.out.println("----------------------------------------");
                    book.output();
                });

        long totalPrice = books.stream()
                .filter(book -> book.getPrice() > 0)
                .mapToLong(Book::getPrice)
                .sum();

        double avgPrice = books.stream()
                .filter(book -> book.getPrice() > 0)
                .mapToLong(Book::getPrice)
                .average()
                .orElse(0.0);

        long expensiveBooksCount = books.stream()
                .filter(book -> book.getPrice() > 50000)
                .count();

        System.out.println("----------------------------------------");
        System.out.printf("Tong so sach: %d | Tong gia: %d | Gia trung binh: %.2f | Sach dat tien (>50000): %d%n",
                books.size(), totalPrice, avgPrice, expensiveBooksCount);

        return "Hien thi hoan tat!";
    }

    private static String searchById() {
        System.out.print("\nNhap ID Sach: ");
        int searchId = scanner.nextInt();
        scanner.nextLine();

        Predicate<Book> byId = book -> book.getId() == searchId;

        List<Book> found = books.stream()
                .filter(byId)
                .collect(Collectors.toList());

        if (found.isEmpty()) {
            return "Khong tim thay sach!";
        }

        System.out.println("\n=== KET QUA TIM KIEM ===");
        found.forEach(Book::output);

        return "Tim thay " + found.size() + " cuon sach";
    }

    private static String searchByAuthor() {
        System.out.print("\nNhap Ten Tac gia: ");
        String author = scanner.nextLine();

        Predicate<Book> byAuthor = book -> book.getAuthor().equalsIgnoreCase(author);

        List<Book> found = books.stream()
                .filter(byAuthor)
                .filter(book -> book.getPrice() > 0)
                .limit(5)
                .collect(Collectors.toList());

        if (found.isEmpty()) {
            return "Khong tim thay sach cua tac gia nay!";
        }

        System.out.println("\n=== SACH CUA " + author.toUpperCase() + " ===");
        found.forEach(book -> {
            System.out.println("----------------------------------------");
            book.output();
        });

        return "Tim thay " + found.size() + " cuon sach (hien thi toi da 5)";
    }

    private static String searchByTitle() {
        System.out.print("\nNhap Tieu de Sach: ");
        String title = scanner.nextLine();

        Predicate<Book> byTitle = book -> book.getTitle().equalsIgnoreCase(title);

        List<Book> found = books.stream()
                .filter(byTitle)
                .filter(book -> book.getId() > 0)
                .limit(3)
                .collect(Collectors.toList());

        if (found.isEmpty()) {
            return "Khong tim thay sach voi tieu de nay!";
        }

        System.out.println("\n=== SACH VOI TIEU DE: " + title.toUpperCase() + " ===");
        found.forEach(book -> {
            System.out.println("----------------------------------------");
            book.output();
        });

        return "Tim thay " + found.size() + " cuon sach (hien thi toi da 3)";
    }

    private static String searchByPriceRange() {
        System.out.print("\nNhap Gia toi thieu: ");
        long minPrice = scanner.nextLong();

        System.out.print("Nhap Gia toi da: ");
        long maxPrice = scanner.nextLong();
        scanner.nextLine();

        Predicate<Book> byPriceRange = book -> book.getPrice() >= minPrice && book.getPrice() <= maxPrice;

        List<Book> found = books.stream()
                .filter(byPriceRange)
                .filter(book -> book.getPrice() > 0)
                .limit(10)
                .collect(Collectors.toList());

        if (found.isEmpty()) {
            return "Khong tim thay sach trong khoang gia nay!";
        }

        System.out.println("\n=== SACH TRONG KHOANG GIA [" + minPrice + " - " + maxPrice + "] ===");
        found.forEach(book -> {
            System.out.println("----------------------------------------");
            book.output();
        });

        return "Tim thay " + found.size() + " cuon sach (hien thi toi da 10)";
    }

    private static String updateBook() {
        System.out.print("\nNhap ID Sach de cap nhat: ");
        int updateId = scanner.nextInt();
        scanner.nextLine();

        Book bookToUpdate = books.stream()
                .filter(book -> book.getId() == updateId)
                .filter(book -> book.getId() > 0)
                .limit(1)
                .findFirst()
                .orElse(null);

        if (bookToUpdate == null) {
            return "Khong tim thay sach!";
        }

        System.out.println("\nThong tin sach hien tai:");
        bookToUpdate.output();
        System.out.println("\nNhap thong tin moi (nhan Enter de giu gia tri hien tai):");

        System.out.print("Nhap Tieu de moi: ");
        String newTitle = scanner.nextLine();
        if (!newTitle.isEmpty()) {
            bookToUpdate.setTitle(newTitle);
        }

        System.out.print("Nhap Tac gia moi: ");
        String newAuthor = scanner.nextLine();
        if (!newAuthor.isEmpty()) {
            bookToUpdate.setAuthor(newAuthor);
        }

        System.out.print("Nhap Gia moi (hoac 0 de giu hien tai): ");
        long newPrice = scanner.nextLong();
        scanner.nextLine();
        if (newPrice > 0) {
            bookToUpdate.setPrice(newPrice);
        }

        System.out.println("\nThong tin sach da cap nhat:");
        bookToUpdate.output();

        return "Cap nhat sach thanh cong!";
    }

    private static String deleteBook() {
        System.out.print("\nNhap ID Sach de xoa: ");
        int deleteId = scanner.nextInt();
        scanner.nextLine();

        boolean removed = books.removeIf(book -> book.getId() == deleteId);

        return removed ? "Xoa sach thanh cong!" : "Khong tim thay sach!";
    }
}
