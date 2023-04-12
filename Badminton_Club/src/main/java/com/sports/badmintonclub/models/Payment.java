package com.sports.badmintonclub.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_details")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "player_phone_number")
	private long playerPhoneNumber;
	@Column(name = "date_of_payment")
	private LocalDateTime dateOfPayment;
	@Column(name = "amount_paid")
	private double amountPaid;

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getPlayerPhoneNumber() {
		return playerPhoneNumber;
	}

	public void setPlayerPhoneNumber(long playerPhoneNumber) {
		this.playerPhoneNumber = playerPhoneNumber;
	}

	public LocalDateTime getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(LocalDateTime dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

}
