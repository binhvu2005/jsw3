package com.example.ss3.service;

import java.util.Optional;

/**
 * Generic CRUD service interface.
 *
 * @param <T>  Entity type
 * @param <ID> Primary‑key type
 */
public interface IService<T, ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    T update(T entity);

    void delete(ID id);
}
