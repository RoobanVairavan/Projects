package com.sports.badmintonclub.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sports.badmintonclub.dao.PlayerAccess;
import com.sports.badmintonclub.models.Player;

import jakarta.servlet.ServletException;

@RestController
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	private PlayerAccess playerAccess;

	@PostMapping("/addPlayer")
	public ResponseEntity<?> createPlayer(@RequestBody Player playerDetails) throws ServletException, IOException {
		if (playerAccess.createPlayer(playerDetails)) {
			return ResponseEntity.status(HttpStatus.OK).body("Player Added");
		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Player already Exist");
		}
	}

	@GetMapping("/viewPlayerByPhonenumber")
	public ResponseEntity<?> getPlayerdetails(@RequestParam long phoneNumber) {
		Optional<Player> playerDetails = playerAccess.getPlayerByPhonenumber(phoneNumber);
		if (playerDetails != null && playerDetails.isPresent()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(playerDetails);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player Not Found");
		}
	}

	@GetMapping("/viewPlayers")
	public ResponseEntity<?> getAllPlayerdetails() {
		List<Player> playerDetails = playerAccess.getPlayers();
		if (playerDetails != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(playerDetails);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Players Not Found");
		}
	}
}
