package com.sports.badmintonclub.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sports.badmintonclub.dao.MatchAccess;
import com.sports.badmintonclub.models.Match;

import jakarta.servlet.ServletException;

@RestController
@RequestMapping("/match")
public class MatchController {
	@Autowired
	private MatchAccess matchAccess;

	@PostMapping("/startMatch")
	public ResponseEntity<?> startMatch(@RequestBody Match matchDetails) throws ServletException, IOException{
		Boolean check = matchAccess.checkPlayer(matchDetails.getPlayer1PhoneNo(), matchDetails.getPlayer2PhoneNo());
		if (check) {
			Match details = matchAccess.startMatch(matchDetails);
			if (details != null) {
				return ResponseEntity.status(HttpStatus.OK).body("Match Started");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Cannot start match: court is currently occupied");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot start match: Player not found");
		}
	}

	@PutMapping("/endMatch")
	public ResponseEntity<?> endMatch(@RequestParam int courtNo, long loserPhoneNo) throws ServletException, IOException {
		Match details = matchAccess.endMatch(courtNo, loserPhoneNo);
		if (details != null) {
			return ResponseEntity.status(HttpStatus.OK).body("Match Ended");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No accessible court found to stop the game or loserPhoneNumber mismatch");
		}
	}

	@GetMapping("/viewMatch")
	public ResponseEntity<?> getMatchByDateTime(@RequestParam LocalDateTime startDateTime, LocalDateTime endDateTime) throws ServletException, IOException{
		List<Match> matchDetails = matchAccess.getMatchByDateTime(startDateTime, endDateTime);
		if (matchDetails != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(matchDetails);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Match Not Found");
		}
	}

	@GetMapping("/viewMatchByPhoneNumber")
	public ResponseEntity<?> getMatchByPhoneNumber(@RequestParam long phoneNumber, LocalDateTime startDateTime,
			LocalDateTime endDateTime) throws ServletException, IOException{
		List<Match> matchDetails = matchAccess.getMatchByPhoneNumber(phoneNumber, startDateTime, endDateTime);
		if (matchDetails != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(matchDetails);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Match Not Found");
		}
	}
}
