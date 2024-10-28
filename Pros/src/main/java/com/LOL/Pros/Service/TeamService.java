package com.LOL.Pros.Service;

import com.LOL.Pros.Entity.Player;
import com.LOL.Pros.Entity.PlayerTeam;
import com.LOL.Pros.Entity.Team;
import com.LOL.Pros.Exception.AppException;
import com.LOL.Pros.Exception.ResponseCode;
import com.LOL.Pros.Repository.PlayerRepository;
import com.LOL.Pros.Repository.PlayerTeamRepository;
import com.LOL.Pros.Repository.RegionRepository;
import com.LOL.Pros.Repository.TeamRepository;
import com.LOL.Pros.dto.request.TeamRequest;
import com.LOL.Pros.dto.request.Update.TeamUpdateRequest;
import com.LOL.Pros.dto.response.TeamAddPlayerResponse;
import com.LOL.Pros.dto.response.TeamResponse;
import com.LOL.Pros.dto.response.TeamUpdateResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeamService {
    private static final Logger log = LoggerFactory.getLogger(TeamService.class);
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PlayerTeamRepository playerTeamRepository;

    public List<Team> getAllTeam()
    {
        return teamRepository.findAll();
    }

    public TeamResponse createTeam(TeamRequest request)
    {
        if (teamRepository.findByTeamName(request.getTeamName()).isPresent())
            throw new AppException(ResponseCode.TEAM_EXISTED);
        Set<String> sponsors = new HashSet<>(Arrays.asList(request.getSponsors().split(",")));
        Team team = Team.builder()
                .teamName(request.getTeamName())
                .sponsors(sponsors)
                .build();

        teamRepository.save(team);

        return TeamResponse.builder()
                .teamName(request.getTeamName())
                .sponsors(sponsors)
                .build();
    }

    // thêm các dữ liệu khác vào team
    public TeamUpdateResponse updateTeam(TeamUpdateRequest request)
    {
        //kiểm tra trong db có tồn tại team khớp với team trong request hay không
        Team team = teamRepository.findByTeamName(request.getTeamName()).orElseThrow(()-> new AppException(ResponseCode.TEAM_NOT_EXIST));
        //ép kiểu của sponsors trong request thành dạng list sau đó thêm list vào trong sponsors
        team.getSponsors().addAll(Arrays.asList(request.getSponsors().split(",")));
        //Kiểm tra trong db có tồn tại player khớp với player trong request hay không
        team.setCaptain(playerRepository.findByIngameName(request.getCaptainIngameName()).orElseThrow(()-> new AppException(ResponseCode.PLAYER_NOT_EXIST)));
        //lấy danh sách tuyển thủ trong request thành 1 list<String>
        if (request.getPlayerTeamIngameName() != null) {
            List<String> playerList = Arrays.asList(request.getPlayerTeamIngameName().split(","));
            AddTeamPlayer(playerList, team);
        }
        team.setRegion(regionRepository.findByRegionName(request.getRegion()).orElseThrow(()-> new AppException(ResponseCode.REGION_NOT_EXISTED)));

        teamRepository.save(team);
        return TeamUpdateResponse.builder()
                .teamName(team.getTeamName())
                .sponsors(team.getSponsors())
                .captainName(team.getCaptain().getIngameName())
                .region(team.getRegion().getRegionName())
                .teamPlayers(getTeamPlayers(team.getPlayerTeams()))
                .build();
    }

    //Thêm player vào team
    public void AddTeamPlayer(List<String> players, Team team)
    {
        for (String player : players)
        {
            PlayerTeam playerTeam = PlayerTeam.builder()
                    .playerTeamName(team.getTeamName())
                    .team(team)
                    .build();
            if (playerRepository.findByIngameName(player).isPresent())
                playerTeam.setPlayer(playerRepository.findByIngameName(player).orElseThrow(()->new AppException(ResponseCode.NOT_IMPLEMENT_EXCEPTION)));
            playerTeamRepository.save(playerTeam);
            team.getPlayerTeams().add(playerTeam);
            log.info("Add " + player + " to " + playerTeam.getPlayerTeamName());
        }
    }

    private Set<String> getTeamPlayers(Set<PlayerTeam> playerTeams)
    {
        return  playerTeams.stream().map(playerTeam -> playerTeam.getPlayer().getPlayerName()).collect(Collectors.toSet());
    }
}
