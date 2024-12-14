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
import com.LOL.Pros.dto.response.PlayerResponse;
import com.LOL.Pros.dto.transferDTO.TransferPlayerGetAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return players.stream().map(this::toTransferPlayerGetAll).collect(Collectors.toList());
        //return playerRepository.findAll();
    }

    public PlayerResponse createPlayer(PlayerRequest request)
    {
        if (playerRepository.findByIngameName(request.getIngameName()).isPresent()
        )
            throw new AppException(ResponseCode.PLAYER_EXISTED);
        Player player = toPlayer(request);
        playerRepository.save(player);
        return toPlayerResponse(player);
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
}
