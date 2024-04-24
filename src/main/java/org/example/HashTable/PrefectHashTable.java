package org.example.HashTable;

import java.io.IOException;

public interface PrefectHashTable<T> {
    public Boolean insert(T key);

    public T delete(T key);

    public Boolean search(T key);

    public void batchInsert(String filePath);

    public void batchDelete(String filePath);

}
