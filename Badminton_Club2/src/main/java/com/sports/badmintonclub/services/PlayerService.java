package com.sports.badmintonclub.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sports.badmintonclub.dao.PlayerAccess;
import com.sports.badmintonclub.models.Player;
import com.sports.badmintonclub.repositories.PlayerRepository;

@Service

public class PlayerService implements PlayerAccess {
	@Autowired
	private PlayerRepository playerRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(PlayerService.class);

	@Override
	public Boolean createPlayer(Player player) throws IllegalArgumentException, IllegalStateException {
		LOGGER.trace("Entering createPlayer() method");
		LOGGER.debug("Authenticating Player");
		if (playerRepository.findById(player.getPhoneNumber()).isPresent()) {
			LOGGER.error("Authentication failed");
			return false;
		} else {
			LOGGER.info("Authenticated successfully Player added into database");
			playerRepository.save(player);
			return true;
		}
	}

	@Override
	public Optional<Player> getPlayerByPhonenumber(long phoneNumber)
			throws IllegalArgumentException, IllegalStateException {
		LOGGER.trace("Entering getPlayerByPhonenumber() method");
		LOGGER.debug("Authenticating Player phoneNumber {}",phoneNumber);
		Optional<Player> playerDetails = playerRepository.findById(phoneNumber);
		if (playerDetails.isPresent()) {
			LOGGER.info("Player authenticated successfully");
			return playerDetails;
		} else {
			LOGGER.error("Authentication failed");
			return null;
		}
	}

	@Override
	public List<Player> getPlayers() throws IllegalArgumentException, IllegalStateException {
		LOGGER.trace("Entering getPlayers() method");
		LOGGER.debug("Authenticating Player");
		List<Player> playerDetails = playerRepository.findAll();
		if (playerDetails.isEmpty()) {
			LOGGER.error("Authentication failed");
			return null;
		} else {
			LOGGER.info("Player authenticated successfully");
			return playerDetails;
		}
	}

}
