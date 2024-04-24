package org.example.HashTable;

import java.io.IOException;

public interface PrefectHashTable<T> {
    public T insert(T key);

    public T delete(T key);

    public boolean search(T key);

    public void batchInsert(String filePath) throws IOException;

    public void batchDelete(String filePath) throws IOException;

}
