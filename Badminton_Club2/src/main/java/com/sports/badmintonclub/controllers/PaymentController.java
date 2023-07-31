package com.sports.badmintonclub.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sports.badmintonclub.dao.PaymentAccess;
import com.sports.badmintonclub.models.Payment;

import jakarta.servlet.ServletException;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentAccess paymentAccess;

	@PostMapping("/addPayment")
	public ResponseEntity<?> createPaymentDetails(@RequestBody Payment paymentDetails)
			throws ServletException, IOException {
		if(paymentAccess.addpayment(paymentDetails)) {
			return ResponseEntity.status(HttpStatus.OK).body("Payment completed successfully");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment incomplete player not found");
		}
	}

	@GetMapping("/viewPayment")
	public ResponseEntity<?> getPaymentDetails(@RequestParam long phoneNumber) throws ServletException, IOException {
		List<Payment> paymentDetails = paymentAccess.getPaymentByPhoneNumber(phoneNumber);
		if (paymentDetails != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(paymentDetails);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Payment History Found");
		}
	}
}
