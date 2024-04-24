package org.example.EngDic;

import org.example.HashTable.HashTable1;
import org.example.HashTable.HashTable2;
import org.example.HashTable.PrefectHashTable;

public class EnglishDictionary<T> {

    private PrefectHashTable<T> hashTable;


    public EnglishDictionary(int order) {
        if ( order == 1 )
            hashTable = new HashTable1<>();
        else if ( order == 2 )
            hashTable = new HashTable2<>();
    }


    public Boolean insert(T key) {
        return hashTable.insert(key);
    }

    public T delete(T key) {
        return hashTable.delete(key);
    }

    public Boolean search(T key) {
        return hashTable.search(key);
    }

    public void batchInsert(String filePath) {

        hashTable.batchInsert(filePath);

    }

    public void batchDelete(String filePath) {

        hashTable.batchDelete(filePath);

    }

        public static void main(String[] args) {
            // Create an EnglishDictionary with HashTable1
            EnglishDictionary<String> dictionary1 = new EnglishDictionary<>(1);

            // Insert some words into the dictionary
            dictionary1.insert("apple");
            dictionary1.insert("banana");
            dictionary1.insert("cherry");
            dictionary1.insert("date");

            // Search for a word in the dictionary
            System.out.println("Is 'banana' in the dictionary? " + dictionary1.search("banana"));

            // Delete a word from the dictionary
            dictionary1.delete("banana");

            // Search for the deleted word again
            System.out.println("Is 'banana' in the dictionary after deletion? " + dictionary1.search("banana"));

            // Create another EnglishDictionary with HashTable2
            EnglishDictionary<String> dictionary2 = new EnglishDictionary<>(1);

            // Create dictionary from file
//            dictionary2.createDictionaryFromFile("dictionary_words.txt");
//
//            // Delete words from dictionary using file
//            dictionary2.deleteWordsFromDictionary("words_to_delete.txt");

            // Test searching for words in the dictionary
            System.out.println("Is 'apple' in the dictionary? " + dictionary2.search("apple"));
            System.out.println("Is 'banana' in the dictionary? " + dictionary2.search("banana"));
        }


}
