package org.example.EngDic;

import org.example.HashTable.HashTable1;
import org.example.HashTable.HashTable2;
import org.example.HashTable.PrefectHashTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class EnglishDictionary {

    private PrefectHashTable hashTable;



    public EnglishDictionary(int order) {
        if ( order == 1 )
            hashTable = new HashTable1<>();
        else if ( order == 2 )
            hashTable = new HashTable2<>();
    }

    private int hash(String key) {
        return key.length() % keys.length;
    }

    public void insert(String key) {
        if (size == capacity) {
            System.out.println("Dictionary is full. Cannot insert.");
            return;
        }
        int index = hash(key);
        while (keys[index] != null) {
            index = (index + 1) % capacity; // Linear probing
        }
        keys[index] = key;
        size++;
    }

    public void delete(String key) {
        int index = search(key);
        if (index != -1) {
            keys[index] = null;
            size--;
        } else {
            System.out.println("Key not found. Cannot delete.");
        }
    }

    public int search(String key) {
        int index = hash(key);
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                return index;
            }
            index = (index + 1) % capacity;
        }
        return -1;
    }

    public void createDictionaryFromFile(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {

                String word = line.trim();

                insert(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteWordsFromDictionary(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String word = line.trim();
                delete(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
