package com.jee.crud.repository;

import com.jee.crud.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer save(Customer customer);
}
