package com.LOL.Pros.dto.request.Update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamUpdateRequest {
    private String teamName;
    private String sponsors;
    private String captainIngameName;
    private String playerTeamIngameName;
    private String region;
}
