package org.example.java.driver.mongodb.dao;

import java.util.List;

import org.example.java.driver.mongodb.entity.Customer;

/**
 * DAO class for {@link Customer} entity.
 *
 * @author adam.bialas
 *
 */
public interface ICustomerDao extends IBaseDao<Customer> {

    List<Customer> findByFirstName(String firstName);

    List<Customer> findTop2ByFirstName(String firstName);

    List<Customer> findTop2ByLastName(String lastName);

    List<Customer> findByAddressCity(String city);

    List<Customer> findByLastName(String lastName);

    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);

    List<Customer> findByAddressBuildingNumberGreaterThan(Integer builingNumber);

    List<Customer> findByAgeBetween(int ageFrom, int ageTo);

    Customer findOneByLastName(String lastName);

    Customer findOneByFirstName(String firstName);

    Customer findOneByFirstNameAndLastName(String firstName, String lastName);

    long countByFirstName(String firstName);

    long countByLastName(String lastName);

    long countByLastNameIgnoreCase(String lastName);
}