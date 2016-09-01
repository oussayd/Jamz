package org.example.spring.mongodb.dao;

import org.example.spring.mongodb.entity.Deal;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * DAO class for {@link Deal} entity.
 *
 *
 */
public interface DealDao extends MongoRepository<Deal, String> {
	
    Deal findOneByAsinAndPays(String asin, String pays);
    
    Deal findOneByAsin(String asin);

    long countByAsin(String asin);


  }