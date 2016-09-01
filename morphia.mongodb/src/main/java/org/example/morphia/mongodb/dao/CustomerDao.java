package org.example.morphia.mongodb.dao;

import java.util.List;

import org.example.morphia.mongodb.entity.Customer;

/**
 * DAO interface for {@link Customer} entity.
 *
 * @author adam.bialas
 *
 */
public interface CustomerDao extends BaseDao<Customer> {

    List<Customer> findByFirstName(String firstName);

    List<Customer> findTop2ByFirstName(String firstName);

    List<Customer> findTop2ByLastName(String lastName);

    List<Customer> findByAddressCity(String city);

    List<Customer> findByLastName(String lastName);

    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);

    List<Customer> findByAddressBuildingNumberGreaterThan(Integer buildingNumber);

    List<Customer> findByAgeBetween(int ageFrom, int ageTo);

    Customer findOneByLastName(String lastName);

    Customer findOneByFirstName(String firstName);

    Customer findOneByFirstNameAndLastName(String firstName, String lastName);

    long countByFirstName(String firstName);

    long countByLastName(String lastName);

    long countByLastNameIgnoreCase(String lastName);

}