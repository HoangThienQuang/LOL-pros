package com.LOL.Pros.Service;

import com.LOL.Pros.Exception.AppException;
import com.LOL.Pros.Exception.ResponseCode;
import com.LOL.Pros.Repository.PlayerRepository;
import com.LOL.Pros.Repository.PlayerTeamRepository;
import com.LOL.Pros.Repository.RegionRepository;
import com.LOL.Pros.Repository.TeamRepository;
import com.LOL.Pros.dto.request.TeamRequest;
import com.LOL.Pros.dto.request.Update.TeamUpdateCaptainRequest;
import com.LOL.Pros.dto.request.Update.TeamUpdateRequest;
import com.LOL.Pros.dto.response.TeamResponse;
import com.LOL.Pros.dto.response.TeamUpdateResponse;
import com.LOL.Pros.dto.transferDTO.TranferTeamGetAll;
import com.LOL.Pros.dto.transferDTO.TransferPlayerTeam;
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

    public List<TranferTeamGetAll> getAllTeam()
    {
        //return teamRepository.findAll();
        List<Team> teams = teamRepository.findAll();
        return teams.stream().map(this::convertToPlayerTeamDTO).collect(Collectors.toList());
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
        if (request.getCaptainIngameName() != null)
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
            //kiem tra su ton tai cua player va team trong DB
            if (playerRepository.findByIngameName(player).isPresent() && teamRepository.findByTeamName(team.getTeamName()).isPresent()) {
                //tạo 1 playerTeam/contract giữa team và player
                PlayerTeam playerTeam = new PlayerTeam();
                playerTeam.setPlayerTeamName("Contract " + player + " and " + team.getTeamName());
                playerTeam.setTeam(team);
                Player player1 = playerRepository.findByIngameName(player).orElseThrow(() -> new AppException(ResponseCode.NOT_IMPLEMENT_EXCEPTION));
                playerTeam.setPlayer(player1);
                playerTeamRepository.save(playerTeam);
                //thêm playerTeam vào trong Team
                team.getPlayerTeams().add(playerTeam);
                //thêm playerTeam vào trong player
                player1.getPlayerTeams().add(playerTeam);
                //set team hien tai cho player
                player1.setCurrentTeam(team.getTeamName());
                log.info("Add " + player + " to " + team.getTeamName());
            }
        }
    }

    private Set<String> getTeamPlayers(Set<PlayerTeam> playerTeams)
    {
        return  playerTeams.stream().map(playerTeam -> playerTeam.getPlayer().getPlayerName()).collect(Collectors.toSet());
    }

    private TranferTeamGetAll convertToPlayerTeamDTO(Team team)
    {
        List<TransferPlayerTeam> DTOList = team.getPlayerTeams()
                .stream().map(playerTeam ->
                        new TransferPlayerTeam(
                                playerTeam.getPlayer().getPlayerName(),
                                playerTeam.getTeam().getTeamName(),
                                playerTeam.getStartDate(),
                                playerTeam.getEndDate()))
                .toList();

        return TranferTeamGetAll.builder()
                .teamName(team.getTeamName())
                .sponsors(team.getSponsors())
                .captainName(team.getCaptain().getPlayerName())
                .teamPlayer(DTOList)
                .build();
    }
    public TranferTeamGetAll updateCaptain(TeamUpdateCaptainRequest request)
    {
        //Kiem tra team co ton tai hay khong
        Team team = teamRepository.findByTeamName(request.getTeamName()).orElseThrow(()-> new AppException(ResponseCode.TEAM_NOT_EXIST));
        if (teamRepository.findByCaptainIngameName(request.getPlayerIngameName()) != null)
            throw new AppException(ResponseCode.CAPTAIN_EXISTED);
        team.setCaptain(playerRepository.findByIngameName(request.getPlayerIngameName()).orElseThrow(()-> new AppException(ResponseCode.PLAYER_NOT_EXIST)));
        teamRepository.save(team);
        return convertToPlayerTeamDTO(team);
    }

    public Team GetTeamByTeamName(String teamName)
    {
        return teamRepository.findByTeamName(teamName).orElseThrow(() -> new AppException(ResponseCode.TEAM_NOT_EXIST));
    }
}
