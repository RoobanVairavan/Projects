package com.sports.badmintonclub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sports.badmintonclub.models.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
