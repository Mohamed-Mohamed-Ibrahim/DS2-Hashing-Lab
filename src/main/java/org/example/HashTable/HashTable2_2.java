package org.example.HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HashTable2_2<T> implements PrefectHashTable<T>{

    private int N = 10;
    private int[] secondN;
    private final Random random = new Random();

    private List<T[]> hashTable;
    private T[] keys;
    private int numberOfInsertions;
    private int numberOfDeletions;
    private int numberOfReHashing;
    private UniversalMatrix[] universalMatrices;
    private UniversalMatrix universalMatrix;

    public HashTable2_2() {

        this.hashTable = new ArrayList<>(N);

        secondN = new int[N];

        Arrays.fill(secondN, 2);

        for (int i = 0; i < N; i++) {

            T[] temp = (T[]) new Object[secondN[i]];

            Arrays.fill(temp, null);

            hashTable.add(temp);
        }

        this.numberOfInsertions = 0;
        this.numberOfDeletions = 0;
        this.numberOfReHashing = 0;

        this.universalMatrix = new UniversalMatrix(N);

        this.universalMatrices = new UniversalMatrix[N];

        for (int i = 0; i < N; i++) {
            universalMatrices[i] = new UniversalMatrix(secondN[i]);
        }

    }

    private void rehash() {

        this.universalMatrix = new UniversalMatrix(N);

        this.universalMatrices = new UniversalMatrix[N];

        for (int i = 0; i < N; i++) {
            universalMatrices[i] = new UniversalMatrix(secondN[i]);
        }

        this.hashTable = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {

            T[] temp = (T[]) new Object[secondN[i]];

            Arrays.fill(temp, null);

            hashTable.add(temp);
        }

        int hash1, hash2;

        for ( T key : keys ) {

            if( key == null )
                continue;

            hash1 = universalMatrix.computeIndex(key);
            hash2 = universalMatrices[hash1].computeIndex(key);

            if( hashTable.get(hash1)[hash2] != null ) {
                rehash();
//                System.out.println("____");
                break;
            }
            hashTable.get(hash1)[hash2] = key;
        }

        Arrays.fill(keys, null);
        numberOfReHashing++;
        System.out.println("No of rehashing " + numberOfReHashing);

    }

    private void rehash(int newSize, int changeSizeI) {
//        System.out.println(N);

        int[] temp1 = secondN.clone();

        secondN = new int[N];

        Arrays.fill(secondN, 2);

        for (int i = 0; i < temp1.length; i++) {
            if( i == changeSizeI )
                secondN[i] = newSize;
            else
                secondN[i] = temp1[i];
        }

        this.universalMatrix = new UniversalMatrix(N);

        this.universalMatrices = new UniversalMatrix[N];

        for (int i = 0; i < N; i++) {
            universalMatrices[i] = new UniversalMatrix(secondN[i]);
        }



        keys = (T[]) new Object[getCapacity()];
        Arrays.fill(keys, null);
        int i=0;

        for (T[] array : hashTable) {
            if (array != null) {
                for (T element : array) {
                    if( element != null )
                        keys[i++] = element;
                }
            }
        }

        this.hashTable = new ArrayList<>(N);

        for (i = 0; i < N; i++) {

            T[] temp = (T[]) new Object[secondN[i]];

            Arrays.fill(temp, null);

            hashTable.add(temp);
        }

        int hash1, hash2;

        for ( int k=0; k<keys.length; k++) {
//        for ( T key : keys ) {

            if( keys[k] == null )
                continue;

            hash1 = universalMatrix.computeIndex(keys[k]);
            hash2 = universalMatrices[hash1].computeIndex(keys[k]);
            while ( hashTable.get(hash1)[hash2] != null ) {
//                rehash();
                System.out.println("____");
//                break;

                this.universalMatrix = new UniversalMatrix(N);

                this.universalMatrices = new UniversalMatrix[N];

                for (i = 0; i < N; i++) {
                    universalMatrices[i] = new UniversalMatrix(secondN[i]);
                }

                this.hashTable = new ArrayList<>(N);

                for (i = 0; i < N; i++) {

                    T[] temp = (T[]) new Object[secondN[i]];

                    Arrays.fill(temp, null);

                    hashTable.add(temp);
                }

                k=0;
                hash1 = universalMatrix.computeIndex(keys[k]);
                hash2 = universalMatrices[hash1].computeIndex(keys[k]);

            }
            hashTable.get(hash1)[hash2] = keys[k];

        }

        Arrays.fill(keys, null);
        numberOfReHashing++;
        System.out.println("No of rehashing " + numberOfReHashing);
    }



    public Boolean insert(T key) {
        int hash1 = this.universalMatrix.computeIndex(key);

        int hash2 = this.universalMatrices[hash1].computeIndex(key);

        if( key.equals(hashTable.get(hash1)[hash2]) ){
            System.out.println("Duplicate is Founded ... Please Report");
            return false;
        }

        if( N <= getCapacity() ) {
            N = N + 10;
            rehash(N, -1);
        }


        int newSize = (int) Math.sqrt(secondN[hash2]) + 1;

        while ( hashTable.get(hash1)[hash2] != null ) {

            if( newSize*newSize != secondN[hash2] )
                secondN[hash2] = newSize*newSize;

            rehash(N, hash2);

            hash2 = this.universalMatrices[hash1].computeIndex(key);

        }

        hashTable.get(hash1)[hash2] = key;
        numberOfInsertions++;
        System.out.println("Item is Inserted");
        System.out.println("No of Insertions " + numberOfInsertions);
        printHashTable();
        return true;
    }

    @Override
    public T delete(T key) {
        int hash1 = universalMatrix.computeIndex(key);
        int hash2 = universalMatrices[hash1].computeIndex(key);

        if( hashTable.get(hash1)[hash2] != null ) {
            hashTable.get(hash1)[hash2] = null;
            numberOfDeletions++;
            System.out.println("Item is deleted");
            System.out.println("No of deletions " + numberOfDeletions);
            return key;
        }
        System.out.println("Item is not Found ... Therefore not deleted");
        return key;
    }

    public Boolean search(T key) {
        int hash1 = universalMatrix.computeIndex(key);
        int hash2 = universalMatrices[hash1].computeIndex(key);

        if( key.equals(hashTable.get(hash1)[hash2])){
            System.out.println("Item is Found");
            return true;
        }

        System.out.println("Item is not Found");
        return false;
    }

    @Override
    public void batchInsert(String filePath) {

    }

    @Override
    public void batchDelete(String filePath) {

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
        int i=0;
        System.out.println("___");
        for (T[] array : hashTable) {
            if (array != null) {
                System.out.print(i++ + " : ");
                for (T element : array) {
                    if( element != null )
                        System.out.print(element + " | ");
                }
                System.out.println();
            }
        }
        System.out.println("\n___");
    }

    public static void main(String[] args) {
        HashTable2_2<String> hashTable = new HashTable2_2<>();

//        System.out.println("Inserting elements...");
//        hashTable.insert(new String("apple"));
//        hashTable.insert(new String("apple"));
//        hashTable.insert("banana");
//        hashTable.insert("cherry");
//        hashTable.insert("date");
//        System.out.println("Number of insertions: " + hashTable.getNumberOfInsertions());
//
//        // Test searches
//        System.out.println("Searching for elements...");
//        System.out.println("Is 'apple' present? " + hashTable.search("apple")); // Should return true
//        System.out.println("Is 'grape' present? " + hashTable.search("grape")); // Should return false
//
//        // Test deletions
//        System.out.println("Deleting elements...");
//        hashTable.delete("banana");
//        System.out.println("Number of deletions: " + hashTable.getNumberOfDeletions());
//        System.out.println("Is 'banana' present? " + hashTable.search("banana")); // Should return false

        System.out.println("Inserting elements to trigger rehashing...");
        for (int i = 0; i < 100; i++) {
            hashTable.insert("element" + i);
        }

        // Print the size of the hashtable before and after rehashing
        System.out.println("Size of the hashtable before rehashing: " + hashTable.getNumberOfInsertions());
        System.out.println("Number of rehashing operations: " + hashTable.getNumberOfReHashing());
        System.out.println("Size of the hashtable after rehashing: " + hashTable.getNumberOfInsertions());
    }


}
