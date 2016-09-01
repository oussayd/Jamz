package org.example.java.driver.mongodb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.example.java.driver.mongodb.config.MongoDB;
import org.example.java.driver.mongodb.dao.IBaseDao;
import org.example.java.driver.mongodb.entity.IBaseEntity;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public abstract class BaseDao<T extends IBaseEntity> implements IBaseDao<T> {

    protected final MongoDatabase database;
    protected final Class<T> clazz;

    public BaseDao(final Class<T> clazz) {
        this.database = MongoDB.instance().getDatabase();
        this.clazz = clazz;
    }

    @Override
    public T save(T entity) {
        if (entity == null) {
            return null;
        }
        database.getCollection(getCollectionName()).insertOne(entity.toDocument());
        return entity;
    }

    @Override
    public long count() {
        return database.getCollection(getCollectionName()).count();
    }

    @Override
    public T get(ObjectId id) {

        return null;
    }

    @Override
    public List<T> findAll() {
        return cursorToObjectList(database.getCollection(getCollectionName()).find().iterator());
    }

    @Override
    public void deleteAll() {
        database.getCollection(getCollectionName()).deleteMany(new BasicDBObject());
    }

    @Override
    public void close() {
        MongoDB.instance().getMongoClient().close();
    }

    @Override
    public List<T> cursorToObjectList(final MongoCursor<Document> cursor) {
        final List<T> result = new ArrayList<T>();
        if (cursor == null) {
            return result;
        }
        try {
            while (cursor.hasNext()) {
                result.add(parseDocument(cursor.next()));
            }
        } finally {
            cursor.close();
        }
        return result;
    }

}

/*
 * $Log$
 */
