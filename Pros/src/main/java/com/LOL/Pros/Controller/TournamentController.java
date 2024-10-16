package com.LOL.Pros.Controller;

import com.LOL.Pros.Service.TournamentService;
import com.LOL.Pros.dto.request.TournamentRequest;
import com.LOL.Pros.dto.response.ApiResponse;
import com.LOL.Pros.dto.response.TournamentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    @PostMapping("/tournament/create")
    ApiResponse<TournamentResponse> createTournament(@RequestBody TournamentRequest request)
    {
        return ApiResponse.<TournamentResponse>builder()
                .code(100)
                .message("Create new tournament success")
                .data(tournamentService.createTournament(request))
                .build();
    }
}
