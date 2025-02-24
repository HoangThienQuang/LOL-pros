package com.User_Service.User_Service.Controller;

import com.User_Service.User_Service.Exception.ResponseCode;
import com.User_Service.User_Service.Service.UserService;
import com.User_Service.User_Service.dto.ApiResponse;
import com.User_Service.User_Service.dto.request.UserCreateRequest;
import com.User_Service.User_Service.dto.respose.UserCreateResponse;
import com.User_Service.User_Service.dto.respose.UserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //API to create user
    @PostMapping("/create")
    ApiResponse<UserCreateResponse> createNewUser(@RequestBody UserCreateRequest request)
    {
        return ApiResponse.<UserCreateResponse>builder()
                .code(1000)
                .mess(ResponseCode.SUCCESS_STATUS.getMess())
                .data(userService.createUser(request))
                .build();
    }

//    //API to get user info by name
//    @GetMapping("/{username}")
//    ApiResponse<UserInfoResponse> getUserInfoByName(@PathVariable String username)
//    {
//        return ApiResponse.<UserInfoResponse>builder()
//                .code(1000)
//                .mess(ResponseCode.SUCCESS_STATUS.getMess())
//                .data(userService.getUserInfoByName(username))
//                .build();
//    }
}
