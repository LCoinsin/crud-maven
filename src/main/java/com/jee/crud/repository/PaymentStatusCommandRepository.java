package com.jee.crud.repository;

import com.jee.crud.model.PaymentStatusCommand;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentStatusCommandRepository extends JpaRepository<PaymentStatusCommand, Integer> {
	
	@Query(value = "SELECT * FROM payment_status_command p WHERE p.state=false", nativeQuery = true)
	List<PaymentStatusCommand> findAllPayment();

}
