package org.example.morphia.mongodb.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.example.morphia.mongodb.entity.BaseEntity;

/**
 * DAO interface for all entities which extends {@link BaseEntity}.
 *
 * @author adam.bialas
 *
 */
public interface BaseDao<T extends BaseEntity> {

    T save(T entity);

    long count();

    T get(final ObjectId id);

    List<T> findAll();

    void deleteAll();

    void close();
}