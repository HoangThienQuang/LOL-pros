package com.LOL.Pros.Controller;

import com.LOL.Pros.Entity.Team;
import com.LOL.Pros.Enum.Role;
import com.LOL.Pros.Service.NewPlayerService;
import com.LOL.Pros.Service.PlayerService;
import com.LOL.Pros.Service.TeamService;
import com.LOL.Pros.dto.request.PlayerRequest;
import com.LOL.Pros.dto.response.ApiResponse;
import com.LOL.Pros.dto.response.PlayerResponse;
import com.LOL.Pros.dto.transferDTO.TransferPlayerGetAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/player") //tiền tố URL, ...localhost:8080/player
@RestController
public class PlayerController { // Controller <- Service <- repo(quản lý bởi JPA - JPA liên kết với DB)
    @Autowired
    private PlayerService playerService;
    @Autowired
    private NewPlayerService newPlayerService;
    @Autowired
    private TeamService teamService;

    //get All player
    @GetMapping("/all") //...localhost:8080/player/all
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
                .data(newPlayerService.createNewPlayer(request))
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
    @PostMapping("/playerFirstName")
    ApiResponse<Object> getPlayerByFName(@RequestBody String playerFirstName)
    {
        List<PlayerResponse> result = playerService.getPlayerByFName(playerFirstName);
        return ApiResponse.builder()
                .code(100)
                .message("Get player success")
                .data(result)
                .build();
    }

    @PostMapping("/playerLastAndMiddleName")
    ApiResponse<Object> getPlayerByLName(@RequestBody String playerLastMiddleName)
    {
        List<PlayerResponse> result = playerService.getPlayerByLName(playerLastMiddleName);
        return ApiResponse.builder()
                .code(100)
                .message("Get player success")
                .data(result)
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
        List<TransferPlayerGetAll> result = playerService.getPlayerByRole(role);
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
