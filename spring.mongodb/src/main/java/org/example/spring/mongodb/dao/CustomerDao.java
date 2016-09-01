package org.example.spring.mongodb.dao;

import java.util.List;

import org.example.spring.mongodb.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * DAO class for {@link Customer} entity.
 *
 * @author adam.bialas
 *
 */
public interface CustomerDao extends MongoRepository<Customer, String> {

    List<Customer> findByFirstName(String firstName);

    List<Customer> findTop2ByFirstName(String firstName);

    List<Customer> findTop2ByLastName(String lastName);

    List<Customer> findByAddressCity(String city);

    List<Customer> findByLastName(String lastName);

    @Query("{'lastName': ?0}")
    List<Customer> findByCustomersLastName(String lastName);

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