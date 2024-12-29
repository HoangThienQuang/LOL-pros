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
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewPlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    //public returnType functionName(input value)
    //{
        //TODO codes
        //return returnType;
    //}

    public PlayerResponse createNewPlayer(PlayerRequest request)
    {
        if (playerRepository.findByIngameName(request.getIngameName()).isPresent())
            throw new AppException(ResponseCode.PLAYER_EXISTED);

        Player player = Player.builder()
                .ingameName(request.getIngameName())
                .playerFirstName(request.getPlayerFirstName())
                .playerLastMiddleName(request.getPlayerLastMiddleName())
                .role(request.getRole())
                .dob(request.getDob())
                .nationality(request.getNationality())
                .build();

        String sql = "SELECT NEXT VALUE FOR entity_sequence";
        Long sequenceValue = (Long) entityManager.createNativeQuery(sql).getSingleResult();
        player.setNumericPlayerId(sequenceValue);

        playerRepository.save(player);

        return PlayerResponse.builder()
                .playerId(player.getPlayerId())
                .playerFirstName(player.getPlayerFirstName())
                .playerLastMiddleName(player.getPlayerLastMiddleName())
                .ingameName(player.getIngameName())
                .nationality(player.getNationality())
                .role(player.getRole())
                .build();
    }
}
