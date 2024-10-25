package com.LOL.Pros.Service;


import com.LOL.Pros.Entity.DomesticTournament;
import com.LOL.Pros.Entity.Tournament;
import com.LOL.Pros.Exception.AppException;
import com.LOL.Pros.Exception.ResponseCode;
import com.LOL.Pros.Mapper.TournamentMapper;
import com.LOL.Pros.Repository.DomesticTournamentRepository;
import com.LOL.Pros.Repository.TournamentRepository;
import com.LOL.Pros.dto.request.DomesticRequest;
import com.LOL.Pros.dto.request.TournamentRequest;
import com.LOL.Pros.dto.response.TournamentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;
    @Autowired
    private TournamentMapper tournamentMapper;
    @Autowired
    private DomesticTournamentRepository domesticTournamentRepository;

    public List<Tournament> getAllTournament()
    {
        return tournamentRepository.findAll();
    }

    public TournamentResponse createDomesticTournament(DomesticRequest request)
    {
        if (tournamentRepository.existsByTournamentName(request.getTournamentName()))
            throw new AppException(ResponseCode.TOURNAMENT_EXISTED);
        DomesticTournament tournament = toDomesticTournament(request);
        tournamentRepository.save(tournament);
        return toTournamentResponse(tournament);
    }



    private DomesticTournament toDomesticTournament(DomesticRequest request)
    {
        return DomesticTournament.builder()
                .tournamentName(request.getTournamentName())
                .competitionPlace(request.getCompetitionPlace())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .numberOfParticipateTeam(request.getNumberOfParticipateTeam())
                .tournamentSeason(request.getSeason())
                .build();
    }

    private TournamentResponse toTournamentResponse(DomesticTournament tournament)
    {
        return TournamentResponse.builder()
                .tournamentName(tournament.getTournamentName())
                .competitionPlace(tournament.getCompetitionPlace())
                .startDate(tournament.getStartDate())
                .endDate(tournament.getEndDate())
                .numberOfParticipateTeam(tournament.getNumberOfParticipateTeam())
                .season(tournament.getTournamentSeason())
                .build();
    }
}
