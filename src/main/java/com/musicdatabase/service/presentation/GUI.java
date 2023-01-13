package com.musicdatabase.service.presentation;

import com.musicdatabase.service.model.Action;

import java.util.Scanner;

public class GUI {
    public static Action menu() {
        System.out.println("\n\n------------------");
        System.out.println("1. Close application");
        System.out.println("2. Show all songs");
        System.out.println("3. Shows songs of author");
        System.out.println("4. Show all albums");
        System.out.println("5. Show all albums of author from a specific year");
        System.out.println("6. Show all authors");
        System.out.println("------------------");
        System.out.println("Choose an option: ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1 -> {
                System.out.println("Closing application...");
                System.exit(0);
            }
            case 2 -> {
                System.out.println("Showing all songs...");
                return Action.SHOW_SONGS;
            }
            case 3 -> {
                System.out.println("Showing songs of author...");
                return Action.SHOW_SONGS_OF_AUTHOR;
            }
            case 4 -> {
                System.out.println("Showing all albums...");
                return Action.SHOW_ALBUMS;
            }
            case 5 -> {
                System.out.println("Showing all albums of author from a specific year...");
                return Action.SHOW_ALBUMS_OF_AUTHOR_FROM_YEAR;
            }
            case 6 -> {
                System.out.println("Showing all authors...");
                return Action.SHOW_AUTHORS;
            }
            default -> {
                System.out.println("Invalid option");
                menu();
            }
        }
        return null;
    }
}
