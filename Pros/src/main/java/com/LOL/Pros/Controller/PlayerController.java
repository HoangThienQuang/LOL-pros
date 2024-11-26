package com.LOL.Pros.Controller;

import com.LOL.Pros.Entity.Player;
import com.LOL.Pros.Service.PlayerService;
import com.LOL.Pros.dto.request.PlayerRequest;
import com.LOL.Pros.dto.response.ApiResponse;
import com.LOL.Pros.dto.response.PlayerResponse;
import com.LOL.Pros.dto.transferDTO.TransferPlayerGetAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping("/allPlayers")
    ApiResponse<Object> getAllPlayer()
    {
        List<TransferPlayerGetAll> result = playerService.getAllPlayer();
        return ApiResponse.builder()
                .code(100)
                .message("Get all player success")
                .data(result)
                .build();
    }

    @PostMapping("/createPlayer")
    ApiResponse<PlayerResponse> createPlayer(@RequestBody PlayerRequest request)
    {
        return ApiResponse.<PlayerResponse>builder()
                .code(100)
                .message("Create player success")
                .data(playerService.createPlayer(request))
                .build();
    }

    @GetMapping("/{playerId}")
    ApiResponse<PlayerResponse> getPlayer(@PathVariable("playerId") String playerId)
    {
        return ApiResponse.<PlayerResponse>builder()
                .code(100)
                .message("Get player success")
                .data(playerService.getPlayerById(playerId))
                .build();
    }

    @PostMapping("/player")
    ApiResponse<PlayerResponse> getPlayerByName(@RequestBody String playerName)
    {
        return ApiResponse.<PlayerResponse>builder()
                .code(100)
                .message("Get player success")
                .data(playerService.getPlayerByName(playerName))
                .build();
    }
}
