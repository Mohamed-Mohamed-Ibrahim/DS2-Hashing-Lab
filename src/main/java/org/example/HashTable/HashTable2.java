package org.example.HashTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HashTable2<T> implements PrefectHashTable<T>{

    private int N = 10;
    private int[] secondN;

    private List<T[]> hashTable;
    private T[] keys;
    private int numberOfInsertions;
    private int numberOfDeletions;
    private int numberOfReHashing;
    private UniversalMatrix[] universalMatrices;
    private UniversalMatrix universalMatrix;

    public HashTable2() {

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


    private void rehash(int newSize, int changeSizeI) {
//        printHashTable();
        int[] temp1 = secondN.clone();

        secondN = new int[N];

        Arrays.fill(secondN, 2);

        for (int i = 0; i < temp1.length; i++) {
            if( i == changeSizeI )
                secondN[i] = newSize;
            else
                secondN[i] = temp1[i];
        }

        if( universalMatrix.M != N )
            universalMatrix = new UniversalMatrix(N);
        else
            universalMatrix.generate();

        this.universalMatrices = new UniversalMatrix[N];

        for (int i = 0; i < N; i++) {
            if( universalMatrices[i] == null || universalMatrices[i].M != secondN[i] )
                universalMatrices[i] = new UniversalMatrix(secondN[i]);
            else
                universalMatrices[i].generate();
        }



        if( keys == null || keys.length != getCapacity() )
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

        int hash1, hash;

        for ( int k=0; k<keys.length; k++) {
//        for ( T key : keys ) {

            if( keys[k] == null )
                continue;

            hash1 = universalMatrix.computeIndex(keys[k]);
            hash = universalMatrices[hash1].computeIndex(keys[k]);
//            System.out.println(321);
            while ( hashTable.get(hash1)[hash] != null ) {
//                rehash();
                numberOfReHashing++;
//                System.out.println("____1");
//                break;
//                T[] temp2 = (T[]) new Object[secondN[hash1]];
//                Arrays.fill(temp2, null);
//                hashTable.set(hash1, temp2);
//                universalMatrices[hash1] = new UniversalMatrix(secondN[hash1]);

//                k = 0;
                rehashSecond(secondN[hash1]+secondN[hash1], hash1);
                hash = universalMatrices[hash1].computeIndex(keys[k]);
//                System.out.println(hash);
            }
            hashTable.get(hash1)[hash] = keys[k];

        }

        Arrays.fill(keys, null);
        System.out.println("No of rehashing " + numberOfReHashing);
    }

    private void rehashSecond(int newSize, int changeSizeI) {
//        System.out.println(N);
        T[] temp1 = hashTable.get(changeSizeI).clone();
//        System.out.println(newSize+" "+ temp1.length);
        T[] temp2 = (T[]) new Object[newSize];
        Arrays.fill(temp2, null);
        hashTable.set(changeSizeI, temp2);
        secondN[changeSizeI] = newSize;
        if( universalMatrices[changeSizeI].M != secondN[changeSizeI] )
            universalMatrices[changeSizeI] = new UniversalMatrix(secondN[changeSizeI]);
        else
            universalMatrices[changeSizeI].generate();


        T[] keys2 = (T[]) new Object[temp1.length];
        Arrays.fill(keys2, null);
        int i=0;

        for (T element : temp1) {
            if (element != null) {
                keys2[i++] = element;
            }
        }

        int hash;

        for ( int k=0; k<keys2.length; k++) {
//        for ( T key : keys2 ) {

            if (keys2[k] == null)
                continue;

            hash = universalMatrices[changeSizeI].computeIndex(keys2[k]);
//            System.out.println(123);
            while (hashTable.get(changeSizeI)[hash] != null) {
                numberOfReHashing++;
//                rehash();
//                System.out.println("____");
//                break;
                Arrays.fill(temp2, null);
                hashTable.set(changeSizeI, temp2);
                if( universalMatrices[changeSizeI].M != secondN[changeSizeI] )
                    universalMatrices[changeSizeI] = new UniversalMatrix(secondN[changeSizeI]);
                else
                    universalMatrices[changeSizeI].generate();

//                k = 0;
                hash = universalMatrices[changeSizeI].computeIndex(keys2[k]);
//                System.out.println(hashTable.get(changeSizeI)[0]);

            }

            hashTable.get(changeSizeI)[hash] = keys2[k];
        }
        Arrays.fill(keys2, null);
        System.out.println("No of rehashing " + numberOfReHashing);
    }

    public Boolean insert(T key) {
        int hash1 = this.universalMatrix.computeIndex(key);

        int hash2 = this.universalMatrices[hash1].computeIndex(key);

        if( hashTable.get(hash1)[hash2] != null && key.hashCode() == (hashTable.get(hash1)[hash2].hashCode()) ){
            System.out.println("Duplicate is Founded ... Please Report");
//            System.out.println(key);
//            System.out.println(hashTable.get(hash1)[hash2]);
            return false;
        }

        if( 0.7 <= getCapacity() / N ) {
            N = 2 * N;
            rehash(N, -1);
        }


        int newSize = (int) Math.sqrt(secondN[hash1]) + 1;

        while ( hashTable.get(hash1)[hash2] != null ) {

            if( newSize*newSize != secondN[hash1] )
                secondN[hash1] = newSize*newSize;
//            System.out.println(newSize);
            rehashSecond(secondN[hash1], hash1);

            hash2 = this.universalMatrices[hash1].computeIndex(key);

        }

        hashTable.get(hash1)[hash2] = key;
        numberOfInsertions++;
        System.out.println("Item is Inserted");
        System.out.println("No of Insertions " + numberOfInsertions);
//        printHashTable();
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
//            System.out.println("No of deletions " + numberOfDeletions);
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

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                T word = (T) line.trim();
                insert(word); // Insert each word into your data structure immediately
                count++;
            }

            // Calculate the initial size for rehashing
            N = (int) Math.sqrt(N) + count + N;
//
//            rehash(N, -1); // Rehash with the calculated initial size
        } catch (IOException e) {
            e.printStackTrace();
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
        int i=0;
        System.out.println("___");
        for (T[] array : hashTable) {
            if (array != null) {
//                System.out.print(i++ + " : ");
                for (T element : array) {
                    if( element != null )
                        System.out.print(element + " | ");
//                        i++;
                }
                System.out.println();
            }
        }

        System.out.println("\n___");
    }
    public static String generateRandomString(Random random, int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(characters.charAt(random.nextInt(characters.length())));
        }
        return stringBuilder.toString();
    }
    public static void main(String[] args) {
        HashTable2<String> hashTable = new HashTable2<>();

        System.out.println("Inserting elements...");
        hashTable.insert(new String("apple"));
        hashTable.insert(new String("apple"));
        hashTable.insert("banana");
        hashTable.insert("banana");
        hashTable.insert("cherry");
        hashTable.insert("date");
        System.out.println("Number of insertions: " + hashTable.getNumberOfInsertions());

        // Test searches
        System.out.println("Searching for elements...");
        System.out.println("Is 'apple' present? " + hashTable.search("apple")); // Should return true
        System.out.println("Is 'grape' present? " + hashTable.search("grape")); // Should return false

        // Test deletions
        System.out.println("Deleting elements...");
        hashTable.delete("banana");
        System.out.println("Number of deletions: " + hashTable.getNumberOfDeletions());
        System.out.println("Is 'banana' present? " + hashTable.search("banana")); // Should return false

//        System.out.println("Inserting elements to trigger rehashing...");
//        for (int i = 0; i < 10000000; i++) {
//            hashTable.insert("element" + i);
//        }
//
//        // Print the size of the hashtable before and after rehashing
//        System.out.println("Size of the hashtable before rehashing: " + hashTable.getNumberOfInsertions());
//        System.out.println("Number of rehashing operations: " + hashTable.getNumberOfReHashing());
//        System.out.println("Size of the hashtable after rehashing: " + hashTable.getNumberOfInsertions());
//        hashTable.printHashTable();

        // Test batch insertions and deletions
//        System.out.println("Batch insertions and deletions...");
////        hashTable.printHashTable();
//        hashTable.batchInsert("C:/Users/Al-Gawad/Desktop/insert_words.txt");
////        hashTable.printHashTable();
////        hashTable.batchDelete("C:/Users/Al-Gawad/Desktop/demo_ass2_test - Copy.txt");
////        hashTable.printHashTable();
//        System.out.println("Number of rehashing: " + hashTable.getNumberOfReHashing());


        Random random = new Random();
        System.out.println("Inserting elements to trigger rehashing...");

        HashMap<String, Integer> elementsCount = new HashMap<>();
        int duplicates = 0;
        for (int i = 0; i < 10000000; i++) {
            String randomElement = generateRandomString(random, 10); // Change 10 to desired length

            // Check if the element is already present
            if (elementsCount.containsKey(randomElement)) {
                duplicates++;
                elementsCount.put(randomElement, elementsCount.get(randomElement) + 1);
            } else {
                elementsCount.put(randomElement, 1);
            }
        }

        int unique = elementsCount.size();
        System.out.println("Duplicates: " + duplicates);
        System.out.println("Unique elements: " + unique);
//        hashTable.printHashTable();
//        // Print the size of the hashtable before and after rehashing
//        System.out.println("Size of the hashtable before rehashing: " + hashTable.getNumberOfInsertions());
//        System.out.println("Number of rehashing operations: " + hashTable.getNumberOfReHashing());
//        System.out.println("Size of the hashtable after rehashing: " + hashTable.getNumberOfInsertions());
////        hashtable.printHashTable();
        System.out.println(hashTable.getCapacity());;

        }


}
