package org.example.spring.mongodb;

import java.text.MessageFormat;

import org.example.spring.mongodb.dao.DealDao;
import org.example.spring.mongodb.service.FinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application class.
 *
 * @author adam.bialas
 *
 */

@SpringBootApplication
public class MongoSpringDataSampleApp implements CommandLineRunner {

    @Autowired
    private DealDao dealDao;

    @Autowired
    private FinderService finderService;

    public static void main(String[] args) {
        SpringApplication.run(MongoSpringDataSampleApp.class, args);
    }

    @Override
    public void run(String... paramArrayOfString) throws Exception {
    	finderService.recherchePrix();

        // save a couple of customers
//        customerDao.save(new Customer("Alice", "Smith", null, 19));
//        customerDao.save(new Customer("Alice", "Jones", null, 26));
//        customerDao.save(new Customer("Bob", "Smith", new Address("Zlota", 22, "00-020", "Warszawa"), 30));
//        customerDao.save(new Customer("Alice", "Brown", null, 80));
//        customerDao.save(new Customer("Alice", "Smedley", null, 52));

        // fetch all customers
        System.out.println("-------------------------------");
//        for (Deal deal : dealDao.findAll()) {
//            System.out.println(deal);
//        }
        System.out.println(dealDao.count());
        System.out.println("-------------------------------");

        System.out.println(dealDao.findOneByAsin("B00ZRT1HSI"));
        System.out.println("-------------------------------");

//        // fetch top 2 customers by last name
//        System.out.println("Customers found with findTop2ByLastName('Smith'):");
//        System.out.println("-------------------------------");
//        for (Customer customer : customerDao.findTop2ByLastName("Smith")) {
//            System.out.println(customer);
//        }
//        System.out.println();
//
//        // fetch top 2 customers by first name
//        System.out.println("Customers found with findTop2ByFirstName('Alice'):");
//        System.out.println("-------------------------------");
//        for (Customer customer : customerDao.findTop2ByFirstName("Alice")) {
//            System.out.println(customer);
//        }
//        System.out.println();
//
//        // fetch an individual customer
//        System.out.println("Customer found with findOneByFirstName('Alice'):");
//        System.out.println("--------------------------------");
//        System.out.println(customerDao.findOneByFirstName("Alice"));
//        System.out.println();
//
//        // fetch an individual customer by first and last name
//        System.out.println("Customer found with findOneByFirstNameAndLastName('Alice', 'Smith'):");
//        System.out.println("--------------------------------");
//        System.out.println(customerDao.findOneByFirstNameAndLastName("Alice", "Smith"));
//        System.out.println();
//
//        System.out.println("Customers found with findByFirstName('Alice'):");
//        System.out.println("--------------------------------");
//        for (Customer customer : customerDao.findByFirstName("Alice")) {
//            System.out.println(customer);
//        }
//        System.out.println();
//
//        System.out.println("Customers found with findByLastName('Smith'):");
//        System.out.println("--------------------------------");
//        for (Customer customer : customerDao.findByLastName("Smith")) {
//            System.out.println(customer);
//        }
//        System.out.println();
//
//        System.out.println("Customers found with findByCustomersLastName('Smith'):");
//        System.out.println("--------------------------------");
//        for (Customer customer : customerDao.findByCustomersLastName("Smith")) {
//            System.out.println(customer);
//        }
//        System.out.println();
//
//        System.out.println("Customers countByLastName('smith'): " + customerDao.countByLastName("smith"));
//        System.out.println(
//                "Customers countByLastNameIgnoreCase('smith'): "
//                        + customerDao.countByLastNameIgnoreCase("smith"));
//
//        // sample modification
//        Customer tmpCust = customerDao.findOneByFirstNameAndLastName("Alice", "Smith");
//        tmpCust.setLastName("Smiths");
//        tmpCust = customerDao.save(tmpCust);
//
//        System.out.println("Customers found with findByAddressCity('Warszawa'):");
//        System.out.println("--------------------------------");
//        for (Customer customer : customerDao.findByAddressCity("Warszawa")) {
//            System.out.println(customer);
//        }
//        System.out.println();
//
//        System.out.println("Customers found with findByAgeBetween(20, 60):");
//        System.out.println("--------------------------------");
//        for (Customer customer : customerDao.findByAgeBetween(20, 60)) {
//            System.out.println(customer);
//        }
    }

}