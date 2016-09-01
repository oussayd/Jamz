package org.example.java.driver.mongodb.entity;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Customer entity class.
 *
 * @author adam.bialas
 *
 */
public class Customer implements IBaseEntity {

    private ObjectId id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Address address;

    public Customer() {
    }

    public Customer(final String firstName, final String lastName, final Address address, final Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.age = age;
    }

    /**
     * @return the id
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%s, firstName='%s', lastName='%s', age='%d', address='%s']", id, firstName,
                lastName, age, address);
    }

    public Document toDocument() {
        final Document document = new Document();
        if (id != null) {
            document.append("_id", id.toString());
        }
        if (StringUtils.isNotEmpty(firstName)) {
            document.append("firstName", firstName);
        }
        if (StringUtils.isNotEmpty(lastName)) {
            document.append("lastName", lastName);
        }
        if (address != null) {
            document.append("address", address.toDocument());
        }
        if (age != null) {
            document.append("age", age);
        }
        return document;
    }

}