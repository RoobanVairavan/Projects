package com.sports.badmintonclub;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sports.badmintonclub.models.Player;
import com.sports.badmintonclub.repositories.PlayerRepository;

@SpringBootTest
class BadmintonClubApplicationTests {
	
	@Autowired
	PlayerRepository playerRepository;
	
	@Test
	void method1() {
		Player player=new Player();
		player.setName("Rooban");
		player.setPhoneNumber(12);
		playerRepository.save(player);
		Optional<Player>checkOptional= playerRepository.findById(12l);
		assertNotNull(checkOptional);
	}

}
