package com.sports.badmintonclub.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "match_details")
public class Match {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "court_no")
	private int courtNo;
	@Enumerated(EnumType.STRING)
	private MatchStatus status;
	@Column(name = "player_1_phone_no")
	private long player1PhoneNo;
	@Column(name = "player_2_phone_no")
	private long player2PhoneNo;
	@Column(name = "start_date_time")
	private LocalDateTime startDateTime;
	@Column(name = "end_date_time")
	private LocalDateTime endDateTime;
	@Column(name = "loser_phone_no")
	private long loserPhoneNo;
	@Column(name = "amount")
	private double amount;

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourtNo() {
		return courtNo;
	}

	public void setCourtNo(int courtNo) {
		this.courtNo = courtNo;
	}

	public MatchStatus getStatus() {
		return status;
	}

	public void setStatus(MatchStatus status) {
		this.status = status;
	}

	public long getPlayer1PhoneNo() {
		return player1PhoneNo;
	}

	public void setPlayer1PhoneNo(long player1PhoneNo) {
		this.player1PhoneNo = player1PhoneNo;
	}

	public long getPlayer2PhoneNo() {
		return player2PhoneNo;
	}

	public void setPlayer2PhoneNo(long player2PhoneNo) {
		this.player2PhoneNo = player2PhoneNo;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	public long getLoserPhoneNo() {
		return loserPhoneNo;
	}

	public void setLoserPhoneNo(long loserPhoneNo) {
		this.loserPhoneNo = loserPhoneNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
