package com.sports.badmintonclub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sports.badmintonclub.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	List<Payment> findAllByPlayerPhoneNumber(long phoneNumber);

}
