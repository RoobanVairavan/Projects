package com.sports.badmintonclub.dao;

import java.util.List;
import java.util.Optional;

import com.sports.badmintonclub.models.Player;

public interface PlayerAccess {
	Boolean createPlayer(Player player);
	Optional<Player> getPlayerByPhonenumber(long phoneNumber);
	List<Player> getPlayers();
}
