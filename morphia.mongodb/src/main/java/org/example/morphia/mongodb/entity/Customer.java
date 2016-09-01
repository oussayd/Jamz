package org.example.morphia.mongodb.entity;

import org.example.morphia.mongodb.converter.LocalDateTimeConverter;
import org.mongodb.morphia.annotations.Converters;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

/**
 * Customer entity class.
 *
 * @author adam.bialas
 *
 */
@Entity(value = "customers")
@Converters({ LocalDateTimeConverter.class })
public class Customer extends BaseEntity {

    private String firstName;
    private String lastName;
    private Integer age;
    @Embedded
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
}