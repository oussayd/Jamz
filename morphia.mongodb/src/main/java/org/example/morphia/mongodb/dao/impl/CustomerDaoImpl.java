package org.example.morphia.mongodb.dao.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.example.morphia.mongodb.dao.CustomerDao;
import org.example.morphia.mongodb.entity.Customer;
import org.mongodb.morphia.query.Query;

/**
 * Implementation of {@link CustomerDao} interface which extends from {@link BaseDaoImpl}.
 *
 * @author adam.bialas
 *
 */
public class CustomerDaoImpl extends BaseDaoImpl<Customer>implements CustomerDao {

    public CustomerDaoImpl() {
        super(Customer.class);
    }

    @Override
    public List<Customer> findByFirstName(final String firstName) {
        return datastore.find(Customer.class).field("firstName").equal(firstName).asList();
    }

    @Override
    public List<Customer> findTop2ByFirstName(final String firstName) {
        return datastore.find(Customer.class).field("firstName").equal(firstName).limit(2).asList();
    }

    @Override
    public List<Customer> findTop2ByLastName(final String lastName) {
        return datastore.find(Customer.class).field("lastName").equal(lastName).asList();
    }

    @Override
    public List<Customer> findByAddressCity(final String city) {
        return datastore.find(Customer.class).field("address.city").equal(city).asList();
    }

    @Override
    public List<Customer> findByLastName(final String lastName) {
        return datastore.find(Customer.class).field("lastName").equal(lastName).asList();
    }

    @Override
    public List<Customer> findByFirstNameAndLastName(final String firstName, String lastName) {
        return datastore.find(Customer.class).field("lastName").equal(lastName).field("firstName").equal(firstName)
                .asList();
    }

    @Override
    public List<Customer> findByAddressBuildingNumberGreaterThan(final Integer buildingNumber) {
        return datastore.find(Customer.class).field("address.buildingNumber").greaterThan(buildingNumber).asList();
    }

    @Override
    public List<Customer> findByAgeBetween(int ageFrom, int ageTo) {
        Query<Customer> query = datastore.find(Customer.class).field("age").greaterThan(ageFrom);
        query.field("age").lessThan(ageTo);
        return query.asList();
    }

    @Override
    public Customer findOneByLastName(String lastName) {
        return datastore.find(Customer.class).field("lastName").equal(lastName).get();
    }

    @Override
    public Customer findOneByFirstName(String firstName) {
        return datastore.find(Customer.class).field("firstName").equal(firstName).get();
    }

    @Override
    public Customer findOneByFirstNameAndLastName(String firstName, String lastName) {
        return datastore.find(Customer.class).field("lastName").equal(lastName).field("firstName").equal(firstName)
                .get();
    }

    @Override
    public long countByFirstName(String firstName) {
        return datastore.find(Customer.class).field("firstName").equal(firstName).countAll();
    }

    @Override
    public long countByLastName(String lastName) {
        return datastore.find(Customer.class).field("lastName").equal(lastName).countAll();
    }

    @Override
    public long countByLastNameIgnoreCase(String lastName) {
        return datastore.find(Customer.class).field("lastName")
                .equal(Pattern.compile(lastName, Pattern.CASE_INSENSITIVE)).countAll();
    }

}