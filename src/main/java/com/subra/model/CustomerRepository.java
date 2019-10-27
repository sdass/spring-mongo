package com.subra.model;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String>{

	@Query
	Customer findByFname(String fname);
	
	 @Query 	List<Customer> findByLname(String lname); // works
	
	@Query 	
	Page<Customer> findByLname(String lname, Pageable pageable);
	

}
