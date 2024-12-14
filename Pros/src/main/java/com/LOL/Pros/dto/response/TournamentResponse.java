package com.LOL.Pros.dto.response;

import com.LOL.Pros.Entity.Region;
import com.LOL.Pros.dto.request.TournamentRequest;
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
public class TournamentResponse {
    private String tournamentName;
    private Date startDate;//YYYY-MM-DD
    private Date endDate;//YYYY-MM-DD
    private Boolean isInternational;
    private String season;
    private String regionName;
    //private int numberOfParticipateTeam;
}
