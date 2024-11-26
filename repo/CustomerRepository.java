package com.springeasystock.easystock.repo;


import com.springeasystock.easystock.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//import org.springframework.stereotype.
import java.util.List;

//@Repository
//@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // Additional query methods can be added here if needed
    // Custom query method
//    @Query("SELECT c FROM Customer c WHERE c.name = ?")
//    List<Customer> findByName(@Param("name")String name);
//    @Query("SELECT c FROM Customer c WHERE c.name = ?1")
//    List<Customer> findByName(String name);
}

