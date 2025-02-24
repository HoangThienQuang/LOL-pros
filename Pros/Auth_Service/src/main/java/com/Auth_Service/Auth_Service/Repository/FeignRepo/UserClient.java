package com.Auth_Service.Auth_Service.Repository.FeignRepo;

import com.Auth_Service.Auth_Service.dto.request.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "userService", url = "http://localhost:8081/Identity/user")
public interface UserClient {
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    void createUserInfo(@RequestBody UserInfo userInfo);
}
