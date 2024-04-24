package org.example;

import org.example.EngDic.EnglishDictionary;
import org.example.HashTable.HashTable1;
import org.example.HashTable.HashTable2;
import org.example.HashTable.PrefectHashTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose the size of the dictionary:");
        System.out.println("1. Size O(n)");
        System.out.println("2. Size O(n^2)");
        System.out.print("Enter your choice: ");
        int sizeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        EnglishDictionary dictionary;
        switch (sizeChoice) {
            case 1:
                dictionary = new EnglishDictionary(1);
                break;
            case 2:
                dictionary = new EnglishDictionary(2);
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
                scanner.close();
                return;
        }

        while (true) {
            System.out.println("Choose an operation:");
            System.out.println("1. Insert a string");
            System.out.println("2. Delete a string");
            System.out.println("3. Search for a string");
            System.out.println("4. Batch insert from file");
            System.out.println("5. Batch delete from file");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the string to insert: ");
                    String insertWord = scanner.nextLine();
                    dictionary.insert(insertWord);
                    break;
                case 2:
                    System.out.print("Enter the string to delete: ");
                    String deleteWord = scanner.nextLine();
                    dictionary.delete(deleteWord);
                    break;
                case 3:
                    System.out.print("Enter the string to search: ");
                    String searchWord = scanner.nextLine();
                    boolean found = dictionary.search(searchWord);
                    if (found) {
                        System.out.println("Word found in the dictionary.");
                    } else {
                        System.out.println("Word not found in the dictionary.");
                    }
                    break;
                case 4:
                    System.out.print("Enter the file path for batch insertion: ");
                    String insertFilePath = scanner.nextLine();
                    dictionary.batchInsert(insertFilePath);
                    break;
                case 5:
                    System.out.print("Enter the file path for batch deletion: ");
                    String deleteFilePath = scanner.nextLine();
                    dictionary.batchDelete(deleteFilePath);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }
}
