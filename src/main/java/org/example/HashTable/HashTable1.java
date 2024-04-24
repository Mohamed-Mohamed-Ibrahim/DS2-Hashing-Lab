package org.example.HashTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HashTable1<T> implements PrefectHashTable<T>{

    private int N;
    private final Random random = new Random();

    private T[] hashTable;
    private T[] keys;
    private int numberOfInsertions;
    private int numberOfDeletions;
    private int numberOfReHashing;

    private int capacity;

    private UniversalMatrix universalMatrix;

    public HashTable1() {
        N = 10*10;
        this.hashTable = (T[]) new Object[N];

        Arrays.fill(hashTable, null);

        this.numberOfInsertions = 0;
        this.numberOfDeletions = 0;
        this.numberOfReHashing = 0;

        this.universalMatrix = new UniversalMatrix(N);
    }

    public HashTable1(int size) {
        N = size*size;
        this.hashTable = (T[]) new Object[N];

        Arrays.fill(hashTable, null);

        this.numberOfInsertions = 0;
        this.numberOfDeletions = 0;
        this.numberOfReHashing = 0;

        this.universalMatrix = new UniversalMatrix(N);
    }

    private void rehash() {
//        printHashTable();
//        System.out.println();
        numberOfReHashing++;

        this.universalMatrix = new UniversalMatrix(N);

        this.hashTable = (T[]) new Object[N];

        Arrays.fill(hashTable, null);

        int hash;

        for ( T key : keys ) {

            if( key == null )
                continue;

            hash = universalMatrix.computeIndex(key);
            if( hashTable[hash] != null ) {
                rehash();
//                System.out.println(12312);
                break;
            }
            hashTable[hash] = key;
        }

        Arrays.fill(keys, null);
        numberOfReHashing++;
        System.out.println("No of rehashing " + numberOfReHashing);

    }

    private void rehash(int newSize) {
//        printHashTable();
//        System.out.println();

        this.universalMatrix = new UniversalMatrix(newSize);

        keys = (T[]) new Object[getCapacity()];
        int i =0;

        for ( T key : hashTable ) {
            if( key != null )
                keys[i++] = key;
        }

        this.hashTable = (T[]) new Object[newSize];

        Arrays.fill(hashTable, null);

        int hash;

        for ( T key : keys ) {

            if( key == null )
                continue;

            hash = universalMatrix.computeIndex(key);
            if( hashTable[hash] != null ) {
                rehash();
//                System.out.println("____");
                break;
            }
            hashTable[hash] = key;
        }

        Arrays.fill(keys, null);
        numberOfReHashing++;
        System.out.println("No of rehashing " + numberOfReHashing);


    }


    @Override
    public Boolean insert(T key) {

        int hash = universalMatrix.computeIndex(key);

        if( key.equals(hashTable[hash]) ){
            System.out.println("Duplicate is Founded ... Please Report");
            return false;
        }

        int newSize = (int) (Math.sqrt(N)+1);

        while (hashTable[hash] != null) {

            if( newSize*newSize != N )
                N = newSize*newSize;

            rehash(N);

            hash = universalMatrix.computeIndex(key);

        }
        numberOfInsertions++;
        hashTable[hash] = key;
        System.out.println("Item is Inserted");
        System.out.println("No of Insertions " + numberOfInsertions);

        return true;
    }

    @Override
    public T delete(T key) {

        int hash = universalMatrix.computeIndex(key);

        if( hashTable[hash] != null ) {
            hashTable[hash] = null;
            numberOfDeletions++;
            System.out.println("Item is deleted");
            System.out.println("No of deletions " + numberOfDeletions);
            return key;
        }
        System.out.println("Item is not Found ... Therefore not deleted");
        return key;
    }


    @Override
    public Boolean search(T key) {

        int hash = universalMatrix.computeIndex(key);

        if( hashTable[hash] != null ){
            System.out.println("Item is Found");
            return true;
        }

        System.out.println("Item is not Found");
        return false;
    }

    @Override
    public void batchInsert(String filePath) {

        List<T> words = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                T word = (T) line.trim();
                words.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        N = (int) Math.sqrt(N) + words.size();

        N = N*N;

        rehash(N);

        for (T word : words) {

            insert(word);

        }
//        printHashTable();
    }

    @Override
    public void batchDelete(String filePath) {

        List<T> words = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                T word = (T) line.trim();
                words.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (T word : words) {
            delete(word);
        }

    }
    public int getNumberOfInsertions() {
        return numberOfInsertions;
    }

    public int getNumberOfDeletions() {
        return numberOfDeletions;
    }

    public int getNumberOfReHashing() {
        return numberOfReHashing;
    }

    public int getCapacity() {
        return numberOfInsertions - numberOfDeletions + 1;
    }

    public void printHashTable() {
        for ( T key : hashTable ) {
            if( key != null )
                System.out.print(key + " | ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Create a hashtable
        HashTable1<String> hashtable = new HashTable1<>(5);

        // Test insertions
//        System.out.println("Inserting elements...");
//        hashtable.insert(new String("apple"));
//        hashtable.insert(new String("apple"));
//        hashtable.insert("banana");
//        hashtable.insert("cherry");
//        hashtable.insert("date");
//        System.out.println("Number of insertions: " + hashtable.getNumberOfInsertions());
//
//        // Test searches
//        System.out.println("Searching for elements...");
//        System.out.println("Is 'apple' present? " + hashtable.search("apple")); // Should return true
//        System.out.println("Is 'grape' present? " + hashtable.search("grape")); // Should return false
//
//        // Test deletions
//        System.out.println("Deleting elements...");
//        hashtable.delete("banana");
//        System.out.println("Number of deletions: " + hashtable.getNumberOfDeletions());
//        System.out.println("Is 'banana' present? " + hashtable.search("banana")); // Should return false

        // Test batch insertions and deletions
//        System.out.println("Batch insertions and deletions...");
//        hashtable.printHashTable();
//        hashtable.batchInsert("C:/Users/Al-Gawad/Desktop/demo_ass2_test.txt");
//        hashtable.printHashTable();
//        hashtable.batchDelete("C:/Users/Al-Gawad/Desktop/demo_ass2_test - Copy.txt");
//        hashtable.printHashTable();
//        System.out.println("Number of rehashing: " + hashtable.getNumberOfReHashing());

        // Insert a large number of elements to trigger rehashing
//        System.out.println("Inserting elements to trigger rehashing...");
//        for (int i = 0; i < 100; i++) {
//            hashtable.insert("element" + i);
//        }
//
//        // Print the size of the hashtable before and after rehashing
//        System.out.println("Size of the hashtable before rehashing: " + hashtable.getNumberOfInsertions());
//        System.out.println("Number of rehashing operations: " + hashtable.getNumberOfReHashing());
//        System.out.println("Size of the hashtable after rehashing: " + hashtable.getNumberOfInsertions());

    }
}
