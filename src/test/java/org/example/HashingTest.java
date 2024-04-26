package org.example;

import org.example.HashTable.HashTable1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.* ;

class HashingTest {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    public static int generateRandomInteger() {
        Random random = new Random();
        return random.nextInt(10) + 1;
    }

    @Test
    void InsertionTest1(){
        System.out.println("Test Case 1 : Insertion");
        System.out.println();

        HashTable1<String> hashtable1     = new HashTable1<>(10) ;
        PerfectHashing<String> hashtable2 = new PerfectHashing<>()    ;

        long startTime = System.nanoTime();

        for (int i = 0; i < 10; i++) {

            String randomString = generateRandomString(generateRandomInteger());
            hashtable1.insert(randomString);
        }

        long endTime = System.nanoTime();

        long duration1 = endTime - startTime;

        startTime = System.nanoTime();

        for (int i = 0; i < 10; i++) {

            String randomString = generateRandomString(generateRandomInteger());
            hashtable2.insert(randomString);
        }

        endTime = System.nanoTime();

        long duration2 = endTime - startTime;

        System.out.println("Time taken for o(N^2): " + duration1 + " nanoseconds");
        System.out.println("Number of rehashing for o(N^2): " + hashtable1.getNumberOfReHashing());
        System.out.println("Time taken for o(N): " + duration2 + " nanoseconds");
        System.out.println("Number of rehashing for o(N): " + hashtable2.getNumberOfReHashing());
        System.out.println("--------------------------------------------------------------------");
        System.out.println();

        assertEquals(hashtable1.getNumberOfInsertions() - hashtable1.getNumberOfDeletions(), hashtable1.getCapacity());
        assertEquals(hashtable2.getNumberOfInsertions() - hashtable2.getNumberOfDeletions(), hashtable2.getCapacity());

    }

    @Test
    void InsertionTest2() {
        System.out.println("Test Case 2 : Insertion");
        System.out.println();

        HashTable1<String> hashtable1     = new HashTable1<>(20) ;
        PerfectHashing<String> hashtable2 = new PerfectHashing<>()     ;

        long startTime = System.nanoTime();

        for (int i = 0; i < 20; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable1.insert(randomString);
        }

        long endTime = System.nanoTime();

        long duration1 = endTime - startTime;

        startTime = System.nanoTime();

        for (int i = 0; i < 20; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable2.insert(randomString);
        }

        endTime = System.nanoTime();

        long duration2 = endTime - startTime;

        System.out.println("Time taken for o(N^2): " + duration1 + " nanoseconds");
        System.out.println("Number of rehashing for o(N^2): " + hashtable1.getNumberOfReHashing());
        System.out.println("Time taken for o(N): " + duration2 + " nanoseconds");
        System.out.println("Number of rehashing for o(N): " + hashtable2.getNumberOfReHashing());
        System.out.println("--------------------------------------------------------------------");
        System.out.println();

        assertEquals(hashtable1.getNumberOfInsertions() - hashtable1.getNumberOfDeletions(), hashtable1.getCapacity());
        assertEquals(hashtable2.getNumberOfInsertions() - hashtable2.getNumberOfDeletions(), hashtable2.getCapacity());

    }

    @Test
    void InsertionTest3() {
        System.out.println("Test Case 3 : Insertion") ;
        System.out.println();

        HashTable1<String> hashtable1 = new HashTable1<>(40) ;
        PerfectHashing<String> hashtable2 = new PerfectHashing<>() ;

        long startTime = System.nanoTime();

        for (int i = 0; i < 40; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable1.insert(randomString);
        }

        long endTime = System.nanoTime();

        long duration1 = endTime - startTime;

        startTime = System.nanoTime();

        for (int i = 0; i < 40; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable2.insert(randomString);
        }

        endTime = System.nanoTime();

        long duration2 = endTime - startTime;

        System.out.println("Time taken for o(N^2): " + duration1 + " nanoseconds");
        System.out.println("Number of rehashing for o(N^2): " + hashtable1.getNumberOfReHashing());
        System.out.println("Time taken for o(N): " + duration2 + " nanoseconds");
        System.out.println("Number of rehashing for o(N): " + hashtable2.getNumberOfReHashing());
        System.out.println("--------------------------------------------------------------------");
        System.out.println();

        assertEquals(hashtable1.getNumberOfInsertions() - hashtable1.getNumberOfDeletions(), hashtable1.getCapacity());
        assertEquals(hashtable2.getNumberOfInsertions() - hashtable2.getNumberOfDeletions(), hashtable2.getCapacity());

    }

    @Test
    void InsertionTest4() {
        System.out.println("Test Case 4 : Insertion") ;
        System.out.println();

        HashTable1<String> hashtable1     = new HashTable1<>(60) ;
        PerfectHashing<String> hashtable2 = new PerfectHashing<>()     ;

        long startTime = System.nanoTime();

        for (int i = 0; i < 60; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable1.insert(randomString);
        }

        long endTime = System.nanoTime();

        long duration1 = endTime - startTime;

        startTime = System.nanoTime();

        for (int i = 0; i < 60; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable2.insert(randomString);
        }

        endTime = System.nanoTime();

        long duration2 = endTime - startTime;

        System.out.println("Time taken for o(N^2): " + duration1 + " nanoseconds");
        System.out.println("Number of rehashing for o(N^2): " + hashtable1.getNumberOfReHashing());
        System.out.println("Time taken for o(N): " + duration2 + " nanoseconds");
        System.out.println("Number of rehashing for o(N): " + hashtable2.getNumberOfReHashing());
        System.out.println("--------------------------------------------------------------------");
        System.out.println();

        assertEquals(hashtable1.getNumberOfInsertions() - hashtable1.getNumberOfDeletions(), hashtable1.getCapacity());

    }

    @Test
    void InsertionTest5() {
        System.out.println("Test Case 5 : Insertion") ;
        System.out.println();

        HashTable1<String> hashtable1     = new HashTable1<>(80) ;
        PerfectHashing<String> hashtable2 = new PerfectHashing<>()     ;

        long startTime = System.nanoTime();

        for (int i = 0; i < 80; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable1.insert(randomString);
        }

        long endTime = System.nanoTime();

        long duration1 = endTime - startTime;

        startTime = System.nanoTime();

        for (int i = 0; i < 80; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable2.insert(randomString);
        }

        endTime = System.nanoTime();

        long duration2 = endTime - startTime;

        System.out.println("Time taken for o(N^2): " + duration1 + " nanoseconds");
        System.out.println("Number of rehashing for o(N^2): " + hashtable1.getNumberOfReHashing());
        System.out.println("Time taken for o(N): " + duration2 + " nanoseconds");
        System.out.println("Number of rehashing for o(N): " + hashtable2.getNumberOfReHashing());
        System.out.println("--------------------------------------------------------------------");
        System.out.println();

        assertEquals(hashtable1.getNumberOfInsertions() - hashtable1.getNumberOfDeletions(), hashtable1.getCapacity());
        assertEquals(hashtable2.getNumberOfInsertions() - hashtable2.getNumberOfDeletions(), hashtable2.getCapacity());
    }

    @Test
    void InsertionTest6() {
        System.out.println("Test Case 6 : Insertion") ;
        System.out.println();

        HashTable1<String> hashtable1     = new HashTable1<>(300) ;
        PerfectHashing<String> hashtable2 = new PerfectHashing<>()     ;

        long startTime = System.nanoTime();

        for (int i = 0; i < 100; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable1.insert(randomString);
        }

        long endTime = System.nanoTime();

        long duration1 = endTime - startTime;

        startTime = System.nanoTime();

        for (int i = 0; i < 100; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable2.insert(randomString);
        }

        endTime = System.nanoTime();

        long duration2 = endTime - startTime;

        System.out.println("Time taken for o(N^2): " + duration1 + " nanoseconds");
        System.out.println("Number of rehashing for o(N^2): " + hashtable1.getNumberOfReHashing());
        System.out.println("Time taken for o(N): " + duration2 + " nanoseconds");
        System.out.println("Number of rehashing for o(N): " + hashtable2.getNumberOfReHashing());
        System.out.println("--------------------------------------------------------------------");
        System.out.println();

        assertEquals(hashtable1.getNumberOfInsertions() - hashtable1.getNumberOfDeletions(), hashtable1.getCapacity());
        assertEquals(hashtable2.getNumberOfInsertions() - hashtable2.getNumberOfDeletions(), hashtable2.getCapacity());

    }

    @Test
    void SearchTest1() {
        System.out.println("Test Case 1 : Search");
        System.out.println();

        HashTable1<String> hashtable1     = new HashTable1<>(10) ;
        PerfectHashing<String> hashtable2 = new PerfectHashing<>()    ;


        for (int i = 0; i < 9; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable1.insert(randomString);

            if(i == 5)
                hashtable1.insert("Hello") ;

        }

        long startTime = System.nanoTime() ;
        assertTrue(hashtable1.search("Hello")) ;
        long endTime   = System.nanoTime() ;

        long duration1 = endTime - startTime;


        for (int i = 0; i < 9; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable2.insert(randomString);

            if(i == 5)
                hashtable2.insert("Hello") ;

        }

        startTime = System.nanoTime() ;
        assertTrue(hashtable2.search("Hello")) ;
        endTime   = System.nanoTime() ;

        long duration2 = endTime - startTime;

        System.out.println("Time taken for o(N^2): " + duration1 + " nanoseconds");
        System.out.println("Time taken for o(N): " + duration2 + " nanoseconds");
        System.out.println("--------------------------------------------------------------------");
        System.out.println();

    }

    @Test
    void SearchTest2() {
        System.out.println("Test Case 2 : Search");
        System.out.println();

        HashTable1<String> hashtable1     = new HashTable1<>(20) ;
        PerfectHashing<String> hashtable2 = new PerfectHashing<>()     ;


        for (int i = 0; i < 19; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable1.insert(randomString);

            if(i == 10)
                hashtable1.insert("Hello") ;

        }

        long startTime = System.nanoTime() ;
        assertTrue(hashtable1.search("Hello")) ;
        long endTime   = System.nanoTime() ;

        long duration1 = endTime - startTime;

        for (int i = 0; i < 19; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable2.insert(randomString);

            if(i == 10)
                hashtable2.insert("Hello") ;

        }

        startTime = System.nanoTime() ;
        assertTrue(hashtable2.search("Hello")) ;
        endTime   = System.nanoTime() ;

        long duration2 = endTime - startTime;

        System.out.println("Time taken for o(N^2): " + duration1 + " nanoseconds");
        System.out.println("Time taken for o(N): " + duration2 + " nanoseconds");
        System.out.println("--------------------------------------------------------------------");
        System.out.println();

    }

    @Test
    void SearchTest3() {
        System.out.println("Test Case 3 : Search");
        System.out.println();

        HashTable1<String> hashtable1     = new HashTable1<>(150) ;
        PerfectHashing<String> hashtable2 = new PerfectHashing<>()     ;

        for (int i = 0; i < 39; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable1.insert(randomString);

            if(i == 20)
                hashtable1.insert("Hello") ;

        }

        long startTime = System.nanoTime() ;
        assertTrue(hashtable1.search("Hello")) ;
        long endTime   = System.nanoTime() ;

        long duration1 = endTime - startTime;


        for (int i = 0; i < 39; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable2.insert(randomString);

            if(i == 20)
                hashtable2.insert("Hello") ;

        }

        startTime = System.nanoTime() ;
        assertTrue(hashtable2.search("Hello")) ;
        endTime   = System.nanoTime() ;

        long duration2 = endTime - startTime;

        System.out.println("Time taken for o(N^2): " + duration1 + " nanoseconds");
        System.out.println("Time taken for o(N): " + duration2 + " nanoseconds");
        System.out.println("--------------------------------------------------------------------");
        System.out.println();

    }

    @Test
    void SearchTest4() {
        System.out.println("Test Case 4 : Search");
        System.out.println();

        HashTable1<String> hashtable1     = new HashTable1<>(60) ;
        PerfectHashing<String> hashtable2 = new PerfectHashing<>()     ;

        for (int i = 0; i < 59; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable1.insert(randomString);

            if(i == 30)
                hashtable1.insert("Hello") ;

        }

        long startTime = System.nanoTime() ;
        assertTrue(hashtable1.search("Hello")) ;
        long endTime   = System.nanoTime() ;

        long duration1 = endTime - startTime;

        for (int i = 0; i < 59; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable2.insert(randomString);

            if(i == 30)
                hashtable2.insert("Hello") ;

        }

        startTime = System.nanoTime() ;
        assertTrue(hashtable2.search("Hello")) ;
        endTime   = System.nanoTime() ;

        long duration2 = endTime - startTime;


        System.out.println("Time taken for o(N^2): " + duration1 + " nanoseconds");
        System.out.println("Time taken for o(N): " + duration2 + " nanoseconds");
        System.out.println("--------------------------------------------------------------------");
        System.out.println();

    }

    @Test
    void SearchTest5() {
        System.out.println("Test Case 5 : Search");
        System.out.println();

        HashTable1<String> hashtable1     = new HashTable1<>(80) ;
        PerfectHashing<String> hashtable2 = new PerfectHashing<>()     ;

        for (int i = 0; i < 79; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable1.insert(randomString);

            if(i == 40)
                hashtable1.insert("Hello") ;

        }

        long startTime = System.nanoTime() ;
        assertTrue(hashtable1.search("Hello")) ;
        long endTime   = System.nanoTime() ;

        long duration1 = endTime - startTime;

        for (int i = 0; i < 79; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable2.insert(randomString);

            if(i == 40)
                hashtable2.insert("Hello") ;

        }

        startTime = System.nanoTime() ;
        assertTrue(hashtable2.search("Hello")) ;
        endTime   = System.nanoTime() ;

        long duration2 = endTime - startTime;


        System.out.println("Time taken for o(N^2): " + duration1 + " nanoseconds");
        System.out.println("Time taken for o(N): " + duration2 + " nanoseconds");
        System.out.println("--------------------------------------------------------------------");
        System.out.println();

    }

    @Test
    void SearchTest6() {
        System.out.println("Test Case 6 : Search");
        System.out.println();

        HashTable1<String> hashtable1     = new HashTable1<>(100) ;
        PerfectHashing<String> hashtable2 = new PerfectHashing<>()     ;

        for (int i = 0; i < 99; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable1.insert(randomString);

            if(i == 50)
                hashtable1.insert("Hello") ;

        }

        long startTime = System.nanoTime() ;
        assertTrue(hashtable1.search("Hello")) ;
        long endTime   = System.nanoTime() ;

        long duration1 = endTime - startTime;

        for (int i = 0; i < 99; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable2.insert(randomString);

            if(i == 50)
                hashtable2.insert("Hello") ;

        }

        startTime = System.nanoTime() ;
        assertTrue(hashtable2.search("Hello")) ;
        endTime   = System.nanoTime() ;

        long duration2 = endTime - startTime;

        System.out.println("Time taken for o(N^2): " + duration1 + " nanoseconds");
        System.out.println("Time taken for o(N): " + duration2 + " nanoseconds");
        System.out.println("--------------------------------------------------------------------");
        System.out.println();

    }

    @Test
    void DeleteTest1() {
        System.out.println("Test Case 1 : Delete");
        System.out.println();

        HashTable1<String> hashtable1     = new HashTable1<>(10) ;
        PerfectHashing<String> hashtable2 = new PerfectHashing<>()    ;

        for (int i = 0; i < 9; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable1.insert(randomString);

            if(i == 5)
                hashtable1.insert("Hello") ;

        }

        long startTime = System.nanoTime() ;
        assertTrue(hashtable1.delete("Hello"));
        long endTime   = System.nanoTime() ;

        long duration1 = endTime - startTime;


        for (int i = 0; i < 9; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable2.insert(randomString);

            if(i == 5)
                hashtable2.insert("Hello") ;

        }

        startTime = System.nanoTime() ;
        assertTrue(hashtable2.delete("Hello"));
        endTime   = System.nanoTime() ;

        long duration2 = endTime - startTime;

        System.out.println("Time taken for o(N^2): " + duration1 + " nanoseconds");
        System.out.println("Time taken for o(N): " + duration2 + " nanoseconds");
        System.out.println("--------------------------------------------------------------------");
        System.out.println();

    }

    @Test
    void DeleteTest2() {
        System.out.println("Test Case 2 : Delete");
        System.out.println();

        HashTable1<String> hashtable1     = new HashTable1<>(20) ;
        PerfectHashing<String> hashtable2 = new PerfectHashing<>()     ;

        for (int i = 0; i < 19; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable1.insert(randomString);

            if(i == 10)
                hashtable1.insert("Hello") ;

        }

        long startTime = System.nanoTime() ;
        assertTrue(hashtable1.delete("Hello")) ;
        long endTime   = System.nanoTime() ;

        long duration1 = endTime - startTime;

        for (int i = 0; i < 19; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable2.insert(randomString);

            if(i == 10)
                hashtable2.insert("Hello") ;

        }

        startTime = System.nanoTime() ;
        assertTrue(hashtable2.delete("Hello")) ;
        endTime   = System.nanoTime() ;

        long duration2 = endTime - startTime;


        System.out.println("Time taken for o(N^2): " + duration1 + " nanoseconds");
        System.out.println("Time taken for o(N): " + duration2 + " nanoseconds");
        System.out.println("--------------------------------------------------------------------");
        System.out.println();

    }

    @Test
    void DeleteTest3() {
        System.out.println("Test Case 3 : Delete");
        System.out.println();

        HashTable1<String> hashtable1     = new HashTable1<>(40) ;
        PerfectHashing<String> hashtable2 = new PerfectHashing<>()     ;

        for (int i = 0; i < 39; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable1.insert(randomString);

            if(i == 20)
                hashtable1.insert("Hello") ;

        }

        long startTime = System.nanoTime() ;
        assertTrue(hashtable1.delete("Hello")) ;
        long endTime   = System.nanoTime() ;

        long duration1 = endTime - startTime;

        for (int i = 0; i < 39; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable2.insert(randomString);

            if(i == 20)
                hashtable2.insert("Hello") ;

        }

        startTime = System.nanoTime() ;
        assertTrue(hashtable2.delete("Hello")) ;
        endTime   = System.nanoTime() ;

        long duration2 = endTime - startTime;

        System.out.println("Time taken for o(N^2): " + duration1 + " nanoseconds");
        System.out.println("Time taken for o(N): " + duration2 + " nanoseconds");
        System.out.println("--------------------------------------------------------------------");
        System.out.println();

    }

    @Test
    void DeleteTest4() {
        System.out.println("Test Case 4 : Delete");
        System.out.println();

        HashTable1<String> hashtable1     = new HashTable1<>(60) ;
        PerfectHashing<String> hashtable2 = new PerfectHashing<>()     ;

        for (int i = 0; i < 59; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable1.insert(randomString);

            if(i == 30)
                hashtable1.insert("Hello") ;

        }

        long startTime = System.nanoTime() ;
        assertTrue(hashtable1.delete("Hello")) ;
        long endTime   = System.nanoTime() ;

        long duration1 = endTime - startTime;

        for (int i = 0; i < 59; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable2.insert(randomString);

            if(i == 30)
                hashtable2.insert("Hello") ;

        }

        startTime = System.nanoTime() ;
        assertTrue(hashtable2.delete("Hello")) ;
        endTime   = System.nanoTime() ;

        long duration2 = endTime - startTime;

        System.out.println("Time taken for o(N^2): " + duration1 + " nanoseconds");
        System.out.println("Time taken for o(N): " + duration2 + " nanoseconds");
        System.out.println("--------------------------------------------------------------------");
        System.out.println();

    }

    @Test
    void DeleteTest5() {
        System.out.println("Test Case 5 : Delete");
        System.out.println();

        HashTable1<String> hashtable1     = new HashTable1<>(80) ;
        PerfectHashing<String> hashtable2 = new PerfectHashing<>()     ;

        for (int i = 0; i < 79; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable1.insert(randomString);

            if(i == 40)
                hashtable1.insert("Hello") ;

        }

        long startTime = System.nanoTime() ;
        assertTrue(hashtable1.delete("Hello")) ;
        long endTime   = System.nanoTime() ;

        long duration1 = endTime - startTime;

        for (int i = 0; i < 79; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable2.insert(randomString);

            if(i == 40)
                hashtable2.insert("Hello") ;

        }

        startTime = System.nanoTime() ;
        assertTrue(hashtable2.delete("Hello")) ;
        endTime   = System.nanoTime() ;

        long duration2 = endTime - startTime;


        System.out.println("Time taken for o(N^2): " + duration1 + " nanoseconds");
        System.out.println("Time taken for o(N): " + duration2 + " nanoseconds");
        System.out.println("--------------------------------------------------------------------");
        System.out.println();

    }

    @Test
    void DeleteTest6() {
        System.out.println("Test Case 6 : Delete");
        System.out.println();

        HashTable1<String> hashtable1     = new HashTable1<>(100) ;
        PerfectHashing<String> hashtable2 = new PerfectHashing<>()     ;

        for (int i = 0; i < 99; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable1.insert(randomString);

            if(i == 50)
                hashtable1.insert("Hello") ;

        }

        long startTime = System.nanoTime() ;
        assertTrue(hashtable1.delete("Hello")) ;
        long endTime   = System.nanoTime() ;

        long duration1 = endTime - startTime;

        for (int i = 0; i < 99; i++) {

            String randomString = generateRandomString(generateRandomInteger());

            hashtable2.insert(randomString);

            if(i == 50)
                hashtable2.insert("Hello") ;

        }

        startTime = System.nanoTime() ;
        assertTrue(hashtable2.delete("Hello")) ;
        endTime   = System.nanoTime() ;

        long duration2 = endTime - startTime;

        System.out.println("Time taken for o(N^2): " + duration1 + " nanoseconds");
        System.out.println("Time taken for o(N): " + duration2+ " nanoseconds");
        System.out.println("--------------------------------------------------------------------");
        System.out.println();

    }

}