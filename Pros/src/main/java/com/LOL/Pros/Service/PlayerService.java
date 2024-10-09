package com.LOL.Pros.Service;

import com.LOL.Pros.Entity.Player;
import com.LOL.Pros.Mapper.PlayerMapper;
import com.LOL.Pros.Repository.PlayerRepository;
import com.LOL.Pros.dto.request.PlayerRequest;
import com.LOL.Pros.dto.response.ApiResponse;
import com.LOL.Pros.dto.response.PlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PlayerMapper playerMapper;

    public List<Player> getAllPlayer()
    {
        return playerRepository.findAll();
    }

    public PlayerResponse createPlayer(PlayerRequest request)
    {
        Player player = playerMapper.toPlayer(request);
        playerRepository.save(player);
        return playerMapper.toPlayerResponse(player);
    }
}