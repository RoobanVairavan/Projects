package com.sports.badmintonclub.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sports.badmintonclub.dao.PaymentAccess;
import com.sports.badmintonclub.models.Payment;
import com.sports.badmintonclub.models.Player;
import com.sports.badmintonclub.repositories.PaymentRepository;
import com.sports.badmintonclub.repositories.PlayerRepository;

@Service
public class PaymentService implements PaymentAccess {
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private PlayerRepository playerRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

	@Override
	public Boolean addpayment(Payment payment)
			throws IllegalArgumentException, IllegalStateException {
		LOGGER.trace("Entering addpayment() method");
		LOGGER.debug("Authenticating Player");
		Optional<Player> playerData = playerRepository.findById(payment.getPlayerPhoneNumber());
		if (playerData.isPresent()) {
			LOGGER.info("Player authenticated successfully");
			Player playerDetails = playerData.get();
			double amount = (playerDetails.getAccountBalance() - payment.getAmountPaid());
			playerDetails.setAccountBalance(amount);
			playerRepository.save(playerDetails);
			LocalDateTime dateTime = LocalDateTime.now();
			payment.setDateOfPayment(dateTime);
			LOGGER.info("Payment completed successfully");
			paymentRepository.save(payment);
			return true;
		} else {
			LOGGER.error("Authentication failed");
			return false;
		}
	}

	@Override
	public List<Payment> getPaymentByPhoneNumber(long phoneNumber)
			throws IllegalArgumentException, IllegalStateException {
		LOGGER.trace("Entering getPaymentByPhoneNumber() method");
		LOGGER.debug("Authenticating Payment phoneNumber {}",phoneNumber);
		List<Payment> paymentDetails = paymentRepository.findAllByPlayerPhoneNumber(phoneNumber);
		if (paymentDetails.isEmpty()) {
			LOGGER.error("Authentication failed");
			return null;
		} else {
			LOGGER.info("Payment authenticated successfully");
			return paymentDetails;
		}
	}

}
