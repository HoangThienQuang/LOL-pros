package com.LOL.Pros.Service;

import com.LOL.Pros.Entity.Region;
import com.LOL.Pros.Entity.Team;
import com.LOL.Pros.Entity.Tournament;
import com.LOL.Pros.Exception.AppException;
import com.LOL.Pros.Exception.ResponseCode;
import com.LOL.Pros.Repository.RegionRepository;
import com.LOL.Pros.Repository.TeamRepository;
import com.LOL.Pros.Repository.TournamentRepository;
import com.LOL.Pros.dto.request.RegionRequest;
import com.LOL.Pros.dto.request.Update.RegionUpdateRequest;
import com.LOL.Pros.dto.response.RegionResponse;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private TournamentRepository tournamentRepository;
    @Autowired
    private TeamRepository teamRepository;

    public List<Region> getAllRegion()
    {
        return regionRepository.findAll();
    }

    public RegionResponse createEmptyRegion(RegionRequest request)
    {
        Region region = toRegion(request);
        regionRepository.save(region);
        return RegionResponse.builder()
                .regionName(region.getRegionName())
                .regionTournament(region.getRegionTournament())
                .teams(region.getTeams())
                .build();
    }

    public RegionResponse updateRegion(RegionUpdateRequest request)
    {
        Region region = regionRepository.findByRegionName(request.getRegionName()).orElseThrow(()-> new AppException(ResponseCode.REGION_NOT_EXISTED));
        Set<String> tournamentSet = new HashSet<>(Arrays.asList(request.getTournamentName().split(",")));
        Set<String> teamsSet = new HashSet<>(Arrays.asList(request.getTeamName().split(",")));

        for (String tournamentName: tournamentSet)
        {
            region.getRegionTournament().add(tournamentName);
        }

        for (String teamName: teamsSet)
        {
            region.getTeams().add(teamName);
        }

        regionRepository.save(region);
        return RegionResponse.builder()
                .regionName(region.getRegionName())
                .regionTournament(region.getRegionTournament())
                .teams(region.getTeams())
                .build();
    }

//    private Region toRegion(RegionRequest request)
//    {
//        Region region = new Region();
//        region.setRegionName(region.getRegionName());
//        if (Objects.nonNull(request.getRegionName()) && tournamentRepository.existsByTournamentName(request.getRegionName()))
//        {
//            Tournament tournament = tournamentRepository.findByTournamentName(region.getRegionName()).orElseThrow(()-> new AppException(ResponseCode.TOURNAMENT_NOT_EXIST));
//
//            region.setRegionTournament();
//        }
//        return
//    }
    private Region toRegion(RegionRequest request)
    {
        if (regionRepository.findByRegionName(request.getRegionName()).isPresent())
            throw new AppException(ResponseCode.REGION_EXISTED);
        Set<String> tournamentSet = new HashSet<>(Arrays.asList(request.getRegionTournament().split(",")));
        Set<String> teamsSet = new HashSet<>(Arrays.asList(request.getTeams().split(",")));

        return Region.builder()
                .regionName(request.getRegionName())
                .regionTournament(tournamentSet)
                .teams(teamsSet)
                .build();
    }
}
