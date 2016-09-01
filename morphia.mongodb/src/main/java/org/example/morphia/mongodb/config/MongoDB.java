package org.example.morphia.mongodb.config;

import java.util.logging.Logger;

import org.example.morphia.mongodb.entity.BaseEntity;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

/**
 * Connection provider for MongoDB instance.
 *
 * @author adam.bialas
 *
 */
public class MongoDB {

    private static final Logger LOG = Logger.getLogger(MongoDB.class.getName());

    /** DB host. */
    public static final String DB_HOST = "localhost";
    /** DB port. */
    public static final int DB_PORT = 27017;
    /** DB name. */
    public static final String DB_NAME = "test";
    /** Datastore instance. */
    private final Datastore datastore;
    /** Shared instance. */
    private static final MongoDB INSTANCE = new MongoDB();

    /**
     * Constructor - singleton.
     */
    private MongoDB() {
        final MongoClientOptions mongoOptions = MongoClientOptions.builder()
                .socketTimeout(60000) // Wait 1m for a query to finish, https://jira.mongodb.org/browse/JAVA-1076
                .connectTimeout(15000) // Try the initial connection for 15s, http://blog.mongolab.com/2013/10/do-you-want-a-timeout/
                .maxConnectionIdleTime(600000) // Keep idle connections for 10m, so we discard failed connections quickly
                .readPreference(ReadPreference.primaryPreferred()) // Read from the primary, if not available use a secondary
                .build();
        final MongoClient mongoClient = new MongoClient(new ServerAddress(DB_HOST, DB_PORT), mongoOptions);
        mongoClient.setWriteConcern(WriteConcern.SAFE);
        datastore = new Morphia().mapPackage(BaseEntity.class.getPackage().getName()).createDatastore(mongoClient,
                DB_NAME);
        datastore.ensureIndexes();
        datastore.ensureCaps();
        LOG.info("Connection to database '" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "' initialized");
    }

    public static MongoDB instance() {
        return INSTANCE;
    }

    public Datastore getDatabase() {
        return datastore;
    }

}