package com.sports.badmintonclub.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.sports.badmintonclub.models.Match;

public interface MatchAccess {
	Match startMatch(Match match);

	Match endMatch(int courtNo, long loserPhoneNo);

	List<Match> getMatchByDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime);

	List<Match> getMatchByPhoneNumber(long phoneNumber, LocalDateTime startDateTime, LocalDateTime endDateTime);

	Boolean checkPlayer(long player1PhoneNo, long player2PhoneNo);
}
