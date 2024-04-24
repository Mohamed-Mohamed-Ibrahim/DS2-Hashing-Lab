package org.example.HashTable;

import java.util.ArrayList;
import java.util.Arrays;

public class Hash2N {
    Integer[] table;

    int maxSize = 0;
    int currentSize = 0;
    int countRehash = 0;
    UniversalMatrix universalMatrix;

    public Hash2N (int n) {
        this.table = new Integer[(int) Math.pow(n, 2)];
        this.universalMatrix = new UniversalMatrix(n * n);
        maxSize = n;
    }
    public boolean insert(int key){
        if (currentSize == maxSize || this.search(key)){
            // System.out.println(key + " exist");
            return false;
        }

        int hashValue = universalMatrix.computeIndex(key);
        if (table[hashValue] == null){
            table[hashValue] = key;
            currentSize++;
        } else if (table[hashValue].equals(key)) {
            return false;
        } else {
            reHash();
            insert(key);
        }
        return true;
    }

    public boolean insertSecondLevel(int key){
        if (currentSize != 0 && this.search(key)){
            return false;
        }
        int hashValue = universalMatrix.computeIndex(key);
        if (table[hashValue] == null){
            table[hashValue] = key;
            currentSize++;
        } else if (table[hashValue].equals(key)) {
            return false;
        } else {
            this.maxSize++;
            this.universalMatrix = new UniversalMatrix(maxSize * maxSize);
            this.reHash();
            this.insert(key);
        }
        return true;
    }

    /*
        public boolean insertSecondLevel(int key){ // for O(N) space method
            if (currentSize != 0 && search(key)){
                return false;
            }

            if (flag){
                this.maxSize++;
            }
            this.universalHashing = new UniversalHashing(maxSize * maxSize);
            this.reHash();
            this.insert(key);
            flag = true;
            return true;
        }
    */
    public void reHash(){
        // save elements to rehash it
        ArrayList<Integer> keys = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null){
                keys.add(table[i]);
            }
        }
        currentSize = 0;
        // update hash and table
//        this.universalMatrix.generate();
        this.table = new Integer[(int) Math.pow(maxSize, 2)];

        for (int i = 0; i < keys.size(); i++){
            int hashValue = this.universalMatrix.computeIndex(keys.get(i));
            if (table[hashValue] == null){
                table[hashValue] = keys.get(i);
                currentSize++;
            }else {
//                this.universalMatrix.generate();
                this.table = new Integer[(int) Math.pow(maxSize, 2)];
                i = -1;
                currentSize = 0;
            }
        }
        this.countRehash++;
        // System.out.print("collision");
        // System.out.println("number of rehashing: " + countRehash);
    }
    public boolean search(int key){
        int hashValue = this.universalMatrix.computeIndex(key);
        return table[hashValue] != null && table[hashValue] == key;
    }

    public boolean delete(int key){
        if (!search(key)){
            return false;
        }
        int hashValue = this.universalMatrix.computeIndex(key);
        table[hashValue] = null;
        this.currentSize--;
        return true;
    }
    public boolean deleteSecondLevel(int key){
        if (!search(key)){
            return false;
        }
        int hashValue = this.universalMatrix.computeIndex(key);
        table[hashValue] = null;
        //this.maxSize--;
        this.currentSize--;
        //this.universalMatrix = new universalMatrix(maxSize * maxSize);
        //this.reHash();
        return true;
    }

    public boolean batchInsert(int words[]){
        boolean success = true;
        for (int i = 0; i < words.length; i++){
            boolean res = this.insert(words[i]);
            if (!res){
                success = res;
                System.out.println("Insert " + words[i] + " failed");
            }

        }
        return success;
    }

    public boolean batchDelete(int words[]){
        boolean success = true;
        for (int i = 0; i < words.length; i++){
            boolean res = this.delete(words[i]);
            if (!res){
                success = res;
                System.out.println("Delete " + words[i] + " failed");
            }
        }
        return success;
    }

    public int getCountRehash(){
        return countRehash;
    }
    public int getSize(){return table.length;}

    public void printTable(){
        System.out.println("size = " + table.length);
        System.out.println(Arrays.deepToString(table));
    }
}
