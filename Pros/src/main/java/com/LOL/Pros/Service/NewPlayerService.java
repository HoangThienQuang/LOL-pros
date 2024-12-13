package com.LOL.Pros.Service;

import com.LOL.Pros.Entity.Player;
import com.LOL.Pros.Enum.Role;
import com.LOL.Pros.Exception.AppException;
import com.LOL.Pros.Exception.ResponseCode;
import com.LOL.Pros.Repository.PlayerRepository;
import com.LOL.Pros.Repository.TeamRepository;
import com.LOL.Pros.dto.request.PlayerRequest;
import com.LOL.Pros.dto.response.ApiResponse;
import com.LOL.Pros.dto.response.PlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewPlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public returnType functionName(input value)
    {
        //code
        return returnType;
    }

    public PlayerResponse createNewPlayer(PlayerRequest request)
    {
        if (playerRepository.findByingameName(request.getIngameName()).isPresent())
            throw new AppException(ResponseCode.PLAYER_EXISTED);

        Player player = Player.builder()
                .ingameName(request.getIngameName())
                .role(request.getRole())
                .build();
        playerRepository.save(player);

        return PlayerResponse.builder()
                .playerId(player.getPlayerId())
                .ingameName(player.getIngameName())
                .role(player.getRole())
                .build();
    }
}
