package com.LOL.Pros.Entity_backup;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity
public class Region {
    @Id
    @Column(name = "regionName", nullable = false, length = 100)
    private String regionName;

    @Column(name = "description", length = 100)
    private String description;

}