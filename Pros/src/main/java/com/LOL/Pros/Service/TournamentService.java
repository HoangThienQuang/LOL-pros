package com.LOL.Pros.Service;


import com.LOL.Pros.Entity.InternationalTournament;
import com.LOL.Pros.Entity.Region;
import com.LOL.Pros.Entity.RegionalTournament;
import com.LOL.Pros.Entity.Tournament;
import com.LOL.Pros.Entity.TournamentPlace;
import com.LOL.Pros.Exception.AppException;
import com.LOL.Pros.Exception.ResponseCode;
import com.LOL.Pros.Mapper.TournamentMapper;
import com.LOL.Pros.Repository.InternationalTournamentRepository;
import com.LOL.Pros.Repository.RegionRepository;
import com.LOL.Pros.Repository.RegionalTournamentRepository;
import com.LOL.Pros.Repository.TournamentPlaceRepository;
import com.LOL.Pros.Repository.TournamentRepository;
import com.LOL.Pros.dto.request.TournamentRequest;
import com.LOL.Pros.dto.response.TournamentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;
    @Autowired
    private TournamentMapper tournamentMapper;
    @Autowired
    private InternationalTournamentRepository internationalTournamentRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private RegionalTournamentRepository regionalTournamentRepository;
    @Autowired
    private TournamentPlaceRepository tournamentPlaceRepository;

    public List<Tournament> getAllTournament()
    {
        return tournamentRepository.findAll();
    }

    @Transactional
    public TournamentResponse createTournament(TournamentRequest request) {
        if (tournamentRepository.findByTournamentName(request.getTournamentName()).isPresent()) {
            throw new AppException(ResponseCode.TOURNAMENT_EXISTED);
        }

        Tournament tournament = new Tournament();

        tournament.setTournamentName(request.getTournamentName());
        tournament.setStartDate(request.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        tournament.setEndDate(request.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        tournamentRepository.save(tournament);

        this.createTournamentPlacement(tournament, request);

        if (Boolean.TRUE.equals(request.getIsInternational())) {
            this.createInternationalTournament(tournament);
        } else {
            this.createRegionTournament(tournament, request);
        }

        return new TournamentResponse(tournament.getTournamentName(), request.getStartDate(), request.getEndDate(),
                request.getIsInternational(), request.getSeason(), request.getRegionName());
    }

    private void createInternationalTournament(Tournament tournament) {
        InternationalTournament internationalTournament = new InternationalTournament();
        internationalTournament.setTournament(tournament);

        internationalTournamentRepository.save(internationalTournament);
    }

    private void createRegionTournament(Tournament tournament, TournamentRequest request) {
        Optional<Region> regionOptional = regionRepository.findByRegionName(request.getRegionName());

        Region region;
        if (regionOptional.isPresent()) {
            region = regionOptional.get();
        } else {
            throw new AppException(ResponseCode.REGION_NOT_EXISTED);
        }

        RegionalTournament regionalTournament = new RegionalTournament();

        regionalTournament.setTournament(tournament);
        regionalTournament.setSeason(request.getSeason());
        regionalTournament.setRegionName(region);

        regionalTournamentRepository.save(regionalTournament);
    }

    private void createTournamentPlacement(Tournament tournament, TournamentRequest request) {
        List<TournamentPlace> tournamentPlaceList = new ArrayList<>();

        List<TournamentRequest.TournamentPlace> tournamentPlaces = request.getTournamentPlaces();
        for (TournamentRequest.TournamentPlace tournamentPlace : tournamentPlaces) {
            TournamentPlace tournamentPlaceEntity = new TournamentPlace();

            tournamentPlaceEntity.setTournament(tournament);
            tournamentPlace.setLocationCity(tournamentPlaceEntity.getId().getLocationCity());
            tournamentPlace.setLocationArena(tournamentPlaceEntity.getId().getLocationArena());

            tournamentPlaceList.add(tournamentPlaceEntity);
        }

        tournamentPlaceRepository.saveAll(tournamentPlaceList);
    }

//    public TournamentResponse createDomesticTournament(DomesticRequest request)
//    {
//        if (tournamentRepository.existsByTournamentName(request.getTournamentName()))
//            throw new AppException(ResponseCode.TOURNAMENT_EXISTED);
//        DomesticTournament tournament = toDomesticTournament(request);
//        tournamentRepository.save(tournament);
//        return toTournamentResponse(tournament);
//    }
//
//
//
//    private RegionalTournament toDomesticTournament(DomesticRequest request)
//    {
//        return DomesticTournament.builder()
//                .tournamentName(request.getTournamentName())
//                .competitionPlace(request.getCompetitionPlace())
//                .startDate(request.getStartDate())
//                .endDate(request.getEndDate())
//                .numberOfParticipateTeam(request.getNumberOfParticipateTeam())
//                .tournamentSeason(request.getSeason())
//                .region(regionRepository.findByRegionName(request.getRegion()).orElseThrow(()-> new AppException(ResponseCode.REGION_NOT_EXISTED)))
//                .build();
//    }
//
//    private TournamentResponse toTournamentResponse(RegionalTournament tournament)
//    {
//        return TournamentResponse.builder()
//                .tournamentName(tournament.getTournamentName())
//                .competitionPlace(tournament.getCompetitionPlace())
//                .startDate(tournament.getStartDate())
//                .endDate(tournament.getEndDate())
//                .numberOfParticipateTeam(tournament.getNumberOfParticipateTeam())
//                .season(tournament.getTournamentSeason())
//                .region(tournament.getRegion())
//                .build();
//    }
}
