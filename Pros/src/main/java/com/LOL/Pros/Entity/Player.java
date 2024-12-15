package com.LOL.Pros.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @Column(name = "playerId", nullable = false, length = 100)
    private String playerId;

    @Transient // tạo một id không lưu vào DB
    private String prefixed;

    @Transient
    private Long numericPlayerId;

    @PrePersist //trước khi data được lưu vào DB sẽ thực thi hàm dưới để thêm prefix vào ID
    @PostLoad
    public void generateID()
    {
        if (this.numericPlayerId != null) {
            String formattedId = String.format("%04d", this.numericPlayerId);
            this.playerId = "PL" + formattedId;
        }
    }

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