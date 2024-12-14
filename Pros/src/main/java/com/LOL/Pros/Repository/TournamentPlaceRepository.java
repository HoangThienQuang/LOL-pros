package com.LOL.Pros.Repository;

import com.LOL.Pros.Entity.TournamentPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentPlaceRepository extends JpaRepository<TournamentPlace, String> {
}
