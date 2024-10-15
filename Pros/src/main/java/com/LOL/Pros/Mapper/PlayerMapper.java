package com.LOL.Pros.Mapper;

import com.LOL.Pros.Entity.Player;
import com.LOL.Pros.dto.request.PlayerRequest;
import com.LOL.Pros.dto.response.PlayerResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    //Player toPlayer(PlayerRequest request);
    //PlayerResponse toPlayerResponse(Player player);
}
