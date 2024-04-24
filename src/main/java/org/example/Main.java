package org.example;
import com.sun.jdi.IntegerType;
import org.example.HashTable.UniversalMatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class PerfectHashing<T> {
    private static final int N = 100; // N is the size of the dictionary
    private static final Random random = new Random();

    private List<T>[] firstLevelTable;
    private List<T>[] secondLevelTables;
    private int numberOfInsertions;
    private int numberOfDeletions;

    private int numberOfReHashing;

    private int capacity;

    private UniversalMatrix universalMatrix;


    public PerfectHashing() {
        this.firstLevelTable = new List[N];
        for (int i = 0; i < N; i++) {
            this.firstLevelTable[i] = new ArrayList<>();
        }

        this.secondLevelTables = new List[N];
        this.numberOfInsertions = 0;
        this.numberOfDeletions = 0;
    }

    private <T> int universalHash(T key, int tableSize) {
        // Random coefficients a and b for universal hashing
        // Random constant b in the range [0, tableSize-1] to cover all possible offset values
        if( tableSize != N )
            this.universalMatrix = new UniversalMatrix(tableSize);

        return this.universalMatrix.computeIndex(key);
    }

    public boolean insert(T key) {
        int hash1 = universalHash(key, N);
        if (!this.firstLevelTable[hash1].contains(key)) {
            if (this.secondLevelTables[hash1] == null) {
                this.firstLevelTable[hash1].add(key);
                numberOfInsertions++;
                return true;
            } else {
                int hash2 = universalHash(key, this.secondLevelTables[hash1].size());
                this.secondLevelTables[hash1].set(hash2, key);
                numberOfInsertions++;
                return true;
            }
        }
        return false;
    }

    public boolean delete(T key) {
        int hash1 = universalHash(key, N);
        if (this.firstLevelTable[hash1].contains(key)) {
            if (this.secondLevelTables[hash1] == null || !this.secondLevelTables[hash1].remove(key)) {
                this.firstLevelTable[hash1].remove(key);
            }
            numberOfDeletions++;
            return true;
        }
        return false;
    }

    public boolean search(T key) {
        int hash1 = universalHash(key, N);
        if (this.secondLevelTables[hash1] == null) {
            return this.firstLevelTable[hash1].contains(key);
        } else {
            int hash2 = universalHash(key, this.secondLevelTables[hash1].size());
            return this.secondLevelTables[hash1].get(hash2).equals(key);
        }
    }

    public void buildSecondLevelTables() {
        for (int i = 0; i < N; i++) {
            List<T> bin = this.firstLevelTable[i];
            if (bin.size() > 1) {
                int m = bin.size() * bin.size();
                this.secondLevelTables[i] = new ArrayList<>(m);
                for (int j = 0; j < m; j++) {
                    this.secondLevelTables[i].add(null);
                }

                for (T key : bin) {
                    int hash2 = universalHash(key, m);
                    while (this.secondLevelTables[i].get(hash2) != null) {
                        hash2 = (hash2 + 1) % m; // Linear probing
                    }
                    this.secondLevelTables[i].set(hash2, key);
                }
            }
        }
    }

    public int getNumberOfInsertions() {
        return numberOfInsertions;
    }

    public int getNumberOfDeletions() {
        return numberOfDeletions;
    }
}


public class Main {
    public static void main(String[] args) {
        String ok1 = new String("asdasd");
        String ok2 = new String("asdasd");
        UniversalMatrix universalMatrix = new UniversalMatrix(100);
        System.out.println(universalMatrix.computeIndex(ok1));
        System.out.println(universalMatrix.computeIndex(ok2));
    }
}
