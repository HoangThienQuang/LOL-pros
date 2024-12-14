package com.LOL.Pros.dto.transferDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferUpdateCaptain {
    private String teamName;
    private String captainName;
}
