package com.LOL.Pros.Repository;

import com.LOL.Pros.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
    Optional<Player> findByIngameName(String ingameName);
    List<Player> findByPlayerLastMiddleName(String playerLName);
    List<Player> findByPlayerFirstName(String playerFName);
    List<Player> findByNationality (String national);
    List<Player> findByRole (String role);
}
