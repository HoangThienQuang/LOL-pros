package com.LOL.Pros.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerRequest {//các thông tin người dùng cần nhập để tạo 1 hàng dữ liệu
    private String ingameName;
    private String playerFirstName;
    private String playerLastMiddleName;
    private LocalDate dob;
    private String nationality;
    private String role;
}
