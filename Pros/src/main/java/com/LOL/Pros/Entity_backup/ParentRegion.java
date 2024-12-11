package com.LOL.Pros.Entity_backup;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ParentRegion {
    @Id
    @Column(name = "minorRegionName", nullable = false, length = 100)
    private String minorRegionName;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "minorRegionName", nullable = false)
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "majorRegionName")
    private Region majorRegionName;

}