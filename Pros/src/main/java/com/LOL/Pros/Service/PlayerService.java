package com.LOL.Pros.Service;

import com.LOL.Pros.Entity.Player;
import com.LOL.Pros.Entity.Team;
import com.LOL.Pros.Exception.AppException;
import com.LOL.Pros.Exception.ResponseCode;
import com.LOL.Pros.Mapper.PlayerMapper;
import com.LOL.Pros.Repository.PlayerRepository;
import com.LOL.Pros.Repository.TeamRepository;
import com.LOL.Pros.dto.request.PlayerRequest;
import com.LOL.Pros.dto.response.ApiResponse;
import com.LOL.Pros.dto.response.PlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private TeamRepository teamRepository;

    public List<Player> getAllPlayer()
    {
        return playerRepository.findAll();
    }

    public PlayerResponse createPlayer(PlayerRequest request)
    {
        if (playerRepository.findByPlayerName(request.getPlayerName()).isPresent())
            throw new AppException(ResponseCode.PLAYER_EXISTED);
        Player player = toPlayer(request);
        playerRepository.save(player);
        return toPlayerResponse(player);
    }

    public PlayerResponse getPlayerById(String playerId)
    {
        return toPlayerResponse(playerRepository.findById(playerId).orElseThrow(() -> new AppException(ResponseCode.USER_NOT_EXIST)));
    }

    public PlayerResponse getPlayerByName(String playerName)
    {
        return toPlayerResponse(playerRepository.findByPlayerName(playerName).orElseThrow(() -> new AppException(ResponseCode.USER_NOT_EXIST)));
    }

    private Player toPlayer(PlayerRequest request)
    {
        Team currentTeam = teamRepository.findByTeamName(request.getCurrentTeam()).orElseThrow(()->new AppException(ResponseCode.TEAM_NOT_EXIST));
        return Player.builder()
                .ingameName(request.getIngameName())
                .playerName(request.getPlayerName())
                .build();
    }

    private PlayerResponse toPlayerResponse(Player player)
    {
        return PlayerResponse.builder()
                .playerId(player.getPlayerId())
                .ingameName(player.getIngameName())
                .playerName(player.getPlayerName())
                .build();
    }
}
