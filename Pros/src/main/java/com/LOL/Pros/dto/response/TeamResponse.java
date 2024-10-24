package com.LOL.Pros.dto.response;

import com.LOL.Pros.Entity.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamResponse {
    private String id;
    private String teamName;
    private String regionName;
}
