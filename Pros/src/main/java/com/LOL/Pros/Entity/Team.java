package com.LOL.Pros.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
@Entity
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class Team {

    private static final AtomicLong counter = new AtomicLong(1);//tạo giá trị tự động tăng

    @Id
    @Column(name = "teamId", nullable = false, length = 100)
    private String teamId;

    @PrePersist // tạo hàm gọi thêm prefix vào ID trước khi lưu xuống DB
    public void generateId() {
        this.teamId = "TM" + counter.getAndIncrement();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamCaptain")
    private Player teamCaptain;

    @Column(name = "teamName", nullable = false, length = 100)
    private String teamName;

    @Column(name = "teamLogo", length = 100)
    private String teamLogo;

}