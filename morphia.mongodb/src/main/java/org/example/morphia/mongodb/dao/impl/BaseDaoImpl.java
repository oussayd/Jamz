package org.example.morphia.mongodb.dao.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.example.morphia.mongodb.config.MongoDB;
import org.example.morphia.mongodb.dao.BaseDao;
import org.example.morphia.mongodb.entity.BaseEntity;
import org.mongodb.morphia.Datastore;

/**
 * Implementation of {@link BaseDao} interface.
 *
 * @author adam.bialas
 *
 */
public class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {

    protected final Datastore datastore;
    protected final Class<T> clazz;

    public BaseDaoImpl(final Class<T> clazz) {
        this.datastore = MongoDB.instance().getDatabase();
        this.clazz = clazz;
    }

    @Override
    public T save(T entity) {
        datastore.save(entity);
        return entity;
    }

    @Override
    public long count() {
        return datastore.find(clazz).countAll();
    }

    @Override
    public T get(final ObjectId id) {
        return datastore.find(clazz).field("id").equal(id).get();
    }

    @Override
    public void deleteAll() {
        datastore.delete(datastore.createQuery(clazz));
    }

    @Override
    public List<T> findAll() {
        return datastore.find(clazz).asList();
    }

    @Override
    public void close() {
        datastore.getMongo().close();
    }
}