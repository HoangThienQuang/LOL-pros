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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq")// tự động tạo id kiểu chuỗi
    @SequenceGenerator(name = "entity_seq", sequenceName = "entity_sequence",allocationSize = 1)//tạo chuỗi với giá trị tự động tăng
    @Column(name = "playerId", nullable = false, length = 100)
    private String playerId;

    @Transient // tạo một id không lưu vào DB
    private String prefixed;

    @PrePersist //trước khi data được lưu vào DB sẽ thực thi hàm dưới để thêm prefix vào ID
    @PostLoad
    public void generateID()
    {
        this.prefixed = "Player-" + this.playerId;
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