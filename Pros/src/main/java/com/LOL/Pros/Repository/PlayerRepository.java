package com.LOL.Pros.Repository;

import com.LOL.Pros.Entity.Player;
import com.LOL.Pros.Enum.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
    Optional<Player> findByIngameName(String ingameName);
    Optional<Player> findByPlayerLName(String playerLName);
    Optional<Player> findByPlayerFName(String playerFName);
    List<Player> findByNationality (String national);
    List<Player> findByRole (Role role);
    List<Player> findByCurrentTeam (String currentTeam);
}
