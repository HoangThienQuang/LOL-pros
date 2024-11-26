package com.LOL.Pros.Repository;

import com.LOL.Pros.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
    Optional<Player> findByPlayerName(String playerName);
    Optional<Player> findByIngameName(String ingameName);
}
