package com.LOL.Pros.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Team {
    @Id
    private String teamId;
}
