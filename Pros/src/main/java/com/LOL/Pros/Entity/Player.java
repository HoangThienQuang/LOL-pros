package com.LOL.Pros.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // tự động tạo id kiểu tự động tăng
    @Column(name = "playerId", nullable = false, length = 100)
    private String playerId;

    @Column(name = "ingameName", nullable = false, length = 100)
    private String ingameName;

    @Column(name = "playerFirstName", length = 100)
    private String playerFirstName;

    @Column(name = "playerLastMiddleName", length = 100)
    private String playerLastMiddleName;

    @Column(name = "dob")
    private LocalDate dob; //YYYY-MM-DD

    @Column(name = "nationality", length = 100)
    private String nationality;

    @Column(name = "role", nullable = false, length = 100)
    private String role;

}