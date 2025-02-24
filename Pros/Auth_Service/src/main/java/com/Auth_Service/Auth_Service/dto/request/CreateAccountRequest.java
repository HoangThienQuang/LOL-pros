package com.Auth_Service.Auth_Service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAccountRequest {
    private String username;
    private String password;

    private String firstName;
    private String lastName;

    private int age;
    private String city;
}
