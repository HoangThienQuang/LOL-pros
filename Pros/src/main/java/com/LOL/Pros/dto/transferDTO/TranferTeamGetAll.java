package com.LOL.Pros.dto.transferDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TranferTeamGetAll {
    private String teamName;
    //    private Set<String> sponsors;
    private String captainName;
    private List<TransferPlayerTeam> teamPlayer;
}
