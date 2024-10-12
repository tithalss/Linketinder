package org.example.Interfaces

interface GenericDAO<T> {
    void create(T entity)
    T getById(int id)
    void update(T entity)
    void delete(int id)
}
