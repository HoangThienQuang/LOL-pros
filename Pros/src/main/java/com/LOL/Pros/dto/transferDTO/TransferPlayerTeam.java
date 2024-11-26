package com.LOL.Pros.dto.transferDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferPlayerTeam {
    private String playerName;
    private String teamName;
    private LocalDate startDate;
    private LocalDate endDate;
}
