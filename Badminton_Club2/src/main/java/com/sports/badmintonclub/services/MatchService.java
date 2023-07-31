package com.sports.badmintonclub.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sports.badmintonclub.dao.MatchAccess;
import com.sports.badmintonclub.models.MatchStatus;
import com.sports.badmintonclub.models.Player;
import com.sports.badmintonclub.models.Match;
import com.sports.badmintonclub.repositories.MatchRepository;
import com.sports.badmintonclub.repositories.PlayerRepository;

@Service
public class MatchService implements MatchAccess {

	@Autowired
	private MatchRepository matchRepository;
	@Autowired
	private PlayerRepository playerRepository;

	private static final int RUPEE = 2;

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchService.class);

	@Override
	public Match startMatch(Match match) throws IllegalArgumentException, IllegalStateException {
		LOGGER.trace("Entering startMatch() method");
		LOGGER.debug("Authenticating Match");
		Optional<Match> matchData = matchRepository.findByCourtNoAndStatus(match.getCourtNo(), MatchStatus.STARTED);
		if (matchData.isEmpty()) {
			LOGGER.info("Authenticated successfully Match started");
			LocalDateTime dateTime = LocalDateTime.now();
			match.setStatus(MatchStatus.STARTED);
			match.setStartDateTime(dateTime);
			return matchRepository.save(match);
		} else {
			LOGGER.error("Authentication failed");
			return null;
		}
	}

	@Override
	public Match endMatch(int courtNo, long loserPhoneNo)
			throws IllegalArgumentException, IllegalStateException {
		LOGGER.trace("Entering endMatch() method");
		LOGGER.debug("Authenticating Match");
		Optional<Match> matchData = matchRepository.findByCourtNoAndStatus(courtNo, MatchStatus.STARTED);
		if (matchData.isPresent()) {
			LocalDateTime dateTime = LocalDateTime.now();
			Match matchDetails = matchData.get();
			matchDetails.setStatus(MatchStatus.ENDED);
			matchDetails.setEndDateTime(dateTime);
			matchDetails.setLoserPhoneNo(loserPhoneNo);
			double duration = (double) Duration.between(matchDetails.getStartDateTime(), matchDetails.getEndDateTime())
					.toMinutes();
			double amount = duration * RUPEE;
			matchDetails.setAmount(amount);
			Optional<Player> playerData = playerRepository.findById(matchDetails.getLoserPhoneNo());
			if(playerData.isPresent()) {
				LOGGER.info("Player authenticated successfully");
				Player playerDetails = playerData.get();
				playerDetails.setAccountBalance(amount);
				playerRepository.save(playerDetails);
			}else {
				LOGGER.error("Authentication failed Player not found");
				return null;
			}
			LOGGER.info("Authenticated successfully Match ended");
			return matchRepository.save(matchDetails);
		} else {
			LOGGER.error("Authentication failed");
			return null;
		}
	}

	@Override
	public List<Match> getMatchByDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime)
			throws IllegalArgumentException, IllegalStateException {
		LOGGER.trace("Entering getMatchByDateTime() method");
		LOGGER.debug("Authenticating Match from {} to {} ", startDateTime, endDateTime);
		List<Match> matchDetails = matchRepository.findByStartDateTimeBetween(startDateTime, endDateTime);
		if (matchDetails.isEmpty()) {
			LOGGER.error("Authentication failed");
			return null;
		} else {
			LOGGER.info("Match authenticated successfully");
			return matchDetails;
		}
	}

	@Override
	public List<Match> getMatchByPhoneNumber(long phoneNumber, LocalDateTime startDateTime,
			LocalDateTime endDateTime) throws IllegalArgumentException, IllegalStateException {
		LOGGER.trace("Entering getMatchByPhoneNumber() method");
		LOGGER.debug("Authenticating Match with {} from {} to {} ", phoneNumber, startDateTime, endDateTime);
		List<Match> matchDetails = matchRepository.findByPlayer1PhoneNoOrPlayer2PhoneNoAndStartDateTimeBetween(
				phoneNumber, phoneNumber, startDateTime, endDateTime);
		if (matchDetails.isEmpty()) {
			LOGGER.error("Authentication failed");
			return null;
		} else {
			LOGGER.info("Match authenticated successfully");
			return matchDetails;
		}
	}

	@Override
	public Boolean checkPlayer(long player1PhoneNo, long player2PhoneNo)
			throws IllegalArgumentException, IllegalStateException {
		LOGGER.trace("Entering checkPlayer() method");
		LOGGER.debug("Authenticating Player with phoneNumber1 {} and phoneNumber2 {}", player1PhoneNo, player2PhoneNo);
		Optional<Player> playerData1 = playerRepository.findById(player1PhoneNo);
		Optional<Player> playerData2 = playerRepository.findById(player2PhoneNo);
		if (playerData1.isPresent() && playerData2.isPresent()) {
			LOGGER.info("Player authenticated successfully");
			return true;
		} else {
			LOGGER.error("Authentication failed");
			return false;
		}
	}

}
