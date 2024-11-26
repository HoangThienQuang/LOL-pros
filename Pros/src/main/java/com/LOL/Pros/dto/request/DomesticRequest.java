package com.LOL.Pros.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DomesticRequest {
    private String tournamentName;
    private String competitionPlace;
    private Date startDate;//YYYY-MM-DD
    private Date endDate;//YYYY-MM-DD
    private int numberOfParticipateTeam;
    private int season;
    private String region;
}
