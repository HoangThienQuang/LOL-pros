package com.Auth_Service.Auth_Service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
    private String id;
    private String username;
    private String fullName;

    private int age;
    private String city;
}
