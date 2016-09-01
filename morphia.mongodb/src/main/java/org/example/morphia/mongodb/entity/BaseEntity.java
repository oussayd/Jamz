package org.example.morphia.mongodb.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Version;

/**
 * Base entity class.
 *
 * @author adam.bialas
 *
 */
public abstract class BaseEntity {

    @Id
    protected ObjectId id;

    protected LocalDateTime creationDate;
    protected LocalDateTime lastChange;

    @Version
    protected long version;

    public BaseEntity() {
        super();
    }

    /**
     * @return the id
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * @return the creationDate
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @return the lastChange
     */
    public LocalDateTime getLastChange() {
        return lastChange;
    }

    @PrePersist
    public void prePersist() {
        this.creationDate = (creationDate == null ? LocalDateTime.now() : creationDate);
        this.lastChange = (lastChange == null ? creationDate : LocalDateTime.now());
    }

}