package com.LOL.Pros.dto.transferDTO;

import com.LOL.Pros.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferPlayerGetAll {
    private String ingameName;
    private String playerName;
    private LocalDate dob;
    private String nationality;
    private Role role;
    private String team;

}
