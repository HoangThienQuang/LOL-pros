package com.LOL.Pros.Service;


import com.LOL.Pros.Entity.Tournament;
import com.LOL.Pros.Exception.AppException;
import com.LOL.Pros.Exception.ResponseCode;
import com.LOL.Pros.Mapper.TournamentMapper;
import com.LOL.Pros.Repository.TournamentRepository;
import com.LOL.Pros.dto.request.TournamentRequest;
import com.LOL.Pros.dto.response.TournamentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;
    @Autowired
    private TournamentMapper tournamentMapper;

//    public TournamentResponse createTournament(TournamentRequest request)
//    {
//        if (tournamentRepository.existsByTournamentName(request.getTournamentName()))
//            throw new AppException(ResponseCode.TOURNAMENT_EXISTED);
//        Tournament tournament = tournamentMapper.toTournament(request);
//        tournamentRepository.save(tournament);
//        return tournamentMapper.toTournamentResponse(tournament);
//    }
}
