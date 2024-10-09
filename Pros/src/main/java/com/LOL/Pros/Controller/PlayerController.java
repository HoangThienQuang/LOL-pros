package com.LOL.Pros.Controller;

import com.LOL.Pros.Entity.Player;
import com.LOL.Pros.Service.PlayerService;
import com.LOL.Pros.dto.request.PlayerRequest;
import com.LOL.Pros.dto.response.ApiResponse;
import com.LOL.Pros.dto.response.PlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping("/allPlayers")
    ApiResponse<Object> getAllPlayer()
    {
        List<Player> result = playerService.getAllPlayer();
        return ApiResponse.builder()
                .code(100)
                .message("Get all player success")
                .data(result)
                .build();
    }

    @PostMapping("/create")
    ApiResponse<PlayerResponse> createPlayer(@RequestBody PlayerRequest request)
    {
        return ApiResponse.<PlayerResponse>builder()
                .code(100)
                .message("Create player success")
                .data(playerService.createPlayer(request))
                .build();
    }
}
