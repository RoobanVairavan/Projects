package com.sports.badmintonclub.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sports.badmintonclub.models.Match;
import com.sports.badmintonclub.models.MatchStatus;

public interface MatchRepository extends JpaRepository<Match, Integer> {

	Optional<Match> findByCourtNoAndStatus(int courtNo, MatchStatus started);

	List<Match> findByStartDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

	List<Match> findByPlayer1PhoneNoOrPlayer2PhoneNoAndStartDateTimeBetween(long phoneNumber, long phoneNumber2,
			LocalDateTime startDateTime, LocalDateTime endDateTime);

}
