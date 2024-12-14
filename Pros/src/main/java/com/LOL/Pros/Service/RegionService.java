package com.LOL.Pros.Service;

import com.LOL.Pros.Entity.Region;
import com.LOL.Pros.Entity.RegionalTournament;
import com.LOL.Pros.Entity.TeamRegion;
import com.LOL.Pros.Exception.AppException;
import com.LOL.Pros.Exception.ResponseCode;
import com.LOL.Pros.Repository.RegionRepository;
import com.LOL.Pros.Repository.RegionalTournamentRepository;
import com.LOL.Pros.Repository.TeamRegionRepository;
import com.LOL.Pros.dto.request.RegionRequest;
import com.LOL.Pros.dto.response.RegionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private TeamRegionRepository teamRegionRepository;
    @Autowired
    private RegionalTournamentRepository regionalTournamentRepository;

    public List<RegionResponse> getAllRegion()
    {
        List<Region> regionResult = regionRepository.findAll();

        List<RegionResponse> result = new ArrayList<>();
        for (Region region : regionResult) {
            Set<TeamRegion> teamRegions = teamRegionRepository.findByRegionName(region);
            Set<RegionalTournament> regionalTournaments = regionalTournamentRepository.findByRegionName(region);

            RegionResponse regionResponse = new RegionResponse();
            regionResponse.setRegionName(region.getRegionName());
            regionResponse.setTeams(teamRegions.stream().map(TeamRegion::getTeam).collect(Collectors.toSet()));
            regionResponse.setDomesticTournaments(regionalTournaments);

            result.add(regionResponse);
        }

        return result;
    }

    public RegionResponse createRegion(RegionRequest request)
    {
        if (regionRepository.findByRegionName(request.getRegionName()).isPresent())
            throw new AppException(ResponseCode.REGION_EXISTED);
        Region region = Region.builder()
                .regionName(request.getRegionName())
                .build();
        regionRepository.save(region);
        return RegionResponse.builder()
                .regionName(region.getRegionName())
                .build();
    }

    //public RegionResponse addTeamToRegion()
}
