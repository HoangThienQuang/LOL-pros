package com.LOL.Pros.Repository;

import com.LOL.Pros.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
}
