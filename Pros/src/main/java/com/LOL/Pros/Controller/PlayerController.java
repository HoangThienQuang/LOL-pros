package com.LOL.Pros.Controller;

import com.LOL.Pros.Enum.Role;
import com.LOL.Pros.Service.PlayerService;
import com.LOL.Pros.Service.TeamService;
import com.LOL.Pros.dto.request.PlayerRequest;
import com.LOL.Pros.dto.response.ApiResponse;
import com.LOL.Pros.dto.response.PlayerResponse;
import com.LOL.Pros.dto.transferDTO.TransferPlayerGetAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/player")
@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private TeamService teamService;

    //get All player
    @GetMapping("/all")
    ApiResponse<Object> getAllPlayer()
    {
        List<TransferPlayerGetAll> result = playerService.getAllPlayer();
        return ApiResponse.builder()
                .code(100)
                .message("Get all player success")
                .data(result)
                .build();
    }

    //create player
    @PostMapping("/create")
    ApiResponse<PlayerResponse> createPlayer(@RequestBody PlayerRequest request)
    {
        return ApiResponse.<PlayerResponse>builder()
                .code(100)
                .message("Create player success")
                .data(playerService.createPlayer(request))
                .build();
    }

    //get player by Id
    @GetMapping("/{playerId}")
    ApiResponse<PlayerResponse> getPlayer(@PathVariable("playerId") String playerId)
    {
        return ApiResponse.<PlayerResponse>builder()
                .code(100)
                .message("Get player success")
                .data(playerService.getPlayerById(playerId))
                .build();
    }

    //get player by name
    @PostMapping("/playerName")
    ApiResponse<PlayerResponse> getPlayerByName(@RequestBody String playerName)
    {
        return ApiResponse.<PlayerResponse>builder()
                .code(100)
                .message("Get player success")
                .data(playerService.getPlayerByName(playerName))
                .build();
    }

    //get player by national
    @PostMapping("/playerNation")
    ApiResponse<Object> getPlayerByNation(@RequestBody String nation)
    {
        List<TransferPlayerGetAll> result = playerService.getPlayerByNation(nation);
        return ApiResponse.builder()
                .code(100)
                .message("Get all player success")
                .data(result)
                .build();
    }
    //get player by role
    @PostMapping("/playerRole")
    ApiResponse<Object> getPlayerByRole(@RequestBody String role)
    {
        Role inputRole = Role.valueOf(role.toUpperCase());
        List<TransferPlayerGetAll> result = playerService.getPlayerByRole(inputRole);
        return ApiResponse.builder()
                .code(100)
                .message("Get all player success")
                .data(result)
                .build();
    }

    //get player by team
    @PostMapping("/playerTeam")
    ApiResponse<Object> getPlayerByTeam(@RequestBody String team)
    {
        Team inputTeam = teamService.GetTeamByTeamName(team.toUpperCase());
        List<TransferPlayerGetAll> result = playerService.getPlayerByTeam(inputTeam);
        return ApiResponse.builder()
                .code(100)
                .message("Get all player success")
                .data(result)
                .build();
    }
}
