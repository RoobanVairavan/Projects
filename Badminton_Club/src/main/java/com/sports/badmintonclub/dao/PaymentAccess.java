package com.sports.badmintonclub.dao;

import java.util.List;

import com.sports.badmintonclub.models.Payment;

public interface PaymentAccess {
	Boolean addpayment(Payment payment);
	List<Payment> getPaymentByPhoneNumber(long phoneNumber);
}
