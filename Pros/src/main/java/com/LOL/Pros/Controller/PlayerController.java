package com.LOL.Pros.Controller;

import com.LOL.Pros.Entity.Player;
import com.LOL.Pros.Service.PlayerService;
import com.LOL.Pros.dto.request.PlayerCreationRequest;
import com.LOL.Pros.dto.response.PlayerResponse;
import com.LOL.Pros.dto.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping("/allPlayer")
    SuccessResponse<Object> getAllPlayer()
    {
        List<Player> result = playerService.getAllPlayer();
        return SuccessResponse.builder()
                .code(1000)
                .message("Request Success")
                .data(result)
                .build();
    }

    @PostMapping("/createPlayer")
    SuccessResponse<Object> createPlayer(@RequestBody PlayerCreationRequest request)
    {
        return SuccessResponse.builder()
                .code(100)
                .message("success")
                .data(playerService.createPlayer(request))
                .build();
    }
}
