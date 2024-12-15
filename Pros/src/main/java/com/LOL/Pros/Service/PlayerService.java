package com.LOL.Pros.Service;

import com.LOL.Pros.Entity.Player;
import com.LOL.Pros.Entity.PlayerTeamHistory;
import com.LOL.Pros.Entity.Team;
import com.LOL.Pros.Enum.Role;
import com.LOL.Pros.Exception.AppException;
import com.LOL.Pros.Exception.ResponseCode;
import com.LOL.Pros.Mapper.PlayerMapper;
import com.LOL.Pros.Repository.PlayerRepository;
import com.LOL.Pros.Repository.PlayerTeamRepository;
import com.LOL.Pros.Repository.TeamRepository;
import com.LOL.Pros.dto.request.PlayerRequest;
import com.LOL.Pros.dto.request.Update.PlayerUpdateRequest;
import com.LOL.Pros.dto.response.PlayerResponse;
import com.LOL.Pros.dto.response.PlayerUpdateResponse;
import com.LOL.Pros.dto.transferDTO.TransferPlayerGetAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerTeamRepository playerTeamRepository;

    public List<TransferPlayerGetAll> getAllPlayer()
    {
        List<Player> players = playerRepository.findAll();
        List<PlayerTeamHistory> playerTeamHistories = playerTeamRepository.findPlayerTeamHistoriesByPlayerInAndEndDateIsNull(players);

        Map<Player, List<PlayerTeamHistory>> playerTeamHistoriesMap = playerTeamHistories.stream().collect(Collectors.groupingBy(PlayerTeamHistory::getPlayer));

        return players.stream().map(player -> this.toTransferPlayerGetAll(player,
                playerTeamHistoriesMap.getOrDefault(player, new ArrayList<>()))).collect(Collectors.toList());
    }

    public PlayerResponse createPlayer(PlayerRequest request)
    {
        if (playerRepository.findByIngameName(request.getIngameName()).isPresent()) {
            throw new AppException(ResponseCode.PLAYER_EXISTED);
        }
        Player player = toPlayer(request);
        playerRepository.save(player);
        return toPlayerResponse(player);
    }

    @Transactional
    public PlayerUpdateResponse updatePlayer(PlayerUpdateRequest request)
    {
        Optional<Player> playerOptional = playerRepository.findByIngameName(request.getIngameName());
        if (playerOptional.isEmpty()) {
            throw new AppException(ResponseCode.PLAYER_NOT_EXIST);
        }

        Optional<Team> teamOptional = teamRepository.findByTeamName(request.getTeam());
        if (teamOptional.isEmpty()) {
            throw new AppException(ResponseCode.TEAM_NOT_EXIST);
        }

        Player player = playerOptional.get();
        Team team = teamOptional.get();

        if (!Objects.equals(player.getPlayerFirstName(), request.getPlayerFirstName())) {
            player.setPlayerFirstName(request.getPlayerFirstName());
        }
        if (!Objects.equals(player.getPlayerLastMiddleName(), request.getPlayerLastMiddleName())) {
            player.setPlayerFirstName(request.getPlayerFirstName());
        }
        if (!Objects.equals(player.getDob(), request.getDob())) {
            player.setPlayerFirstName(request.getPlayerFirstName());
        }
        if (!Objects.equals(player.getNationality(), request.getNationality())) {
            player.setPlayerFirstName(request.getPlayerFirstName());
        }
        if (!Objects.equals(player.getRole(), request.getRole())) {
            player.setPlayerFirstName(request.getPlayerFirstName());
        }

        playerRepository.save(player);

        this.transferPlayerToTeam(player, team);

        return new PlayerUpdateResponse(player.getPlayerId(), player.getIngameName(), player.getPlayerFirstName(),
                player.getPlayerLastMiddleName(), player.getDob(), player.getNationality(), player.getRole());
    }

    private void transferPlayerToTeam(Player player, Team team) {
        try {
            playerTeamRepository.updatePlayerTeam(player.getPlayerId(), team.getTeamId());
        } catch (Exception e) {
            throw new AppException(ResponseCode.NOT_IMPLEMENT_EXCEPTION);
        }
    }

    public PlayerResponse getPlayerById(String playerId)
    {
        return toPlayerResponse(playerRepository.findById(playerId).orElseThrow(() -> new AppException(ResponseCode.PLAYER_NOT_EXIST)));
    }

    public List<PlayerResponse> getPlayerByFName(String playerFirstName)
    {
        return playerRepository.findByPlayerFirstName(playerFirstName).stream().map(this::toPlayerResponse).collect(Collectors.toList());
    }

    public List<PlayerResponse> getPlayerByLName(String playerLastMiddleName)
    {
        return playerRepository.findByPlayerLastMiddleName(playerLastMiddleName).stream().map(this::toPlayerResponse).collect(Collectors.toList());
    }

    public List<TransferPlayerGetAll> getPlayerByNation(String nation)
    {
        List<Player> players = playerRepository.findByNationality(nation);
        return players.stream().map(this::toTransferPlayerGetAll).collect(Collectors.toList());
        //return playerRepository.findAll();
    }

    public List<TransferPlayerGetAll> getPlayerByRole(String role)
    {
        List<Player> players = playerRepository.findByRole(role);
        return players.stream().map(this::toTransferPlayerGetAll).collect(Collectors.toList());
        //return playerRepository.findAll();
    }

    public List<TransferPlayerGetAll> getPlayerByTeam(Team team)
    {
        List<PlayerTeamHistory> playerTeamHistories = playerTeamRepository.findByTeam(team);

        List<Player> players = playerTeamHistories.stream().map(PlayerTeamHistory::getPlayer).toList();
        return players.stream().map(this::toTransferPlayerGetAll).collect(Collectors.toList());
        //return playerRepository.findAll();
    }


    private Player toPlayer(PlayerRequest request)
    {
        return Player.builder()
                .ingameName(request.getIngameName())
                .playerFirstName(request.getPlayerFirstName())
                .playerLastMiddleName(request.getPlayerLastMiddleName())
                .dob(request.getDob())
                .nationality(request.getNationality())
                .role(checkRole(request.getRole()).toString())
                .build();
    }

    private PlayerResponse toPlayerResponse(Player player)
    {
        return PlayerResponse.builder()
                .playerId(player.getPlayerId())
                .ingameName(player.getIngameName())
                .playerFirstName(player.getPlayerFirstName())
                .playerLastMiddleName(player.getPlayerLastMiddleName())
                .role(player.getRole())
                .nationality(player.getNationality())
                .dob(player.getDob())
                .build();
    }
    private Role checkRole(String role)
    {
        if (role == null)
            return null;
        return switch (role.toUpperCase()) {
            case "TOP" -> Role.TOP;
            case "JUNG" -> Role.JUNG;
            case "MID" -> Role.MID;
            case "AD" -> Role.AD;
            case "SP" -> Role.SP;
            default -> null;
        };
    }

    private TransferPlayerGetAll toTransferPlayerGetAll(Player player)
    {
        return TransferPlayerGetAll.builder()
                .playerFirstName(player.getPlayerFirstName())
                .playerLastMiddleName(player.getPlayerLastMiddleName())
                .ingameName(player.getIngameName())
                .dob(player.getDob())
                .nationality(player.getNationality())
                .role(player.getRole())
                //.team(player.getCurrentTeam())
                .build();
    }

    private TransferPlayerGetAll toTransferPlayerGetAll(Player player, List<PlayerTeamHistory> playerTeamHistories)
    {
        String team = null;
        if (!playerTeamHistories.isEmpty()) {
            team = playerTeamHistories.get(0).getTeam().getTeamName();
        }
        return TransferPlayerGetAll.builder()
                .playerFirstName(player.getPlayerFirstName())
                .playerLastMiddleName(player.getPlayerLastMiddleName())
                .ingameName(player.getIngameName())
                .dob(player.getDob())
                .nationality(player.getNationality())
                .role(player.getRole())
                .team(team)
                .build();
    }
}
