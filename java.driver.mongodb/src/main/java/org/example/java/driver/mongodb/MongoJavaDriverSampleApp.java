package org.example.java.driver.mongodb;

import org.example.java.driver.mongodb.dao.ICustomerDao;
import org.example.java.driver.mongodb.dao.impl.CustomerDao;
import org.example.java.driver.mongodb.entity.Address;
import org.example.java.driver.mongodb.entity.Customer;

/**
 * Hello world!
 *
 */
public class MongoJavaDriverSampleApp {

    private static final ICustomerDao customerDao = new CustomerDao();

    public static void main(String[] args) {
        // Clear Customer collection by removing all entities
        customerDao.deleteAll();

        // Save couple of Customers
        customerDao.save(new Customer("Alice", "Smith", null, 19));
        customerDao.save(new Customer("Alice", "Jones", null, 26));
        customerDao.save(new Customer("Bob", "Smith", new Address("Zlota", 22, "00-020", "Warszawa"), 30));
        customerDao.save(new Customer("Alice", "Brown", null, 80));
        customerDao.save(new Customer("Alice", "Smedley", null, 52));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : customerDao.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch top 2 customers by last name
        System.out.println("Customers found with findTop2ByLastName('Smith'):");
        System.out.println("-------------------------------");
        for (Customer customer : customerDao.findTop2ByLastName("Smith")) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch top 2 customers by first name
        System.out.println("Customers found with findTop2ByFirstName('Alice'):");
        System.out.println("-------------------------------");
        for (Customer customer : customerDao.findTop2ByFirstName("Alice")) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findOneByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(customerDao.findOneByFirstName("Alice"));
        System.out.println();

        // fetch an individual customer by first and last name
        System.out.println("Customer found with findOneByFirstNameAndLastName('Alice', 'Smith'):");
        System.out.println("--------------------------------");
        System.out.println(customerDao.findOneByFirstNameAndLastName("Alice", "Smith"));
        System.out.println();

        System.out.println("Customers found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        for (Customer customer : customerDao.findByFirstName("Alice")) {
            System.out.println(customer);
        }
        System.out.println();

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : customerDao.findByLastName("Smith")) {
            System.out.println(customer);
        }
        System.out.println();

        System.out.println("Customers countByLastName('smith'): " + customerDao.countByLastName("smith"));
        System.out.println(
                "Customers countByLastNameIgnoreCase('smith'): " + customerDao.countByLastNameIgnoreCase("smith"));

        // sample modification
        Customer customerEntity = customerDao.findOneByFirstNameAndLastName("Alice", "Smith");
        customerEntity.setLastName("Smiths");
        customerEntity = customerDao.save(customerEntity);

        System.out.println("Customers found with findByAddressCity('Warszawa'):");
        System.out.println("--------------------------------");
        for (Customer customer : customerDao.findByAddressCity("Warszawa")) {
            System.out.println(customer);
        }
        System.out.println();

        System.out.println("Customers found with findByAgeBetween(20, 60):");
        System.out.println("--------------------------------");
        for (Customer customer : customerDao.findByAgeBetween(20, 60)) {
            System.out.println(customer);
        }

        customerDao.close();
    }
}
