package org.example.java.driver.mongodb.config;

import java.util.logging.Logger;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

    private static final Logger LOG = Logger.getLogger(MongoDB.class.getName());

    /** DB host. */
    public static final String DB_HOST = "localhost";
    /** DB port. */
    public static final int DB_PORT = 27017;
    /** DB name. */
    public static final String DB_NAME = "test";
    /** Shared instance. */
    private static final MongoDB INSTANCE = new MongoDB();
    /** MongoClient instance. */
    private final MongoClient mongoClient;
    /** MongoDatabase instance. */
    private final MongoDatabase database;

    private MongoDB() {
        final MongoClientOptions mongoOptions = MongoClientOptions.builder()
                .socketTimeout(60000) // Wait 1m for a query to finish, https://jira.mongodb.org/browse/JAVA-1076
                .connectTimeout(15000) // Try the initial connection for 15s, http://blog.mongolab.com/2013/10/do-you-want-a-timeout/
                .maxConnectionIdleTime(600000) // Keep idle connections for 10m, so we discard failed connections quickly
                .readPreference(ReadPreference.primaryPreferred()) // Read from the primary, if not available use a secondary
                .build();
        this.mongoClient = new MongoClient(new ServerAddress(DB_HOST, DB_PORT), mongoOptions);
        this.database = mongoClient.getDatabase(DB_NAME);
        LOG.info("Connection to database '" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "' initialized");
    }

    public static MongoDB instance() {
        return INSTANCE;
    }

    /**
     * @return the database
     */
    public MongoDatabase getDatabase() {
        return database;
    }

    /**
     * @return the mongoClient
     */
    public MongoClient getMongoClient() {
        return mongoClient;
    }

}