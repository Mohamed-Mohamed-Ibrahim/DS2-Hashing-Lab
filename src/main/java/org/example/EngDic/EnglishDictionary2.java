package org.example.EngDic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class EnglishDictionary2 {

    private Hashtable<String, Integer> dictionary;

    public EnglishDictionary2(int capacity) {
        dictionary = new Hashtable<>(capacity);
    }

    public void insert(String key) {
        dictionary.put(key, key.length());
    }

    public void delete(String key) {
        dictionary.remove(key);
    }

    public boolean search(String key) {
        return dictionary.containsKey(key);
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
