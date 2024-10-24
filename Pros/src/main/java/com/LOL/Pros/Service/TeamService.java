package com.LOL.Pros.Service;

import com.LOL.Pros.Entity.Region;
import com.LOL.Pros.Entity.Team;
import com.LOL.Pros.Exception.AppException;
import com.LOL.Pros.Exception.ResponseCode;
import com.LOL.Pros.Repository.RegionRepository;
import com.LOL.Pros.Repository.TeamRepository;
import com.LOL.Pros.dto.request.TeamRequest;
import com.LOL.Pros.dto.response.TeamResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private RegionRepository regionRepository;

    public List<Team> getAllTeam()
    {
        return teamRepository.findAll();
    }

    public TeamResponse createTeam(TeamRequest request)
    {
        Region region = regionRepository.findByRegionName(request.getRegionName())
                .orElseThrow(()-> new AppException(ResponseCode.REGION_NOT_EXISTED));
        Team team = Team.builder()
                .teamName(request.getTeamName())
                .build();
        teamRepository.save(team);
        region.getTeams().add(team);
        regionRepository.save(region);
        return TeamResponse.builder()
                .teamName(team.getTeamName())
                .regionName(region.getRegionName())
                .build();
    }
}
