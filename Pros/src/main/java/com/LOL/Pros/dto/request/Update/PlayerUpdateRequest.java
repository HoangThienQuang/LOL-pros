package com.LOL.Pros.dto.request.Update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PlayerUpdateRequest {
    private String ingameName;
    private String playerFirstName;
    private String playerLastMiddleName;
    private LocalDate dob;
    private String nationality;
    private String role;
    private String team;
}
