package com.LOL.Pros.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TournamentRequest {
    private String tournamentName;
    private List<TournamentPlace> tournamentPlaces;
    private Date startDate;//YYYY-MM-DD
    private Date endDate;//YYYY-MM-DD
    private Boolean isInternational;
    private String season;
    private String regionName;
    //private int numberOfParticipateTeam;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TournamentPlace {
        String locationCity;
        String locationArena;
    }
}
