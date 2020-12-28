package com.ers.dao;

import java.util.Collection;

public interface Repository<T, ID> {
    Collection<T> getAll();
    T getById(ID id);
    ID save(T obj);
    void delete(T obj);
}
