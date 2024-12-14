package com.LOL.Pros.Controller;

import com.LOL.Pros.Service.TournamentService;
import com.LOL.Pros.dto.request.TournamentRequest;
import com.LOL.Pros.dto.response.ApiResponse;
import com.LOL.Pros.dto.response.TournamentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tournament")
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    @GetMapping("/getAll")
    ApiResponse<Object> getAllTournament()
    {
        return ApiResponse.builder()
                .code(100)
                .message("Get all tournaments success")
                .data(tournamentService.getAllTournament())
                .build();
    }

    @PostMapping("/create")
    ApiResponse<TournamentResponse> createDomesticTournament(@RequestBody TournamentRequest request)
    {
        return ApiResponse.<TournamentResponse>builder()
                .code(100)
                .message("Create new tournament success")
                .data(tournamentService.createTournament(request))
                .build();
    }
}
