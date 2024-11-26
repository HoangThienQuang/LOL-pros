package com.LOL.Pros.dto.response;

import com.LOL.Pros.Entity.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamResponse {
    private String teamName;
    private Set<String> sponsors;
}
