package com.LOL.Pros.Controller;

import com.LOL.Pros.Entity.Team;
import com.LOL.Pros.Service.TeamService;
import com.LOL.Pros.dto.request.TeamRequest;
import com.LOL.Pros.dto.response.ApiResponse;
import com.LOL.Pros.dto.response.TeamResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TemController {
    @Autowired
    private TeamService teamService;

    @PostMapping("/create")
    ApiResponse<TeamResponse> createTeam(@RequestBody TeamRequest request)
    {
        return ApiResponse.<TeamResponse>builder()
                .code(100)
                .message("Create team" + request.getTeamName() + "success")
                .data(teamService.createTeam(request))
                .build();
    }

    @GetMapping("/getAll")
    ApiResponse<Object> getAllTeam()
    {
        List<Team> result = teamService.getAllTeam();
        return ApiResponse.builder()
                .code(100)
                .message("Get all team success")
                .data(result)
                .build();
    }
}
