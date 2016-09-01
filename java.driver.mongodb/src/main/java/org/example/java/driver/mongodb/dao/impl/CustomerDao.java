package org.example.java.driver.mongodb.dao.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.example.java.driver.mongodb.dao.ICustomerDao;
import org.example.java.driver.mongodb.entity.Address;
import org.example.java.driver.mongodb.entity.Customer;

import com.mongodb.client.model.Filters;

public class CustomerDao extends BaseDao<Customer>implements ICustomerDao {

    public CustomerDao() {
        super(Customer.class);
    }

    @Override
    public List<Customer> findByFirstName(String firstName) {
        return cursorToObjectList(
                database.getCollection(getCollectionName()).find(Filters.eq("firstName", firstName)).iterator());
    }

    @Override
    public List<Customer> findTop2ByFirstName(String firstName) {
        return cursorToObjectList(database.getCollection(getCollectionName()).find(Filters.eq("firstName", firstName))
                .limit(2).iterator());
    }

    @Override
    public List<Customer> findTop2ByLastName(String lastName) {
        return cursorToObjectList(
                database.getCollection(getCollectionName()).find(Filters.eq("lastName", lastName)).limit(2).iterator());
    }

    @Override
    public List<Customer> findByAddressCity(String city) {
        return cursorToObjectList(
                database.getCollection(getCollectionName()).find(Filters.eq("address.city", city)).iterator());
    }

    @Override
    public List<Customer> findByLastName(String lastName) {
        return cursorToObjectList(
                database.getCollection(getCollectionName()).find(Filters.eq("lastName", lastName)).iterator());
    }

    @Override
    public List<Customer> findByFirstNameAndLastName(String firstName, String lastName) {
        return cursorToObjectList(database.getCollection(getCollectionName())
                .find(Filters.and(Filters.eq("firstName", firstName), Filters.eq("lastName", lastName))).iterator());
    }

    @Override
    public List<Customer> findByAddressBuildingNumberGreaterThan(Integer builingNumber) {
        return cursorToObjectList(database.getCollection(getCollectionName())
                .find(Filters.gt("address.builingNumber", builingNumber)).iterator());
    }

    @Override
    public List<Customer> findByAgeBetween(int ageFrom, int ageTo) {
        return cursorToObjectList(database.getCollection(getCollectionName())
                .find(Filters.and(Filters.gt("age", ageFrom), Filters.lt("age", ageTo))).iterator());
    }

    @Override
    public Customer findOneByLastName(String lastName) {
        return parseDocument(
                database.getCollection(getCollectionName()).find(Filters.eq("lastName", lastName)).first());
    }

    @Override
    public Customer findOneByFirstName(String firstName) {
        return parseDocument(
                database.getCollection(getCollectionName()).find(Filters.eq("firstName", firstName)).first());
    }

    @Override
    public Customer findOneByFirstNameAndLastName(String firstName, String lastName) {
        return parseDocument(
                database.getCollection(getCollectionName())
                        .find(Filters.and(Filters.eq("firstName", firstName), Filters.eq("lastName", lastName)))
                        .first());
    }

    @Override
    public long countByFirstName(String firstName) {
        return database.getCollection(getCollectionName()).count((Filters.eq("firstName", firstName)));
    }

    @Override
    public long countByLastName(String lastName) {
        return database.getCollection(getCollectionName()).count((Filters.eq("lastName", lastName)));
    }

    @Override
    public long countByLastNameIgnoreCase(String lastName) {
        return database.getCollection(getCollectionName())
                .count((Filters.eq("lastName", Pattern.compile(lastName, Pattern.CASE_INSENSITIVE))));
    }

    @Override
    public String getCollectionName() {
        return "customers";
    }

    @Override
    public Customer parseDocument(Document document) {
        if (document == null) {
            return null;
        }
        final Customer customer = new Customer();
        customer.setId(document.getObjectId("_id"));

        if (StringUtils.isNotEmpty(document.getString("firstName"))) {
            customer.setFirstName(document.getString("firstName"));
        }
        if (StringUtils.isNotEmpty(document.getString("lastName"))) {
            customer.setLastName(document.getString("lastName"));
        }
        if (document.getInteger("age") != null) {
            customer.setAge(document.getInteger("age"));
        }
        if (document.get("address") == null) {
            return customer;
        }
        Document addressDoc = document.get("address", Document.class);
        final Address address = new Address();
        if (StringUtils.isNotEmpty(addressDoc.getString("street"))) {
            address.setStreet(addressDoc.getString("street"));
        }
        if (StringUtils.isNotEmpty(addressDoc.getString("zipCode"))) {
            address.setZipCode(addressDoc.getString("zipCode"));
        }
        if (StringUtils.isNotEmpty(addressDoc.getString("city"))) {
            address.setCity(addressDoc.getString("city"));
        }
        if (addressDoc.getInteger("buildingNumber") != null) {
            address.setBuildingNumber(addressDoc.getInteger("buildingNumber"));
        }
        customer.setAddress(address);
        return customer;
    }

}

/*
 * $Log$
 */
