package com.LOL.Pros.dto.request.Update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegionUpdateRequest {
    private String regionName;
    private String tournamentName;
    private String teamName;
}
