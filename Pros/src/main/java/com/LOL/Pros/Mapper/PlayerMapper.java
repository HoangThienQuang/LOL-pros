package com.LOL.Pros.Mapper;

import com.LOL.Pros.Entity.Player;
import com.LOL.Pros.dto.request.PlayerCreationRequest;
import com.LOL.Pros.dto.response.PlayerResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    Player toPlayer(PlayerCreationRequest request);
    PlayerResponse toPlayerResponse(Player player);
}
