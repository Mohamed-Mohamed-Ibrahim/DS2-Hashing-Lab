package org.example.HashTable;

import org.example.Main;

import java.util.Random;

public class UniversalMatrix {

    int[][] matrix;
    int b;
    int u;
    int M;

    public UniversalMatrix(int size) {
        u = 32;
        this.b = (int) (Math.log(size) / Math.log(2)) + 1;
        matrix = new int[b][u];
        M = size;
        generate();
    }

    private void generate() {
        Random rand = new Random();

        for (int i = 0; i < b; i++) {
            for (int j = 0; j < u; j++) {
                matrix[i][j] = rand.nextInt(2);
            }
        }
    }

    public int computeIndex(Object x) {

        int hashCode = x.hashCode();

        String binaryString = Integer.toBinaryString(hashCode);

        while (binaryString.length() < u) {
            binaryString = "0" + binaryString;
        }

        int[] xBits = new int[u];
        for (int i = 0; i < u; i++) {
            xBits[i] = binaryString.charAt(i) - '0';
        }

        return computeIndexFromBits(xBits);
    }

    private int computeIndexFromBits(int[] xBits) {
        int[] hx = new int[b];

        for (int i = 0; i < b; i++) {
            int sum = 0;
            for (int j = 0; j < u; j++) {
                sum += matrix[i][j] * xBits[j];
            }
            hx[i] = sum % 2;
        }

        int index = 0;
        for (int i = 0; i < b; i++) {
            index += hx[i] * Math.pow(2, i);
        }

        return index % M;
    }

    public static void main(String[] args) {
        int tableSize = 16; // Example table size
        UniversalMatrix universalMatrix = new UniversalMatrix(tableSize);

        // Generate a random key (for testing purposes)
        Random rand = new Random();
        int randomKey = rand.nextInt();

        // Compute the index for the random key using the UniversalMatrix object
        int index = universalMatrix.computeIndex(randomKey);

        // Print the result
        System.out.println("Table size: " + tableSize);
        System.out.println("Random key: " + randomKey);
        System.out.println("Index: " + index);
    }
}
