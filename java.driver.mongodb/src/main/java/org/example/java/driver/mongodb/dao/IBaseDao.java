package org.example.java.driver.mongodb.dao;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.example.java.driver.mongodb.entity.IBaseEntity;

import com.mongodb.client.MongoCursor;

/**
 * DAO interface for all entities which extends {@link IBaseEntity}.
 *
 * @author adam.bialas
 *
 */
public interface IBaseDao<T extends IBaseEntity> {

    T save(T entity);

    long count();

    T get(final ObjectId id);

    List<T> findAll();

    void deleteAll();

    void close();

    String getCollectionName();

    T parseDocument(final Document document);

    List<T> cursorToObjectList(final MongoCursor<Document> cursor);
}