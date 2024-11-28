package com.LOL.Pros.Controller;

import com.LOL.Pros.Exception.ResponseCode;
import com.LOL.Pros.Service.UserService;
import com.LOL.Pros.dto.request.Update.UserUpdateRequest;
import com.LOL.Pros.dto.request.UserCreateRequest;
import com.LOL.Pros.dto.response.ApiResponse;
import com.LOL.Pros.dto.response.UserResponse;
import com.LOL.Pros.dto.transferDTO.TransferUserGetAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;


    //API create user
    @PostMapping("/create")
    ApiResponse<UserResponse> createNewUser(@RequestBody UserCreateRequest request)
    {
        return ApiResponse.<UserResponse>builder()
                .data(userService.createNewUser(request))
                .code(ResponseCode.SUCCESS_STATUS.getCode())
                .message(ResponseCode.SUCCESS_STATUS.getMessage())
                .build();
    }

    //API get all user
    @GetMapping("/allUsers")
    ApiResponse<List<TransferUserGetAll>> getAllUser()
    {
        return ApiResponse.<List<TransferUserGetAll>>builder()
                .data(userService.getAllUser())
                .code(ResponseCode.SUCCESS_STATUS.getCode())
                .message(ResponseCode.SUCCESS_STATUS.getMessage())
                .build();
    }

    //API get user by user name
    @GetMapping("/{userName}")
    ApiResponse<UserResponse> getUserByUserName(@PathVariable("userName") String userName)
    {
        return ApiResponse.<UserResponse>builder()
                .data(userService.getUserByName(userName))
                .code(ResponseCode.SUCCESS_STATUS.getCode())
                .message(ResponseCode.SUCCESS_STATUS.getMessage())
                .build();
    }

    // API update User
    @PostMapping("/update")
    ApiResponse<UserResponse> updateUserByUserName(@RequestBody UserUpdateRequest request)
    {
        return ApiResponse.<UserResponse>builder()
                .data(userService.updateUserByUserName(request))
                .code(ResponseCode.SUCCESS_STATUS.getCode())
                .message(ResponseCode.SUCCESS_STATUS.getMessage())
                .build();
    }

}
